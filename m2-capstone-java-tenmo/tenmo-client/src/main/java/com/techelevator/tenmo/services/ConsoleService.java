package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Request;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.UserCredentials;

import javax.naming.Name;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }


    public void printTransactionDetails(Request request) {
        String transactionType = null;
        String transactionStatus = null;

        if(request.getTransferType() == 1){
            transactionType = "Request";
        } else if (request.getTransferType() == 2){
            transactionType = "Send";
        }

        if(request.getTransferStatus() == 1){
            transactionStatus = "Pending";
        } else if(request.getTransferStatus() == 2){
            transactionStatus = "Approved";
        }else if(request.getTransferStatus() == 3){
            transactionStatus = "Rejected";
        }

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println(" Transfer Details              ");
        System.out.println("-------------------------------------------");
        System.out.println(" ID: " + request.getTransactionId());
        System.out.println(" From: " + request.getSenderName());
        System.out.println(" To: " + request.getReceiverName());
        System.out.println(" Type: " + transactionType);
        System.out.println(" Status: " + request.getTransactionId());
        System.out.println(" Amount: $" + request.getAmount());
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public Long promptForLong(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter the ID of the person you'd like to make this transaction with:");;
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    public void printUsers(List<User> incomingList, Long logInId){
        System.out.println("-----------------------------------------------");
        System.out.println("Users");
        System.out.print("ID");
        System.out.println("          Name");
        System.out.println("-----------------------------------------------");
        for(User currentUser : incomingList) {
            if(currentUser.getId() != logInId) {
                System.out.println(currentUser.getId() + "        " + currentUser.getUsername());
            }
        }
        System.out.println("--------------------");

    }

    public void printTransactions (List<Request> incomingList) {
        System.out.println("-----------------------------------------------");
        System.out.println("Transfers");
        System.out.print("ID");
        System.out.println("          From/To           Amount");
        System.out.println("-----------------------------------------------");
        for(Request currentRequest : incomingList) {

            System.out.println(currentRequest.getTransactionId() + "        To: " + currentRequest.getReceiverName() +
                    "          $" + currentRequest.getAmount());

        }
        System.out.println("--------------------");
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

}
