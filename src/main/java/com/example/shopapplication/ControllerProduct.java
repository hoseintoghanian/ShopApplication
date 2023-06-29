package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class ControllerProduct {

    public void changeScene(ActionEvent e, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void back(ActionEvent e) throws IOException {
        changeScene(e, Application.shop.pageURL);
    }

    public void backToCustomerPage(ActionEvent e) throws IOException {
        changeScene(e, "customer.fxml");
    }

    public void changeToPaymentScene(ActionEvent e) throws IOException {
        changeScene(e, "payment.fxml");
    }


    @FXML
    private Label nameLabel, brandLabel, priceLabel, sizeLabel, scoreLabel, propertyLabel;
    @FXML
    private ImageView emojiscore, itemImage;

    public void displayInfo() {
        nameLabel.setText("Name          :   " + Application.shop.currentItem.name);
        brandLabel.setText("Brand          :   " + Application.shop.currentItem.brand);
        priceLabel.setText("Price            :   " + Application.shop.currentItem.price);
        sizeLabel.setText("Size              :   " + Application.shop.currentItem.size);
        scoreLabel.setText("Score            :   " + Application.shop.currentItem.score + "%");
        propertyLabel.setText("Properties   :   " + Application.shop.currentItem.property);
        emojiscore.setImage(Application.shop.currentItem.scoreEmoji);
        itemImage.setImage(Application.shop.currentItem.image);
        comment.setText(Application.shop.currentItem.comments);
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
    private Label comment;
    @FXML
    private TextArea commentText;

    public void sendComments() throws SQLException {

        if (comment.getText().equals("")) {
            comment.setText(comment.getText() + Application.shop.currentCustomer.getUsername() + "  :\n" + commentText.getText());
        } else {
            comment.setText(comment.getText() + "\n\n" + Application.shop.currentCustomer.getUsername() + "  :\n" + commentText.getText());
        }

        commentText.setText("");
        Application.shop.currentItem.comments = comment.getText();
        Database.updateItem(Application.shop.currentItem);
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


    //------------------------cart section------------------------


    @FXML
    private AnchorPane cartPage;
    static int icount = 0;

    public void showItems() {

        cartPage.getChildren().clear();
        icount = 0;

        Application.shop.pageURL = "cart.fxml";


        for (int i = 0; i < Application.shop.currentCustomer.items.size(); i++) {

            int finalI = i;
            ImageView imageView = new ImageView(Application.shop.currentCustomer.items.get(i).image);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {

                    Item item = Application.shop.currentCustomer.items.get(finalI);
                    Application.shop.currentItem = item;

                    try {
                        FXMLLoader fxmlLoader = null;

                   /* if (Application.shop.currentSeller == null)
                        fxmlLoader = new FXMLLoader(Application.class.getResource("productCustomer.fxml"));
                    if (Application.shop.currentCustomer == null)
                        fxmlLoader = new FXMLLoader(Application.class.getResource("productSeller.fxml"));*/
                        fxmlLoader = new FXMLLoader(Application.class.getResource("product.fxml"));//have to remove

                        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        Scene scene = new Scene(fxmlLoader.load());
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);


            Label name = new Label(Application.shop.currentCustomer.items.get(i).name);
            name.setFont(new Font("Arial", 20));
            name.setLayoutX(200);
            name.setLayoutY(0);

            Label brand = new Label(Application.shop.currentCustomer.items.get(i).name);
            brand.setFont(new Font("Arial", 20));
            brand.setLayoutX(200);
            brand.setLayoutY(100);

            DecimalFormat decimalFormat = new DecimalFormat("##.##");

            Label score = new Label("Score : " + decimalFormat.format(Application.shop.currentCustomer.items.get(i).score) + "%");
            score.setFont(new Font("Arial", 20));
            score.setLayoutX(400);
            score.setLayoutY(0);


            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setLayoutX(50);
            anchorPane.setLayoutY(icount * 225 + 50);
            anchorPane.setPrefWidth(1000);
            anchorPane.setPrefHeight(200);
            anchorPane.setStyle("-fx-background-color: #FFCF21;");
            anchorPane.setEffect(new DropShadow());
            anchorPane.setEffect(new InnerShadow());

            anchorPane.getChildren().addAll(imageView, name, brand, score);
            cartPage.getChildren().add(anchorPane);

            icount++;
        }


    }

}
