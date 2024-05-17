package com.payment.doku.core.api.service.inquiry;

import com.payment.doku.core.api.util.request.inquiry.RequestGeneral;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class InquiryServiceImplRequestObjectDecoderTest extends InquiryServiceImpl {

    @Test
    public void requestString_BCA_ShouldGenerateRequestObject() {
        String request = getBCARequest();
        assertEquals(RequestGeneral.class, getRequestObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_Mandiri_ShouldGenerateRequestObject() {
        String request = getMandiriRequest();
        assertEquals(RequestGeneral.class, getRequestObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_Syariah_ShouldGenerateRequestObject() throws IOException {
        String request = getSyariahRequest();
        assertEquals(RequestGeneral.class, getRequestObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_BRI_ShouldGenerateRequestObject() {
        String request = getBRIRequest();
        assertEquals(RequestGeneral.class, getRequestObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_Permata_ShouldGenerateRequestObject() {
        String request = getPermataRequest();
        assertEquals(RequestGeneral.class, getRequestObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_DOKU_ShouldGenerateRequestObject() {
        String request = getDOKURequest();
        assertEquals(RequestGeneral.class, getRequestObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_CIMB_ShouldGenerateRequestObject() {
        String request = getCIMBRequest();
        assertEquals(RequestGeneral.class, getRequestObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_BNI_ShouldGenerateRequestObject() {
        String request = getBNIRequest();
        assertEquals(RequestGeneral.class, getRequestObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_Alfa_ShouldGenerateRequestObject() {
        String request = getAlfaRequest();
        assertEquals(RequestGeneral.class, getRequestObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_Unknown_ShouldThrowInvalidRequestObjectException() {
        try {
            getRequestObjectFromRequestString("{\"Test\" : 1}");
        } catch (Exception e) {
            assertEquals("Invalid type of object received as request.", e.getMessage());
        }
    }

    private String getBCARequest() {
        return "{" +
                "    \"service\": {" +
                "        \"id\":\"VIRTUAL_ACCOUNT\"" +
                "    }," +
                "    \"acquirer\": {" +
                "        \"id\":\"BCA\"" +
                "    }," +
                "    \"channel\": {" +
                "        \"id\":\"VIRTUAL_ACCOUNT_BCA\"" +
                "    }," +
                "    \"virtual_account_info\": {" +
                "        \"virtual_account_number\":\"8896512345678123\"," +
                "        \"billing_type\":\"FIX_BILL\"" +
                "    }," +
                "    \"virtual_account_inquiry\": {" +
                "        \"date\":\"2020-08-11T08:45:42Z\"," +
                "        \"identifier\": [" +
                "            {" +
                "                \"name\": \"REQUEST_ID\"," +
                "                \"value\": \"201507131507262221400000001975\"" +
                "            }," +
                "            {" +
                "                \"name\": \"CHANNEL_TYPE\"," +
                "                \"value\": \"6011\"" +
                "            }" +
                "        ]" +
                "    }" +
                "}";
    }

    private String getMandiriRequest() {
        return "{" +
                "    \"service\": {" +
                "        \"id\":\"VIRTUAL_ACCOUNT\"" +
                "    }," +
                "    \"acquirer\": {" +
                "        \"id\":\"BANK_MANDIRI\"" +
                "    }," +
                "    \"channel\": {" +
                "        \"id\":\"VIRTUAL_ACCOUNT_BANK_MANDIRI\"" +
                "    }," +
                "    \"virtual_account_info\": {" +
                "        \"virtual_account_number\":\"8896512345678123\"" +
                "    }," +
                "    \"virtual_account_inquiry\": {" +
                "        \"date\":\"2020-08-11T08:45:42Z\"," +
                "        \"identifier\": [" +
                "            {" +
                "                \"name\": \"TRACE_NUMBER\"," +
                "                \"value\": \"100001\"" +
                "            }," +
                "            {" +
                "                \"name\": \"TRANSACTION_NUMBER\"," +
                "                \"value\": \"c1aa04bf421e5b38c3d18933e9994d3f289def65\"" +
                "            }" +
                "        ]" +
                "    }" +
                "}";
    }

    private String getSyariahRequest() {
        return "{" +
                "    \"service\": {" +
                "        \"id\":\"VIRTUAL_ACCOUNT\"" +
                "    }," +
                "    \"acquirer\": {" +
                "        \"id\":\"BANK_SYARIAH_MANDIRI\"" +
                "    }," +
                "    \"channel\": {" +
                "        \"id\":\"VIRTUAL_ACCOUNT_BANK_SYARIAH_MANDIRI\"" +
                "    }," +
                "    \"virtual_account_info\": {" +
                "        \"virtual_account_number\":\"8896512345678123\"" +
                "    }," +
                "    \"virtual_account_inquiry\": {" +
                "        \"date\":\"20220610152106\"," +
                "        \"identifier\": [" +
                "        ]" +
                "    }" +
                "}";
    }

    private String getBRIRequest() {
        return "{" +
                "   \"service\":{" +
                "      \"id\": \"VIRTUAL_ACCOUNT\"" +
                "   }," +
                "   \"acquirer\":{" +
                "      \"id\": \"BRI\"" +
                "   }," +
                "   \"channel\":{" +
                "      \"id\": \"VIRTUAL_ACCOUNT_BRI\"" +
                "   }," +
                "   \"virtual_account_info\":{" +
                "      \"virtual_account_number\": \"7889940000000001\"," +
                "      \"billing_type\": \"FIX_BILL\"" +
                "   }," +
                "   \"virtual_account_inquiry\":{" +
                "      \"date\": \"20210913100120\"," +
                "      \"identifier\": [" +
                "         {" +
                "            \"name\": \"BANK_ID\"," +
                "            \"value\": \"002\"" +
                "         }," +
                "         {" +
                "            \"name\": \"TERMINAL_ID\"," +
                "            \"value\": \"1\"" +
                "         }" +
                "      ]" +
                "   }" +
                "}";
    }

    private String getPermataRequest() {
        return "{" +
                "   \"service\":{" +
                "      \"id\": \"VIRTUAL_ACCOUNT\"" +
                "   }," +
                "   \"acquirer\":{" +
                "      \"id\": \"BANK_PERMATA\"" +
                "   }," +
                "   \"channel\":{" +
                "      \"id\": \"VIRTUAL_ACCOUNT_BANK_PERMATA\"" +
                "   }," +
                "   \"virtual_account_info\":{" +
                "      \"virtual_account_number\": \"7889940000000001\"," +
                "      \"billing_type\": \"FIX_BILL\"" +
                "   }," +
                "   \"virtual_account_inquiry\":{" +
                "      \"date\": \"2022-06-21T10:03:44Z\"," +
                "      \"identifier\": [" +
                "         {" +
                "            \"name\": \"BANK_ID\"," +
                "            \"value\": \"002\"" +
                "         },{" +
                "            \"name\": \"TERMINAL_ID\"," +
                "            \"value\": \"1\"" +
                "         }" +
                "      ]" +
                "   }" +
                "}";
    }

    private String getDOKURequest() {
        return "{" +
                "   \"service\":{" +
                "      \"id\": \"VIRTUAL_ACCOUNT\"" +
                "   }," +
                "   \"acquirer\":{" +
                "      \"id\": \"DOKU\"" +
                "   }," +
                "   \"channel\":{" +
                "      \"id\": \"VIRTUAL_ACCOUNT_DOKU\"" +
                "   }," +
                "   \"virtual_account_info\":{" +
                "      \"virtual_account_number\": \"7889940000000001\"" +
                "   }," +
                "   \"virtual_account_inquiry\":{" +
                "      \"date\": \"20210913100120\"," +
                "      \"identifier\": [" +
                "      ]" +
                "   }" +
                "}";
    }

    private String getCIMBRequest() {
        return "{" +
                "   \"service\":{" +
                "      \"id\": \"VIRTUAL_ACCOUNT\"" +
                "   }," +
                "   \"acquirer\":{" +
                "      \"id\": \"BANK_CIMB\"" +
                "   }," +
                "   \"channel\":{" +
                "      \"id\": \"VIRTUAL_ACCOUNT_BANK_CIMB\"" +
                "   }," +
                "   \"virtual_account_info\":{" +
                "      \"virtual_account_number\": \"7889940000000001\"" +
                "   }," +
                "   \"virtual_account_inquiry\":{" +
                "      \"date\": \"2021-12-17T11:15:16Z\"," +
                "      \"identifier\": [" +
                "         {" +
                "            \"name\":\"TERMINAL_ID\"," +
                "            \"value\":\"123.456.890.111\"" +
                "         }," +
                "         {" +
                "            \"name\":\"TRANSACTION_NUMBER\"," +
                "            \"value\":\"e40f0204-b761-4532-ae91-8e862d200005\"" +
                "         }" +
                "      ]" +
                "   }" +
                "}";
    }

    private String getBNIRequest() {
        return "{" +
                "    \"service\": {" +
                "        \"id\":\"VIRTUAL_ACCOUNT\"" +
                "    }," +
                "    \"acquirer\": {" +
                "        \"id\":\"BNI\"" +
                "    }," +
                "    \"channel\": {" +
                "        \"id\":\"VIRTUAL_ACCOUNT_BNI\"" +
                "    }," +
                "    \"virtual_account_info\": {" +
                "        \"virtual_account_number\":\"780300000000001234\"," +
                "        \"merchant_unique_reference\": \"101\"," +
                "        \"identifier\":[" +
                "            {" +
                "                \"name\": \"BILLING_NUMBER\"," +
                "                \"value\": \"3280300000000001234\"" +
                "            }" +
                "        ]" +
                "    }," +
                "    \"virtual_account_inquiry\": {" +
                "        \"date\":\"2020-08-11T08:45:42Z\"" +
                "    }" +
                "}";
    }

    private String getAlfaRequest() {
        return "{" +
                "    \"service\": {" +
                "        \"id\":\"ONLINE_TO_OFFLINE\"" +
                "    }," +
                "    \"acquirer\": {" +
                "        \"id\":\"ALFA\"" +
                "    }," +
                "    \"channel\": {" +
                "        \"id\":\"ONLINE_TO_OFFLINE_ALFA\"" +
                "    }," +
                "    \"online_to_offline_info\": {" +
                "        \"payment_code\":\"8896512345678123\"" +
                "    }," +
                "    \"online_to_offline_inquiry\": {" +
                "        \"date\":\"2020-08-11T08:45:42Z\"," +
                "        \"identifier\": [" +
                "        ]" +
                "    }" +
                "}";
    }
}