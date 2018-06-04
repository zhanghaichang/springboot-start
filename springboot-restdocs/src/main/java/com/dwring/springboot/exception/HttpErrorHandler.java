package com.dwring.springboot.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class HttpErrorHandler implements ErrorController {

	private final static String ERROR_PATH = "/error";

	@RequestMapping(value = ERROR_PATH)
	@ResponseBody
	public Object error(HttpServletRequest request) {
		return "404";
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
