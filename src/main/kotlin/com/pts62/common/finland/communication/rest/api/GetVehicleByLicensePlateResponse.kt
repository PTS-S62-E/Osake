package com.pts62.common.finland.communication.rest.api

import com.pts62.common.facade.administration.OwnerFacade
import com.pts62.common.facade.administration.OwnershipFacade
import com.pts62.common.facade.administration.TariffCategoryFacade
import com.pts62.common.facade.verplaatsing.VehicleFacade
import java.io.Serializable

data class GetVehicleByLicensePlateResponse(
        val ownership: List<OwnershipFacade>,
        val vehicleDto: VehicleFacade,
        val tariffCategory: TariffCategoryFacade
) : Serializable