package com.qf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author liuzh
 * @since 2015-12-12 18:22
 */
@SpringBootApplication
@ComponentScan("com.qf")
public class Application  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
