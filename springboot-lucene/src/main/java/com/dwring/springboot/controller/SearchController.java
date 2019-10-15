package com.dwring.springboot.controller;

import java.io.IOException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dwring.springboot.model.BaseResponse;
import com.dwring.springboot.model.PageQuery;
import com.dwring.springboot.model.UserInfo;
import com.dwring.springboot.service.ILuceneService;

/**
 * @ClassName: SearchController
 * @Description:全文检索
 * @author: zhanghaichang
 * @date: 2019年10月15日 下午3:45:59
 * 
 */
@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private ILuceneService service;

	@PostMapping("/key")
	public BaseResponse<PageQuery<UserInfo>> getUserInfoList(@RequestBody PageQuery<UserInfo> pageQuery)
			throws IOException, ParseException {
		PageQuery<UserInfo> pageResult = service.searchUserInfo(pageQuery);
		return new BaseResponse<PageQuery<UserInfo>>(pageResult);
	}
}
