package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class ControllerPayment {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CAPTCHA_LENGTH = 6;
    private String captchaText;
    @FXML
    private TextField txtcaptchainput2;
    @FXML
    private Label txtCaptcha2;

    public void ChangeScene2(ActionEvent e,String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void ChangeToCartScene(ActionEvent e) throws IOException {
        ChangeScene2(e, "cart.fxml");
    }
    public void changeToBakScene(ActionEvent e) throws IOException {
        ChangeScene2(e, "bankPortal.fxml");
    }
    public void changeToPaymentScene(ActionEvent e) throws IOException{
        ChangeScene2(e,"payment.fxml");
    }

    public void captcha2() throws Exception {
        captchaText = generateCaptchaText();
        txtCaptcha2.setText(captchaText);
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
    private Label labelfinalcost;
    public void displayInfo(){
        int sum = 0;
        for (int i=0;i<Application.shop.currentCustomer.items.size();i++){
            sum+=Application.shop.currentCustomer.items.get(i).price;
        }
        labelfinalcost.setText(String.valueOf(sum));
    }
    public void buy() {
        System.out.println(2);
    }


    //for the spinner in cart tab see bro code
}
