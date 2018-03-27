package com.pts62.common.europe

interface ISubInvoice {
    val InvoiceNumber: String
    val Country: String
    val PaymentStatus: Boolean
    val InvoiceDate: String
    val Price: Int
}