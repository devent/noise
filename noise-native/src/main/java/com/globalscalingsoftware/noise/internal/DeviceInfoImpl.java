package com.globalscalingsoftware.noise.internal;

import com.globalscalingsoftware.noise.DeviceInfo;

public class DeviceInfoImpl implements DeviceInfo {

	private final String deviceName;

	private final String deviceNumber;

	private final String configurationSettings;

	private final boolean GPSStatus;

	private final NoiseDriverImpl driver;

	public DeviceInfoImpl(String deviceName, String deviceNumber,
			String configurationSettings, boolean gPSStatus, NoiseDriverImpl driver) {
		this.deviceName = deviceName;
		this.deviceNumber = deviceNumber;
		this.configurationSettings = configurationSettings;
		this.GPSStatus = gPSStatus;
		this.driver = driver;
	}

	public NoiseDriverImpl getDriver() {
		return driver;
	}

	@Override
	public String getDeviceName() {
		return deviceName;
	}

	@Override
	public String getDeviceNumber() {
		return deviceNumber;
	}

	@Override
	public String getConfigurationSettings() {
		return configurationSettings;
	}

	@Override
	public boolean getGPSStatus() {
		return GPSStatus;
	}

	@Override
	public int[] getADCIds() {
		// TODO Auto-generated method stub
		return null;
	}

}
