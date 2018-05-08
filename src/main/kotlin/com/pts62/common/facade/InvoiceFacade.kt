package com.pts62.common.facade

import java.io.Serializable

open class InvoiceFacade(
        val date: String,
        val amount:Int,
        val owner: OwnerFacade,
        val vehicle: VehicleFacade
) : Serializable