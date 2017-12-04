package com.dwring.springboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: ApplicationConfig
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午5:40:17
 * 
 */
@Configuration
@EnableTransactionManagement
@EnableCaching
@MapperScan(basePackages = "com.dwring.springboot.mapper")
public class ApplicationConfig {

}
