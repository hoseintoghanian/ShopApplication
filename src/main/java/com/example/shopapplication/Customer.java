package com.example.shopapplication;

import javafx.scene.image.Image;

import java.util.*;

public class Customer extends Applicant {
    public long wallet;
    public long increaseAmount = 0;
    ArrayList<Item> AuctionItems = new ArrayList<>();
    ArrayList<Item> cartItems = new ArrayList<>();
    ArrayList<Item> purchase = new ArrayList<>();

    public Customer(String firstname, String lastname, String phoneNumber, String username, String password, String email, Long wallet, String imageurl) {
        super(firstname, lastname, phoneNumber, username, password, email);
        this.wallet=wallet;
        this.image=new Image(imageurl);
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