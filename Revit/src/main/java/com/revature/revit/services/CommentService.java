package com.revature.revit.services;
import com.revature.revit.models.Comments;
import com.revature.revit.models.Thread;

import com.revature.revit.daos.CommentDAO;
import com.revature.revit.daos.UserDAO;
import com.revature.revit.util.annotations.Inject;

import java.util.ArrayList;

public class CommentService {

    @Inject
    private final CommentDAO commentDAO;

    @Inject
    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    //getAllCommentsByThreadID
    public ArrayList<Comments> getAllCommentsByThreadID(Thread thread){
        return commentDAO.getAllCommentsByThreadId(thread);
    }

    //SaveComments
    public void saveComment(Comments commentToSave){
        commentDAO.save(commentToSave);
    }
}
