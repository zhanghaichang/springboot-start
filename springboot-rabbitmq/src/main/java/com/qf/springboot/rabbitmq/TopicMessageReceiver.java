package com.qf.springboot.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.message")
public class TopicMessageReceiver {

	@RabbitHandler
	public void process(String msg) {
		System.out.println("====================topicMessageReceiver  : " + msg);
	}

}
