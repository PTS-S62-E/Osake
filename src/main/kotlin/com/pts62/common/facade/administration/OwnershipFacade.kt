package com.pts62.common.facade.administration

import java.io.Serializable
import java.sql.Timestamp

open class OwnershipFacade(
        val id: Int,
        val readableFromDate: String,
        val readableToDate: String?,
        val vehicleId: Int
) : Serializable