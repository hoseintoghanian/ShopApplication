package com.example.shopapplication;

public class Admin extends Applicant{
    public Admin(String firstname, String lastname,String  phoneNumber,String username,String password){
        super(firstname,lastname,phoneNumber,username,password);
        applicantKind = "Admin";
    }
}
