package com.sauloborges.ggs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.sauloborges.ggs.constants.QueueConstants;
import com.sauloborges.ggs.domain.Programmer;
import com.sauloborges.ggs.domain.StatisticMap;

//@EnableJpaRepositories("com.sauloborges.ggs.repository.*")
@SpringBootApplication
public class Application implements CommandLineRunner {

	 private static final Logger logger = LoggerFactory.getLogger(Application.class);


	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AnnotationConfigApplicationContext context;

	@Autowired
	QueueApplicationConfiguration queueApplication;

	@Autowired
	StatisticsComponent statisticsComponent;
	
	@Autowired
	ExternalConfigComponent config;

	@Bean
	StatisticMap stats() {
		return new StatisticMap();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("Starting the application");
		
		for (int i = 1; i <= config.getProgrammers(); i++) {
			Programmer programmer = new Programmer("Programmer " + i);
			rabbitTemplate.convertAndSend(QueueConstants.CHOOSE_COFFEE_QUEUE, programmer);
		}

		while (stats().total < config.getProgrammers() * 3) {
		}

		System.out.println(statisticsComponent.howMuchCoffeIsSold());
		System.out.println(statisticsComponent.howMuchCoffeIsDispensedByEachCoffeeMachine());
		System.out.println(statisticsComponent.howMuchTimeDoesTheAverageProgrammerSpendGettingHerCoffee());
		System.out.println(statisticsComponent.howLongDidItTakeTheFastestAndTheSlowestProgrammerToGetHerCoffee());

		context.close();
	}

}
