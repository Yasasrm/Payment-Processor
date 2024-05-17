package com.payment.doku.core.api.util.request.notification;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@Data
@JsonIgnoreProperties
public class VirtualAccountPaymentNtf {
    private List<IdentifierNtf> identifier;
}
