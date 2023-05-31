package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
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


    public void switchToScene1(ActionEvent e) throws IOException {
        fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent e) throws IOException {
        if (txtCaptchaInput.getText().equals(captchaText)) {
            fxmlLoader = new FXMLLoader(Application.class.getResource("signUp.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } else {
            txtCaptchaInput.clear();
        }
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



}