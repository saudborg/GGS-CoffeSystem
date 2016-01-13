package com.sauloborges.ggs.receiver;

import static com.sauloborges.ggs.constants.TimeConstants.FINDING_A_CUP;
import static com.sauloborges.ggs.constants.TimeConstants.GETTING_THE_CUP;
import static com.sauloborges.ggs.constants.TimeConstants.PICKING_THE_TYPE;
import static com.sauloborges.ggs.constants.TimeConstants.PUTTING_INTO_OUTLET;

import java.util.Calendar;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sauloborges.ggs.entity.Programmer;

@Component
public class GetCoffeeInMachineReceiver {

	private CountDownLatch latch = new CountDownLatch(1);

	@Autowired
	RabbitTemplate rabbitTemplate;

	public void receiveMessage(Programmer programmer) throws InterruptedException {
		// System.out.println("Received in a machine to get a coffee: <" +
		// programmer.getName() + ">");
		programmer.setTimeLeaveGetTheCoffeeQueue(Calendar.getInstance().getTimeInMillis());
		programmer.setTimeStartedToGetTheCoffe(Calendar.getInstance().getTimeInMillis());

		Thread.sleep(FINDING_A_CUP);
		Thread.sleep(PUTTING_INTO_OUTLET);
		Thread.sleep(PICKING_THE_TYPE);

		Integer timeByTypeOfCoffe = programmer.getCoffeType().getTime(); // PREPARING
		Thread.sleep(timeByTypeOfCoffe);

		Thread.sleep(GETTING_THE_CUP);

		programmer.setTimeFinished(Calendar.getInstance().getTimeInMillis());

		latch.countDown();
		StringBuffer sb = new StringBuffer();
		sb.append("User <" + programmer.getName() + "> got you coffee. ");
		sb.append("\n\tSpent total: " + calculateTotalTimeSpent(programmer));
		sb.append("\n\tSpent in the pay queue: " + calculateTimeSpentInPayQueue(programmer));
		sb.append("\n\tSpent to paid and got a coffee: " + calculateTimeSpentToPaidAndGotACoffee(programmer));
		sb.append("\n\tSpent in the machine coffee queue: " + calculateTimeSpentInMachineCofffeQueue(programmer));
		sb.append("\n\tSpent to got the coffee in machine: " + calculateTimeSpentInCoffeeMachine(programmer));
		System.out.println(sb.toString());
	}

	private String calculateTimeSpentInPayQueue(Programmer programmer) {
		long time = programmer.getTimeLeavePayQueue() - programmer.getTimeEnterPayQueue();
		return formatTime(time);
	}

	private String calculateTimeSpentInMachineCofffeQueue(Programmer programmer) {
		long time = programmer.getTimeLeaveGetTheCoffeeQueue() - programmer.getTimeEnterGetTheCoffeeQueue();
		return formatTime(time);
	}

	private String calculateTotalTimeSpent(Programmer programmer) {
		long time = programmer.getTimeFinished() - programmer.getTimeStarted();
		return formatTime(time);
	}

	private String calculateTimeSpentToPaidAndGotACoffee(Programmer programmer) {
		long time = programmer.getTimeFinished() - programmer.getTimePaid();
		return formatTime(time);
	}

	private String calculateTimeSpentInCoffeeMachine(Programmer programmer) {
		long time = programmer.getTimeFinished() - programmer.getTimeStartedToGetTheCoffe();
		return formatTime(time);
	}

	private String formatTime(long time) {
		long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(time)
				- TimeUnit.MINUTES.toSeconds(minutes);
		long mili = (time % 1000);
		
		String formatedTime = String.format("%02d min, %02d sec, %03d mili", minutes, seconds, mili);
		return formatedTime;
	}

}
