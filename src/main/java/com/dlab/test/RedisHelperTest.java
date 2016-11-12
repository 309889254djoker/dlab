package com.dlab.test;

import com.dlab.util.*;

public class RedisHelperTest {

	public static void main(String[] args){
		
		RedisHelper rh1 = RedisHelper.getInstance();
		RedisHelper rh2 = RedisHelper.getInstance();
		System.out.println(rh1);
		System.out.println(rh2);
		for(int i=0; i<20; i++){
			rh1.set("key" + new String().valueOf(i), "value" + new String().valueOf(i));
		}
		
		for(int i=0; i<10; i++){
			System.out.println("key:->" + rh1.get("key" + new String().valueOf(i)));
		}
	}
}
