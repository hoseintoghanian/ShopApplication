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

            if (Application.shop.sellers.contains(seller)) {
                seller = Application.shop.sellers.get(Application.shop.sellers.indexOf(seller));
                URL = "seller.fxml";
            }
            if (Application.shop.customers.contains(customer)) {
                customer = Application.shop.customers.get(Application.shop.customers.indexOf(customer));
                URL = "customer.fxml";
            }

            if (Objects.equals(seller.getPassword(), txtLoginPass.getText()) || Objects.equals(customer.getPassword(), txtLoginPass.getText())) {
                if (txtCaptchaInput.getText().equals(captchaText)) {
                    fxmlLoader = new FXMLLoader(Application.class.getResource(URL));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                    stage.show();
                } else {
                    txtCaptchaInput.clear();
                }
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
                        if (Application.shop.sellers.contains(seller) || Application.shop.customers.contains(new Customer(seller.getUsername()))) {
                            applicantKind = null;
                            validUsername = false;
                        }
                    }
                    if (buttonCustomer.isSelected()) {
                        applicantKind = "Customer";
                        customer = new Customer(txtFirstname.getText(), txtLastname.getText(), txtPhoneNumber.getText(), txtUserName.getText(), txtPass.getText(), txtEmail.getText());
                        if (Application.shop.customers.contains(customer) || Application.shop.sellers.contains(new Seller(customer.getUsername()))) {
                            applicantKind = null;
                            validUsername = false;
                        }
                    }

                    if (!validUsername) txtRegister.setText("invalid username");

                    if (applicantKind != null) {

                        if (applicantKind.equals("Seller")) Application.shop.sellers.add(seller);
                        if (applicantKind.equals("Customer")) Application.shop.customers.add(customer);

                        txtRegister.setText("Registered Successfully");

                        String sql = "INSERT INTO applicant (firstname, lastname, phoneNumber, username, pass, email, applicantKind) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement statement = Database.getDBC().prepareStatement(sql);

                        statement.setString(1, txtFirstname.getText());
                        statement.setString(2, txtLastname.getText());
                        statement.setString(3, txtPhoneNumber.getText());
                        statement.setString(4, txtUserName.getText());
                        statement.setString(5, txtPass.getText());
                        statement.setString(6, txtEmail.getText());
                        statement.setString(7, applicantKind);

                        statement.executeUpdate();

                        statement.close();
                        Database.getDBC().close();
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