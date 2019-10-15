package com.dwring.springboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.dwring.springboot.mapper*")
public class ApplicationConfig {


}
