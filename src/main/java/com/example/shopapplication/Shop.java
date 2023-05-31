package com.example.shopapplication;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

import java.util.*;

public class Shop {

    private static Shop instance = new Shop();

    private Shop() {
    }

    public static Shop getInstance() {
        return instance;
    }


    public Admin admin = new Admin("Admin","Admin","09927930896","Admin_a","1234");
    public List<Seller> sellers = new ArrayList<>();
    public List<Customer> customers = new ArrayList<>();

}
