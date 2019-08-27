package com.dwring.springboot.model;

import java.io.Serializable;

import com.dwring.springboot.constant.EnumUtils;

import lombok.Data;

@Data
public class BaseResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int SUCCESS = EnumUtils.ResponseCode.SUCCESS.getCode();
	private static final int FAIL = EnumUtils.ResponseCode.FAIL.getCode();;
	private String msg =EnumUtils.ResponseCode.SUCCESS.getMessage();
	private int code = SUCCESS;
	private T data;

	public BaseResponse() {
		super();
	}

	public BaseResponse(T data) {
		super();
		this.data = data;
	}

	public BaseResponse(Throwable e) {
		super();
		this.msg = e.toString();
		this.code = FAIL;
	}
}
