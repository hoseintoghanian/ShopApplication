package com.example.shopapplication;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;

public class Seller extends Applicant {

    public String workplace;

    public ArrayList<Item> allItems = new ArrayList<>();
    public ArrayList<Item> tempItems = new ArrayList<>();
    public Item auction;
    public String chat;
    public long wallet;


    public Seller(String firstname, String lastname, String phoneNumber, String username, String password, String email, String workplace, Long walletbalance, String imageurl, String chat) {
        super(firstname, lastname, phoneNumber, username, password, email);
        this.workplace = workplace;
        this.wallet = walletbalance;
        //File file = new File(imageurl);
        this.image = new Image(imageurl);
        applicantKind = "seller";
        this.chat = chat;

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
