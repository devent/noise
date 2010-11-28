package com.globalscalingsoftware.noise.internal;

import static java.lang.String.format;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.globalscalingsoftware.noise.DeviceInfo;
import com.globalscalingsoftware.noise.NoiseData;
import com.globalscalingsoftware.noise.NoiseDataListener;
import com.globalscalingsoftware.noise.NoiseDiodePreoscillations;
import com.globalscalingsoftware.noise.NoiseDriver;
import com.globalscalingsoftware.noise.SensorBanksMeasurementSource;
import com.globalscalingsoftware.noise.SensorSampleRate;
import com.globalscalingsoftware.rsscon.BaudRate;
import com.globalscalingsoftware.rsscon.RssconDriver;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class NoiseDriverImpl implements NoiseDriver {

	private static final String INFO_COMMAND = "i";

	private static final String SET_SENSOR_BANK_MEASUREMENT_SOURCE_COMMAND = "ca";

	private static final String SET_SENSOR_SAMPLE_RATE_COMMAND = "ce";

	private static final String GPS_TRACKING_ON_COMMAND = "ct1";

	private static final String GPS_TRACKING_OFF_COMMAND = "ct0";

	private static final String SET_NOISE_DIODE_PREOSCILLATIONS_COMMAND = "co";

	private static final String START_COMMAND = "g";

	private static final String STOP_COMMAND = "s";

	private final String device;

	private final List<DeviceInfoImpl> deviceInfo;

	private final List<NoiseDataImpl> noiseDatas;

	private final RssconDriver rssconDriver;

	private final BaudRate baudRate;

	private SensorBanksMeasurementSource sensorBanksMeasurementSource;

	private SensorSampleRate sensorSampleRate;

	private boolean GPSTracking;

	private NoiseDiodePreoscillations noiseDiodePreoscillations;

	private final LinkedList<NoiseDataListener> noiseDataListeners;

	private NoiseDriverRunner driverRunner;

	private boolean running;

	@Inject
	NoiseDriverImpl(RssconDriver rssconDriver, @Assisted String device) {
		this.rssconDriver = rssconDriver;
		this.driverRunner = new NoiseDriverRunner();
		this.device = device;
		this.baudRate = BaudRate.BAUDRATE_921600;
		this.deviceInfo = new ArrayList<DeviceInfoImpl>();
		this.noiseDatas = new ArrayList<NoiseDataImpl>();

		noiseDataListeners = new LinkedList<NoiseDataListener>();

		running = false;
	}

	@Override
	public synchronized void init() throws IOException {
		throwIfRunning();
		retrieveDeviceInfo();
	}

	private void throwIfRunning() throws IOException {
		if (running) {
			throw new IOException("Driver is already running.");
		}
	}

	private void retrieveDeviceInfo() throws IOException {
		rssconDriver.open(device, baudRate);
		sendInfoCommand();
		rssconDriver.close();
	}

	private void sendInfoCommand() throws IOException {
		writeCommand(INFO_COMMAND);

		StringBuilder builder = new StringBuilder();
		byte[] buffer = new byte[255];
		rssconDriver.read(buffer);
		builder.append(new String(buffer));
		rssconDriver.read(buffer);
		builder.append(new String(buffer));

		String deviceName = builder.toString();
		String deviceNumber = "";
		String configurationSettings = "";
		boolean gPSStatus = false;
		NoiseDriverImpl driver = this;
		setDeviceInfo(new DeviceInfoImpl(deviceName, deviceNumber,
				configurationSettings, gPSStatus, driver));
	}

	private void writeCommand(String command) throws IOException {
		command = format("%s\r\t", command);
		rssconDriver.write(command.getBytes());
	}

	@Override
	public String getDevice() {
		return device;
	}

	@Override
	public DeviceInfo getDeviceInfo() {
		return deviceInfo.get(0);
	}

	private void setDeviceInfo(DeviceInfoImpl deviceInfo) {
		if (this.deviceInfo.size() == 0) {
			this.deviceInfo.add(deviceInfo);
		}

		this.deviceInfo.set(0, deviceInfo);
	}

	@Override
	public synchronized void setSensorBanksMeasurementSource(
			SensorBanksMeasurementSource source) throws IOException {
		throwIfRunning();
		if (source == sensorBanksMeasurementSource) {
			return;
		}

		sendSetSensorBanksMeasurementSourceCommand(source.getSource());
		sensorBanksMeasurementSource = source;
	}

	private void sendSetSensorBanksMeasurementSourceCommand(int source)
			throws IOException {
		rssconDriver.open(device, baudRate);
		String command = format("%s%d",
				SET_SENSOR_BANK_MEASUREMENT_SOURCE_COMMAND, source);
		writeCommand(command);
	}

	@Override
	public SensorBanksMeasurementSource getSensorBanksMeasurementSource() {
		return sensorBanksMeasurementSource;
	}

	@Override
	public synchronized void setSensorSampleRate(SensorSampleRate rate)
			throws IOException {
		throwIfRunning();
		if (rate == sensorSampleRate) {
			return;
		}

		sendSetSensorSampleRateCommand(rate.getRate());
		sensorSampleRate = rate;
	}

	private void sendSetSensorSampleRateCommand(int rate) throws IOException {
		rssconDriver.open(device, baudRate);
		String command = format("%s%d", SET_SENSOR_SAMPLE_RATE_COMMAND, rate);
		writeCommand(command);
	}

	@Override
	public SensorSampleRate getSensorSampleRate() {
		return sensorSampleRate;
	}

	@Override
	public synchronized void setGPSTracking(boolean tracking)
			throws IOException {
		throwIfRunning();
		sendGPSTrackingCommand(tracking);
		GPSTracking = tracking;
	}

	private void sendGPSTrackingCommand(boolean tracking) throws IOException {
		rssconDriver.open(device, baudRate);
		if (tracking) {
			driverRunner = new NoiseDriverRunnerGPS();
			writeCommand(GPS_TRACKING_ON_COMMAND);
		} else {
			driverRunner = new NoiseDriverRunner();
			writeCommand(GPS_TRACKING_OFF_COMMAND);
		}
	}

	@Override
	public boolean isGPSTrackingOn() {
		return GPSTracking;
	}

	@Override
	public synchronized void setNoiseDiodePreoscillations(
			NoiseDiodePreoscillations preoscillations) throws IOException {
		throwIfRunning();
		if (preoscillations == noiseDiodePreoscillations) {
			return;
		}

		sendSetNoiseDiodePreoscillationsCommand(preoscillations
				.getPreoscillation());
		noiseDiodePreoscillations = preoscillations;
	}

	private void sendSetNoiseDiodePreoscillationsCommand(int preoscillations)
			throws IOException {
		rssconDriver.open(device, baudRate);
		String command = format("%s%d",
				SET_NOISE_DIODE_PREOSCILLATIONS_COMMAND, preoscillations);
		writeCommand(command);
	}

	@Override
	public NoiseDiodePreoscillations getNoiseDiodePreoscillations() {
		return noiseDiodePreoscillations;
	}

	@Override
	public List<? extends NoiseData> getNoiseDatas() {
		return Collections.unmodifiableList(noiseDatas);
	}

	@Override
	public synchronized void start() throws IOException {
		throwIfRunning();
		sendStartCommand();
		running = true;
	}

	@Override
	public synchronized void readNoiseData() throws IOException {
		if (running) {
			driverRunner.readNoiseData(this, rssconDriver, noiseDataListeners);
		}
	}

	private void sendStartCommand() throws IOException {
		rssconDriver.open(device, baudRate);
		writeCommand(START_COMMAND);
	}

	@Override
	public synchronized void stop() throws IOException {
		throwIfNotRunning();
		running = false;
		sendStopCommand();
	}

	private void throwIfNotRunning() throws IOException {
		if (!running) {
			throw new IOException("The driver is not running.");
		}
	}

	private void sendStopCommand() throws IOException {
		writeCommand(STOP_COMMAND);
		rssconDriver.close();
	}

	@Override
	public void addNoiseDataListener(NoiseDataListener listener) {
		noiseDataListeners.addLast(listener);
	}

	@Override
	public void removeNoiseDataListener(NoiseDataListener listener) {
		noiseDataListeners.remove(listener);
	}

}
