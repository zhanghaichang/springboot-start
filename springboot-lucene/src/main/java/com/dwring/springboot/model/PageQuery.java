package com.dwring.springboot.model;

import java.util.List;
import java.util.Map;
import lombok.Data;

/**   
 * @ClassName:  PageQuery   
 * @Description: 分页查询
 * @author: zhanghaichang
 * @date:   2019年10月15日 下午4:10:56   
 *   
 */
@Data
public class PageQuery<T> {

	private PageInfo pageInfo;
	/**
	 * 排序字段
	 */
	private Sort sort;
	/**
	 * 查询参数类
	 */
	private T params;
	/**
	 * 返回结果集
	 */
	private List<T> results;
	/**
	 * 不在T类中的参数
	 */
	private Map<String, String> queryParam;

}
