package com.payment.doku.core.api.service.inquiry;

import com.payment.doku.core.api.enums.InquiryStatus;
import com.payment.doku.core.api.enums.PaymentMethod;
import com.payment.doku.core.api.exception.InvalidRequestObjectException;
import com.payment.doku.core.api.repo.DokuInvoiceDao;
import com.payment.doku.core.api.repo.PaymentInquiryDao;
import com.payment.doku.core.api.service.log.LoggerService;
import com.payment.doku.core.api.util.request.inquiry.RequestGeneral;
import com.payment.doku.core.api.util.response.inquiry.error.UnexpectedErrorResponse;
import com.payment.doku.core.api.util.response.inquiry.success.ResponseGeneral;
import com.payment.doku.core.api.util.resultset.PaymentInquiry;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    protected LoggerService logger;

    @Autowired
    protected PaymentInquiryDao paymentInquiryDao;

    @Autowired
    protected DokuInvoiceDao dokuInvoiceDao;

    private BigDecimal logId = null;

    @Override
    public <T> T inquireCustomerForPaymentsAndSendInformation(String request, HttpServletResponse httpServletResponse) {
        String response = "";
        try {
            createLogRecord("PaymentInquiry", request);
            ResponseGeneral responseGeneral = new ResponseGeneralInitiator(getPaymentRecord(request)).initiate(httpServletResponse);
            response = responseGeneral.toString();
            return (T) responseGeneral;
        } catch (Exception e) {
            e.printStackTrace();
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            UnexpectedErrorResponse unexpectedErrorResponse = unexpectedError(e.getMessage());
            response = unexpectedErrorResponse.toString();
            return (T) unexpectedErrorResponse;
        } finally {
            updateLogRecord(response);
        }
    }

    private PaymentInquiry getPaymentRecord(String request) {
        RequestGeneral requestGeneral = getRequestObjectFromRequestString(request);
        PaymentInquiry paymentInquiry = getPaymentInquiry(paymentInquiryDao.getPaymentInformation(getVirtualAccountPaymentCode(requestGeneral)));
        paymentInquiry.setInvoiceNumber(getNextInvoiceNumber());
        paymentInquiry.setRequestGeneral(requestGeneral);
        logPaymentInquiry(paymentInquiry);
        return paymentInquiry;
    }

    private void logPaymentInquiry(PaymentInquiry paymentInquiry) {
        dokuInvoiceDao.logInvoice(getInvoiceDataSet(paymentInquiry));
    }

    private HashMap<String, String> getInvoiceDataSet(PaymentInquiry paymentInquiry) {
        HashMap<String, String> dokuInvoiceRecord = new HashMap<>();
        dokuInvoiceRecord.put("InvoiceNumber", paymentInquiry.getInvoiceNumber());
        dokuInvoiceRecord.put("Service", paymentInquiry.getRequestGeneral().getService().getId());
        dokuInvoiceRecord.put("Acquirer", paymentInquiry.getRequestGeneral().getAcquirer().getId());
        dokuInvoiceRecord.put("Channel", paymentInquiry.getRequestGeneral().getChannel().getId());
        dokuInvoiceRecord.put("Vapc", getVirtualAccountPaymentCode(paymentInquiry.getRequestGeneral()));
        dokuInvoiceRecord.put("PaymentRequest", paymentInquiry.getRequestGeneral().toString());
        dokuInvoiceRecord.put("InvoiceAmount", paymentInquiry.getAmount().toString());
        dokuInvoiceRecord.put("CustomerName", paymentInquiry.getCustomerName());
        dokuInvoiceRecord.put("CustomerEmail", paymentInquiry.getCustomerEmail());
        dokuInvoiceRecord.put("CustomerTel", paymentInquiry.getCustomerPhoneNumber());
        dokuInvoiceRecord.put("ContractNumber", paymentInquiry.getContractNumber());
        dokuInvoiceRecord.put("InquiryStatus", paymentInquiry.getInquiry().getStatus());
        return dokuInvoiceRecord;
    }

    private String getNextInvoiceNumber() {
        return "IN" + getTimeId() + getInvoiceId();
    }

    private String getInvoiceId() {
        return String.format("%08d", Integer.parseInt(paymentInquiryDao.getNextInvoiceId()));
    }

    private String getTimeId() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
    }

    private PaymentInquiry getPaymentInquiry(HashMap<String, String> paymentInquiryResult) {
        PaymentInquiry paymentInquiry = new PaymentInquiry();
        paymentInquiry.setAmount(getFormattedAmount(paymentInquiryResult.get("Amount")));
        paymentInquiry.setCustomerName(paymentInquiryResult.get("CustomerName"));
        paymentInquiry.setCustomerEmail(paymentInquiryResult.get("CustomerEmail"));
        paymentInquiry.setCustomerPhoneNumber(paymentInquiryResult.get("CustomerPhoneNumber"));
        paymentInquiry.setContractNumber(paymentInquiryResult.get("ContractNumber"));
        paymentInquiry.setInquiry(InquiryStatus.getInquiryStatusFromMessage(paymentInquiryResult.get("InquiryStatus")));
        return paymentInquiry;
    }

    private int getFormattedAmount(String amount) {
        if (amount == null)
            amount = "0";
        int decimalIndex = amount.indexOf('.');
        if (decimalIndex != -1) {
            return Integer.parseInt(amount.substring(0, decimalIndex));
        } else {
            return Integer.parseInt(amount);
        }
    }

    private String getVirtualAccountPaymentCode(RequestGeneral requestGeneral) {
        return PaymentMethod.VIRTUAL_ACCOUNT.getId().equals(requestGeneral.getService().getId()) ?
                requestGeneral.getVirtualAccountInfo().getVirtualAccountNumber() : requestGeneral.getOnlineToOfflineInfo().getPaymentCode();
    }


    private UnexpectedErrorResponse unexpectedError(String message) {
        UnexpectedErrorResponse response = new UnexpectedErrorResponse();
        response.setMessage(message);
        return response;
    }

    protected RequestGeneral getRequestObjectFromRequestString(String request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(request, RequestGeneral.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new InvalidRequestObjectException();
    }

    protected void createLogRecord(String method, String Value) {
        logId = logger.getNextLogId();
        logger.logRecord(logId, method, Value);
    }

    protected void updateLogRecord(String Value) {
        logger.logRecord(logId, null, Value);
    }

}
