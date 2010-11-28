package com.globalscalingsoftware.noise;


/**
 * Every {@link NoiseDataListener} is notified if the noise generator have a
 * new {@link NoiseDataEvent}.
 */
public interface NoiseDataListener {

	/**
	 * Called if the noise generator have a new {@link NoiseDataEvent}.
	 */
	void noiseDataUpdate(NoiseDataEvent event);

}