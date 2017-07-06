package com.qf.kafka.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qf.application.Application;
import com.qf.application.kafka.producer.MsgProducer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AppTest {

	@Autowired
	private MsgProducer producre;

	@Test
	public void sendMsgTest() {
		producre.send("Hello World.");

	}

}
