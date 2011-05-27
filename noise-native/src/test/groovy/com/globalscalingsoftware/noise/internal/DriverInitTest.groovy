package com.globalscalingsoftware.noise.internal

import org.junit.Test

class DriverInitTest extends AbstractDriverTest {

	@Test
	void testInit() {
		assert driver.device == LINUX_DEVICE
		driver.init()
	}

	@Test
	void testInitGetDeviceInfo() {
		driver.init()
		// def deviceInfo = driver.getDeviceInfo()

		// assert deviceInfo.driver == driver
	}
}
