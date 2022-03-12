package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Request {

    private String senderName;
    private String receiverName;
    private BigDecimal amount;
    private Long transferType;
    private Long transferStatus;

    public Request(){}

    public Request(String senderName, String receiverName, BigDecimal amount, Long transferType, Long transferId) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.amount = amount;
        this.transferType = transferType;
        this.transferStatus = transferId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getTransferType() {
        return transferType;
    }

    public void setTransferType(Long transferType) {
        this.transferType = transferType;
    }

    public Long getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(Long transferStatus) {
        this.transferStatus = transferStatus;
    }

}
