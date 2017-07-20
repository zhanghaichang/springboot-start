package com.qf.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @Title: Role.java
 * @Package com.qf.springboot.model
 * @Description: 角色（管理员，普通用户等）
 * @author haichangzhang
 * @date 2017年7月10日 下午5:10:11
 * @version V1.0
 */
@Entity
@Table(name = "t_role")
public class Role implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;// 编号

	private Boolean available = Boolean.FALSE;// 是否可用,如果不可用将不会添加给用户

	private String description;// 角色描述,UI界面显示使用

	// 角色 -- 权限关系：多对多关系;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_role_permission", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = {
			@JoinColumn(name = "permissionId") })
	private List<Permission> permissions;

	private String rolename;// 角色标识程序中判断使用,如"admin",这个是唯一的:

	// 用户 - 角色关系定义;
	@ManyToMany
	@JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = {
			@JoinColumn(name = "uid") })
	private List<User> users;

	public String getDescription() {
		return description;
	}

	public Integer getId() {
		return id;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public String getRolename() {
		return rolename;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", available=" + available + ", description=" + description + "]";
	}

}
