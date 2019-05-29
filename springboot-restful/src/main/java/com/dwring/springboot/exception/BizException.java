package com.dwring.springboot.exception;

/**   
 * @ClassName:  BizException   
 * @Description: 业务逻辑异常
 * @author: zhanghaichang
 * @date:   2019年5月29日 上午10:57:55   
 *     
 */
public class BizException extends RuntimeException {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public BizException(String message) {
		super(message);
	}
}
