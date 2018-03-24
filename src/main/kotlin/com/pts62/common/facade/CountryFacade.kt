package com.pts62.common.facade

import java.io.Serializable

data class CountryFacade(
        val countryCode: String,
        val fullCountryName: String
) : Serializable