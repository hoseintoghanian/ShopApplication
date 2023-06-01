package com.example.shopapplication;

import java.util.*;

public class Shop {

    private static Shop instance = new Shop();

    private Shop() {
    }

    public static Shop getInstance() {
        return instance;
    }


    public Admin admin = new Admin("Admin", "Admin", "09927930896", "Admin1", "1234", "Admin@gamil.com");
    public ArrayList<Seller> sellers = new ArrayList<>();
    public ArrayList<Customer> customers = new ArrayList<>();

    public ArrayList<Item> items = new ArrayList<>();

}
