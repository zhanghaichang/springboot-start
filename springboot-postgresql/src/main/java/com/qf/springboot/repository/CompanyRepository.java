package com.qf.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qf.springboot.model.Company;

/**   
* @Title: CompanyRepository.java 
* @Package com.qf.springboot.repository 
* @Description: TODO
* @author haichangzhang   
* @date 2017年7月3日 下午5:40:44 
* @version V1.0   
*/
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
