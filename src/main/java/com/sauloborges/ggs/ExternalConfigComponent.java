package com.sauloborges.ggs;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExternalConfigComponent {

	private static Logger logger = LoggerFactory.getLogger(ExternalConfigComponent.class);
	
	@Value("${programmers}")
	private Integer programmers;

	@PostConstruct
	public void postConstruct() {
		logger.info("Programmers: " + getProgrammers());
	}

	public Integer getProgrammers() {
		return programmers;
	}

	public void setProgrammers(Integer programmers) {
		this.programmers = programmers;
	}

}
