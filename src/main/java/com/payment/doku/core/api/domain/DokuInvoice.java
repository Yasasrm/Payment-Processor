package com.payment.doku.core.api.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Doku_Invoice")
@NamedQuery(name = "di.findRecord", query = "SELECT d FROM DokuInvoice d WHERE d.invoiceNumber = ?1")
public class DokuInvoice {
    @Id
    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;
    @Column(name = "SERVICE")
    private String service;
    @Column(name = "ACQUIRER")
    private String acquirer;
    @Column(name = "CHANNEL")
    private String channel;
    @Column(name = "VAPC")
    private String vapc;
    @Column(name = "PAYMENT_REQUEST")
    private String paymentRequest;
    @Column(name = "INVOICE_AMOUNT")
    private Integer invoiceAmount;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "CUSTOMER_EMAIL")
    private String customerEmail;
    @Column(name = "CUSTOMER_TEL")
    private String customerTel;
    @Column(name = "CONTRACT_NUMBER")
    private String contractNumber;
    @Column(name = "INQUIRY_STATUS")
    private String inquiryStatus;
    @Column(name = "RECEIPT_NUMBER")
    private String receiptNumber;
    @Column(name = "RECEIPT_AMOUNT")
    private String receiptAmount;
    @Column(name = "TRANSACTION_TIME")
    private Date transactionTime;
    @Column(name = "PAYMENT_NOTIFICATION")
    private String paymentNotification;
    @Column(name = "C_USER")
    private String cUser;
    @Column(name = "C_DATE")
    private Date cDate;
    @Column(name = "M_USER")
    private String mUser;
    @Column(name = "M_DATE")
    private Date mDate;
    @Column(name = "VERSION")
    private Integer version;
}
