package com.yoel.dao;
/**
 * author 1672063 Yoel Oscar
 */

import com.yoel.entity.Faculty;
import com.yoel.entity.department;
import com.yoel.util.DaoServices;
import com.yoel.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DepartmentDaoImplementation implements DaoServices<department> {
    @Override
    public List<department> fetchAll() throws ClassNotFoundException, SQLException {
        List<department> departments = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()){
            String query = "SELECT d.id, d.name, f.name AS faculty_name FROM " +
                    "department d JOIN faculty f on f.id = d.faculty_id";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                try (ResultSet rsltset = pstmt.executeQuery()) {
                    while(rsltset.next()){
                        Faculty fclty= new Faculty();
                        fclty.setIdUSer(rsltset.getInt("faculty_id"));
                        fclty.setNameUser(rsltset.getString("faculty_name"));

                        department departmnt = new department();
                        departmnt.setId(rsltset.getString("id"));
                        departmnt.setName(rsltset.getString("name"));
                        departmnt.setFaculty(fclty);
                        departments.add(departmnt);
                    }
                }
            }
        }
        return departments;
    }

    @Override
    public int addData(department department) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()){
            String query = "INSERT INTO department(id, name, faculty_id) VALUES(?,?,?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, department.getId());
                pstmt.setString(2, department.getName());
                pstmt.setString(3, String.valueOf(department.getFaculty().getIdUSer()));
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
    public int deleteData(department department) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM department WHERE id=?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, department.getId());
                if (pstmt.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(department department) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE department SET name= ?, faculty_id=? WHERE id=?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, department.getName());
                pstmt.setString(3, department.getId());
                pstmt.setString(2, String.valueOf(department.getFaculty().getIdUSer()));
                if (pstmt.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }
}
