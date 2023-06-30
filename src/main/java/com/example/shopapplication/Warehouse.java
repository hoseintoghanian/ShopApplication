package com.example.shopapplication;

import java.util.*;

public class Warehouse {

    public String name;
    public String storeAdmin;
    public String address;
    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Item> inputs = new ArrayList<>();
    ArrayList<Item> outputs = new ArrayList<>();


    public Warehouse(String name, String storeAdmin, String address) {
        this.name = name;
        this.storeAdmin = storeAdmin;
        this.address = address;
    }


    public boolean equals(Object o) {
        if (o instanceof Warehouse) {
            Warehouse other = (Warehouse) o;
            if (name.equals(other.name) && storeAdmin.equals(other.storeAdmin) && address.equals(other.address))
                return true;
        }
        return false;
    }
}
