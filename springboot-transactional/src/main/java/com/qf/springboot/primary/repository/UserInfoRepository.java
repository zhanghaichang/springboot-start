package com.qf.springboot.primary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.qf.springboot.model.UserInfo;

/** 
 * @ClassName UserInfoRepository
 * @Description TODO
 * @author zhanghaichang 
 * @date: 2017年9月19日 下午5:14:32
 *
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
	
	UserInfo findByUsername(String username);
	
	@Modifying(clearAutomatically=true)
	@Query(value="update UserInfo u set u.realname=:realname where u.id=:id ")
	void update(@Param("realname") String realname,@Param("id") long id);

}
