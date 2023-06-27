package com.example.shopapplication;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {
    String kind;
    String minorKind;
    String brand;
    String name;
    long price;
    int size;
    Image image;

    public Item(String kind, String minorKind, String brand, String name, long price, int size, Image image) {
        this.kind = kind;
        this.minorKind = minorKind;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.size = size;
        this.image = image;
    }

    public Item(String kind, String minorKind, String brand, String name, long price, int size, String imageURL) {
        this.kind = kind;
        this.minorKind = minorKind;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.size = size;
        this.image = new Image(imageURL);
    }


    public String toString() {
        return kind + " " + minorKind + " " + brand + " " + name + " " + price + " " + size;
    }

    public boolean equals(Object o) {
        if (o instanceof Item) {
            Item other = (Item) o;
            if (name.equals(other.name) && brand.equals(other.brand)) return true;
        }
        return false;
    }
}