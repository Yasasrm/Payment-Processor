package com.payment.doku.core.api.controller;

import com.payment.doku.core.api.service.inquiry.InquiryService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "inquiry")
public class InquiryController {

    @Autowired
    protected InquiryService inquiryService;

    @RequestMapping(value = "payments", method = RequestMethod.POST)
    @ResponseBody
    public String customerInquiryForPayments(@RequestBody String request, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(inquiryService.inquireCustomerForPaymentsAndSendInformation(request, httpServletResponse));
    }
}
