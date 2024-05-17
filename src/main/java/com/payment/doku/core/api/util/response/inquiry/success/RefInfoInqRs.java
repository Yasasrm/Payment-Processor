package com.payment.doku.core.api.util.response.inquiry.success;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RefInfoInqRs {
    @JsonProperty("ref_name")
    private String refName;
    @JsonProperty("ref_value")
    private String refValue;
}
