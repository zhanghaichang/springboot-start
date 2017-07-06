package com.qf.springboot.service;

import com.qf.springboot.domain.AccountInfo;

/**
 * @Title: AccounInfoService.java
 * @Package:com.qf.springboot.service
 * @Description: ES
 * @author haichangzhang
 * @date 2017年6月21日 下午4:55:20
 * @version V1.0
 */
public interface AccountInfoService {

	void delete(String id);

	void save(AccountInfo accountInfo);

	AccountInfo queryAccountInfoById(String id);

	AccountInfo queryAccountInfoByName(String accountName);
}
