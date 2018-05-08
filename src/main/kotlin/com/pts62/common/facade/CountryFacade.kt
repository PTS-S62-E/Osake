package com.pts62.common.facade

import java.io.Serializable

open class CountryFacade(
        val countryCode: String,
        val fullCountryName: String
) : Serializable