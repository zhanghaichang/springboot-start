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

package com.qf.springboot.primary.service;

import com.qf.springboot.model.UserInfo;
import com.qf.springboot.primary.repository.UserInfoRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserInfoService
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午1:59:38
 * 
 */
@Service
public class UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	public UserInfo loadById(Integer id) {
		return userInfoRepository.selectByPrimaryKey(id);
	}

	public List<UserInfo> selectAll() {
		return userInfoRepository.findAll();
	}

	@Transactional
	public void save(UserInfo userInfo) {
		userInfoRepository.save(userInfo);
	}

	@Transactional
	public void update(UserInfo userInfo) {
		userInfoRepository.update(userInfo.getRealname(), userInfo.getId());
	}

	@Transactional
	public void deleteById(UserInfo userInfo) {
		userInfoRepository.delete(userInfo);
	}

}
