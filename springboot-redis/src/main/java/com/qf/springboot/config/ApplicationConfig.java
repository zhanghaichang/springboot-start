package com.qf.springboot.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
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
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class ApplicationConfig   {

}
