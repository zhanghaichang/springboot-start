package com.dwring.springboot.dao.impl;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Map;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.lucene.search.Sort;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dwring.springboot.dao.ILuceneDao;
import com.dwring.springboot.model.PageInfo;
import com.dwring.springboot.model.PageQuery;
import com.dwring.springboot.model.UserInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: LuceneDaoImpl
 * @Description: 构建索引
 * @author: zhanghaichang
 * @date: 2019年10月15日 下午4:14:56
 * 
 */
@Slf4j
@Repository(value = "luceneDao")
public class LuceneDaoImpl implements ILuceneDao {

	@Autowired(required = false)
	private IndexWriter indexWriter;

	@Autowired
	private Analyzer analyzer;

	@Autowired
	private SearcherManager searcherManager;

	@Override
	public void createUserInfoIndex(List<UserInfo> UserInfoList) throws IOException {
		List<Document> docs = new ArrayList<Document>();
		for (UserInfo p : UserInfoList) {
			Document doc = new Document();
			doc.add(new StringField("id", p.getId() + "", Field.Store.YES));
			doc.add(new TextField("username", p.getUsername(), Field.Store.YES));
			doc.add(new StringField("tel", p.getTel(), Field.Store.YES));
			doc.add(new TextField("realname", p.getRealname(), Field.Store.YES));
			docs.add(doc);
		}
		indexWriter.addDocuments(docs);
		indexWriter.commit();
	}

	@Override
	public PageQuery<UserInfo> searchUserInfo(PageQuery<UserInfo> pageQuery) throws IOException, ParseException {
		log.info("searchUserInfo pageQuery:{}", JSONObject.toJSONString(pageQuery,SerializerFeature.WriteMapNullValue));
		searcherManager.maybeRefresh();
		IndexSearcher indexSearcher = searcherManager.acquire();
		UserInfo params = pageQuery.getParams();
		Map<String, String> queryParam = pageQuery.getQueryParam();
		Builder builder = new BooleanQuery.Builder();
		Sort sort = new Sort();
		// 排序规则
		com.dwring.springboot.model.Sort sort1 = pageQuery.getSort();
		if (sort1 != null && sort1.getOrder() != null) {
			if ("ASC".equals((sort1.getOrder()).toUpperCase())) {
				sort.setSort(new SortField(sort1.getField(), SortField.Type.FLOAT, false));
			} else if ("DESC".equals((sort1.getOrder()).toUpperCase())) {
				sort.setSort(new SortField(sort1.getField(), SortField.Type.FLOAT, true));
			}
		}
		// 模糊匹配,匹配词
		String keyStr = queryParam.get("searchKeyStr");
		if (keyStr != null) {
			// 输入空格,不进行模糊查询
			if (!"".equals(keyStr.replaceAll(" ", ""))) {
				builder.add(new QueryParser("username", analyzer).parse(keyStr), Occur.MUST);
			}
		}
		// 精确查询
//		if (params.getTel() != null) {
//			builder.add(new TermQuery(new Term("tel", params.getTel())), Occur.MUST);
//		}
		PageInfo pageInfo = pageQuery.getPageInfo();
		TopDocs topDocs = indexSearcher.search(builder.build(), pageInfo.getPageNum() * pageInfo.getPageSize(), sort);
		pageInfo.setTotal(topDocs.totalHits);
		ScoreDoc[] hits = topDocs.scoreDocs;
		List<UserInfo> pList = new ArrayList<UserInfo>();
		for (int i = 0; i < hits.length; i++) {
			Document doc = indexSearcher.doc(hits[i].doc);
			System.out.println(doc.toString());
			UserInfo UserInfo = new UserInfo();
			UserInfo.setId(Integer.parseInt(doc.get("id")));
			UserInfo.setUsername(doc.get("username"));
			UserInfo.setRealname(doc.get("realname"));
			UserInfo.setTel(doc.get("tel"));
			pList.add(UserInfo);
		}
		pageQuery.setResults(pList);
		return pageQuery;
	}

	@Override
	public void addUserInfoIndex(UserInfo p) throws IOException {
		Document doc = new Document();
		doc.add(new StringField("id", p.getId() + "", Field.Store.YES));
		doc.add(new TextField("username", p.getUsername(), Field.Store.YES));
		doc.add(new StringField("tel", p.getTel(), Field.Store.YES));
		doc.add(new TextField("realname", p.getRealname(), Field.Store.YES));
		indexWriter.addDocument(doc);
		indexWriter.commit();
	}

	@Override
	public void deleteUserInfoIndexById(String id) throws IOException {
		indexWriter.deleteDocuments(new Term("id", id));
		indexWriter.commit();
	}

}
