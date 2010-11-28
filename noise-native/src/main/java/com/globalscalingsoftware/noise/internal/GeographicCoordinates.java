package com.globalscalingsoftware.noise.internal;

import com.globalscalingsoftware.noise.LatitudeHemisphere;
import com.globalscalingsoftware.noise.LongitudeHemisphere;

class GeographicCoordinates {

	private final GPSDataImpl gpsData;

	private final float latitudeDegrees;

	private final float latitudeMinutes;

	private final String latitudeHemisphere;

	private final float longitudeDegrees;

	private final float longitudeMinutes;

	private final String longitudeHemisphere;

	public GeographicCoordinates(GPSDataImpl gpsData, float latitudeDegrees,
			float latitudeMinutes, LatitudeHemisphere latitudeHemisphere,
			float longitudeDegrees, float longitudeMinutes,
			LongitudeHemisphere longitudeHemisphere) {
		this.gpsData = gpsData;
		this.latitudeDegrees = latitudeDegrees;
		this.latitudeMinutes = latitudeMinutes;
		this.latitudeHemisphere = latitudeHemisphere.getHemisphere();
		this.longitudeDegrees = longitudeDegrees;
		this.longitudeMinutes = longitudeMinutes;
		this.longitudeHemisphere = longitudeHemisphere.getHemisphere();
	}

	public GPSDataImpl getGpsData() {
		return gpsData;
	}

	public float getLatitudeDegrees() {
		return latitudeDegrees;
	}

	public float getLatitudeMinutes() {
		return latitudeMinutes;
	}

	public LatitudeHemisphere getLatitudeHemisphere() {
		return LatitudeHemisphere.parse(latitudeHemisphere);
	}

	public float getLongitudeDegrees() {
		return longitudeDegrees;
	}

	public float getLongitudeMinutes() {
		return longitudeMinutes;
	}

	public LongitudeHemisphere getLongitudeHemisphere() {
		return LongitudeHemisphere.parse(longitudeHemisphere);
	}

}
