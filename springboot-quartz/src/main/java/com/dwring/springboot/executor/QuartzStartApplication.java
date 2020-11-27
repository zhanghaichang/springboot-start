package com.dwring.springboot.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
* @ClassName: QuartzStartApplication
* @Description: Spring Boot集成定时任务Quartz
* @author zhanghaichang
* @date 2020年11月27日
*
*/
@SpringBootApplication
public class QuartzStartApplication {

	public static void main(String[] args) {
        SpringApplication.run(QuartzStartApplication.class, args);
	}

}