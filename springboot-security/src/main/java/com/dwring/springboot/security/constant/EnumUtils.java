package com.dwring.springboot.security.constant;


/**   
 * @ClassName:  EnumUtils   
 * @Description: 枚举类   
 * @author: zhanghaichang
 * @date:   2019年4月8日 下午5:42:31   
 *     
 * @Copyright: 2019 www.topcheer.com Inc. All rights reserved. 
 * 注意：本内容仅限于上海天正软件公司内部传阅，禁止外泄以及用于其他的商业目 
 */
public interface EnumUtils {

	enum ResponseCode {
		NOT_FOUND(404, "page not found"), 
		FAIL(500, "fail"), 
		SUCCESS(200, "success"),
		NO_PERMISSION(401, "no permission");
		ResponseCode(int code, String message) {
			this.code = code;
			this.message = message;
		}

		public int getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

		private int code;
		private String message;
	}

}
