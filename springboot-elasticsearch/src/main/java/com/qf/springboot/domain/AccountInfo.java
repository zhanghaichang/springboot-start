package com.qf.springboot.domain;

import java.io.Serializable;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Title: AccountInfo.java
 * @Package com.qf.springboot.domain
 * @Description: AccountInfo ES
 * @author haichangzhang
 * @date 2017年6月21日 下午4:49:07
 * @version V1.0
 */
@Document(indexName = "bank", type = "accountinfo")
public class AccountInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String accountName;
	private String nickName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return "AccountInfo [id=" + id + ", accountName=" + accountName + ", nickName=" + nickName + "]";
	}
}
