package com.pts62.common.europe.facades

import com.rekeningrijden.europe.interfaces.IJourney
import com.rekeningrijden.europe.interfaces.ISubInvoice
import com.rekeningrijden.europe.interfaces.IVehicle

data class IVehicleFacade(
        private val hashedLicensePlate: String,
        private val journeys: List<IJourney>,
        private val subInvoices: List<ISubInvoice>
) : IVehicle {
    override fun getJourneys() = this.journeys.toMutableList()
    override fun getSubInvoices() = this.subInvoices.toMutableList()
    override fun getHashedLicensePlate() = this.hashedLicensePlate
}