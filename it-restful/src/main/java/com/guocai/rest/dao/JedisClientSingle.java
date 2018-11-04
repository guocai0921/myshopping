package com.guocai.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle implements JedisClient {

	@Autowired
	private JedisPool pool;
	
	@Override
	public String get(String key) {
		Jedis jedis = pool.getResource();
		String str = jedis.get(key);
		jedis.close();
		return str;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = pool.getResource();
		String str = jedis.set(key,value);
		jedis.close();
		return str;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = pool.getResource();
		String str = jedis.hget(hkey, key);
		jedis.close();
		return str;
	}

	@Override
	public long hset(String hkey, String key, String value) {
		Jedis jedis = pool.getResource();
		long str = jedis.hset(hkey,key,value);
		jedis.close();
		return str;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = pool.getResource();
		Long value = jedis.incr(key);
		jedis.close();
		return value;
	}

	@Override
	public long decr(String key) {
		Jedis jedis = pool.getResource();
		Long value = jedis.decr(key);
		jedis.close();
		return value;
	}

	@Override
	public long expire(String key, int second) {
		Jedis jedis = pool.getResource();
		Long value = jedis.expire(key, second);
		jedis.close();
		return value;
	}

	@Override
	public long ttl(String key) {
		Jedis jedis = pool.getResource();
		Long value = jedis.ttl(key);
		jedis.close();
		return value;
	}

	@Override
	public long del(String key) {
		Jedis jedis = pool.getResource();
		Long value = jedis.del(key);
		jedis.close();
		return value;
	}

	@Override
	public long hdel(String hkey, String key) {
		Jedis jedis = pool.getResource();
		Long value = jedis.hdel(hkey,key);
		jedis.close();
		return value;
	}

}
