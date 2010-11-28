package com.globalscalingsoftware.noise.internal


import com.globalscalingsoftware.noise.NoiseDriverService 
import org.junit.Before;
import org.junit.BeforeClass;

class AbstractDriverTest {
	
	static final LINUX_DEVICE = "/dev/ttyUSB0"
	
	static driverService
	
	@BeforeClass
	static void beforeClass() {
		driverService = new InjectorFactory().injector.getInstance(NoiseDriverService)
	}
	
	NoiseDriverImpl driver
	
	@Before
	void beforeTest() {
		driver = driverService.create(LINUX_DEVICE)
	}
}
