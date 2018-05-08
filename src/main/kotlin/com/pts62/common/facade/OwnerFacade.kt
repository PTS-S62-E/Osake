package com.pts62.common.facade

import java.io.Serializable

open class OwnerFacade(
        val name: String,
        val email: String? = null,
        val address: String,
        val city: String,
        val password: String
) : Serializable