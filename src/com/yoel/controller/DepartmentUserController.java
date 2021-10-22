package com.yoel.controller;
/**
 * author 1672063 Yoel Oscar
 *
 *
 */

import com.yoel.entity.Faculty;
import com.yoel.entity.department;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DepartmentUserController  implements Initializable {

    @FXML
    private TextField idDepart;
    @FXML
    private TextField nameDepart;
    @FXML
    private ComboBox<Faculty> departCombo;
    @FXML
    private TableView<department> tableDepart;
    @FXML
    private TableColumn<department,String> departIdCol;
    @FXML
    private TableColumn<department,String> departNameCol;
    @FXML
    private TableColumn<department, String> facultyNameCol;

    private ObservableList<department> departments;
    private ObservableList<Faculty> faculties;

    private Button saveDepartmentButton;
    private Button resDepartmentbutton;
    private Button updateDepartmentButton;
    private Button deleteDepartmentButton;
    private department selectedDepartmentData;
    private MainUserController mainController;

    public void saveDepartOnAction(ActionEvent actionEvent) {
        String id = idDepart.getText();
        String name = nameDepart.getText();
        int nameLength = name.length();
        if(!name.isEmpty() && nameLength < 80){
            department dprtmnt = new department();
            dprtmnt.setId(id);
            dprtmnt.setName(name);
            dprtmnt.setFaculty(departCombo.getValue());
            try{
                int result = mainController.getDepartmentDao().addData(dprtmnt);
                if(result == 1){
                    mainController.getDepartments().clear();
                    mainController.getDepartments().addAll(mainController.getDepartmentDao().fetchAll());
                    idDepart.clear();
                    nameDepart.clear();

                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error on adding data");
            alert.showAndWait();
        }
    }
    public void resDepartOnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        departments = FXCollections.observableArrayList();
        faculties = FXCollections.observableArrayList();
        departCombo.setItems(faculties);
        tableDepart.setItems(departments);
        departIdCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));
        departNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        facultyNameCol.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getFaculty().getNameUser()));

        tableDepart.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedDepartmentData = newValue;
            if(selectedDepartmentData !=null){
                idDepart.setText(selectedDepartmentData.getId());
                nameDepart.setText(selectedDepartmentData.getName());
                departCombo.setValue(selectedDepartmentData.getFaculty());
                saveDepartmentButton.setDisable(true);
                deleteDepartmentButton.setDisable(false);
                updateDepartmentButton.setDisable(false);
                idDepart.setDisable(true);

            }
        });
    }
    public void setMainController(MainUserController mainController) {
        this.mainController = mainController;
        departCombo.setItems(mainController.getFaculties());
        tableDepart.setItems(mainController.getDepartments());
    }

    public ObservableList<Faculty> getFaculties(){
        return faculties;
    }



    public void updateOnAction(ActionEvent actionEvent) {
        String id = idDepart.getId();
        String name = nameDepart.getText();
        int nameLength = name.length();
        if(!name.isEmpty() && nameLength < 80){

            selectedDepartmentData.setId(id);
            selectedDepartmentData.setName(name);
            try{
                int result = mainController.getDepartmentDao().updateData(selectedDepartmentData);
                if(result == 1){
                    mainController.getDepartments().clear();
                    mainController.getDepartments().addAll(mainController.getDepartmentDao().fetchAll());
                    idDepart.clear();
                    nameDepart.clear();
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error on adding data");
            alert.showAndWait();
        }
    }
    public void deleteAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Sure delete Data ?");
        alert.showAndWait();

        if(alert.getResult() == ButtonType.OK){
            try{
                int result = mainController.getDepartmentDao().deleteData(selectedDepartmentData);
                if(result == 1){
                    mainController.getDepartments().clear();
                    mainController.getDepartments().addAll(mainController.getDepartmentDao().fetchAll());
                    idDepart.clear();
                    nameDepart.clear();
                    saveDepartmentButton.setDisable(false);
                    deleteDepartmentButton.setDisable(true);
                    updateDepartmentButton.setDisable(true);
                    idDepart.setDisable(false);
                    selectedDepartmentData = null;
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

