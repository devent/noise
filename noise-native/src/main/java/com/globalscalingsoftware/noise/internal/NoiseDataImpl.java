package com.globalscalingsoftware.noise.internal;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

import com.globalscalingsoftware.noise.GPSData;
import com.globalscalingsoftware.noise.NoiseData;

public class NoiseDataImpl implements NoiseData {

	private final int serialNumber;

	private final List<SensorData> sensorDatas;

	private final NoiseDriverImpl driver;

	private final List<GPSData> gpsData;

	public NoiseDataImpl(int serialNumber, NoiseDriverImpl driver,
			List<SensorData> sensorDatas) {
		this.serialNumber = serialNumber;
		this.sensorDatas = sensorDatas;
		this.driver = driver;
		this.gpsData = new ArrayList<GPSData>();
	}

	public NoiseDriverImpl getDriver() {
		return driver;
	}

	@Override
	public int getSerialNumber() {
		return serialNumber;
	}

	@Override
	public int getNoiseData(int sensor) {
		return sensorDatas.get(sensor).getData();
	}

	@Override
	public void setGpsData(GPSData gpsData) {
		if (gpsData == null) {
			this.gpsData.clear();
			return;
		}
		if (this.gpsData.size() == 0) {
			this.gpsData.add(gpsData);
		} else {
			this.gpsData.set(0, gpsData);
		}
	}

	@Override
	public GPSData getGPSData() {
		return gpsData.get(0);
	}

	@Override
	public String toString() {
		StringBuilder datasString = new StringBuilder();
		for (SensorData data : sensorDatas) {
			datasString.append(format(" %s;", data.toString()));
		}
		return format("#%s -%s", serialNumber, datasString);
	}

}
