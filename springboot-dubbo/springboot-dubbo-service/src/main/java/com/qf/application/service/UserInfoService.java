package com.qf.application.service;

import com.qf.application.domain.UserInfo;
import java.util.List;

/**
 * @ClassName: UserInfoService
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:38
 * 
 */
public interface UserInfoService {

	UserInfo loadById(Integer id);

	List<UserInfo> selectAll();

	void save(UserInfo userInfo);

	void update(UserInfo userInfo);

	void deleteById(UserInfo userInfo);

}
