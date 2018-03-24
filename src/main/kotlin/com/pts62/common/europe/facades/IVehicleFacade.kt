package com.pts62.common.europe.facades

import com.pts62.common.europe.IJourney
import com.pts62.common.europe.ISubInvoice
import com.pts62.common.europe.IVehicle

data class IVehicleFacade(
        override val LicensePlate: String,
        override val Journeys: List<IJourney>,
        override val SubInvoices: List<ISubInvoice>
) : IVehicle