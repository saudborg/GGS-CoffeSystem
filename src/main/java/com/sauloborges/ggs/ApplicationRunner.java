package com.sauloborges.ggs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.sauloborges.ggs.component.ExternalConfigComponent;
import com.sauloborges.ggs.component.StatisticsComponent;
import com.sauloborges.ggs.constants.QueueConstants;
import com.sauloborges.ggs.constants.TimeConstants;
import com.sauloborges.ggs.domain.Programmer;

/**
 * Represent the command line application. In this class will be show the application in progress
 * @author sauloborges
 *
 */
@Component
public class ApplicationRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

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

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("Starting the application for " + config.getProgrammers() + " programmers");
		System.out.println("Starting the application for " + config.getProgrammers() + " programmers");

		// Create a number of programmers that it is configured in application.context
		for (int i = 1; i <= config.getProgrammers(); i++) {
			Programmer programmer = new Programmer("Programmer " + i);
			// Send to a queue where the programmer will choose a coffee type he would like
			rabbitTemplate.convertAndSend(QueueConstants.CHOOSE_COFFEE_QUEUE, programmer);
		}

		while (statisticsComponent.isStatsDone() == false) {
			// Waiting until all the statistics have been processed
			Thread.sleep(TimeConstants.HALF_SECOND);
			if (!config.showDetailsInConsole())
				System.out.print(".");
		}

		System.out.println(statisticsComponent.howMuchCoffeIsSold());
		System.out.println(statisticsComponent.howMuchCoffeIsDispensedByEachCoffeeMachine());
		System.out.println(statisticsComponent.howMuchTimeDoesTheAverageProgrammerSpendGettingHerCoffee());
		System.out.println(statisticsComponent.howLongDidItTakeTheFastestAndTheSlowestProgrammerToGetHerCoffee());

	}

}
