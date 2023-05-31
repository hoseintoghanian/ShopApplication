package com.example.shopapplication;

public class Seller extends Applicant {

    public String workplace;

    public Seller(String firstname, String lastname, String  phoneNumber, String workplace) {
        super(firstname, lastname, phoneNumber);
        applicantKind = "Seller";

        this.workplace = workplace;
    }
}
