package com.dwring.springboot.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by zhanghaichang on 2017/4/9.
 */
@Document(collection="user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private String uuid;
	
	private String name;
	
    private int age;
    
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [uuid=" + uuid + ", name=" + name + ", age=" + age + "]";
	}





}
