package com.pts62.common.facade.verplaatsing

import java.io.Serializable

open class VehicleFacade(
        val id: Long,
        val licensePlate: String,
        val brand: String,
        val type: String,
        val hardwareSn: String,
        val category: String
) : Serializable