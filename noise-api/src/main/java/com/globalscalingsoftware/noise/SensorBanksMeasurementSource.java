package com.globalscalingsoftware.noise;

import static java.lang.String.format;

public enum SensorBanksMeasurementSource {

	/**
	 * Measure GMR Signals (horizontal x and y magn. Fields).
	 */
	GMR_SIGNALS(0),

	/**
	 * Measure external noise-diode voltages.
	 */
	EX_NOISE_DIODE_VOLTAGES(6);

	public static SensorBanksMeasurementSource parse(int value) {
		for (SensorBanksMeasurementSource s : SensorBanksMeasurementSource
				.values()) {
			if (s.source == value) {
				return s;
			}
		}
		throw new IllegalArgumentException(
				format("Not a valid sensor banks measurement source number given."));
	}

	private final int source;

	private SensorBanksMeasurementSource(int source) {
		this.source = source;
	}

	public int getSource() {
		return source;
	}
}
