package com.dwring.springboot.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
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

	@NotNull
	private Integer id;

	/**
	 * 用户名
	 */
	@NotNull
	private String username;

	/**
	 * 密码
	 */
	@NotBlank
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
	@Size(max = 11, min = 6, message = "{user.qq.length}")
	private String qq;

	@Email
	private String email;

	/**
	 * 联系电话
	 */
	@Size(max = 11, min = 8, message = "{user.tel.length}")
	private String tel;

}