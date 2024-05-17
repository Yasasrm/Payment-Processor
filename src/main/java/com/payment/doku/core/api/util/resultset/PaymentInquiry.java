package com.payment.doku.core.api.util.resultset;

import com.payment.doku.core.api.enums.InquiryStatus;
import com.payment.doku.core.api.util.request.inquiry.RequestGeneral;
import lombok.Data;

@Data
public class PaymentInquiry {
    private String invoiceNumber;
    private Integer amount;
    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;
    private String contractNumber;
    private InquiryStatus inquiry;
    private RequestGeneral requestGeneral;
}
