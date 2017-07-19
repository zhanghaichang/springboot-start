package com.qf.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qf.springboot.model.Person;

/**   
* @Title: CompanyRepository.java 
* @Package com.qf.springboot.repository 
* @Description: TODO
* @author haichangzhang   
* @date 2017年6月30日 下午4:58:54 
* @version V1.0   
*/
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
