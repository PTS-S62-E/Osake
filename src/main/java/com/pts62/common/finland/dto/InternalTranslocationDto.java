package com.pts62.common.finland.dto;

public class InternalTranslocationDto extends TranslocationDto {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public InternalTranslocationDto(){
		super();
	}

	public InternalTranslocationDto(String serialNumber, double latitude, double longitude, String timestamp, String countryCode, long id){
		super(serialNumber,latitude, longitude, timestamp, countryCode);
		this.id = id;
	}
}
