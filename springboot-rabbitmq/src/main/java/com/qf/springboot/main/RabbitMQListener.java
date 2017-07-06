package com.qf.springboot.main;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class RabbitMQListener implements ChannelAwareMessageListener {

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		 byte[] body = message.getBody();  
         System.out.println("==================receive msg : " + new String(body));
         channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
	}

}
