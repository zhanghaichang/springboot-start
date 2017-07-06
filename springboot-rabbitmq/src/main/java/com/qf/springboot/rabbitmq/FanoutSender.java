package com.qf.springboot.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String msgString = "============fanoutSender :hello i am hzb";
		System.out.println(msgString);
		this.rabbitTemplate.convertAndSend("fanoutExchange", "", msgString);
	}
}
