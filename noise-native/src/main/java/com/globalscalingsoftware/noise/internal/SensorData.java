package com.globalscalingsoftware.noise.internal;


class SensorData {

	private final int data;

	private final NoiseDataImpl noiseData;

	public SensorData(int data, NoiseDataImpl noiseData) {
		this.data = data;
		this.noiseData = noiseData;
	}

	public NoiseDataImpl getNoiseData() {
		return noiseData;
	}

	public int getData() {
		return data;
	}

	@Override
	public String toString() {
		return Integer.toString(data);
	}
}
