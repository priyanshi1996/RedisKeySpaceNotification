package com.sample.redis.pubsub;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

@Service
public class KeyExpiredListener extends JedisPubSub{
	
	@Autowired
	private JedisPool jedisPool;
	
	@Override
    public void onPMessage(String pattern, String channel, String message) {

        String key = message.split(":")[1];
        System.out.println("key: "+key);
        readMessage(key);
    }
	
	public void readMessage(String key) {
			Jedis jedis = jedisPool.getResource();
			String queueName = jedis.get(key);
			System.out.println("QueueName: "+queueName);
			
			List<String> list = jedis.lrange(queueName, 0, -1);
			System.out.println(list.size());
			for(String customer:list) {
				System.out.println("Customer: "+customer);
			}
			jedis.del(queueName);
	}

}
