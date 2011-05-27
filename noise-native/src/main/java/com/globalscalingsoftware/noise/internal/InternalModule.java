package com.globalscalingsoftware.noise.internal;

import static com.google.inject.assistedinject.FactoryProvider.newFactory;

import com.globalscalingsoftware.noise.internal.LoggerFactory.Logger;
import com.google.inject.AbstractModule;

public class InternalModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(NoiseDriverRunnerFactory.class).toProvider(
				newFactory(NoiseDriverRunnerFactory.class,
						NoiseDriverRunner.class));
		bind(NoiseDriverRunnerGPSFactory.class).toProvider(
				newFactory(NoiseDriverRunnerGPSFactory.class,
						NoiseDriverRunnerGPS.class));
		bind(LoggerFactory.class).toProvider(
				newFactory(LoggerFactory.class, Logger.class));
	}

}
