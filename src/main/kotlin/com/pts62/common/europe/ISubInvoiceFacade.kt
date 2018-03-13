package com.pts62.common.europe

data class ISubInvoiceFacade(
        private val invoiceNumber: String,
        private val country: String,
        private val paymentStatus: Boolean,
        private val invoiceDate: String,
        private val price: Double
) : ISubInvoice {
    override fun getInvoiceNumber() = this.invoiceNumber
    override fun getCountry() = this.country
    override fun getPaymentStatus() = this.paymentStatus
    override fun getInvoiceDate() = this.invoiceDate
    override fun getPrice() = this.price
}