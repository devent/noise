package com.globalscalingsoftware.noise.internal;

import com.globalscalingsoftware.noise.log.AbstractLogger;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

interface LoggerFactory {

	Logger create(@Assisted Class<?> clazz);

	class Logger extends AbstractLogger {

		@Inject
		Logger(@Assisted Class<?> contextClass) {
			super(contextClass);
		}

		public void readLine(String line) {
			if (log.isDebugEnabled()) {
				line = getLine(line);
				log.debug("Read the line ``{}''.", line);
			}
		}

		private String getLine(String line) {
			return line.replaceAll("\n", "\\\\n").replaceAll("\t", "\\\\t")
					.replace("\r", "\\\\r");
		}

		public void lineNotMatch(String line) {
			if (log.isWarnEnabled()) {
				line = getLine(line);
				log.warn("The line ``{}'' does not match..", line);
			}
		}

		public void readGPSData(GPSDataImpl gpsdata) {
			log.debug("Read the GPS data ``{}''.", gpsdata);
		}

	}
}
