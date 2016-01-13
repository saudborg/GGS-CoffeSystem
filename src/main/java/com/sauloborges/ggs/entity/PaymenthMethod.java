package com.sauloborges.ggs.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.sauloborges.ggs.constants.TimeConstants.*;

public enum PaymenthMethod {

	ESPRESSO("Cash", QUARTER_SECOND), //
	LATTE_MACCHIATO("Credit", HALF_SECOND);
	
	private static final List<PaymenthMethod> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	
	private static final Random RANDOM = new Random();

	private String type;

	private Integer time;

	public Integer getTime() {
		return time;
	}

	public String getType() {
		return type;
	}

	private PaymenthMethod( String type, Integer time) {
		this.time = time;
		this.type = type;
	}
	
	public static PaymenthMethod getARandomPaymenthMethod() {
		return VALUES.get(RANDOM.nextInt(VALUES.size()));
	}
}
