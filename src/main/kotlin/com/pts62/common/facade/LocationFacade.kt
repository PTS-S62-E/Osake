package com.pts62.common.facade

import com.pts62.common.facade.verplaatsing.VehicleFacade
import java.io.Serializable

open class LocationFacade(
        val vehicle: VehicleFacade,
        val latitude: Double,
        val longtitude: Double,
        val timestamp: String,
        val previousTimestamp: String
) : Serializable