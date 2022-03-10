package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {

    private Long userId;
    private BigDecimal balance;
    private Long accountId;

    public Account (){};

    public Account(Long accountId, Long userId, BigDecimal balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String toString() {
        return "Account{" +
                "account_id=" + accountId +
                ", user_id=" + userId + '\'' +
                ", balance=" + balance + "}";
    }
}
