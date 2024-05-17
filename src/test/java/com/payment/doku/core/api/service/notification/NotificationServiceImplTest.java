package com.payment.doku.core.api.service.notification;

import com.payment.doku.core.api.repo.DokuInvoiceDaoImplStub;
import com.payment.doku.core.api.repo.LoggerServiceImplStub;
import com.payment.doku.core.api.repo.PaymentInquiryDaoImplStub;
import com.payment.doku.core.api.repo.PaymentReceiptDaoImplStub;
import com.payment.doku.core.api.util.response.notification.NotificationResponse;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class NotificationServiceImplTest extends NotificationServiceImpl {

    private HttpServletResponse httpServletResponse;

    @Before
    public void setup() {
        logger = new LoggerServiceImplStub();
        paymentInquiryDao = new PaymentInquiryDaoImplStub();
        paymentReceiptDao = new PaymentReceiptDaoImplStub();
        dokuInvoiceDao = new DokuInvoiceDaoImplStub();
        httpServletResponse = getDummyHttpServletResponse();
    }

    @Test
    public void givenUnsuccessfulPaymentNotificationShouldReturnAccepted() {
        NotificationResponse notificationResponse = getPaymentInformationAndCreateReceipt(getRequest("001"), httpServletResponse);
        assertEquals("error_response_accepted", notificationResponse.getMessage());
    }

    @Test
    public void givenInvalidInvoiceNumberShouldReturnBillNotFound() {
        NotificationResponse notificationResponse = getPaymentInformationAndCreateReceipt(getRequest("002"), httpServletResponse);
        assertEquals("billing_not_found", notificationResponse.getMessage());
    }

    @Test
    public void givenInvalidServiceShouldReturnInvalidSignature() {
        NotificationResponse notificationResponse = getPaymentInformationAndCreateReceipt(getRequest("003"), httpServletResponse);
        assertEquals("invalid_signature", notificationResponse.getMessage());
    }

    @Test
    public void givenInvalidAcquirerShouldReturnInvalidSignature() {
        NotificationResponse notificationResponse = getPaymentInformationAndCreateReceipt(getRequest("004"), httpServletResponse);
        assertEquals("invalid_signature", notificationResponse.getMessage());
    }

    @Test
    public void givenInvalidChannelShouldReturnInvalidSignature() {
        NotificationResponse notificationResponse = getPaymentInformationAndCreateReceipt(getRequest("005"), httpServletResponse);
        assertEquals("invalid_signature", notificationResponse.getMessage());
    }

    @Test
    public void givenInvalidVapcShouldReturnInvalidSignature() {
        NotificationResponse notificationResponse = getPaymentInformationAndCreateReceipt(getRequest("006"), httpServletResponse);
        assertEquals("invalid_signature", notificationResponse.getMessage());
    }

    @Test
    public void givenDuplicatePaymentShouldReturnBillAlreadyPaid() {
        NotificationResponse notificationResponse = getPaymentInformationAndCreateReceipt(getRequest("007"), httpServletResponse);
        assertEquals("billing_already_paid", notificationResponse.getMessage());
    }

    @Test
    public void givenSuccessfulPaymentShouldReturnReceiptNumber() {
        NotificationResponse notificationResponse = getPaymentInformationAndCreateReceipt(getRequest("008"), httpServletResponse);
        assertEquals("Receipt No: B 649656", notificationResponse.getMessage());
    }

    private String getRequest(String invoiceNumber) {
        return "{\n" +
                "  \"service\": {\n" +
                "    \"id\": \"VIRTUAL_ACCOUNT\"\n" +
                "  },\n" +
                "  \"acquirer\": {\n" +
                "    \"id\": \"001\"\n" +
                "  },\n" +
                "  \"channel\": {\n" +
                "    \"id\": \"001\"\n" +
                "  },\n" +
                "  \"order\": {\n" +
                "    \"invoice_number\": \"" + invoiceNumber + "\",\n" +
                "    \"amount\": 20000\n" +
                "  },\n" +
                "  \"virtual_account_info\": {\n" +
                "    \"virtual_account_number\": \"001\",\n" +
                "    \"merchant_unique_reference\": \"001\",\n" +
                "    \"billing_type\": \"001\",\n" +
                "    \"identifier\":[\n" +
                "      {\n" +
                "        \"name\": \"001\",\n" +
                "        \"value\": \"001\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"virtual_account_payment\": {\n" +
                "    \"identifier\": [\n" +
                "      {\n" +
                "        \"name\": \"001\",\n" +
                "        \"value\": \"001\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"001\",\n" +
                "        \"value\": \"001\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"transaction\": {\n" +
                "    \"status\": \"" + (invoiceNumber.equals("001") ? "DECLINED" : "SUCCESS") + "\",\n" +
                "    \"date\": \"2021-12-24T16:23:39Z\",\n" +
                "    \"original_request_id\": \"456b001e-adc8-4fb8-a976-95831fb61468\"\n" +
                "  }\n" +
                "}";
    }

    private HttpServletResponse getDummyHttpServletResponse() {
        return new HttpServletResponse() {


            private int sc;

            @Override
            public void addCookie(Cookie cookie) {

            }

            @Override
            public boolean containsHeader(String name) {
                return false;
            }

            @Override
            public String encodeURL(String url) {
                return null;
            }

            @Override
            public String encodeRedirectURL(String url) {
                return null;
            }

            @Override
            public String encodeUrl(String url) {
                return null;
            }

            @Override
            public String encodeRedirectUrl(String url) {
                return null;
            }

            @Override
            public void sendError(int sc, String msg) throws IOException {

            }

            @Override
            public void sendError(int sc) throws IOException {

            }

            @Override
            public void sendRedirect(String location) throws IOException {

            }

            @Override
            public void setDateHeader(String name, long date) {

            }

            @Override
            public void addDateHeader(String name, long date) {

            }

            @Override
            public void setHeader(String name, String value) {

            }

            @Override
            public void addHeader(String name, String value) {

            }

            @Override
            public void setIntHeader(String name, int value) {

            }

            @Override
            public void addIntHeader(String name, int value) {

            }

            @Override
            public void setStatus(int sc) {
                this.sc = sc;
            }

            @Override
            public void setStatus(int sc, String sm) {

            }

            @Override
            public String getCharacterEncoding() {
                return null;
            }

            @Override
            public void setCharacterEncoding(String charset) {

            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public void setContentType(String type) {

            }

            @Override
            public ServletOutputStream getOutputStream() throws IOException {
                return null;
            }

            @Override
            public PrintWriter getWriter() throws IOException {
                return null;
            }

            @Override
            public void setContentLength(int len) {

            }

            @Override
            public int getBufferSize() {
                return 0;
            }

            @Override
            public void setBufferSize(int size) {

            }

            @Override
            public void flushBuffer() throws IOException {

            }

            @Override
            public void resetBuffer() {

            }

            @Override
            public boolean isCommitted() {
                return false;
            }

            @Override
            public void reset() {

            }

            @Override
            public Locale getLocale() {
                return null;
            }

            @Override
            public void setLocale(Locale loc) {

            }
        };
    }

}