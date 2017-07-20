package com.qf.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName: UserInfo
 * @Description:用户信息
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:30
 * 
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable {
	
	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/ 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;

	private String name;

	@NotEmpty(message = "密码不能为空")
	private String password;

	// 立即从数据库中进行加载数据;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = {
			@JoinColumn(name = "roleId") })
	private List<Role> roleList;// 一个用户具有多个角色

	private String salt;// 加密密码的盐

	private byte state;// 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,
						// 1:正常状态,2：用户被锁定.

	@NotEmpty(message = "用户名不能为空")
	@Column(unique = true)
	private String username;

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public String getSalt() {
		return salt;
	}

	public byte getState() {
		return state;
	}

	public Integer getUid() {
		return uid;
	}

	public String getUsername() {
		return username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	 /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }
    
	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", password=" + password + ", roleList=" + roleList + ", salt="
				+ salt + ", state=" + state + ", username=" + username + "]";
	}
}