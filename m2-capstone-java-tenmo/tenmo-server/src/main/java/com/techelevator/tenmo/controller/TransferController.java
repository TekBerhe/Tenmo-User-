package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.RequestDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.BasicUser;
import com.techelevator.tenmo.model.Request;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class TransferController {
    ///handles requests
    private TransferDao transferDao;
    private AccountDao accountDao;
    private UserDao userDao;
    private RequestDao requestDao;

    public TransferController(TransferDao transferDao, AccountDao accountDao, UserDao userDao, RequestDao requestDao) {
        this.transferDao = transferDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.requestDao = requestDao;
    }
    @RequestMapping(path = "/account/transfer", method = RequestMethod.POST)
    public void sendMoney(@RequestBody Request transfer){
        transferDao.performSendTransfer(transfer);
    }

    @RequestMapping(path = "/account/transfer/history", method = RequestMethod.GET)
    public List<Request> transactionList(Principal principal) {

      Long id = userDao.findIdByUsername(principal.getName());
      return requestDao.listOfTransactionsById(id);

    }
}
