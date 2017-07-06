package com.qf.springboot.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "directQueue")
public class DirectReceiver {

	@RabbitHandler
	public void process(String message) {
		System.out.println("direct message:"+message);
	}

}
