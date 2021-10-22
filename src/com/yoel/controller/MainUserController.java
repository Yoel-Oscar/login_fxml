package com.yoel.controller;

import com.yoel.Main;
import com.yoel.dao.DepartmentDaoImplementation;
import com.yoel.dao.FacultyDaoImplementation;
import com.yoel.entity.Faculty;
import com.yoel.entity.department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * author 1672063 Yoel Oscar
 */

public class MainUserController implements Initializable {

    @FXML
    private BorderPane userMainBorder;
    private FacultyDaoImplementation facultyDao;
    private DepartmentDaoImplementation dprtmntDao;
    private ObservableList<Faculty> faculties;
    private ObservableList<department> departments;
    private Stage facultyStage;
    private Stage departmentStage;

    @FXML
    private void showInfoAction(ActionEvent actionEvent) {
        Alert information = new Alert(Alert.AlertType.INFORMATION);
        information.setContentText("Created by Yoel Oscar 1672063");
        information.show();
    }
    @FXML
    private void shwFacultManageAction(ActionEvent actionEvent) throws IOException {
        if(facultyStage == null){

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/facultyUserView.fxml"));
            Parent root = loader.load();
            UserFacultyController controller= loader.getController();
            controller.setMainController(this);
            Scene scene = new Scene(root);
            facultyStage = new Stage();
            facultyStage.setTitle("Faculty Management - 1672063");
            facultyStage.setScene(scene);
            facultyStage.initOwner(userMainBorder.getScene().getWindow());
        }
        if(facultyStage.isShowing()){
            facultyStage.toFront();
        }else{
            facultyStage.show();
        }

    }

    @FXML
    private void shwDepartManageAction(ActionEvent actionEvent) throws IOException {
        if(departmentStage == null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/departementUserView.fxml"));
            Parent root = loader.load();
            DepartmentUserController controller= loader.getController();
            controller.setMainController(this);
            Scene scene = new Scene(root);
            departmentStage = new Stage();
            departmentStage.setTitle("Department Management - 1672063");
            departmentStage.setScene(scene);
            departmentStage.initOwner(userMainBorder.getScene().getWindow());
        }
        if(departmentStage.isShowing()){
            departmentStage.toFront();
        }else{
            departmentStage.show();
        }
    }

    @FXML
    private void closeAppAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facultyDao  = new FacultyDaoImplementation();
        dprtmntDao  = new DepartmentDaoImplementation();
        departments = FXCollections.observableArrayList();
        faculties   = FXCollections.observableArrayList();
        try {
            faculties.addAll(facultyDao.fetchAll());
            departments.addAll(dprtmntDao.fetchAll());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public FacultyDaoImplementation getFacultyDao() {
        return facultyDao;
    }

    public ObservableList<Faculty> getFaculties() {
        return faculties;
    }

    public DepartmentDaoImplementation getDepartmentDao() {
        return dprtmntDao;
    }

    public ObservableList<department> getDepartments() {
        return departments;
    }

}

