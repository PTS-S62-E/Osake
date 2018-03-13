package com.pts62.common.facade

data class InvoiceFacade(
        val date: String,
        val bedrag:Int,
        val eigenaar: OwnerFacade,
        val auto: AutoFacade
)