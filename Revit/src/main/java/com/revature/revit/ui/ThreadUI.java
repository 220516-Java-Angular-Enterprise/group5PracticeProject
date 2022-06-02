package com.revature.revit.ui;
import com.revature.revit.models.Thread;

import com.revature.revit.daos.ThreadDAO;
import com.revature.revit.models.Users;
import com.revature.revit.services.CommentService;
import com.revature.revit.services.ThreadService;
import com.revature.revit.services.UserService;
import com.revature.revit.util.annotations.Inject;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class ThreadUI {

    @Inject
    private final Users user;
    private final UserService userService;
    ThreadService threadService;

    @Inject
    public ThreadUI(Users user, UserService userService) {
        this.user = user;
        this.userService = userService;
        threadService = new ThreadService(new ThreadDAO());
    }

    //CALL THIS FIRST!
    public void main(){
        Scanner mainScanner = new Scanner(System.in);
        boolean gettingCurrentField = true;
        String input = "";
        String nameOfThread;
        int choice; // index of thread.

        while (gettingCurrentField) {
            System.out.println("Threads: Select by number. [x] to exit. [c] to create thread:\n");
            ArrayList<Thread> threads = threadService.getAll(); //GET THREADS FROM THREAD SERVICE!
            for (int i = 0; i < threads.size(); i++) {
                System.out.println("[" + i + "]" + threads.get(i).getTitle());
            }
            System.out.print("Enter: ");
            input = mainScanner.nextLine();
            String numberRegex = "[0,9]+";

            if (input.equals("x")){
                return;
            }
            if (input.equals("c")){
                createNewThread();
                //gettingCurrentField = false; //Keeping true allows people to return to thread to look at other threads or make a new one.
            }
            else if (input.matches(numberRegex)){
                choice = Integer.parseInt(input);
                if (choice < threads.size()){
                    //gettingCurrentField = false;

                    new CommentUI(user, userService).main(threads.get(choice));

                }
                else{
                    System.out.println("Invalid input index!");
                }
            }


            else{
                System.out.println("Invalid input NAN!");
            }
        }

        //Open thread with post!

    }



    private void createNewThread(){
        Scanner mainScanner = new Scanner(System.in);
        boolean gettingCurrentField = true;
        String input = "";
        String nameOfThread = "";

        while (gettingCurrentField){
            System.out.println("Title of Thread ([x] to cancel)");
            input = mainScanner.nextLine();

            if (input.equals("x")){
                return;
            }
            else {
                nameOfThread = input;
                gettingCurrentField = false;
            }
        }

        //Save thread to database
        Thread newThread = new Thread();
        //(String id, String title, String user_id)
        newThread.setId(UUID.randomUUID().toString());
        newThread.setTitle(nameOfThread);
        newThread.setUser_id(user.getId());

        threadService.save(newThread);
        //Start comment with thread.
        new CommentUI(user, userService).main(newThread);

    }

}
