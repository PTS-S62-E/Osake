package com.pts62.common.europe.facades

import com.pts62.common.europe.ITransLocation

class ITranslocationFacade(
        override val lat: Double,
        override val lon: Double,
        override val Time: String,
        override val Serialnumber: String,
        override val Countrycode: String
) : ITransLocation