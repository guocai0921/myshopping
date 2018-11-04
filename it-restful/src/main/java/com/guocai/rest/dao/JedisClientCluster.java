package com.guocai.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisCluster;

public class JedisClientCluster implements JedisClient{
	
	@Autowired
	private JedisCluster cluster;

	@Override
	public String get(String key) {
		return cluster.get(key);
	}

	@Override
	public String set(String key, String value) {
		// TODO Auto-generated method stub
		return cluster.set(key, value);
	}

	@Override
	public String hget(String hkey, String key) {
		// TODO Auto-generated method stub
		return cluster.hget(hkey, key);
	}

	@Override
	public long hset(String hkey, String key, String value) {
		// TODO Auto-generated method stub
		return cluster.hset(hkey, key, value);
	}

	@Override
	public long incr(String key) {
		// TODO Auto-generated method stub
		return cluster.incr(key);
	}

	@Override
	public long decr(String key) {
		// TODO Auto-generated method stub
		return cluster.decr(key);
	}

	@Override
	public long expire(String key, int second) {
		// TODO Auto-generated method stub
		return cluster.expire(key, second);
	}

	@Override
	public long ttl(String key) {
		// TODO Auto-generated method stub
		return cluster.ttl(key);
	}

	@Override
	public long del(String key) {
		// TODO Auto-generated method stub
		return cluster.del(key);
	}

	@Override
	public long hdel(String hkey, String key) {
		// TODO Auto-generated method stub
		return cluster.hdel(hkey, key);
	}

}
