package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControllerAdmin {
    Server server;

    DropShadow dropShadow;
    InnerShadow innerShadow;

    public ControllerAdmin() {
        dropShadow = new DropShadow();
        dropShadow.setWidth(10);
        dropShadow.setHeight(10);

        innerShadow = new InnerShadow();
        innerShadow.setWidth(10);
        innerShadow.setHeight(10);


        server = new Server();
        server.start();
    }

    public void changingScene(ActionEvent e, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Online Shop Application");
        stage.getIcons().add(new Image("shop.png"));
        stage.setResizable(false);
        stage.setOnCloseRequest(ev -> {
            closeFiles();
            System.exit(0);
        });
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void changeToLoginSceneAdmin(ActionEvent e) throws IOException {
        closeFiles();

        changingScene(e, "Login.fxml");
    }

    public void closeFiles() {
        for (int i = 0; i < Application.shop.sellers.size(); i++) {
            if (server.clientsMap.get(Application.shop.sellers.get(i).getUsername()) != null)
                server.clientsMap.get(Application.shop.sellers.get(i).getUsername()).closeClientManagersStreams();
        }
        server.closeServer();
    }

    //-------------------------------bankPage-------------------------------------------------------
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
        transactionTable.setEffect(innerShadow);


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
        table.setEffect(innerShadow);


        bankPage.getChildren().addAll(table, transactionTable);
    }

    //-----------------------------chart tab-------------------------------------------------------
    @FXML
    private AnchorPane chartPage;
    LineChart<String, Number> currentIncomeChart, currentCostChart;

    public void displayChartPage() {

        chartPage.getChildren().removeAll(currentIncomeChart, currentCostChart);


        // Create a list of all items in all warehouses
        ArrayList<WarehouseItem> cost = new ArrayList<>();
        for (Warehouse warehouse : Application.shop.warehouses) {
            cost.addAll(warehouse.inputs);
        }

        // Create a line chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);

        // Set the chart title and axis labels
        xAxis.setLabel("Date");
        yAxis.setLabel("Price");

        // Create a series for the chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Cost");

        // Add the data to the series
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (WarehouseItem item : cost) {
            String dateStr = item.getUploadDate().format(formatter);
            series.getData().add(new XYChart.Data<>(dateStr, item.getPrice()));
        }

        // Add the series to the chart
        ObservableList<XYChart.Series<String, Number>> chartData = FXCollections.observableArrayList();
        chartData.add(series);
        lineChart.setData(chartData);

        lineChart.setPrefWidth(530);
        lineChart.setPrefHeight(530);
        lineChart.setLayoutX(550);
        lineChart.setLayoutY(130);
        lineChart.setEffect(dropShadow);

        currentCostChart = lineChart;


        // Create a list of all items in all warehouses
        ArrayList<WarehouseItem> allItems = new ArrayList<>();
        for (Warehouse warehouse : Application.shop.warehouses) {
            allItems.addAll(warehouse.outputs);
        }

        // Create a line chart
        CategoryAxis xAxis2 = new CategoryAxis();
        NumberAxis yAxis2 = new NumberAxis();
        LineChart<String, Number> lineChart2 = new LineChart<>(xAxis2, yAxis2);

        // Set the chart title and axis labels
        xAxis2.setLabel("Date");
        yAxis2.setLabel("Price");

        // Create a series for the chart
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Income");

        // Add the data to the series
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (WarehouseItem item : allItems) {
            String dateStr = item.getUploadDate().format(formatter2);
            series2.getData().add(new XYChart.Data<>(dateStr, item.getPrice()));
        }

        // Add the series to the chart
        ObservableList<XYChart.Series<String, Number>> chartData2 = FXCollections.observableArrayList();
        chartData2.add(series2);
        lineChart2.setData(chartData2);

        lineChart2.setPrefWidth(530);
        lineChart2.setPrefHeight(530);
        lineChart2.setLayoutY(130);
        lineChart2.setEffect(dropShadow);

        currentIncomeChart = lineChart2;

        chartPage.getChildren().addAll(lineChart, lineChart2);
    }

    //---------------------------warehouse--------------------------------------------------------
    @FXML
    private AnchorPane warehousePage, addItemAnchorPane;
    @FXML
    private TextField txtAddName, txtAddAdmin, txtAddItemName, txtAddItemPrice, txtAddItemSize, editNameTextField, editAdminTextField;
    @FXML
    private TextArea txtAddAddress, editAddressTextField;
    @FXML
    private DatePicker addItemDate;
    @FXML
    private Button deleteButton, editButton, cancelButton, downloadCSVButton;
    @FXML
    private MenuButton warehouseMenu, chartMenuButton;
    @FXML
    private RadioButton outputButton, inputButton;
    @FXML
    private Label storeName, storeAdmin, storeAddress, chartText, addWarehouseErrorLabel, warehouseInputsLabel, warehouseOutputsLabel;
    LineChart<String, Number> currentWarehouseChart;
    TableView inputTable, outputTable;


    public void displayWarehousePage() {

        warehousePage.getChildren().removeAll(inputTable, outputTable, currentWarehouseChart);
        chartText.setText("");

        deleteButton.setDisable(true);
        deleteButton.setOpacity(0);

        editButton.setDisable(true);
        editButton.setOpacity(0);

        chartMenuButton.setDisable(true);
        chartMenuButton.setOpacity(0);

        downloadCSVButton.setDisable(true);
        downloadCSVButton.setOpacity(0);

        warehouseInputsLabel.setVisible(false);
        warehouseOutputsLabel.setVisible(false);

        addItemDate.setValue(null);

        addItemAnchorPane.setVisible(false);

        addWarehouseErrorLabel.setText("");

        warehouseMenu.getItems().clear();
        Application.shop.currentWarehouse = null;

        storeName.setText("Name     : ");
        storeAdmin.setText("Admin   : ");
        storeAddress.setText("Address : ");

        for (int i = 0; i < Application.shop.warehouses.size(); i++) {
            MenuItem menuItem = new MenuItem(Application.shop.warehouses.get(i).name);
            warehouseMenuItemsOnAction(menuItem, Application.shop.warehouses.get(i));
        }
    }

    private void warehouseMenuItemsOnAction(MenuItem menuItem, Warehouse warehouse) {

        menuItem.setOnAction(ev -> {

            warehousePage.getChildren().removeAll(inputTable, outputTable, currentWarehouseChart);
            chartText.setText("");

            Application.shop.currentWarehouse = warehouse;
            warehouseTableViews(warehouse.inputs, 375, 65);
            warehouseTableViews(warehouse.outputs, 650, 65);

            deleteButton.setDisable(false);
            deleteButton.setOpacity(1);

            editButton.setDisable(false);
            editButton.setOpacity(1);

            chartMenuButton.setDisable(false);
            chartMenuButton.setOpacity(1);

            downloadCSVButton.setDisable(false);
            downloadCSVButton.setOpacity(1);

            warehouseInputsLabel.setVisible(true);
            warehouseOutputsLabel.setVisible(true);

            addItemAnchorPane.setVisible(true);
        });
        warehouseMenu.getItems().add(menuItem);
    }

    public void addWarehouse() throws SQLException {

        if (!txtAddName.getText().equals("") && !txtAddAdmin.getText().equals("") && !txtAddAddress.getText().equals("")) {
            Warehouse warehouse = new Warehouse(txtAddName.getText(), txtAddAdmin.getText(), txtAddAddress.getText());

            if (!Application.shop.warehouses.contains(warehouse)) {
                Application.shop.warehouses.add(warehouse);
                Database.writeWarehouse(warehouse);

                Application.shop.currentWarehouse = warehouse;

                MenuItem menuItem = new MenuItem(txtAddName.getText());
                warehouseMenuItemsOnAction(menuItem, warehouse);

                txtAddName.setText("");
                txtAddAdmin.setText("");
                txtAddAddress.setText("");

                addWarehouseErrorLabel.setText("add successfully");
            } else {
                txtAddName.setText("");
                txtAddAdmin.setText("");
                txtAddAddress.setText("");

                addWarehouseErrorLabel.setText("warehouse has already been added");
            }
        } else addWarehouseErrorLabel.setText("fill all the blanks");
    }

    public void deleteWarehouse() throws SQLException {
        Database.deleteWarehouse(Application.shop.currentWarehouse.name);
        Application.shop.warehouses.remove(Application.shop.currentWarehouse);
        displayWarehousePage();
    }

    public void editWarehouse() throws SQLException {

        storeName.setText("Name     : ");
        storeAdmin.setText("Admin   : ");
        storeAddress.setText("Address : ");

        editNameTextField.setDisable(false);
        editAdminTextField.setDisable(false);
        editAddressTextField.setDisable(false);
        cancelButton.setDisable(false);

        editNameTextField.setVisible(true);
        editAdminTextField.setVisible(true);
        editAddressTextField.setVisible(true);
        cancelButton.setVisible(true);

        if (!editNameTextField.getText().equals("") && !editAdminTextField.getText().equals("") && !editAddressTextField.getText().equals(""))
            if (!Application.shop.warehouses.contains(new Warehouse(editNameTextField.getText(), editAdminTextField.getText(), editAddressTextField.getText()))) {

                String previousName = Application.shop.currentWarehouse.name;

                Application.shop.currentWarehouse.name = editNameTextField.getText();
                Application.shop.currentWarehouse.admin = editAdminTextField.getText();
                Application.shop.currentWarehouse.address = editAddressTextField.getText();

                storeName.setText("Name     : " + Application.shop.currentWarehouse.name);
                storeAdmin.setText("Admin   : " + Application.shop.currentWarehouse.admin);
                storeAddress.setText("Address : " + Application.shop.currentWarehouse.address);

                Database.editWarehouse(previousName, Application.shop.currentWarehouse);

                editNameTextField.setText("");
                editAdminTextField.setText("");
                editAddressTextField.setText("");

                editNameTextField.setDisable(true);
                editAdminTextField.setDisable(true);
                editAddressTextField.setDisable(true);
                cancelButton.setDisable(true);

                editNameTextField.setVisible(false);
                editAdminTextField.setVisible(false);
                editAddressTextField.setVisible(false);
                cancelButton.setVisible(false);
            }

    }

    public void cancelEdit() {

        editNameTextField.setText("");
        editAdminTextField.setText("");
        editAddressTextField.setText("");

        editNameTextField.setDisable(true);
        editAdminTextField.setDisable(true);
        editAddressTextField.setDisable(true);
        cancelButton.setDisable(true);

        editNameTextField.setVisible(false);
        editAdminTextField.setVisible(false);
        editAddressTextField.setVisible(false);
        cancelButton.setVisible(false);

        storeName.setText("Name     : " + Application.shop.currentWarehouse.name);
        storeAdmin.setText("Admin   : " + Application.shop.currentWarehouse.admin);
        storeAddress.setText("Address : " + Application.shop.currentWarehouse.address);
    }

    public void warehouseTableViews(ArrayList<WarehouseItem> arrays, int x, int y) {

        if (Application.shop.currentWarehouse != null) {

            storeName.setText("Name     : " + Application.shop.currentWarehouse.name);
            storeAdmin.setText("Admin   : " + Application.shop.currentWarehouse.admin);
            storeAddress.setText("Address : " + Application.shop.currentWarehouse.address);


            TableColumn<WarehouseItem, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

            TableColumn<WarehouseItem, String> priceColumn = new TableColumn<>("Price");
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

            TableColumn<WarehouseItem, String> sizeColumn = new TableColumn<>("Size");
            sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));

            TableColumn<WarehouseItem, String> dateColumn = new TableColumn<>("Date");
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("uploadDate"));


            TableView<WarehouseItem> tableView = new TableView<>();
            tableView.setItems(FXCollections.observableArrayList(arrays));
            tableView.getColumns().addAll(nameColumn, priceColumn, sizeColumn, dateColumn);

            tableView.setLayoutX(x);
            tableView.setLayoutY(y);
            tableView.setPrefWidth(250);
            tableView.setPrefHeight(200);
            InnerShadow innerShadow = new InnerShadow();
            innerShadow.setWidth(10);
            innerShadow.setHeight(10);
            tableView.setEffect(innerShadow);

            if (x == 375) inputTable = tableView;
            if (x == 650) outputTable = tableView;

            warehousePage.getChildren().add(tableView);
        }
    }

    public void addWarehouseItem() throws SQLException {

        if (!txtAddItemName.getText().equals("") && !txtAddItemPrice.getText().equals("") && !txtAddItemSize.getText().equals(""))
            if (Application.shop.currentWarehouse != null) {

                WarehouseItem item;

                if (inputButton.isSelected()) {
                    item = new WarehouseItem(txtAddItemName.getText(), Integer.parseInt(txtAddItemPrice.getText()), Integer.parseInt(txtAddItemSize.getText()), addItemDate.getValue(), "input");
                    if (!Application.shop.currentWarehouse.inputs.contains(item)) {
                        Application.shop.currentWarehouse.inputs.add(item);
                        Database.addWarehouseItem(item, Application.shop.currentWarehouse.name);
                    }
                }
                if (outputButton.isSelected()) {
                    item = new WarehouseItem(txtAddItemName.getText(), Integer.parseInt(txtAddItemPrice.getText()), Integer.parseInt(txtAddItemSize.getText()), addItemDate.getValue(), "output");
                    if (Application.shop.currentWarehouse.inputs.contains(item)) {
                        Application.shop.currentWarehouse.outputs.add(item);
                        Application.shop.currentWarehouse.inputs.remove(item);
                        item.kind = "output";
                        item.uploadDate = addItemDate.getValue().atTime(LocalTime.now());
                        Database.updateWarehouseItem(Application.shop.currentWarehouse, item);
                    }
                }

                warehousePage.getChildren().removeAll(inputTable, outputTable, currentWarehouseChart);
                chartText.setText("");

                warehouseTableViews(Application.shop.currentWarehouse.inputs, 375, 65);
                warehouseTableViews(Application.shop.currentWarehouse.outputs, 650, 65);

                txtAddItemName.setText("");
                txtAddItemPrice.setText("");
                txtAddItemSize.setText("");
            }
    }

    private LineChart<String, Number> createLineChart(String title) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setPrefWidth(700);
        chart.setLayoutX(370);
        chart.setLayoutY(320);
        chart.setEffect(dropShadow);
        chartText.setText(title);

        currentWarehouseChart = chart;

        return chart;
    }

    public void createChart(String title) {

        warehousePage.getChildren().remove(currentWarehouseChart);

        if (Application.shop.currentWarehouse != null) {

            ArrayList<WarehouseItem> items = Application.shop.currentWarehouse.inputs;
            items.sort(new Comparator<WarehouseItem>() {
                @Override
                public int compare(WarehouseItem o1, WarehouseItem o2) {
                    return o1.uploadDate.compareTo(o2.uploadDate);
                }
            });

            LineChart<String, Number> chart = createLineChart(title);

            Map<LocalDate, Integer> dayMap = new HashMap<>();
            Map<String, Integer> monthMap = new TreeMap<>();
            Map<Integer, Integer> yearMap = new TreeMap<>();

            for (WarehouseItem item : items) {
                LocalDate date = item.uploadDate.toLocalDate();
                dayMap.put(date, (dayMap.getOrDefault(date, 0) + item.getSize()));

                String month = String.format("%d-%02d", date.getYear(), date.getMonthValue());
                monthMap.put(month, monthMap.getOrDefault(month, 0) + item.getSize());

                int year = date.getYear();
                yearMap.put(year, yearMap.getOrDefault(year, 0) + item.getSize());
            }

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();


            if (title.equals("Uploads by Day")) {
                for (Map.Entry<LocalDate, Integer> entry : dayMap.entrySet()) {
                    data.add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
                }
                series.setName("Uploads by Day");
            }

            if (title.equals("Uploads by Month")) {
                for (Map.Entry<String, Integer> entry : monthMap.entrySet()) {
                    data.add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                }
                series.setName("Uploads by Month");
            }

            if (title.equals("Uploads by Year")) {
                for (Map.Entry<Integer, Integer> entry : yearMap.entrySet()) {
                    data.add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
                }
                series.setName("Uploads by Year");
            }

            // sort the data in ascending order based on the x-values (i.e., the upload dates)
            if (title.equals("Uploads by Day")) {
                data.sort(new Comparator<XYChart.Data<String, Number>>() {
                    @Override
                    public int compare(XYChart.Data<String, Number> o1, XYChart.Data<String, Number> o2) {
                        LocalDate date1 = LocalDate.parse(o1.getXValue());
                        LocalDate date2 = LocalDate.parse(o2.getXValue());
                        return date1.compareTo(date2);
                    }
                });
            }

            series.setData(data);
            chart.getData().add(series);
            warehousePage.getChildren().add(chart);
        }
    }

    public void chartByDay() {
        createChart("Uploads by Day");
    }

    public void chartByMonth() {
        createChart("Uploads by Month");
    }

    public void chartByYear() {
        createChart("Uploads by Year");
    }

    public void csv(ArrayList<WarehouseItem> data, String filename) throws IOException {
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

        } else System.out.println("File does not exist.");

    }

    public void downloadCSV() throws IOException {
        if (Application.shop.currentWarehouse != null) {
            String address = Application.shop.currentWarehouse.name;
            csv(Application.shop.currentWarehouse.inputs, "CSV/" + address + "_inputs.csv");
            csv(Application.shop.currentWarehouse.outputs, "CSV/" + address + "_outputs.csv");
        }
    }


    //------------------------------------chat---------------------------------
    @FXML
    private TextArea chatTextArea;
    @FXML
    private Label chatText;
    @FXML
    private ImageView chatBackgroundImg, imgTheme1, imgTheme2, imgTheme3, imgTheme4, imgTheme5, imgTheme6, imgTheme7;
    @FXML
    private Label chatPageFirstname, chatPageLastname, chatPageUsername;
    @FXML
    private MenuButton sellersMenuButton;
    @FXML
    private Button sendButton;

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

    public void displayChatPage() {

        chatText.setText("");
        chatTextArea.setDisable(true);
        sendButton.setDisable(true);

        sellersMenuButton.getItems().clear();

        for (int i = 0; i < Application.shop.sellers.size(); i++) {
            MenuItem menuItem = new MenuItem(Application.shop.sellers.get(i).getLastname());
            int finalI = i;
            menuItem.setOnAction(ev -> {
                Application.shop.currentSeller = Application.shop.sellers.get(finalI);
                chatText.setText(Application.shop.currentSeller.chat);

                chatTextArea.setDisable(false);
                sendButton.setDisable(false);

                chatPageFirstname.setText("Firstname : " + Application.shop.currentSeller.getFirstname());
                chatPageLastname.setText("Lastname  : " + Application.shop.currentSeller.getLastname());
                chatPageUsername.setText("Username : " + Application.shop.currentSeller.getUsername());
            });
            sellersMenuButton.getItems().add(menuItem);
        }
    }

    public void send() throws SQLException {
        if (Application.shop.currentSeller != null) {
            String msg = chatTextArea.getText();
            chatText.setText(chatText.getText() + "\nAdmin : " + msg);
            Application.shop.currentSeller.chat = chatText.getText();
            Database.updateSeller(Application.shop.currentSeller);
            chatTextArea.setText("");

            if (server.clientsMap.get(Application.shop.currentSeller.getUsername()) != null)//means the client is online
                server.clientsMap.get(Application.shop.currentSeller.getUsername()).sendMessageToClient("Admin : " + msg);
        }
    }

    public void receive() throws IOException {
        if (Application.shop.currentSeller != null && server.clientsMap.get(Application.shop.currentSeller.getUsername()) != null) {//means both server & client are online
            String msg = server.clientsMap.get(Application.shop.currentSeller.getUsername()).getMessageFromClient();
            if (!msg.equals("")) chatText.setText(chatText.getText() + "\n" + msg);
        }
    }

