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


}
