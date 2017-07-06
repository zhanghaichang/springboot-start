package com.qf.springboot.config;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: AmqpConfig
 * @Description:
 * @author haichangzhang
 * @date 2017年6月5日 下午2:27:11
 * 
 */
@Configuration
@EnableConfigurationProperties(RabbitProperties.class)
public class AmqpConfig {

	public static final String EXCHANGE = "spring-boot-exchange";
	public static final String ROUTINGKEY = "spring-boot-routingKey";
	public static final String QUEUENAME = "spring-boot-queue";

	// @Autowired
	// private RabbitProperties rabbitProperties;
	//
	// @Bean
	// @ConfigurationProperties(prefix = "spring.rabbitmq")
	// public ConnectionFactory connectionFactory() {
	// CachingConnectionFactory connectionFactory = new
	// CachingConnectionFactory();
	// connectionFactory.setHost(rabbitProperties.getHost());
	// connectionFactory.setUsername(rabbitProperties.getUsername());
	// connectionFactory.setPassword(rabbitProperties.getPassword());
	// connectionFactory.setVirtualHost(rabbitProperties.getVirtualHost());
	// connectionFactory.setPublisherConfirms(true);
	// return connectionFactory;
	// }
	//
	// @Bean
	// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	// public RabbitTemplate rabbitTemplate() {
	// RabbitTemplate template = new RabbitTemplate(connectionFactory());
	// template.setReceiveTimeout(60000);
	// return template;
	// }
	//
	// /**
	// * 针对消费者配置 1. 设置交换机类型 2. 将队列绑定到交换机
	// *
	// *
	// * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念 HeadersExchange
	// * ：通过添加属性key-value匹配 DirectExchange:按照routingkey分发到指定队列
	// * TopicExchange:多关键字匹配
	// */
	// @Bean
	// public DirectExchange defaultExchange() {
	// return new DirectExchange(EXCHANGE);
	// }
	//
	// @Bean
	// public Queue queue() {
	// return new Queue(QUEUENAME, true);
	// }
	//
	// @Bean
	// public Binding binding() {
	// return
	// BindingBuilder.bind(queue()).to(defaultExchange()).with(AmqpConfig.ROUTINGKEY);
	// }
	//
	// @Bean
	// public SimpleMessageListenerContainer messageContainer() {
	// SimpleMessageListenerContainer container = new
	// SimpleMessageListenerContainer(connectionFactory());
	// container.setQueues(queue());
	// container.setExposeListenerChannel(true);
	// container.setMaxConcurrentConsumers(100);
	// container.setConcurrentConsumers(5);
	// container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
	// container.setMessageListener(new RabbitMQListener());
	// return container;
	//
	// }
}
