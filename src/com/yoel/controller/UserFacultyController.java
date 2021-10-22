package com.yoel.controller;
/**
 * author 1672063 Yoel Oscar
 */

import com.yoel.entity.Faculty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserFacultyController implements Initializable {

    @FXML
    private VBox VboxFaculty;
    @FXML
    private TextField facultyFieldId;
    @FXML
    private TableView<Faculty> tableFaculty;
    @FXML
    private TableColumn<Faculty,Integer> idFaculColumn;
    @FXML
    private TableColumn<Faculty, String> nameFaculColumn;

    public Button buttonSave;
    public Button buttonUpdate;
    public Button buttonDel;
    private MainUserController mainController;
    private Faculty selectedFacultyData;

    @FXML
    private void savFacultyOnAction(ActionEvent actionEvent) {
        String name = facultyFieldId.getText();
        int nameLength =name.length();
        if(!name.isEmpty() && nameLength < 80){
            Faculty faculty = new Faculty();
            faculty.setNameUser(name);
            try{
                int result = mainController.getFacultyDao().addData(faculty);
                if(result ==1 ){
                    mainController.getFaculties().clear();
                    mainController.getFaculties().addAll(mainController.getFacultyDao().fetchAll());
                    facultyFieldId.clear();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error on new Faculty Name");
            alert.showAndWait();
        }

    }

    @FXML
    private void resFacultyOnAction(ActionEvent actionEvent) {
        resetDataForm();
    }
    private void resetDataForm(){
        buttonSave.setDisable(false);
        buttonDel.setDisable(true);
        buttonUpdate.setDisable(true);
        selectedFacultyData=null;

        facultyFieldId.clear();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idFaculColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getIdUSer()).asObject());
        nameFaculColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNameUser()));
    }
    public void setMainController(MainUserController mainController) {
        this.mainController = mainController;
        tableFaculty.setItems(mainController.getFaculties());
    }

    public void updateDataFacultyAction(ActionEvent actionEvent) {
        String name = facultyFieldId.getText();
        int nameLength =name.length();
        if(!name.isEmpty() && nameLength < 80){

            selectedFacultyData.setNameUser(name);
            try{
                int result = mainController.getFacultyDao().updateData(selectedFacultyData);
                facultyData(result);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error on new Faculty Name");
            alert.showAndWait();
        }

    }

    public void deleteDataFacultyAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Sure delete Data ?");
        alert.showAndWait();

        if(alert.getResult() == ButtonType.OK){
            try{
                int result = mainController.getFacultyDao().deleteData(selectedFacultyData);
                facultyData(result);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            tableFaculty.refresh();
        }

    }

    private void facultyData(int result) throws ClassNotFoundException, SQLException {
        if(result ==1 ){
            mainController.getFaculties().clear();
            mainController.getFaculties().addAll(mainController.getFacultyDao().fetchAll());
            mainController.getDepartments().clear();
            mainController.getDepartments().addAll(mainController.getDepartmentDao().fetchAll());
            resetDataForm();
        }
    }

    public void tableFacultyClicked(MouseEvent mouseEvent) {
        selectedFacultyData = tableFaculty.getSelectionModel().getSelectedItem();
        if(selectedFacultyData != null){
            facultyFieldId.setText(String.valueOf(selectedFacultyData.getIdUSer()));
            buttonSave.setDisable(true);
            buttonDel.setDisable(false);
            buttonUpdate.setDisable(false);

        }
    }
}
