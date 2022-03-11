package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    @Autowired
    private JdbcAccountDao jdbcAccountDao;
    private JdbcTemplate jdbcTemplate;
    private JdbcRequestDao jdbcRequestDao;
    @Autowired
    private JdbcUserDao jdbcUserDao;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void logTransaction(Request sendMoney, Long senderAccountId, Long receiverAccountId) {
        String logTransfer = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(
                logTransfer,
                sendMoney.getTransferType(),
                sendMoney.getTransferStatus(),
                senderAccountId,
                receiverAccountId,
                sendMoney.getAmount()
        );
    }

    @Override
    public void performSendTransfer(Request sendMoney) {
        Long senderId = jdbcUserDao.findIdByUsername(sendMoney.getSenderName());
        Long receiverId = jdbcUserDao.findIdByUsername(sendMoney.getReceiverName());
        Account fromAccount = jdbcAccountDao.getAccountByUserId(senderId);
        Account toAccount = jdbcAccountDao.getAccountByUserId(receiverId);
        sendMoney.setTransferType(2L);
        sendMoney.setTransferStatus(2L);

        //Logs rejected transaction
        if (fromAccount.getBalance().compareTo(sendMoney.getAmount())== -1){
            sendMoney.setTransferStatus(3L);
            logTransaction(sendMoney, fromAccount.getAccountId(), toAccount.getAccountId());


        } else {

            // Pulling Funds
            String senderSql = "UPDATE account SET balance = balance - ? WHERE account_id = ?;";
            String receiverSql = "UPDATE account SET balance = balance + ? WHERE account_id = ?;";
            jdbcTemplate.update(senderSql, sendMoney.getAmount(), fromAccount.getAccountId());
            jdbcTemplate.update(receiverSql, sendMoney.getAmount(), toAccount.getAccountId());

            //Logs completed transaction
            logTransaction(sendMoney, fromAccount.getAccountId(), toAccount.getAccountId());

        }


    }

    @Override
    public List<Transfer> getTransferById(Long userId) {
        return null;
    }


    private Transfer mapToTransfer (SqlRowSet rowSet){
        Transfer transfer = new Transfer();

        transfer.setAccountIdFrom(rowSet.getLong("account_from"));
        transfer.setAccountIdTo(rowSet.getLong("account_to"));
        transfer.setTransferAmount(rowSet.getBigDecimal("amount"));
        transfer.setTransferStatusId(rowSet.getInt("transfer_status_id"));
        transfer.setTransferTypeId(rowSet.getInt("transfer_type_id"));
        transfer.setTransferId(rowSet.getLong("transfer_id"));


        return transfer;
    }
}
