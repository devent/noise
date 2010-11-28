package com.globalscalingsoftware.noise.internal;

import static java.lang.String.format;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.globalscalingsoftware.noise.GPSQualityIndication;
import com.globalscalingsoftware.noise.LatitudeHemisphere;
import com.globalscalingsoftware.noise.LongitudeHemisphere;

class NoiseDriverRunnerGPS extends NoiseDriverRunner {

	@Override
	protected GPSDataImpl parseGPSData(String line, NoiseDataImpl noiseData) {
		String[] groups = splitGroups(line);
		throwIfTheGroupsDoesNotContainGPS(groups);
		GPSDataImpl gpsdata = parseGPSData(noiseData, groups);
		return gpsdata;
	}

	private String[] splitGroups(String line) {
		return line.split("\t");
	}

	private void throwIfTheGroupsDoesNotContainGPS(String[] groups) {
		if (!groups[9].equals("GPS:")) {
			throw new IllegalArgumentException(format(
					"The parsed line '%s' does not contain GPS data.",
					Arrays.toString(groups)));
		}
	}

	private GPSDataImpl parseGPSData(NoiseDataImpl noiseData, String[] groups) {
		GPSDataImpl gpsdata = null;
		if (groups.length > 11) {
			Date utctime = parsUTCTime(groups[10]);
			GPSQualityIndication gpsquality = parseGPSQualityIndication(groups);
			gpsdata = parseGPSData(noiseData, groups, utctime, gpsquality);
			GeographicCoordinates coords = parseCoords(groups, gpsdata);
			gpsdata.setGeographicCoordinates(coords);
		}
		return gpsdata;
	}

	private Date parsUTCTime(String string) {
		string = string.trim();
		Calendar cal = Calendar.getInstance();
		if (string.isEmpty()) {
			return cal.getTime();
		} else {
			return parseUTCTime(string, cal);
		}
	}

	private Date parseUTCTime(String string, Calendar cal) {
		String h = string.substring(0, 2);
		String m = string.substring(2, 4);
		String s = string.substring(4, 6);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hourOfDay = Integer.parseInt(h);
		int minute = Integer.parseInt(m);
		int second = Integer.parseInt(s);
		cal.set(year, month, day, hourOfDay, minute, second);
		Date time = cal.getTime();
		return time;
	}

	private GPSQualityIndication parseGPSQualityIndication(String[] groups) {
		String string = groups[15].trim();
		if (string.isEmpty()) {
			return null;
		} else {
			return parseGpsQualityIndication(string);
		}
	}

	private GPSQualityIndication parseGpsQualityIndication(String string) {
		int i = Integer.parseInt(string);
		return GPSQualityIndication.parseIndication(i);
	}

	private GPSDataImpl parseGPSData(NoiseDataImpl noiseData, String[] groups,
			Date utctime, GPSQualityIndication gpsquality) {
		Float dilution = parseHorizontalDilutionOfPrecision(groups);
		Float height = parseAntennaHeight(groups);
		GPSDataImpl gpsdata = new GPSDataImpl(noiseData, utctime, gpsquality,
				0, dilution, height);
		return gpsdata;
	}

	private Float parseHorizontalDilutionOfPrecision(String[] groups) {
		String string = groups[17];
		if (string.isEmpty()) {
			return null;
		} else {
			return Float.parseFloat(string);
		}
	}

	private Float parseAntennaHeight(String[] groups) {
		String string = groups[18];
		if (string.isEmpty()) {
			return null;
		} else {
			return Float.parseFloat(string);
		}
	}

	private GeographicCoordinates parseCoords(String[] groups,
			GPSDataImpl gpsdata) {
		GeographicCoordinates coords = parseCoords(groups[11], groups[12],
				groups[13], groups[14], gpsdata);
		return coords;
	}

	private GeographicCoordinates parseCoords(String lat1, String lat2,
			String long1, String long2, GPSDataImpl gpsData) {
		String d = lat1.substring(0, 2);
		String m = lat1.substring(2, lat1.length());
		LatitudeHemisphere lath = LatitudeHemisphere.parse(lat2);
		float latdegrees = Float.parseFloat(d);
		float latminutes = Float.parseFloat(m);

		d = long1.substring(0, 3);
		m = long1.substring(3, long1.length());
		LongitudeHemisphere longh = LongitudeHemisphere.parse(long2);
		float longdegrees = Float.parseFloat(d);
		float longminutes = Float.parseFloat(m);

		return new GeographicCoordinates(gpsData, latdegrees, latminutes, lath,
				longdegrees, longminutes, longh);
	}

}
