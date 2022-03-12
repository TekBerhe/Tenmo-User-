package com.techelevator.tenmo;


import com.techelevator.tenmo.model.*;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.services.*;

import java.math.BigDecimal;
import java.util.List;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final RequestService requestService = new RequestService(API_BASE_URL);
    private final ConsoleService consoleService = new ConsoleService();
    private final AccountService accountService = new AccountService(API_BASE_URL);
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final UserService userService = new UserService(API_BASE_URL);
    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {

        Long userId =  currentUser.getUser().getId();
       Account currentAccount =  accountService.getAccountByUserId(userId, currentUser);
        BigDecimal currentBalance = currentAccount.getBalance();
        System.out.println("Your current balance is: $" + currentBalance);

	}


	private void viewTransferHistory() {
		// TODO Auto-generated method stub
		
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
        BigDecimal amount = null;
        Long enteredId = null;
        Long userId = currentUser.getUser().getId();
		consoleService.printUsers(userService.userList(currentUser),userId);
        while (enteredId == null) {
            enteredId = consoleService.promptForLong("Please enter the ID of the person you'd like to make this transaction with: ");
            if (!enteredId.equals(userId)) {
              amount =  consoleService.promptForBigDecimal("Please enter a transferable amount: ");
                break;
            } else System.out.println("You cannot send yourself money.");
        }

        String receivingUser = "";
        List<User> userList = userService.userList(currentUser);
        for (User currentUser : userList){
            if (currentUser.getId().equals(enteredId)) {
                receivingUser = currentUser.getUsername();
            } else consoleService.printMainMenu();
        }

        requestService.sendMoneyRequest(currentUser, receivingUser, amount);
	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

}
