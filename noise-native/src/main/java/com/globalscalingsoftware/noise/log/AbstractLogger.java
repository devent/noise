package com.globalscalingsoftware.noise.log;

public abstract class AbstractLogger {

	protected transient org.slf4j.Logger log;

	protected AbstractLogger(Class<?> contextClass) {
		this.log = org.slf4j.LoggerFactory.getLogger(contextClass);
	}

}
