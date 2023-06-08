package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAdmin {

    @FXML
    private TableView<ControllerAdmin> TVBankAccountsList;
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
    private String BankName,AccountType,Balance,OpeningDate;
    private Integer Branch,AccountNumber;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;


    public void changingScene(ActionEvent e, String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void changetoLoginSceneAdmin(ActionEvent e) throws IOException {
        changingScene(e, "Login.fxml");
    }


}
