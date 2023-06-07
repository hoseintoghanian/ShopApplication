package com.example.shopapplication;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerAdmin {

    @FXML
    private TableView<ControllerAdmin> TVBankAccount;
    @FXML
    private TableColumn<ControllerAdmin, String> TCBankName;
    @FXML
    private TableColumn<ControllerAdmin, Integer> TCBranch;
    @FXML
    private TableColumn<ControllerAdmin,Integer> TCAccountNumber;
    @FXML
    private TableColumn<ControllerAdmin,String> TCAccountType;
    @FXML
    private TableColumn<ControllerAdmin,String> TCBalance;
    @FXML
    private TableColumn<ControllerAdmin,String> TCOpeningDate;

    /*public void Initialize(){

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        tableView.getColumns().addAll(nameColumn, ageColumn);

        ObservableList<ControllerAdmin> data = FXCollections.observableArrayList(
                new MyDataModel("John", 30),
                new MyDataModel("Jane", 25),
                new MyDataModel("Bob", 40)
        );

        tableView.setItems(data);

    }*/
}
