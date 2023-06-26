package com.example.shopapplication;

import javafx.scene.image.ImageView;

public class Item {
    String kind;
    String minorKind;
    String brand;
    String name;
    long price;
    int size;
    String imageURL;
    ImageView image;

    public Item(String kind, String minorKind, String brand, String name, long price, int size, String imageURL) {
        this.kind = kind;
        this.minorKind = minorKind;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.size = size;
        this.imageURL = imageURL;
        //image = new ImageView(imageURL);
    }


    public String toString() {
        return kind + " " + minorKind + " " + brand + " " + name + " " + price + " " + size + " " + imageURL;
    }

    public boolean equals(Object o) {
        if (o instanceof Item) {
            Item other = (Item) o;
            if (name.equals(other.name) && brand.equals(other.brand)) return true;
        }
        return false;
    }
}
