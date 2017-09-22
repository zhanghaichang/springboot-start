package com.qf.springboot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.qf.springboot.model.UserInfo;
import com.qf.springboot.primary.repository.UserInfoRepository;
import com.qf.springboot.secondary.repository.SecondUserInfoRepository;

/**
 * @ClassName UserInfoService
 * @Description TODO
 * @author zhanghaichang
 * @date: 2017年9月19日 下午5:19:18
 *
 */
@Service
public class UserInfoService {

	@Autowired
	private SecondUserInfoRepository userInfoRepositorySecond;

	@Autowired
	private UserInfoRepository userInfoRepositoryPrimary;

	public List<UserInfo> getList() {
		return userInfoRepositoryPrimary.findAll();
	}

	public void update(UserInfo userinfo) {
		userInfoRepositoryPrimary.update(userinfo.getRealname(), userinfo.getId());
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void save(UserInfo userinfo) {
		userInfoRepositoryPrimary.save(userinfo);
		userinfo.setPassword("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		userInfoRepositorySecond.save(userinfo);
	}

	public void delete(long id) {
		userInfoRepositoryPrimary.delete(id);
	}

	public UserInfo load(long id) {
		return userInfoRepositoryPrimary.findOne(id);
	}
}
