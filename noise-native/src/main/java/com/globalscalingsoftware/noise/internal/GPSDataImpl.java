package com.globalscalingsoftware.noise.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.globalscalingsoftware.noise.GPSData;
import com.globalscalingsoftware.noise.GPSQualityIndication;
import com.globalscalingsoftware.noise.LatitudeHemisphere;
import com.globalscalingsoftware.noise.LongitudeHemisphere;

public class GPSDataImpl implements GPSData {

	private final NoiseDataImpl noiseData;

	private final List<GeographicCoordinates> geographicCoordinates;

	private final Date utcTime;

	private final Integer gpsQualityIndication;

	private final Integer numberSatellites;

	private final Float horizontalDilutionOfPrecision;

	private final Float antennaHeight;

	public GPSDataImpl(NoiseDataImpl noiseData, Date utcTime,
			GPSQualityIndication gpsQualityIndication,
			Integer numberSatellites, Float horizontalDilutionOfPrecision,
			Float antennaHeight) {
		this.noiseData = noiseData;
		this.utcTime = utcTime;
		this.geographicCoordinates = new ArrayList<GeographicCoordinates>();
		this.numberSatellites = numberSatellites;
		this.gpsQualityIndication = gpsQualityIndication.getIndication();
		this.horizontalDilutionOfPrecision = horizontalDilutionOfPrecision;
		this.antennaHeight = antennaHeight;
	}

	public GeographicCoordinates getGeographicCoordinates() {
		return geographicCoordinates.isEmpty() ? null : geographicCoordinates
				.get(0);
	}

	public void setGeographicCoordinates(GeographicCoordinates coordinates) {
		if (coordinates == null) {
			geographicCoordinates.clear();
			return;
		}
		if (geographicCoordinates.size() == 0) {
			geographicCoordinates.add(coordinates);
		} else {
			geographicCoordinates.set(0, coordinates);
		}
	}

	public NoiseDataImpl getNoiseData() {
		return noiseData;
	}

	@Override
	public Date getUTCTime() {
		return utcTime;
	}

	@Override
	public Float getLatitudeDegrees() {
		return getGeographicCoordinates() == null ? null
				: getGeographicCoordinates().getLatitudeDegrees();
	}

	@Override
	public Float getLatitudeMinutes() {
		return getGeographicCoordinates() == null ? null
				: getGeographicCoordinates().getLatitudeMinutes();
	}

	@Override
	public LatitudeHemisphere getLatitudeHemisphere() {
		return getGeographicCoordinates() == null ? null
				: getGeographicCoordinates().getLatitudeHemisphere();
	}

	@Override
	public Float getLongitudeDegrees() {
		return getGeographicCoordinates() == null ? null
				: getGeographicCoordinates().getLongitudeDegrees();
	}

	@Override
	public Float getLongitudeMinutes() {
		return getGeographicCoordinates() == null ? null
				: getGeographicCoordinates().getLongitudeMinutes();
	}

	@Override
	public LongitudeHemisphere getLongitudeHemisphere() {
		return getGeographicCoordinates() == null ? null
				: getGeographicCoordinates().getLongitudeHemisphere();
	}

	@Override
	public GPSQualityIndication getGpsQualityIndication() {
		return gpsQualityIndication == null ? null : GPSQualityIndication
				.parseIndication(gpsQualityIndication);
	}

	@Override
	public Integer getNumberSatellites() {
		return numberSatellites;
	}

	@Override
	public Float getHorizontalDilutionOfPrecision() {
		return horizontalDilutionOfPrecision;
	}

	@Override
	public Float getAntennaHeight() {
		return antennaHeight;
	}

}
