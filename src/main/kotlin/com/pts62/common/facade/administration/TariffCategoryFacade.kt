package com.pts62.common.facade.administration

import java.io.Serializable

open class TariffCategoryFacade(
        val name: String,
        val tariff: Int,
        val description: String
) : Serializable