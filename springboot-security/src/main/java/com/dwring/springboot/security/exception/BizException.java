/**  
 * @Title:  BizExcetion.java   
 * @Package com.topcheer.springboot.exception   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: zhanghaichang    
 * @date:   2019年4月10日 下午2:00:46   
 * @version V1.0 
 * @Copyright: 2019 www.topcheer.com Inc. All rights reserved. 
 * 注意：本内容仅限于上海天正软件公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.dwring.springboot.security.exception;

/**   
 * @ClassName:  BizExcetion   
 * @Description: 业务异常类  
 * @author: zhanghaichang
 * @date:   2019年4月10日 下午2:00:46   
 *     
 * @Copyright: 2019 www.topcheer.com Inc. All rights reserved. 
 * 注意：本内容仅限于上海天正软件公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public class BizException extends RuntimeException {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */ 
	private static final long serialVersionUID = 1L;

	
	public BizException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
