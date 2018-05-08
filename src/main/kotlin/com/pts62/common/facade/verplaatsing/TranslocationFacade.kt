package com.pts62.common.facade.verplaatsing

import java.io.Serializable

open class TranslocationFacade(
        val id: Long,
        val latitude: Double,
        val longitude: Double,
        val timestamp: String,
        val countryCode: String,
        val flagged: Boolean,
        val vehicle: VehicleFacade
) : Serializable