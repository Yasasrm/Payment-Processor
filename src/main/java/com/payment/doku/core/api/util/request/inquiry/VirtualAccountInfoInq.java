package com.payment.doku.core.api.util.request.inquiry;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
public class VirtualAccountInfoInq {
    @JsonProperty("virtual_account_number")
    private String virtualAccountNumber;
    @JsonProperty("billing_type")
    private String billingType;
    @JsonProperty("merchant_unique_reference")
    private String merchantUniqueReference;
    private IdentifierInq[] identifier;
}
