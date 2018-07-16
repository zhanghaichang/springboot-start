package com.dwring.springboot.model;

import javax.persistence.*;

import lombok.Data;

/**
 * @ClassName: UserInfo
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:30
 * 
 */
@Entity
@Data
@Table(name = "user_info")
public class UserInfo {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	private String usertype;

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
	private String qq;

	private String email;

	/**
	 * 联系电话
	 */
	private String tel;

}