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
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password VALUES (?,?,?)");
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
        return null;
    }

    @Override
    public List<Users> getAll() {
        List<User> users = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setAddress(rs.getString("address"));
                user.setCity(rs.getString("city"));
                user.setState(rs.getString("state"));

                users.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to get data from the database.");
        }
        return users;


    }
    public List<String> getAllUsernames() throws IOException {
        List<String> usernames = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT username FROM users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usernames.add(rs.getString("username").toLowerCase());
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred when trying to get data from the database.");
        }
        return usernames;

    }
}
