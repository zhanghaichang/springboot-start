package com.qf.application.kafka.consumer;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @ClassName: MsgConsumer
 * @Description: 消费者
 * @author haichangzhang
 * @date 2017年6月16日 下午2:04:28
 * 
 */
public class MsgConsumer {
	
	private static final Logger logger = LoggerFactory.getLogger(MsgConsumer.class);

	@KafkaListener(topics = { "test1" }, group = "myGroup")
	public void listen(ConsumerRecord<?, ?> record) {
		Optional<?> kafkaMessage = Optional.ofNullable(record.value());
		if (kafkaMessage.isPresent()) {
			try {
				Object message = kafkaMessage.get();
				logger.info("-----------------------listen: {}", message.toString());
			} catch (Throwable throwable) {
				logger.error(throwable.getMessage(), throwable);
			}
		}
	}
}
