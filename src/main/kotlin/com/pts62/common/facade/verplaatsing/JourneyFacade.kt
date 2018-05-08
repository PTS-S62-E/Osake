package com.pts62.common.facade.verplaatsing

import java.io.Serializable

open class JourneyFacade(
        val translocationFacades: List<TranslocationFacade> = emptyList()
) : Serializable