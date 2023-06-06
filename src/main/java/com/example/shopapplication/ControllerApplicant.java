package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerApplicant {

    private Stage stage2;
    private Scene scene2;
    private FXMLLoader fxmlLoader2;
    @FXML
    private Label txtFNaccount,txtLNaccount,txtPNaccount,txtUNaccount,txtPWaccount,txtEMaccount,txtWBaccount;
    @FXML
    private Label txtfnaccount,txtlnaccount,txtpnaccount,txtunaccount,txtpwaccount,txtemaccount;

    public ControllerApplicant() {
    }

    public void displayInfo(){
         if (Application.shop.customers.contains(Application.shop.currentCustomer)){
             txtFNaccount.setText(Application.shop.currentCustomer.getFirstname());
             txtLNaccount.setText(Application.shop.currentCustomer.getLastname());
             txtPNaccount.setText(Application.shop.currentCustomer.getPhoneNumber());
             txtUNaccount.setText(Application.shop.currentCustomer.getUsername());
             txtPWaccount.setText(Application.shop.currentCustomer.getPassword());
             txtEMaccount.setText(Application.shop.currentCustomer.getEmail());
         }
         else if(Application.shop.sellers.contains(Application.shop.currentSeller)){
             txtfnaccount.setText(Application.shop.currentSeller.getFirstname());
             txtlnaccount.setText(Application.shop.currentSeller.getLastname());
             txtpnaccount.setText(Application.shop.currentSeller.getPhoneNumber());
             txtunaccount.setText(Application.shop.currentSeller.getUsername());
             txtpwaccount.setText(Application.shop.currentSeller.getPassword());
             txtemaccount.setText(Application.shop.currentSeller.getEmail());
         }
    }

    public void changingScene(ActionEvent e, String fxml) throws IOException {
        fxmlLoader2 = new FXMLLoader(Application.class.getResource(fxml));
        stage2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene2 = new Scene(fxmlLoader2.load());
        stage2.setScene(scene2);
        stage2.show();
    }

    public void changetoLoginSceneCustomer(ActionEvent e) throws IOException {
        changingScene(e, "Login.fxml");
    }

    public void changetoLoginSceneSeller(ActionEvent e) throws IOException {
        changingScene(e, "Login.fxml");
    }


    public void buy() {
        System.out.println(2);
    }


    //for the spinner in cart tab see bro code
}
