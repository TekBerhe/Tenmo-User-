package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

public class AccountService {

    private String baseUrl = "http://localhost:8080";
    RestTemplate restTemplate = new RestTemplate();


    public AccountService (String baseUrl){
        this.baseUrl = baseUrl;
    }

    public Account getAccountByUserId (Long userId, AuthenticatedUser user){
        Account currentAccount = new Account();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(user.getToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);


        try {
          currentAccount =  restTemplate.exchange(baseUrl + "account/" + userId, HttpMethod.GET, entity, Account.class).getBody();
          return currentAccount;
        } catch (RestClientResponseException e){
            System.out.println("Request cannot be completed");
        } catch (ResourceAccessException ex) {
            System.out.println("Cannot find account associated with Id");
        }

        return currentAccount;
    }

}
