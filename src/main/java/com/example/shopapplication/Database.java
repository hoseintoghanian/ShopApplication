package com.example.shopapplication;

import java.sql.*;
import java.util.ArrayList;

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
        String sql = "INSERT INTO customer (firstname, lastname, phoneNumber, username, pass, email) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = getDBC().prepareStatement(sql);

        statement.setString(1, customer.getFirstname());
        statement.setString(2, customer.getLastname());
        statement.setString(3, customer.getPhoneNumber());
        statement.setString(4, customer.getUsername());
        statement.setString(5, customer.getPassword());
        statement.setString(6, customer.getEmail());

        statement.executeUpdate();

        createItemTable(customer.getUsername(), "purchase_");

        statement.close();
        getDBC().close();
    }

    public static void writeSeller(Seller seller) throws SQLException {
        String sql = "INSERT INTO seller (firstname, lastname, phoneNumber, username, pass, email, workPlace) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = getDBC().prepareStatement(sql);

        statement.setString(1, seller.getFirstname());
        statement.setString(2, seller.getLastname());
        statement.setString(3, seller.getPhoneNumber());
        statement.setString(4, seller.getUsername());
        statement.setString(5, seller.getPassword());
        statement.setString(6, seller.getEmail());
        statement.setString(7, seller.workplace);

        statement.executeUpdate();

        createItemTable(seller.getUsername(), "items_");

        statement.close();
        getDBC().close();
    }

    public static void createItemTable(String username, String str) throws SQLException {
        String sql = "create table " + str + username +
                "(code int, kind varchar(15), minorKind varchar(20), brand varchar(20), name varchar(50), price int, size int, property varchar(500)," +
                "score double, e0 int, e1 int, e2 int, e3 int, e4 int," +
                "uploadDate datetime, imageURL varchar(500), scoreEmojiURL varchar(500), sellerUsername varchar(50), comments varchar(2000), isAuction boolean)";

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();
    }

    public static void readCustomer() throws SQLException {

        Statement statement = getDBC().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");

        while (resultSet.next()) {
            Application.shop.customers.add(new Customer(
                    resultSet.getString("firstname"),
                    resultSet.getString("lastname"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("username"),
                    resultSet.getString("pass"),
                    resultSet.getString("email")
            ));
        }

        statement.close();
        resultSet.close();
        getDBC().close();
    }

    public static void readSeller() throws SQLException {

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
                    resultSet.getString("workplace")
            ));
        }

        statement.close();
        resultSet.close();
        getDBC().close();
    }

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

    public static void addProduct(String str, Item item, String username) throws SQLException {
        String sql = "INSERT INTO " + str + username +
                "(code, kind, minorKind, brand, name, price, size, property," +
                "score, e0, e1, e2, e3, e4," +
                "uploadDate, imageURL, scoreEmojiURL, sellerUsername, comments, isAuction)" +
                "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = getDBC().prepareStatement(sql);

        statement.setInt(1, item.getCode());
        statement.setString(2, item.kind);
        statement.setString(3, item.minorKind);
        statement.setString(4, item.brand);
        statement.setString(5, item.name);
        statement.setLong(6, item.price);

        if (str.equals("items_")) statement.setInt(7, item.size);
        if (str.equals("purchase_")) statement.setInt(7, item.tempSize);

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


        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void addWarehouse(Warehouse warehouse) throws SQLException {
        String sql = "INSERT INTO warehouse(name, storeAdmin, address)VALUES( ?, ?, ?)";

        PreparedStatement statement = getDBC().prepareStatement(sql);

        statement.setString(1, warehouse.name);
        statement.setString(2, warehouse.storeAdmin);
        statement.setString(3, warehouse.address);

        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void deleteWarehouse(String name) throws SQLException {
        String sql = "delete from warehouse where name = '" + name + "'";

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void readItemTables() throws SQLException {
        Statement statement = getDBC().createStatement();
        ResultSet resultSet = null;

        for (int i = 0; i < Application.shop.sellers.size(); i++) {
            resultSet = statement.executeQuery("SELECT * FROM items_" + Application.shop.sellers.get(i).getUsername());

            while (resultSet.next()) {
                Application.shop.sellers.get(i).allItems.add(new Item(
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
                        resultSet.getBoolean("isAuction")
                ));
            }
        }

        statement.close();
        if (resultSet != null) resultSet.close();
        getDBC().close();
    }

    public static void readPurchaseTables() throws SQLException {
        Statement statement = getDBC().createStatement();
        ResultSet resultSet = null;

        for (int i = 0; i < Application.shop.customers.size(); i++) {
            resultSet = statement.executeQuery("SELECT * FROM purchase_" + Application.shop.customers.get(i).getUsername());

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
                        resultSet.getBoolean("isAuction")
                ));
            }
        }

        statement.close();
        if (resultSet != null) resultSet.close();
        getDBC().close();
    }


    public static void updateItem(Item item, String emoji, int i) throws SQLException {
        String sql = "update items_" + item.sellerUsername + " set " + emoji + " = " + item.emojiNumber[i] +
                " , score = " + item.score + " where code = " + item.getCode();

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void updateItem(Item item) throws SQLException {
        String sql = "update items_" + item.sellerUsername + " set comments = '" + item.comments + "' where code = " + item.getCode();

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }

    public static void updateItemAuction(Item item) throws SQLException {
        String sql = "update items_" + item.sellerUsername + " set isAuction = " + item.isAuction + " where code = " + item.getCode();

        PreparedStatement statement = getDBC().prepareStatement(sql);
        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }
}