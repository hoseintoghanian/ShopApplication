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

    public void changeToBankScene(ActionEvent e) throws IOException {

        Application.shop.pageURL = "payment.fxml";
        ChangeScene2(e, "bankPortal.fxml");
    }

    public void back(ActionEvent e) throws IOException {
        ChangeScene2(e, Application.shop.pageURL);
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
        if (Application.shop.pageURL=="payment.fxml") {
            int sum = 0;
            for (int i = 0; i < Application.shop.currentCustomer.cartItems.size(); i++) {
                sum += Application.shop.currentCustomer.cartItems.get(i).price * Application.shop.currentCustomer.cartItems.get(i).tempSize;
            }
            labelFinalCost.setText(String.valueOf(sum));
        }
        else if (Application.shop.pageURL=="customer.fxml"){
            labelFinalCost.setText(String.valueOf(Application.shop.currentCustomer.increaseAmount));
        }
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


    //////////////////////////////////   buy conditions   ////////////////////////////////////////////////
    @FXML
    private Label txtbankportalerror;

    public boolean checkBankScene() {

        if (!txtcardnumber.getText().equals("") &&
                !txtcvv2.getText().equals("") &&
                !txtcardexpireyear.getText().equals("") &&
                !txtcardexpiremonth.getText().equals("") &&
                !txtcardsecondcode.getText().equals("") &&
                !txtemail.getText().equals("")
        ) {
            if (txtcardnumber.getText().length() != 16) {
                txtbankportalerror.setText("شماره کارت نامعتبر است");
                return false;
            }
            for (int i = 0; i < 16; i++) {
                if (txtcardnumber.getText().charAt(i) < 48 || txtcardnumber.getText().charAt(i) > 57) {
                    txtbankportalerror.setText("شماره کارت نامعتبر است");
                    return false;
                }
            }
            if (txtcvv2.getText().length() != 4) {
                txtbankportalerror.setText("cvv2 نامعتبر است");
                return false;
            }
            if (txtcardexpireyear.getText().length() != 4) {
                txtbankportalerror.setText("تاریخ انقضای سال نامعتبر است");
                return false;
            }
            int cardExpireMonth = Integer.parseInt(txtcardexpiremonth.getText());
            if (cardExpireMonth > 12 || cardExpireMonth < 0) {
                txtbankportalerror.setText("تاریخ انقضای ماه نامعتبر است");
                return false;
            }
            if (!txtcaptchainput2.getText().equals(captchaText)) {
                txtcaptchainput2.clear();
                txtbankportalerror.setText("کپچا را اشتباه وارد کردید");
                return false;
            }
            if (txtcardsecondcode.getText().length() != 5) {
                txtbankportalerror.setText("رمز اینترنتی نامعتبر است");
                return false;
            }
            if (!txtemail.getText().endsWith("@gmail.com")) {
                txtbankportalerror.setText("ایمیل نامعتبر است");
                return false;
            }
        } else if (txtcardnumber.getText().equals("") ||
                txtcvv2.getText().equals("") ||
                txtcardexpireyear.getText().equals("") ||
                txtcardexpiremonth.getText().equals("") ||
                txtcardsecondcode.getText().equals("") ||
                txtemail.getText().equals("")
        ) {
            txtbankportalerror.setText("لطفا تمام فیلد ها را پر کنید");
            return false;
        }
        return true;
    }

    @FXML
    private Button buttonpayment;
    @FXML
    private TextField txtpaymentprovince, txtpaymentcity, txtpaymentpostalcode, txtpaymentname, txtpaymentphonenumber, txtpaymentdiscountcode;

    public void checkPayment() {

        if (!txtpaymentprovince.getText().equals("") &&
                !txtpaymentcity.getText().equals("") &&
                !txtpaymentpostalcode.getText().equals("") &&
                !txtpaymentname.getText().equals("") &&
                !txtpaymentphonenumber.getText().equals("") &&
                !txtpaymentpostaladdress.getText().equals("")
        ) {
            buttonpayment.setDisable(false);
        } else {
            buttonpayment.setDisable(true);
        }
    }

    public void pay(ActionEvent e) throws SQLException, IOException {

        if (Application.shop.pageURL.equals("payment.fxml") || Application.shop.pageURL.equals("customer.fxml")) {
            checkBankScene();
            if (checkBankScene()) {
                if (Application.shop.currentCustomer.wallet >= Long.valueOf(labelFinalCost.getText()) || Application.shop.pageURL == "customer.fxml") {

                    if (Application.shop.pageURL != "customer.fxml") {
                        Application.shop.currentCustomer.purchase.addAll(Application.shop.currentCustomer.cartItems);
                        Application.shop.currentCustomer.cartItems.clear();
                        Application.shop.currentCustomer.wallet -= Long.valueOf(labelFinalCost.getText());
                        for (int i = 0; i < Application.shop.currentCustomer.purchase.size(); i++) {
                            Database.addProduct("customer_purchase_", Application.shop.currentCustomer.purchase.get(i), Application.shop.currentCustomer.getUsername());
                        }
                    }
                    if (Application.shop.pageURL == "customer.fxml") {
                        Application.shop.currentCustomer.wallet += Long.valueOf(labelFinalCost.getText());
                        labelFinalCost.setText("0");
                    }
                    txtbankportalerror.setStyle("-fx-text-fill: green;");
                    txtbankportalerror.setText("خرید با موفقیت انجام شد");
                    //if (Application.shop.pageURL == "customer.fxml") ChangeScene2(e, "customer.fxml");
                    //if (Application.shop.pageURL == "seller.fxml") ChangeScene2(e, "seller.fxml");
                    return;
                } else
                    txtbankportalerror.setStyle("-fx-text-fill: red;");
                txtbankportalerror.setText("موجودی ناکافی");
            }
        }
    }
}
