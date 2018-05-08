package com.pts62.common.facade.administration

import java.io.Serializable

open class InvoiceFacade(
        val id: Long,
        val invoiceDetails: List<InvoiceDetailsFacade>,
        val paymentDetails: String,
        val countryCode: String,
        val price: Int,
        val totalDistance: Long,
        val owner: OwnerFacade,
        val vehicleId: Long
) : Serializable