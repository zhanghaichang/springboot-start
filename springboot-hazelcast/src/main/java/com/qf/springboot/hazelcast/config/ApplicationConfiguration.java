package com.qf.springboot.hazelcast.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;

@Configuration
@EnableSpringHttpSession
@EnableHazelcastHttpSession
@EnableCaching
public class ApplicationConfiguration {

	
	@Bean
	public Config hazelcastConfig() {
		String name = "GatewayManager";
		Config ret = new Config();
		ret.getGroupConfig().setName(name).setPassword("dev");
		ret.setInstanceName(name);
		return ret;
	}
}
