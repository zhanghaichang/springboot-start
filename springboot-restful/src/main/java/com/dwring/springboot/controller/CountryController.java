package com.dwring.springboot.controller;

import com.dwring.springboot.model.BaseResponse;
import com.dwring.springboot.model.Country;
import com.dwring.springboot.secondary.service.CountryService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CountryController
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:25
 * 
 */
@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping("/")
	public BaseResponse<?> getAll() {
		List<Country> CountryList = countryService.selectAll();
		return new BaseResponse<>(CountryList);
	}

	@GetMapping("/{id}")
	public BaseResponse<?> view(@PathVariable @Valid Integer id) {
		Country country = countryService.loadById(id);
		return new BaseResponse<>(country);
	}

	@DeleteMapping("/{id}")
	public BaseResponse<?> delete(@PathVariable Integer id) {
		Country country = new Country();
		country.setId(id);
		countryService.deleteById(country);
		return new BaseResponse<>();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public BaseResponse<?> update(@PathVariable @Valid Integer id, @RequestBody Country country) {
		countryService.update(country);
		return new BaseResponse<>();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public BaseResponse<?> add(@RequestBody Country country) {
		countryService.save(country);
		return new BaseResponse<>();
	}
}
