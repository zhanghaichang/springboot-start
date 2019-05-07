package com.dwring.springboot.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: RedisCache
 * @Description: 缓存配置-使用Lettuce客户端，自动注入配置的方式
 * @author: zhanghaichang
 * @date: 2019年4月4日 上午10:43:16
 * 
 */
@Configuration
public class RedisConfig  {
	
	/**   
	 * @Title: redisTemplate   
	 * @Description: 获取缓存操作助手对象  
	 * @param: @param factory
	 * @param: @return      
	 * @return: RedisTemplate<String,String>      
	 * @throws   
	 */
	@Bean("redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		 //创建Redis缓存操作助手RedisTemplate对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		//以下代码为将RedisTemplate的Value序列化方式由JdkSerializationRedisSerializer更换为Jackson2JsonRedisSerializer
		//此种序列化方式结果清晰、容易阅读、存储字节少、速度快，所以推荐更换
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper om=new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setKeySerializer(jackson2JsonRedisSerializer);
		//RedisTemplate对象需要指明Key序列化方式，如果声明StringRedisTemplate对象则不需要
		template.setValueSerializer(new StringRedisSerializer());
	    //template.setEnableTransactionSupport(true);//是否启用事务
		template.afterPropertiesSet();
		return template;
	}
	

}
