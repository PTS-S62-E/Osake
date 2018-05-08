package com.pts62.common.facade.administration

open class InvoiceDetailsFacade(
        val id: Long,
        val locationPointsIds: List<Long> = emptyList(),
        val description: String,
        val price: Int,
        val distance: Long
)