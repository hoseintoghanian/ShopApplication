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
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class ControllerPayment {
    private static final String CHARACTERS = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789";
    private static final int CAPTCHA_LENGTH = 6;
    private String captchaText;

    @FXML
    private Label labelfinalcost, labelFinalCost, txtCaptcha2;
    @FXML
    private TextField txtCardNumber, txtCVV2, txtCardExpireMonth, txtCardExpireYear, txtCaptchaInput2, txtCardSecondCode, txtEmail;
    @FXML
    private TextArea txtPaymentPostalAddress;


    public void ChangeScene2(ActionEvent e, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Online Shop Application");
        stage.getIcons().add(new Image("shop.png"));
        stage.setResizable(false);
        stage.setOnCloseRequest(ev -> {
            System.exit(0);
        });
        stage.setScene(scene);
        stage.centerOnScreen();
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

    public void displayInfo() {
        int sum = 0;
        for (int i = 0; i < Application.shop.currentCustomer.cartItems.size(); i++) {
            sum += Application.shop.currentCustomer.cartItems.get(i).price * Application.shop.currentCustomer.cartItems.get(i).tempSize;
        }
        labelfinalcost.setText(String.valueOf(sum));

        checkPayment();
    }

    public void displayInfo2() {
        if (Application.shop.pageURL == "payment.fxml") {
            int sum = 0;
            for (int i = 0; i < Application.shop.currentCustomer.cartItems.size(); i++) {
                sum += Application.shop.currentCustomer.cartItems.get(i).price * Application.shop.currentCustomer.cartItems.get(i).tempSize;
            }
            labelFinalCost.setText(String.valueOf(sum));
        } else if (Application.shop.pageURL == "customer.fxml") {
            labelFinalCost.setText(String.valueOf(Application.shop.currentCustomer.increaseAmount));
        }
    }

    public void captcha2() {
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

    //---------------------------------------------------buy----------------------------------------
    @FXML
    private Label txtBankPortalError;
    @FXML
    private Button buttonPayment;
    @FXML
    private TextField txtPaymentProvince, txtPaymentCity, txtPaymentPostalCode, txtPaymentName, txtPaymentPhoneNumber, txtPaymentDiscountCode;

    public boolean checkBankScene() {

        if (!txtCardNumber.getText().equals("") &&
                !txtCVV2.getText().equals("") &&
                !txtCardExpireYear.getText().equals("") &&
                !txtCardExpireMonth.getText().equals("") &&
                !txtCardSecondCode.getText().equals("") &&
                !txtEmail.getText().equals("")
        ) {
            if (txtCardNumber.getText().length() != 16) {
                txtBankPortalError.setText("card number is invalid");
                return false;
            }
            for (int i = 0; i < 16; i++) {
                if (txtCardNumber.getText().charAt(i) < 48 || txtCardNumber.getText().charAt(i) > 57) {
                    txtBankPortalError.setText("card number is invalid");
                    return false;
                }
            }
            if (txtCVV2.getText().length() != 4) {
                txtBankPortalError.setText("cvv2 is invalid");
                return false;
            }
            if (txtCardExpireYear.getText().length() != 4) {
                txtBankPortalError.setText("expire year is invalid");
                return false;
            }
            int cardExpireMonth = Integer.parseInt(txtCardExpireMonth.getText());
            if (cardExpireMonth > 12 || cardExpireMonth < 0) {
                txtBankPortalError.setText("expire month is invalid");
                return false;
            }
            if (!txtCaptchaInput2.getText().equals(captchaText)) {
                txtCaptchaInput2.clear();
                txtBankPortalError.setText("captcha is incorrect");
                return false;
            }
            if (txtCardSecondCode.getText().length() != 5) {
                txtBankPortalError.setText("internet code is invalid");
                return false;
            }
            if (!txtEmail.getText().endsWith("@gmail.com")) {
                txtBankPortalError.setText("email is invalid");
                return false;
            }
        } else if (txtCardNumber.getText().equals("") ||
                txtCVV2.getText().equals("") ||
                txtCardExpireYear.getText().equals("") ||
                txtCardExpireMonth.getText().equals("") ||
                txtCardSecondCode.getText().equals("") ||
                txtEmail.getText().equals("")
        ) {
            txtBankPortalError.setText("please fill all the fields");
            return false;
        }
        return true;
    }

    public void checkPayment() {

        if (!txtPaymentProvince.getText().equals("") &&
                !txtPaymentCity.getText().equals("") &&
                !txtPaymentPostalCode.getText().equals("") &&
                !txtPaymentName.getText().equals("") &&
                !txtPaymentPhoneNumber.getText().equals("") &&
                !txtPaymentPostalAddress.getText().equals("")
        ) {
            buttonPayment.setDisable(false);
        } else {
            buttonPayment.setDisable(true);
        }
    }

    public void pay() throws SQLException {

        Item item;

        if (Application.shop.pageURL.equals("payment.fxml") || Application.shop.pageURL.equals("customer.fxml")) {
            checkBankScene();
            if (checkBankScene()) {
                if (Application.shop.currentCustomer.wallet >= Long.valueOf(labelFinalCost.getText()) || Application.shop.pageURL == "customer.fxml") {

                    if (Application.shop.pageURL != "customer.fxml") {
                        Application.shop.currentCustomer.purchase.addAll(Application.shop.currentCustomer.cartItems);
                        Application.shop.currentCustomer.wallet -= Long.valueOf(labelFinalCost.getText());
                        labelFinalCost.setText("0");
                        Database.updateCustomerWallet(Application.shop.currentCustomer);

                        for (int i = 0; i < Application.shop.currentCustomer.cartItems.size(); i++) {
                            for (int j = 0; j < Application.shop.sellers.size(); j++) {
                                for (int k = 0; k < Application.shop.sellers.get(j).allItems.size(); k++) {

                                    if (Application.shop.currentCustomer.cartItems.get(i) == Application.shop.sellers.get(j).allItems.get(k)) {

                                        Application.shop.sellers.get(j).wallet += Application.shop.currentCustomer.cartItems.get(i).price * Application.shop.currentCustomer.cartItems.get(i).tempSize;
                                        Database.updateSellerWallet(Application.shop.sellers.get(j));
                                        Application.shop.sellers.get(j).allItems.get(k).size -= Application.shop.currentCustomer.cartItems.get(i).tempSize;

                                        if (Application.shop.sellers.get(j).allItems.get(k).size == 0) {

                                            item = Application.shop.sellers.get(j).allItems.get(k);
                                            Application.shop.allItems.remove(Application.shop.sellers.get(j).allItems.get(k));
                                            Application.shop.sellers.get(j).allItems.remove(k);
                                            Database.removeProduct(item.sellerUsername, item.getCode());
                                        }
                                    }
                                }
                            }
                        }

                        Application.shop.currentCustomer.cartItems.clear();
                        for (int i = 0; i < Application.shop.currentCustomer.purchase.size(); i++) {
                            Database.addProduct("customer_purchase_", Application.shop.currentCustomer.purchase.get(i), Application.shop.currentCustomer.getUsername());
                        }
                    }
                    if (Application.shop.pageURL == "customer.fxml") {
                        Application.shop.currentCustomer.wallet += Long.valueOf(labelFinalCost.getText());
                        Database.updateCustomerWallet(Application.shop.currentCustomer);
                        labelFinalCost.setText("0");
                    }
                    txtBankPortalError.setStyle("-fx-text-fill: green;");
                    txtBankPortalError.setText("The purchase was made successfully");
                    //if (Application.shop.pageURL == "customer.fxml") ChangeScene2(e, "customer.fxml");
                    //if (Application.shop.pageURL == "seller.fxml") ChangeScene2(e, "seller.fxml");
                    return;
                } else
                    txtBankPortalError.setStyle("-fx-text-fill: red;");
                txtBankPortalError.setText("the balance is not enough");
            }
        }
    }
}
