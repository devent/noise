package com.globalscalingsoftware.noise.internal

import com.google.inject.AbstractModule;
import static com.google.inject.assistedinject.FactoryProvider.*;
import com.globalscalingsoftware.noise.module.DriverModule 
import com.globalscalingsoftware.rsscon.RssconDriver;
import com.globalscalingsoftware.rsscon.internal.RssconDriverImpl;
import com.google.inject.Guice 

class InjectorFactory {
	
	static class RssconModule extends AbstractModule {
		
		@Override
		protected void configure() {
			bind(RssconDriver).to(RssconDriverImpl)
		}
	}
	
	def getInjector() {
		return Guice.createInjector(new DriverModule(), new RssconModule())
	}
}
