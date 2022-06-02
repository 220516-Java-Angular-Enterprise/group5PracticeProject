package com.revature.revit;

import com.revature.revit.daos.UserDAO;
import com.revature.revit.services.UserService;
import com.revature.revit.ui.StartMenu;

public class MainDriver {
    public static void main(String[] args) {

        new StartMenu(new UserService(new UserDAO())).start();
    }
}
