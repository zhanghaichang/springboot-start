package com.qf.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.qf.springboot.model.User;

/**
 * @ClassName: UserInfoRepository
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:34
 * 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	@Query("from User u where u.id=:id")
	User selectByPrimaryKey(@Param("id") Integer id);
	
	@Query("from User u where u.username=:name")
	User findByName(@Param("name")String name);

}
