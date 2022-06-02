package com.revature.revit.daos;

import com.revature.revit.models.Comments;
import com.revature.revit.models.Users;
import com.revature.revit.models.Thread;
import com.revature.revit.util.DatabaseConnection;

import javax.xml.stream.events.Comment;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO implements CrudDAO<Comments>{

    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Comments obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO comments (id, message, time, thread_id, user_id) VALUES (?,?,?,?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getPostContent());
            ps.setString(3, obj.getTime());
            ps.setString(4, obj.getThread_id());
            ps.setString(5, obj.getUser_id());
            ps.executeUpdate();

        } catch (
                SQLException e) {
            throw new RuntimeException("An error occurred when saving to the database.");
        }
    }

    @Override
    public void update(Comments obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Comments getById(String id) {

        Comments comment = new Comments();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM comments where id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                comment.setId(rs.getString("id"));
                comment.setPostContent(rs.getString("message"));
                comment.setTime(rs.getString("time"));
                comment.setThread_id(rs.getString("thread_id"));
                comment.setUser_id(rs.getString("user_id"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comment;
    }

    @Override
    public List<Comments> getAll() {
        return null;
    }


    public ArrayList<Comments> getAllCommentsByThreadId(Thread thread) {
        ArrayList<Comments> comments = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM comments where thread_id = ?");
            ps.setString(1, String.valueOf(thread.getId()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Comments comment = new Comments();
                comment.setId(rs.getString("id"));
                comment.setPostContent(rs.getString("message"));
                comment.setTime(rs.getString("time"));
                comment.setThread_id(rs.getString("thread_id"));
                comment.setUser_id(rs.getString("user_id"));

                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return comments;
    }
}
