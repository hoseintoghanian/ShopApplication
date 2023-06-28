package com.example.shopapplication;

import javafx.scene.image.Image;

import java.time.LocalDateTime;

public class Item {
    private static int count;//should write in database

    private int code;
    String kind;
    String minorKind;
    String brand;
    String name;
    long price;
    int size;
    String property;
    double score;
    int[] emojiNumber = new int[5];
    LocalDateTime uploadDate;
    Image image;
    Image scoreEmoji;
    String sellerUsername;

    public Item(String kind, String minorKind, String brand, String name, long price, int size, String property, Image image, String sellerUsername) {

        code = count;
        count++;

        this.kind = kind;
        this.minorKind = minorKind;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.size = size;
        this.property = property;

        this.score = 0;

        for (int i = 0; i < 5; i++) emojiNumber[i] = 0;

        this.uploadDate = LocalDateTime.now();
        this.image = image;
        scoreEmoji = new Image("2.png");

        this.sellerUsername = sellerUsername;
    }

    public Item(int code, String kind, String minorKind, String brand, String name, long price, int size, String property,
                double score, int e0, int e1, int e2, int e3, int e4,
                Object uploadDate, String imageURL, String scoreEmojiURL, String sellerUsername) {

        count++;

        this.code = code;
        this.kind = kind;
        this.minorKind = minorKind;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.size = size;
        this.property = property;
        this.score = score;
        emojiNumber[0] = e0;
        emojiNumber[1] = e1;
        emojiNumber[2] = e2;
        emojiNumber[3] = e3;
        emojiNumber[4] = e4;
        this.uploadDate = (LocalDateTime) uploadDate;
        this.image = new Image(imageURL);
        this.scoreEmoji = new Image(scoreEmojiURL);
        this.sellerUsername = sellerUsername;

        calculateScore(-1);
    }

    public int getCode() {
        return code;
    }


    public void calculateScore(int vote) {//vote range is between 0 to 4 // 0 means the best emoji and 4 means the worst

        for (int i = 0; i < 5; i++) if (vote == i) emojiNumber[i]++;


        double allVotes = 0, max = 0, maxIndex = -1;
        for (int i = 0; i < 5; i++) {
            if (emojiNumber[i] > max) {
                max = emojiNumber[i];
                maxIndex = i;
            }
            allVotes += emojiNumber[i];
        }

        if (allVotes != 0) score = (max / allVotes) * 100;

        switch ((int) maxIndex) {
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
        return name + " " + code + " " + sellerUsername;
    }

    public boolean equals(Object o) {
        if (o instanceof Item) {
            Item other = (Item) o;
            if (name.equals(other.name) && brand.equals(other.brand)) return true;
        }
        return false;
    }

}