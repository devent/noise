package com.globalscalingsoftware.noise;

import static java.lang.String.format;

public enum SensorSampleRate {

	/**
	 * 1.1Hz.
	 */
	RATE1_1(0),

	/**
	 * 5.0Hz.
	 */
	RATE5_0(1),

	/**
	 * 22Hz.
	 */
	RATE22(2),

	/**
	 * 101Hz.
	 */
	RATE101(3);

	public static SensorSampleRate parse(int value) {
		for (SensorSampleRate r : SensorSampleRate.values()) {
			if (r.rate == value) {
				return r;
			}
		}
		throw new IllegalArgumentException(
				format("Not a valid sensor sample rate number given."));
	}

	private final int rate;

	private SensorSampleRate(int rate) {
		this.rate = rate;
	}

	public int getRate() {
		return rate;
	}
}
