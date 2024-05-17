package com.payment.doku.core.api.util.response.notification;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

@Data
public class NotificationResponse {
    private String status;
    private String message;
    @JsonIgnore
    private String receiptAmount;
    @JsonIgnore
    @Value("N/A")
    private String receiptNumber;
}
