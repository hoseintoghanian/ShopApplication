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
    public ArrayList<Item> tempItems = new ArrayList<>();
    String pageURL;


    public static void SortByDate(ArrayList<Item> allItems, ArrayList<Item> tempItems) {
        Collections.sort(allItems, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.uploadDate.compareTo(item2.uploadDate);
            }
        });

        tempItems.clear();
        tempItems.addAll(allItems);
    }

    public static void SortByKind(ArrayList<Item> allItems, ArrayList<Item> tempItems, String kind) {

        tempItems.clear();

        for (int i = 0; i < allItems.size(); i++) {
            if (allItems.get(i).kind.equals(kind) || allItems.get(i).minorKind.equals(kind))
                tempItems.add(allItems.get(i));
        }
    }

    public static void SortByBrand() {

    }


    public static void SortByScore(ArrayList<Item> items) {

    }

    public static void SortByPrice(ArrayList<Item> items) {

    }
}
