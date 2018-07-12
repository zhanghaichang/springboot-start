package com.dwring.springboot.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dwring.springboot.model.UserInfo;

/**
 * @Title: UserRepository.java
 * @Package com.dwring.springboot.repository
 * @Description: 数据层
 * @author haichangzhang
 * @date 2018年7月11日 下午6:33:19
 * @version V1.0
 */
@Repository
public class UserRepository {

	static Map<Integer, UserInfo> users = Collections.synchronizedMap(new HashMap<Integer, UserInfo>());

	static UserInfo userinfo = new UserInfo();

	static {
		userinfo.setId(1);
		userinfo.setEmail("zhanghaichang@163.com");
		userinfo.setPassword("admin");
		userinfo.setQq(77022345);
		userinfo.setRealname("zhangsan");
		userinfo.setTel("13585822222");
		userinfo.setUsername("admin");
		userinfo.setUsertype(1);
		userinfo.setEnabled(1);
		users.put(userinfo.getId(), userinfo);

	}

	public List<UserInfo> selectAll() {
		List<UserInfo> list = new ArrayList<>(users.values());
		System.out.println("---------------->"+list);
		return list;
	}

}
