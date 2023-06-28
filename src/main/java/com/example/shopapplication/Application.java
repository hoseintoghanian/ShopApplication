package com.example.shopapplication;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Application extends javafx.application.Application {
    public static Shop shop = Shop.getInstance();

    @Override
    public void start(Stage stage) throws IOException, SQLException {

        Database.readSeller();
        Database.readCustomer();
        Database.readItemTables();


        for (int i = 0; i < shop.sellers.size(); i++) {
           /* for (int j = 0; j < shop.sellers.get(i).items.size(); j++)
                System.out.println(shop.sellers.get(i).items.get(j));*/
            shop.allItems.addAll(shop.sellers.get(i).items);
        }


        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Online Shop Application");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}