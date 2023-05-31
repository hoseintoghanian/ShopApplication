package com.example.shopapplication;

import java.util.*;

public class Shop {

    private static Shop instance = new Shop();

    private Shop() {
    }

    public static Shop getInstance() {
        return instance;
    }


    ArrayList<Admin> admins = new ArrayList<>();
    ArrayList<Seller> sellers = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();


}
