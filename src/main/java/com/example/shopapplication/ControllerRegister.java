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
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
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
            String URL = "";


            if (Application.shop.admin.getUsername().equals(txtLoginUsername.getText())) {
                URL = "admin.fxml";

            } else {

                if (Application.shop.sellers.contains(seller)) {
                    seller = Application.shop.sellers.get(Application.shop.sellers.indexOf(seller));
                    URL = "seller.fxml";
                    Application.shop.currentSeller = seller;
                    Application.shop.currentCustomer = null;
                }
                if (Application.shop.customers.contains(customer)) {
                    customer = Application.shop.customers.get(Application.shop.customers.indexOf(customer));
                    URL = "customer.fxml";
                    Application.shop.currentCustomer = customer;
                    Application.shop.currentSeller = null;
                }
            }


            if (Objects.equals(Application.shop.admin.getPassword(), txtLoginPass.getText()) ||
                    Objects.equals(seller.getPassword(), txtLoginPass.getText()) ||
                    Objects.equals(customer.getPassword(), txtLoginPass.getText())
            ) {
                //if (txtCaptchaInput.getText().equals(captchaText)) {
                fxmlLoader = new FXMLLoader(Application.class.getResource(URL));
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

        if (!txtFirstname.getText().equals("") && !txtLastname.getText().equals("") && !txtPhoneNumber.getText().equals("") && !txtUserName.getText().equals("") && !txtPass.getText().equals("") && !txtEmail.getText().equals("")) {
            if (txtConfirmPassword.getText().equals(txtPass.getText())) {
                try {
                    if (buttonSeller.isSelected() && !txtWorkPlace.getText().equals("")) {
                        applicantKind = "Seller";
                        seller = new Seller(txtFirstname.getText(), txtLastname.getText(), txtPhoneNumber.getText(), txtUserName.getText(), txtPass.getText(), txtEmail.getText(), txtWorkPlace.getText());
                        if (Application.shop.sellers.contains(seller) || Application.shop.customers.contains(new Customer(seller.getUsername())) || txtUserName.getText().equals("admin")) {
                            applicantKind = null;
                            validUsername = false;
                        }
                    }
                    if (buttonCustomer.isSelected()) {
                        applicantKind = "Customer";
                        customer = new Customer(txtFirstname.getText(), txtLastname.getText(), txtPhoneNumber.getText(), txtUserName.getText(), txtPass.getText(), txtEmail.getText());
                        if (Application.shop.customers.contains(customer) || Application.shop.sellers.contains(new Seller(customer.getUsername())) || txtUserName.getText().equals("admin")) {
                            applicantKind = null;
                            validUsername = false;
                        }
                    }

                    if (!validUsername) txtRegister.setText("invalid username");

                    if (applicantKind != null) {
                        if (applicantKind.equals("Seller")) {
                            Application.shop.sellers.add(seller);//must not delete
                            Database.writeSeller(seller);
                            txtRegister.setText("Registered Successfully");
                        }
                        if (applicantKind.equals("Customer")) {
                            Application.shop.customers.add(customer);//must not delete
                            Database.writeCustomer(customer);
                            txtRegister.setText("Registered Successfully");
                        }
                    }

                } catch (SQLException event) {
                    System.out.println("Connection failed: " + event.getMessage());
                }
            } else if (!txtConfirmPassword.getText().equals(txtPass.getText())) {
                txtRegister.setText("incorrect confirm password");
            }
        } else {
            txtRegister.setText("please fill all the fields");
        }
    }
}