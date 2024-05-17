package com.payment.doku.core.api.repo;

import java.util.HashMap;

public interface DokuInvoiceDao {
    void logInvoice(HashMap<String, String> dokuInvoice);

    void updateInvoice(HashMap<String, String> receiptDetails);
}
