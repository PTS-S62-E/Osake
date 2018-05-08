package com.pts62.common.facade

import java.io.Serializable

open class CategoryFacade(
        val level: Int,
        val rate: Int
) : Serializable