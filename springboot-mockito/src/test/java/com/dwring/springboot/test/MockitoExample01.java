package com.dwring.springboot.test;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @Title: MockitoTest.java
 * @Package com.dwring.springboot.test
 * @Description: TODO
 * @author haichangzhang
 * @date 2018年7月11日 下午5:02:31
 * @version V1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoExample01 {

	/**
	 * @Title: mockitoTest
	 * @Description: 检验调对象相关行为是否被调用
	 */
	@Test
	@SuppressWarnings("unchecked")
	public void mockitoTest() {
		// Mock creation
		List<String> mockedList = mock(List.class);
		// Use mock object - it does not throw any "unexpected interaction"
		// exception
		mockedList.add("one"); // 调用了add("one")行为
		mockedList.clear(); // 调用了clear()行为

		// Selective, explicit, highly readable verification
		verify(mockedList).add("one"); // 检验add("one")是否已被调用
		verify(mockedList).clear(); // 检验clear()是否已被调用
	}

	/**
	 * @Title: mockitoWhenTest
	 * @Description: 配置/方法行为
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void mockitoWhenTest() {
		// you can mock concrete classes, not only interfaces
		LinkedList<String> mockedList = mock(LinkedList.class);
		// stubbing appears before the actual execution
		when(mockedList.get(0)).thenReturn("first");
		// the following prints "first"
		System.out.println(mockedList.get(0));
		// the following prints "null" because get(999) was not stubbed
		System.out.println(mockedList.get(999));

	}

	/**
	 * @Title: mockitoWhen01Test
	 * @Description: 实例化虚拟对象
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void mockitoWhen01Test() {
		// You can mock concrete classes, not just interfaces
		LinkedList<String> mockedList = mock(LinkedList.class);
		// Stubbing
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenThrow(new RuntimeException());
		// Following prints "first"
		System.out.println(mockedList.get(0));
		// Following throws runtime exception
		System.out.println(mockedList.get(1));
		// Following prints "null" because get(999) was not stubbed
		System.out.println(mockedList.get(999));
		// Although it is possible to verify a stubbed invocation, usually it's
		// just redundant
		// If your code cares what get(0) returns, then something else breaks
		// (often even before verify() gets executed).
		// If your code doesn't care what get(0) returns, then it should not be
		// stubbed. Not convinced? See here.
		verify(mockedList).get(0);

	}

	/**
	 * @Title: mockitoEqualsTest
	 * @Description: 参数匹配(equals方法来验证参数是否一致)
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void mockitoEqualsTest() {
		// You can mock concrete classes, not just interfaces
		LinkedList<String> mockedList = mock(LinkedList.class);
		// Stubbing using built-in anyInt() argument matcher
		when(mockedList.get(anyInt())).thenReturn("element");
		// Stubbing using custom matcher (let's say isValid() returns your own
		// matcher implementation):
		// when(mockedList.contains(argThat(isValid()))).thenReturn("element");
		// Following prints "element"
		System.out.println(mockedList.get(999));
		// You can also verify using an argument matcher
		verify(mockedList).get(anyInt());
		// Argument matchers can also be written as Java 8 Lambdas
		// verify(mockedList).add(argThat(someString -> someString.length() >
		// 5));

	}

	/**
	 * @Title: mockitoVerifyTest
	 * @Description: 校验次数
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void mockitoVerifyTest() {
		LinkedList<String> mockedList = mock(LinkedList.class);
		// Use mock
		mockedList.add("once");
		mockedList.add("twice");
		mockedList.add("twice");
		mockedList.add("three times");
		mockedList.add("three times");
		mockedList.add("three times");

		// Follow two verifications work exactly the same - times(1) is used by
		// default
		verify(mockedList).add("once");
		verify(mockedList, times(1)).add("once");

		// Exact number of invocations verification
		verify(mockedList, times(2)).add("twice");
		verify(mockedList, times(3)).add("three times");

		// Verification using never(). never() is an alias to times(0)
		verify(mockedList, never()).add("never happened");

		// Verification using atLeast()/atMost()
		verify(mockedList, atLeastOnce()).add("three times");
		verify(mockedList, atLeast(2)).add("three times");
		verify(mockedList, atMost(5)).add("three times");

	}

}
