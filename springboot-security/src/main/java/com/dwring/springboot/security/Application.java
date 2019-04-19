package com.dwring.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args){
		PasswordEncoder password=new BCryptPasswordEncoder();
		System.out.print(password.encode("admin"));
		SpringApplication.run(Application.class, args);
	}
	
}
