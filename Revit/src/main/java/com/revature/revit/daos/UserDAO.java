package com.revature.revit.daos;

import com.revature.revit.models.Users;
import com.revature.revit.util.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<Users> {

    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Users obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, name, password) VALUES (?,?,?)");
            ps.setString(1, obj.getId());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.executeUpdate();

        } catch (
                SQLException e) {
            throw new RuntimeException("An error occurred when saving to the database.");
        }
    }

    @Override
    public void update(Users obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Users getById(String id) {

       //Users users = new ArrayList<Users>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = '" + id + "'");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to get data from the database.");
        }
        return null;

    }

    @Override
    public List<Users> getAll() {
        List<Users> users = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Users user = new Users();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("name"));
                user.setPassword(rs.getString("password"));

                users.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to get data from the database.");
        }
        return users;


    }
    public List<String> getAllUsernames(){
        List<String> usernames = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT name FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usernames.add(rs.getString("name").toLowerCase());
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to get data from the database.");
        }
        return usernames;

    }
}
