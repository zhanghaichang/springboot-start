package com.dwring.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(excludeName = "ValidationAutoConfiguration.class")
@SpringBootApplication
@MapperScan(basePackages = "com.dwring.springboot.mapper")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
