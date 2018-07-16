package com.dwring.springboot.config;

import javax.sql.DataSource;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**  
* @ClassName: ApplicationConfig  
* @Description:  
* @author haichangzhang  
* @date 2017年6月8日 下午5:40:17  
*    
*/
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(DruidDataSourceProperties.class)
public class ApplicationConfig {

	@Autowired
	private DruidDataSourceProperties properties;

	@Bean
	@Primary
	public DataSource druidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(properties.getDriverClassName());
		druidDataSource.setUrl(properties.getUrl());
		druidDataSource.setUsername(properties.getUsername());
		druidDataSource.setPassword(properties.getPassword());
		druidDataSource.setInitialSize(properties.getInitialSize());
		druidDataSource.setMinIdle(properties.getMinIdle());
		druidDataSource.setMaxActive(properties.getMaxActive());
		druidDataSource.setMaxWait(properties.getMaxWait());
		druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
		druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
		druidDataSource.setValidationQuery(properties.getValidationQuery());
		druidDataSource.setTestWhileIdle(properties.isTestWhileIdle());
		druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
		druidDataSource.setTestOnReturn(properties.isTestOnReturn());
		druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(
				properties.getMaxPoolPreparedStatementPerConnectionSize());
		druidDataSource.setConnectionProperties(properties.getConnectionProperties());
		try {
			druidDataSource.setFilters(properties.getFilters());
			druidDataSource.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return druidDataSource;
	}

	@Bean
	@ConditionalOnMissingBean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");
		// 添加初始化参数：initParams
		// 白名单：
		// servletRegistrationBean.addInitParameter("allow","127.0.0.1");
		// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
		// permitted to view this page.
		// 登录查看信息的账号密码.
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin");
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "true");
		return servletRegistrationBean;
	}

	@Bean
	@ConditionalOnMissingBean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
	@Bean
	public EmbeddedServletContainerFactory servletContainerFactory() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory() {
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};
		factory.addAdditionalTomcatConnectors(createHttpConnector());
		return factory;
	}

	private Connector createHttpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setSecure(false);
		connector.setPort(9090);
		connector.setRedirectPort(8433);
		return connector;
	}

}
