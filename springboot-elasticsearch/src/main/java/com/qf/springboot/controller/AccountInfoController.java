
package com.qf.springboot.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.qf.springboot.domain.AccountInfo;
import com.qf.springboot.service.AccountInfoService;

/**
 * @ClassName: UserInfoController
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:25
 * 
 */
@RestController
@RequestMapping("/account")
public class AccountInfoController {

	@Autowired
	private AccountInfoService accountInfoService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody AccountInfo queryById(@RequestParam("id") String id) {
		return accountInfoService.queryAccountInfoById(id);
	}

	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
	public @ResponseBody AccountInfo searchById(@PathVariable String id) {
		return accountInfoService.queryAccountInfoById(id);
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	@ResponseBody
	public AccountInfo queryByName(@PathVariable String name) {
		return accountInfoService.queryAccountInfoByName(name);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String deleteById(@PathVariable String id) {
		accountInfoService.delete(id);
		return "SUCCESS";
	}

	@RequestMapping(value = "/save", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public void save(@RequestBody Map<String, Object> map) {
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setId((String) map.get("id"));
		accountInfo.setAccountName((String) map.get("accountName"));
		accountInfo.setNickName((String) map.get("nickName"));
		accountInfoService.save(accountInfo);
	}

}
