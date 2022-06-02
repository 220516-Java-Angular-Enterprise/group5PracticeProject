package com.revature.revit.daos;

import com.revature.revit.models.Thread;
import com.revature.revit.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThreadDAO implements CrudDAO<Thread>{

    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Thread obj) {
        try{
            //preparedStatement objects are used to generate and execute lines of SQL script
            PreparedStatement ps = con.prepareStatement("INSERT INTO threads (id, title, user_id) VALUES (?, ?, ?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getTitle());
            ps.setString(3, obj.getUser_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Database query failed");
        }
    }

    @Override
    public void update(Thread thread) {
        try {
            PreparedStatement ps = con.prepareStatement("Update threads SET title = ? WHERE id = ?");
            ps.setString(1, thread.getTitle());
            ps.setString(2, thread.getUser_id());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Failed to update thread in database.");
        }
    }

    @Override
    public void delete(String id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM threads WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete thread from database.");
        }
    }

    @Override
    public Thread getById(String id) {
        Thread thread = new Thread();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM threads where id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                thread = new Thread(rs.getString("id"), rs.getString("title"), rs.getString("user_id"));
            }
        } catch (SQLException e) {
            System.out.println("Database query failed");
            return null;
        }
        return thread;
    }

    @Override
    public ArrayList<Thread> getAll() {

        ArrayList<Thread> threads = new ArrayList<>();

        try {
            //SQL query to return all entries from threads table
            PreparedStatement ps = con.prepareStatement("SELECT * FROM threads");
            ResultSet rs = ps.executeQuery();
//ResultSet is a row of values from the query.  rs.next() returns the next row
            while (rs.next()) {
                Thread thread = new Thread(); // user -> null
                thread.setId(rs.getString("id"));
                thread.setTitle(rs.getString("title"));
                thread.setUser_id(rs.getString("user_id"));
                threads.add(thread);
            }
        } catch (SQLException e) {
            System.out.println("Database query failed");
            return null;
        }

        return threads;
    }
}