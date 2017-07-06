package com.qf.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qf.springboot.domain.AccountInfo;
import com.qf.springboot.repository.AccountInfoRepository;

/**
 * @Title: AccountInfoServiceImpl.java
 * @Package com.qf.springboot.service
 * @Description: ES
 * @author haichangzhang
 * @date 2017年6月21日 下午4:56:30
 * @version V1.0
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {

	@Autowired
	private AccountInfoRepository accountInfoRepository;

	@Override
	public AccountInfo queryAccountInfoById(String id) {
		return accountInfoRepository.findOne(id);
	}

	@Override
	public AccountInfo queryAccountInfoByName(String accountName) {
		return accountInfoRepository.findByAccountName(accountName);
	}

	@Override
	public void save(AccountInfo accountInfo) {
		accountInfoRepository.save(accountInfo);
	}

	@Override
	public void delete(String id) {
		accountInfoRepository.delete(id);		
	}

}
