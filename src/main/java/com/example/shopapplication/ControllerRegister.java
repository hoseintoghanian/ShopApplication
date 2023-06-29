package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.sql.*;

public class ControllerRegister {
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private static final String CHARACTERS = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz123456789";
    private static final int CAPTCHA_LENGTH = 6;
    private String captchaText;
    @FXML
    private TextField txtFirstname, txtLastname, txtPhoneNumber, txtUserName, txtPass, txtEmail, txtWorkPlace, txtConfirmPassword;
    @FXML
    private TextField txtCaptchaInput, txtLoginUsername, txtLoginPass;
    @FXML
    private Label txtCaptcha, txtRegister;
    @FXML
    private RadioButton buttonSeller, buttonCustomer;
    @FXML
    Button buttonRegister;
    @FXML
    private Hyperlink txtLogOut, txtLogout;


    public void changeScene(ActionEvent e, String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }


    public void goSignUpScene(ActionEvent e) throws IOException {
        changeScene(e, "signUP.fxml");
    }

    public void goLoginScene(ActionEvent e) throws IOException {
        changeScene(e, "Login.fxml");
    }

    public void chooseApplicant(ActionEvent e) {
        if (buttonSeller.isSelected()) {
            txtWorkPlace.setVisible(true);
        } else if (buttonCustomer.isSelected()) {
            txtWorkPlace.setVisible(false);
        }
    }

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

    public void login(ActionEvent e) throws IOException {

        if (!txtLoginUsername.getText().equals("") && !txtLoginPass.getText().equals("")) {

            Seller seller = new Seller(txtLoginUsername.getText());
            Customer customer = new Customer(txtLoginUsername.getText());


            if (Application.shop.admin.getUsername().equals(txtLoginUsername.getText())) {
                Application.shop.pageURL = "admin.fxml";

            } else {

                if (Application.shop.sellers.contains(seller)) {
                    seller = Application.shop.sellers.get(Application.shop.sellers.indexOf(seller));
                    Application.shop.pageURL = "seller.fxml";
                    Application.shop.currentSeller = seller;
                    Application.shop.currentCustomer = null;
                }
                if (Application.shop.customers.contains(customer)) {
                    customer = Application.shop.customers.get(Application.shop.customers.indexOf(customer));
                    Application.shop.pageURL = "customer.fxml";
                    Application.shop.currentCustomer = customer;
                    Application.shop.currentSeller = null;
                }
            }


            if (Objects.equals(Application.shop.admin.getPassword(), txtLoginPass.getText()) ||
                    Objects.equals(seller.getPassword(), txtLoginPass.getText()) ||
                    Objects.equals(customer.getPassword(), txtLoginPass.getText())
            ) {
                //if (txtCaptchaInput.getText().equals(captchaText)) {
                fxmlLoader = new FXMLLoader(Application.class.getResource(Application.shop.pageURL));
                stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                stage.show();
                //} else {
                //txtCaptchaInput.clear();
                //}
            }
        }
    }


    public void Register() {

        String applicantKind = null;
        boolean validUsername = true;
        Seller seller = null;
        Customer customer = null;


        if (buttonSeller.isSelected()) applicantKind = "seller";
        if (buttonCustomer.isSelected()) applicantKind = "customer";

        //if (registerCheck(applicantKind)) {

            seller = new Seller(txtFirstname.getText(), txtLastname.getText(), txtPhoneNumber.getText(), txtUserName.getText(), txtPass.getText(), txtEmail.getText(), txtWorkPlace.getText());
            if (Application.shop.sellers.contains(seller) || Application.shop.customers.contains(new Customer(seller.getUsername())) || txtUserName.getText().equals("admin")) {
                validUsername = false;
            }

            customer = new Customer(txtFirstname.getText(), txtLastname.getText(), txtPhoneNumber.getText(), txtUserName.getText(), txtPass.getText(), txtEmail.getText());
            if (Application.shop.customers.contains(customer) || Application.shop.sellers.contains(new Seller(customer.getUsername())) || txtUserName.getText().equals("admin")) {
                validUsername = false;
            }


            if (validUsername) {

                try {

                    if (applicantKind.equals("seller")) {
                        Application.shop.sellers.add(seller);
                        Database.writeSeller(seller);
                        txtRegister.setText("Registered Successfully");
                    }
                    if (applicantKind.equals("customer")) {
                        Application.shop.customers.add(customer);
                        Database.writeCustomer(customer);
                        txtRegister.setText("Registered Successfully");
                    }

                } catch (SQLException event) {
                    //System.out.println("Connection failed: " + event.getMessage());
                    event.printStackTrace();
                }

            } else {
                txtRegister.setText("Invalid username");
            }
        //}


    }


