package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.CompletedTransfer;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
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

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void logTransaction(Transfer sendMoney, Long senderAccountId, Long receiverAccountId) {
        String logTransfer = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(
                logTransfer,
                sendMoney.getTransferTypeId(),
                sendMoney.getTransferStatusId(),
                senderAccountId,
                receiverAccountId,
                sendMoney.getTransferAmount()
        );
    }

    @Override
    public String performSendTransfer(Transfer sendMoney) {

        Account fromAccount = jdbcAccountDao.getAccountByUserId(sendMoney.getAccountIdFrom());
        Account toAccount = jdbcAccountDao.getAccountByUserId(sendMoney.getAccountIdTo());
        String s;
        sendMoney.setTransferStatusId(2);
        sendMoney.setTransferTypeId(2);

        //Logs rejected transaction
        if (fromAccount.getBalance().compareTo(sendMoney.getTransferAmount())== -1){
            sendMoney.setTransferStatusId(3);
            logTransaction(sendMoney, fromAccount.getAccountId(), toAccount.getAccountId());
            return s = "There is not enough money in your account to make this transaction";

        } else {

            // Pulling Funds
            String senderSql = "UPDATE account SET balance = balance - ? WHERE account_id = ?;";
            String receiverSql = "UPDATE account SET balance = balance + ? WHERE account_id = ?;";
            jdbcTemplate.update(senderSql, sendMoney.getTransferAmount(), fromAccount.getAccountId());
            jdbcTemplate.update(receiverSql, sendMoney.getTransferAmount(), toAccount.getAccountId());

            //Logs completed transaction
            logTransaction(sendMoney, fromAccount.getAccountId(), toAccount.getAccountId());

        }

        return s = "Transaction completed";
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
