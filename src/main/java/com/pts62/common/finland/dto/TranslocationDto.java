package com.pts62.common.finland.dto;

public class TranslocationDto {

	private String serialNumber;
	private double latitude;
	private double longitude;
	private String timestamp;
	private String countryCode;

	public TranslocationDto(){}

	public TranslocationDto(String serialNumber, double latitude, double longitude, String timestamp, String countryCode) {
		this.serialNumber = serialNumber;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
		this.countryCode = countryCode;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
