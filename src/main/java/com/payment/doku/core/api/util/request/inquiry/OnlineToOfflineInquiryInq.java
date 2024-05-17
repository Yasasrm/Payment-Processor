package com.payment.doku.core.api.util.request.inquiry;

import lombok.Data;

import java.util.Date;

@Data
public class OnlineToOfflineInquiryInq {
    private Date date;
    private IdentifierInq[] identifier;
}
