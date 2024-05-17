package com.payment.doku.core.api.util.response.inquiry.success;

import com.payment.doku.core.api.util.annotation.Acquirer;
import com.payment.doku.core.api.util.annotation.AcquirerMandate;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.List;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class VirtualAccountInfoInqRs {

    @Acquirer(id = {"BCA", "BANK_MANDIRI", "BANK_SYARIAH_MANDIRI", "BRI", "BANK_PERMATA", "DOKU", "BANK_CIMB", "BNI"})
    @AcquirerMandate(id = {"BCA", "BANK_MANDIRI", "BANK_SYARIAH_MANDIRI", "BRI", "BANK_PERMATA", "DOKU", "BANK_CIMB", "BNI"})
    @JsonProperty("virtual_account_number")
    private String virtualAccountNumber;

    @Acquirer(id = {"BCA", "BANK_MANDIRI", "BANK_SYARIAH_MANDIRI", "BRI", "DOKU", "BANK_CIMB"})
    private String info1;

    @Acquirer(id = {"BCA", "BANK_MANDIRI", "BANK_SYARIAH_MANDIRI", "BRI", "BANK_CIMB"})
    private String info2;

    @Acquirer(id = {"BCA", "BANK_MANDIRI", "BANK_SYARIAH_MANDIRI", "BRI", "BANK_CIMB"})
    private String info3;

    @Acquirer(id = "BRI")
    private String info4;

    @Acquirer(id = "BRI")
    private String info5;

    @Acquirer(id = {"BANK_MANDIRI", "DOKU", "BANK_CIMB"})
    @JsonProperty("billing_type")
    private String billingType;

    @Acquirer(id = "BANK_PERMATA")
    @JsonProperty("ref_info")
    private List<RefInfoInqRs> refInfo;

    @Acquirer(id = "BNI")
    @AcquirerMandate(id = "BNI")
    @JsonProperty("merchant_unique_reference")
    private String merchantUniqueReference;

    @Acquirer(id = "BNI")
    private String info;

    @Acquirer(id = "BNI")
    private List<IdentifierInqRs> identifier;
}
