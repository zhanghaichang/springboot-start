package com.dwring.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args){
		PasswordEncoder password=new BCryptPasswordEncoder();
		String encodedPassword=password.encode("admin");
		System.out.println(encodedPassword);
		System.out.println(password.matches("admin", encodedPassword));
		SpringApplication.run(Application.class, args);
	}
	
}
