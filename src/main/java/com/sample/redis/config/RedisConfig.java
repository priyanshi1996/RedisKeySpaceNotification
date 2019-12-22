package com.sample.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig{
  private JedisPoolConfig buildPoolConfig() {
	    final JedisPoolConfig poolConfig = new JedisPoolConfig();
	    return poolConfig;
	}
  
	@Bean
	public JedisPool jedisPool() {
		return new JedisPool(buildPoolConfig(), "127.0.0.1", 6379);
	}
}