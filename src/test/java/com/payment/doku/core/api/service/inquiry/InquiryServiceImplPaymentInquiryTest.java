package com.payment.doku.core.api.service.inquiry;

import com.payment.doku.core.api.enums.InquiryStatus;
import com.payment.doku.core.api.repo.DokuInvoiceDaoImplStub;
import com.payment.doku.core.api.repo.LoggerServiceImplStub;
import com.payment.doku.core.api.repo.PaymentInquiryDaoImplStub;
import com.payment.doku.core.api.util.response.inquiry.success.ResponseGeneral;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import static org.junit.Assert.assertEquals;


public class InquiryServiceImplPaymentInquiryTest extends InquiryServiceImpl {

    HttpServletResponse httpServletResponse;

    @Before
    public void setup() {
        paymentInquiryDao = new PaymentInquiryDaoImplStub();
        dokuInvoiceDao = new DokuInvoiceDaoImplStub();
        logger = new LoggerServiceImplStub();
    }

    @Test
    public void givenTheInvalid_PC_ShouldResponseWithStatus_BILLING_NOT_FOUND() {
        assertEquals(InquiryStatus.BILLING_NOT_FOUND.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyPCRequest("0000"))).getOnlineToOfflineInquiry().getStatus());
    }

    @Test
    public void givenTheInvalid_VA_ShouldResponseWithStatus_BILLING_NOT_FOUND() {
        assertEquals(InquiryStatus.BILLING_NOT_FOUND.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyVARequest("0000"))).getVirtualAccountInquiry().getStatus());
    }

    @Test
    public void givenTheDuePaid_PC_ShouldResponseWithStatus_BILLING_ALREADY_PAID() {
        assertEquals(InquiryStatus.BILLING_ALREADY_PAID.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyPCRequest("0001"))).getOnlineToOfflineInquiry().getStatus());
    }

    @Test
    public void givenTheDuePaid_VA_ShouldResponseWithStatus_BILLING_ALREADY_PAID() {
        assertEquals(InquiryStatus.BILLING_ALREADY_PAID.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyVARequest("0001"))).getVirtualAccountInquiry().getStatus());
    }

    @Test
    public void givenTheDueZeroExpired_PC_ShouldResponseWithStatus_BILLING_WAS_EXPIRED() {
        assertEquals(InquiryStatus.BILLING_WAS_EXPIRED.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyPCRequest("0002"))).getOnlineToOfflineInquiry().getStatus());
    }

    @Test
    public void givenTheDueZeroExpired_VA_ShouldResponseWithStatus_BILLING_WAS_EXPIRED() {
        assertEquals(InquiryStatus.BILLING_WAS_EXPIRED.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyVARequest("0002"))).getVirtualAccountInquiry().getStatus());
    }

    @Test
    public void givenTheNull_PC_ShouldResponseWithStatus_INVALID_SIGNATURE() {
        assertEquals(InquiryStatus.INVALID_SIGNATURE.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyPCRequest("0003"))).getOnlineToOfflineInquiry().getStatus());
    }

    @Test
    public void givenTheNull_VA_ShouldResponseWithStatus_INVALID_SIGNATURE() {
        assertEquals(InquiryStatus.INVALID_SIGNATURE.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyVARequest("0003"))).getVirtualAccountInquiry().getStatus());
    }

    @Test
    public void givenThePCWithUndeclaredMandatoryString_ShouldResponseWithStatus_DECLINE() {
        assertEquals(InquiryStatus.DECLINE.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyPCRequest("0004"))).getOnlineToOfflineInquiry().getStatus());
    }

    @Test
    public void givenThePCWithUndeclaredMandatoryInteger_ShouldResponseWithStatus_DECLINE() {
        assertEquals(InquiryStatus.DECLINE.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyPCRequest("0005"))).getOnlineToOfflineInquiry().getStatus());
    }

    @Test
    public void givenTheVAWithUndeclaredMandatoryString_ShouldResponseWithStatus_DECLINE() {
        assertEquals(InquiryStatus.DECLINE.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyVARequest("0004"))).getVirtualAccountInquiry().getStatus());
    }

    @Test
    public void givenTheVAWithUndeclaredMandatoryInteger_ShouldResponseWithStatus_DECLINE() {
        assertEquals(InquiryStatus.DECLINE.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyVARequest("0005"))).getVirtualAccountInquiry().getStatus());
    }


    @Test
    public void inAnUnexpectedSituation_ShouldThrowUnexpectedErrorException() {
        try {
            getResponseGeneral(getDummyPCRequest("0006"));
        } catch (Exception e) {
            assertEquals("Unexpected Error!", e.getMessage());
        }
    }

    @Test
    public void givenTheValid_PC_ShouldResponseWithStatus_SUCCESS() {
        assertEquals(InquiryStatus.SUCCESS.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyPCRequest("0007"))).getOnlineToOfflineInquiry().getStatus());
    }

    @Test
    public void givenTheValid_VAShouldResponseWithStatus_SUCCESS() {
        assertEquals(InquiryStatus.SUCCESS.getStatus(), ((ResponseGeneral) getResponseGeneral(getDummyVARequest("0007"))).getVirtualAccountInquiry().getStatus());
    }

    private <T> T getResponseGeneral(String dummyPCRequest) {
        httpServletResponse = getDummyHttpServletResponse();
        String request = dummyPCRequest;
        return inquireCustomerForPaymentsAndSendInformation(request, httpServletResponse);
    }

    private String getDummyVARequest(String va) {
        return "{\n" +
                "    \"service\": {\n" +
                "        \"id\":\"VIRTUAL_ACCOUNT\"\n" +
                "    },\n" +
                "    \"acquirer\": {\n" +
                "        \"id\":\"BCA\"\n" +
                "    },\n" +
                "    \"channel\": {\n" +
                "        \"id\":\"VIRTUAL_ACCOUNT_BCA\"\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"virtual_account_number\":\"" + va + "\",\n" +
                "        \"billing_type\":\"FIX_BILL\"\n" +
                "    },\n" +
                "    \"virtual_account_inquiry\": {\n" +
                "        \"date\":\"2020-08-11T08:45:42Z\",\n" +
                "        \"identifier\": [\n" +
                "            {\n" +
                "                \"name\": \"REQUEST_ID\",\n" +
                "                \"value\": \"201507131507262221400000001975\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"CHANNEL_TYPE\",\n" +
                "                \"value\": \"6011\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }

    private String getDummyPCRequest(String pc) {
        return "{\n" +
                "    \"service\": {\n" +
                "        \"id\":\"ONLINE_TO_OFFLINE\"\n" +
                "    },\n" +
                "    \"acquirer\": {\n" +
                "        \"id\":\"ALFA\"\n" +
                "    },\n" +
                "    \"channel\": {\n" +
                "        \"id\":\"ONLINE_TO_OFFLINE_ALFA\"\n" +
                "    },\n" +
                "    \"online_to_offline_info\": {\n" +
                "        \"payment_code\":\"" + pc + "\"\n" +
                "    },\n" +
                "    \"online_to_offline_inquiry\": {\n" +
                "        \"date\":\"2020-08-11T08:45:42Z\",\n" +
                "        \"identifier\": [\n" +
                "        ]\n" +
                "    }\n" +
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