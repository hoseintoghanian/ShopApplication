package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerProduct {

    public void changingScene(ActionEvent e, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void back(ActionEvent e) throws IOException {
        if (Application.shop.currentCustomer != null) changingScene(e, "customer.fxml");
        if (Application.shop.currentSeller != null) changingScene(e, "seller.fxml");
    }


    @FXML
    private Label nameLabel, brandLabel, priceLabel, sizeLabel, scoreLabel, propertyLabel;
    @FXML
    private ImageView emojiscore;

    public void displayInfo() {
        nameLabel.setText("Name          :   " + Application.shop.currentItem.name);
        brandLabel.setText("Brand          :   " + Application.shop.currentItem.brand);
        priceLabel.setText("Price            :   " + Application.shop.currentItem.price);
        sizeLabel.setText("Size              :   " + Application.shop.currentItem.size);
        scoreLabel.setText("Score            :   " + Application.shop.currentItem.score + "%");
        propertyLabel.setText("Properties   :   " + Application.shop.currentItem.property);
        emojiscore.setImage(Application.shop.currentItem.scoreEmoji);
    }


    @FXML
    private ImageView emoji0, emoji1, emoji2, emoji3, emoji4;
    private boolean isClicked;

    public void emojiClick() {

        if (!isClicked) {

            emoji0.setOnMouseClicked(ev -> {
                emojiProperty(emoji0, emoji1, emoji2, emoji3, emoji4);
            });
            emoji1.setOnMouseClicked(ev -> {
                emojiProperty(emoji1, emoji0, emoji2, emoji3, emoji4);
            });
            emoji2.setOnMouseClicked(ev -> {
                emojiProperty(emoji2, emoji0, emoji1, emoji3, emoji4);
            });
            emoji3.setOnMouseClicked(ev -> {
                emojiProperty(emoji3, emoji0, emoji1, emoji2, emoji4);
            });
            emoji4.setOnMouseClicked(ev -> {
                emojiProperty(emoji4, emoji0, emoji1, emoji2, emoji3);
            });
        }

        isClicked = true;
    }

    public void emojiEntered() {

        if (!isClicked) {

            emoji0.setOnMouseEntered(ev -> {
                if (!isClicked) emojiProperty(emoji0, emoji1, emoji2, emoji3, emoji4);
            });
            emoji1.setOnMouseEntered(ev -> {
                if (!isClicked) emojiProperty(emoji1, emoji0, emoji2, emoji3, emoji4);
            });
            emoji2.setOnMouseEntered(ev -> {
                if (!isClicked) emojiProperty(emoji2, emoji0, emoji1, emoji3, emoji4);
            });
            emoji3.setOnMouseEntered(ev -> {
                if (!isClicked) emojiProperty(emoji3, emoji0, emoji1, emoji2, emoji4);
            });
            emoji4.setOnMouseEntered(ev -> {
                if (!isClicked) emojiProperty(emoji4, emoji0, emoji1, emoji2, emoji3);
            });


            emoji0.setOnMouseExited(ev -> {
                if (!isClicked) emoji0.setOpacity(0.5);
            });
            emoji1.setOnMouseExited(ev -> {
                if (!isClicked) emoji1.setOpacity(0.5);
            });
            emoji2.setOnMouseExited(ev -> {
                if (!isClicked) emoji2.setOpacity(0.5);
            });
            emoji3.setOnMouseExited(ev -> {
                if (!isClicked) emoji3.setOpacity(0.5);
            });
            emoji4.setOnMouseExited(ev -> {
                if (!isClicked) emoji4.setOpacity(0.5);
            });
        }
    }

    private void emojiProperty(ImageView emoji0, ImageView emoji1, ImageView emoji2, ImageView emoji3, ImageView emoji4) {

        if (!voteButton.isDisable()) {

            emoji0.setOpacity(1);
            emoji0.setEffect(null);

            InnerShadow innerShadow = new InnerShadow();
            innerShadow.setWidth(10);
            innerShadow.setHeight(10);

            emoji1.setOpacity(0.3);
            emoji1.setEffect(innerShadow);
            emoji2.setOpacity(0.3);
            emoji2.setEffect(innerShadow);
            emoji3.setOpacity(0.3);
            emoji3.setEffect(innerShadow);
            emoji4.setOpacity(0.3);
            emoji4.setEffect(innerShadow);
        }
    }


    @FXML
    private Button voteButton;

    public void vote() throws SQLException {

        if (emoji0.getOpacity() == 1) {
            Application.shop.currentItem.calculateScore(0);
            Database.updateItem(Application.shop.currentItem, "e0", 0);
        }
        if (emoji1.getOpacity() == 1) {
            Application.shop.currentItem.calculateScore(1);
            Database.updateItem(Application.shop.currentItem, "e1", 1);
        }
        if (emoji2.getOpacity() == 1) {
            Application.shop.currentItem.calculateScore(2);
            Database.updateItem(Application.shop.currentItem, "e2", 2);
        }
        if (emoji3.getOpacity() == 1) {
            Application.shop.currentItem.calculateScore(3);
            Database.updateItem(Application.shop.currentItem, "e3", 3);
        }
        if (emoji4.getOpacity() == 1) {
            Application.shop.currentItem.calculateScore(4);
            Database.updateItem(Application.shop.currentItem, "e4", 4);
        }

        voteButton.setDisable(true);
        voteButton.setVisible(false);
    }


    @FXML
    private ImageView chatBackgroundImg;
    @FXML
    private ImageView imgTheme1, imgTheme2, imgTheme3, imgTheme4, imgTheme5, imgTheme6, imgTheme7;

    public void setChatBackground() {

        Image t1 = new Image("t1.png");
        Image t2 = new Image("t2.png");
        Image t3 = new Image("t3.png");
        Image t4 = new Image("t4.png");
        Image t5 = new Image("t5.png");
        Image t6 = new Image("t6.png");
        Image t7 = new Image("t7.png");

        imgTheme1.setOnMouseClicked(ev -> {
            chatBackgroundImg.setImage(t1);
        });
        imgTheme2.setOnMouseClicked(ev -> {
            chatBackgroundImg.setImage(t2);
        });
        imgTheme3.setOnMouseClicked(ev -> {
            chatBackgroundImg.setImage(t3);
        });
        imgTheme4.setOnMouseClicked(ev -> {
            chatBackgroundImg.setImage(t4);
        });
        imgTheme5.setOnMouseClicked(ev -> {
            chatBackgroundImg.setImage(t5);
        });
        imgTheme6.setOnMouseClicked(ev -> {
            chatBackgroundImg.setImage(t6);
        });
        imgTheme7.setOnMouseClicked(ev -> {
            chatBackgroundImg.setImage(t7);
        });
    }
}
