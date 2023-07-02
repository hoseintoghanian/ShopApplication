module com.example.shopapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jfxrt;


    opens com.example.shopapplication to javafx.fxml;
    exports com.example.shopapplication;
    exports com.company.java;
    opens com.company.java to javafx.fxml;
}