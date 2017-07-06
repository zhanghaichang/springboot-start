package com.dwring.springboot.dao;

import com.dwring.springboot.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zhanghaichang on 2017/4/9.
 */
public interface UserRepository {

    User findByUserName(String name);
}
