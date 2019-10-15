package com.dwring.springboot.service;

import java.io.IOException;
import org.apache.lucene.queryparser.classic.ParseException;
import com.dwring.springboot.model.PageQuery;
import com.dwring.springboot.model.UserInfo;

/**   
 * @ClassName:  ILuceneService   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: zhanghaichang
 * @date:   2019年10月15日 下午4:01:45   
 *     
 */
public interface ILuceneService {

	void synUserInfoCreatIndex();

	PageQuery<UserInfo> searchUserInfo(PageQuery<UserInfo> pageQuery) throws IOException, ParseException;

}
