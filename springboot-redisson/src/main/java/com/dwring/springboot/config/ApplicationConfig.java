package com.dwring.springboot.config;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Predicate;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: ApplicationConfig
 * @Description:
 * @author haichangzhang
 * @date 2017年7月25日 上午11:09:40
 * 
 */
@Configuration
@EnableCaching
@EnableSwagger2 // 启用 Swagger
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800) // 启动redis session
@EnableTransactionManagement
@MapperScan(basePackages = "com.dwring.springboot.mapper")
public class ApplicationConfig extends WebMvcConfigurerAdapter {

	@Value("${redis.node}")
	private String redisNode;
	
	 @Value(value = "${spring.messages.basename}")
	 private String basename;
	
	 @Bean(name = "messageSource")
	 public ResourceBundleMessageSource getMessageResource() {
	 ResourceBundleMessageSource messageSource = new
	 ResourceBundleMessageSource();
	 messageSource.setBasename(basename);
	 return messageSource;
	 }
	
	/**
	 * Swagger会默认把所有Controller中的RequestMapping方法都生成API出来，
	 * 实际上我们一般只需要标准接口的（像返回页面的那种Controller方法我们并不需要）， 所有你可以按下面的方法来设定要生成API的方法的要求。
	 * 如下我针对RestController注解的类和ResponseBody注解的方法才生成Swaager的API，并且排除了特定的类
	 */
	@Bean
	public Docket createRestApi() {
		Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
			@Override
			public boolean apply(RequestHandler input) {
				Class<?> declaringClass = input.declaringClass();
				if (declaringClass == BasicErrorController.class)// 排除
					return false;
				if (declaringClass.isAnnotationPresent(RestController.class)) // 被注解的类
					return true;
				if (input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
					return true;
				return false;
			}
		};
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).useDefaultResponseMessages(false).select()
				.apis(predicate)
				// 这里设置api 生成的范围，有两种方式:
				// 1.上面通过自定义过滤规则
				// 2,通过定义需要生成API的包路径
				// .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("用户接口信息服务").version("1.0")
				.contact(new Contact("zhanghaichang", "http://www.dwring.com/", "zhanghaichang@163.com"))
				.description("更多Spring Boot相关文章请关注：http://www.dwring.com/").build();
	}

	@Bean(value="redisson",destroyMethod = "shutdown")
	public RedissonClient redisson() {
		Config config = new Config();
		// config.useClusterServers().addNodeAddress("127.0.0.1:7004",
		// "127.0.0.1:7001");//redis cluster配置
		config.useSingleServer().setAddress(redisNode);// redis 单点配置
		return Redisson.create(config);
	}

	@Bean
	CacheManager cacheManager(RedissonClient redissonClient) {
		Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
		// 创建一个名称为"testMap"的缓存，过期时间ttl为24秒钟，同时最长空闲时maxIdleTime为12秒钟。
		config.put("testMap", new CacheConfig(24 * 60 * 1000, 12 * 60 * 1000));
		return new RedissonSpringCacheManager(redissonClient, config);
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(getMessageResource());
		return localValidatorFactoryBean;
	}

	@Override
	public Validator getValidator() {
		return validator();
	}
}
