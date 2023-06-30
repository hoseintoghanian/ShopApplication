package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

    public void insertWarehouseItems() {

        for (int i = 0; i < Application.shop.warehouses.size(); i++) {
            MenuItem menuItem = new MenuItem(Application.shop.warehouses.get(i).name);
            int finalI = i;
            menuItem.setOnAction(ev -> {
                warehouseMenu.setText(Application.shop.warehouses.get(finalI).name);
                Application.shop.currentWarehouse = Application.shop.warehouses.get(finalI);
                displayInfo();
            });

            warehouseMenu.getItems().add(menuItem);
        }
    }

    @FXML
    private AnchorPane warehousePage;

    public void displayInfo() {

        if (Application.shop.currentWarehouse != null) {

            storeName.setText("store name   : " + Application.shop.currentWarehouse.name);
            storeAdmin.setText("store admin : " + Application.shop.currentWarehouse.storeAdmin);
            storeAddress.setText("address : " + Application.shop.currentWarehouse.address);
        }


        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(100);

        TableColumn<Item, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(90);

        TableColumn<Item, String> sizeColumn = new TableColumn<>("Size");
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        sizeColumn.setPrefWidth(40);


        TableView<Item> tableView = new TableView<>();
        tableView.setItems(FXCollections.observableArrayList(Application.shop.currentWarehouse.items));
        tableView.getColumns().addAll(nameColumn, priceColumn, sizeColumn);

        tableView.setLayoutX(35);
        tableView.setLayoutY(310);
        tableView.setPrefWidth(250);
        tableView.setPrefHeight(200);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setWidth(10);
        dropShadow.setHeight(10);
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setWidth(10);
        innerShadow.setHeight(10);
        tableView.setEffect(innerShadow);
        tableView.setEffect(dropShadow);

        warehousePage.getChildren().add(tableView);

    }

    @FXML
    private TextField txtAddItemName, txtAddItemPrice, txtAddItemSize;
    @FXML
    private RadioButton outputButton, inputButton;

    public void addItem() {

        if (!txtAddItemName.getText().equals("") && !txtAddItemPrice.getText().equals("") && !txtAddItemSize.getText().equals(""))
            if (Application.shop.currentWarehouse != null) {

                Item item = new Item(txtAddItemName.getText(), "brand", Integer.parseInt(txtAddItemPrice.getText()), Integer.parseInt(txtAddItemSize.getText()));

                if (inputButton.isSelected()) {
                    if (!Application.shop.currentWarehouse.items.contains(item)) {
                        Application.shop.currentWarehouse.items.add(item);
                        Application.shop.currentWarehouse.inputs.add(item);
                    }
                }
                if (outputButton.isSelected()) {
                    if (Application.shop.currentWarehouse.items.contains(item)) {
                        Application.shop.currentWarehouse.outputs.add(item);
                        Application.shop.currentWarehouse.items.remove(item);
                    }
                }

                displayInfo();
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
