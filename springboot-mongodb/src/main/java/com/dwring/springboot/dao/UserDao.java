package com.dwring.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.dwring.springboot.domain.User;

/**
 * Created by zhanghaichang on 2017/4/9.
 */
@Repository
public class UserDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(User user) {
		mongoTemplate.save(user);
	}

	public User find(User user) {
		return mongoTemplate.findOne(new Query(Criteria.where("uuid").is(user.getUuid())), User.class);
	}
	
	public void update(User user){
		Query query=new Query(Criteria.where("uuid").is(user.getUuid()));
		Update update=new Update().set("age", user.getAge()).set("name", user.getName());
		mongoTemplate.updateFirst(query, update, User.class);
	}

	public void delete(User user) {
		mongoTemplate.remove(new Query(Criteria.where("uuid").is(user.getUuid())),User.class);
	}
	
	public List<User> findAll() {
		return mongoTemplate.findAll(User.class);
	}
}
