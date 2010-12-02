package com.globalscalingsoftware.noise.internal;

import static java.lang.String.format;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.globalscalingsoftware.noise.NoiseDataEvent;
import com.globalscalingsoftware.noise.NoiseDataListener;
import com.globalscalingsoftware.rsscon.RssconDriver;

class NoiseDriverRunner {

	public void readNoiseData(NoiseDriverImpl driver,
			RssconDriver rssconDriver,
			Iterable<NoiseDataListener> noiseDataListeners) throws IOException {
		String line = readLine(rssconDriver);
		while (!line.startsWith("+")) {
			line = readLine(rssconDriver);
		}

		NoiseDataImpl noiseData = parseLine(line, driver);
		fireNoiseDataUpdate(noiseDataListeners, noiseData);
	}

	private String readLine(RssconDriver rssconDriver) throws IOException {
		StringBuilder builder = new StringBuilder();
		byte[] buffer = new byte[1];
		while (true) {
			rssconDriver.read(buffer);
			String str = new String(buffer);
			builder.append(str);
			if (str.equals("\n")) {
				break;
			}
		}
		return builder.toString();
	}

	private NoiseDataImpl parseLine(String line, NoiseDriverImpl driver) {
		String[] groups = splitGroups(line);
		int serial = parseInteger(groups[0]);
		List<SensorData> sensorDatas = new ArrayList<SensorData>();
		NoiseDataImpl noiseData = new NoiseDataImpl(serial, driver, sensorDatas);
		GPSDataImpl gpsData = parseGPSData(line, noiseData);
		noiseData.setGpsData(gpsData);
		fillSensorData(sensorDatas, groups, noiseData);
		return noiseData;
	}

	protected GPSDataImpl parseGPSData(String line, NoiseDataImpl noiseData) {
		return null;
	}

	protected int parseInteger(String string) {
		if (string.startsWith("+")) {
			string = string.substring(1);
		}
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(format(
					"The string '%s' can not be parsed to a integer.", string));
		}
	}

	private void fillSensorData(List<SensorData> sensorDatas, String[] groups,
			NoiseDataImpl noiseData) {
		for (int i = 1; i <= 8; i++) {
			int d = parseInteger(groups[i]);
			SensorData data = new SensorData(d, noiseData);
			sensorDatas.add(data);
		}
	}

	private String[] splitGroups(String line) {
		return line.split("\t");
	}

	private void fireNoiseDataUpdate(
			Iterable<NoiseDataListener> noiseDataListeners,
			NoiseDataImpl noiseData) {
		NoiseDataEvent event = createNoiseDataEvent(noiseData);
		for (NoiseDataListener l : noiseDataListeners) {
			l.noiseDataUpdate(event);
		}
	}

	private NoiseDataEvent createNoiseDataEvent(final NoiseDataImpl noiseData) {
		NoiseDataEvent event = new NoiseDataEvent(noiseData);
		return event;
	}
}
