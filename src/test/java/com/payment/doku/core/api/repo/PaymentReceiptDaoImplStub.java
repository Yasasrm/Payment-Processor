package com.payment.doku.core.api.repo;

import java.util.HashMap;

public class PaymentReceiptDaoImplStub extends PaymentReceiptDaoImpl {
    @Override
    public HashMap<String, String> generateReceiptAndGetDetails(String channel, String vapc, String invoiceNo, Integer amount) {
        HashMap<String, String> receiptDetail = new HashMap<>();
        receiptDetail.put("ReceiptNumber", "B 649656");
        receiptDetail.put("ReceiptAmount", "15000");
        return receiptDetail;
    }
}