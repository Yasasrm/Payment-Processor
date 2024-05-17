package com.payment.doku.core.api.repo;

import com.payment.doku.core.api.service.log.LoggerService;

import java.math.BigDecimal;
import java.util.Random;

public class LoggerServiceImplStub implements LoggerService {

    public BigDecimal id1;
    public BigDecimal id2;

    @Override
    public BigDecimal getNextLogId() {
        id1 = new BigDecimal(new Random().nextInt());
        return id1;
    }

    @Override
    public void logRecord(BigDecimal id, String method, String data) {
        id2 = id;
    }
}