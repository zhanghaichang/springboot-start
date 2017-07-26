package com.dwring.springboot.domain;

/**   
* @Title: LoginInfo.java 
* @Package com.dwring.springboot.domain 
* @Description: TODO
* @author haichangzhang   
* @date 2017年7月25日 下午3:15:33 
* @version V1.0   
*/
public class LoginInfo {
	
	private int id;
	private String clientId;
	private String username;
	private String password;
	private String captchaCode;
	private String captchaValue;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
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
	public String getCaptchaCode() {
		return captchaCode;
	}
	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}
	public String getCaptchaValue() {
		return captchaValue;
	}
	public void setCaptchaValue(String captchaValue) {
		this.captchaValue = captchaValue;
	}
}
