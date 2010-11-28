package com.globalscalingsoftware.noise;

/**
 * Makes the {@link NoiseData} available.
 */
public class NoiseDataEvent {

	private final NoiseData noiseData;

	public NoiseDataEvent(NoiseData noiseData) {
		this.noiseData = noiseData;
	}

	/**
	 * Returns the {@link NoiseData} from the noise generator.
	 */
	public NoiseData getNoiseData() {
		return noiseData;
	}

}