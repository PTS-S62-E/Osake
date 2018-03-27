package com.pts62.common.facade

import java.io.Serializable

data class LocationFacade(
        val vehicle: VehicleFacade,
        val latitude: Double,
        val longtitude: Double,
        val timestamp: String,
        val previousTimestamp: String
) : Serializable