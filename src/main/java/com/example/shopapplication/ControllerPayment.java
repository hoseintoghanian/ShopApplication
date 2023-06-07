package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerPayment {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CAPTCHA_LENGTH = 6;
    private String captchaText;
    @FXML
    private TextField txtcaptchainput2;
    @FXML
    private Label txtCaptcha2;
    public void changeScene(ActionEvent e, String fxml) {

    }


    public void buy() {
        System.out.println(2);
    }


    //for the spinner in cart tab see bro code
}
