package com.dwring.springboot.dao;

import java.io.IOException;
import java.util.List;
import org.apache.lucene.queryparser.classic.ParseException;
import com.dwring.springboot.model.PageQuery;
import com.dwring.springboot.model.UserInfo;

/**
 * @ClassName: ILuceneDao
 * @Description: lucene
 * @author: zhanghaichang
 * @date: 2019年10月15日 下午4:06:45
 * 
 */
public interface ILuceneDao {

	/**
	 * 创建索引
	 * 
	 * @param UserInfoList
	 * @throws IOException
	 */
	public void createUserInfoIndex(List<UserInfo> UserInfoList) throws IOException;

	/**
	 * 查询索引
	 * 
	 * @param pageQuery
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public PageQuery<UserInfo> searchUserInfo(PageQuery<UserInfo> pageQuery) throws IOException, ParseException;

	/**
	 * 添加一个新索引
	 * 
	 * @param UserInfo
	 * @throws IOException
	 */
	public void addUserInfoIndex(UserInfo userInfo) throws IOException;

	/**
	 * 通过id删除索引
	 * 
	 * @param id
	 * @throws IOException
	 */
	public void deleteUserInfoIndexById(String id) throws IOException;
}
