package com.dwring.springboot.neum;

/**
 * @Title: UserInfoEnum.java
 * @Package com.dwring.springboot.neum
 * @Description: test user enum.
 * @author haichangzhang
 * @date 2017年7月25日 下午4:26:04
 * @version V1.0
 */
public enum UserInfoEnum {

	TEST(1, "test", "123456"), ADMIN(2, "admin", "admin"), ZHANGSAN(3, "zhangsan", "123456");
	
	private int id;
	private String username;
	private String password;

	UserInfoEnum(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

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

}
