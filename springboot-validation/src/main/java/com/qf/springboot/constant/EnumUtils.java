package com.qf.springboot.constant;

public interface EnumUtils {

	enum ResponseCode{
		NOT_FOUND(404,"page not found"),
		FAIL(500,"fail"),
		SUCCESS(200,"success"),
		NO_PERMISSION(401,"no permission");
		ResponseCode(int code,String message){
			this.code=code;
			this.message=message;
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
