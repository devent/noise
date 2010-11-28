package com.globalscalingsoftware.noise;

import com.google.inject.assistedinject.Assisted;

public interface NoiseDriverService {

	NoiseDriver create(@Assisted String device);
}
