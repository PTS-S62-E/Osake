package com.pts62.common.europe.facades

import com.pts62.common.europe.ISubInvoice
import java.math.BigDecimal

data class ISubInvoiceFacade(
        override val InvoiceNumber: String,
        override val Country: String,
        override val PaymentStatus: Boolean,
        override val InvoiceDate: String,
        override val Price: BigDecimal
) : ISubInvoice