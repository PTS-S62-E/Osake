package com.pts62.common.europe

data class IJourneyFacade(
        private val translocations:List<ITransLocation>
) : IJourney {
    override fun getTransLocations() = this.translocations
}