package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerAdmin {
    public void changingScene(ActionEvent e, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void changeToLoginSceneAdmin(ActionEvent e) throws IOException {
        changingScene(e, "Login.fxml");
    }

    @FXML
    private TextField txtAddName, txtAddAdmin;
    @FXML
    private TextArea txtAddAddress;

    public void addWarehouse() throws SQLException {

        if (!txtAddName.getText().equals("") && !txtAddAdmin.getText().equals("") && !txtAddAddress.getText().equals("")) {
            Warehouse warehouse = new Warehouse(txtAddName.getText(), txtAddAdmin.getText(), txtAddAddress.getText());

            if (!Application.shop.warehouses.contains(warehouse)) {
                Application.shop.warehouses.add(warehouse);
                Database.addWarehouse(warehouse);

                createMenuItem(warehouse);
            }

        }
    }

    @FXML
    private MenuButton warehouseMenu;

    public void createMenuItem(Warehouse warehouse) {
        String text = txtAddName.getText();
        MenuItem menuItem = new MenuItem(text);

        menuItem.setOnAction(ev -> {
            warehouseMenu.setText(text);
            Application.shop.currentWarehouse = warehouse;
        });

        warehouseMenu.getItems().add(menuItem);
    }

    @FXML
    private Label storeName, storeAdmin, storeAddress;

    public void displayInfo() {

        for (int i = 0; i < Application.shop.warehouses.size(); i++) {
            MenuItem menuItem = new MenuItem(Application.shop.warehouses.get(i).name);
            int finalI = i;
            menuItem.setOnAction(ev -> {
                warehouseMenu.setText(Application.shop.warehouses.get(finalI).name);
                Application.shop.currentWarehouse = Application.shop.warehouses.get(finalI);
            });

            warehouseMenu.getItems().add(menuItem);
        }

        if (Application.shop.currentWarehouse != null) {

            storeName.setText("store name   : " + Application.shop.currentWarehouse.name);
            storeAdmin.setText("store admin : " + Application.shop.currentWarehouse.storeAdmin);
            storeAddress.setText("address : " + Application.shop.currentWarehouse.address);

        }
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
