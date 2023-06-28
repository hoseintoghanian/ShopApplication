package com.example.shopapplication;

import java.util.ArrayList;
import java.util.Objects;

public class Seller extends Applicant {

    public String workplace;

    public ArrayList<Item> items = new ArrayList<>();


    public Seller(String firstname, String lastname, String phoneNumber, String username, String password, String email, String workplace) {
        super(firstname, lastname, phoneNumber, username, password, email);
        this.workplace = workplace;
        applicantKind = "seller";
    }
    public Seller(String username) {
        super(username);
    }

    public boolean equals(Object other) {
        if (other instanceof Seller) {
            Seller s = (Seller) other;
            return Objects.equals(this.getUsername(), s.getUsername());
        }
        return false;
    }

}
