package com.payment.doku.core.api.service.inquiry;

import com.payment.doku.core.api.enums.FieldFlag;
import com.payment.doku.core.api.enums.InquiryStatus;
import com.payment.doku.core.api.exception.InquiryException;
import com.payment.doku.core.api.util.response.inquiry.success.ResponseGeneral;
import com.payment.doku.core.api.util.resultset.PaymentInquiry;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

public class ResponseGeneralInitiator {

    private final PaymentInquiry paymentInquiry;
    private ResponseGeneral responseGeneral;

    protected ResponseGeneralInitiator(PaymentInquiry paymentInquiry) {
        this.paymentInquiry = paymentInquiry;
    }

    protected ResponseGeneral initiate(HttpServletResponse httpServletResponse) {
        createResponseGeneral();
        initiateResponseGeneral(httpServletResponse);
        validateResponseGeneral(httpServletResponse);
        return responseGeneral;
    }

    private void validateResponseGeneral(HttpServletResponse httpServletResponse) {
        try {
            int numberOfUninitializedMandatoryField = 0;
            if (loopResponseGeneralAndGet(numberOfUninitializedMandatoryField) > 0)
                throw new InquiryException(InquiryStatus.DECLINE);
        } catch (InquiryException ie) {
            setErrorAndCode(httpServletResponse, ie.getStatusCode(), ie.getMessage());
        } catch (IllegalAccessException iae) {
            iae.printStackTrace();
            setErrorAndCode(httpServletResponse, InquiryStatus.DECLINE.getCode(), InquiryStatus.DECLINE.getStatus());
        }
    }

    private int loopResponseGeneralAndGet(int numberOfUninitializedMandatoryField) throws IllegalAccessException {
        for (Field field : responseGeneral.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object fieldValue = field.get(responseGeneral);
            if (fieldValue != null) {
                if (fieldValue instanceof Iterable) {
                    for (Object item : (Iterable<?>) fieldValue) {
                        if (isUninitializedMandatoryValuesAvailableThenSetToDefault(item))
                            numberOfUninitializedMandatoryField++;
                    }
                } else {
                    if (isUninitializedMandatoryValuesAvailableThenSetToDefault(fieldValue))
                        numberOfUninitializedMandatoryField++;
                }
            }
        }
        return numberOfUninitializedMandatoryField;
    }

    private void initiateResponseGeneral(HttpServletResponse httpServletResponse) {
        initiateVirtualAccountInquiry();
        initiateOnlineToOfflineInquiry();
        initiateOrder();
        initiateCustomer();
        initiateVirtualAccountInfo();
        initiateOnlineToOfflineInfo();
        if (isPaymentInquiryFailed())
            setErrorAndCode(httpServletResponse, paymentInquiry.getInquiry().getCode(), paymentInquiry.getInquiry().getStatus());
    }

    private void setErrorAndCode(HttpServletResponse httpServletResponse, Integer code, String status) {
        httpServletResponse.setStatus(code);
        if (responseGeneral.getVirtualAccountInquiry() != null)
            responseGeneral.getVirtualAccountInquiry().setStatus(status);
        if (responseGeneral.getOnlineToOfflineInquiry() != null)
            responseGeneral.getOnlineToOfflineInquiry().setStatus(status);
    }

    private boolean isPaymentInquiryFailed() {
        return !paymentInquiry.getInquiry().equals(InquiryStatus.SUCCESS);
    }

    private void initiateOnlineToOfflineInquiry() {
        if (isObjectAvailable(responseGeneral.getOnlineToOfflineInquiry())) {
            responseGeneral.getOnlineToOfflineInquiry().setStatus(paymentInquiry.getInquiry().getStatus());
        }
    }

    private void initiateVirtualAccountInquiry() {
        if (isObjectAvailable(responseGeneral.getVirtualAccountInquiry())) {
            responseGeneral.getVirtualAccountInquiry().setStatus(paymentInquiry.getInquiry().getStatus());
        }
    }

    private void initiateOnlineToOfflineInfo() {
        if (isObjectAvailable(responseGeneral.getOnlineToOfflineInfo())) {
            if (isObjectAvailable(responseGeneral.getOnlineToOfflineInfo().getPaymentCode()))
                responseGeneral.getOnlineToOfflineInfo().setPaymentCode(paymentInquiry.getRequestGeneral().getOnlineToOfflineInfo().getPaymentCode());

            if (isObjectAvailable(responseGeneral.getOnlineToOfflineInfo().getInfo1()))
                responseGeneral.getOnlineToOfflineInfo().setInfo1("Contract No: " + paymentInquiry.getContractNumber());
        }
    }

