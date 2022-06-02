package com.revature.revit.ui;

import com.revature.revit.daos.UserDAO;
import com.revature.revit.models.Users;
import com.revature.revit.services.UserService;

import java.util.Scanner;
import java.util.UUID;
import com.revature.revit.util.annotations.Inject;
import com.revature.revit.util.customExceptions.InvalidUserException;


public class StartMenu extends IMenu{
    @Inject
    private final UserService userService;
    @Inject
    public StartMenu(UserService userService) {
        this.userService = userService;
    }

    /*
        First Function Called when you start up the Program.

        Displays some ASCII art and some flavor text for when the store opens.
        It then prompts the user to find out if they want to login/signup/ or exit.
     */
    @Override
    public void start() {

        // User Input:
        Scanner scan = new Scanner(System.in);

        // Display the Store Opening Text
        displayStartMessage();

        /*
            If it exists this break point then the user is leaving the Adventurer Store
            Will continue to loop through the start menu until either the user enters some
            valid input and moves on to a new menu, or they exit the program.
        */
        exit_store: {
            while (true) {

                displayLoginMessage();
                System.out.print("\nEnter: ");
                String input = scan.nextLine();
                System.out.println("\n------------------------------------------\n");
                switch(input) {
                    case "1":
                        // Go to the login method
                        login();
                        break;
                    case "2":
                        // Go to the Sign-up method
                        signup();
                        break;
                    case "x":
                        //Exits the Store
                        break exit_store;
                    default:
                        System.out.println("\nInvalid input.");
                        break;
                }
            }
        }
    }
    private void login() {
        /*
            Login function:
            Enter an advName and a password. Checks the Database to see if the
            Adventurer account actually exists
        */
        String username, password;
        Users user = new Users();
        Scanner scan = new Scanner(System.in);

        /*
            Loops through the Login Process until the user successfully logs into their account
            Checks if the AdvName/password combo exists within the database
            If the new Adventurer exists in the DB then it checks the usrRole to see if it's an ADMIN
         */

        while (true) {

            //Ask the input an Adventurer Name and password
            System.out.println("\nLogging in...");
            System.out.print("\nUser Name: ");
            username = scan.nextLine();
            System.out.print("\nPassword: ");
            password = scan.nextLine();
            System.out.println("\n------------------------------------------\n");

            try {
                user = userService.login(username, password);

                new MainMenu(user, new UserService(new UserDAO())).start();
                break;
            } catch (InvalidUserException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void signup() {

        /*
            Signup function:
            Enter a advName and a password. Make sure they are not already taken and
            make sure they meet the requirements for the advName and password.
        */
        String username, password;
        Scanner scan = new Scanner(System.in);

        /*
            Loops through the Sign-up Process until the user creates a successful account
            Checks if the AdvName/password is valid and has not already been used.
            Asks the user to confirm their credentials
            If the new Adventurer is validated then the user will be sent to the Main Menu
         */
        completeSignup:
        {
            while (true) {

                //Ask the user to create an Adventurer Name
                System.out.print("\nAdventurer Name: ");
                username = scan.nextLine();

                //Checks validity of the AdvName and makes sure it isn't already taken
                try {
                    if (userService.isValidUserName(username)) {
                        if (userService.isNotDuplicateUsername(username)) break;
                    }
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }


            //Asking and Checking the Password
            while (true) {
                System.out.print("\nPassword: ");
                password = scan.nextLine();

                try {
                    if (userService.isValidPassword(password)) {
                        /* Asking user to enter in password again. */
                        System.out.print("\nRe enter password again: ");
                        String confirm = scan.nextLine();

                        /* If the two password equals each other, break. Else re-enter password. */
                        if (password.equals(confirm)) break;
                        else System.out.println("Password does not match :(");
                    }
                } catch (InvalidUserException e) {
                    System.out.println(e.getMessage());
                }
            }

            confirmExit:
            {
                while (true) {
                    /* Asking user to confirm username and password. */
                    System.out.println("\nPlease confirm your credentials (y/n)");
                    System.out.println("\nUsername: " + username);
                    System.out.println("Password: " + password);

                    System.out.print("\nEnter: ");
                    String input = scan.nextLine();
                    System.out.println("\n------------------------------------------\n");

                    /* Switch statement for user input. Basically yes or no. */
                    switch (input) {
                        case "y":
                            /* If yes, we instantiate a User object to store all the information into it. */
                            Users user = new Users(UUID.randomUUID().toString(), username, password);
                            userService.register(user);

                            /* Calling the anonymous class MainMenu.start() to navigate to the main menu screen. */
                            /* We are also passing in a user object, so we know who is logged in. */
                            new MainMenu(adv, new UserService(new AdvDAO()), new ItemService(new ItemDAO()), new StoreService(new StoreDAO()), new OrderService(new OrderDAO()), new OrderHistoryService(new OrderHistoryDAO())).start();;

                            /* Break out of the entire loop. */
                            break completeSignup;
                        case "n":
                            /* Re-enter in credentials again. */
                            break confirmExit;
                        default:
                            System.out.println("Invalid Input.");
                            break;
                    }
                }
            }

        }
    }
    private void displayStartMessage() {
        System.out.println("Welcome to the best Fantasy Adventure store in all the realms!");
        System.out.println("Here you can find anything you may need on your adventures; whether that be");
        System.out.println("weapons, armour, traps, potions, food, or magical books and scrolls.");
        System.out.println("We have all that and more and you won't find a better deal on your adventuring gear than here!");
    }

    private void displayLoginMessage() {
        System.out.println("[1] Login");
        System.out.println("[2] Signup");
        System.out.println("[x] Exit");
}
