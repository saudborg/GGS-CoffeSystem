package com.sauloborges.ggs;

import static com.sauloborges.ggs.constants.QueueConstants.CHOOSE_COFFEE_QUEUE;
import static com.sauloborges.ggs.constants.QueueConstants.GET_COFFEE_IN_MACHINE_QUEUE;
import static com.sauloborges.ggs.constants.QueueConstants.PAY_COFFEE_QUEUE;
import static com.sauloborges.ggs.constants.QueueConstants.STATISTICS_QUEUE;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sauloborges.ggs.receiver.ChooseCoffeeReceiver;
import com.sauloborges.ggs.receiver.GetCoffeeInMachineReceiver;
import com.sauloborges.ggs.receiver.PayCoffeeReceiver;
import com.sauloborges.ggs.receiver.StatisticsReceiver;

@Configuration
public class QueueApplicationConfiguration {

	@Bean
	Queue chooseCoffeeQueue() {
		return new Queue(CHOOSE_COFFEE_QUEUE, false);
	}

	@Bean
	Queue payCoffeeQueue() {
		return new Queue(PAY_COFFEE_QUEUE, false);
	}

	@Bean
	Queue getCoffeInMachineQueue() {
		return new Queue(GET_COFFEE_IN_MACHINE_QUEUE, false);
	}
	
	@Bean
	Queue statisticsQueue() {
		return new Queue(STATISTICS_QUEUE, false);
	}


	@Bean
	TopicExchange exchange() {
		return new TopicExchange("coffee-machine");
	}

	@Bean
	Binding bindingChooseCoffee(TopicExchange exchange) {
		return BindingBuilder.bind(chooseCoffeeQueue()).to(exchange).with(CHOOSE_COFFEE_QUEUE);
	}

	@Bean
	Binding bindingPayCoffee(TopicExchange exchange) {
		return BindingBuilder.bind(payCoffeeQueue()).to(exchange).with(PAY_COFFEE_QUEUE);
	}

	@Bean
	Binding bindingGetCoffeInMachine(TopicExchange exchange) {
		return BindingBuilder.bind(getCoffeInMachineQueue()).to(exchange).with(GET_COFFEE_IN_MACHINE_QUEUE);
	}
	
	@Bean
	Binding bindingStatistics(TopicExchange exchange) {
		return BindingBuilder.bind(statisticsQueue()).to(exchange).with(STATISTICS_QUEUE);
	}

	@Bean
	SimpleMessageListenerContainer containerChooseCoffee(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(CHOOSE_COFFEE_QUEUE);
		container.setMaxConcurrentConsumers(10);
		container.setConcurrentConsumers(10);
		container.setMessageListener(new MessageListenerAdapter(chooseCoffee(), "receiveMessage"));
		return container;
	}

	@Bean
	SimpleMessageListenerContainer containerPayCoffee(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(PAY_COFFEE_QUEUE);
		container.setMaxConcurrentConsumers(5);
		container.setConcurrentConsumers(5);
		container.setMessageListener(new MessageListenerAdapter(payCoffee(), "receiveMessage"));
		return container;
	}

	@Bean
	SimpleMessageListenerContainer containerGetCoffeeInMachine(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(GET_COFFEE_IN_MACHINE_QUEUE);
		container.setMaxConcurrentConsumers(2);
		container.setConcurrentConsumers(2);
		container.setMessageListener(new MessageListenerAdapter(getCoffeeInMachine(), "receiveMessage"));
		return container;
	}
	
	@Bean
	SimpleMessageListenerContainer containerStatistics(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(STATISTICS_QUEUE);
		container.setMessageListener(new MessageListenerAdapter(statisticReceiver(), "receiveMessage"));
		return container;
	}

	@Bean
	ChooseCoffeeReceiver chooseCoffee() {
		return new ChooseCoffeeReceiver();
	}

	@Bean
	PayCoffeeReceiver payCoffee() {
		return new PayCoffeeReceiver();
	}

	@Bean
	GetCoffeeInMachineReceiver getCoffeeInMachine() {
		return new GetCoffeeInMachineReceiver();
	}
	
	@Bean
	StatisticsReceiver statisticReceiver(){
		return new StatisticsReceiver();
	}

}
