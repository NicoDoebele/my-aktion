package io.ds.myaktion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import io.ds.myaktion.dto.Transaction;
import io.ds.myaktion.redis.ProcessedTransactionReceiver;

@SpringBootApplication
public class MyaktionApplication {

	private static final Logger log = LoggerFactory.getLogger(MyaktionApplication.class);

	public static void main(String[] args) {
		log.info("Starting application");
		SpringApplication.run(MyaktionApplication.class, args);
	}

	@Bean
	RedisTemplate<String, Transaction> template(RedisConnectionFactory connectionFactory, Jackson2JsonRedisSerializer<Transaction> serializer) {
			RedisTemplate<String, Transaction> redisTemplate= new RedisTemplate<>();
			redisTemplate.setConnectionFactory(connectionFactory);
			redisTemplate.setDefaultSerializer(serializer);
			redisTemplate.afterPropertiesSet();
			return redisTemplate;
		}

	@Bean
	public Jackson2JsonRedisSerializer<Transaction> jackson2JsonRedisSerializer() {
			return new Jackson2JsonRedisSerializer<>(Transaction.class);
		}

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
			RedisMessageListenerContainer container= new RedisMessageListenerContainer();
			container.setConnectionFactory(connectionFactory);
			container.addMessageListener(listenerAdapter, new PatternTopic("transactionProcessed"));
			return container;
		}

	@Bean
	MessageListenerAdapter listenerAdapter(ProcessedTransactionReceiver receiver) {
			MessageListenerAdapter messageListenerAdapter= new MessageListenerAdapter(receiver, "receiveProcessedTransaction");
			messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(Transaction.class));
			return messageListenerAdapter;
		}

	@Bean
	ProcessedTransactionReceiver receiver() {
			return new ProcessedTransactionReceiver();
		}

}
