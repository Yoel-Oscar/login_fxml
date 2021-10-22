package com.yoel.controller;
/**
 * author 1672063 Yoel Oscar
 *
 *
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

public class loginUserController implements Initializable{
    @FXML
    private GridPane gridUserPane;
    @FXML
    private TextField emailUser;
    @FXML
    private TextField passUser;

    @FXML
    private Stage mainStage;

    @FXML
    private void logUserOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

        if (emailUser.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Silahkan isi email anda");
            alert.showAndWait();
        } else if (passUser.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Silahkan isi password anda");
            alert.showAndWait();
        }
        if (emailUser.getText().isEmpty() && passUser.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Silahkan isi email dan password anda");
            alert.showAndWait();
        }


    }
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/mainUserView.fxml"));
        primaryStage.setTitle("1672063-Yoel Oscar Werinussa");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserDaoImplementation usrDao = new UserDaoImplementation();
        String emailData = emailUser.getText();
        String pwdData = passUser.getText();
        try {
            usrDao.login(emailData,pwdData);
            if(usrDao !=null && usrDao.equals(emailData)){
                try {
                    start(mainStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

