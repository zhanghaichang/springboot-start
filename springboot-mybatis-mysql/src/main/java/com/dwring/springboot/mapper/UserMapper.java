package com.dwring.springboot.mapper;

import java.util.List;


import com.dwring.springboot.model.User;

public interface UserMapper {
	int save(User user);

	User selectById(Integer id);

	int updateById(User user);

	int deleteById(Integer id);

	List<User> queryAll();
}
