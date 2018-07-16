package com.dwring.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.dwring.springboot.model.UserInfo;

/**
 * @ClassName: UserInfoRepository
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:34
 * 
 */
@RepositoryRestResource(path="user")
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	@Query("from UserInfo u where u.id=:id")
	UserInfo selectByPrimaryKey(@Param("id") Long id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update UserInfo u set u.realname=:realname where u.id=:id")
	void update(@Param("realname") String realname, @Param("id") Long id);

}
