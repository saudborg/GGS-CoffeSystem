package com.sauloborges.ggs.component;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExternalConfigComponent {

	private static Logger logger = LoggerFactory.getLogger(ExternalConfigComponent.class);
	
	/**
	 * Number of programmer is getting coffee
	 */
	@Value("${programmers}")
	private Integer programmers;
	
	/**
	 * Show details about times at console 
	 */
	@Value("${time.programmer.details.console}")
	private boolean showDetailsInConsole;
	
	@PostConstruct
	public void postConstruct() {
		logger.debug("Programmers: " + getProgrammers());
		logger.debug("showDetailsInConsole: " + showDetailsInConsole());
	}

	public Integer getProgrammers() {
		return programmers;
	}

	public void setProgrammers(Integer programmers) {
		this.programmers = programmers;
	}

	public boolean showDetailsInConsole() {
		return showDetailsInConsole;
	}

	public void setShowDetailsInConsole(boolean showDetailsInConsole) {
		this.showDetailsInConsole = showDetailsInConsole;
	}

}
