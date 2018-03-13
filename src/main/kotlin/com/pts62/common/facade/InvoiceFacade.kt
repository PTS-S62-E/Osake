package com.pts62.common.facade

import java.io.Serializable

data class InvoiceFacade(
        val date: String,
        val amount:Int,
        val owner: OwnerFacade,
        val vehicle: VehicleFacade
) : Serializable