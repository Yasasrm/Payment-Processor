package com.payment.doku.core.api.service.inquiry;

import com.payment.doku.core.api.enums.ChannelAcquirer;
import com.payment.doku.core.api.enums.FieldFlag;
import com.payment.doku.core.api.exception.InvalidParameterTypeException;
import com.payment.doku.core.api.util.response.inquiry.success.ResponseGeneral;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class InquiryServiceImplResponseObjectDecoderTest extends InquiryServiceImpl {

    @Test
    public void givenAcquirerBCA_ShouldReturnBCAEmptyResponse() {
        String expectedResponse = getExpectedBCAEmptyResponse();
        ResponseGeneral requestGeneral = new ResponseGeneralCreator().getEmptyObject(ChannelAcquirer.BCA.getId());
        assertEquals(getJsonNode(expectedResponse), getJsonNode(requestGeneral));
    }

    @Test
    public void givenAcquirerMandiri_ShouldReturnMandiriEmptyResponse() {
        String expectedResponse = getExpectedMandiriEmptyResponse();
        ResponseGeneral requestGeneral = new ResponseGeneralCreator().getEmptyObject(ChannelAcquirer.MANDIRI.getId());
        assertEquals(getJsonNode(expectedResponse), getJsonNode(requestGeneral));
    }

    @Test
    public void givenAcquirerSyariah_ShouldReturnSyariahEmptyResponse() {
        String expectedResponse = getExpectedSyariahEmptyResponse();
        ResponseGeneral requestGeneral = new ResponseGeneralCreator().getEmptyObject(ChannelAcquirer.SYARIAH.getId());
        assertEquals(getJsonNode(expectedResponse), getJsonNode(requestGeneral));
    }

    @Test
    public void givenAcquirerBRI_ShouldReturnBRIEmptyResponse() {
        String expectedResponse = getExpectedBRIEmptyResponse();
        ResponseGeneral requestGeneral = new ResponseGeneralCreator().getEmptyObject(ChannelAcquirer.BRI.getId());
        assertEquals(getJsonNode(expectedResponse), getJsonNode(requestGeneral));
    }

    @Test
    public void givenAcquirerPermata_ShouldReturnPermataEmptyResponse() {
        String expectedResponse = getExpectedPermataEmptyResponse();
        ResponseGeneral requestGeneral = new ResponseGeneralCreator().getEmptyObject(ChannelAcquirer.PERMATA.getId());
        assertEquals(getJsonNode(expectedResponse), getJsonNode(requestGeneral));
    }

    @Test
    public void givenAcquirerDOKU_ShouldReturnDOKUEmptyResponse() {
        String expectedResponse = getExpectedDOKUEmptyResponse();
        ResponseGeneral requestGeneral = new ResponseGeneralCreator().getEmptyObject(ChannelAcquirer.DOKU.getId());
        assertEquals(getJsonNode(expectedResponse), getJsonNode(requestGeneral));
    }

    @Test
    public void givenAcquirerCIMB_ShouldReturnCIMBEmptyResponse() {
        String expectedResponse = getExpectedCIMBEmptyResponse();
        ResponseGeneral requestGeneral = new ResponseGeneralCreator().getEmptyObject(ChannelAcquirer.CIMB.getId());
        assertEquals(getJsonNode(expectedResponse), getJsonNode(requestGeneral));
    }

    @Test
    public void givenAcquirerBNI_ShouldReturnBNIEmptyResponse() {
        String expectedResponse = getExpectedBNIEmptyResponse();
        ResponseGeneral requestGeneral = new ResponseGeneralCreator().getEmptyObject(ChannelAcquirer.BNI.getId());
        assertEquals(getJsonNode(expectedResponse), getJsonNode(requestGeneral));
    }

    @Test
    public void givenAcquirerAlfa_ShouldReturnAlfaEmptyResponse() {
        String expectedResponse = getExpectedAlfaEmptyResponse();
        ResponseGeneral requestGeneral = new ResponseGeneralCreator().getEmptyObject(ChannelAcquirer.ALFA.getId());
        assertEquals(getJsonNode(expectedResponse), getJsonNode(requestGeneral));
    }

    private <T> JsonNode getJsonNode(T request) {
        JsonNode j2 = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestGeneralString;
            if (request instanceof ResponseGeneral) {
                requestGeneralString = objectMapper.writeValueAsString(request);
            } else if (request instanceof String) {
                requestGeneralString = (String) request;
            } else
                throw new InvalidParameterTypeException();
            j2 = objectMapper.readTree(requestGeneralString);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        return j2;
    }

    private String getExpectedAlfaEmptyResponse() {
        return "{\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\":\"*\",\n" +
                "        \"amount\":" + FieldFlag.MANDATORY.getIntValue() + "\n" +
                "    },\n" +
                "    \"online_to_offline_info\": {\n" +
                "        \"payment_code\":\"*\",\n" +
                "        \"info1\":\"\"\n" +
                "    },\n" +
                "    \"online_to_offline_inquiry\": {\n" +
                "        \"status\":\"*\"\n" +
                "    },\n" +
                "    \"customer\": {\n" +
                "        \"name\":\"*\",\n" +
                "        \"email\":\"\"\n" +
                "    },\n" +
                "    \"additional_info\": {\n" +
                "    }\n" +
                "}";
    }

    private String getExpectedBNIEmptyResponse() {
        return "{\n" +
                "  \"order\": {\n" +
                "    \"invoice_number\": \"*\",\n" +
                "    \"amount\": " + FieldFlag.MANDATORY.getIntValue() + "\n" +
                "  },\n" +
                "  \"virtual_account_info\": {\n" +
                "    \"virtual_account_number\": \"*\",\n" +
                "    \"merchant_unique_reference\": \"*\",\n" +
                "    \"info\": \"\",\n" +
                "    \"identifier\":[\n" +
                "            {\n" +
                "                \"name\": \"\",\n" +
                "                \"value\": \"\"\n" +
                "            }\n" +
                "        ]\n" +
                "  },\n" +
                "    \"virtual_account_inquiry\": {\n" +
                "        \"status\":\"*\"\n" +
                "    },\n" +
                "  \"customer\": {\n" +
                "    \"name\": \"*\",\n" +
                "    \"email\": \"\",\n" +
                "    \"phone\": \"\"\n" +
                "  },\n" +
                "  \"additional_info\": {\n" +
                "    \"addl_label_1\": \"\",\n" +
                "    \"addl_label_2\": \"\",\n" +
                "    \"addl_label_3\": \"\",\n" +
                "    \"addl_value_1\": \"\",\n" +
                "    \"addl_value_2\": \"\",\n" +
                "    \"addl_value_3\": \"\",\n" +
                "    \"addl_label_1_en\": \"\",\n" +
                "    \"addl_label_2_en\": \"\",\n" +
                "    \"addl_label_3_en\": \"\",\n" +
                "    \"addl_value_1_en\": \"\",\n" +
                "    \"addl_value_2_en\": \"\",\n" +
                "    \"addl_value_3_en\": \"\"\n" +
                "  }\n" +
                "}";
    }

    private String getExpectedCIMBEmptyResponse() {
        return "{\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"*\",\n" +
                "        \"amount\": " + FieldFlag.MANDATORY.getIntValue() + "\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"billing_type\":\"\",\n" +
                "        \"virtual_account_number\": \"*\",\n" +
                "        \"info1\": \"\",\n" +
                "        \"info2\": \"\",\n" +
                "        \"info3\": \"\"\n" +
                "    },\n" +
                "    \"virtual_account_inquiry\": {\n" +
                "        \"status\": \"*\"\n" +
                "    },\n" +
                "    \"customer\": {\n" +
                "        \"name\": \"*\",\n" +
                "        \"email\": \"\"\n" +
                "    },\n" +
                "    \"additional_info\": {\n" +
                "    }\n" +
                "}";
    }

    private String getExpectedDOKUEmptyResponse() {
        return "{\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"*\",\n" +
                "        \"amount\": " + FieldFlag.MANDATORY.getIntValue() + "\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"billing_type\":\"\",\n" +
                "        \"virtual_account_number\": \"*\",\n" +
                "        \"info1\": \"\"\n" +
                "    },\n" +
                "    \"virtual_account_inquiry\": {\n" +
                "        \"status\": \"*\"\n" +
                "    },\n" +
                "    \"customer\": {\n" +
                "        \"name\": \"*\",\n" +
                "        \"email\": \"\"\n" +
                "    },\n" +
                "    \"additional_info\": {\n" +
                "    }\n" +
                "}";
    }

    private String getExpectedPermataEmptyResponse() {
        return "{\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"*\",\n" +
                "        \"amount\": " + FieldFlag.MANDATORY.getIntValue() + "\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"virtual_account_number\": \"*\",\n" +
                "        \"ref_info\" : [\n" +
                "            {\n" +
                "                \"ref_name\": \"\",\n" +
                "                \"ref_value\": \"\"\n" +
                "            },{\n" +
                "                \"ref_name\": \"\",\n" +
                "                \"ref_value\": \"\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"virtual_account_inquiry\": {\n" +
                "        \"status\": \"*\"\n" +
                "    },\n" +
                "    \"customer\": {\n" +
                "        \"name\": \"*\",\n" +
                "        \"email\": \"\"\n" +
                "    },\n" +
                "    \"additional_info\": {\n" +
                "    }\n" +
                "}";
    }

    private String getExpectedBRIEmptyResponse() {
        return "{\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"*\",\n" +
                "        \"amount\": " + FieldFlag.MANDATORY.getIntValue() + "\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"virtual_account_number\": \"*\",\n" +
                "        \"info1\": \"\",\n" +
                "        \"info2\": \"\",\n" +
                "        \"info3\": \"\",\n" +
                "        \"info4\": \"\",\n" +
                "        \"info5\": \"\"\n" +
                "    },\n" +
                "    \"virtual_account_inquiry\": {\n" +
                "        \"status\": \"*\"\n" +
                "    },\n" +
                "    \"customer\": {\n" +
                "        \"name\": \"*\",\n" +
                "        \"email\": \"\"\n" +
                "    },\n" +
                "    \"additional_info\": {\n" +
                "    }\n" +
                "}";
    }

    private String getExpectedSyariahEmptyResponse() {
        return "{\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\":\"*\",\n" +
                "        \"amount\":" + FieldFlag.MANDATORY.getIntValue() + "\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"virtual_account_number\":\"*\",\n" +
                "        \"info1\":\"\",\n" +
                "        \"info2\":\"\",\n" +
                "        \"info3\":\"\"\n" +
                "    },\n" +
                "    \"virtual_account_inquiry\": {\n" +
                "        \"status\":\"*\"\n" +
                "    },\n" +
                "    \"customer\": {\n" +
                "        \"name\":\"*\",\n" +
                "        \"email\":\"\"\n" +
                "    },\n" +
                "    \"additional_info\": {\n" +
                "    }\n" +
                "}";
    }

    private String getExpectedMandiriEmptyResponse() {
        return "{\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\":\"*\",\n" +
                "        \"amount\":" + FieldFlag.MANDATORY.getIntValue() + "\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"billing_type\":\"\",\n" +
                "        \"virtual_account_number\":\"*\",\n" +
                "        \"info1\":\"\",\n" +
                "        \"info2\":\"\",\n" +
                "        \"info3\":\"\"\n" +
                "    },\n" +
                "    \"virtual_account_inquiry\": {\n" +
                "        \"status\":\"*\"\n" +
                "    },\n" +
                "    \"customer\": {\n" +
                "        \"name\":\"*\",\n" +
                "        \"email\":\"\"\n" +
                "    },\n" +
                "    \"additional_info\": {\n" +
                "    }\n" +
                "}";
    }

    private String getExpectedBCAEmptyResponse() {
        return "{" +
                "    \"order\": {" +
                "        \"invoice_number\":\"*\"," +
                "        \"amount\":" + FieldFlag.MANDATORY.getIntValue() + "" +
                "    }," +
                "    \"virtual_account_info\": {" +
                "        \"virtual_account_number\":\"*\"," +
                "        \"info1\":\"\"," +
                "        \"info2\":\"\"," +
                "        \"info3\":\"\"" +
                "    }," +
                "    \"virtual_account_inquiry\": {" +
                "        \"status\":\"*\"" +
                "    }," +
                "    \"customer\": {" +
                "        \"name\":\"*\"," +
                "        \"email\":\"\"" +
                "    }," +
                "    \"additional_info\": {" +
                "    }" +
                "}";
    }

}