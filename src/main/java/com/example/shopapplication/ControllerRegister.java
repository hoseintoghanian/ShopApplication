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
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class ControllerRegister {
    private static final String CHARACTERS = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz123456789";
    private static final int CAPTCHA_LENGTH = 6;
    private String captchaText;
    @FXML
    private TextField txtFirstname, txtLastname, txtPhoneNumber, txtUserName, txtPass, txtEmail, txtWorkPlace, txtConfirmPassword;
    @FXML
    private TextField txtCaptchaInput, txtLoginUsername, txtLoginPass;
    @FXML
    private Label txtCaptcha, txtRegister, txtLoginError;
    @FXML
    private RadioButton buttonSeller, buttonCustomer;
    @FXML
    Button buttonRegister;

    public void changeScene(ActionEvent e, String fxml) throws IOException {
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

    public void goSignUpScene(ActionEvent e) throws IOException {
        changeScene(e, "signUP.fxml");
    }

    public void goLoginScene(ActionEvent e) throws IOException {
        changeScene(e, "Login.fxml");
    }

    public void chooseApplicant() {
        if (buttonSeller.isSelected()) {
            txtWorkPlace.setVisible(true);
        } else if (buttonCustomer.isSelected()) {
            txtWorkPlace.setVisible(false);
        }
    }

    public void captcha() {
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

            if (Application.shop.admin.getUsername().equals(txtLoginUsername.getText()) && Application.shop.admin.getPassword().equals(txtLoginPass.getText())) {
                Application.shop.pageURL = "admin.fxml";
                changeScene(e, "admin.fxml");

            } else {
                if (Application.shop.sellers.contains(seller) || Application.shop.customers.contains(customer)) {

                    if (Application.shop.customers.contains(customer)) {

                        customer = Application.shop.customers.get(Application.shop.customers.indexOf(customer));

                        if (customer.getPassword().equals(txtLoginPass.getText())) {

                            Application.shop.pageURL = "customer.fxml";
                            Application.shop.currentCustomer = customer;
                            Application.shop.currentSeller = null;

                            if (txtCaptchaInput.getText().equals(captchaText)) {
                                changeScene(e, Application.shop.pageURL);
                            } else {
                                txtCaptchaInput.clear();
                                txtLoginError.setText("Incorrect captcha");
                            }

                        } else txtLoginError.setText("Password is incorrect");
                    }

                    if (Application.shop.sellers.contains(seller)) {

                        seller = Application.shop.sellers.get(Application.shop.sellers.indexOf(seller));

                        if (seller.getPassword().equals(txtLoginPass.getText())) {
                            if (seller.allowToLogin) {

                                Application.shop.pageURL = "seller.fxml";
                                Application.shop.currentSeller = seller;
                                Application.shop.currentCustomer = null;

                                if (txtCaptchaInput.getText().equals(captchaText)) {
                                    changeScene(e, Application.shop.pageURL);
                                } else {
                                    txtCaptchaInput.clear();
                                    txtLoginError.setText("Incorrect captcha");
                                }

                            } else txtLoginError.setText("You're not allow to login yet");

                        } else txtLoginError.setText("Password is incorrect");
                    }

                } else txtLoginError.setText("Username is invalid\nplease sign up first");
            }
        } else txtLoginError.setText("Please fill all the blanks");
    }

    public void Register() {

        String applicantKind = null;
        boolean validUsername = true;
        Seller seller = null;
        Customer customer = null;
        Random random = new Random();
        long randomNumber = random.nextLong(10) + 10;

        if (buttonSeller.isSelected()) applicantKind = "seller";
        if (buttonCustomer.isSelected()) applicantKind = "customer";

        if (registerCheck(applicantKind)) {

            seller = new Seller(txtFirstname.getText(), txtLastname.getText(), txtPhoneNumber.getText(), txtUserName.getText(), txtPass.getText(), txtEmail.getText(), txtWorkPlace.getText(), Long.valueOf(0), "customer.png", "", false);
            if (Application.shop.sellers.contains(seller) || Application.shop.customers.contains(new Customer(seller.getUsername())) || txtUserName.getText().equals("admin")) {
                validUsername = false;
            }

            customer = new Customer(txtFirstname.getText(), txtLastname.getText(), txtPhoneNumber.getText(), txtUserName.getText(), txtPass.getText(), txtEmail.getText(), Long.valueOf(1000), "customer.png");
            customer.discountCode = new ControllerApplicant.DiscountCode(randomNumber);
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

                    txtFirstname.setText("");
                    txtLastname.setText("");
                    txtPhoneNumber.setText("");
                    txtUserName.setText("");
                    txtPass.setText("");
                    txtEmail.setText("");
                    txtWorkPlace.setText("");
                    txtConfirmPassword.setText("");

                } catch (SQLException event) {
                    System.out.println("Connection failed: " + event.getMessage());
                    event.printStackTrace();
                }

            } else {
                txtRegister.setText("Invalid username");
            }
        }
    }

    private boolean registerCheck(String applicantKind) {

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
                txtRegister.setText("The entered number is invalid!\nThere are some characters in the number");
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

        if (!txtConfirmPassword.getText().equals(txtPass.getText())) {
            txtRegister.setText("Confirm password and password\nare different !");
            return false;
        }

        if (!txtEmail.getText().endsWith("@gmail.com")) {
            txtRegister.setText("The email address is invalid! Please\nenter the address in the form \"*****@gmail.com\"");
            return false;
        }

        return true;
    }

    public void forgotPassword() {
        if (!txtLoginUsername.getText().equals("")) {

            Seller seller = new Seller(txtLoginUsername.getText());
            Customer customer = new Customer(txtLoginUsername.getText());

            if (Application.shop.customers.contains(customer)) {
                customer = Application.shop.customers.get(Application.shop.customers.indexOf(customer));
                new Email(customer.getEmail(), "your password is : " + customer.getPassword());
            }
            if (Application.shop.sellers.contains(seller)) {
                seller = Application.shop.sellers.get(Application.shop.sellers.indexOf(seller));
                new Email(seller.getEmail(), "your password is : " + seller.getPassword());
            }

            txtLoginError.setText("We have sent your\npassword to your Gmail");

        } else txtLoginError.setText("enter your username !");
    }
}