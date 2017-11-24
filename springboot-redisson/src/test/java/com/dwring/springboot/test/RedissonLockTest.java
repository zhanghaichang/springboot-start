package com.dwring.springboot.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.dwring.springboot.lock.DistributedRedisLock;

/**
 * @ClassName RedissonLockTest
 * @Description TODO
 * @author zhanghaichang
 * @date: 2017年11月9日 下午6:02:49
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissonLockTest {


	// @Autowired
	// private RedissonClient redisson;

	// @Test
	// public void redisLockTest() {
	// for (int i = 0; i < 3; i++) {
	// Thread t = new Thread(new Runnable() {
	// @Override
	// public void run() {
	// try {
	// String key = "test123";
	// lock.acquire(key);
	// Thread.sleep(1000); // 获得锁之后可以进行相应的处理
	// System.err.println("======获得锁后进行相应的操作======");
	// lock.release(key);
	// System.err.println("=============================");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// t.start();
	// }
	// }

	// @Test
	// public void redisTest() {
	// RBucket<String> rBucket = redisson.getBucket("number");
	// // String value = rBucket.get();
	// // System.out.println(value);
	// rBucket.delete();
	// }

	@Test
	public void lockTest() {
		for (int i = 0; i < 100; i++) {
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String key = "test123";
						DistributedRedisLock.acquire(key);
						Thread.sleep(1000); // 获得锁之后可以进行相应的处理
						System.err.println("======获得锁后进行相应的操作======");
						DistributedRedisLock.release(key);
						System.err.println("=============================");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			t.start();
		}

	}
}
