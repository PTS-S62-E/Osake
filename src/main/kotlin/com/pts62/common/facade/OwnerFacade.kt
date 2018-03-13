package com.pts62.common.facade

data class OwnerFacade(
        val name: String,
        val email: String? = null,
        val address: String,
        val city:String,
        val autos: List<AutoFacade>,
        val wachtwoord: String
)