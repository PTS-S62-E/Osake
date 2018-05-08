package com.pts62.common.facade.administration

import java.io.Serializable

open class TariffCategoryFacade(
        val level: Int,
        val rate: Int
) : Serializable