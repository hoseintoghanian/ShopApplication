package com.example.shopapplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Application extends javafx.application.Application {
    public static Shop shop = Shop.getInstance();

    @Override
    public void start(Stage stage) throws IOException, SQLException {

        Database.readSeller();
        Database.readCustomer();
        Database.readWarehouse();
        Database.readItemTables();
        Database.readPurchaseTables();


        for (int i = 0; i < shop.sellers.size(); i++) {

            for (int j = 0; j < shop.sellers.get(i).allItems.size(); j++) {
                if (shop.sellers.get(i).allItems.get(j).isAuction)
                    shop.sellers.get(i).auction = shop.sellers.get(i).allItems.get(j);
            }

            shop.sellers.get(i).tempItems.addAll(shop.sellers.get(i).allItems);

            shop.allItems.addAll(shop.sellers.get(i).allItems);
            shop.tempItems.addAll(shop.sellers.get(i).allItems);
        }
        Shop.SortByDate(shop.allItems, shop.tempItems);


        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Online Shop Application");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}