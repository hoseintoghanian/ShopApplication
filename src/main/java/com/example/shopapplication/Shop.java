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
    public ArrayList<Item> tempitems = new ArrayList<>();
    String pageURL;


    public void SortByDate() {
        Collections.sort(allItems, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.uploadDate.compareTo(item2.uploadDate);
            }
        });
    }



}
