package com.example.shopapplication;

import javafx.scene.image.Image;

import java.time.LocalDateTime;

public class Item {
    String kind;
    String minorKind;
    String brand;
    String name;
    long price;
    int size;
    double score=68.23;
    LocalDateTime uploadDate;
    Image image;
    Image scoreEmoji;//based on score


    public Item(String kind, String minorKind, String brand, String name, long price, int size, Image image) {
        this.kind = kind;
        this.minorKind = minorKind;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.size = size;
        //this.score = 0;
        this.uploadDate = LocalDateTime.now();
        this.image = image;
    }

    public Item(String kind, String minorKind, String brand, String name, long price, int size, double score, Object uploadDate, String imageURL) {
        this.kind = kind;
        this.minorKind = minorKind;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.size = size;
        //this.score = score;
        this.uploadDate = (LocalDateTime) uploadDate;
        this.image = new Image(imageURL);
    }

    public String toString() {
        return kind + " " + minorKind + " " + brand + " " + name + " " + price + " " + size + " " + score + " " + uploadDate;
    }

    public boolean equals(Object o) {
        if (o instanceof Item) {
            Item other = (Item) o;
            if (name.equals(other.name) && brand.equals(other.brand)) return true;
        }
        return false;
    }
}