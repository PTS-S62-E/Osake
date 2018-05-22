package com.pts62.common.finland.dto;

import com.pts62.common.facade.administration.OwnershipFacade;
import com.pts62.common.facade.administration.TariffCategoryFacade;

import java.io.Serializable;
import java.util.List;

public class OwnershipWithVehicleDto implements Serializable{

	private List<OwnershipFacade> ownership;
	private VehicleDto vehicleDto;

	public TariffCategoryFacade getTariffCategory() {
		return tariffCategory;
	}

	public void setTariffCategory(TariffCategoryFacade tariffCategory) {
		this.tariffCategory = tariffCategory;
	}

	private TariffCategoryFacade tariffCategory;

	public List<OwnershipFacade> getOwnership() {
		return ownership;
	}

	public void setOwnership(List<OwnershipFacade> ownership) {
		this.ownership = ownership;
	}

	public VehicleDto getVehicleDto() {
		return vehicleDto;
	}

	public void setVehicleDto(VehicleDto vehicleDto) {
		this.vehicleDto = vehicleDto;
	}

	public OwnershipWithVehicleDto(){}

	public OwnershipWithVehicleDto(List<OwnershipFacade> ownership, VehicleDto vehicleDto, TariffCategoryFacade tariffCategory) {
		this.tariffCategory = tariffCategory;
		this.ownership = ownership;
		this.vehicleDto = vehicleDto;
	}
}
