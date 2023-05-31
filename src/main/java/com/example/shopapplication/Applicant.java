package com.example.shopapplication;

public class Applicant {

    private final String firstname;
    private final String lastname;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    public String applicantKind;


    public Applicant(String firstname, String lastname, String phoneNumber, String username, String password) {//username & password & email ro toye sign up daryaft mikone
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
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