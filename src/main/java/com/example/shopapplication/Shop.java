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
    public Warehouse currentWarehouse;
    public Item currentItem;

    public ArrayList<Seller> sellers = new ArrayList<>();
    public ArrayList<Customer> customers = new ArrayList<>();
    public ArrayList<Warehouse> warehouses = new ArrayList<>();

    public ArrayList<Item> allItems = new ArrayList<>();
    public ArrayList<Item> tempItems = new ArrayList<>();

    String pageURL;


    public static void sortByDate(ArrayList<Item> allItems, ArrayList<Item> tempItems) {
        allItems.sort(new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item1.uploadDate.compareTo(item2.uploadDate);
            }
        });

        tempItems.clear();
        tempItems.addAll(allItems);
    }

    public static void sortByKind(ArrayList<Item> allItems, ArrayList<Item> tempItems, String kind) {
        tempItems.clear();

        for (int i = 0; i < allItems.size(); i++) {
            if (allItems.get(i).kind.equals(kind) || allItems.get(i).minorKind.equals(kind))
                tempItems.add(allItems.get(i));
        }
    }

    public static void sortByBrand(ArrayList<Item> allItems, ArrayList<Item> tempItems, String brand) {
        tempItems.clear();

        for (int i = 0; i < allItems.size(); i++) {
            if (allItems.get(i).brand.equals(brand)) tempItems.add(allItems.get(i));
        }
    }

    public static void sortByScore(ArrayList<Item> tempItems, int sort) {
        tempItems.sort(new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {

                double score1 = 0, score2 = 0;
                double max = 0, maxIndex = -1;

                for (int i = 0; i < 5; i++) {
                    if (item1.emojiNumber[i] > max) {
                        max = item1.emojiNumber[i];
                        maxIndex = i;
                    }
                }

                if (maxIndex == 0) score1 = item1.score * max * 2;
                if (maxIndex == 1) score1 = item1.score * max * 1;
                if (maxIndex == 2) score1 = item1.score * max * 0;
                if (maxIndex == 3) score1 = item1.score * max * (-1);
                if (maxIndex == 4) score1 = item1.score * max * (-2);

                max = 0;
                maxIndex = -1;
                for (int i = 0; i < 5; i++) {
                    if (item2.emojiNumber[i] > max) {
                        max = item2.emojiNumber[i];
                        maxIndex = i;
                    }
                }

                if (maxIndex == 0) score2 = item2.score * max * 2;
                if (maxIndex == 1) score2 = item2.score * max * 1;
                if (maxIndex == 2) score2 = item2.score * max * 0;
                if (maxIndex == 3) score2 = item2.score * max * (-1);
                if (maxIndex == 4) score2 = item2.score * max * (-2);


                if (sort == 1) {
                    if (score1 > score2) return 1;
                    if (score1 < score2) return -1;
                }
                if (sort == -1) {
                    if (score1 < score2) return 1;
                    if (score1 > score2) return -1;
                }
                return 0;//both are equal
            }
        });

    }

    public static void sortByPrice(ArrayList<Item> tempItems, int sort) {
        tempItems.sort(new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {

                if (sort == 1) {
                    if (item1.price > item2.price) return 1;
                    if (item1.price < item2.price) return -1;
                }
                if (sort == -1) {
                    if (item1.price < item2.price) return 1;
                    if (item1.price > item2.price) return -1;
                }
                return 0;
            }
        });
    }

    public static void sortByAuction(ArrayList<Item> allItems, ArrayList<Item> tempItems) {
        tempItems.clear();

        for (int i = 0; i < allItems.size(); i++) {
            if (allItems.get(i).isAuction) tempItems.add(allItems.get(i));
        }
    }

    public static void sortByName(ArrayList<Item> allItems, ArrayList<Item> tempItems, String name) {
        tempItems.clear();

        for (int i = 0; i < allItems.size(); i++) {
            if (allItems.get(i).name.equals(name)) tempItems.add(allItems.get(i));
        }
    }

}
