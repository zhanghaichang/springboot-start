package com.qf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**  
* @ClassName: Application  
* @Description:  
* @author haichangzhang  
* @date 2017年7月6日 上午11:31:34  
*    
*/
@SpringBootApplication
@ComponentScan("com.qf")
public class Application  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
