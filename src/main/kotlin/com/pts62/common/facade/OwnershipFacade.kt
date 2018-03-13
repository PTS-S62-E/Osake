package com.pts62.common.facade

import java.io.Serializable
import java.sql.Timestamp

data class OwnershipFacade(
        val owner: OwnerFacade,
        val vehicle: VehicleFacade,
        val from: Timestamp,
        val to: Timestamp? = null
) : Serializable