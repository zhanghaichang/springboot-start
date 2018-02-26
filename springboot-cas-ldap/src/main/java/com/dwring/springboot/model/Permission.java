package com.dwring.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Title: Permission.java
 * @Package com.qf.springboot.model
 * @Description: 权限（增删改查等）
 * @author haichangzhang
 * @date 2017年7月10日 下午5:11:28
 * @version V1.0
 */
@Entity
@Table(name = "t_permission")
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String permissionname;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;// 一个权限对应一个角色
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPermissionname() {
		return permissionname;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
