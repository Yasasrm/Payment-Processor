package com.payment.doku.core.api.service.notification;

import javax.servlet.http.HttpServletResponse;

public interface NotificationService {
    <T> T getPaymentInformationAndCreateReceipt(String request, HttpServletResponse httpServletResponse);
}