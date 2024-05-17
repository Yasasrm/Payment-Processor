package com.payment.doku.core.api.util.response.inquiry.success;

import com.payment.doku.core.api.util.annotation.Acquirer;
import com.payment.doku.core.api.util.annotation.AcquirerMandate;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class VirtualAccountInquiryInqRs {

    @Acquirer(id = {"BCA", "BANK_MANDIRI", "BANK_SYARIAH_MANDIRI", "BRI", "BANK_PERMATA", "DOKU", "BANK_CIMB", "BNI"})
    @AcquirerMandate(id = {"BCA", "BANK_MANDIRI", "BANK_SYARIAH_MANDIRI", "BRI", "BANK_PERMATA", "DOKU", "BANK_CIMB", "BNI"})
    private String status;
}
