package com.revature.revit.ui;
import com.revature.revit.daos.CommentDAO;
import com.revature.revit.daos.UserDAO;
import com.revature.revit.models.Thread;

import com.revature.revit.models.Comments;
import com.revature.revit.models.Users;
import com.revature.revit.services.CommentService;
import com.revature.revit.services.UserService;
import com.revature.revit.util.annotations.Inject;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;


//Scanner mainScanner = new Scanner(System.in);
//            input = mainScanner.nextLine();
public class CommentUI {

    @Inject
    private final Users user;
    private final UserService userService;

    @Inject
    public CommentUI(Users user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }


    ArrayList<Comments> comments = new ArrayList<Comments>();
    Thread thread;

    public void main(Thread newThread){
        thread = newThread;
        boolean gettingCurrentField = true;


        while(gettingCurrentField) {
            displayPastThreadComments();
            System.out.println("\n\n");
            System.out.println("Add new comment: [a] \nReturn to index: [x]");
            System.out.print("Enter: ");
            Scanner mainScanner = new Scanner(System.in);
            String input = mainScanner.nextLine();

            if (input.equals("x")) {
                return;
            } else if (input.equals("a")) {
                addNewComment(user, newThread);
            } else {
                System.out.println("Invalid input!");
            }
        }



    }

    private void addNewComment(Users user, Thread newThread) {
        System.out.print("Enter Comment: " );
        Scanner scan = new Scanner(System.in);

        String comment = scan.nextLine();
        Timestamp time = Timestamp.from(Instant.now());
        Comments commentObj = new Comments(UUID.randomUUID().toString(), newThread.getId(), user.getId(), comment, time.toString());
        new CommentService(new CommentDAO()).saveComment(commentObj);
    }

    private void displayPastThreadComments(){

        //FINISH!!
        comments = new CommentService(new CommentDAO()).getAllCommentsByThreadID(thread);

        UserService uService = new UserService(new UserDAO());

        //GET COMMENTS!!


        System.out.println("\n\n\n\n" + thread.getTitle() + "\n\n");

        for(Comments c : comments){
            String userThatPosted = "";
            Users curUser = uService.getUserByID(c.getUser_id());
            if (curUser.equals(null)){
                userThatPosted = "NULL USERNAME!";
            }
            else{
                userThatPosted = curUser.getUsername();
            }
            System.out.println(userThatPosted + ":     " + c.getPostContent());
        }

    }

}
