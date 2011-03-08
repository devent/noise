package com.globalscalingsoftware.noise.internal


import com.globalscalingsoftware.noise.NoiseDataListener 
import static com.globalscalingsoftware.noise.NoiseDiodePreoscillations.*;
import static com.globalscalingsoftware.noise.SensorBanksMeasurementSource.*;
import static com.globalscalingsoftware.noise.SensorSampleRate.*;
import org.junit.Test;

class DriverStartTest extends AbstractDriverTest {
	
	@Test
	void testStartAndReadNoiseData() {
		driver.addNoiseDataListener({event ->  println event.noiseData } as NoiseDataListener)
		driver.setGPSTracking false
		driver.setNoiseDiodePreoscillations NONE
		driver.setSensorBanksMeasurementSource GMR_SIGNALS
		driver.setSensorSampleRate RATE22
		driver.init()
		driver.start()
		
		def running = true
		def thread = new Thread().start {
			while(running) {
				driver.readNoiseData()
			}
		}
		
		Thread.sleep 1000
		running = false
		thread.join()
		driver.stop()
	}
	
	@Test
	void testStartAndReadNoiseDataWithGPS() {
		driver.addNoiseDataListener({ event ->  
			println event.noiseData 
			println event.noiseData.GPSData
		} as NoiseDataListener)
		driver.setGPSTracking true
		driver.setNoiseDiodePreoscillations NONE
		driver.setSensorBanksMeasurementSource GMR_SIGNALS
		driver.setSensorSampleRate RATE22
		driver.init()
		driver.start()
		
		def running = true
		def thread = new Thread().start {
			while(running) {
				driver.readNoiseData()
			}
		}
		
		Thread.sleep 1000
		running = false
		thread.join()
		driver.stop()
	}
	
	@Test
	void testSetSensorBanksMeasurementSourceWhileReadNoiseData() {
		driver.init()
		driver.start()
		
		def running = true
		def thread = new Thread().start {
			while(running) {
				driver.readNoiseData()
				Thread.sleep 500
			}
		}
		
		def ioexception = false
		try {
			driver.setSensorBanksMeasurementSource GMR_SIGNALS
		} catch (IOException e) {
			ioexception = true
		}
		
		Thread.sleep 5000
		running = false
		thread.join()
		driver.stop()
		
		assert ioexception : "Expected IOException while changing the parameters on running device."
	}
}
