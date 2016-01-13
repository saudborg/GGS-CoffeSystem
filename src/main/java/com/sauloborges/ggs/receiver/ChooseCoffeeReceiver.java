package com.sauloborges.ggs.receiver;

import static com.sauloborges.ggs.constants.TimeConstants.CHOOSING_TYPE_COFFEE;

import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sauloborges.ggs.constants.QueueConstants;
import com.sauloborges.ggs.domain.CoffeType;
import com.sauloborges.ggs.domain.Programmer;
import com.sauloborges.ggs.domain.Statistic;
import com.sauloborges.ggs.repository.ProgrammerRepository;

@Component
public class ChooseCoffeeReceiver {

	final static Logger logger = Logger.getLogger(ChooseCoffeeReceiver.class);

	private CountDownLatch latch = new CountDownLatch(1);

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ProgrammerRepository programmerRepository;

	public void receiveMessage(Programmer programmer) throws InterruptedException {
		logger.debug("Received in choose queue:  <" + programmer.getName() + ">");
		programmer.setTimeStarted(Calendar.getInstance().getTimeInMillis());

		Thread.sleep(CHOOSING_TYPE_COFFEE);
		programmer.setCoffeType(CoffeType.getARandomCoffe());

		programmer.setTimeEnterPayQueue(Calendar.getInstance().getTimeInMillis());
		
		// save programmer
		Programmer saved = programmerRepository.save(programmer);
		programmer.setId(saved.getId());

		rabbitTemplate.convertAndSend(QueueConstants.PAY_COFFEE_QUEUE, programmer);

		// stats
		rabbitTemplate.convertAndSend(QueueConstants.STATISTICS_QUEUE, new Statistic("ChooseCoffeeReceiver" + Thread.currentThread().getId(), programmer));

		logger.debug("Leaving in choose queue:  <" + programmer.getName() + "> / CoffeType = "
				+ programmer.getCoffeType().getType());
		latch.countDown();
	}

}
