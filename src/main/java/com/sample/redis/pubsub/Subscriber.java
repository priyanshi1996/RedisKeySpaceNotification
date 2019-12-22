package com.sample.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class Subscriber{
	
	@Autowired
	private JedisPool jedisPool;
	
	@Autowired
	private KeyExpiredListener keyExpiredListener;
	
	@Bean
	public void subscribe() {
        Jedis jedis = jedisPool.getResource();
        jedis.psubscribe(keyExpiredListener,"__keyevent*__:*");
        
    }

}
