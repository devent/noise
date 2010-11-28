package com.globalscalingsoftware.noise;

/**
 * Contains the device information.
 */
public interface DeviceInfo {

	/**
	 * Returns the device name.
	 */
	String getDeviceName();

	/**
	 * Returns the device number.
	 */
	String getDeviceNumber();

	/**
	 * Returns the configuration settings.
	 */
	String getConfigurationSettings();

	/**
	 * Returns the GPS status. <code>true</code> if the GPS is activated,
	 * <code>false</code> if it's deactivated.
	 */
	boolean getGPSStatus();

	/**
	 * Returns the chip-IDs of the first 4 ADCs.
	 */
	int[] getADCIds();

}