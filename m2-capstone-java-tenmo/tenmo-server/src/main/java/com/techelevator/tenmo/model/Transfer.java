package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {

   private BigDecimal transferAmount;
   private Long accountIdTo;
   private Long accountIdFrom;
   private int transferStatusId;
   private int transferTypeId;
   private Long transferId;

    public Transfer(BigDecimal transferAmount, Long accountIdTo, Long accountIdFrom) {
        this.transferAmount = transferAmount;
        this.accountIdTo = accountIdTo;
        this.accountIdFrom = accountIdFrom;

    }

    public Transfer() {
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Long getAccountIdTo() {
        return accountIdTo;
    }

    public void setAccountIdTo(Long accountIdTo) {
        this.accountIdTo = accountIdTo;
    }

    public Long getAccountIdFrom() {
        return accountIdFrom;
    }

    public void setAccountIdFrom(Long accountIdFrom) {
        this.accountIdFrom = accountIdFrom;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }
}
