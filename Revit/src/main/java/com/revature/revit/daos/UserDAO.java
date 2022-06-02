package com.revature.revit.daos;

import com.revature.revit.models.Users;
import com.revature.revit.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements CrudDAO<Users> {

    Connection con = DatabaseConnection.getCon();
    @Override
    public void save(Users obj) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password VALUES (?,?,?,?)");
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
        return null;
    }
}
