package com.example.shopapplication;

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
        String sql = "INSERT INTO customer (firstname, lastname, phoneNumber, username, pass, email) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = getDBC().prepareStatement(sql);

        statement.setString(1, customer.getFirstname());
        statement.setString(2, customer.getLastname());
        statement.setString(3, customer.getPhoneNumber());
        statement.setString(4, customer.getUsername());
        statement.setString(5, customer.getPassword());
        statement.setString(6, customer.getEmail());

        statement.executeUpdate();

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

        createItemTable(seller.getUsername());

        statement.close();
        getDBC().close();
    }

    public static void createItemTable(String username) throws SQLException {
        String sql = "create table " + "items_" + username +
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

    public static void addProduct(Item item) throws SQLException {
        String sql = "INSERT INTO " + "items_" + Application.shop.currentSeller.getUsername() +
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
        statement.setInt(7, item.size);
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