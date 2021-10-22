package com.yoel;
/**
 * author 1672063 Yoel Oscar
 *
 *
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        primaryStage.setTitle("1672063-Yoel Oscar Werinussa");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
