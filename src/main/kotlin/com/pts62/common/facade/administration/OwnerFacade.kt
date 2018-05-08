package com.pts62.common.facade.administration

import java.io.Serializable

open class OwnerFacade(
        val name: String,
        val email: String? = null,
        val address: String,
        val postalCode: String
) : Serializable