package com.pts62.common.europe

data class IVehicleFacade(
        private val hashedLicensePlate:String,
        private val journey:List<IJourney>,
        private val subInvoices:List<ISubInvoice>
) : IVehicle {
    override fun getHashedLicensePlate() = this.hashedLicensePlate
    override fun getJourneys() = this.journey
    override fun getSubInvoices() = this.subInvoices
}