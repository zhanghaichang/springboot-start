package com.dwring.springboot.lock;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import com.dwring.springboot.config.SpringContextUtil;

/**
 * @ClassName RedisLockUtils
 * @Description TODO
 * @author zhanghaichang
 * @date: 2017年11月9日 下午5:19:01
 *
 */
public class DistributedRedisLock {

	private static final String LOCK_TITLE = "redisLock_";

	public static  void acquire(String lockName) {
		String key = LOCK_TITLE + lockName;
		RedissonClient redisson=SpringContextUtil.getBean("redisson");
		RLock mylock = redisson.getLock(key);
		mylock.lock(10, TimeUnit.SECONDS); // lock提供带timeout参数，timeout结束强制解锁，防止死锁
		System.err.println("======lock======" + Thread.currentThread().getName());
	}

	public static void release(String lockName) {
		String key = LOCK_TITLE + lockName;
		RedissonClient redisson=SpringContextUtil.getBean("redisson");
		RLock mylock = redisson.getLock(key);
		mylock.unlock();
		System.err.println("======unlock======" + Thread.currentThread().getName());
	}
}
