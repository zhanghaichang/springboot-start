
package com.qf.springboot.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qf.springboot.model.Person;
import com.qf.springboot.service.PersonService;

/**
 * @ClassName: UserInfoController
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:25
 * 
 */
@Component
@Path("/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> loadList() {
		return personService.list();
	}

	@GET
	@Path("id")
	@Produces(MediaType.APPLICATION_JSON)
	public Person load(@QueryParam("personId") Long personId) {
		System.out.println("============================"+personId);
		return personService.load(personId);
	}

	@GET
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	public String delete() {
		personService.delete(1);
		return "delete success";
	}

	@GET
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	public String save() {
		Person record = new Person();
		record.setAddress("上海");
		record.setAge(30);
		record.setName("张三");
		personService.save(record);
		return "save success";
	}

}
