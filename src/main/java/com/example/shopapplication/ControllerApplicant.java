package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControllerApplicant {

    @FXML
    private Label txtFNaccount, txtLNaccount, txtPNaccount, txtUNaccount, txtPWaccount, txtEMaccount, txtWBaccount;
    private Tab tabaccount;

    public void changeScene(ActionEvent e, String fxml) {

    }

    public void customerAccountTab(ActionEvent e) {

    }

    public void sellerAccountTab(ActionEvent e) {

    }

    public void displayInfo() {
        //Applicant applicant=
    }

    @FXML
    MenuButton menuButtonKind, menuButtonMinorKind, menuButtonBrand;

    public void chooseKindGrocery() {
        menuButtonKind.setText("grocery");

        MenuItem bread = new MenuItem("bread");
        MenuItem rice = new MenuItem("rice");
        MenuItem oil = new MenuItem("oil");
        MenuItem brokenSugar = new MenuItem("broken sugar");
        MenuItem sugar = new MenuItem("sugar");
        MenuItem sauce = new MenuItem("sauce");
        MenuItem rob = new MenuItem("rob");
        MenuItem pickle = new MenuItem("pickle");
        MenuItem lemonJuice = new MenuItem("lemon juice");
        MenuItem pasta = new MenuItem("pasta");
        MenuItem saffron = new MenuItem("saffron");
        MenuItem pea = new MenuItem("pea");

        bread.setOnAction(event -> {
            menuButtonMinorKind.setText("bread");

            MenuItem mazrae = new MenuItem("Mazrae");

            menuButtonBrand.getItems().add(mazrae);
        });
        rice.setOnAction(event -> {
            menuButtonMinorKind.setText("rice");

            MenuItem behrooz = new MenuItem("Behrooz");
            MenuItem golestan = new MenuItem("Golestan");
            MenuItem tabiat = new MenuItem("Tabiat");

            menuButtonBrand.getItems().addAll(behrooz, golestan, tabiat);
        });
        oil.setOnAction(event -> {
            menuButtonMinorKind.setText("oil");

            MenuItem ladan = new MenuItem("Ladan");
            MenuItem oila = new MenuItem("Oila");
            MenuItem tabiat = new MenuItem("Tabiat");

            menuButtonBrand.getItems().addAll(ladan,oila,tabiat);
        });
        brokenSugar.setOnAction(event -> {
            menuButtonMinorKind.setText("broken sugar");

            MenuItem azughe = new MenuItem("Azughe");
            MenuItem ferdous = new MenuItem("Ferdous");
            MenuItem mashMash = new MenuItem("Mash Mash");

            menuButtonBrand.getItems().addAll(azughe,ferdous,mashMash);
        });
        sugar.setOnAction(event -> {
            menuButtonMinorKind.setText("sugar");

            MenuItem emruz = new MenuItem("Emruz");
            MenuItem golestan = new MenuItem("Golestan");
            MenuItem shahdane = new MenuItem("Shahdane");

            menuButtonBrand.getItems().addAll(emruz,golestan,shahdane);
        });
        sauce.setOnAction(event -> {
            menuButtonMinorKind.setText("sauce");

            MenuItem behruz = new MenuItem("Behruz");
            MenuItem delvese = new MenuItem("Delvese");
            MenuItem mahram = new MenuItem("Mahram");

            menuButtonBrand.getItems().addAll(behruz,delvese,mahram);
        });
        rob.setOnAction(event -> {
            menuButtonMinorKind.setText("rob");

            MenuItem chinChin = new MenuItem("Chin Chin");
            MenuItem tabarok = new MenuItem("Tabarok");
            MenuItem tabiat = new MenuItem("Tabiat");

            menuButtonBrand.getItems().addAll(chinChin,tabarok,tabiat);
        });
        pickle.setOnAction(event -> {
            menuButtonMinorKind.setText("pickle");

            MenuItem behruz = new MenuItem("Behruz");
            MenuItem mahram = new MenuItem("Mahram");
            MenuItem yekOyek = new MenuItem("Yek & Yek");

            menuButtonBrand.getItems().addAll(behruz,mahram,yekOyek);
        });
        lemonJuice.setOnAction(event -> {
            menuButtonMinorKind.setText("lemon juice");

            MenuItem behruz = new MenuItem("Behruz");
            MenuItem mahram = new MenuItem("Mahram");
            MenuItem yekOyek = new MenuItem("Yek & Yek");

            menuButtonBrand.getItems().addAll(behruz,mahram,yekOyek);
        });
        pasta.setOnAction(event -> {
            menuButtonMinorKind.setText("pasta");

            MenuItem deCecco = new MenuItem("De Cecco");
            MenuItem tak = new MenuItem("Tak Makaron");
            MenuItem zar = new MenuItem("Zar Makaron");

            menuButtonBrand.getItems().addAll(deCecco,tak,zar);
        });
        saffron.setOnAction(event -> {
            menuButtonMinorKind.setText("saffron");

            MenuItem bahraman = new MenuItem("Bahraman");
            MenuItem momtaz = new MenuItem("Momtaz");
            MenuItem seharkhiz = new MenuItem("Seharkhiz");

            menuButtonBrand.getItems().addAll(bahraman,momtaz,seharkhiz);
        });
        pea.setOnAction(event -> {
            menuButtonMinorKind.setText("pea");

            MenuItem aaliChin = new MenuItem("Aali Chin");
            MenuItem delpazir = new MenuItem("Delpazir");
            MenuItem shahsavand= new MenuItem("Shahsavand");

            menuButtonBrand.getItems().addAll(aaliChin,delpazir,shahsavand);
        });




        menuButtonMinorKind.getItems().addAll(bread, rice, oil, brokenSugar, sugar, sauce, rob, pickle, lemonJuice, pasta, saffron, pea);
    }

    public void chooseKindBreakfast() {
    }

    public void chooseKindProtein() {
    }

    public void chooseKindDairy() {
    }

    public void chooseKindDrinks() {
    }

    public void chooseKindSnacks() {
    }


    //for the spinner in cart tab see bro code
}
