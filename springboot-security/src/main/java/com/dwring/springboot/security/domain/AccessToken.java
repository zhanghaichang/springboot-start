package com.dwring.springboot.security.domain;

import lombok.Data;

/**   
* @Title: AccessToken.java 
* @Package com.dwring.springboot.domain 
* @Description: Token
* @author haichangzhang   
* @date 2017年7月25日 下午3:13:55 
* @version V1.0   
*/
@Data
public class AccessToken {
	
	
	private String accessToken;

	private String username;
	
	private String password;
	
	private String imeiNo;
	
}
