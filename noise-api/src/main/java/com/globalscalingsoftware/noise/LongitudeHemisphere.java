package com.globalscalingsoftware.noise;

import static java.lang.String.format;

public enum LongitudeHemisphere {

	EAST("e"), WEST("w");

	public static LongitudeHemisphere parse(char c) {
		return parse(Character.toString(c));
	}

	public static LongitudeHemisphere parse(String s) {
		for (LongitudeHemisphere h : LongitudeHemisphere.values()) {
			if (h.hemisphere.equalsIgnoreCase(s)) {
				return h;
			}
		}
		throw new IllegalArgumentException(format(
				"String '%s' is not a longitude hemisphere.", s));
	}

	private final String hemisphere;

	private LongitudeHemisphere(String hemisphere) {
		this.hemisphere = hemisphere;
	}

	public String getHemisphere() {
		return hemisphere;
	}
}
