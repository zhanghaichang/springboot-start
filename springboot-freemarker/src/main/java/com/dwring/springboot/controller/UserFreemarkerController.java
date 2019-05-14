package com.dwring.springboot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwring.springboot.model.Group;
import com.dwring.springboot.model.UserGroup;
import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.utils.FreemarkerUtils;

import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

/**   
 * @ClassName:  UserFreemarkerController   
 * @Description: freemarker用户模块
 * @author: zhanghaichang
 * @date:   2019年5月14日 下午1:49:44   
 *     
 */
@Slf4j
@RequestMapping("/freemarker")
@Controller
public class UserFreemarkerController {
	
	@GetMapping("/index")
	public String index(Model model) {
		try {
			 Map<String,Object> map=new HashMap<String, Object>();
			 map.put("name", "张海昌");
			 model.addAttribute("name","张海昌");
			String html=FreemarkerUtils.getTemplate("resume_template.ftl",map);
			log.info("================html:{}",html);
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		return "views/index";
	}
	
	/**   
	 * @Title: userInfo   
	 * @Description: 返回模板
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	@GetMapping("/user")
	public String userInfo(Model model) {
		UserInfo userinfo = new UserInfo();
		userinfo.setId(1);
		userinfo.setEmail("zhanghaichang@163.com");
		userinfo.setPassword("admin");
		userinfo.setQq("77022369");
		userinfo.setRealname("張海昌");
		userinfo.setTel("13588888888");
		userinfo.setUsername("haichangzhang");
		userinfo.setUsertype("haichangzhang");
		userinfo.setEnabled(1);
		List<Group> groups=new ArrayList<>();
		Group group=new Group();
		group.setId(1);
		group.setName("===管理员组===");
		groups.add(group);
		Group group1=new Group();
		group1.setId(2);
		group1.setName("===普通组===");
		groups.add(group1);
		UserGroup userGroup=new UserGroup();
		userGroup.setUser(userinfo);
		userGroup.setGroups(groups);
		model.addAttribute("userGroup", userGroup);
		return "views/user";
	}

}
