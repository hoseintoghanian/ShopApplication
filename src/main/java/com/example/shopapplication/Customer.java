package com.example.shopapplication;

public class Customer extends Applicant {

    //yek arraylist baray sabad kharid bezan

    public long wallet;

    //yek arraylist baraye buy history bezan
    //yek arraylist baraye liste kod haye takhfif bezan

    public Customer(String firstname, String lastname, long phoneNumber) {
        super(firstname, lastname, phoneNumber);
        applicantKind = "Customer";
    }
}
