package com.dwring.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dwring.springboot.security.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	User findByName(String name);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update User u set u.accessToken=:accessToken where u.id=:id")
	void update(@Param("accessToken") String accessToken, @Param("id") Long id);

}
