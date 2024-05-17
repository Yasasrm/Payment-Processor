package com.payment.doku.core.api.service.notification;

import com.payment.doku.core.api.enums.NotificationStatus;
import com.payment.doku.core.api.enums.PaymentMethod;
import com.payment.doku.core.api.exception.InvalidRequestObjectException;
import com.payment.doku.core.api.exception.PaymentException;
import com.payment.doku.core.api.repo.DokuInvoiceDao;
import com.payment.doku.core.api.repo.PaymentInquiryDao;
import com.payment.doku.core.api.repo.PaymentReceiptDao;
import com.payment.doku.core.api.service.log.LoggerService;
import com.payment.doku.core.api.util.request.notification.NotificationGeneral;
import com.payment.doku.core.api.util.response.notification.NotificationResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    protected LoggerService logger;

    @Autowired
    protected PaymentInquiryDao paymentInquiryDao;

    @Autowired
    protected PaymentReceiptDao paymentReceiptDao;

    @Autowired
    protected DokuInvoiceDao dokuInvoiceDao;

    private BigDecimal logId = null;

    @Override
    public <T> T getPaymentInformationAndCreateReceipt(String request, HttpServletResponse httpServletResponse) {
        String response = "";
        NotificationGeneral notificationGeneral = null;
        NotificationResponse notificationResponse = null;
        try {
            createLogRecord("PaymentInquiry", request);
            notificationGeneral = getNotificationObjectFromRequestString(request);
            HashMap<String, String> resultSet = paymentInquiryDao.getInvoiceInformation(notificationGeneral.getOrder().getInvoiceNumber());
            validateNotification(notificationGeneral, resultSet);
            notificationResponse = generateReceiptAndGetDetails(notificationGeneral);
            response = notificationResponse.toString();
            return (T) notificationResponse;
        } catch (PaymentException pe) {
            notificationResponse = getErrorNotificationResponse(httpServletResponse, NotificationStatus.getInquiryStatusFromMessage(pe.getMessage()), pe.getMessage());
            response = notificationResponse.toString();
            return (T) notificationResponse;
        } catch (Exception e) {
            e.printStackTrace();
            notificationResponse = getErrorNotificationResponse(httpServletResponse, NotificationStatus.DECLINE, e.getMessage());
            response = notificationResponse.toString();
            return (T) notificationResponse;
        } finally {
            updateReceiptDetailsInDokuInvoice(notificationGeneral, notificationResponse);
            updateLogRecord(response);
        }
    }

    private void updateReceiptDetailsInDokuInvoice(NotificationGeneral notificationGeneral, NotificationResponse notificationResponse) {
        HashMap<String, String> receiptDetails = new HashMap<>();
        receiptDetails.put("InvoiceNumber", notificationGeneral.getOrder().getInvoiceNumber());
        receiptDetails.put("ReceiptNumber", notificationResponse.getReceiptNumber());
        receiptDetails.put("ReceiptAmount", notificationResponse.getReceiptAmount());
        receiptDetails.put("PaymentNotification", notificationGeneral.toString());
        dokuInvoiceDao.updateInvoice(receiptDetails);
    }

    private NotificationResponse generateReceiptAndGetDetails(NotificationGeneral notificationGeneral) {
        return getErrorNotificationResponse(paymentReceiptDao.generateReceiptAndGetDetails(notificationGeneral.getAcquirer().getId(),
                getVirtualAccountPaymentCode(notificationGeneral), notificationGeneral.getOrder().getInvoiceNumber(),
                notificationGeneral.getOrder().getAmount()));
    }

    private NotificationResponse getErrorNotificationResponse(HashMap<String, String> receiptDetails) {
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setStatus(NotificationStatus.SUCCESS.getStatus());
        notificationResponse.setMessage("Receipt No: " + receiptDetails.get("ReceiptNumber"));
        notificationResponse.setReceiptNumber(receiptDetails.get("ReceiptNumber"));
        notificationResponse.setReceiptAmount(receiptDetails.get("ReceiptAmount"));
        return notificationResponse;
    }

    private void validateNotification(NotificationGeneral notificationGeneral, HashMap<String, String> resultSet) {
        if (!notificationGeneral.getTransaction().getStatus().equalsIgnoreCase(NotificationStatus.SUCCESS.getStatus()))
            throw new PaymentException(NotificationStatus.ACCEPTED);

        if (resultSet.get("InvoiceNumber") == null)
            throw new PaymentException(NotificationStatus.BILLING_NOT_FOUND);

        if (!resultSet.get("InvoiceNumber").equals(notificationGeneral.getOrder().getInvoiceNumber()))
            throw new PaymentException(NotificationStatus.INVALID_SIGNATURE);

        if (!resultSet.get("Service").equals(notificationGeneral.getService().getId()))
            throw new PaymentException(NotificationStatus.INVALID_SIGNATURE);

        if (!resultSet.get("Acquirer").equals(notificationGeneral.getAcquirer().getId()))
            throw new PaymentException(NotificationStatus.INVALID_SIGNATURE);

        if (!resultSet.get("Channel").equals(notificationGeneral.getChannel().getId()))
            throw new PaymentException(NotificationStatus.INVALID_SIGNATURE);

        if (!resultSet.get("Vapc").equals(getVirtualAccountPaymentCode(notificationGeneral)))
            throw new PaymentException(NotificationStatus.INVALID_SIGNATURE);

        if (resultSet.get("ReceiptNumber") != null)
            throw new PaymentException(NotificationStatus.BILLING_ALREADY_PAID);
    }

    private NotificationResponse getErrorNotificationResponse(HttpServletResponse httpServletResponse, NotificationStatus notificationStatus, String message) {
        NotificationResponse notificationResponse;
        notificationResponse = new NotificationResponse();
        notificationResponse.setStatus(NotificationStatus.DECLINE.getStatus());
        notificationResponse.setMessage(message);
        httpServletResponse.setStatus(notificationStatus.getCode());
        return notificationResponse;
    }

    private String getVirtualAccountPaymentCode(NotificationGeneral notificationGeneral) {
        return PaymentMethod.VIRTUAL_ACCOUNT.getId().equals(notificationGeneral.getService().getId()) ?
                notificationGeneral.getVirtualAccountInfo().getVirtualAccountNumber() : notificationGeneral.getOnlineToOfflineInfo().getPaymentCode();
    }

    private void updateLogRecord(String Value) {
        logger.logRecord(logId, null, Value);
    }

    private void createLogRecord(String method, String Value) {
        logId = logger.getNextLogId();
        logger.logRecord(logId, method, Value);
    }

    protected NotificationGeneral getNotificationObjectFromRequestString(String request) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(request, NotificationGeneral.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new InvalidRequestObjectException();
    }
}
