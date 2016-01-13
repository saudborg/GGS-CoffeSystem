package com.sauloborges.ggs.receiver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sauloborges.ggs.domain.Programmer;
import com.sauloborges.ggs.domain.Statistic;
import com.sauloborges.ggs.domain.StatisticMap;
import com.sauloborges.ggs.repository.StatisticRepository;

@Component
public class StatisticsReceiver {

	@Autowired
	StatisticMap stats;

	@Autowired
	private StatisticRepository statisticRepository;

	public void receiveMessage(Statistic statisticEntity) throws InterruptedException {

		List<Programmer> listProgramers = stats.map.get(statisticEntity.getMachine());

		if (listProgramers == null)
			listProgramers = new ArrayList<Programmer>();

		listProgramers.add(statisticEntity.getProgrammer());

		statisticRepository.save(statisticEntity);
		
		stats.total++;

		// stats.map.put(statisticEntity.getMachine(), listProgramers);

	}

}
