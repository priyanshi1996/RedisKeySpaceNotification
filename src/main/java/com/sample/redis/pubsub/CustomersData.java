package com.sample.redis.pubsub;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.redis.model.Customer;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class CustomersData {

	@Autowired
	JedisPool jedisPool;
	
	public List<Customer> getCustomersList() {
		LocalDate currentTime = LocalDate.now();
		List<Customer> list = new LinkedList<>();
		list.add(new Customer("9927032294","BSESRA","1233890",currentTime));
		list.add(new Customer("8869029631","CESCOB","57798203", currentTime));
		list.add(new Customer("9412604499","MESCOB","456203", currentTime));
		return list;
	}
//	
	public String getCustomerList() {
		Jedis jedis = jedisPool.getResource();
		String queueName = "CustomerList";
		LocalDate currentTime = LocalDate.now();
		jedis.lpush(queueName,new Customer("9927032294","BSESRA","1233890",currentTime).toString());
		jedis.lpush(queueName,new Customer("8869029631","CESCOB","57798203", currentTime).toString());
		jedis.lpush(queueName,new Customer("9412604499","MESCOB","456203", currentTime).toString());
		return queueName;
	}

}
