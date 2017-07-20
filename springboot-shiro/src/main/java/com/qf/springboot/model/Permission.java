package com.qf.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
public class Permission implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Long parentId;

	private String parentIds;

	private String permission;

	private String permissionname;

	private Boolean available = Boolean.FALSE;

	@Column(columnDefinition = "enum('menu','button')")
	private String resourceType;// 资源类型，[menu|button]

	@ManyToMany
	@JoinTable(name = "t_role_permission", joinColumns = { @JoinColumn(name = "permissionId") }, inverseJoinColumns = {
			@JoinColumn(name = "roleId") })
	private List<Role> roles;// 一个权限对应一个角色

	private String url;

	public Integer getId() {
		return id;
	}

	public Long getParentId() {
		return parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public String getPermission() {
		return permission;
	}

	public String getPermissionname() {
		return permissionname;
	}

	public String getResourceType() {
		return resourceType;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public String getUrl() {
		return url;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", parentId=" + parentId + ", parentIds=" + parentIds + ", available="
				+ available + ", resourceType=" + resourceType + ", url=" + url + "]";
	}

}
