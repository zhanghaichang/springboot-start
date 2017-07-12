package com.qf.springboot.model;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhanghaichang
 * @since 2017-07-11
 */
@TableName("user_info")
public class UserInfo extends SuperEntity<UserInfo>{

    private static final long serialVersionUID = 1L;
    
	private String email;
	private Integer enabled;
	private String password;
	private String qq;
	private String realname;
	private String tel;
	private String username;
	private String usertype;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
			"email=" + email +
			", enabled=" + enabled +
			", password=" + password +
			", qq=" + qq +
			", realname=" + realname +
			", tel=" + tel +
			", username=" + username +
			", usertype=" + usertype +
			"}";
	}
}
