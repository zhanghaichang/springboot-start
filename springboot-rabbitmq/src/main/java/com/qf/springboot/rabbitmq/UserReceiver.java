package com.qf.springboot.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.qf.springboot.domain.User;

@Component
@RabbitListener(queues="user")
public class UserReceiver {

	@RabbitHandler
	public void process(User user){
		System.out.println("==============user Receiver:"+user.toString());
	}
}
