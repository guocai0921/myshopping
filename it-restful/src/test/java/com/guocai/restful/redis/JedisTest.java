package com.guocai.restful.redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	
	/**
	 * 测试redis单机版连接
	 */
	@Test
	public void testJediSingle() {
		// 创建一个jedis对象
		Jedis jedis = new Jedis("192.168.254.135", 6379);
		// 调用jedis对象的方法,方法名称和redis的方法命名方式一致
		jedis.set("str", "Hello World!");
		String string = jedis.get("str");
		System.out.println(string);
		// 关闭jedis
		jedis.close();
	}
	
	/**
	 * 使用连接池连接redis
	 */
	@Test
	public void testJedisPool() {
		// 创建Jedis连接池
		JedisPool pool = new JedisPool("192.168.254.135", 6379);
		
		// 从连接池中获取jedis对象
		Jedis jedis = pool.getResource();		
		String a = jedis.get("str");
		System.out.println(a);
		// 关闭Jedis对象
		jedis.close();
		pool.close();
	}
	
	/**
	 * 连接集群测试
	 */
	@Test
	public void testCluster () {
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.254.135", 7001));
        nodes.add(new HostAndPort("192.168.254.135", 7002));
        nodes.add(new HostAndPort("192.168.254.135", 7003));
        nodes.add(new HostAndPort("192.168.254.135", 7004));
        nodes.add(new HostAndPort("192.168.254.135", 7005));
        nodes.add(new HostAndPort("192.168.254.135", 7006));
        nodes.add(new HostAndPort("192.168.254.135", 7007));
        nodes.add(new HostAndPort("192.168.254.135", 7008));
        JedisCluster cluster = new JedisCluster(nodes );
        cluster.set("key1", "1000");
        System.out.println(cluster.get("key1"));
        cluster.close();
	}
	
	/**
	 * spring整合jedis单机版
	 */
	@Test
	public void testSpringJedisSingle () {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
		JedisPool pool = (JedisPool) context.getBean("redisClient");
		Jedis jedis = pool.getResource();
		String str = jedis.get("str");
		System.out.println(str);
		jedis.close();
		pool.close();
		((ClassPathXmlApplicationContext)context).close();
	}
	
	/**
	 * spring整合redis集群测试
	 */
	@Test
	public void testSpringJedisCluster() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
		JedisCluster jedisCluster = (JedisCluster) context
				.getBean("jedisCluster");
		jedisCluster.set("name", "zhangsan");
		String value = jedisCluster.get("key1");
		System.out.println(value);
		((ClassPathXmlApplicationContext)context).close();
	}
	
}












