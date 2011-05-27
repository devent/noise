package com.globalscalingsoftware.noise.module;

import static com.google.inject.assistedinject.FactoryProvider.newFactory;

import com.globalscalingsoftware.noise.NoiseDriverService;
import com.globalscalingsoftware.noise.internal.InternalModule;
import com.globalscalingsoftware.noise.internal.NoiseDriverImpl;
import com.google.inject.AbstractModule;

public class NoiseDriverModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(NoiseDriverService.class).toProvider(
				newFactory(NoiseDriverService.class, NoiseDriverImpl.class));
		install(new InternalModule());
	}

}
