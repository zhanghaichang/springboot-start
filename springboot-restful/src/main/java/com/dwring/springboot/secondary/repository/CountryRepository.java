package com.dwring.springboot.secondary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dwring.springboot.model.Country;

/**
 * @ClassName: UserInfoRepository
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:34
 * 
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	@Query("from Country u where u.id=:id")
	Country selectByPrimaryKey(@Param("id") Integer id);
	
}
