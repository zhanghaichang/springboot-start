package com.qf.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qf.springboot.main.Producer;
import com.qf.springboot.rabbitmq.DirectSender;
import com.qf.springboot.rabbitmq.FanoutSender;
import com.qf.springboot.rabbitmq.HelloSender;
import com.qf.springboot.rabbitmq.TopicSender;
import com.qf.springboot.rabbitmq.UserSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class rabbitmqTest {

	@Autowired
	private Producer producer;

	@Autowired
	private HelloSender sender;

	@Autowired
	private UserSender userSender;

	@Autowired
	private TopicSender topicSender;

	@Autowired
	private FanoutSender fanoutSender;
	
	@Autowired
	private DirectSender directSender;

	@Test
	public void producerTest() {
		for (int i = 0; i < 10000; i++) {
			producer.sendMsg("Hello World.");
		}
	}

	@Test
	public void helloSendTest() {
		for (int i = 0; i < 100; i++) {
			sender.send();
		}
	}

	@Test
	public void userTest() {
		userSender.send();
	}

	@Test
	public void topicTest() {
		topicSender.send();
	}

	@Test
	public void fanoutTest() {
		fanoutSender.send();
	}
	
	@Test
	public void directTest(){
		directSender.send();
	}
}
