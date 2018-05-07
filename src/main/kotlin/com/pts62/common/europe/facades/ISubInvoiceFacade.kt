package com.pts62.common.europe.facades

import com.rekeningrijden.europe.interfaces.ISubInvoice
import java.time.LocalDateTime

open class ISubInvoiceFacade(
        private val invoiceNumber: String,
        private val country: String,
        private val paymentStatus: Boolean,
        private val invoiceDate: LocalDateTime,
        private val price: Int
) : ISubInvoice {
    override fun getInvoiceNumber() = this.invoiceNumber
    override fun getPrice() = (this.price.toDouble() / 100.0)
    override fun getPaymentStatus() = this.paymentStatus
    override fun getCountry() = this.country
    override fun getInvoiceDate() = this.invoiceDate.toString()
    fun getInvoiceAsLocalDateTime() = this.invoiceDate
}