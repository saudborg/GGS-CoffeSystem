package com.sauloborges.ggs;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExternalConfigComponent {

	private static Logger logger = LoggerFactory.getLogger(ExternalConfigComponent.class);
	
	@Value("${property.one}")
	private String propertyOne;

	@Value("${property.two}")
	private String propertyTwo;

	@Value("${property.three}")
	private String propertyThree;

	@PostConstruct
	public void postConstruct() {
		logger.info("Property One: " + propertyOne);
		logger.info("Property Two: " + propertyTwo);
		logger.info("Property Three: " + propertyThree);
	}

}
