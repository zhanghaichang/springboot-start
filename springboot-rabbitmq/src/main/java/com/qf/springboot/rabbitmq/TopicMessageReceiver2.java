package com.qf.springboot.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.messages")
public class TopicMessageReceiver2 {

	@RabbitHandler
	public void process(String msg) {
		System.out.println("====================topicMessageReceiver2  : " + msg);
	}

}
