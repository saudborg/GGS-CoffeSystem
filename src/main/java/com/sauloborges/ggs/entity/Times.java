package com.sauloborges.ggs.entity;

public class Times {

private long timeStarted;
	
	private long timePaid;
	
	private long timeStartedToGetTheCoffe;

	private long timeFinished;
	
	private long timeEnterPayQueue;
	
	private long timeLeavePayQueue;
	
	private long timeEnterGetTheCoffeeQueue;
	
	private long timeLeaveGetTheCoffeeQueue;
	
	
	public long getTimeStarted() {
		return timeStarted;
	}

	public void setTimeStarted(long timeStarted) {
		this.timeStarted = timeStarted;
	}

	public long getTimeFinished() {
		return timeFinished;
	}

	public void setTimeFinished(long timeFinished) {
		this.timeFinished = timeFinished;
	}
	
	public long getTimePaid() {
		return timePaid;
	}

	public void setTimePaid(long timePaid) {
		this.timePaid = timePaid;
	}

	public long getTimeStartedToGetTheCoffe() {
		return timeStartedToGetTheCoffe;
	}

	public void setTimeStartedToGetTheCoffe(long timeStartedToGetTheCoffe) {
		this.timeStartedToGetTheCoffe = timeStartedToGetTheCoffe;
	}

	public long getTimeEnterPayQueue() {
		return timeEnterPayQueue;
	}

	public void setTimeEnterPayQueue(long timeEnterPayQueue) {
		this.timeEnterPayQueue = timeEnterPayQueue;
	}

	public long getTimeLeavePayQueue() {
		return timeLeavePayQueue;
	}

	public void setTimeLeavePayQueue(long timeLeavePayQueue) {
		this.timeLeavePayQueue = timeLeavePayQueue;
	}

	public long getTimeEnterGetTheCoffeeQueue() {
		return timeEnterGetTheCoffeeQueue;
	}

	public void setTimeEnterGetTheCoffeeQueue(long timeEnterGetTheCoffeeQueue) {
		this.timeEnterGetTheCoffeeQueue = timeEnterGetTheCoffeeQueue;
	}

	public long getTimeLeaveGetTheCoffeeQueue() {
		return timeLeaveGetTheCoffeeQueue;
	}

	public void setTimeLeaveGetTheCoffeeQueue(long timeLeaveGetTheCoffeeQueue) {
		this.timeLeaveGetTheCoffeeQueue = timeLeaveGetTheCoffeeQueue;
	}
}
