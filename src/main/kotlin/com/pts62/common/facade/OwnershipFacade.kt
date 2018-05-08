package com.pts62.common.facade

import java.io.Serializable
import java.sql.Timestamp

open class OwnershipFacade(
        val owner: OwnerFacade,
        val from: Timestamp,
        val to: Timestamp? = null
) : Serializable