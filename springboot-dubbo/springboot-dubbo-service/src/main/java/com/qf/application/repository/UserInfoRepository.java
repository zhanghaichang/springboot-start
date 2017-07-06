package com.qf.application.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qf.application.domain.UserInfo;

/**
 * @ClassName: UserInfoRepository
 * @Description:缓存支持：注解配置与EhCache使用
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:34
 * 
 */
@CacheConfig(cacheNames = "userInfo")
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	@Cacheable(key = "#p0", condition = "#p0>0")
	@Query("from UserInfo u where u.id=:id")
	UserInfo selectByPrimaryKey(@Param("id") Integer id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update UserInfo u set u.realname=:realname where u.id=:id")
	void update(@Param("realname") String realname, @Param("id") Integer id);

}
