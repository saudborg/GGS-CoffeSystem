package com.sauloborges.ggs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sauloborges.ggs.constants.QueueConstants;
import com.sauloborges.ggs.domain.Programmer;
import com.sauloborges.ggs.domain.Statistic;
import com.sauloborges.ggs.domain.StatisticMap;
import com.sauloborges.ggs.repository.ProgrammerRepository;
import com.sauloborges.ggs.repository.StatisticRepository;

//@EnableJpaRepositories("com.sauloborges.ggs.repository.*")
@SpringBootApplication
public class Application implements CommandLineRunner {

	final static Logger logger = Logger.getLogger(Application.class);

	private final Integer PROGRAMMERS = 100;

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AnnotationConfigApplicationContext context;

	@Autowired
	QueueApplicationConfiguration queueApplication;

	@Autowired
	StatisticsComponent statisticsComponent;
	
	@Bean
	StatisticMap stats() {
		return new StatisticMap();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// initializeLogger();
		logger.info("Starting the application");
		for (int i = 1; i <= PROGRAMMERS; i++) {
			Programmer programmer = new Programmer("Programmer " + i);
			rabbitTemplate.convertAndSend(QueueConstants.CHOOSE_COFFEE_QUEUE, programmer);
		}

		while (stats().total < PROGRAMMERS*3) {
			logger.debug("continua");
		}

		System.out.println(statisticsComponent.howMuchCoffeIsSold());
		System.out.println(statisticsComponent.howMuchCoffeIsDispensedByEachCoffeeMachine());
		System.out.println(statisticsComponent.howMuchTimeDoesTheAverageProgrammerSpendGettingHerCoffee());
		System.out.println(statisticsComponent.howLongDidItTakeTheFastestAndTheSlowestProgrammerToGetHerCoffee());
		
		context.close();
	}

	private void initializeLogger() {
		Properties logProperties = new Properties();
		try {
			// load our log4j properties / configuration file
			logProperties.load(new FileInputStream("log4j.properties"));
			PropertyConfigurator.configure(logProperties);
		} catch (IOException e) {
			throw new RuntimeException("Unable to load logging property " + "log4j.properties", e);
		}
	}
}
