package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class Controller {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CAPTCHA_LENGTH = 6;
    private String captchaText;


    public void changeScene(ActionEvent e, String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void goMainScene(ActionEvent e) throws IOException {//Main scene --> scene includes Items like (makaroni)
        if (txtCaptchaInput.getText().equals(captchaText)) {
            fxmlLoader = new FXMLLoader(Application.class.getResource("mainPage.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } else {
            txtCaptchaInput.clear();
        }
    }//this method calls by login button

    public void goSignUpScene(ActionEvent e) throws IOException {
        changeScene(e, "signUP.fxml");
    }

    public void goLoginScene(ActionEvent e) throws IOException {
        changeScene(e, "Login.fxml");
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

    @FXML
    private Label txtCaptcha;
    @FXML
    private TextField txtCaptchaInput;

    public void captcha() throws Exception {
        captchaText = generateCaptchaText();
        txtCaptcha.setText(captchaText);
    }

    private String generateCaptchaText() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(CAPTCHA_LENGTH);
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    @FXML
    private TextField txtFirstname, txtLastname, txtPhoneNumber, txtUserName, txtPassWord, txtEmail, txtWorkPlace;

    public void Register() {
        if (buttonSeller.isSelected()) /*if (buttonRegister.isSelected())*/ {
            Application.shop.sellers.add(new Seller(txtFirstname.getText(), txtLastname.getText(), txtPhoneNumber.getText(), txtUserName.getText(), txtPassWord.getText(), txtEmail.getText(), txtWorkPlace.getText()));//make again the work place text field
            System.out.println(Application.shop.sellers.toString());
        } else if (buttonCustomer.isSelected()) /*if (buttonRegister.isSelected())*/ {
            Application.shop.customers.add(new Customer(txtFirstname.getText(), txtLastname.getText(), txtPhoneNumber.getText(), txtUserName.getText(), txtPassWord.getText(), txtEmail.getText()));
            System.out.println(Application.shop.customers.toString());
        }
    }

}