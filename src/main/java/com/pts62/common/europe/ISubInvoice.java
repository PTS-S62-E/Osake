package com.pts62.common.europe;

public interface ISubInvoice {
    String getInvoiceNumber();

    String getCountry();

    boolean getPaymentStatus();

    String getInvoiceDate();

    double getPrice();
}