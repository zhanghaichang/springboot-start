package com.qf.application.kafka.producer;

import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName: MsgProducerKafka
 * @Description: 生产者
 * @author haichangzhang
 * @date 2017年6月16日 下午2:07:47
 * 
 */
@Component
public class MsgProducer {

	@Value("${spring.kafka.template.default-topic}")
	private String topic;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * @Title: send @Description:发送消息 @param @param msg @return void
	 *         返回类型 @throws
	 */
	public void send(String msg) {
		kafkaTemplate.send(topic, msg);
		kafkaTemplate.metrics();
		kafkaTemplate.execute(new KafkaOperations.ProducerCallback<String, String, Object>() {
			@Override
			public Object doInKafka(Producer<String, String> producer) {
				return null;
			}
		});

	}
}
