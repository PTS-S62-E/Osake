package com.pts62.common.facade

import java.io.Serializable

data class VehicleFacade (
        val plate: String,
        val brand: String,
        val type: String,
        val hardwareSn: String,
        val owners: List<OwnershipFacade> = emptyList(),
        val category: String
) : Serializable