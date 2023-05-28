module com.example.shopapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.shopapplication to javafx.fxml;
    exports com.example.shopapplication;
}