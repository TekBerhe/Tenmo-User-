package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Request;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.util.List;

public interface TransferDao {

    void logTransaction(Request sendMoney, Long senderAccountId, Long receiverAccountId);

    void performSendTransfer(Request sendMoney);

    List<Transfer> getTransferById (Long userId);
}
