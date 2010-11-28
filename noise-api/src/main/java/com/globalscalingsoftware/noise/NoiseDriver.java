package com.globalscalingsoftware.noise;

import java.io.IOException;
import java.util.List;

/**
 * The driver configure and controls the noise generator.
 */
public interface NoiseDriver {

	/**
	 * Initialize the noise generator.
	 * 
	 * @throws IOException
	 */
	void init() throws IOException;

	/**
	 * Returns the device.
	 */
	String getDevice();

	/**
	 * Returns the {@link DeviceInfo device information}.
	 */
	DeviceInfo getDeviceInfo();

	/**
	 * Sets the sensor banks measurement source.
	 * 
	 * @throws IOException
	 */
	void setSensorBanksMeasurementSource(SensorBanksMeasurementSource source)
			throws IOException;

	/**
	 * Gets the set sensor banks measurement source.
	 * 
	 * @see NoiseDriver#setSensorBanksMeasurementSource(int)
	 */
	SensorBanksMeasurementSource getSensorBanksMeasurementSource();

	/**
	 * Sets the sensor sample-rate.
	 * 
	 * @throws IOException
	 */
	void setSensorSampleRate(SensorSampleRate rate) throws IOException;

	/**
	 * Gets the set sensor sample-rate.
	 * 
	 * @see NoiseDriver#setSensorSampleRate(int)
	 */
	SensorSampleRate getSensorSampleRate();

	/**
	 * Sets the GPS-tracking.
	 * 
	 * @param tracking
	 *            sets the GPS-tracking:
	 *            <ul>
	 *            <li><code>true</code>: GPS-ON</li>
	 *            <li><code>false</code>: GPS-OFF</li>
	 *            </ul>
	 * @throws IOException
	 */
	void setGPSTracking(boolean tracking) throws IOException;

	/**
	 * Gets if the GPS-tracking is set.
	 * 
	 * @see NoiseDriver#setGPSTracking(boolean)
	 */
	boolean isGPSTrackingOn();

	/**
	 * Sets the noise diode preoscillations.
	 * 
	 * @throws IOException
	 * 
	 */
	void setNoiseDiodePreoscillations(NoiseDiodePreoscillations preoscillation)
			throws IOException;

	/**
	 * Gets the set noise diode preoscillations.
	 * 
	 * @see NoiseDriver#getNoiseDiodePreoscillations()
	 */
	NoiseDiodePreoscillations getNoiseDiodePreoscillations();

	List<? extends NoiseData> getNoiseDatas();

	/**
	 * Starts the permanent data-logging.
	 * 
	 * @throws IOException
	 */
	void start() throws IOException;

	/**
	 * Stops the permanent data-logging.
	 * 
	 * @throws IOException
	 */
	void stop() throws IOException;

	void readNoiseData() throws IOException;

	/**
	 * Add a {@link NoiseDataListener}. The listener will be notified for each
	 * new data.
	 */
	void addNoiseDataListener(NoiseDataListener listener);

	/**
	 * Remove an added {@link NoiseDataListener}.
	 */
	void removeNoiseDataListener(NoiseDataListener listener);

}