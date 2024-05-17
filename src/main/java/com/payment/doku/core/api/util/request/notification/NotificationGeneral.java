package com.payment.doku.core.api.util.request.notification;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
@JsonIgnoreProperties
public class NotificationGeneral {
    private ServiceNtf service;
    private AcquirerNtf acquirer;
    private ChannelNtf channel;
    private TransactionNtf transaction;
    private OrderNtf order;
    @JsonProperty("virtual_account_info")
    private VirtualAccountInfoNtf virtualAccountInfo;
    @JsonProperty("virtual_account_payment")
    private VirtualAccountPaymentNtf virtualAccountPayment;
    @JsonProperty("online_to_offline_info")
    private OnlineToOfflineInfoNtf onlineToOfflineInfo;
    @JsonProperty("online_to_offline_payment")
    private OnlineToOfflinePaymentNtf onlineToOfflinePayment;
}
