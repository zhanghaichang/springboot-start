package com.qf.springboot.mapper;

import com.qf.platform.annotation.QfBatisRepository;
import com.qf.springboot.model.UserInfo;

/**
 * @ClassName: UserInfoMapper
 * @Description:
 * @author haichangzhang
 * @date 2017年6月2日 上午9:55:41
 * 
 */
@QfBatisRepository
public interface UserInfoMapper {

	UserInfo selectByPrimaryKey(Integer id);

	void deleteByPrimaryKey(Integer id);

	void insert(UserInfo country);

	void updateByPrimaryKey(UserInfo country);
}
