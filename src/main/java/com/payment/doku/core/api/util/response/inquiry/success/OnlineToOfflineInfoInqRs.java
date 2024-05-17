package com.payment.doku.core.api.util.response.inquiry.success;

import com.payment.doku.core.api.util.annotation.Acquirer;
import com.payment.doku.core.api.util.annotation.AcquirerMandate;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OnlineToOfflineInfoInqRs {

    @Acquirer(id = "ALFA")
    @AcquirerMandate(id = "ALFA")
    @JsonProperty("payment_code")
    private String paymentCode;

    @Acquirer(id = "ALFA")
    private String info1;
}
