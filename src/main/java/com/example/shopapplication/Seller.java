package com.example.shopapplication;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;

public class Seller extends Applicant {
    public String workplace;
    public long wallet;
    public long increaseamount = 0;
    public String chat;
    public Item auction;
    public boolean allowToLogin;


    public ArrayList<Item> allItems = new ArrayList<>();
    public ArrayList<Item> tempItems = new ArrayList<>();

    public Seller(String firstname, String lastname, String phoneNumber, String username, String password, String email, String workplace, Long walletBalance, String imageURL, String chat, boolean allowToLogin) {
        super(firstname, lastname, phoneNumber, username, password, email);
        this.workplace = workplace;
        this.wallet = walletBalance;
        this.image = new Image(imageURL);
        applicantKind = "seller";
        this.chat = chat;
        this.allowToLogin = allowToLogin;


        tempItems.addAll(allItems);
    }

    public Seller(String username) {
        super(username);
    }

    public boolean equals(Object other) {
        if (other instanceof Seller) {
            Seller s = (Seller) other;
            return Objects.equals(this.getUsername(), s.getUsername());
        }
        return false;
    }
}
