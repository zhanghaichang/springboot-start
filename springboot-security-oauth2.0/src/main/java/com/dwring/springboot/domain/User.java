package com.dwring.springboot.domain;

/**
 * @Title: User.java
 * @Package com.dwring.springboot.domain
 * @Description: TODO
 * @author haichangzhang
 * @date 2017年7月27日 上午10:06:40
 * @version V1.0
 */
public class User {

	private int age;

	private String name;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(String name, int age) {
		this.age = age;
		this.name = name;
	}

}
