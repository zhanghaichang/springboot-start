
package com.qf.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qf.springboot.model.Company;
import com.qf.springboot.service.CompanyService;

/**
 * @ClassName: UserInfoController
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:25
 * 
 */
@RestController
public class DockerController {

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/docker", method = RequestMethod.GET)
	public @ResponseBody String helloWorld() {
		return "Hello Docker!";
	}

	@GetMapping("/list")
	public @ResponseBody List<Company> loadList() {
		return companyService.list();
	}

	@GetMapping("/id/{id}")
	public @ResponseBody Company load(@PathVariable("id") int id) {
		return companyService.load(id);
	}

	@GetMapping("/delete")
	public @ResponseBody String delete() {
		companyService.delete(1);
		return "delete success";
	}
	
	@GetMapping("/save")
	public @ResponseBody String save(){
		Company record=new Company();
		record.setId(7);
		record.setName("java");
		companyService.save(record);
		return "save success";
	}

}
