package com.payment.doku.core.api.util.request.notification;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@Data
@JsonIgnoreProperties
public class VirtualAccountInfoNtf {

    @JsonProperty("virtual_account_number")
    private String virtualAccountNumber;

    @JsonProperty("billing_type")
    private String billingType;

    @JsonProperty("merchant_unique_reference")
    private String merchantUniqueReference;

    private List<IdentifierNtf> identifier;
}