    private void initiateVirtualAccountInfo() {
        if (isObjectAvailable(responseGeneral.getVirtualAccountInfo())) {
            if (isObjectAvailable(responseGeneral.getVirtualAccountInfo().getVirtualAccountNumber()))
                responseGeneral.getVirtualAccountInfo().setVirtualAccountNumber(paymentInquiry.getRequestGeneral().getVirtualAccountInfo().getVirtualAccountNumber());

            if (isObjectAvailable(responseGeneral.getVirtualAccountInfo().getInfo1()))
                responseGeneral.getVirtualAccountInfo().setInfo1("Contract No: " + paymentInquiry.getContractNumber());

            if (isObjectAvailable(responseGeneral.getVirtualAccountInfo().getInfo()))
                responseGeneral.getVirtualAccountInfo().setInfo("Contract No: " + paymentInquiry.getContractNumber());

            if (isObjectAvailable(responseGeneral.getVirtualAccountInfo().getRefInfo())) {
                if (isObjectAvailable(responseGeneral.getVirtualAccountInfo().getRefInfo().get(0).getRefName()))
                    responseGeneral.getVirtualAccountInfo().getRefInfo().get(0).setRefName("Contract Number");
                if (isObjectAvailable(responseGeneral.getVirtualAccountInfo().getRefInfo().get(0).getRefValue()))
                    responseGeneral.getVirtualAccountInfo().getRefInfo().get(0).setRefValue(paymentInquiry.getContractNumber());
            }

            if (isObjectAvailable(responseGeneral.getVirtualAccountInfo().getMerchantUniqueReference()))
                responseGeneral.getVirtualAccountInfo().setMerchantUniqueReference(responseGeneral.getVirtualAccountInfo().getMerchantUniqueReference());
        }
    }

    private void initiateCustomer() {
        if (isObjectAvailable(responseGeneral.getCustomer())) {
            if (isObjectAvailable(responseGeneral.getCustomer().getName()))
                responseGeneral.getCustomer().setName(paymentInquiry.getCustomerName());

            if (isObjectAvailable(responseGeneral.getCustomer().getEmail()))
                responseGeneral.getCustomer().setEmail(paymentInquiry.getCustomerEmail());

            if (isObjectAvailable(responseGeneral.getCustomer().getPhone()))
                responseGeneral.getCustomer().setPhone(paymentInquiry.getCustomerPhoneNumber());
        }
    }

    private void initiateOrder() {
        if (isObjectAvailable(responseGeneral.getOrder())) {
            if (isObjectAvailable(responseGeneral.getOrder().getInvoiceNumber()))
                responseGeneral.getOrder().setInvoiceNumber(paymentInquiry.getInvoiceNumber());
            if (isObjectAvailable(responseGeneral.getOrder().getAmount()))
                responseGeneral.getOrder().setAmount(paymentInquiry.getAmount());
        }
    }

    private <T> boolean isUninitializedMandatoryValuesAvailableThenSetToDefault(T object) throws IllegalAccessException {
        int uninitializedMandatoryValueTracker = 0;
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (isUninitializedMandatoryValue(field.get(object))) {
                setFieldToDefault(object, field);
                uninitializedMandatoryValueTracker++;
            }
        }
        if (uninitializedMandatoryValueTracker > 0)
            return true;
        return false;
    }

    private <T> void setFieldToDefault(T object, Field field) throws IllegalAccessException {
        if (field.get(object) instanceof String)
            field.set(object, FieldFlag.DEFAULT.getStringValue());
        if (field.get(object) instanceof Integer)
            field.set(object, FieldFlag.DEFAULT.getIntValue());
    }

    private <T> boolean isUninitializedMandatoryValue(T value) {
        if (isObjectAvailable(value))
            return (value.equals(FieldFlag.MANDATORY.getIntValue()) || value.equals(FieldFlag.MANDATORY.getStringValue()));
        return false;
    }

    private <T> boolean isObjectAvailable(T object) {
        return object != null;
    }

    private void createResponseGeneral() {
        responseGeneral = new ResponseGeneralCreator().getEmptyObject(paymentInquiry.getRequestGeneral().getAcquirer().getId());
    }
}
