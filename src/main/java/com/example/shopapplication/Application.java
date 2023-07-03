package com.example.shopapplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class Application extends javafx.application.Application {
    public static Shop shop = Shop.getInstance();

    @Override
    public void start(Stage stage) throws IOException, SQLException, UnsupportedAudioFileException, LineUnavailableException {

        Database.readSellers();
        Database.readCustomers();
        Database.readWarehouse();
        Database.readItemTables();//seller items
        Database.readPurchaseTables();//customer purchase history
        Database.readWarehouseTables();//warehouse inputs and outputs

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Online Shop Application");
        stage.setOnCloseRequest(ev->{
            System.exit(0);
        });
        stage.setScene(scene);
        stage.show();


        SoundThread t = new SoundThread();
        t.start();
    }


    public static void main(String[] args) {
        launch(args);
    }

}