package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerAdmin {

    @FXML
    private TableView<ControllerAdmin> TVBankAccountsList;
    @FXML
    private TableColumn<ControllerAdmin, String> TCBankName;
    @FXML
    private TableColumn<ControllerAdmin, Integer> TCBranch;
    @FXML
    private TableColumn<ControllerAdmin, Integer> TCAccountNumber;
    @FXML
    private TableColumn<ControllerAdmin, String> TCAccountType;
    @FXML
    private TableColumn<ControllerAdmin, String> TCBalance;
    @FXML
    private TableColumn<ControllerAdmin, String> TCOpeningDate;
    private String BankName, AccountType, Balance, OpeningDate;
    private Integer Branch, AccountNumber;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;


    public void changingScene(ActionEvent e, String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void changeToLoginSceneAdmin(ActionEvent e) throws IOException {
        changingScene(e, "Login.fxml");
    }

    public void Initialize() {

        TCBankName.setCellValueFactory(new PropertyValueFactory<>("BankName"));
        TCBranch.setCellValueFactory(new PropertyValueFactory<>("Branch"));
        TCAccountNumber.setCellValueFactory(new PropertyValueFactory<>("AccountNumber"));
        TCAccountType.setCellValueFactory(new PropertyValueFactory<>("AccountType"));
        TCBalance.setCellValueFactory(new PropertyValueFactory<>("Balance"));
        TCOpeningDate.setCellValueFactory(new PropertyValueFactory<>("openingDate"));

        TVBankAccountsList.getColumns().addAll(TCBankName, TCBranch, TCAccountNumber, TCAccountType, TCBalance, TCOpeningDate);

        /* ObservableList<ControllerAdmin> BAList = FXCollections.observableArrayList(
                new ControllerAdmin("Melli", "longtime", "4000000", "6/8/2023", 1155, 568874)
        );*/

        // TVBankAccountsList.setItems(BAList);

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
