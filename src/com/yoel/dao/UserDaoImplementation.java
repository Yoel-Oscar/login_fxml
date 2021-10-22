package com.yoel.dao;

/*
  author 1672063 Yoel Oscar
 */


import com.yoel.entity.User;
import com.yoel.util.LoginServices;
import com.yoel.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class UserDaoImplementation implements LoginServices<User> {


    @Override
    public User login(String email, String password) throws ClassNotFoundException, SQLException {
        User usersData = null;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT email, password FROM user WHERE email =? AND password = MD5(?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                try (ResultSet rsltset = pstmt.executeQuery()) {
                    while (rsltset.next()) {
                        usersData.setEmail(rsltset.getString("email"));
                        usersData.setPassword(rsltset.getString("password"));
                    }
                }
            }
        }
        return usersData;
    }
}
