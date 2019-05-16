package com.dwring.springboot.primary.service;

import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.primary.repository.UserInfoRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserInfoService
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:38
 * 
 */
@Service
public class UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Cacheable(value="users", key="#id")
	public UserInfo loadById(Integer id) {
		return userInfoRepository.selectByPrimaryKey(id);
	}

	public List<UserInfo> selectAll() {
		return userInfoRepository.findAll();
	}

	@CacheEvict(value="users",allEntries=true)
	@Transactional
	public void save(UserInfo userInfo) {
		userInfoRepository.save(userInfo);
	}

	@CacheEvict(value="users",key="#0.id")
	@Transactional
	public void update(UserInfo userInfo) {
		userInfoRepository.update(userInfo.getRealname(), userInfo.getId());
	}

	@CacheEvict(value="users",key="#0.id")
	@Transactional
	public void deleteById(UserInfo userInfo) {
		userInfoRepository.delete(userInfo);
	}

}
