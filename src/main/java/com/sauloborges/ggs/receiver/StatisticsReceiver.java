package com.sauloborges.ggs.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sauloborges.ggs.component.ExternalConfigComponent;
import com.sauloborges.ggs.component.StatisticsComponent;
import com.sauloborges.ggs.domain.Statistic;
import com.sauloborges.ggs.repository.StatisticRepository;

/**
 * This class represents a receiver to save all the statistics about each step
 * in the program
 * 
 * It class was created so that it was not create another connection to the
 * database at each stage of the process
 * 
 * @author sauloborges
 *
 */
@Component
public class StatisticsReceiver {

	private static final Logger logger = LoggerFactory.getLogger(StatisticsReceiver.class);

	/**
	 * Count how many times the statistic will be saved
	 */
	private int total;

	@Autowired
	StatisticsComponent statisticComponent;

	@Autowired
	ExternalConfigComponent config;

	@Autowired
	private StatisticRepository statisticRepository;

	public void receiveMessage(Statistic statisticEntity) throws InterruptedException {

		logger.debug("Saving statistic: " + statisticEntity.toString());
		statisticRepository.save(statisticEntity);

		total++;

		// Number of machines in the process
		int numberOfQueues = 3;
		if (total == config.getProgrammers() * numberOfQueues) {
			// Only set OK for all the statistics when the program has sure that each programmer passed for the 3 machines
			statisticComponent.setStatsDone(true);
		}

	}

}
