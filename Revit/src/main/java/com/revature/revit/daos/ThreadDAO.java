package com.revature.revit.daos;

import com.revature.revit.models.Threads;
import com.revature.revit.models.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThreadDAO implements CrudDAO<Threads>{

    @Override
    public void save(Threads obj) {

    }

    @Override
    public void update(Threads obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Threads getById(String id) {
        Threads thread = new Threads();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM threads where id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                thread = new Threads(rs.getString("id"), rs.getString("title"), rs.getString("user_id"), rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("Database query failed");
            return null;
        }
        return thread;
    }

    @Override
    public List<Threads> getAll() {

        List<Threads> threads = new ArrayList<>();

        try {
            //SQL query to return all entries from users table
            PreparedStatement ps = con.prepareStatement("SELECT * FROM threads");
            ResultSet rs = ps.executeQuery();
//ResultSet is a row of values from the query.  rs.next() returns the next row
            while (rs.next()) {
                Threads thread = new Threads(); // user -> null
                thread.setId(rs.getString("id"));
                thread.setTitle(rs.getString("title"));
                thread.setUser(rs.getString("user_id"));
                threads.add(thread);
            }
        } catch (SQLException e) {
            System.out.println("Database query failed");
            return null;
        }

        return threads;
    }
}
