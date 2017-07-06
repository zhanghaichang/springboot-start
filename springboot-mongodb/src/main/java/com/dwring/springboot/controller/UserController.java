package com.dwring.springboot.controller;

import com.dwring.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhanghaichang on 2017/4/9.
 */
@RestController
public class UserController {

    @Autowired
    private User user;

    @RequestMapping("/")
    public String index(){
        return "Hello World index."+user.toString();
    }
}
