package com.dwring.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by zhanghaichang on 2017/4/9.
 */
@SpringBootApplication
@ServletComponentScan
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

}
