package com.payment.doku.core.api.service.inquiry;

import com.payment.doku.core.api.repo.LoggerServiceImplStub;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class InquiryServiceImplLoggerTest extends InquiryServiceImpl {

    @Test
    public void logRecordShouldSaveForSameId() {
        logger = new LoggerServiceImplStub();
        createLogRecord("Service Test", "createLogRecord");
        updateLogRecord("updateLogRecord");
        LoggerServiceImplStub loggerStub = (LoggerServiceImplStub) logger;
        assertEquals(loggerStub.id1, loggerStub.id2);
    }
}