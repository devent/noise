package com.globalscalingsoftware.noise.internal

import com.globalscalingsoftware.noise.NoiseDiodePreoscillations 
import com.globalscalingsoftware.noise.SensorBanksMeasurementSource 
import com.globalscalingsoftware.noise.SensorSampleRate 
import org.junit.Test;


class DriverSetDeviceConfigurationTest extends AbstractDriverTest {
	
	@Test
	void testSetSensorBanksMeasurementSourceToGMRSignals() {
		def source = SensorBanksMeasurementSource.GMR_SIGNALS
		
		driver.init()
		driver.setSensorBanksMeasurementSource(source)
		
		def driverSource = driver.getSensorBanksMeasurementSource() 
		assert driverSource == source
	}
	
	@Test
	void testSetSensorSampleRateTo11Hz() {
		def rate = SensorSampleRate.RATE1_1
		
		driver.init()
		driver.setSensorSampleRate(rate)
		
		def driverRate = driver.getSensorSampleRate() 
		assert driverRate == rate
	}
	
	@Test
	void testSetSensorSampleRateTo50Hz() {
		def rate = SensorSampleRate.RATE5_0
		
		driver.init()
		driver.setSensorSampleRate(rate)
		
		def driverRate = driver.getSensorSampleRate() 
		assert driverRate == rate
	}
	
	@Test
	void testSetSensorSampleRateTo22Hz() {
		def rate = SensorSampleRate.RATE22
		
		driver.init()
		driver.setSensorSampleRate(rate)
		
		def driverRate = driver.getSensorSampleRate() 
		driverRate == rate
	}
	
	@Test
	void testSetSensorSampleRateTo101Hz() {
		def rate = SensorSampleRate.RATE101
		
		driver.init()
		driver.setSensorSampleRate(rate)
		
		def driverRate = driver.getSensorSampleRate() 
		assert driverRate == rate
	}
	
	@Test
	void testSetGPSTrackingToOn() {
		def tracking = true
		
		driver.init()
		driver.setGPSTracking(tracking)
		
		def driverTracking = driver.isGPSTrackingOn() 
		driverTracking == tracking
	}
	
	@Test
	void testSetGPSTrackingToOff() {
		def tracking = false
		
		driver.init()
		driver.setGPSTracking(tracking)
		
		def driverTracking = driver.isGPSTrackingOn() 
		driverTracking == tracking
	}
	
	@Test
	void testSetNoiseDiodePreoscillationsToNone() {
		def pre = NoiseDiodePreoscillations.NONE
		
		driver.init()
		driver.setNoiseDiodePreoscillations(pre)
		
		def driverPre = driver.getNoiseDiodePreoscillations() 
		driverPre == pre
	}
	
	@Test
	void testSetNoiseDiodePreoscillationsTo5Samples() {
		def pre = NoiseDiodePreoscillations.SAMPLES5
		
		driver.init()
		driver.setNoiseDiodePreoscillations(pre)
		
		def driverPre = driver.getNoiseDiodePreoscillations() 
		assert driverPre == pre
	}
	
	@Test
	void testSetNoiseDiodePreoscillationsToPremanent() {
		def pre = NoiseDiodePreoscillations.PERMANENT
		
		driver.init()
		driver.setNoiseDiodePreoscillations(pre)
		
		def driverPre = driver.getNoiseDiodePreoscillations() 
		assert driverPre == pre
	}
}
