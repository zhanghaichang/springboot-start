package com.dwring.springboot.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhanghaichang
 * @since 2017-07-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_info")
public class UserInfo extends Model<UserInfo>{

    private static final long serialVersionUID = 1L;
    
	/**
	 * 主键Id
	 */
	private int id;
	private String email;
	private Integer enabled;
	private String password;
	private String qq;
	private String realname;
	private String tel;
	private String username;
	private String usertype;


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
