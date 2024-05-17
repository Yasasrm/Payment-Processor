package com.payment.doku.core.api.util.request.inquiry;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class OnlineToOfflineInfoInq {
    @JsonProperty("payment_code")
    private String paymentCode;
}
