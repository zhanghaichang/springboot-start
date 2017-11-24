package com.dwring.springboot.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import lombok.Data;

@Data
@Table(name = "user_info")
public class UserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	@Size(max = 11, min = 6, message = "{user.password.length}")
	@NotBlank(message = "{user.password.notblank}")
	private String password;

	/**
	 * 用户类型
	 */
	@Size(max = 2, min = 1, message = "{user.usertype.length}")
	private String usertype;

	/**
	 * 是否可用
	 */
	@Min(value=0,message= "{user.enabled.length}")
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

	@Email(message = "{user.email}")
	private String email;

	/**
	 * 联系电话
	 */
	@Size(max = 11, min = 8, message = "{user.tel.length}")
	private String tel;

}