    /*private boolean registerCheck(String applicantKind) {

        if (applicantKind == null) {
            txtRegister.setText("choose seller or customer !");
            return false;
        }


        if (applicantKind.equals("customer")) {
            if (txtFirstname.getText().equals("") || txtLastname.getText().equals("") || txtPhoneNumber.getText().equals("") || txtUserName.getText().equals("") || txtPass.getText().equals("") || txtEmail.getText().equals("")) {
                txtRegister.setText("Please fill all the fields !");
                return false;
            }
        }

        if (applicantKind.equals("seller")) {
            if (txtFirstname.getText().equals("") || txtLastname.getText().equals("") || txtPhoneNumber.getText().equals("") || txtUserName.getText().equals("") || txtPass.getText().equals("") || txtEmail.getText().equals("") || txtWorkPlace.getText().equals("")) {
                txtRegister.setText("Please fill all the fields !");
                return false;
            }
        }


        if (!txtConfirmPassword.getText().equals(txtPass.getText())) {
            txtRegister.setText("Confirm password and password\nare different !");
            return false;
        }


        if (txtFirstname.getText().length() < 3) {
            txtRegister.setText("First name is too short!\nPlease enter at least 3 characters");
            return false;
        }

        for (int i = 0; i < txtFirstname.getText().length(); i++) {
            if (txtFirstname.getText().charAt(i) <= 64 || (txtFirstname.getText().charAt(i) <= 96
                    && txtFirstname.getText().charAt(i) >= 91) || txtFirstname.getText().charAt(i) >= 123) {
                txtRegister.setText("Please do not enter a number in the first name!");
                return false;
            }
        }

        if (txtLastname.getText().length() < 3) {
            txtRegister.setText("Last name is too short!\nPlease enter at least 3 characters");
            return false;
        }

        for (int i = 0; i < txtLastname.getText().length(); i++) {
            if (txtLastname.getText().charAt(i) <= 64 || (txtLastname.getText().charAt(i) <= 96
                    && txtLastname.getText().charAt(i) >= 91) || txtLastname.getText().charAt(i) >= 123) {
                txtRegister.setText("Please do not enter a number in the last name!");
                return false;
            }
        }

        if (txtPhoneNumber.getText().length() != 11) {
            txtRegister.setText("The number entered is invalid!\nPlease enter an 11-digit mobile number");
            return false;
        }

        for (int i = 0; i < 11; i++) {
            if (txtPhoneNumber.getText().charAt(i) < 48 || txtPhoneNumber.getText().charAt(i) > 57) {
                txtRegister.setText("The entered number is invalid!\nThere are invalid characters in the number");
                return false;
            }
        }

        if (txtUserName.getText().length() < 8) {
            txtRegister.setText("Username is too short!\nPlease enter at least 8 characters");
            return false;
        }

        if (txtPass.getText().length() < 4) {
            txtRegister.setText("Password is too weak!\nPlease enter at least 4 characters");
            return false;
        }

        if (!txtEmail.getText().endsWith("@gmail.com")) {
            txtRegister.setText("The email address is invalid! Please\nenter the address in the form \"*****@gmail.com\"");
            return false;
        }

        return true;
    }*/
}