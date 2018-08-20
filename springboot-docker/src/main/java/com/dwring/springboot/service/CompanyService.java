package com.dwring.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dwring.springboot.mapper.CompanyMapper;
import com.dwring.springboot.model.Company;

/**
 * @Title: CompanyService.java
 * @Package com.qf.springboot.service
 * @Description: TODO
 * @author haichangzhang
 * @date 2017年6月29日 下午6:30:02
 * @version V1.0
 */
@Service
public class CompanyService {

	@Autowired
	private CompanyMapper companyMapper;

	
	/** 
	* @Title: load 
	* @Description: @Cacheable 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中 
	* @param @param id
	* @param @return    设定文件 
	* @return Company    返回类型 
	* @throws 
	*/
	@Cacheable(value = "company", key = "#id+'_company'")
	public Company load(int id) {
		return companyMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	* @Title: delete 
	* @Description: @CacheEvict 应用到删除数据的方法上，调用方法时会从缓存中删除对应key的数据
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@CacheEvict(value = "company", key = "#id+'_company'")
	public void delete(int id) {
		// return companyMapper.deleteByPrimaryKey(id);
	}

	public List<Company> list() {
		return companyMapper.selectAll();
	}
	
	/** 
	* @Title: save 
	* @Description: 应用到写数据的方法上，如新增/修改方法，调用方法时会自动把相应的数据放入缓存 
	* @param @param record    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	@CachePut(value="company",key="#id+'_company'")
	public void save(Company record){
		 companyMapper.insert(record);
	}

}
