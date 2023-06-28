package com.example.shopapplication;

import javafx.scene.image.Image;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Item {
    String kind;
    String minorKind;
    String brand;
    String name;
    long price;
    int size;
    double score;
    private int[] emojiNumber = new int[5];//add to database
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

        for (int i = 0; i < 5; i++) emojiNumber[i] = 0;

        this.uploadDate = LocalDateTime.now();
        this.image = image;

        calculateScore(-1);
    }

    public Item(String kind, String minorKind, String brand, String name, long price, int size, double score, Object uploadDate, String imageURL) {
        this.kind = kind;
        this.minorKind = minorKind;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.size = size;
        this.score = score;
        this.uploadDate = (LocalDateTime) uploadDate;
        this.image = new Image(imageURL);
    }


    public void calculateScore(int vote) {//vote range is between 0 to 4 // 0 means the best emoji and 4 means the worst

        for (int i = 0; i < 5; i++) {
            if (vote == i) emojiNumber[i]++;
        }

        int allVotes = 0, max = 0, maxIndex = -1;
        for (int i = 0; i < 5; i++) {
            if (emojiNumber[i] > max) {
                max = emojiNumber[i];
                maxIndex = i;
            }
            allVotes += emojiNumber[i];
        }


        if (allVotes != 0) score = (max / allVotes) * 100;

        switch (maxIndex) {
            case -1:
                scoreEmoji = new Image("2.png");
                break;
            case 0:
                scoreEmoji = new Image("0.png");
                break;
            case 1:
                scoreEmoji = new Image("1.png");
                break;
            case 2:
                scoreEmoji = new Image("2.png");
                break;
            case 3:
                scoreEmoji = new Image("3.png");
                break;
            case 4:
                scoreEmoji = new Image("4.png");
                break;
        }
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