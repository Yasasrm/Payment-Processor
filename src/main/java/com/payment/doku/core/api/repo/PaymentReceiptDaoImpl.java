package com.payment.doku.core.api.repo;

import com.payment.doku.core.api.enums.NotificationStatus;
import com.payment.doku.core.api.exception.PaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Repository
public class PaymentReceiptDaoImpl implements PaymentReceiptDao {

    @Autowired
    EntityManager em;

    @Override
    public HashMap<String, String> generateReceiptAndGetDetails(String channel, String vapc, String invoiceNo, Integer amount) {
        return generateReceipt(amount, getStringParameterFromValues(channel, vapc, invoiceNo, amount));
    }

    private HashMap<String, String> generateReceipt(Integer amount, String paraList) {
        HashMap<String, String> receiptDetail = new HashMap<>();
        Query query = em.createNativeQuery("Select  client_refno , customerName, contractNo, receiptNo, Receiptdate,  Errflg,  Msg  From Table (Func_Genarate_Receipt_Service(?))");
        query.setParameter(1, paraList);
        List<Object[]> resultList = query.getResultList();

        for (Object[] obj : resultList) {
            if ("Y".equals("" + obj[5]))
                throw new PaymentException("" + obj[6]);
            receiptDetail.put("ReceiptNumber", (String) obj[3]);
            receiptDetail.put("ReceiptAmount", "" + amount);
        }
        return receiptDetail;
    }

    private String getStringParameterFromValues(String channel, String vapc, String invoiceNo, Integer amount) {
        HashMap<String, String> contractDetails = getContractNumberFromVirtualAccountPaymentCode(vapc);
        String paraList = "{" +
                "\"user\":\"" + channel + "\"," +
                "\"contractNo\":\"" + contractDetails.get("ContractNumber") + "\"," +
                "\"customerName\":\"" + contractDetails.get("LeseCode") + "\"," +
                "\"clientRefNoIn\":\"" + invoiceNo + "\"," +
                "\"valueDate\":\"" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("DD/MM/YYYY")) + "\"," +
                "\"amount\":\"" + amount + "\"" +
                "}";
        return paraList;
    }

    private HashMap<String, String> getContractNumberFromVirtualAccountPaymentCode(String vapc) {
        Query query = em.createNativeQuery("SELECT Lchd_Cont_No, Lchd_L_Code FROM Lchd WHERE Lchd_Vapc = ?1");
        query.setParameter(1, vapc);
        List<Object[]> resultList = query.getResultList();
        return getContractDetailsFromResultSet(resultList);
    }

    private HashMap<String, String> getContractDetailsFromResultSet(List<Object[]> resultList) {
        if (resultList == null || resultList.isEmpty())
            throw new PaymentException(NotificationStatus.INVALID_SIGNATURE);

        HashMap resultSet = new HashMap();
        for (Object[] obj : resultList) {
            resultSet.put("ContractNumber", getStringValueIfNotNull(obj[0]));
            resultSet.put("LeseCode", getStringValueIfNotNull(obj[1]));
        }
        return resultSet;
    }

    private String getStringValueIfNotNull(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
