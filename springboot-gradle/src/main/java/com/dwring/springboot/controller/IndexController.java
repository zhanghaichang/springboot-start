package com.dwring.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhanghaichang on 2017/8/3.
 */
@RestController
public class IndexController
{
    @GetMapping("/")
    public String index(){
        return "Hello Gradle!";
    }
}
