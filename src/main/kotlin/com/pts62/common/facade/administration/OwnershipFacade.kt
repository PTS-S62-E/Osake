package com.pts62.common.facade.administration

import java.io.Serializable
import java.sql.Timestamp

open class OwnershipFacade(
        val owner: OwnerFacade,
        val readbleFromDate: String,
        val readableToDate: String?,
        val vehicleId: Int
) : Serializable