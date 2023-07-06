package com.example.shopapplication;

import javafx.scene.image.Image;

public abstract class Applicant {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String username;
    private String password;
    private String email;
    public String applicantKind;
    public Image image;

    public Applicant(String firstname, String lastname, String phoneNumber, String username, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Applicant(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String toString() {
        return firstname + " " + lastname + " " + phoneNumber;
    }
}