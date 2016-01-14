package com.sauloborges.ggs.util;

import java.util.concurrent.TimeUnit;

import com.sauloborges.ggs.domain.Programmer;

public class Utils {

	public static String formatTime(long time) {
		long minutes = TimeUnit.MILLISECONDS.toMinutes(time);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(minutes);
		long mili = (time % 1000);

		String formatedTime = String.format("%02d min, %02d sec, %03d mili", minutes, seconds, mili);
		return formatedTime;
	}

	public static String calculateTimeSpentInPayQueue(Programmer programmer) {
		long time = programmer.getTimeLeavePayQueue() - programmer.getTimeEnterPayQueue();
		return formatTime(time);
	}

	public static String calculateTimeSpentInMachineCofffeQueue(Programmer programmer) {
		long time = programmer.getTimeLeaveGetTheCoffeeQueue() - programmer.getTimeEnterGetTheCoffeeQueue();
		return formatTime(time);
	}

	public static String calculateTotalTimeSpent(Programmer programmer) {
		long time = programmer.getTimeFinished() - programmer.getTimeStarted();
		return formatTime(time);
	}

	public static String calculateTimeSpentToPaidAndGotACoffee(Programmer programmer) {
		long time = programmer.getTimeFinished() - programmer.getTimePaid();
		return formatTime(time);
	}

	public static String calculateTimeSpentInCoffeeMachine(Programmer programmer) {
		long time = programmer.getTimeFinished() - programmer.getTimeStartedToGetTheCoffe();
		return formatTime(time);
	}
}
