package com.sample.redis.pubsub;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.sample.redis.model.Customer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class Publisher {
	
	@Autowired
	private JedisPool jedisPool;
	
	@Autowired
	CustomersData customersData;
	
	private String nextBillFetchDiff = "12s";
	
	private int maxQueueSize = 5;
	
	private int nextQueueTimeInterval = 3000; //in mills
	
	@Bean
	public void publish() {
        Jedis jedis = jedisPool.getResource();
        String queueName = customersData.getCustomerList();
        jedis.set("name", queueName);
        jedis.set("shadowkey:name", "");
        jedis.expire("shadowkey:name", 10);

    }
	
	public void publishBulkRecords() {
		List<Customer> list = customersData.getCustomersList();
		for(Customer customer: list) {
//			publish(customer);
			System.out.println(customer);
		}
	}
	
	public void getExpirationTimeInSeconds(LocalDateTime nextBillFetchTime) {
		LocalDateTime currentDate = LocalDate.now().atStartOfDay();
		
	}
	
	public String getQueueName(LocalDateTime lt) {
		return lt.getDayOfMonth()+""+lt.getMonth()+""+lt.getYear()+""+lt.getSecond();
	}
	
	public LocalDateTime getNextBillFetchTime(LocalDate billDueDate) {
		LocalDateTime nextFetchTime = billDueDate.atStartOfDay();
		if(nextBillFetchDiff.contains("s")) {
			nextFetchTime = nextFetchTime.plusSeconds(getTime());
		}
		else if(nextBillFetchDiff.contains("m")) {
			nextFetchTime = nextFetchTime.plusMinutes(getTime());
		}
		else if(nextBillFetchDiff.contains("h")) {
			nextFetchTime = nextFetchTime.plusHours(getTime());
		}
		else {
			nextFetchTime = nextFetchTime.plusDays(getTime());
		}
		return nextFetchTime;
	}
	
	public int getTime() {
		int length = nextBillFetchDiff.length();
		return Integer.parseInt(nextBillFetchDiff.substring(0,length-1));
	}

}
