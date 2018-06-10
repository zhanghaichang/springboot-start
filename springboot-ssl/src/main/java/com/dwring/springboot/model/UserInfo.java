package com.dwring.springboot.model;

import lombok.Data;
/**
 * @ClassName: UserInfo
 * @Description: 用户信息
 * @author haichangzhang
 * @date 2017年7月25日 上午11:08:55
 * 
 */
@Data
public class UserInfo {

	private Integer id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户类型
	 */
	private Integer usertype;

	/**
	 * 是否可用
	 */
	private Integer enabled;

	/**
	 * 真实姓名
	 */
	private String realname;

	/**
	 * QQ
	 */
	private Integer qq;

	private String email;

	/**
	 * 联系电话
	 */
	private String tel;

}