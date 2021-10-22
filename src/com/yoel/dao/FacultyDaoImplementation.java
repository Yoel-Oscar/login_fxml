package com.yoel.dao;

import com.yoel.entity.Faculty;
import com.yoel.util.DaoServices;
import com.yoel.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * author 1672063 Yoel Oscar
 *
 *
 */

public class FacultyDaoImplementation implements DaoServices<Faculty> {



    @Override
    public List<Faculty> fetchAll() throws ClassNotFoundException, SQLException {
        List<Faculty> faculties = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()){
            String query = "SELECT id, name FROM faculty";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                try (ResultSet rsltset = pstmt.executeQuery()) {
                    while(rsltset.next()){
                        Faculty faculty = new Faculty();
                        faculty.setIdUSer(rsltset.getInt("id"));
                        faculty.setNameUser(rsltset.getString("name"));
                        faculties.add(faculty);
                    }
                }
            }
        }
        return faculties;
    }
    @Override
    public int addData(Faculty faculty) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()){
            String query = "INSERT INTO faculty(name) VALUES(?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, faculty.getNameUser());
                if(pstmt.executeUpdate() != 0){
                    connection.commit();
                    result = 1;
                }else{
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int deleteData(Faculty faculty) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()){
            String query = "DELETE FROM faculty WHERE id=?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, faculty.getIdUSer());
                if(pstmt.executeUpdate() != 0){
                    connection.commit();
                    result = 1;
                }else{
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(Faculty faculty) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()){
            String query = "UPDATE faculty SET name= ? WHERE id=?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, faculty.getNameUser());
                pstmt.setInt(2, faculty.getIdUSer());
                if(pstmt.executeUpdate() != 0){
                    connection.commit();
                    result = 1;
                }else{
                    connection.rollback();
                }
            }
        }
        return result;
    }
}
