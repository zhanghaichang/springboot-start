package com.dwring.springboot.model;

import lombok.Data;

/**   
 * @ClassName:  PageInfo   
 * @Description: 分页
 * @author: zhanghaichang
 * @date:   2019年10月15日 下午4:12:09   
 *     
 */
@Data
public class PageInfo {
	/**
	 * 当前页数
	 */
	private int pageNum;

	/**
	 * 每页条数
	 */
	private int pageSize;
	/**
	 * 总数
	 */
	private long total;

}
