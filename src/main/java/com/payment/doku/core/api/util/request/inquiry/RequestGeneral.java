package com.payment.doku.core.api.util.request.inquiry;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
@JsonIgnoreProperties
public class RequestGeneral {
    private ServiceInq service;
    private AcquirerInq acquirer;
    private ChannelInq channel;
    @JsonProperty("virtual_account_info")
    private VirtualAccountInfoInq virtualAccountInfo;
    @JsonProperty("virtual_account_inquiry")
    private VirtualAccountInquiryInq virtualAccountInquiry;
    @JsonProperty("online_to_offline_info")
    private OnlineToOfflineInfoInq onlineToOfflineInfo;
    @JsonProperty("online_to_offline_inquiry")
    private OnlineToOfflineInquiryInq onlineToOfflineInquiry;
}
