package com.dwring.springboot.webservice;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.dwring.springboot.model.UserInfo;

@WebService(serviceName="CommonService",targetNamespace="http://webservice.dwring.com/", endpointInterface="com.dwring.springboot.webservice.ICommonService")
@Service
public class CommonServiceImpl implements ICommonService {

	@Override
	public String sayHello(String username) {
		return "Hello,"+username;
	}

	@Override
	public UserInfo getUser(String name) {
		UserInfo userInfo=new UserInfo();
		userInfo.setId(1);
		userInfo.setUsername(name);
		userInfo.setEmail("zhanghaichang@163.com");
		userInfo.setQq("77022360");
		userInfo.setRealname("小明");
		return userInfo;
	}

}
