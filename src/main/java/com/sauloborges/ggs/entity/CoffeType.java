package com.sauloborges.ggs.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.sauloborges.ggs.constants.TimeConstants.*;

public enum CoffeType {

	ESPRESSO("Espresso", QUARTER_SECOND), //
	LATTE_MACCHIATO("Latte Macchiato", HALF_SECOND), //
	CAPPUCCINO("Cappuccino", TREE_QUARTER_SECOND);

	private static final List<CoffeType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

	private static final Random RANDOM = new Random();

	private String type;

	private Integer time;

	public Integer getTime() {
		return time;
	}

	public String getType() {
		return type;
	}

	private CoffeType(String type, Integer time) {
		this.time = time;
		this.type = type;
	}

	public static CoffeType getARandomCoffe() {
		return VALUES.get(RANDOM.nextInt(VALUES.size()));
	}

}
