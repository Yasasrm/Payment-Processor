package com.payment.doku.core.api.service.notification;


import com.payment.doku.core.api.util.request.notification.NotificationGeneral;
import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class NotificationServiceImplRequestObjectDecoderTest extends NotificationServiceImpl {

    @Test
    public void requestString_BCA_ShouldGenerateNotificationObject() {
        String request = getBCANotification();
        Assert.assertEquals(NotificationGeneral.class, getNotificationObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_Mandiri_ShouldGenerateNotificationObject() {
        String request = getMandiriNotification();
        Assert.assertEquals(NotificationGeneral.class, getNotificationObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_Syariah_ShouldGenerateNotificationObject() {
        String request = getSyariahNotification();
        Assert.assertEquals(NotificationGeneral.class, getNotificationObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_BRI_ShouldGenerateNotificationObject() {
        String request = getBRINotification();
        Assert.assertEquals(NotificationGeneral.class, getNotificationObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_BNI_ShouldGenerateNotificationObject() {
        String request = getBNINotification();
        Assert.assertEquals(NotificationGeneral.class, getNotificationObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_Doku_ShouldGenerateNotificationObject() {
        String request = getDokuNotification();
        Assert.assertEquals(NotificationGeneral.class, getNotificationObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_CIMB_ShouldGenerateNotificationObject() {
        String request = getCIMBNotification();
        Assert.assertEquals(NotificationGeneral.class, getNotificationObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_Permata_ShouldGenerateNotificationObject() {
        String request = getPermataNotification();
        Assert.assertEquals(NotificationGeneral.class, getNotificationObjectFromRequestString(request).getClass());
    }

    @Test
    public void requestString_Alfa_ShouldGenerateNotificationObject() {
        String request = getAlfaNotification();
        Assert.assertEquals(NotificationGeneral.class, getNotificationObjectFromRequestString(request).getClass());
    }

    private String getAlfaNotification() {
        return "{\n" +
                "    \"service\": {\n" +
                "        \"id\": \"ONLINE_TO_OFFLINE\"\n" +
                "    },\n" +
                "    \"acquirer\": {\n" +
                "        \"id\": \"ALFA\"\n" +
                "    },\n" +
                "    \"channel\": {\n" +
                "        \"id\": \"ONLINE_TO_OFFLINE_ALFA\"\n" +
                "    },\n" +
                "    \"transaction\": {\n" +
                "        \"status\": \"SUCCESS\",\n" +
                "        \"date\": \"2021-08-12T07:06:28Z\",\n" +
                "        \"original_request_id\": \"5b8e438f-fac1-4103-9e0e-ebfdc38b5acb\"\n" +
                "    },\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"INV-20210125-0001\",\n" +
                "        \"amount\": 150000\n" +
                "    },\n" +
                "    \"online_to_offline_info\": {\n" +
                "        \"payment_code\": \"73\"\n" +
                "    },\n" +
                "    \"online_to_offline_payment\": {\n" +
                "        \"identifier\": [\n" +
                "            {\n" +
                "                \"name\": \"AGENT_ID\",\n" +
                "                \"value\": \"ALFAMART\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"AGENT_STORE_ID\",\n" +
                "                \"value\": \"store\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"AGENT_TRX_ID\",\n" +
                "                \"value\": \"1246\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }

    private String getPermataNotification() {
        return "{\n" +
                "    \"service\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT\"\n" +
                "    },\n" +
                "    \"acquirer\": {\n" +
                "        \"id\": \"BANK_PERMATA\"\n" +
                "    },\n" +
                "    \"channel\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT_BANK_PERMATA\"\n" +
                "    },\n" +
                "    \"transaction\": {\n" +
                "        \"status\": \"SUCCESS\",\n" +
                "        \"date\": \"2020-08-11T09:06:18Z\",\n" +
                "        \"original_request_id\": \"e5a8a8b8-7eab-4be3-91d8-5e2d7ab7cc25\"\n" +
                "    },\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"INV-20210124-0001\",\n" +
                "        \"amount\": 150000\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"virtual_account_number\": \"1236260000000004\"\n" +
                "    },\n" +
                "    \"virtual_account_payment\": {\n" +
                "        \"identifier\": [\n" +
                "            {\n" +
                "                \"name\": \"TRANSAKSI_ID\",\n" +
                "                \"value\": \"127503812\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"TERMINAL_ID\",\n" +
                "                \"value\": \"1\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }

    private String getCIMBNotification() {
        return "{\n" +
                "    \"service\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT\"\n" +
                "    },\n" +
                "    \"acquirer\": {\n" +
                "        \"id\": \"CIMB\"\n" +
                "    },\n" +
                "    \"channel\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT_BANK_CIMB\"\n" +
                "    },\n" +
                "    \"transaction\": {\n" +
                "        \"status\": \"SUCCESS\",\n" +
                "        \"date\": \"2020-08-11T09:06:18Z\",\n" +
                "        \"original_request_id\": \"e5a8a8b8-7eab-4be3-91d8-5e2d7ab7cc25\"\n" +
                "    },\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"INV-20210124-0001\",\n" +
                "        \"amount\": 150000\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"virtual_account_number\": \"1236260000000004\"\n" +
                "    },\n" +
                "    \"virtual_account_payment\": {\n" +
                "        \"identifier\": [\n" +
                "            {\n" +
                "                \"name\": \"TRANSAKSI_ID\",\n" +
                "                \"value\": \"127503812\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"TERMINAL_ID\",\n" +
                "                \"value\": \"1\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }

    private String getDokuNotification() {
        return "{\n" +
                "    \"service\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT\"\n" +
                "    },\n" +
                "    \"acquirer\": {\n" +
                "        \"id\": \"DOKU\"\n" +
                "    },\n" +
                "    \"channel\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT_DOKU\"\n" +
                "    },\n" +
                "    \"transaction\": {\n" +
                "        \"status\": \"SUCCESS\",\n" +
                "        \"date\": \"2021-01-22T07:06:28Z\",\n" +
                "        \"original_request_id\": \"09e0defe-a071-45b3-9feb-ac134374628c\"\n" +
                "    },\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"INV-20210124-0001\",\n" +
                "        \"amount\": 150000\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"virtual_account_number\": \"8000100000000323\"\n" +
                "    },\n" +
                "    \"virtual_account_payment\": {\n" +
                "        \"identifier\": [\n" +
                "            {\n" +
                "                \"name\": \"TRACE_NUMBER\",\n" +
                "                \"value\": \"19832\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"TRANSACTION_NUMBER\",\n" +
                "                \"value\": \"d094700e379f0fb3b543e25c77f8e4b3e068f057\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"HOST_REFERENCE_NUMBER\",\n" +
                "                \"value\": \"\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }

    private String getBNINotification() {
        return "{\n" +
                "  \"service\": {\n" +
                "    \"id\": \"VIRTUAL_ACCOUNT\"\n" +
                "  },\n" +
                "  \"acquirer\": {\n" +
                "    \"id\": \"BNI\"\n" +
                "  },\n" +
                "  \"channel\": {\n" +
                "    \"id\": \"VIRTUAL_ACCOUNT_BNI\"\n" +
                "  },\n" +
                "  \"order\": {\n" +
                "    \"invoice_number\": \"INV-1640337742\",\n" +
                "    \"amount\": 20000\n" +
                "  },\n" +
                "  \"virtual_account_info\": {\n" +
                "    \"virtual_account_number\": \"8803300000000098\",\n" +
                "    \"merchant_unique_reference\": \"UNIQUE_00003\",\n" +
                "    \"billing_type\": \"FIXED\",\n" +
                "    \"identifier\":[\n" +
                "      {\n" +
                "        \"name\": \"BILLING_NUMBER\",\n" +
                "        \"value\": \"3280300000000001234\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"virtual_account_payment\": {\n" +
                "    \"identifier\": [\n" +
                "      {\n" +
                "        \"name\": \"TRX_ID\",\n" +
                "        \"value\": \"11901640337564039UNIQUE_00003\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"PAYMENT_NTB\",\n" +
                "        \"value\": \"228853\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"transaction\": {\n" +
                "    \"status\": \"SUCCESS\",\n" +
                "    \"date\": \"2021-12-24T16:23:39Z\",\n" +
                "    \"original_request_id\": \"456b001e-adc8-4fb8-a976-95831fb61468\"\n" +
                "  }\n" +
                "}";
    }

    private String getBRINotification() {
        return "{\n" +
                "    \"service\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT\"\n" +
                "    },\n" +
                "    \"acquirer\": {\n" +
                "        \"id\": \"BRI\"\n" +
                "    },\n" +
                "    \"channel\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT_BRI\"\n" +
                "    },\n" +
                "    \"transaction\": {\n" +
                "        \"status\": \"SUCCESS\",\n" +
                "        \"date\": \"2020-08-11T09:06:18Z\",\n" +
                "        \"original_request_id\": \"e5a8a8b8-7eab-4be3-91d8-5e2d7ab7cc25\"\n" +
                "    },\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"INV-20210124-0001\",\n" +
                "        \"amount\": 150000\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"virtual_account_number\": \"1236260000000004\"\n" +
                "    },\n" +
                "    \"virtual_account_payment\": {\n" +
                "        \"identifier\": [\n" +
                "            {\n" +
                "                \"name\": \"TRANSAKSI_ID\",\n" +
                "                \"value\": \"127503812\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"TERMINAL_ID\",\n" +
                "                \"value\": \"1\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }

    private String getSyariahNotification() {
        return "{\n" +
                "    \"service\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT\"\n" +
                "    },\n" +
                "    \"acquirer\": {\n" +
                "        \"id\": \"BANK_SYARIAH_MANDIRI\"\n" +
                "    },\n" +
                "    \"channel\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT_BANK_SYARIAH_MANDIRI\"\n" +
                "    },\n" +
                "    \"transaction\": {\n" +
                "        \"status\": \"SUCCESS\",\n" +
                "        \"date\": \"2021-01-27T06:00:20Z\",\n" +
                "        \"original_request_id\": \"d24a5644-6078-4249-8740-4a6dcd92df5a\"\n" +
                "    },\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"INV-20210124-0001\",\n" +
                "        \"amount\": 150000\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"virtual_account_number\": \"6059000000000205\"\n" +
                "    },\n" +
                "    \"virtual_account_payment\": {\n" +
                "        \"identifier\": [\n" +
                "            {\n" +
                "                \"name\": \"PAY_TERMINAL_ID\",\n" +
                "                \"value\": \"\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"BANK_REFERENCE\",\n" +
                "                \"value\": \"1232990188\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"PAY_CHANNEL\",\n" +
                "                \"value\": \"6019\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }

    private String getMandiriNotification() {
        return "{\n" +
                "    \"service\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT\"\n" +
                "    },\n" +
                "    \"acquirer\": {\n" +
                "        \"id\": \"BANK_MANDIRI\"\n" +
                "    },\n" +
                "    \"channel\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT_BANK_MANDIRI\"\n" +
                "    },\n" +
                "    \"transaction\": {\n" +
                "        \"status\": \"SUCCESS\",\n" +
                "        \"date\": \"2021-01-27T07:24:50Z\",\n" +
                "        \"original_request_id\": \"cc682442-6c22-493e-8121-b9ef6b3fa728\"\n" +
                "    },\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"INV-20210124-0001\",\n" +
                "        \"amount\": 150000\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"virtual_account_number\": \"8889940000000213\"\n" +
                "    },\n" +
                "    \"virtual_account_payment\": {\n" +
                "        \"identifier\": [\n" +
                "            {\n" +
                "                \"name\": \"TRANSACTION_ID\",\n" +
                "                \"value\": \"8341422\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"CHANNEL_ID\",\n" +
                "                \"value\": \"001\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }

    private String getBCANotification() {
        return "{\n" +
                "    \"service\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT\"\n" +
                "    },\n" +
                "    \"acquirer\": {\n" +
                "        \"id\": \"BCA\"\n" +
                "    },\n" +
                "    \"channel\": {\n" +
                "        \"id\": \"VIRTUAL_ACCOUNT_BCA\"\n" +
                "    },\n" +
                "    \"transaction\": {\n" +
                "        \"status\": \"SUCCESS\",\n" +
                "        \"date\": \"2021-01-27T03:24:23Z\",\n" +
                "        \"original_request_id\": \"15022aab-444f-4b04-afa8-ddfce89432ec\"\n" +
                "    },\n" +
                "    \"order\": {\n" +
                "        \"invoice_number\": \"INV-20210124-0001\",\n" +
                "        \"amount\": 150000\n" +
                "    },\n" +
                "    \"virtual_account_info\": {\n" +
                "        \"virtual_account_number\": \"1900600000000046\"\n" +
                "    },\n" +
                "    \"virtual_account_payment\": {\n" +
                "        \"identifier\": [\n" +
                "            {\n" +
                "                \"name\": \"REQUEST_ID\",\n" +
                "                \"value\": \"7892931\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"REFERENCE\",\n" +
                "                \"value\": \"6769200\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"CHANNEL_TYPE\",\n" +
                "                \"value\": \"6010\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
    }

}