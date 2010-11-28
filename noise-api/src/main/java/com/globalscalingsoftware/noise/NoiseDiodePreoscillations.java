package com.globalscalingsoftware.noise;

import static java.lang.String.format;

public enum NoiseDiodePreoscillations {

	/**
	 * No preoscillation (permanent supply).
	 */
	NONE(0),

	/**
	 * 5 samples preoscillated.
	 */
	SAMPLES5(1),

	/**
	 * Permanent oscillation (always power down between sampling)
	 */
	PERMANENT(2);

	public static NoiseDiodePreoscillations parse(int value) {
		for (NoiseDiodePreoscillations p : NoiseDiodePreoscillations.values()) {
			if (p.preoscillation == value) {
				return p;
			}
		}
		throw new IllegalArgumentException(
				format("Not a valid presoscillations number given."));
	}

	private final int preoscillation;

	private NoiseDiodePreoscillations(int preoscillation) {
		this.preoscillation = preoscillation;
	}

	public int getPreoscillation() {
		return preoscillation;
	}
}
