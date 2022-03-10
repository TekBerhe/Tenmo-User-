package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao{


    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

   @Override
   public Account getAccountByUserId (Long userId) {

       Account userAccount = null;
       String sql = "SELECT account_id, user_id, balance " +
               "FROM account " +
               "WHERE user_id = ?; ";
      SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
      while(rowSet.next()) {
          userAccount = mapRowToAccount(rowSet);
      }
       return userAccount;
   }


    private Account mapRowToAccount (SqlRowSet rowSet){
       Account account = new Account();

       account.setAccountId(rowSet.getLong("account_id"));
       account.setBalance(rowSet.getBigDecimal("balance"));
       account.setUserId(rowSet.getLong("user_id"));

        return account;

   }
}

