package com.sauloborges.ggs.receiver;

import static com.sauloborges.ggs.constants.TimeConstants.CHOOSING_TYPE_COFFEE;

import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sauloborges.ggs.constants.QueueConstants;
import com.sauloborges.ggs.entity.CoffeType;
import com.sauloborges.ggs.entity.Programmer;

@Component
public class ChooseCoffeeReceiver {

	private CountDownLatch latch = new CountDownLatch(1);

	@Autowired
	RabbitTemplate rabbitTemplate;

	public void receiveMessage(Programmer programmer) throws InterruptedException {
//		System.out.println("Received in choose queue:  <" + programmer.getName() + ">");
		programmer.setTimeStarted(Calendar.getInstance().getTimeInMillis());

		Thread.sleep(CHOOSING_TYPE_COFFEE);
		programmer.setCoffeType(CoffeType.getARandomCoffe());

		programmer.setTimeEnterPayQueue(Calendar.getInstance().getTimeInMillis());
		
		rabbitTemplate.convertAndSend(QueueConstants.PAY_COFFEE_QUEUE, programmer);

		// stats.map.put(Thread.currentThread().getName(), programmer);

		// Fazer uma nova fila para consultar as estatisticas

//		System.out.println("Leaving in choose queue:  <" + programmer.getName() + "> / CoffeType = "
//				+ programmer.getCoffeType().getType());
		latch.countDown();
	}

}
