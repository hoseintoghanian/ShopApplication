package com.example.shopapplication;

public class Admin extends Applicant{
    public Admin(String firstname, String lastname,String  phoneNumber){
        super(firstname,lastname,phoneNumber);
        applicantKind = "Admin";
    }
}
