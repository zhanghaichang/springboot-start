
package com.qf.springboot.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qf.springboot.model.Person;
import com.qf.springboot.service.PersonService;

/**
 * @ClassName: UserInfoController
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:25
 * 
 */
@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping("/uid")
	public String getUuid(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("uid");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		return session.getId();
	}

	@RequestMapping("/list")
	public List<Person> loadList() {
		return personService.list();
	}

	@RequestMapping("/id/{personId}")
	public Person load(@PathVariable("personId") Long personId) {
		return personService.load(personId);
	}

	@RequestMapping("/delete")
	public String delete() {
		personService.delete(1);
		return "delete success";
	}

	@RequestMapping("/save")
	public String save() {
		Person record = new Person();
		record.setAddress("上海");
		record.setAge(30);
		record.setName("张三");
		personService.save(record);
		return "save success";
	}

}
