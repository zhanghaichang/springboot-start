package com.dwring.springboot;

import com.dwring.springboot.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by zhanghaichang on 2017/4/9.
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class ApplicationTest {


    @Autowired
    private MongoTemplate mongoTemplate;



    @Test
    public void testAdd() {
        User user = new User();
        user.setName("zhanghaichang");
        user.setAge(30);
        mongoTemplate.save(user,"person");
        System.out.println(user.toString());
        System.out.println("-------------------user------------------");

    }

    @Test
    public void testFind() {
        User user = new User();
        user.setName("zhanghaichang");
        user.setAge(30);
        mongoTemplate.save(user);
        user=(User)mongoTemplate.findById("58ea3ba6649cb614387f348d",User.class,"person");
        if(user!=null){
            System.out.println(user.toString());
        }
        System.out.println("-------------------user------------------");
    }

    @Test
    public void testDelete() {
        User user = new User();
        user.setName("zhanghaichang");
        user.setAge(30);
        mongoTemplate.remove(new Query(new Criteria("Name").is("zhanghaichang")),"person");
        System.out.println("-------------------user------------------");
    }
}
