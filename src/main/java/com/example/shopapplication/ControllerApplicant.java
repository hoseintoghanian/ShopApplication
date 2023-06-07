package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ControllerApplicant {

    private Stage stage2;
    private Scene scene2;
    private FXMLLoader fxmlLoader2;
    @FXML
    private Label txtFNaccount,txtLNaccount,txtPNaccount,txtUNaccount,txtPWaccount,txtEMaccount,txtWBaccount;
    @FXML
    private Label txtfnaccount,txtlnaccount,txtpnaccount,txtunaccount,txtpwaccount,txtemaccount;
    @FXML
    MenuButton menuButtonKind, menuButtonMinorKind, menuButtonBrand;

    public void displayInfo(){
        if (Application.shop.customers.contains(Application.shop.currentCustomer)){
            txtFNaccount.setText(Application.shop.currentCustomer.getFirstname());
            txtLNaccount.setText(Application.shop.currentCustomer.getLastname());
            txtPNaccount.setText(Application.shop.currentCustomer.getPhoneNumber());
            txtUNaccount.setText(Application.shop.currentCustomer.getUsername());
            txtPWaccount.setText(Application.shop.currentCustomer.getPassword());
            txtEMaccount.setText(Application.shop.currentCustomer.getEmail());
        }
        else if(Application.shop.sellers.contains(Application.shop.currentSeller)){
            txtfnaccount.setText(Application.shop.currentSeller.getFirstname());
            txtlnaccount.setText(Application.shop.currentSeller.getLastname());
            txtpnaccount.setText(Application.shop.currentSeller.getPhoneNumber());
            txtunaccount.setText(Application.shop.currentSeller.getUsername());
            txtpwaccount.setText(Application.shop.currentSeller.getPassword());
            txtemaccount.setText(Application.shop.currentSeller.getEmail());
        }
    }

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


        menuButtonKind.setText("drinks");

        MenuItem tea = new MenuItem("tea");
        MenuItem herbalTea = new MenuItem("herbal tea");
        MenuItem coffee = new MenuItem("coffee");
        MenuItem water = new MenuItem("water");
        MenuItem beer = new MenuItem("beer");
        MenuItem soda = new MenuItem("soda");
        MenuItem juice = new MenuItem("juice");

        tea.setOnAction(event -> {
            menuButtonMinorKind.setText("tea");

            MenuItem debsh = new MenuItem("Debsh");
            MenuItem golestan = new MenuItem("Golestan");
            MenuItem shahrzad = new MenuItem("Shahrzad");

            menuButtonBrand.getItems().addAll(debsh,golestan,shahrzad);
        });

        herbalTea.setOnAction(event -> {
            menuButtonMinorKind.setText("herbal tea");

            MenuItem golestan = new MenuItem("Golestan");
            MenuItem seharKhiz = new MenuItem("Sehar Khiz");
            MenuItem shahsavand = new MenuItem("Shahsavand");

            menuButtonBrand.getItems().addAll(golestan,seharKhiz,shahsavand);
        });

        coffee.setOnAction(event -> {
            menuButtonMinorKind.setText("coffee");

            MenuItem aliCafe = new MenuItem("Ali Cafe");
            MenuItem goodDay = new MenuItem("Good Day");
            MenuItem nescafe = new MenuItem("Nescafe");

            menuButtonBrand.getItems().addAll(aliCafe,goodDay,nescafe);
        });

        water.setOnAction(event -> {
            menuButtonMinorKind.setText("water");

            MenuItem damavand = new MenuItem("Damavand");
            MenuItem miva = new MenuItem("Miva");
            MenuItem oxab = new MenuItem("Oxab");

            menuButtonBrand.getItems().addAll(damavand,miva,oxab);
        });

        beer.setOnAction(event -> {
            menuButtonMinorKind.setText("cream");

            MenuItem aalis = new MenuItem("Aalis");
            MenuItem heyDay = new MenuItem("Hey Day");
            MenuItem jojo = new MenuItem("Jojo");

            menuButtonBrand.getItems().addAll(aalis,heyDay,jojo);
        });

        soda.setOnAction(event -> {
            menuButtonMinorKind.setText("cream");

            MenuItem cocaCola = new MenuItem("Coca Cola");
            MenuItem fantaLemon = new MenuItem("Fanta Lemon");
            MenuItem fantaOrange = new MenuItem("Fanta Orange");

            menuButtonBrand.getItems().addAll(cocaCola,fantaLemon,fantaOrange);
        });

        juice.setOnAction(event -> {
            menuButtonMinorKind.setText("cream");

            MenuItem mihan = new MenuItem("Mihan");
            MenuItem sanIch = new MenuItem("San Ich");
            MenuItem takDaneh = new MenuItem("Tak Daneh");

            menuButtonBrand.getItems().addAll(mihan,sanIch,takDaneh);
        });

        menuButtonMinorKind.getItems().addAll(tea,herbalTea,coffee,water,beer,soda,juice);
    }

    public void chooseKindSnacks() {

        menuButtonKind.setText("snacks");

        MenuItem chocolate = new MenuItem("chocolate");
        MenuItem biscuit = new MenuItem("biscuit");
        MenuItem nuts = new MenuItem("nuts");
        MenuItem cake = new MenuItem("cake");
        MenuItem chips = new MenuItem("chips");
        MenuItem pofak = new MenuItem("pofak");
        MenuItem chewingGum = new MenuItem("chewing gum");

        chocolate.setOnAction(event -> {
            menuButtonMinorKind.setText("chocolate");

            MenuItem hiss = new MenuItem("Hiss");
            MenuItem rangarang = new MenuItem("Rangarang");
            MenuItem takTak = new MenuItem("Tak Daneh");

            menuButtonBrand.getItems().addAll(hiss,rangarang,takTak);
        });

        biscuit.setOnAction(event -> {
            menuButtonMinorKind.setText("biscuit");

            MenuItem hiBye = new MenuItem("Hi Bye");
            MenuItem petitBeurre = new MenuItem("petit Beurre");
            MenuItem sagheTalai = new MenuItem("Saghe Talai");

            menuButtonBrand.getItems().addAll(hiBye,petitBeurre,sagheTalai);
        });

        nuts.setOnAction(event -> {
            menuButtonMinorKind.setText("nuts");

            MenuItem mani = new MenuItem("Hi Bye");
            MenuItem mazMaz = new MenuItem("Maz Maz");
            MenuItem sanjaghak = new MenuItem("Sanjaghak");

            menuButtonBrand.getItems().addAll(mani,mazMaz,sanjaghak);
        });

        cake.setOnAction(event -> {
            menuButtonMinorKind.setText("nuts");

            MenuItem cakeMake = new MenuItem("Cake Make");
            MenuItem tTop = new MenuItem("T Top");
            MenuItem tiny = new MenuItem("Tiny");

            menuButtonBrand.getItems().addAll(cakeMake,tTop,tiny);
        });

        chips.setOnAction(event -> {
            menuButtonMinorKind.setText("chips");

            MenuItem chakelz = new MenuItem("Chakelz");
            MenuItem cheetoz = new MenuItem("Cheeroz");
            MenuItem delMaze = new MenuItem("delMaze");

            menuButtonBrand.getItems().addAll(chakelz,cheetoz,delMaze);
        });

        pofak.setOnAction(event -> {
            menuButtonMinorKind.setText("chips");

            MenuItem chakelz = new MenuItem("Chakelz");
            MenuItem cheetoz = new MenuItem("Cheeroz");
            MenuItem pofakNamaki = new MenuItem("Pofak Namaki");

            menuButtonBrand.getItems().addAll(chakelz,cheetoz,pofakNamaki);
        });

        chewingGum.setOnAction(event -> {
            menuButtonMinorKind.setText("chewing gum");

            MenuItem action = new MenuItem("Action");
            MenuItem biodent = new MenuItem("Biodent");
            MenuItem trident = new MenuItem("Trident");

            menuButtonBrand.getItems().addAll(action,biodent,trident);
        });

        menuButtonMinorKind.getItems().addAll(chocolate,biscuit,nuts,cake,chips,pofak,chewingGum);

    }


    //for the spinner in cart tab see bro code
}
