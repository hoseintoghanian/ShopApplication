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
                "(kind varchar(15), minorKind varchar(20), brand varchar(20), productName varchar(50), price int,size int, imageURL varchar(200));";

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
                "(kind, minorKind, brand, productName, price, size, imageURL)VALUES( ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = getDBC().prepareStatement(sql);

        statement.setString(1, item.kind);
        statement.setString(2, item.minorKind);
        statement.setString(3, item.brand);
        statement.setString(4, item.name);
        statement.setLong(5, item.price);
        statement.setInt(6, item.size);
        statement.setString(7, item.imageURL);

        statement.executeUpdate();

        statement.close();
        getDBC().close();
    }


}