package com.pts62.common.europe

interface IVehicle {
    val LicensePlate: String
    val Journeys: List<IJourney>
    val SubInvoices: List<ISubInvoice>
}
