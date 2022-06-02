package com.revature.revit.services;

import com.revature.revit.daos.UserDAO;
import com.revature.revit.models.Users;
import com.revature.revit.util.annotations.Inject;
import com.revature.revit.util.customExceptions.InvalidUserException;

import java.util.List;

public class UserService {

    @Inject
    private final UserDAO userDAO;

    @Inject
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Users login(String username, String password) {
        /* List<User> users = new ArrayList<>() */
        /* users = userDAO.getAll() */

        Users user = new Users();
        List<Users> users = UserDAO.getAll();

        for (Users u : users) {
            if (u.getUsername().equals(username)) {
                user.setId(u.getId());
                user.setUsername(u.getUsername());
                if (u.getPassword().equals(password)) {
                    user.setPassword(u.getPassword());
                    break;
                }
            }
            if (u.getPassword().equals(password)) {
                user.setPassword(u.getPassword());
            }
        }

        return isValidCredentials(user);
    }

    public void register(Users user) {
        userDAO.save(user);
    }

    // Runs the AdvName through a Regex to check its complexity
    public boolean isValidUserName(String username) {
        if (username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) return true;
        throw new InvalidUserException("Invalid username. Username needs to be 8-20 characters long.");
    }


    // Checks through our database to see if the advName has already been taken by another Adventurer
    public boolean isNotDuplicateUsername(String advName) {
        List<String> UserNames = UserDAO.getAllUserNames();
        if (UserNames.contains(advName)) throw new InvalidUserException("Username is already taken :(");
        return true;
    }


    // Runs the password through a Regex to check its complexity
    public boolean isValidPassword(String password) {
        if(password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) return true;
        throw new InvalidUserException("Invalid username. Username needs to be 8-20 characters long.");
    }



    // Checks the Adventurer Object to see if both the AdvName and the Password are both valid.
    public Users isValidCredentials(Users user) {
        if (user.getUsername() == null && user.getPassword() == null)
            throw new InvalidUserException("Incorrect username and password.");
        else if (user.getUsername() == null) throw new InvalidUserException("Incorrect username.");
        else if (user.getPassword() == null) throw new InvalidUserException("Incorrect password.");

        return user;
    }
}
