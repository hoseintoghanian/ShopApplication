package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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
    private TextField txtFirstname, txtLastname, txtPhoneNumber, txtUserName, txtPassWord, txtEmail, txtWorkPlace;
    @FXML
    private TextField txtCaptchaInput;
    @FXML
    private Label txtCaptcha, txtRegister;
    @FXML
    private RadioButton buttonSeller, buttonCustomer;


    public void changeScene(ActionEvent e, String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void goMainScene(ActionEvent e) throws IOException {
        if (txtCaptchaInput.getText().equals(captchaText)) {
            fxmlLoader = new FXMLLoader(Application.class.getResource("customer.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } else {
            txtCaptchaInput.clear();
        }
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

    public void Register() {

        String applicantKind = null;

        try {

            if (buttonSeller.isSelected()) {
                Application.shop.sellers.add(new Seller(txtFirstname.getText(), txtLastname.getText(), txtPhoneNumber.getText(), txtUserName.getText(), txtPassWord.getText(), txtEmail.getText(), txtWorkPlace.getText()));
                txtRegister.setText("Registered Successfully");

                applicantKind = "Seller";
            }
            if (buttonCustomer.isSelected()) {
                Application.shop.customers.add(new Customer(txtFirstname.getText(), txtLastname.getText(), txtPhoneNumber.getText(), txtUserName.getText(), txtPassWord.getText(), txtEmail.getText()));
                txtRegister.setText("Registered Successfully");

                applicantKind = "Customer";
            }


            String sql = "INSERT INTO applicant (firstname, lastname, phoneNumber, username, password, email, applicantKind) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = Database.getDBC().prepareStatement(sql);

            statement.setString(1, txtFirstname.getText());
            statement.setString(2, txtLastname.getText());
            statement.setString(3, txtPhoneNumber.getText());
            statement.setString(4, txtUserName.getText());
            statement.setString(5, txtPassWord.getText());
            statement.setString(6, txtEmail.getText());
            statement.setString(7, applicantKind);

            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected");

            statement.close();
            Database.getDBC().close();

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}