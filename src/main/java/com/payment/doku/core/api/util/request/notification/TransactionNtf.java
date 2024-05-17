package com.payment.doku.core.api.util.request.notification;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
@JsonIgnoreProperties
public class TransactionNtf {
    private String status;
    private String date;
    @JsonProperty("original_request_id")
    private String originalRequestId;
}
