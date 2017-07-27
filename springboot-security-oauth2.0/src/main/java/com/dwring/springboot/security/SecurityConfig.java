package com.dwring.springboot.security;

import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @Title: SecurityConfig.java
 * @Package com.dwring.springboot.security
 * @Description: security 项目安全的配置.
 * @author haichangzhang
 * @date 2017年7月27日 上午10:30:21
 * @version V1.0
 */
@EnableWebSecurity
@EnableOAuth2Client // 启用 OAuth 2.0 客户端
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法安全设置
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext oauth2ClientContext;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class).antMatcher("/**").authorizeRequests()
				.antMatchers("/", "/index", "/403", "/css/**", "/js/**", "/fonts/**").permitAll() // 不设限制，都允许访问
				.anyRequest().authenticated().and().logout().logoutSuccessUrl("/").permitAll().and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

	private Filter ssoFilter() {
		OAuth2ClientAuthenticationProcessingFilter githubFilter = new OAuth2ClientAuthenticationProcessingFilter(
				"/login");
		OAuth2RestTemplate githubTemplate = new OAuth2RestTemplate(github(), oauth2ClientContext);
		githubFilter.setRestTemplate(githubTemplate);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(githubResource().getUserInfoUri(),
				github().getClientId());
		tokenServices.setRestTemplate(githubTemplate);
		githubFilter.setTokenServices(tokenServices);
		return githubFilter;

	}

	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	@Bean
	@ConfigurationProperties("github.client")
	public AuthorizationCodeResourceDetails github() {
		return new AuthorizationCodeResourceDetails();
	}

	@Bean
	@ConfigurationProperties("github.resource")
	public ResourceServerProperties githubResource() {
		return new ResourceServerProperties();
	}
}
