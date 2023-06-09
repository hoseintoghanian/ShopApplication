package com.example.shopapplication;

import javafx.scene.image.Image;

import java.sql.*;

public class Database {
    public static Connection getDBC() {//getDBC --> get database connection
        Connection connection = null;

        String URL = "jdbc:mysql://localhost:3306/shopapplicationdb";
        String username = "root";
        String password = "5581";

        try {
            connection = DriverManager.getConnection(URL, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void writeCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (firstname, lastname, phoneNumber, username, pass, email, wallet, imageurl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = getDBC().prepareStatement(sql);

        statement.setString(1, customer.getFirstname());
        statement.setString(2, customer.getLastname());
        statement.setString(3, customer.getPhoneNumber());
        statement.setString(4, customer.getUsername());
        statement.setString(5, customer.getPassword());
        statement.setString(6, customer.getEmail());
        statement.setLong(7, customer.wallet);
        statement.setString(8, customer.image.getUrl());

        statement.executeUpdate();

        createItemTable(customer.getUsername(), "customer_purchase_");

        statement.close();
        getDBC().close();
    }

    public static void writeSeller(Seller seller) throws SQLException {
        String sql = "INSERT INTO seller (firstname, lastname, phoneNumber, username, pass, email, workPlace, wallet, imageurl, chat, allowToLogin)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = getDBC().prepareStatement(sql);

        statement.setString(1, seller.getFirstname());
        statement.setString(2, seller.getLastname());
        statement.setString(3, seller.getPhoneNumber());
        statement.setString(4, seller.getUsername());
        statement.setString(5, seller.getPassword());
        statement.setString(6, seller.getEmail());
        statement.setString(7, seller.workplace);
        statement.setLong(8, seller.wallet);
        statement.setString(9, seller.image.getUrl());
        statement.setString(10, seller.chat);
        statement.setBoolean(11, seller.allowToLogin);

        statement.executeUpdate();

        createItemTable(seller.getUsername(), "seller_items_");

        statement.close();
        getDBC().close();
    }

    public static void createItemTable(String username, String str) throws SQLException {
        String sql = "create table " + str + username +
                "(code int, kind varchar(15), minorKind varchar(20), brand varchar(20), name varchar(50), price int, size int, property varchar(500)," +
                "score double, e0 int, e1 int, e2 int, e3 int, e4 int," +
                "uploadDate datetime, imageURL varchar(500), scoreEmojiURL varchar(500), sellerUsername varchar(50), comments varchar(2000), isAuction boolean, tempPrice long)";

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();
    }

    public static void readCustomers() throws SQLException {

        Statement statement = getDBC().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
        Image image;

        while (resultSet.next()) {
            Application.shop.customers.add(new Customer(
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("username"),
                    resultSet.getString("pass"),
                    resultSet.getString("email"),
                    resultSet.getLong("wallet"),
                    resultSet.getString("imageurl")
            ));
        }

        statement.close();
        resultSet.close();
        getDBC().close();
    }

    public static void readSellers() throws SQLException {

        Statement statement = getDBC().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM seller");

        while (resultSet.next()) {
            Application.shop.sellers.add(new Seller(
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("username"),
                    resultSet.getString("pass"),
                    resultSet.getString("email"),
                    resultSet.getString("workplace"),
                    resultSet.getLong("wallet"),
                    resultSet.getString("imageurl"),
                    resultSet.getString("chat"),
                    resultSet.getBoolean("allowToLogin")
            ));
        }

        statement.close();
        resultSet.close();
        getDBC().close();
    }

    public static void readItemTables() throws SQLException {
        Statement statement = getDBC().createStatement();
        ResultSet resultSet = null;

        for (int i = 0; i < Application.shop.sellers.size(); i++) {
            resultSet = statement.executeQuery("SELECT * FROM seller_items_" + Application.shop.sellers.get(i).getUsername());

            while (resultSet.next()) {
                Item item = new Item(
                        resultSet.getInt("code"),
                        resultSet.getString("kind"),
                        resultSet.getString("minorKind"),
                        resultSet.getString("brand"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("size"),
                        resultSet.getString("property"),

                        resultSet.getDouble("score"),
                        resultSet.getInt("e0"),
                        resultSet.getInt("e1"),
                        resultSet.getInt("e2"),
                        resultSet.getInt("e3"),
                        resultSet.getInt("e4"),

                        resultSet.getObject("uploadDate"),
                        resultSet.getString("imageURL"),
                        resultSet.getString("scoreEmojiURL"),
                        resultSet.getString("sellerUsername"),
                        resultSet.getString("comments"),
                        resultSet.getBoolean("isAuction"),
                        resultSet.getLong("tempPrice")
                );

                Application.shop.sellers.get(i).allItems.add(item);
                if (item.isAuction) Application.shop.sellers.get(i).auction = item;
            }
            Application.shop.allItems.addAll(Application.shop.sellers.get(i).allItems);
            Application.shop.tempItems.addAll(Application.shop.sellers.get(i).allItems);
        }
        Shop.sortByDate(Application.shop.allItems, Application.shop.tempItems);

        statement.close();
        if (resultSet != null) resultSet.close();
        getDBC().close();
    }

    public static void readPurchaseTables() throws SQLException {
        Statement statement = getDBC().createStatement();
        ResultSet resultSet = null;

        for (int i = 0; i < Application.shop.customers.size(); i++) {
            resultSet = statement.executeQuery("SELECT * FROM customer_purchase_" + Application.shop.customers.get(i).getUsername());

            while (resultSet.next()) {
                Application.shop.customers.get(i).purchase.add(new Item(
                        resultSet.getInt("code"),
                        resultSet.getString("kind"),
                        resultSet.getString("minorKind"),
                        resultSet.getString("brand"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getInt("size"),
                        resultSet.getString("property"),

                        resultSet.getDouble("score"),
                        resultSet.getInt("e0"),
                        resultSet.getInt("e1"),
                        resultSet.getInt("e2"),
                        resultSet.getInt("e3"),
                        resultSet.getInt("e4"),

                        resultSet.getObject("uploadDate"),
                        resultSet.getString("imageURL"),
                        resultSet.getString("scoreEmojiURL"),
                        resultSet.getString("sellerUsername"),
                        resultSet.getString("comments"),
                        resultSet.getBoolean("isAuction"),
                        resultSet.getLong("tempPrice")
                ));
            }
        }

        statement.close();
        if (resultSet != null) resultSet.close();
        getDBC().close();
    }

    public static void addProduct(String str, Item item, String username) throws SQLException {
        String sql = "INSERT INTO " + str + username +
                "(code, kind, minorKind, brand, name, price, size, property," +
                "score, e0, e1, e2, e3, e4," +
                "uploadDate, imageURL, scoreEmojiURL, sellerUsername, comments, isAuction, tempPrice)" +
                "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = getDBC().prepareStatement(sql);

        statement.setInt(1, item.getCode());
        statement.setString(2, item.kind);
        statement.setString(3, item.minorKind);
        statement.setString(4, item.brand);
        statement.setString(5, item.name);
        statement.setLong(6, item.price);

        if (str.equals("seller_items_")) statement.setInt(7, item.size);
        if (str.equals("customer_purchase_")) statement.setInt(7, item.tempSize);

        statement.setString(8, item.property);

        statement.setDouble(9, item.score);
        statement.setInt(10, item.emojiNumber[0]);
        statement.setInt(11, item.emojiNumber[1]);
        statement.setInt(12, item.emojiNumber[2]);
        statement.setInt(13, item.emojiNumber[3]);
        statement.setInt(14, item.emojiNumber[4]);

        statement.setObject(15, item.uploadDate);
        statement.setString(16, item.image.getUrl());
        statement.setString(17, item.scoreEmoji.getUrl());
        statement.setString(18, item.sellerUsername);
        statement.setString(19, item.comments);
        statement.setBoolean(20, item.isAuction);
        statement.setLong(21, item.tempPrice);


        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void removeProduct(String username, int code) throws SQLException {

        String tableName = "seller_items_" + username;
        String sql = "DELETE FROM " + tableName + " WHERE code = ?";

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.setInt(1, code);
        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void updateItem(Item item, String emoji, int i) throws SQLException {
        String sql = "update seller_items_" + item.sellerUsername + " set " + emoji + " = " + item.emojiNumber[i] +
                " , score = " + item.score + " where code = " + item.getCode();

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void updateItem(Item item) throws SQLException {

        String sql = "update seller_items_" + item.sellerUsername + " set comments = ?, size = ? where code = ?";

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.setString(1, item.comments);
        statement.setInt(2, item.size);
        statement.setInt(3, item.getCode());

        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void updateItemAuction(Item item) throws SQLException {
        String sql = "UPDATE seller_items_" + item.sellerUsername + " SET isAuction = ?, tempPrice = ?, uploadDate = ? WHERE code = ?";

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.setBoolean(1, item.isAuction);
        statement.setLong(2, item.tempPrice);
        statement.setObject(3, item.uploadDate);
        statement.setInt(4, item.getCode());

        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void updateSellerWallet(Seller seller) throws SQLException {

        String sql = "update seller  set wallet = " + seller.wallet + " where username = '" + seller.getUsername() + "'";

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void updateCustomerWallet(Customer customer) throws SQLException {

        String sql = "update customer  set wallet = " + customer.wallet + " where username = '" + customer.getUsername() + "'";

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void updateApplicantImage(Applicant applicant) throws SQLException {

        if (applicant.applicantKind == "customer") {

            Customer customer = (Customer) applicant;
            String sql = "update customer  set imageurl = '" + customer.image.getUrl() + "' where username = '" + customer.getUsername() + "'";

            PreparedStatement statement = getDBC().prepareStatement(sql);
            statement.executeUpdate();

            statement.close();
            getDBC().close();
        }

        if (applicant.applicantKind == "seller") {

            Seller seller = (Seller) applicant;
            String sql = "update seller  set imageurl = '" + seller.image.getUrl() + "' where username = '" + seller.getUsername() + "'";

            PreparedStatement statement = getDBC().prepareStatement(sql);
            statement.executeUpdate();

            statement.close();
            getDBC().close();
        }
    }

    public static synchronized void updateSeller(Seller seller) throws SQLException {
        String sql = "update seller set chat = '" + seller.chat + "',allowToLogin = " + seller.allowToLogin + " where username = '" + seller.getUsername() + "'";

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    //---------------------------warehouse-----------------------------

    public static void readWarehouse() throws SQLException {

        Statement statement = getDBC().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM warehouse");

        while (resultSet.next()) {
            Application.shop.warehouses.add(new Warehouse(
                    resultSet.getString("name"),
                    resultSet.getString("storeAdmin"),
                    resultSet.getString("address")
            ));
        }

        statement.close();
        resultSet.close();
        getDBC().close();
    }

    public static void writeWarehouse(Warehouse warehouse) throws SQLException {
        String sql = "INSERT INTO warehouse(name, storeAdmin, address)VALUES( ?, ?, ?)";

        PreparedStatement statement = getDBC().prepareStatement(sql);

        statement.setString(1, warehouse.name);
        statement.setString(2, warehouse.admin);
        statement.setString(3, warehouse.address);

        statement.executeUpdate();

        createWarehouseItemTable(warehouse.name);

        statement.close();
        getDBC().close();
    }

    public static void createWarehouseItemTable(String warehouseName) throws SQLException {
        String sql = "create table warehouse_items_" + warehouseName +
                "(name varchar(100),price long,size int,uploadDate datetime,kind varchar(10))";

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();
    }

    public static void deleteWarehouse(String name) throws SQLException {
        String sql1 = "DELETE FROM warehouse WHERE name = '" + name + "'";
        String sql2 = "DROP TABLE warehouse_items_" + name;

        PreparedStatement statement = getDBC().prepareStatement(sql1);
        statement.executeUpdate();

        statement = getDBC().prepareStatement(sql2);
        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void editWarehouse(String previousName, Warehouse warehouse) throws SQLException {

        renameWarehouseItemTable(previousName, warehouse.name);

        String sql = "update warehouse set name = '" + warehouse.name + "', storeAdmin = '" + warehouse.admin + "', " +
                "address = '" + warehouse.address + "'" + "where name = '" + previousName + "'";

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    private static void renameWarehouseItemTable(String previousName, String newName) throws SQLException {
        String sql = "rename table warehouse_items_" + previousName + " to warehouse_items_" + newName;

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
        getDBC().close();

    }

    public static void readWarehouseTables() throws SQLException {
        Statement statement = getDBC().createStatement();
        ResultSet resultSet = null;

        for (int i = 0; i < Application.shop.warehouses.size(); i++) {
            resultSet = statement.executeQuery("SELECT * FROM warehouse_items_" + Application.shop.warehouses.get(i).name);

            while (resultSet.next()) {

                ControllerAdmin.WarehouseItem warehouseItem = new ControllerAdmin.WarehouseItem(
                        resultSet.getString("name"),
                        resultSet.getLong("price"),
                        resultSet.getInt("size"),
                        resultSet.getObject("uploadDate"),
                        resultSet.getString("kind")
                );

                if (warehouseItem.kind.equals("input")) {
                    Application.shop.warehouses.get(i).inputs.add(warehouseItem);
                }
                if (warehouseItem.kind.equals("output")) {
                    Application.shop.warehouses.get(i).outputs.add(warehouseItem);
                }
            }
        }

        statement.close();
        if (resultSet != null) resultSet.close();
        getDBC().close();
    }

    public static void addWarehouseItem(ControllerAdmin.WarehouseItem warehouseItem, String name) throws SQLException {
        String sql = "INSERT INTO warehouse_items_" + name +
                "(name, price, size, uploadDate, kind)VALUES( ?, ?, ?, ?, ?)";

        PreparedStatement statement = getDBC().prepareStatement(sql);

        statement.setString(1, warehouseItem.getName());
        statement.setDouble(2, warehouseItem.getPrice());
        statement.setInt(3, warehouseItem.getSize());
        statement.setObject(4, warehouseItem.getUploadDate());
        statement.setString(5, warehouseItem.kind);

        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void updateWarehouseItem(Warehouse warehouse, ControllerAdmin.WarehouseItem warehouseItem) throws SQLException {
        String sql = "UPDATE warehouse_items_" + warehouse.name + " SET kind = '" + warehouseItem.kind + "', uploadDate = '" + warehouseItem.uploadDate + "' WHERE name = '" + warehouseItem.getName() + "'";
        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }
}