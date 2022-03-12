package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Request;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRequestDao implements RequestDao {
    @Autowired
    private JdbcAccountDao jdbcAccountDao;
    @Autowired
    private JdbcTransferDao jdbcTransferDao;
    @Autowired JdbcUserDao jdbcUserDao;
    private JdbcTemplate jdbcTemplate;

    public JdbcRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public String performSendTransfer(Request sendMoney) {
        return null;
    }

    @Override
    public List<Request> listOfTransactionsById(Long userId) {
        Account account = jdbcAccountDao.getAccountByUserId(userId);
       List<Transfer> transferList = jdbcTransferDao.listOfTransactionsById(account.getAccountId());
       List<Request> requestList = new ArrayList<>();

       for (Transfer currentTransfer : transferList) {
               Request requestBuilder = new Request();

               String senderName = jdbcUserDao.findUsernameByAccountId(currentTransfer.getAccountIdFrom());
               String receiverName = jdbcUserDao.findUsernameByAccountId(currentTransfer.getAccountIdTo());

               requestBuilder.setTransactionId(currentTransfer.getTransferId());
               requestBuilder.setTransferType(currentTransfer.getTransferTypeId());
               requestBuilder.setTransferStatus(currentTransfer.getTransferStatusId());
               requestBuilder.setSenderName(senderName);
               requestBuilder.setReceiverName(receiverName);
               requestBuilder.setAmount(currentTransfer.getTransferAmount());

               requestList.add(requestBuilder);
           }
       return requestList;
    }


    private Request mapToRequest (SqlRowSet rowSet){
        Request request = new Request();

        request.setTransactionId(rowSet.getLong("transactionId"));
        request.setSenderName(rowSet.getString("senderName"));
        request.setReceiverName(rowSet.getString("receiverName"));
        request.setAmount(rowSet.getBigDecimal("amount"));
        request.setTransferStatus(rowSet.getLong("transferStatus"));
        request.setTransferType(rowSet.getLong("transferType"));
        return request;
    }
}
