package com.revature.revit.ui;

import java.util.ArrayList;
import java.util.Scanner;

public class ThreadUI {


    public void main(){
        Scanner mainScanner = new Scanner(System.in);
        boolean gettingCurrentField = true;
        String input = "";
        String nameOfThread;
        int choice; // index of thread.

        System.out.println("Threads: Select by number. [x] to exit. [c] to create thread:\n");
        while (gettingCurrentField) {
            ArrayList<Thread> threads; //GET THREADS FROM THREAD SERVICE!
            for (int i = 0; i < threads.size(); i++) {
                System.out.println("[" + i + "]" + threads.get(i).getTitle());
            }

            input = mainScanner.nextLine();
            String numberRegex = "\\d[0,9]";

            if (input.equals("x")){
                return;
            }
            if (input.equals("c")){
                createNewThread();
                gettingCurrentField = false;
            }
            else if (input.matches(numberRegex)){
                choice = Integer.parseInt(input);
                if (choice < threads.size()){
                    gettingCurrentField = false;

                }
                else{
                    System.out.println("Invalid input!");
                }
            }
            else{
                System.out.println("Invalid input!");
            }
        }

        //Open thread with post!

    }



    public void createNewThread(){
        Scanner mainScanner = new Scanner(System.in);
        boolean gettingCurrentField = true;
        String input = "";
        String nameOfThread;

        while (gettingCurrentField){
            System.out.println("Title of Post ([x] to cancel)");
            input = mainScanner.nextLine();

            if (input.equals("x")){
                return;
            }
            else {
                nameOfThread = input;
            }
        }

    }

}
