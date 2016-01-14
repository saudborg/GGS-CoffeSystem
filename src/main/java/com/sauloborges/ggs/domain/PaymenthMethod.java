package com.sauloborges.ggs.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.sauloborges.ggs.constants.TimeConstants.*;

public enum PaymenthMethod {

	CASH(1, "Cash", QUARTER_SECOND), //
	CREDIT(2, "Credit", HALF_SECOND);

	private static final List<PaymenthMethod> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

	private static final Random RANDOM = new Random();

	private Integer id;

	private String type;

	private Integer time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getTime() {
		return time;
	}

	public String getType() {
		return type;
	}

	private PaymenthMethod(Integer id, String type, Integer time) {
		this.id = id;
		this.time = time;
		this.type = type;
	}

	public static PaymenthMethod getARandomPaymenthMethod() {
		return VALUES.get(RANDOM.nextInt(VALUES.size()));
	}

}
