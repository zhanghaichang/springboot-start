package com.dwring.springboot.security.exception;


/**   
 * @ClassName:  BaseException   
 * @Description:异常父类 
 * @author: zhanghaichang
 * @date:   2019年4月10日 下午1:58:54   
 *     
 * @Copyright: 2019 www.topcheer.com Inc. All rights reserved. 
 * 注意：本内容仅限于上海天正软件公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class BaseException  extends RuntimeException{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;
	
	
	public BaseException(String message) {
		super(message);
	}

}
