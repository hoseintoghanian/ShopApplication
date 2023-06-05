package com.example.shopapplication;

import java.util.*;

public class Customer extends Applicant {

    //yek arraylist baray sabad kharid bezan

    public long wallet;

    //yek arraylist baraye buy history bezan
    ArrayList<String> discountCode;

    public Customer(String firstname, String lastname, String phoneNumber, String username, String password, String email) {
        super(firstname, lastname, phoneNumber, username, password, email);
        applicantKind = "customer";
    }
    public Customer(String username) {
        super(username);
    }

    public boolean equals(Object other) {
        if (other instanceof Customer) {
            Customer c = (Customer) other;
            return Objects.equals(this.getUsername(), c.getUsername());
        }
        return false;
    }

}
