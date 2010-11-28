package com.globalscalingsoftware.noise;

import static java.lang.String.format;

public enum GPSQualityIndication {

	Fix_Not_Available(0), NonDifferentialGPSfixAvailable(1), DifferentialGPSFixAvailable(
			2), Estimated(6);

	public static GPSQualityIndication parseIndication(int value) {
		for (GPSQualityIndication i : GPSQualityIndication.values()) {
			if (i.indication == value) {
				return i;
			}
		}
		throw new IllegalArgumentException(
				format("Not a valid indication number given."));
	}

	private final int indication;

	private GPSQualityIndication(int indication) {
		this.indication = indication;
	}

	public int getIndication() {
		return indication;
	}
}
