package com.sauloborges.ggs;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sauloborges.ggs.constants.QueueConstants;
import com.sauloborges.ggs.entity.Programmer;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AnnotationConfigApplicationContext context;

	@Autowired
	QueueApplicationConfiguration queueApplication;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		for (int i = 1; i <= 101; i++) {
			Programmer programmer = new Programmer("Programmer " + i);
			rabbitTemplate.convertAndSend(QueueConstants.CHOOSE_COFFEE_QUEUE, programmer);
		}
		// context.close();
	}
}
