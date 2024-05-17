package com.payment.doku.core.api.util.request.notification;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class OrderNtf {
    @JsonProperty("invoice_number")
    private String invoiceNumber;
    private Integer amount;
}
