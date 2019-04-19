package com.dwring.springboot.security.domain;

import java.io.Serializable;
import com.dwring.springboot.security.constant.EnumUtils;
import com.dwring.springboot.security.constant.EnumUtils.ResponseCode;
import lombok.Data;

/**
 * @ClassName: BaseResponse
 * @Description: 返回报文类
 * @author: zhanghaichang
 * @date: 2019年4月8日 下午5:46:38
 * 
 * @param <T>
 * @Copyright: 2019 www.topcheer.com Inc. All rights reserved.
 *             注意：本内容仅限于上海天正软件公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Data
public class BaseResponse<T> implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private static final int SUCCESS = ResponseCode.SUCCESS.getCode();

	private static final int FAIL = EnumUtils.ResponseCode.FAIL.getCode();

	/**
	 * @Fields msg : 返回信息
	 */
	private String msg = EnumUtils.ResponseCode.SUCCESS.getMessage();

	/**
	 * @Fields code : 返回码
	 */
	private int code = SUCCESS;

	/**
	 * @Fields data : 返回数据
	 */
	private T data;

	public BaseResponse() {
		super();
	}

	public BaseResponse(int code, String msg) {
		super();
		if (this.code == EnumUtils.ResponseCode.FAIL.getCode()) {
			this.msg = EnumUtils.ResponseCode.FAIL.getMessage();
		} else {
			this.msg = msg;
		}
	}

	public BaseResponse(T data, int code) {
		super();
		this.data = data;
		this.code = code;
		if (this.code == EnumUtils.ResponseCode.FAIL.getCode()) {
			this.msg = EnumUtils.ResponseCode.FAIL.getMessage();
		}
	}

	public BaseResponse(Throwable e) {
		super();
		this.msg = e.toString();
		this.code = FAIL;
	}

}
