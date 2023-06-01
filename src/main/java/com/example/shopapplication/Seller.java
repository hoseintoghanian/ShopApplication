package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class Seller extends Applicant {

    public String workplace;

    public Seller(String firstname, String lastname, String phoneNumber, String username, String password, String email, String workplace) {
        super(firstname, lastname, phoneNumber, username, password, email);
        this.workplace = workplace;
        applicantKind = "Seller";
    }

    public boolean equals(Object other) {
        if (other instanceof Seller) {
            Seller s = (Seller) other;
            return this.getUsername() == s.getUsername();
        }
        return false;
    }

}
