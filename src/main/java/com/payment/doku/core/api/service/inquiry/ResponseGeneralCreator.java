package com.payment.doku.core.api.service.inquiry;

import com.payment.doku.core.api.enums.FieldFlag;
import com.payment.doku.core.api.util.response.inquiry.success.*;
import com.payment.doku.core.api.util.response.inquiry.success.*;

import java.util.ArrayList;
import java.util.List;

public class ResponseGeneralCreator {

    private ResponseGeneral responseGeneral;

    public ResponseGeneral getEmptyObject(String acquirer) {
        responseGeneral = new ResponseGeneral();
        responseGeneral.setOrder(getOrder(acquirer));
        responseGeneral.setCustomer(getCustomer(acquirer));
        responseGeneral.setAdditionalInfo(getAdditionalInfo(acquirer));
        responseGeneral.setVirtualAccountInfo(getVirtualAccountInfo(acquirer));
        responseGeneral.setVirtualAccountInquiry(getVirtualAccountInquiry(acquirer));
        responseGeneral.setOnlineToOfflineInfo(getOnlineToOfflineInfo(acquirer));
        responseGeneral.setOnlineToOfflineInquiry(getOnlineToOfflineInquiry(acquirer));
        return new AcquirerAnnotationProcessor<ResponseGeneral>(responseGeneral, acquirer).getProcessedObject();
    }

    private OnlineToOfflineInquiryInqRs getOnlineToOfflineInquiry(String acquirer) {
        OnlineToOfflineInquiryInqRs onlineToOfflineInquiry = new OnlineToOfflineInquiryInqRs();
        onlineToOfflineInquiry.setStatus(FieldFlag.DEFAULT.getStringValue());
        return new AcquirerAnnotationProcessor<OnlineToOfflineInquiryInqRs>(onlineToOfflineInquiry, acquirer).getProcessedObject();
    }

    private OnlineToOfflineInfoInqRs getOnlineToOfflineInfo(String acquirer) {
        OnlineToOfflineInfoInqRs onlineToOfflineInfo = new OnlineToOfflineInfoInqRs();
        onlineToOfflineInfo.setPaymentCode(FieldFlag.DEFAULT.getStringValue());
        onlineToOfflineInfo.setInfo1(FieldFlag.DEFAULT.getStringValue());
        return new AcquirerAnnotationProcessor<OnlineToOfflineInfoInqRs>(onlineToOfflineInfo, acquirer).getProcessedObject();
    }

    private VirtualAccountInquiryInqRs getVirtualAccountInquiry(String acquirer) {
        VirtualAccountInquiryInqRs virtualAccountInquiry = new VirtualAccountInquiryInqRs();
        virtualAccountInquiry.setStatus(FieldFlag.DEFAULT.getStringValue());
        return new AcquirerAnnotationProcessor<VirtualAccountInquiryInqRs>(virtualAccountInquiry, acquirer).getProcessedObject();
    }

    private VirtualAccountInfoInqRs getVirtualAccountInfo(String acquirer) {
        VirtualAccountInfoInqRs virtualAccountInfo = new VirtualAccountInfoInqRs();
        virtualAccountInfo.setVirtualAccountNumber(FieldFlag.DEFAULT.getStringValue());
        virtualAccountInfo.setInfo1(FieldFlag.DEFAULT.getStringValue());
        virtualAccountInfo.setInfo2(FieldFlag.DEFAULT.getStringValue());
        virtualAccountInfo.setInfo3(FieldFlag.DEFAULT.getStringValue());
        virtualAccountInfo.setInfo4(FieldFlag.DEFAULT.getStringValue());
        virtualAccountInfo.setInfo5(FieldFlag.DEFAULT.getStringValue());
        virtualAccountInfo.setBillingType(FieldFlag.DEFAULT.getStringValue());
        virtualAccountInfo.setRefInfo(getRefInfoList());
        virtualAccountInfo.setMerchantUniqueReference(FieldFlag.DEFAULT.getStringValue());
        virtualAccountInfo.setInfo(FieldFlag.DEFAULT.getStringValue());
        virtualAccountInfo.setIdentifier(getIdentifiers());
        return new AcquirerAnnotationProcessor<VirtualAccountInfoInqRs>(virtualAccountInfo, acquirer).getProcessedObject();
    }

