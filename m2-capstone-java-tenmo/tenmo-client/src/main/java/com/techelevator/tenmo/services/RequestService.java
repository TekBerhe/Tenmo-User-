package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Request;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

public class RequestService {

    @Autowired
    private UserService userService;

    private String baseUrl = "http://localhost:8080";
    RestTemplate restTemplate = new RestTemplate();

    public RequestService(String baseUrl) {this.baseUrl = baseUrl;}

    public void sendMoneyRequest (AuthenticatedUser user, String receivingUser, BigDecimal amount) {
        Request currentRequest = new Request();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(user.getToken());
        HttpEntity<Request> entity = new HttpEntity<Request>(currentRequest, headers);

        currentRequest.setSenderName(user.getUser().getUsername());
        currentRequest.setReceiverName(receivingUser);
        currentRequest.setAmount(amount);
        currentRequest.setTransferStatus(1L);
        currentRequest.setTransferType(2L);

        try {
           restTemplate.exchange(baseUrl + "/account/transfer",HttpMethod.POST,entity,Request.class);
        } catch (RestClientResponseException e){
            System.out.println("Cannot perform transaction at this time");
        }
    }
}
