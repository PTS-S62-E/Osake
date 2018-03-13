package com.pts62.common.europe

class ITranslocationFacade(
        private val lat:Double,
        private val lon:Double,
        private val dateTime:String,
        private val serialNumber:String,
        private val countryCode:String
) : ITransLocation {
    override fun getLat() = this.lat
    override fun getLon() = this.lon
    override fun getDateTime() = this.dateTime
    override fun getSerialNumber() = this.serialNumber
    override fun getCountryCode() = this.countryCode
}