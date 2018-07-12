package com.dwring.springboot.test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import org.junit.Test;

import com.dwring.springboot.model.UserInfo;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
/**
 * @Title: JmockdataTest.java
 * @Package com.dwring.springboot.test
 * @Description: Jmockdta(模拟JAVA类型或对象的实例化并随机初始化对象的数据的工具)
 * @author haichangzhang
 * @date 2018年7月12日 上午10:38:17
 * @version V1.0
 */
public class JmockdataTest {
	
	
	@Test
	public void testBasicData() {
		// 基本类型模拟
		int intNum = JMockData.mock(int.class);
		int[] intArray = JMockData.mock(int[].class);
		Integer integer = JMockData.mock(Integer.class);
		Integer[] integerArray = JMockData.mock(Integer[].class);
		// 常用类型模拟
		BigDecimal bigDecimal = JMockData.mock(BigDecimal.class);
		BigInteger bigInteger = JMockData.mock(BigInteger.class);
		Date date = JMockData.mock(Date.class);
		String str = JMockData.mock(String.class);

		System.out.println("int:" + intNum);
		System.out.println("int[]:" + intArray);
		System.out.println("Integer:" + integer);
		System.out.println("Integer[]:" + integerArray);
		System.out.println("BigDecimal:" + bigDecimal);
		System.out.println("BigInteger:" + bigInteger);
		System.out.println("Date:" + date);
		System.out.println("String:" + str);
	}

	@Test
	public void testClassObj() {
		// 调用模拟数据的方法模拟Java对象
		UserInfo appBean = JMockData.mock(UserInfo.class);
		System.out.println(appBean);
	}

	@Test
	public void testdd() {
		// 更改随机范围
		MockConfig mockConfig = new MockConfig().byteRange((byte) 0, Byte.MAX_VALUE)
				.shortRange((short) 0, Short.MAX_VALUE).intRange(0, Integer.MAX_VALUE)
				.floatRange(0.0f, Float.MAX_EXPONENT).doubleRange(0.0, Double.MAX_VALUE).longRange(0, Long.MAX_VALUE)
				.dateRange("2010-01-01", "2020-12-30").sizeRange(5, 10).stringSeed("a", "b", "c")
				.charSeed((char) 97, (char) 98);
		UserInfo appBean = JMockData.mock(UserInfo.class, mockConfig);
		System.out.println(appBean);
	}
}
