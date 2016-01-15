package com.sauloborges.ggs.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity which represents a Programmer.
 * 
 * Evertime that a new Programmer is created, they will get a radom payment method
 * 
 * @author sauloborges
 *
 */
@Entity
public class Programmer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private CoffeeType coffeType;

	private PaymenthMethod paymenthMethod;

	/**
	 * When the programmer starts to choose your coffee
	 */
	private long timeStarted;

	/**
	 * When the programmer finished to pay your coffee
	 */
	private long timePaid;

	/**
	 * When the programmer starts to getting a coffee in a machine
	 */
	private long timeStartedToGetTheCoffe;

	/**
	 * When the programmer gets your coffee and finished the program
	 */
	private long timeFinished;

	/**
	 * When the programmer enters in the queue to wait until a machine to pay be available
	 */
	private long timeEnterPayQueue;

	/**
	 * When the programmer leaves the queue to wait to pay and goes to machine to pay
	 */
	private long timeLeavePayQueue;

	/**
	 * When the programmer enters in the queue to wait to get your coffee until a machine be available
	 */
	private long timeEnterGetTheCoffeeQueue;

	/**
	 * When the programmer leaves the queue and goes to get your coffee
	 */
	private long timeLeaveGetTheCoffeeQueue;

	public Programmer() {
	}

	public Programmer(String name) {
		this.name = name;
		this.paymenthMethod = PaymenthMethod.getARandomPaymenthMethod();
	}

	public CoffeeType getCoffeType() {
		return coffeType;
	}

	public void setCoffeType(CoffeeType coffeType) {
		this.coffeType = coffeType;
	}

	public PaymenthMethod getPaymenthMethod() {
		return paymenthMethod;
	}

	public void setPaymenthMethod(PaymenthMethod paymenthMethod) {
		this.paymenthMethod = paymenthMethod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Programmer [id=" + id + ", name=" + name + ", coffeType=" + coffeType + ", paymenthMethod="
				+ paymenthMethod + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
