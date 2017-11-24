package com.dwring.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

 
/**  
* @ClassName: Application  
* @Description:  
* @author haichangzhang  
* @date 2017年7月25日 上午11:09:33  
*    
*/
@SpringBootApplication
@ComponentScan
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
