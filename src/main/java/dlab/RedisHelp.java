package dlab;

import redis.clients.jedis.*;

public class RedisHelp extends redis.clients.jedis.Jedis{
	private static RedisHelp Instance = null;
	
	private RedisHelp(){}
	
	public static RedisHelp getInstance(){
		if( Instance == null){
			Instance = new RedisHelp();
		}
		return Instance;
	}
}
