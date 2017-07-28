package com.qf.springboot.config;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @Title: RedisCacheConfig.java
 * @Package com.qf.springboot.config
 * @Description: Redis作为二级缓存
 * @author haichangzhang
 * @date 2017年7月26日 下午6:09:26
 * @version V1.0
 */
public class RedisCache implements Cache {

	private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private String id;
	private static final long EXPIRE_TIME_IN_MINUTES = 30; // redis过期时间
	private RedisTemplate<Object, Object> redisTemplate;

	public RedisCache(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void putObject(Object key, Object value) {
		RedisTemplate<Object, Object> redisTemplate = getRedisTemplate();
		redisTemplate.opsForValue().set(key, value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
		logger.debug("Put query result to redis");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getObject(Object key) {
		RedisTemplate<Object, Object> redisTemplate = getRedisTemplate();
		ValueOperations opsForValue = redisTemplate.opsForValue();
		return opsForValue.get(key);
	}

	@Override
	public Object removeObject(Object key) {
		RedisTemplate<Object, Object> redisTemplate = getRedisTemplate();
		redisTemplate.delete(key);
		logger.debug("Remove cached query result from redis");
		return null;
	}

	@Override
	public void clear() {
		RedisTemplate<Object, Object> redisTemplate = getRedisTemplate();
		redisTemplate.execute((RedisCallback<?>) connection -> {
			connection.flushDb();
			return null;
		});
		logger.debug("Clear all the cached query result from redis");
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	private RedisTemplate<Object, Object> getRedisTemplate() {
		if (redisTemplate == null) {
			redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
		}
		return redisTemplate;
	}

}
