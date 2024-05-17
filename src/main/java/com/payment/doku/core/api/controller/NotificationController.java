package com.payment.doku.core.api.controller;

import com.payment.doku.core.api.service.notification.NotificationService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "payment")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @RequestMapping(value = "notification", method = RequestMethod.POST)
    @ResponseBody
    public String customerPaymentNotification(@RequestBody String request, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(notificationService.getPaymentInformationAndCreateReceipt(request, httpServletResponse));
    }
}
