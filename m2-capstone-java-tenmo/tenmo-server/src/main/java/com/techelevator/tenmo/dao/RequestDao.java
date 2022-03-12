package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Request;
import com.techelevator.tenmo.model.Transfer;

import java.util.List;

public interface RequestDao {
    String performSendTransfer(Request sendMoney);

    List<Request> listOfTransactionsById (Long userId);

}
