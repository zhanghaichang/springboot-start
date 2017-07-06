package com.dwring.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwring.springboot.security.domain.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	User findByName(String name);

}
