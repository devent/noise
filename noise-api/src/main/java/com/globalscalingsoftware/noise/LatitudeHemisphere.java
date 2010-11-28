package com.globalscalingsoftware.noise;

import static java.lang.String.format;

public enum LatitudeHemisphere {

	NORTH("n"), SOUTH("s");

	public static LatitudeHemisphere parse(char c) {
		return parse(Character.toString(c));
	}

	public static LatitudeHemisphere parse(String s) {
		for (LatitudeHemisphere h : LatitudeHemisphere.values()) {
			if (h.hemisphere.equalsIgnoreCase(s)) {
				return h;
			}
		}
		throw new IllegalArgumentException(format(
				"String '%s' is not a latitude hemisphere.", s));
	}

	private final String hemisphere;

	private LatitudeHemisphere(String hemisphere) {
		this.hemisphere = hemisphere;
	}

	public String getHemisphere() {
		return hemisphere;
	}
}
