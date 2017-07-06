
package com.qf.springboot.secondary.service;

import com.qf.springboot.model.Country;
import com.qf.springboot.secondary.repository.CountryRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: CountryService
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:38
 * 
 */
@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public Country loadById(Integer id) {
		return countryRepository.selectByPrimaryKey(id);
	}

	public List<Country> selectAll() {
		return countryRepository.findAll();
	}

	@Transactional
	public void save(Country Country) {
		countryRepository.save(Country);
	}

	@Transactional
	public void update(Country Country) {
		//countryRepository.update(Country.getRealname(), Country.getId());
	}

	@Transactional
	public void deleteById(Country Country) {
		countryRepository.delete(Country);
	}

}
