package com.payment.doku.core.api.repo;

import com.payment.doku.core.api.domain.DokuInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.HashMap;

@Repository
public class DokuInvoiceDaoImpl implements DokuInvoiceDao {

    @Autowired
    EntityManager em;

    @Override
    @Transactional
    public void logInvoice(HashMap<String, String> dokuInvoiceDataset) {
        em.persist(getDokuInvoiceFromDataset(dokuInvoiceDataset));
    }

    @Override
    @Transactional
    public void updateInvoice(HashMap<String, String> receiptDetails) {
        try {
            em.merge(getDokuInvoiceUpdated(receiptDetails));
        } catch (Exception e) {
        }
    }

    private DokuInvoice getDokuInvoiceUpdated(HashMap<String, String> receiptDetails) {
        DokuInvoice dokuInvoice = getInvoice(receiptDetails.get("InvoiceNumber"));
        dokuInvoice.setReceiptNumber(receiptDetails.get("ReceiptNumber"));
        dokuInvoice.setReceiptAmount(receiptDetails.get("ReceiptAmount"));
        dokuInvoice.setPaymentNotification(receiptDetails.get("PaymentNotification"));
        dokuInvoice.setTransactionTime(new Date());
        return dokuInvoice;
    }

    private DokuInvoice getInvoice(String invoiceNumber) {
        Query query = em.createNamedQuery("di.findRecord");
        query.setParameter(1, invoiceNumber);
        return (DokuInvoice) query.getSingleResult();
    }

    private DokuInvoice getDokuInvoiceFromDataset(HashMap<String, String> dokuInvoiceDataset) {
        DokuInvoice dokuInvoice = new DokuInvoice();
        dokuInvoice.setInvoiceNumber(dokuInvoiceDataset.get("InvoiceNumber"));
        dokuInvoice.setService(dokuInvoiceDataset.get("Service"));
        dokuInvoice.setAcquirer(dokuInvoiceDataset.get("Acquirer"));
        dokuInvoice.setChannel(dokuInvoiceDataset.get("Channel"));
        dokuInvoice.setVapc(dokuInvoiceDataset.get("Vapc"));
        dokuInvoice.setPaymentRequest(dokuInvoiceDataset.get("PaymentRequest"));
        dokuInvoice.setInvoiceAmount(Integer.parseInt(dokuInvoiceDataset.get("InvoiceAmount")));
        dokuInvoice.setCustomerName(dokuInvoiceDataset.get("CustomerName"));
        dokuInvoice.setCustomerEmail(dokuInvoiceDataset.get("CustomerEmail"));
        dokuInvoice.setCustomerTel(dokuInvoiceDataset.get("CustomerTel"));
        dokuInvoice.setContractNumber(dokuInvoiceDataset.get("ContractNumber"));
        dokuInvoice.setInquiryStatus(dokuInvoiceDataset.get("InquiryStatus"));
        return dokuInvoice;
    }
}
