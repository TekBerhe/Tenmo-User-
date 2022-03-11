package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Request;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
@Component
public class JdbcRequestDao implements RequestDao {
    @Autowired
    private JdbcAccountDao jdbcAccountDao;
    private JdbcTemplate jdbcTemplate;

    public JdbcRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public String performSendTransfer(Request sendMoney) {
        return null;
    }



    private Request mapToRequest (SqlRowSet rowSet){
        Request request = new Request();

        request.setSenderName(rowSet.getString("senderName"));
        request.setReceiverName(rowSet.getString("receiverName"));
        request.setAmount(rowSet.getBigDecimal("amount"));
        request.setTransferStatus(rowSet.getLong("transferStatus"));
        request.setTransferType(rowSet.getLong("transferType"));
        return request;
    }
}
