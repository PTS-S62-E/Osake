package com.pts62.common.facade

import java.io.Serializable

data class OwnerFacade(
        val name: String,
        val email: String? = null,
        val address: String,
        val city:String,
        val vehicles:List<VehicleFacade> = emptyList(),
        val password: String
) : Serializable