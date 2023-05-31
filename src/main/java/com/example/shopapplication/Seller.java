package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class Seller extends Applicant {

    public String workplace;

    public Seller(String firstname, String lastname, String  phoneNumber,String username,String password, String workplace) {
        super(firstname, lastname, phoneNumber,username,password);
        applicantKind = "Seller";

        this.workplace = workplace;
    }

}