//------------------------------inner classes--------------------------------

    public static class WarehouseItem {
        private final String name;
        private final long price;
        private final int size;
        LocalDateTime uploadDate;
        String kind;
        Warehouse warehouse;

        public WarehouseItem(String name, long price, int size, LocalDate uploadDate, String kind) {
            this.name = name;
            this.price = price;
            this.size = size;
            this.uploadDate = uploadDate.atTime(LocalTime.now());
            this.kind = kind;

            warehouse = Application.shop.currentWarehouse;
        }

        public WarehouseItem(String name, long price, int size, Object uploadDate, String kind) {
            this.name = name;
            this.price = price;
            this.size = size;
            this.uploadDate = (LocalDateTime) uploadDate;
            this.kind = kind;

            warehouse = Application.shop.currentWarehouse;

        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getSize() {
            return size;
        }

        public LocalDateTime getUploadDate() {
            return uploadDate;
        }

        public Warehouse getWarehouse() {
            return warehouse;
        }

        public boolean equals(Object o) {
            if (o instanceof WarehouseItem) {
                WarehouseItem other = (WarehouseItem) o;
                if (name.equals(other.name) && price == other.price && size == other.size) return true;
            }
            return false;
        }

    }

    public static class Transaction {
        private final String date;
        private final String description;
        private final double amount;
        private final double balance;

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
        private final String bankName;
        private final String branch;
        private final String accountNumber;
        private final String accountType;
        private final double balance;
        private final String openingDate;

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

    public static class CSVFile {
        public static void writeToFile(ArrayList<WarehouseItem> data, String filename) throws IOException {
            FileWriter writer = new FileWriter(filename);
            writer.append("Name, Price, Size, date\n"); // add header row

            for (WarehouseItem item : data) {
                writer.append(item.getName());
                writer.append(",");
                writer.append(String.valueOf(item.getPrice()));
                writer.append(",");
                writer.append(String.valueOf(item.getSize()));
                writer.append(",");
                writer.append(String.valueOf(item.getUploadDate()));
                writer.append("\n");
            }

            writer.flush();
            writer.close();
        }
    }
}
