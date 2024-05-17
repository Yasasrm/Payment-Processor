package com.payment.doku.core.api.util.response.inquiry.success;

import com.payment.doku.core.api.util.annotation.Acquirer;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseGeneral {

    @Acquirer(id = {"BCA", "BANK_MANDIRI", "BANK_SYARIAH_MANDIRI", "BRI", "BANK_PERMATA", "DOKU", "BANK_CIMB", "BNI", "ALFA"})
    private OrderInqRs order;

    @Acquirer(id = {"BCA", "BANK_MANDIRI", "BANK_SYARIAH_MANDIRI", "BRI", "BANK_PERMATA", "DOKU", "BANK_CIMB", "BNI", "ALFA"})
    private CustomerInqRs customer;

    @Acquirer(id = {"BCA", "BANK_MANDIRI", "BANK_SYARIAH_MANDIRI", "BRI", "BANK_PERMATA", "DOKU", "BANK_CIMB", "BNI", "ALFA"})
    @JsonProperty("additional_info")
    private AdditionalInfoInqRs additionalInfo;

    @Acquirer(id = {"BCA", "BANK_MANDIRI", "BANK_SYARIAH_MANDIRI", "BRI", "BANK_PERMATA", "DOKU", "BANK_CIMB", "BNI"})
    @JsonProperty("virtual_account_info")
    private VirtualAccountInfoInqRs virtualAccountInfo;


    @Acquirer(id = {"BCA", "BANK_MANDIRI", "BANK_SYARIAH_MANDIRI", "BRI", "BANK_PERMATA", "DOKU", "BANK_CIMB", "BNI"})
    @JsonProperty("virtual_account_inquiry")
    private VirtualAccountInquiryInqRs virtualAccountInquiry;

    @Acquirer(id = "ALFA")
    @JsonProperty("online_to_offline_info")
    private OnlineToOfflineInfoInqRs onlineToOfflineInfo;

    @Acquirer(id = "ALFA")
    @JsonProperty("online_to_offline_inquiry")
    private OnlineToOfflineInquiryInqRs onlineToOfflineInquiry;
}
