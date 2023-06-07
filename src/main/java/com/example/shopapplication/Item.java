package com.example.shopapplication;

import javafx.scene.image.ImageView;

public class Item {
    String kind;
    String minorKind;
    String brand;
    String name;
    long price;
    int size;
    ImageView image;

    public Item(String kind, String minorKind, String brand, String name, long price, int size, String imageURL) {
        this.kind = kind;
        this.minorKind = minorKind;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.size = size;
        image = new ImageView(imageURL);
    }
}
