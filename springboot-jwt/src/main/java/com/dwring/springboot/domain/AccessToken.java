package com.dwring.springboot.domain;

/**   
* @Title: AccessToken.java 
* @Package com.dwring.springboot.domain 
* @Description: TODO
* @author haichangzhang   
* @date 2017年7月25日 下午3:13:55 
* @version V1.0   
*/
public class AccessToken {

	private String accessToken;
	private String accessType;
	private long expiresIn;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
