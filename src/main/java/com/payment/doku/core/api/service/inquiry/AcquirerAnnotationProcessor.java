package com.payment.doku.core.api.service.inquiry;

import com.payment.doku.core.api.enums.FieldFlag;
import com.payment.doku.core.api.util.annotation.Acquirer;
import com.payment.doku.core.api.util.annotation.AcquirerMandate;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class AcquirerAnnotationProcessor<T> {
    T response;
    String acquirer;

    public AcquirerAnnotationProcessor(T response, String acquirer) {
        this.response = response;
        this.acquirer = acquirer;
    }

    public T getProcessedObject() {
        processObject();
        return response;
    }

    private void processObject() {
        for (Field field : response.getClass().getDeclaredFields()) {
            Acquirer acquirerAnnotation = field.getAnnotation(Acquirer.class);
            AcquirerMandate acquirerMandateAnnotation = field.getAnnotation(AcquirerMandate.class);
            if (field.isAnnotationPresent(Acquirer.class)) {
                processAcquirer(field, acquirerAnnotation, acquirerMandateAnnotation);
            } else {
                setFieldValue(field, null);
            }
        }
    }

    private void processAcquirer(Field field, Acquirer acquirerAnnotation, AcquirerMandate acquirerMandateAnnotation) {
        List<String> acquirers = Arrays.asList(acquirerAnnotation.id());
        if (!acquirers.contains(acquirer)) {
            setFieldValue(field, null);
        } else {
            if (field.isAnnotationPresent(AcquirerMandate.class)) {
                processAcquirerMandate(field, acquirerMandateAnnotation);
            }
        }
    }

    private void processAcquirerMandate(Field field, AcquirerMandate acquirerMandateAnnotation) {
        List<String> fieldMandateAcquirers = Arrays.asList(acquirerMandateAnnotation.id());
        if (fieldMandateAcquirers.contains(acquirer)) {
            if (field.getType().equals(Integer.class)) {
                setFieldValue(field, FieldFlag.MANDATORY.getIntValue());
            } else {
                setFieldValue(field, FieldFlag.MANDATORY.getStringValue());
            }
        }
    }

    private <F> void setFieldValue(Field field, F fieldValue) {
        field.setAccessible(true);
        try {
            field.set(response, fieldValue);
        } catch (Exception e) {
        }
    }
}
