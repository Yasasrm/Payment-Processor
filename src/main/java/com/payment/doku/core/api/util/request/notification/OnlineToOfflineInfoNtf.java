package com.payment.doku.core.api.util.request.notification;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class OnlineToOfflineInfoNtf {
    @JsonProperty("payment_code")
    private String paymentCode;
}
