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

        menuButtonKind.setText("break fast");

        MenuItem jam = new MenuItem("jam");
        MenuItem honey = new MenuItem("honey");
        MenuItem halva = new MenuItem("halva");

        jam.setOnAction(event -> {
            menuButtonMinorKind.setText("pea");

            MenuItem bijan = new MenuItem("Bijan");
            MenuItem mixLand = new MenuItem("Mix Land");
            MenuItem yekOyek= new MenuItem("yek & yek");

            menuButtonBrand.getItems().addAll(bijan,mixLand,yekOyek);
        });
        honey.setOnAction(event -> {
            menuButtonMinorKind.setText("honey");

            MenuItem rayehe = new MenuItem("Rayehe");
            MenuItem segmen = new MenuItem("Segmen");
            MenuItem shahsavand= new MenuItem("Shahsavand");

            menuButtonBrand.getItems().addAll(rayehe,segmen,shahsavand);
        });
        halva.setOnAction(event -> {
            menuButtonMinorKind.setText("halva");

            MenuItem oghab = new MenuItem("Oghab");
            MenuItem tahini = new MenuItem("Tahini");
            MenuItem taksun = new MenuItem("Taksun");

            menuButtonBrand.getItems().addAll(oghab,tahini,taksun);
        });

        menuButtonMinorKind.getItems().addAll(jam,honey,halva);
    }

    public void chooseKindProtein() {

        menuButtonKind.setText("protein foods");

        MenuItem bologna = new MenuItem("bologna");
        MenuItem lambMeet = new MenuItem("lamb meet");
        MenuItem chicken = new MenuItem("chicken");
        MenuItem egg = new MenuItem("egg");
        MenuItem beef = new MenuItem("beef");
        MenuItem shrimp = new MenuItem("shrimp");
        MenuItem tuna = new MenuItem("tuna");

        bologna.setOnAction(event -> {
            menuButtonMinorKind.setText("bologna");

            MenuItem kampooreh = new MenuItem("Kampooreh");
            MenuItem robat = new MenuItem("Robat");
            MenuItem shamSham = new MenuItem("Sham Sham");

            menuButtonBrand.getItems().addAll(kampooreh, robat, shamSham);
        });
        lambMeet.setOnAction(event -> {
            menuButtonMinorKind.setText("lamb meet");

            MenuItem ariaBahar = new MenuItem("Aria Bahar");
            MenuItem mahya = new MenuItem("Mahya");
            MenuItem puya = new MenuItem("Puya");

            menuButtonBrand.getItems().addAll(ariaBahar,mahya,puya);
        });
        chicken.setOnAction(event -> {
            menuButtonMinorKind.setText("chicken");

            MenuItem mahya = new MenuItem("Mahya");
            MenuItem puya = new MenuItem("Puya");
            MenuItem telavang = new MenuItem("Puya");

            menuButtonBrand.getItems().addAll(mahya,puya,telavang);
        });
        egg.setOnAction(event -> {
            menuButtonMinorKind.setText("egg");

            MenuItem morghdaran = new MenuItem("Morghdaran");
            MenuItem porotana = new MenuItem("Porotana");
            MenuItem telavang = new MenuItem("Telavang");

            menuButtonBrand.getItems().addAll(morghdaran,porotana,telavang);
        });
        beef.setOnAction(event -> {
            menuButtonMinorKind.setText("beef");

            MenuItem khoram = new MenuItem("Khoram");
            MenuItem mahya = new MenuItem("Mahya");
            MenuItem puya = new MenuItem("Puya");

            menuButtonBrand.getItems().addAll(khoram,mahya,puya);
        });
        shrimp.setOnAction(event -> {
            menuButtonMinorKind.setText("shrimp");

            MenuItem marine = new MenuItem("Marine");
            MenuItem pemina = new MenuItem("Pemina");
            MenuItem tohfe = new MenuItem("Tohfe");

            menuButtonBrand.getItems().addAll(marine,pemina,tohfe);
        });
        tuna.setOnAction(event -> {
            menuButtonMinorKind.setText("tuna");

            MenuItem makenzi = new MenuItem("Makenzi");
            MenuItem shilton = new MenuItem("Shilton");
            MenuItem tohfe = new MenuItem("Tohfe");

            menuButtonBrand.getItems().addAll(makenzi,shilton,tohfe);
        });

        menuButtonMinorKind.getItems().addAll(bologna,lambMeet,chicken,egg,beef,shrimp,tuna);

    }

    public void chooseKindDairy() {

        menuButtonKind.setText("dairy");

        MenuItem milk = new MenuItem("milk");
        MenuItem yogurt = new MenuItem("yogurt");
        MenuItem cheese = new MenuItem("cheese");
        MenuItem cream = new MenuItem("cream");

        milk.setOnAction(event -> {
            menuButtonMinorKind.setText("milk");

            MenuItem damdaran = new MenuItem("Damdaran");
            MenuItem kale = new MenuItem("Kale");
            MenuItem mihan = new MenuItem("Mihan");

            menuButtonBrand.getItems().addAll(damdaran,kale,mihan);
        });

        yogurt.setOnAction(event -> {
            menuButtonMinorKind.setText("yogurt");

            MenuItem kale = new MenuItem("Kale");
            MenuItem mihan = new MenuItem("Mihan");
            MenuItem ramak = new MenuItem("Ramak");

            menuButtonBrand.getItems().addAll(kale,mihan,ramak);
        });

        cheese.setOnAction(event -> {
            menuButtonMinorKind.setText("cheese");

            MenuItem kale = new MenuItem("Kale");
            MenuItem mihan = new MenuItem("Mihan");
            MenuItem ruzane = new MenuItem("Ruzan");

            menuButtonBrand.getItems().addAll(kale,mihan,ruzane);
        });

        cream.setOnAction(event -> {
            menuButtonMinorKind.setText("cream");

            MenuItem kale = new MenuItem("Kale");
            MenuItem mihan = new MenuItem("Mihan");
            MenuItem pegah = new MenuItem("Pegah");

            menuButtonBrand.getItems().addAll(kale,mihan,pegah);
        });

        menuButtonMinorKind.getItems().addAll(milk,yogurt,cheese,cream);
    }

    public void chooseKindDrinks() {
    }

    public void chooseKindSnacks() {
    }


    //for the spinner in cart tab see bro code
}
