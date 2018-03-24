package com.pts62.common.europe.facades

import com.pts62.common.europe.IJourney
import com.pts62.common.europe.ITransLocation

data class IJourneyFacade(
        override val TransLocations: List<ITransLocation>
) : IJourney