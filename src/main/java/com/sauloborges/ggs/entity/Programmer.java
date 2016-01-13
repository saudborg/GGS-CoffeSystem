package com.sauloborges.ggs.entity;

import java.io.Serializable;

public class Programmer extends Times implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private CoffeType coffeType;

	private PaymenthMethod paymenthMethod;

	public Programmer(String name) {
		this.name = name;
		this.paymenthMethod = PaymenthMethod.getARandomPaymenthMethod();
	}

	public CoffeType getCoffeType() {
		return coffeType;
	}

	public void setCoffeType(CoffeType coffeType) {
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
		return "Programmer [coffeType=" + coffeType + ", paymenthMethod=" + paymenthMethod + "]";
	}

}