    private List<IdentifierInqRs> getIdentifiers() {
        List<IdentifierInqRs> identifierRsList = new ArrayList<>();
        IdentifierInqRs identifierRs = new IdentifierInqRs();
        identifierRs.setName(FieldFlag.DEFAULT.getStringValue());
        identifierRs.setValue(FieldFlag.DEFAULT.getStringValue());
        identifierRsList.add(identifierRs);
        return identifierRsList;
    }

    private List<RefInfoInqRs> getRefInfoList() {
        List<RefInfoInqRs> refInfoList = new ArrayList<>();
        RefInfoInqRs refInfo1 = new RefInfoInqRs();
        refInfo1.setRefName(FieldFlag.DEFAULT.getStringValue());
        refInfo1.setRefValue(FieldFlag.DEFAULT.getStringValue());
        RefInfoInqRs refInfo2 = new RefInfoInqRs();
        refInfo2.setRefName(FieldFlag.DEFAULT.getStringValue());
        refInfo2.setRefValue(FieldFlag.DEFAULT.getStringValue());
        refInfoList.add(refInfo1);
        refInfoList.add(refInfo2);
        return refInfoList;
    }

    private AdditionalInfoInqRs getAdditionalInfo(String acquirer) {
        AdditionalInfoInqRs additionalInfo = new AdditionalInfoInqRs();
        additionalInfo.setAddlLabel1(FieldFlag.DEFAULT.getStringValue());
        additionalInfo.setAddlLabel2(FieldFlag.DEFAULT.getStringValue());
        additionalInfo.setAddlLabel3(FieldFlag.DEFAULT.getStringValue());
        additionalInfo.setAddlValue1(FieldFlag.DEFAULT.getStringValue());
        additionalInfo.setAddlValue2(FieldFlag.DEFAULT.getStringValue());
        additionalInfo.setAddlValue3(FieldFlag.DEFAULT.getStringValue());
        additionalInfo.setAddlLabel1En(FieldFlag.DEFAULT.getStringValue());
        additionalInfo.setAddlLabel2En(FieldFlag.DEFAULT.getStringValue());
        additionalInfo.setAddlLabel3En(FieldFlag.DEFAULT.getStringValue());
        additionalInfo.setAddlValue1En(FieldFlag.DEFAULT.getStringValue());
        additionalInfo.setAddlValue2En(FieldFlag.DEFAULT.getStringValue());
        additionalInfo.setAddlValue3En(FieldFlag.DEFAULT.getStringValue());
        return new AcquirerAnnotationProcessor<AdditionalInfoInqRs>(additionalInfo, acquirer).getProcessedObject();
    }

    private CustomerInqRs getCustomer(String acquirer) {
        CustomerInqRs customer = new CustomerInqRs();
        customer.setEmail(FieldFlag.DEFAULT.getStringValue());
        customer.setName(FieldFlag.DEFAULT.getStringValue());
        customer.setPhone(FieldFlag.DEFAULT.getStringValue());
        return new AcquirerAnnotationProcessor<CustomerInqRs>(customer, acquirer).getProcessedObject();
    }

    private OrderInqRs getOrder(String acquirer) {
        OrderInqRs order = new OrderInqRs();
        order.setInvoiceNumber(FieldFlag.DEFAULT.getStringValue());
        order.setAmount(FieldFlag.DEFAULT.getIntValue());
        return new AcquirerAnnotationProcessor<OrderInqRs>(order, acquirer).getProcessedObject();
    }
}
