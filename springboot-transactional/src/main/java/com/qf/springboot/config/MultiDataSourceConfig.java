package com.qf.springboot.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * @ClassName MultiDataSourceConfig
 * @Description TODO
 * @author zhanghaichang
 * @date: 2017年9月20日 下午2:38:55
 *
 */
@Configuration
public class MultiDataSourceConfig {

	@Primary
	@Bean("primaryDataSource")
	@ConfigurationProperties("spring.datasource.druid.one")
	public DataSource dataSourceOne() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean("secondaryDataSource")
	@ConfigurationProperties("spring.datasource.druid.two")
	public DataSource dataSourceTwo() {
		return DruidDataSourceBuilder.create().build();
	}

}
