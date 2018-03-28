package com.pts62.common.europe.facades

import com.rekeningrijden.europe.interfaces.IJourney
import com.rekeningrijden.europe.interfaces.ITransLocation

open class IJourneyFacade(
        private val translocations: List<ITransLocation>
) : IJourney {
    override fun getTransLocations() = this.translocations
}