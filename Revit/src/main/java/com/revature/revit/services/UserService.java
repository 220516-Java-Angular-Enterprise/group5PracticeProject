package com.revature.revit.services;

import com.revature.revit.daos.UserDAO;
import com.revature.revit.models.Users;

import java.util.List;

public class UserService {

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

    // Runs the AdvName through a Regex to check its complexity
    public boolean isValidAdvName(String advName) {
        if (advName.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) return true;
        throw new InvalidUserException("Invalid username. Username needs to be 8-20 characters long.");
    }


    // Checks through our database to see if the advName has already been taken by another Adventurer
    public boolean isNotDuplicateUsername(String advName) {
        List<String> advNames = AdvDAO.getAllAdvNames();
        if (advNames.contains(advName)) throw new InvalidUserException("Username is already taken :(");
        return true;
    }


    // Runs the password through a Regex to check its complexity
    public boolean isValidPassword(String password) {
        if(password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) return true;
        throw new InvalidUserException("Invalid username. Username needs to be 8-20 characters long.");
    }



    // Checks the Adventurer Object to see if both the AdvName and the Password are both valid.
    public Users isValidCredentials(Users user) {
        if (adv.getAdvName() == null && adv.getPassword() == null)
            throw new InvalidUserException("Incorrect username and password.");
        else if (adv.getAdvName() == null) throw new InvalidUserException("Incorrect username.");
        else if (adv.getPassword() == null) throw new InvalidUserException("Incorrect password.");

        return adv;
    }
}
