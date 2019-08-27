package com.dwring.springboot.dao;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import com.dwring.springboot.model.UserInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserInfoRepository {

	private ConcurrentHashMap<Long, UserInfo> repository = new ConcurrentHashMap<>();

	private static final AtomicLong idGenerator = new AtomicLong(0);

	public Long save(UserInfo user) {
		Long id = idGenerator.incrementAndGet();
		user.setId(id);
		repository.put(id, user);
		log.info("----creatUser request end-----");
		return id;
	}

	public Collection<UserInfo> findAll() {
		return repository.values();
	}

	public UserInfo getUserById(Long id) {
		return repository.get(id);
	}

	public Long updateUser(UserInfo user) {
		repository.put(user.getId(), user);
		return user.getId();
	}

	public Long delete(Long id) {
		repository.remove(id);
		return id;
	}

}
