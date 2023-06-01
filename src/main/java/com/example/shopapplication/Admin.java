package com.example.shopapplication;

public class Admin extends Applicant {
    public Admin(String firstname, String lastname, String phoneNumber, String username, String password, String email) {
        super(firstname, lastname, phoneNumber, username, password, email);
        applicantKind = "Admin";
    }
}
