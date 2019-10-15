package com.dwring.springboot.model;

import lombok.Data;

/**
 * @ClassName: Sort
 * @Description: 字段排序
 * @author: zhanghaichang
 * @date: 2019年10月15日 下午4:10:27
 * 
 */
@Data
public class Sort {
	/**
	 * 字段名
	 */
	private String field;
	/**
	 * 升序,asc,desc
	 */
	private String order;

}
