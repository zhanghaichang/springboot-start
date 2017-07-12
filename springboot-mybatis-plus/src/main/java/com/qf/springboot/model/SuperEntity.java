package com.qf.springboot.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * @Title: SuperEntity.java
 * @Package com.qf.springboot.model
 * @Description: TODO
 * @author haichangzhang
 * @date 2017年7月11日 下午6:19:09
 * @version V1.0
 */
@SuppressWarnings("rawtypes")
public class SuperEntity<T extends Model> extends Model<T> {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId("id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
