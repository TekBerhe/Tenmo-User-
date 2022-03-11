package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserService {
    ConsoleService consoleService = new ConsoleService();
    private String baseUrl = "http://localhost:8080";
    RestTemplate restTemplate = new RestTemplate();
    public UserService(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public List<User> userList(AuthenticatedUser user){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(user.getToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
      User[] currentUser = restTemplate.exchange(baseUrl +  "tenmo_user", HttpMethod.GET, entity, User[].class).getBody();
      return Arrays.asList(currentUser);
    }


}
