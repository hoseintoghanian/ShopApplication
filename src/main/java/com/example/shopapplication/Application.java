package com.example.shopapplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

        /*for (int i = 0; i < shop.sellers.size(); i++) {
            for (int j = 0; j < shop.sellers.get(i).items.size(); j++) {
                System.out.println(shop.sellers.get(i).items.get(j));
            }
        }*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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