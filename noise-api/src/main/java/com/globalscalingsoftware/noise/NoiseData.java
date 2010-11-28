package com.globalscalingsoftware.noise;

/**
 * A set of noise data red from the noise generator.
 */
public interface NoiseData {

	/**
	 * Returns the serial number of the red data. The serial number is continues
	 * incremented number for each new data set.
	 */
	int getSerialNumber();

	/**
	 * Returns the noise data from a sensor.
	 * 
	 * @param sensor
	 *            the number of the sensor.
	 */
	int getNoiseData(int sensor);

	void setGpsData(GPSData gpsData);

	GPSData getGPSData();

}