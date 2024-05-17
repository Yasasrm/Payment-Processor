package com.payment.doku.core.api.service.inquiry;

import javax.servlet.http.HttpServletResponse;

public interface InquiryService {
    <T> T inquireCustomerForPaymentsAndSendInformation(String request, HttpServletResponse httpServletResponse);
}
