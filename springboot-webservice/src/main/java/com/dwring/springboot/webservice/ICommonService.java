package com.dwring.springboot.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.dwring.springboot.model.UserInfo;

/**
 * @author zhanghaichang
 *
 */
// 暴露服务名称
// 命名空间,一般是接口的包名倒序
@WebService(name = "CommonService", targetNamespace = "http://webservice.dwring.com/")
public interface ICommonService {

	
	
	@WebMethod
	public String sayHello(@WebParam(name="userName") String username);
	
	@WebMethod
	public UserInfo getUser(@WebParam(name="userName") String name);
}
