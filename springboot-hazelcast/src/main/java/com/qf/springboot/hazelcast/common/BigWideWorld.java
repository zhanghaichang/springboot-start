package com.qf.springboot.hazelcast.common;

import java.util.Random;

import com.qf.springboot.hazelcast.domain.User;
import com.qf.springboot.hazelcast.domain.Users;

public class BigWideWorld {
	
	private static Random rand = new Random(System.currentTimeMillis());

	private final Users users = new Users();

	private final int totalNumUsers = users.size();

	public String nextUser() {

		User user = users.get(rand.nextInt(totalNumUsers));
		String name = user.getUsername();
		return name;
	}
}
