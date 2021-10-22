package com.yoel.controller;
/**
 * author 1672063 Yoel Oscar
 */


import com.yoel.dao.UserDaoImplementation;
import com.yoel.entity.User;
import com.yoel.entity.department;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginUserController implements Initializable {
    @FXML
    private GridPane gridUserPane;
    @FXML
    private TextField emailUser;
    @FXML
    private TextField passUser;
    @FXML
    private Stage mainStage;
    private UserDaoImplementation userDao;

    @FXML
    private void logUserOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String emailData = emailUser.getText();
        String pwdData = passUser.getText();
        if (emailData.isEmpty() || pwdData.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Silahkan isi email dan password anda");
            alert.showAndWait();
        } else {
            try {
                User user = userDao.login(emailData, pwdData);
                if (user != null && user.getEmail().equals(emailData)) {
                    //  TODO Call Main Stage
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/mainUserView.fxml"));
        primaryStage.setTitle("1672063-Yoel Oscar Werinussa");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userDao = new UserDaoImplementation();
    }
}

