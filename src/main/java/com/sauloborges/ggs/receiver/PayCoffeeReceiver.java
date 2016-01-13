package com.sauloborges.ggs.receiver;


import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sauloborges.ggs.constants.QueueConstants;
import com.sauloborges.ggs.entity.Programmer;

@Component
public class PayCoffeeReceiver {

	private CountDownLatch latch = new CountDownLatch(1);

	@Autowired
	RabbitTemplate rabbitTemplate;

	public void receiveMessage(Programmer programmer) throws InterruptedException {
//		System.out.println("Received in pay queue:  <" + programmer.getName() + ">");
		
		programmer.setTimeLeavePayQueue(Calendar.getInstance().getTimeInMillis());

		Integer timeSpentByPaymenthMethod = programmer.getPaymenthMethod().getTime();
		Thread.sleep(timeSpentByPaymenthMethod); 

		programmer.setTimePaid(Calendar.getInstance().getTimeInMillis());
		
		programmer.setTimeEnterGetTheCoffeeQueue(Calendar.getInstance().getTimeInMillis());
		rabbitTemplate.convertAndSend(QueueConstants.GET_COFFEE_IN_MACHINE_QUEUE, programmer);

		latch.countDown();
//		System.out.println("Leaving pay queue:  <" + programmer.getName() + ">");
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
