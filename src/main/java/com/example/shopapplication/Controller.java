package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;


    public void switchToScene1(ActionEvent e) throws IOException {
        fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent e) throws IOException {
        fxmlLoader = new FXMLLoader(Application.class.getResource("signUp.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private Label txtApplicant;
    @FXML
    private RadioButton buttonSeller, buttonCustomer;

    public void chooseApplicant(ActionEvent e) {
        if (buttonSeller.isSelected()) {
            txtApplicant.setText(buttonSeller.getText());
        } else if (buttonCustomer.isSelected()) {
            txtApplicant.setText(buttonCustomer.getText());
        }
    }
}