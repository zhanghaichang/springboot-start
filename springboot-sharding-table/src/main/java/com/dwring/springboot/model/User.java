package com.dwring.springboot.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName: User
 * @Description: 用户信息
 * @author haichangzhang
 * @date 2017年7月25日 上午11:08:55
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user")
public class User extends Model<User> {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键Id
	 */
	private int id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 年龄
	 */
	private int age;
}