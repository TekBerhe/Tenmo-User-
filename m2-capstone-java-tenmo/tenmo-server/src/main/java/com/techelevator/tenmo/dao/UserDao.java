package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao {

    String findUsernameByAccountId (Long accountId);

    List<User> findAll();

    User findByUsername(String username);

    Long findIdByUsername(String username);

    BigDecimal getAccountBalance (int userId);

    boolean create(String username, String password);
}
