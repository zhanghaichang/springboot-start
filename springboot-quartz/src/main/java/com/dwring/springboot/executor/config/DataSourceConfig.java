package com.dwring.springboot.executor.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
public class DataSourceConfig {

	@Bean
	@QuartzDataSource
	@ConfigurationProperties(prefix = "spring.quartz.properties.org.quartz.datasource")
	DataSource quartzDataSource() {
		return DruidDataSourceBuilder.create().build();
	}
}
