package com.example.shopapplication;

import java.util.*;

public class Shop {

    private static Shop instance = new Shop();

    private Shop() {
    }

    public static Shop getInstance() {
        return instance;
    }


    Admin admin = new Admin("Admin","Admin","09927930896");
    ArrayList<Seller> sellers = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();


}
