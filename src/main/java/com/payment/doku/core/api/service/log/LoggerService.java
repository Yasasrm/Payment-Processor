package com.payment.doku.core.api.service.log;

import java.math.BigDecimal;

/**
 * @author YasasMa
 * @version 1.0.0.0
 */
public interface LoggerService {

    BigDecimal getNextLogId();

    void logRecord(BigDecimal id, String method, String data);

}
