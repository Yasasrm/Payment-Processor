package com.payment.doku.core.api.repo;

import java.util.HashMap;

public interface PaymentInquiryDao {

    String getNextInvoiceId();

    HashMap<String, String> getPaymentInformation(String virtualAccountPaymentCode);

    HashMap<String, String> getInvoiceInformation(String invoiceNumber);
}
