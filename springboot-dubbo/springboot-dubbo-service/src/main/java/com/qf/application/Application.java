package com.qf.application;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
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
@EnableCaching
public class Application {

	@Bean
	public CountDownLatch closeLatch(){
		return new CountDownLatch(1);
	}
	
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx=SpringApplication.run(Application.class, args);
		System.out.println("项目启动!");
		CountDownLatch closeLatch=ctx.getBean(CountDownLatch.class);
		closeLatch.await();
		
	}

}
