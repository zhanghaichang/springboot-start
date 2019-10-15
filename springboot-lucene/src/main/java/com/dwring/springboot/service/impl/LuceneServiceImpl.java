package com.dwring.springboot.service.impl;

import java.io.IOException;
import java.util.List;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dwring.springboot.dao.ILuceneDao;
import com.dwring.springboot.mapper.UserInfoMapper;
import com.dwring.springboot.model.PageQuery;
import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.service.ILuceneService;

/**   
 * @ClassName:  LuceneServiceImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: zhanghaichang
 * @date:   2019年10月15日 下午4:23:16   
 *     
 */
@Service
public class LuceneServiceImpl implements ILuceneService {

	@Autowired
	private ILuceneDao luceneDao;

	@Autowired
	private UserInfoMapper mapper;

	@Override
	public void synUserInfoCreatIndex() {
		List<UserInfo> lsit = mapper.getAll();
		// 再插入UserInfoList
		try {
			luceneDao.createUserInfoIndex(lsit);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PageQuery<UserInfo> searchUserInfo(PageQuery<UserInfo> pageQuery) throws IOException, ParseException {
		return luceneDao.searchUserInfo(pageQuery);
	}
}
