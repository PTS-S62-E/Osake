package com.pts62.common.facade

data class LocationFacade(
        val auto: AutoFacade,
        val latitude: Double,
        val longtitude: Double,
        val timestamp: String
)