package com.qf.springboot.primary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qf.springboot.model.UserInfo;

/**
 * @ClassName: UserInfoRepository
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:34
 * 
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	@Query("from UserInfo u where u.id=:id")
	UserInfo selectByPrimaryKey(@Param("id") Integer id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update UserInfo u set u.realname=:realname where u.id=:id")
	void update(@Param("realname") String realname, @Param("id") Integer id);

}
