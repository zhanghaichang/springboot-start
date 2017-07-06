package com.qf.springboot.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.qf.springboot.domain.AccountInfo;

/**   
* @Title: AccountInfoRepository.java 
* @Package com.qf.springboot.repository 
* @Description: ES
* @author haichangzhang   
* @date 2017年6月21日 下午4:53:01 
* @version V1.0   
*/
public interface AccountInfoRepository extends ElasticsearchRepository<AccountInfo, String> {
	
	AccountInfo findByAccountName(String accountName);

}
