package com.dwring.springboot.exception;

public class BizException extends RuntimeException {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BizException(String message) {
		super(message);
	}
}
