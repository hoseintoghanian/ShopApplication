package com.example.shopapplication;

import java.util.*;

public class Customer extends Applicant {

    //yek arraylist baray sabad kharid bezan

    public long wallet;

    //yek arraylist baraye buy history bezan
    ArrayList<String> discountCode;

    public Customer(String firstname, String lastname, String  phoneNumber,String username,String password) {
        super(firstname, lastname, phoneNumber,username,password);
        applicantKind = "Customer";
    }

}
