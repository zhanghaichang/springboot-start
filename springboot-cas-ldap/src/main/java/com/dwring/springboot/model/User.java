package com.dwring.springboot.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName: UserInfo
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:30
 * 
 */
@Entity
@Table(name = "t_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "用户名不能为空")
	private String username;

	@NotEmpty(message = "密码不能为空")
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	
	private List<Role> roleList;// 一个用户具有多个角色

	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Transient
	public Set<String> getRolesName() {
		List<Role> roles = getRoleList();
		Set<String> set = new HashSet<String>();
		for (Role role : roles) {
			set.add(role.getRolename());
		}
		return set;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

}