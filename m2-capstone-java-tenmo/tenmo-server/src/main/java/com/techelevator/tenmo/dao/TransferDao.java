package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.util.List;

public interface TransferDao {

    void logTransaction(Transfer sendMoney, Long senderAccountId, Long receiverAccountId);

    String performSendTransfer(Transfer sendMoney);

    List<Transfer> getTransferById (Long userId);
}
