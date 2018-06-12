package com.pts62.common.facade.verplaatsing

import java.io.Serializable

open class TranslocationFacade(
        val id: Long? = null,
        val latitude: Double? = null,
        val longitude: Double? = null,
        val timestamp: String? = null,
        val countryCode: String? = null,
        val flagged: Boolean? = null,
        val vehicle: VehicleFacade? = null
) : Serializable