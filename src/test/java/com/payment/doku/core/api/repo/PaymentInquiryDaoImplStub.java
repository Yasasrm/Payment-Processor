package com.payment.doku.core.api.repo;

import com.payment.doku.core.api.enums.FieldFlag;
import com.payment.doku.core.api.enums.InquiryStatus;

import java.util.HashMap;

public class PaymentInquiryDaoImplStub extends PaymentInquiryDaoImpl {

    @Override
    public String getNextInvoiceId() {
        return "0";
    }

    @Override
    public HashMap<String, String> getPaymentInformation(String virtualAccountPaymentCode) {
        if (virtualAccountPaymentCode.equals("0000"))
            return getPaymentInquiry(InquiryStatus.BILLING_NOT_FOUND);

        if (virtualAccountPaymentCode.equals("0001"))
            return getPaymentInquiry(InquiryStatus.BILLING_ALREADY_PAID);

        if (virtualAccountPaymentCode.equals("0002"))
            return getPaymentInquiry(InquiryStatus.BILLING_WAS_EXPIRED);

        if (virtualAccountPaymentCode.equals("0003"))
            return getPaymentInquiry(InquiryStatus.INVALID_SIGNATURE);

        if (virtualAccountPaymentCode.equals("0004"))
            return getPaymentInquiryWithUnassignedMandatoryString(InquiryStatus.SUCCESS);

        if (virtualAccountPaymentCode.equals("0005"))
            return getPaymentInquiryWithUnassignedMandatoryInteger(InquiryStatus.SUCCESS);

        if (virtualAccountPaymentCode.equals("0006"))
            throw new RuntimeException("Unexpected Error!");

        return getPaymentInquiry(InquiryStatus.SUCCESS);
    }

    public HashMap<String, String> getInvoiceInformation(String invoiceNumber) {
        HashMap resultSet = new HashMap();
        if (invoiceNumber.equals("002")) {
            resultSet.put("InvoiceNumber", null);
            resultSet.put("Service", "VIRTUAL_ACCOUNT");
            resultSet.put("Acquirer", "001");
            resultSet.put("Channel", "001");
            resultSet.put("Vapc", "001");
            resultSet.put("InvoiceAmount", "20000");
            resultSet.put("ContractNumber", "001");
            resultSet.put("ReceiptNumber", null);
        } else if (invoiceNumber.equals("003")) {
            resultSet.put("InvoiceNumber", "003");
            resultSet.put("Service", "003");
            resultSet.put("Acquirer", "001");
            resultSet.put("Channel", "001");
            resultSet.put("Vapc", "001");
            resultSet.put("InvoiceAmount", "20000");
            resultSet.put("ContractNumber", "001");
            resultSet.put("ReceiptNumber", null);
        } else if (invoiceNumber.equals("004")) {
            resultSet.put("InvoiceNumber", "004");
            resultSet.put("Service", "VIRTUAL_ACCOUNT");
            resultSet.put("Acquirer", "004");
            resultSet.put("Channel", "001");
            resultSet.put("Vapc", "001");
            resultSet.put("InvoiceAmount", "20000");
            resultSet.put("ContractNumber", "001");
            resultSet.put("ReceiptNumber", null);
        } else if (invoiceNumber.equals("005")) {
            resultSet.put("InvoiceNumber", "005");
            resultSet.put("Service", "VIRTUAL_ACCOUNT");
            resultSet.put("Acquirer", "001");
            resultSet.put("Channel", "005");
            resultSet.put("Vapc", "001");
            resultSet.put("InvoiceAmount", "20000");
            resultSet.put("ContractNumber", "001");
            resultSet.put("ReceiptNumber", null);
        } else if (invoiceNumber.equals("006")) {
            resultSet.put("InvoiceNumber", "006");
            resultSet.put("Service", "VIRTUAL_ACCOUNT");
            resultSet.put("Acquirer", "001");
            resultSet.put("Channel", "001");
            resultSet.put("Vapc", "006");
            resultSet.put("InvoiceAmount", "20000");
            resultSet.put("ContractNumber", "001");
            resultSet.put("ReceiptNumber", null);
        } else if (invoiceNumber.equals("007")) {
            resultSet.put("InvoiceNumber", "007");
            resultSet.put("Service", "VIRTUAL_ACCOUNT");
            resultSet.put("Acquirer", "001");
            resultSet.put("Channel", "001");
            resultSet.put("Vapc", "001");
            resultSet.put("InvoiceAmount", "20000");
            resultSet.put("ContractNumber", "001");
            resultSet.put("ReceiptNumber", "007");
        } else {
            resultSet.put("InvoiceNumber", "008");
            resultSet.put("Service", "VIRTUAL_ACCOUNT");
            resultSet.put("Acquirer", "001");
            resultSet.put("Channel", "001");
            resultSet.put("Vapc", "001");
            resultSet.put("InvoiceAmount", "20000");
            resultSet.put("ContractNumber", "001");
            resultSet.put("ReceiptNumber", null);
        }
        return resultSet;
    }

    private HashMap<String, String> getPaymentInquiry(InquiryStatus statusMsg) {
        HashMap<String, String> paymentInquiryResult = new HashMap<>();
        paymentInquiryResult.put("InvoiceNumber", "INV2578962");
        paymentInquiryResult.put("Amount", "15500");
        paymentInquiryResult.put("CustomerName", "A D Tom");
        paymentInquiryResult.put("CustomerEmail", "tom@email.com");
        paymentInquiryResult.put("ContractNumber", "SW-15-021FGH");
        paymentInquiryResult.put("InquiryStatus", statusMsg.getStatus());
        return paymentInquiryResult;
    }

    private HashMap<String, String> getPaymentInquiryWithUnassignedMandatoryString(InquiryStatus statusMsg) {
        HashMap<String, String> paymentInquiryResult = new HashMap<>();
        paymentInquiryResult.put("InvoiceNumber", "INV2578962");
        paymentInquiryResult.put("Amount", "15500");
        paymentInquiryResult.put("CustomerName", FieldFlag.MANDATORY.getStringValue());
        paymentInquiryResult.put("CustomerEmail", "tom@email.com");
        paymentInquiryResult.put("ContractNumber", "SW-15-021FGH");
        paymentInquiryResult.put("InquiryStatus", statusMsg.getStatus());
        return paymentInquiryResult;
    }

    private HashMap<String, String> getPaymentInquiryWithUnassignedMandatoryInteger(InquiryStatus statusMsg) {
        HashMap<String, String> paymentInquiryResult = new HashMap<>();
        paymentInquiryResult.put("InvoiceNumber", "INV2578962");
        paymentInquiryResult.put("Amount", "" + FieldFlag.MANDATORY.getIntValue());
        paymentInquiryResult.put("CustomerName", "A D Tom");
        paymentInquiryResult.put("CustomerEmail", "tom@email.com");
        paymentInquiryResult.put("ContractNumber", "SW-15-021FGH");
        paymentInquiryResult.put("InquiryStatus", statusMsg.getStatus());
        return paymentInquiryResult;
    }
}