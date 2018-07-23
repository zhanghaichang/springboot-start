package com.dwring.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**   
* @Title: GlobalRepositoryRestConfigurer.java 
* @Description: data rest cross
* @author haichangzhang   
* @date 2018年7月23日 上午11:02:46 
* @version V1.0   
*/
@Configuration
public class GlobalRepositoryRestConfigurer  extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.getCorsRegistry()
		        .addMapping("/**")
		        .allowedOrigins("*")
		        .allowedHeaders("*")
	            .exposedHeaders("WWW-Authenticate") 
		        .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
		        .allowCredentials(true).maxAge(3600);
     }
}
