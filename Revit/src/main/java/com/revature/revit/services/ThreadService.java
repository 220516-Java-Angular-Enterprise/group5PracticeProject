package com.revature.revit.services;

import com.revature.revit.daos.ThreadDAO;
import com.revature.revit.models.Thread;

import java.util.ArrayList;
import java.util.List;


public class ThreadService {

    //inject ThreadDAO dependency
    private final ThreadDAO threadDAO;
    public ThreadService (ThreadDAO threadDAO){this.threadDAO=threadDAO;}

    public ArrayList<Thread> getAll() {
        ArrayList<Thread> outList = new ArrayList<>();
        outList = threadDAO.getAll();
        //threadDAO returns null if it encounters an exception.
        if (!(outList.equals(null))){
            return outList;
        } else {System.out.println("Failed to get all threads."); return null;}

    }

    public void save(Thread threadToSave){
        threadDAO.save(threadToSave);
    }

    public void updateTitle(Thread threadToUpdate){
        threadDAO.update(threadToUpdate);
    }

}
