package com.qf.springboot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Data
@Table(name = "user_info")
public class UserInfo  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * 用户名
	 */
	@NotNull
	@Column(name = "username", nullable = false, length = 20)
	private String username;

	/**
	 * 密码
	 */
	@NotBlank(message="{user.name.notblank}")
	@Column(name = "password", nullable = false, length = 20)
	private String password;

	/**
	 * 用户类型
	 */
	@Column(name = "user_type", nullable = false, length = 2)
	private String usertype;

	/**
	 * 是否可用
	 */
	@Column(name = "enabled", nullable = false, length = 1)
	private Integer enabled;

	/**
	 * 真实姓名
	 */
	@Column(name = "realname", nullable = false, length = 20)
	private String realname;

	/**
	 * QQ
	 */
	@Column(name = "qq", nullable = false, length = 11)
	@Size(max = 11, min = 6, message = "{user.qq.length}")
	private String qq;

	@Email
	@Column(name = "email", nullable = false, length = 50)
	private String email;

	/**
	 * 联系电话
	 */
	@Size(max = 11, min = 8, message = "{user.tel.length}")
	@Column(name = "tel", nullable = false, length = 12)
	private String tel;

}