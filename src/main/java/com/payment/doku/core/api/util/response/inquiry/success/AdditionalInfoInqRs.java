package com.payment.doku.core.api.util.response.inquiry.success;

import com.payment.doku.core.api.util.annotation.Acquirer;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AdditionalInfoInqRs {

    @Acquirer(id = "BNI")
    @JsonProperty("addl_label_1")
    private String addlLabel1;

    @Acquirer(id = "BNI")
    @JsonProperty("addl_label_2")
    private String addlLabel2;

    @Acquirer(id = "BNI")
    @JsonProperty("addl_label_3")
    private String addlLabel3;

    @Acquirer(id = "BNI")
    @JsonProperty("addl_value_1")
    private String addlValue1;

    @Acquirer(id = "BNI")
    @JsonProperty("addl_value_2")
    private String addlValue2;

    @Acquirer(id = "BNI")
    @JsonProperty("addl_value_3")
    private String addlValue3;

    @Acquirer(id = "BNI")
    @JsonProperty("addl_label_1_en")
    private String addlLabel1En;

    @Acquirer(id = "BNI")
    @JsonProperty("addl_label_2_en")
    private String addlLabel2En;

    @Acquirer(id = "BNI")
    @JsonProperty("addl_label_3_en")
    private String addlLabel3En;

    @Acquirer(id = "BNI")
    @JsonProperty("addl_value_1_en")
    private String addlValue1En;

    @Acquirer(id = "BNI")
    @JsonProperty("addl_value_2_en")
    private String addlValue2En;

    @Acquirer(id = "BNI")
    @JsonProperty("addl_value_3_en")
    private String addlValue3En;
}
