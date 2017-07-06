package com.qf.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**  
* @ClassName: Application  
* @Description:  
* @author haichangzhang  
* @date 2017年6月21日 下午1:40:58  
*    
*/
@SpringBootApplication
@ImportResource({"classpath:dubbo.xml"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
