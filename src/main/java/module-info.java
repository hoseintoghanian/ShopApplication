module com.example.shopapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.mail;

    opens com.example.shopapplication to javafx.fxml;
    exports com.example.shopapplication;
}