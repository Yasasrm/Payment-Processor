package com.payment.doku.core.api.util.response.inquiry.success;

import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class IdentifierInqRs {
    private String name;
    private String value;
}
