package com.qf.springboot.domain;

import java.io.Serializable;

public class User implements Serializable {

	
	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/ 
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

}
