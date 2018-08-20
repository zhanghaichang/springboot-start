package com.dwring.springboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**  
* @ClassName: ApplicationConfig  
* @Description:  
* @author haichangzhang  
* @date 2017年6月8日 下午5:40:17  
*    
*/
@Configuration
@EnableCaching
@MapperScan(basePackages = "com.dwring.springboot.mapper")
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class ApplicationConfig {



}
