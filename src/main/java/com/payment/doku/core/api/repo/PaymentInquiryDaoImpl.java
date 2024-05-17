package com.payment.doku.core.api.repo;

import com.payment.doku.core.api.domain.DokuInvoice;
import com.payment.doku.core.api.domain.DokuInvoice_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class PaymentInquiryDaoImpl implements PaymentInquiryDao {

    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public String getNextInvoiceId() {
        Query query = em.createNativeQuery("SELECT doku_invoice_seq.NEXTVAL FROM dual");
        return query.getSingleResult().toString();
    }

    @Override
    public HashMap<String, String> getPaymentInformation(String virtualAccountPaymentCode) {
        Query query = em.createNativeQuery("SELECT SUM (Letx_Bal)," +
                "         Lese_Tit || ' ' || Lese_Full_Name," +
                "         Lese_Email," +
                "         Lese_Tel1," +
                "         Lese_Tel2," +
                "         Lchd_Cont_No," +
                "         Lchd_Vapc" +
                "    FROM Lchd, Lese, Letx" +
                "   WHERE     Lchd_L_Code = Lese_Code" +
                "         AND Lchd_Cont_No = Letx_Cont_No" +
                "         AND Lchd_Status >= '0720'" +
                "         AND Lchd_Status <> '0730'" +
                "         AND Lchd_Status <= '0770'" +
                "         AND NVL (Lchd_Wtof, 'N') = 'N'" +
                "         AND Letx_Date <= TRUNC (SYSDATE)" +
                "         AND Letx_Type IN ('D', 'G', 'D3', 'G3')" +
                "         AND Lchd_Vapc = ?" +
                "GROUP BY Lese_Tit || ' ' || Lese_Full_Name," +
                "         Lese_Email," +
                "         Lese_Tel1," +
                "         Lese_Tel2," +
                "         Lchd_Cont_No," +
                "         Lchd_Vapc");
        query.setParameter(1, virtualAccountPaymentCode);
        List<Object[]> resultList = query.getResultList();
        return getInquiryResultSet(resultList);
    }

    @Override
    public HashMap<String, String> getInvoiceInformation(String invoiceNumber) {
        return getInvoiceResultSet(getDokuInvoice(invoiceNumber));
    }

    private HashMap getInvoiceResultSet(DokuInvoice dokuInvoice) {
        HashMap resultSet = new HashMap();
        resultSet.put("InvoiceNumber", dokuInvoice != null ? dokuInvoice.getInvoiceNumber() : null);
        resultSet.put("Service", dokuInvoice != null ? dokuInvoice.getService() : null);
        resultSet.put("Acquirer", dokuInvoice != null ? dokuInvoice.getAcquirer() : null);
        resultSet.put("Channel", dokuInvoice != null ? dokuInvoice.getChannel() : null);
        resultSet.put("Vapc", dokuInvoice != null ? dokuInvoice.getVapc() : null);
        resultSet.put("InvoiceAmount", dokuInvoice != null ? dokuInvoice.getInvoiceAmount() : null);
        resultSet.put("ContractNumber", dokuInvoice != null ? dokuInvoice.getContractNumber() : null);
        resultSet.put("ReceiptNumber", dokuInvoice != null ? dokuInvoice.getReceiptNumber() : null);
        return resultSet;
    }

    private DokuInvoice getDokuInvoice(String invoiceNumber) {
        DokuInvoice dokuInvoice = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<DokuInvoice> cq = cb.createQuery(DokuInvoice.class);
            Root<DokuInvoice> dokuInvoiceRoot = cq.from(DokuInvoice.class);
            List<Predicate> conditions = new ArrayList<Predicate>();
            conditions.add(cb.equal(dokuInvoiceRoot.get(DokuInvoice_.invoiceNumber), invoiceNumber));
            cq.select(dokuInvoiceRoot).where(conditions.toArray(new Predicate[]{}));
            dokuInvoice = em.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dokuInvoice;
    }

    private HashMap getInquiryResultSet(List<Object[]> resultList) {

        HashMap resultSet = new HashMap();
        if (resultList.size() == 0) {
            resultSet.put("InquiryStatus", "billing_not_found");
            return resultSet;
        }

        if (resultList.size() > 1) {
            resultSet.put("InquiryStatus", "invalid_signature");
            return resultSet;
        }

        for (Object[] obj : resultList) {

            String amount = getStringValueIfNotNull(obj[0]);
            if (Integer.parseInt(amount) == 0) {
                resultSet.put("InquiryStatus", "billing_already_paid");
                return resultSet;
            }

            if (getStringValueIfNotNull(obj[6]) == null) {
                resultSet.put("InquiryStatus", "invalid_signature");
                return resultSet;
            }

            resultSet.put("Amount", amount);
            resultSet.put("CustomerName", getStringValueIfNotNull(obj[1]));
            resultSet.put("CustomerEmail", getStringValueIfNotNull(obj[2]));
            resultSet.put("CustomerPhoneNumber", getNotNullStringIfPossible(obj[3], obj[4]));
            resultSet.put("ContractNumber", getStringValueIfNotNull(obj[5]));
            resultSet.put("InquiryStatus", "success");
        }
        return resultSet;
    }

    private String getNotNullStringIfPossible(Object obj1, Object obj2) {
        String value = getStringValueIfNotNull(obj1);
        if (value == null)
            value = getStringValueIfNotNull(obj2);
        return value;
    }

    private String getStringValueIfNotNull(Object obj) {
        return obj == null ? "" : obj.toString();
    }
}
