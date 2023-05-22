package io.ds.myaktion.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import io.ds.myaktion.bank.dto.Transaction;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
		MessageListenerAdapter listenerAdapter) {
			RedisMessageListenerContainer container= new RedisMessageListenerContainer();
			container.setConnectionFactory(connectionFactory);
			container.addMessageListener(listenerAdapter, new PatternTopic("processTransaction"));
			return container;
		}

	@Bean
	MessageListenerAdapter listenerAdapter(TransactionReceiver receiver) {
			MessageListenerAdapter messageListenerAdapter= new MessageListenerAdapter(receiver, "receiveTransaction");
			messageListenerAdapter.setSerializer(new Jackson2JsonRedisSerializer<>(Transaction.class));
			return messageListenerAdapter;
		}

	@Bean
	TransactionReceiver receiver() {
			return new TransactionReceiver();
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

}
