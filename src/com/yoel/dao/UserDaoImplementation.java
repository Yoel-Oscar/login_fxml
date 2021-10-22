package com.yoel.dao;

import com.yoel.entity.User;
import com.yoel.util.LoginServices;
import com.yoel.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 1672063 Yoel Oscar
 */

public class UserDaoImplementation implements LoginServices {
    @Override
    public User login(String email, String password) throws ClassNotFoundException, SQLException {
        User user = null;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT id, name, email FROM user WHERE email =? AND password = MD5(?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                try (ResultSet rsts = pstmt.executeQuery()) {
                    while (rsts.next()) {
                        user.setId(rsts.getInt("id"));
                        user.setName(rsts.getString("name"));
                        user.setEmail(rsts.getString("email"));
                    }
                }
            }
        }
        return user;
    }
}
