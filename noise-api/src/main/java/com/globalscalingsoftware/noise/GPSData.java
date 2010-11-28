package com.globalscalingsoftware.noise;

import java.util.Date;

public interface GPSData {

	Date getUTCTime();

	Float getLatitudeDegrees();

	Float getLatitudeMinutes();

	LatitudeHemisphere getLatitudeHemisphere();

	Float getLongitudeDegrees();

	Float getLongitudeMinutes();

	LongitudeHemisphere getLongitudeHemisphere();

	GPSQualityIndication getGpsQualityIndication();

	Integer getNumberSatellites();

	Float getHorizontalDilutionOfPrecision();

	Float getAntennaHeight();
}
