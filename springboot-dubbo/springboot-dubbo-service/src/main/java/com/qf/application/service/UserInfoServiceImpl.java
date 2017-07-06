
package com.qf.application.service;

import com.qf.application.domain.UserInfo;
import com.qf.application.repository.UserInfoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: UserInfoService
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:38
 * 
 */
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserInfo loadById(Integer id) {
		return userInfoRepository.selectByPrimaryKey(id);
	}

	@Override
	public List<UserInfo> selectAll() {
		return userInfoRepository.findAll();
	}

	@Override
	public void save(UserInfo userInfo) {
		userInfoRepository.save(userInfo);
	}

	@Override
	public void update(UserInfo userInfo) {
		//userInfoRepository.update(realname, id);

	}

	@Override
	public void deleteById(UserInfo userInfo) {
		userInfoRepository.delete(userInfo);
	}

}
