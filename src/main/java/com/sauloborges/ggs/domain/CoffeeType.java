package com.sauloborges.ggs.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.sauloborges.ggs.constants.TimeConstants.*;

/**
 * Class represents one Coffee. Your type and how long it takes to be done
 * 
 * @author sauloborges
 *
 */
public enum CoffeeType {

	ESPRESSO(1, "Espresso", QUARTER_SECOND), //
	LATTE_MACCHIATO(2, "Latte Macchiato", HALF_SECOND), //
	CAPPUCCINO(3, "Cappuccino", TREE_QUARTER_SECOND);

	private static final List<CoffeeType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));

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

	public Integer getTime() {
		return time;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	private CoffeeType(Integer id, String type, Integer time) {
		this.id = id;
		this.time = time;
		this.type = type;
	}

	/**
	 * Method will return a random coffee present in the list
	 * @return
	 */
	public static CoffeeType getARandomCoffe() {
		return VALUES.get(RANDOM.nextInt(VALUES.size()));
	}

}
