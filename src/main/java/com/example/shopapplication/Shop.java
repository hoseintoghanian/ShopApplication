package com.example.shopapplication;

import java.util.*;

public class Shop {

    private static Shop instance = new Shop();

    private Shop() {
    }

    public static Shop getInstance() {
        return instance;
    }


    public Admin admin = new Admin("admin", "5581");


    public Seller currentSeller;
    public Customer currentCustomer;
    public Item currentItem;


    public ArrayList<Seller> sellers = new ArrayList<>();
    public ArrayList<Customer> customers = new ArrayList<>();


    public ArrayList<Item> allItems = new ArrayList<>();

    String pageURL;

}
