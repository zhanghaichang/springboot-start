/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.qf.springboot.controller;

import com.qf.springboot.model.UserInfo;
import com.qf.springboot.service.IUserInfoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: UserInfoController
 * @Description:
 * @author haichangzhang
 * @date 2017年7月11日 上午11:31:47
 * 
 */
@RestController
@RequestMapping("/users")
public class UserInfoController {

	@Autowired
	private IUserInfoService userInfoServiceImpl;

	@RequestMapping(value = "/view/{id}")
	public UserInfo view(@PathVariable Long id) {
		UserInfo userInfo = userInfoServiceImpl.selectById(id);
		return userInfo;
	}

	@RequestMapping(value = "/list")
	public List<UserInfo> getAll() {
		return userInfoServiceImpl.getAll();
	}

	@RequestMapping(value = "/delete/{id}")
	public ModelMap delete(@PathVariable Long id) {
		ModelMap result = new ModelMap();
		userInfoServiceImpl.deleteById(id);
		result.put("msg", "删除成功!");
		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelMap save(@RequestBody UserInfo userInfo) {
		ModelMap result = new ModelMap();
		String msg = userInfo.getId() == null ? "新增成功!" : "更新成功!";
		userInfoServiceImpl.insertOrUpdate(userInfo);
		result.put("userInfo", userInfo);
		result.put("msg", msg);
		return result;
	}
}
