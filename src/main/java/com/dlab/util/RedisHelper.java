package com.dlab.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * @deprecated Jedis的封装
 * @author ddh
 *
 */
public class RedisHelper {

	private static JedisPool pool = null;
	
	static {
		if(pool == null){
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(500);
			config.setMaxIdle(10);
			config.setTestOnBorrow(true);
			config.setTestOnReturn(true);
			
			String RedisIP = "192.168.89.111";
			int RedisPort = 6379;
			pool = new JedisPool(config, RedisIP, RedisPort, 100000);
		}
	}
	
	private static RedisHelper Instance = null;
	
	private RedisHelper(){}
	
	public static RedisHelper getInstance(){
		if(Instance == null){
			Instance = new RedisHelper();
		}
		return Instance;
	}
	
	public static void returnResource(JedisPool pool, Jedis jedis){
		if(jedis != null){
			pool.returnResource(jedis);
		}
		
	}
	/**
	 * 读取数据
	 * @param key
	 * @return
	 */
	public static String get(String key){
		
		String value = null;
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			value = jedis.get(key);
		} catch(Exception e){
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally{
			returnResource(pool, jedis);
		}
		return value;
	}
	
	/**
	 * 设置数据
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean set(String key, String value){
		Jedis jedis = null;
		boolean bool = false;
		try{
			jedis = pool.getResource();
			jedis.set(key, value);
			bool = true;
		} catch(Exception e){
			//pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return bool;
		
	}
	
	/**
	 * 清空数据
	 * @return
	 */
	public static boolean FlushDB(){
		JedisPool pool = null;
		Jedis jedis = null;
		boolean bool = false;
		try{
			jedis = pool.getResource();
			jedis.flushDB();
			bool = true;
		} catch(Exception e){
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return bool;
	}
}
