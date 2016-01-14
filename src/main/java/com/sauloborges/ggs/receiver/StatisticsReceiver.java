package com.sauloborges.ggs.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sauloborges.ggs.ExternalConfigComponent;
import com.sauloborges.ggs.StatisticsComponent;
import com.sauloborges.ggs.domain.Statistic;
import com.sauloborges.ggs.repository.StatisticRepository;

@Component
public class StatisticsReceiver {

	private static final Logger logger = LoggerFactory.getLogger(StatisticsReceiver.class);
	
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
		
		int numberOfQueues = 3;
		if(total == config.getProgrammers() * numberOfQueues){
			statisticComponent.setStatsDone(true);
		}

	}

}
