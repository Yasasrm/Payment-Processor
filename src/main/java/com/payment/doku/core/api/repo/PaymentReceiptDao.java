package com.payment.doku.core.api.repo;

import java.util.HashMap;

public interface PaymentReceiptDao {
    HashMap<String, String> generateReceiptAndGetDetails(String channel, String vapc, String invoiceNo, Integer amount);
}
