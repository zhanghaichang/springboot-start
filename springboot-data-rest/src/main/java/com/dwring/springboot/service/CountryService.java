package com.dwring.springboot.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dwring.springboot.model.Country;
import com.dwring.springboot.repository.CountryRepository;

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
		// countryRepository.update(Country.getRealname(), Country.getId());
	}

	@Transactional
	public void deleteById(Country Country) {
		countryRepository.delete(Country);
	}
}
