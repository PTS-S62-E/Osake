package com.pts62.common.facade

import java.io.Serializable

data class CategoryFacade(
        val level: Int,
        val rate: Int
) : Serializable