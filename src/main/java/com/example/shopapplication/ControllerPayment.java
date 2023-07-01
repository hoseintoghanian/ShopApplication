package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class ControllerPayment {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CAPTCHA_LENGTH = 6;
    private String captchaText;

    public void ChangeScene2(ActionEvent e, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void ChangeToCartScene(ActionEvent e) throws IOException {
        ChangeScene2(e, "cart.fxml");
    }

    @FXML
    private Button buttonpayment;

    public void changeToBankScene(ActionEvent e) throws IOException {

        Application.shop.pageURL = "payment.fxml";
        ChangeScene2(e, "bankPortal.fxml");
    }

    public void checkPayment() {

        if (!txtpaymentprovince.getText().equals("") &&
                !txtpaymentcity.getText().equals("") &&
                !txtpaymentpostalcode.getText().equals("") &&
                !txtpaymentname.getText().equals("") &&
                !txtpaymentphonenumber.getText().equals("")
        ) {
            buttonpayment.setDisable(false);
        } else {
            buttonpayment.setDisable(true);
        }
    }

    public void back(ActionEvent e) throws IOException {
        ChangeScene2(e, Application.shop.pageURL);
    }

    @FXML
    private TextField txtpaymentprovince, txtpaymentcity, txtpaymentpostalcode, txtpaymentname, txtpaymentphonenumber, txtpaymentdiscountcode;

    public void pay() throws SQLException {

        if (Application.shop.pageURL.equals("payment.fxml")) {

            Application.shop.currentCustomer.purchase.addAll(Application.shop.currentCustomer.cartItems);
            Application.shop.currentCustomer.cartItems.clear();

            for (int i = 0; i < Application.shop.currentCustomer.purchase.size(); i++) {
                Database.addProduct("purchase_", Application.shop.currentCustomer.purchase.get(i), Application.shop.currentCustomer.getUsername());
            }
        }

    }

    @FXML
    private Label labelfinalcost;

    public void displayInfo() {
        int sum = 0;
        for (int i = 0; i < Application.shop.currentCustomer.cartItems.size(); i++) {
            sum += Application.shop.currentCustomer.cartItems.get(i).price * Application.shop.currentCustomer.cartItems.get(i).tempSize;
        }
        labelfinalcost.setText(String.valueOf(sum));

        checkPayment();
    }

    @FXML
    private Label labelFinalCost;

    public void displayInfo2() {
        int sum = 0;
        for (int i = 0; i < Application.shop.currentCustomer.cartItems.size(); i++) {
            sum += Application.shop.currentCustomer.cartItems.get(i).price * Application.shop.currentCustomer.cartItems.get(i).tempSize;
        }
        labelFinalCost.setText(String.valueOf(sum));
    }


    @FXML
    private TextField txtcardnumber, txtcvv2, txtcardexpiremonth, txtcardexpireyear, txtcaptchainput2, txtcardsecondcode, txtemail;
    @FXML
    private TextArea txtpaymentpostaladdress;
    @FXML
    private Label txtCaptcha2;

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


    public boolean check() {
        if (txtcardnumber.getText().length() != 16) return false;
        for (int i = 0; i < 16; i++) {
            if (txtcardnumber.getText().charAt(i) < 48 || txtcardnumber.getText().charAt(i) > 57) return false;
        }
        if (txtcvv2.getText().length() != 4 && txtcvv2.getText().length() != 5) return false;
        if (txtcardexpireyear.getText().length() != 4) return false;
        int cardExpireMonth = Integer.parseInt(txtcardexpiremonth.getText());
        if (cardExpireMonth > 12 || cardExpireMonth < 0) return false;
        if (txtcardsecondcode.getText().length() != 5 && txtcardsecondcode.getText().length() != 4) return false;
        if (!txtemail.getText().endsWith("@gmail.com")) return false;
        return true;
    }


    //for the spinner in cart tab see bro code
}
