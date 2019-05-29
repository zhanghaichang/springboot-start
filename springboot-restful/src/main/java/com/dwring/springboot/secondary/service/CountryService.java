package com.dwring.springboot.secondary.service;

import com.dwring.springboot.model.Country;
import com.dwring.springboot.secondary.repository.CountryRepository;
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
	public void save(Country country) {
		countryRepository.save(country);
	}

	@Transactional
	public void update(Country country) {
		countryRepository.save(country);
	}

	@Transactional
	public void deleteById(Country country) {
		countryRepository.delete(country);
	}

}
