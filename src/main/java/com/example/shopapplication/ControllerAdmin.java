package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

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

    //-------------------------------bankPage----------------------------
    @FXML
    private AnchorPane bankPage;

    public void displayBankPage() {

        TableView<Transaction> transactionTable;
        TableView<Account> table;

        TableColumn<Transaction, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Transaction, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Transaction, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Transaction, Double> balanceColumn = new TableColumn<>("Balance");
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));


        transactionTable = new TableView<>();
        transactionTable.getColumns().addAll(dateColumn, descriptionColumn, amountColumn, balanceColumn);


        ObservableList<Transaction> transactions = FXCollections.observableArrayList(
                new Transaction("2021-06-01", "Deposit", 1000.00, 1000.00),
                new Transaction("2021-06-02", "Withdrawal", -500.00, 500.00),
                new Transaction("2021-06-03", "Deposit", 750.00, 1250.00)
        );
        transactionTable.setItems(transactions);

        transactionTable.setLayoutX(750);
        transactionTable.setLayoutY(100);
        transactionTable.setPrefWidth(300);
        transactionTable.setPrefHeight(570);
        transactionTable.setEffect(new InnerShadow());


        TableColumn<Account, String> bankNameColumn = new TableColumn<>("Bank Name");
        bankNameColumn.setCellValueFactory(new PropertyValueFactory<>("bankName"));
        bankNameColumn.setPrefWidth(140);

        TableColumn<Account, String> branchColumn = new TableColumn<>("Branch");
        branchColumn.setCellValueFactory(new PropertyValueFactory<>("branch"));
        branchColumn.setPrefWidth(140);

        TableColumn<Account, String> accountNumberColumn = new TableColumn<>("Account Number");
        accountNumberColumn.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));

        TableColumn<Account, String> accountTypeColumn = new TableColumn<>("Account Type");
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("accountType"));

        TableColumn<Account, Double> balanceColumn1 = new TableColumn<>("Balance");
        balanceColumn1.setCellValueFactory(new PropertyValueFactory<>("balance"));

        TableColumn<Account, String> openingDateColumn = new TableColumn<>("Opening Date");
        openingDateColumn.setCellValueFactory(new PropertyValueFactory<>("openingDate"));


        table = new TableView<>();
        table.getColumns().addAll(bankNameColumn, branchColumn, accountNumberColumn, accountTypeColumn, balanceColumn1, openingDateColumn);


        ObservableList<Account> accounts = FXCollections.observableArrayList(
                new Account("Bank A", "Branch 1", "123456", "Savings", 1000.00, "2021-01-01"),
                new Account("Bank B", "Branch 2", "789012", "Checking", 500.00, "2021-02-01"),
                new Account("Bank C", "Branch 3", "345678", "Savings", 750.00, "2021-03-01")
        );
        table.setItems(accounts);

        table.setLayoutX(50);
        table.setLayoutY(100);
        table.setPrefWidth(650);
        table.setPrefHeight(570);
        table.setEffect(new InnerShadow());


        bankPage.getChildren().addAll(table, transactionTable);
    }

    //-------------------chart tab--------------------------
    public void chartTab() {

    }


    //----------------------warehouse--------------------------------


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

            Application.shop.currentWarehouse = warehouse;

            displayInfo(warehouse.items, 35, 310);
            displayInfo(warehouse.inputs, 480, 55);
            displayInfo(warehouse.outputs, 480, 285);
        }
    }

    @FXML
    private Button delete;

    public void deleteWarehouse() throws SQLException {

        Database.deleteWarehouse(Application.shop.currentWarehouse.name);
        Application.shop.warehouses.remove(Application.shop.currentWarehouse);
        insertWarehouseItems();
    }

    @FXML
    private MenuButton warehouseMenu;

    public void createMenuItem(Warehouse warehouse) {
        String text = txtAddName.getText();
        MenuItem menuItem = new MenuItem(text);

        menuItem.setOnAction(ev -> {
            warehouseMenu.setText(text);
            Application.shop.currentWarehouse = warehouse;
            displayInfo(warehouse.items, 35, 310);
            displayInfo(warehouse.inputs, 480, 55);
            displayInfo(warehouse.outputs, 480, 285);

            delete.setDisable(false);
            delete.setOpacity(1);
        });

        warehouseMenu.getItems().add(menuItem);
    }

    @FXML
    private Label storeName, storeAdmin, storeAddress;

    public void insertWarehouseItems() {

        delete.setDisable(true);
        delete.setOpacity(0);

        warehouseMenu.getItems().clear();
        warehouseMenu.setText("Warehouses");

        storeName.setText("store name   : ");
        storeAdmin.setText("store admin : ");
        storeAddress.setText("address : ");

        Application.shop.currentWarehouse = null;

        for (int i = 0; i < Application.shop.warehouses.size(); i++) {
            MenuItem menuItem = new MenuItem(Application.shop.warehouses.get(i).name);
            int finalI = i;
            menuItem.setOnAction(ev -> {
                warehouseMenu.setText(Application.shop.warehouses.get(finalI).name);
                Application.shop.currentWarehouse = Application.shop.warehouses.get(finalI);
                displayInfo(Application.shop.warehouses.get(finalI).items, 35, 310);
                displayInfo(Application.shop.warehouses.get(finalI).inputs, 480, 55);
                displayInfo(Application.shop.warehouses.get(finalI).outputs, 480, 285);

                delete.setDisable(false);
                delete.setOpacity(1);
            });

            warehouseMenu.getItems().add(menuItem);
        }
    }

    @FXML
    private AnchorPane warehousePage;

    public void displayInfo(ArrayList<Item> items, int x, int y) {

        if (Application.shop.currentWarehouse != null) {

            storeName.setText("store name   : " + Application.shop.currentWarehouse.name);
            storeAdmin.setText("store admin : " + Application.shop.currentWarehouse.storeAdmin);
            storeAddress.setText("address : " + Application.shop.currentWarehouse.address);

            TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            nameColumn.setPrefWidth(120);

            TableColumn<Item, String> priceColumn = new TableColumn<>("Price");
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            priceColumn.setPrefWidth(80);

            TableColumn<Item, String> sizeColumn = new TableColumn<>("Size");
            sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
            sizeColumn.setPrefWidth(50);


            TableView<Item> tableView = new TableView<>();
            tableView.setItems(FXCollections.observableArrayList(items));
            tableView.getColumns().addAll(nameColumn, priceColumn, sizeColumn);

            tableView.setLayoutX(x);
            tableView.setLayoutY(y);
            tableView.setPrefWidth(250);
            tableView.setPrefHeight(200);
            InnerShadow innerShadow = new InnerShadow();
            innerShadow.setWidth(10);
            innerShadow.setHeight(10);
            tableView.setEffect(innerShadow);

            warehousePage.getChildren().add(tableView);
        }
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
                        Application.shop.currentWarehouse.inputs.remove(item);
                    }
                }

                displayInfo(Application.shop.currentWarehouse.items, 35, 310);
                displayInfo(Application.shop.currentWarehouse.inputs, 480, 55);
                displayInfo(Application.shop.currentWarehouse.outputs, 480, 285);
            }

    }

    public void csv(ArrayList<Item> data, String filename) throws IOException {
        CSVFile.writeToFile(data, filename);

        // download the file
        File file = new File(filename);
        if (file.exists()) {
            // set the content type and header fields for the download
            String mimeType = "application/csv";
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", file.getName());

            // set the headers for the download response
            byte[] dataBytes = Files.readAllBytes(file.toPath());
            Path path = Paths.get(file.getName());
            Files.write(path, dataBytes);
        } else {
            System.out.println("File does not exist.");
        }
    }

    public void downloadCSV() throws IOException {
        if (Application.shop.currentWarehouse != null) {
            String address = Application.shop.currentWarehouse.name;
            csv(Application.shop.currentWarehouse.inputs, "CSV/" + address + "_inputs.csv");
            csv(Application.shop.currentWarehouse.outputs, "CSV/" + address + "_outputs.csv");
        }
    }


    //------------------------------------chat--------------------------


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

//---------------------------------------------------------------------------------

    public static class Transaction {

        private String date;
        private String description;
        private double amount;
        private double balance;

        public Transaction(String date, String description, double amount, double balance) {
            this.date = date;
            this.description = description;
            this.amount = amount;
            this.balance = balance;
        }

        public String getDate() {
            return date;
        }

        public String getDescription() {
            return description;
        }

        public double getAmount() {
            return amount;
        }

        public double getBalance() {
            return balance;
        }
    }

    public static class Account {

        private String bankName;
        private String branch;
        private String accountNumber;
        private String accountType;
        private double balance;
        private String openingDate;

        public Account(String bankName, String branch, String accountNumber, String accountType, double balance, String openingDate) {
            this.bankName = bankName;
            this.branch = branch;
            this.accountNumber = accountNumber;
            this.accountType = accountType;
            this.balance = balance;
            this.openingDate = openingDate;
        }

        public String getBankName() {
            return bankName;
        }

        public String getBranch() {
            return branch;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getAccountType() {
            return accountType;
        }

        public double getBalance() {
            return balance;
        }

        public String getOpeningDate() {
            return openingDate;
        }
    }
}
