package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class CompletedTransfer {



    private BigDecimal transferAmount;
    private String senderUsername;
    private String receiverUsername;
    private int transferStatusId;
    private int transferTypeId;
    private Long transferId;

    public CompletedTransfer(BigDecimal transferAmount, int transferStatusId, int transferTypeId, Long transferId) {
        this.transferAmount = transferAmount;
        this.transferStatusId = transferStatusId;
        this.transferTypeId = transferTypeId;
        this.transferId = transferId;
    }
}
