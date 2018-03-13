package com.pts62.common.europe;

import java.util.List;

public interface IVehicle {
    String getHashedLicensePlate();

    List<IJourney> getJourneys();

    List<ISubInvoice> getSubInvoices();
}
