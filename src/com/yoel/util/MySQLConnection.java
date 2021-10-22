package com.yoel.util;
/**
 //Class.forName("com.mysql.jdbc.driver"); MYSQL versi 05
*
*author Yoel Oscar 1672063
* */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {


    private static final String URL ="jdbc:mysql://localhost:3306/dboopl_1672063";
    private static final String USERNAME ="root";
    private static final String PASSWORD ="";

    public static Connection createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }
}
