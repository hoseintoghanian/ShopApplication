package com.example.shopapplication;

public class Admin extends Applicant {
    public Admin(String username, String password) {
        super(username);
        setPassword(password);
    }
}
