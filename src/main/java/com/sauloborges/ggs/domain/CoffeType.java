package com.sauloborges.ggs.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.sauloborges.ggs.constants.TimeConstants.*;

public enum CoffeType {

	ESPRESSO(1, "Espresso", QUARTER_SECOND), //
	LATTE_MACCHIATO(2, "Latte Macchiato", HALF_SECOND), //
	CAPPUCCINO(3, "Cappuccino", TREE_QUARTER_SECOND);

	private static final List<CoffeType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

	private static final Random RANDOM = new Random();

	private Integer id;

	private String type;

	private Integer time;

	public Integer getTime() {
		return time;
	}

	public String getType() {
		return type;
	}

	private CoffeType(Integer id, String type, Integer time) {
		this.id = id;
		this.time = time;
		this.type = type;
	}

	public static CoffeType getARandomCoffe() {
		return VALUES.get(RANDOM.nextInt(VALUES.size()));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}