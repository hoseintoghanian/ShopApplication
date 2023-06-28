package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class ControllerApplicant {

    @FXML
    private Label txtFNaccount, txtLNaccount, txtPNaccount, txtUNaccount, txtPWaccount, txtEMaccount, txtWBaccount;
    @FXML
    private Label txtfnaccount, txtlnaccount, txtpnaccount, txtunaccount, txtpwaccount, txtemaccount,txtwpaccount;
    @FXML
    MenuButton menuButtonKind, menuButtonMinorKind, menuButtonBrand;
    @FXML
    MenuButton filterButtonKind, filterButtonMinorKind, filterButtonBrand;

    public void displayInfo() {
        if (Application.shop.customers.contains(Application.shop.currentCustomer)) {
            txtFNaccount.setText(Application.shop.currentCustomer.getFirstname());
            txtLNaccount.setText(Application.shop.currentCustomer.getLastname());
            txtPNaccount.setText(Application.shop.currentCustomer.getPhoneNumber());
            txtUNaccount.setText(Application.shop.currentCustomer.getUsername());
            txtPWaccount.setText(Application.shop.currentCustomer.getPassword());
            txtEMaccount.setText(Application.shop.currentCustomer.getEmail());
        } else if (Application.shop.sellers.contains(Application.shop.currentSeller)) {
            txtfnaccount.setText(Application.shop.currentSeller.getFirstname());
            txtlnaccount.setText(Application.shop.currentSeller.getLastname());
            txtpnaccount.setText(Application.shop.currentSeller.getPhoneNumber());
            txtunaccount.setText(Application.shop.currentSeller.getUsername());
            txtpwaccount.setText(Application.shop.currentSeller.getPassword());
            txtemaccount.setText(Application.shop.currentSeller.getEmail());
            txtwpaccount.setText(Application.shop.currentSeller.workplace);
        }
    }

    public void changingScene(ActionEvent e, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void changeToLoginScene(ActionEvent e) throws IOException {
        changingScene(e, "Login.fxml");
    }

    public void kindGrocery() {
        grocery(menuButtonKind, menuButtonMinorKind, menuButtonBrand);
    }

    public void kindBreakfast() {
        breakfast(menuButtonKind, menuButtonMinorKind, menuButtonBrand);
    }

    public void kindProtein() {
        protein(menuButtonKind, menuButtonMinorKind, menuButtonBrand);
    }

    public void kindDairy() {
        dairy(menuButtonKind, menuButtonMinorKind, menuButtonBrand);
    }

    public void kindDrinks() {
        drinks(menuButtonKind, menuButtonMinorKind, menuButtonBrand);
    }

    public void kindSnacks() {
        snacks(menuButtonKind, menuButtonMinorKind, menuButtonBrand);
    }

    public void filterGrocery() {
        grocery(filterButtonKind, filterButtonMinorKind, filterButtonBrand);
    }

    public void filterBreakfast() {
        breakfast(filterButtonKind, filterButtonMinorKind, filterButtonBrand);
    }

    public void filterProtein() {
        protein(filterButtonKind, filterButtonMinorKind, filterButtonBrand);
    }

    public void filterDairy() {
        dairy(filterButtonKind, filterButtonMinorKind, filterButtonBrand);
    }

    public void filterDrinks() {
        drinks(filterButtonKind, filterButtonMinorKind, filterButtonBrand);
    }

    public void filterSnacks() {
        snacks(filterButtonKind, filterButtonMinorKind, filterButtonBrand);
    }


    public void grocery(MenuButton menuButtonKind, MenuButton menuButtonMinorKind, MenuButton menuButtonBrand) {

        menuInfo(1, "grocery", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

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

            menuInfo(2, "bread", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem mazrae = new MenuItem("Mazrae");

            mazrae.setOnAction(ev -> {
                menuButtonBrand.setText("mazrae");
            });

            menuButtonBrand.getItems().add(mazrae);
        });
        rice.setOnAction(event -> {

            menuInfo(2, "rice", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem behrooz = new MenuItem("Behrooz");
            MenuItem golestan = new MenuItem("Golestan");
            MenuItem tabiat = new MenuItem("Tabiat");

            behrooz.setOnAction(ev -> {
                menuButtonBrand.setText("behrooz");
            });
            golestan.setOnAction(ev -> {
                menuButtonBrand.setText("golestan");
            });
            tabiat.setOnAction(ev -> {
                menuButtonBrand.setText("tabiat");
            });

            menuButtonBrand.getItems().addAll(behrooz, golestan, tabiat);
        });
        oil.setOnAction(event -> {

            menuInfo(2, "oil", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem ladan = new MenuItem("Ladan");
            MenuItem oila = new MenuItem("Oila");
            MenuItem tabiat = new MenuItem("Tabiat");

            ladan.setOnAction(ev -> {
                menuButtonBrand.setText("ladan");
            });
            oila.setOnAction(actionEvent -> {
                menuButtonBrand.setText("oila");
            });
            tabiat.setOnAction(actionEvent -> {
                menuButtonBrand.setText("tabiat");
            });

            menuButtonBrand.getItems().addAll(ladan, oila, tabiat);
        });
        brokenSugar.setOnAction(event -> {

            menuInfo(2, "brokenSugar", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem azughe = new MenuItem("Azughe");
            MenuItem ferdous = new MenuItem("Ferdous");
            MenuItem mashMash = new MenuItem("Mash Mash");

            azughe.setOnAction(actionEvent -> {
                menuButtonBrand.setText("azughe");
            });
            ferdous.setOnAction(actionEvent -> {
                menuButtonBrand.setText("ferdous");
            });
            mashMash.setOnAction(actionEvent -> {
                menuButtonBrand.setText("mash mash");
            });

            menuButtonBrand.getItems().addAll(azughe, ferdous, mashMash);
        });
        sugar.setOnAction(event -> {

            menuInfo(2, "sugar", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem emruz = new MenuItem("Emruz");
            MenuItem golestan = new MenuItem("Golestan");
            MenuItem shahdane = new MenuItem("Shahdane");

            emruz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("emruz");
            });
            golestan.setOnAction(actionEvent -> {
                menuButtonBrand.setText("golestan");
            });
            shahdane.setOnAction(actionEvent -> {
                menuButtonBrand.setText("shahdane");
            });

            menuButtonBrand.getItems().addAll(emruz, golestan, shahdane);
        });
        sauce.setOnAction(event -> {

            menuInfo(2, "sauce", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem behruz = new MenuItem("Behruz");
            MenuItem delvese = new MenuItem("Delvese");
            MenuItem mahram = new MenuItem("Mahram");

            behruz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("behruz");
            });
            delvese.setOnAction(actionEvent -> {
                menuButtonBrand.setText("delvese");
            });
            mahram.setOnAction(actionEvent -> {
                menuButtonBrand.setText("mahram");
            });

            menuButtonBrand.getItems().addAll(behruz, delvese, mahram);
        });
        rob.setOnAction(event -> {

            menuInfo(2, "rob", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem chinChin = new MenuItem("Chin Chin");
            MenuItem tabarok = new MenuItem("Tabarok");
            MenuItem tabiat = new MenuItem("Tabiat");

            chinChin.setOnAction(actionEvent -> {
                menuButtonBrand.setText("chin chin");
            });
            tabarok.setOnAction(actionEvent -> {
                menuButtonBrand.setText("tabarok");
            });
            tabiat.setOnAction(actionEvent -> {
                menuButtonBrand.setText("tabiat");
            });

            menuButtonBrand.getItems().addAll(chinChin, tabarok, tabiat);
        });
        pickle.setOnAction(event -> {

            menuInfo(2, "pickle", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem behruz = new MenuItem("Behruz");
            MenuItem mahram = new MenuItem("Mahram");
            MenuItem yekOyek = new MenuItem("Yek & Yek");

            behruz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("behruz");
            });
            mahram.setOnAction(actionEvent -> {
                menuButtonBrand.setText("mahram");
            });
            yekOyek.setOnAction(actionEvent -> {
                menuButtonBrand.setText("yek & yek");
            });

            menuButtonBrand.getItems().addAll(behruz, mahram, yekOyek);
        });
        lemonJuice.setOnAction(event -> {

            menuInfo(2, "lemonJuice", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem behruz = new MenuItem("Behruz");
            MenuItem mahram = new MenuItem("Mahram");
            MenuItem yekOyek = new MenuItem("Yek & Yek");

            behruz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("behruz");
            });
            mahram.setOnAction(actionEvent -> {
                menuButtonBrand.setText("mahram");
            });
            yekOyek.setOnAction(actionEvent -> {
                menuButtonBrand.setText("yek & yek");
            });

            menuButtonBrand.getItems().addAll(behruz, mahram, yekOyek);
        });
        pasta.setOnAction(event -> {

            menuInfo(2, "pasta", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem deCecco = new MenuItem("De Cecco");
            MenuItem tak = new MenuItem("Tak Makaron");
            MenuItem zar = new MenuItem("Zar Makaron");

            deCecco.setOnAction(actionEvent -> {
                menuButtonBrand.setText("de cecco");
            });
            tak.setOnAction(actionEvent -> {
                menuButtonBrand.setText("tak makaron");
            });
            zar.setOnAction(actionEvent -> {
                menuButtonBrand.setText("zar makaron");
            });

            menuButtonBrand.getItems().addAll(deCecco, tak, zar);
        });
        saffron.setOnAction(event -> {

            menuInfo(2, "saffron", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem bahraman = new MenuItem("Bahraman");
            MenuItem momtaz = new MenuItem("Momtaz");
            MenuItem seharkhiz = new MenuItem("Seharkhiz");

            bahraman.setOnAction(actionEvent -> {
                menuButtonBrand.setText("bahraman");
            });
            momtaz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("momtaz");
            });
            seharkhiz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("seharkhiz");
            });

            menuButtonBrand.getItems().addAll(bahraman, momtaz, seharkhiz);
        });
        pea.setOnAction(event -> {

            menuInfo(2, "pea", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem aaliChin = new MenuItem("Aali Chin");
            MenuItem delpazir = new MenuItem("Delpazir");
            MenuItem shahsavand = new MenuItem("Shahsavand");

            aaliChin.setOnAction(actionEvent -> {
                menuButtonBrand.setText("aali chin");
            });
            delpazir.setOnAction(actionEvent -> {
                menuButtonBrand.setText("delpazir");
            });
            shahsavand.setOnAction(actionEvent -> {
                menuButtonBrand.setText("shahsavand");
            });

            menuButtonBrand.getItems().addAll(aaliChin, delpazir, shahsavand);
        });

        menuButtonMinorKind.getItems().addAll(bread, rice, oil, brokenSugar, sugar, sauce, rob, pickle, lemonJuice, pasta, saffron, pea);
    }

    public void breakfast(MenuButton menuButtonKind, MenuButton menuButtonMinorKind, MenuButton menuButtonBrand) {

        menuInfo(1, "break fast", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

        MenuItem jam = new MenuItem("jam");
        MenuItem honey = new MenuItem("honey");
        MenuItem halva = new MenuItem("halva");

        jam.setOnAction(event -> {

            menuInfo(2, "jam", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem bijan = new MenuItem("Bijan");
            MenuItem mixLand = new MenuItem("Mix Land");
            MenuItem yekOyek = new MenuItem("yek & yek");

            bijan.setOnAction(ev -> {
                menuButtonBrand.setText("bijan");
            });
            mixLand.setOnAction(ev -> {
                menuButtonBrand.setText("mix land");
            });
            yekOyek.setOnAction(ev -> {
                menuButtonBrand.setText("yek & yek");
            });

            menuButtonBrand.getItems().addAll(bijan, mixLand, yekOyek);
        });
        honey.setOnAction(event -> {

            menuInfo(2, "honey", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem rayehe = new MenuItem("Rayehe");
            MenuItem segmen = new MenuItem("Segmen");
            MenuItem shahsavand = new MenuItem("Shahsavand");

            rayehe.setOnAction(ev -> {
                menuButtonBrand.setText("rayehe");
            });
            segmen.setOnAction(ev -> {
                menuButtonBrand.setText("segmen");
            });
            shahsavand.setOnAction(ev -> {
                menuButtonBrand.setText("shahsavand");
            });

            menuButtonBrand.getItems().addAll(rayehe, segmen, shahsavand);
        });
        halva.setOnAction(event -> {

            menuInfo(2, "halva", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem oghab = new MenuItem("Oghab");
            MenuItem tahini = new MenuItem("Tahini");
            MenuItem taksun = new MenuItem("Taksun");

            oghab.setOnAction(ev -> {
                menuButtonBrand.setText("oghab");
            });
            tahini.setOnAction(ev -> {
                menuButtonBrand.setText("tahini");
            });
            taksun.setOnAction(ev -> {
                menuButtonBrand.setText("taksun");
            });

            menuButtonBrand.getItems().addAll(oghab, tahini, taksun);
        });

        menuButtonMinorKind.getItems().addAll(jam, honey, halva);
    }

    public void protein(MenuButton menuButtonKind, MenuButton menuButtonMinorKind, MenuButton menuButtonBrand) {

        menuInfo(1, "protein foods", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

        MenuItem bologna = new MenuItem("bologna");
        MenuItem lambMeet = new MenuItem("lamb meet");
        MenuItem chicken = new MenuItem("chicken");
        MenuItem egg = new MenuItem("egg");
        MenuItem beef = new MenuItem("beef");
        MenuItem shrimp = new MenuItem("shrimp");
        MenuItem tuna = new MenuItem("tuna");

        bologna.setOnAction(event -> {

            menuInfo(2, "bologna", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem kampooreh = new MenuItem("Kampooreh");
            MenuItem robat = new MenuItem("Robat");
            MenuItem shamSham = new MenuItem("Sham Sham");

            kampooreh.setOnAction(ev -> {
                menuButtonBrand.setText("kampooreh");
            });
            robat.setOnAction(ev -> {
                menuButtonBrand.setText("robat");
            });
            shamSham.setOnAction(ev -> {
                menuButtonBrand.setText("sham sham");
            });

            menuButtonBrand.getItems().addAll(kampooreh, robat, shamSham);
        });
        lambMeet.setOnAction(event -> {

            menuInfo(2, "lambMeet", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem ariaBahar = new MenuItem("Aria Bahar");
            MenuItem mahya = new MenuItem("Mahya");
            MenuItem puya = new MenuItem("Puya");

            ariaBahar.setOnAction(ev -> {
                menuButtonBrand.setText("aria bahar");
            });
            mahya.setOnAction(ev -> {
                menuButtonBrand.setText("mahya");
            });
            puya.setOnAction(ev -> {
                menuButtonBrand.setText("puya");
            });

            menuButtonBrand.getItems().addAll(ariaBahar, mahya, puya);
        });
        chicken.setOnAction(event -> {

            menuInfo(2, "chicken", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem mahya = new MenuItem("Mahya");
            MenuItem puya = new MenuItem("Puya");
            MenuItem telavang = new MenuItem("Puya");

            mahya.setOnAction(ev -> {
                menuButtonBrand.setText("mahya");
            });
            puya.setOnAction(ev -> {
                menuButtonBrand.setText("puya");
            });
            telavang.setOnAction(ev -> {
                menuButtonBrand.setText("telavang");
            });

            menuButtonBrand.getItems().addAll(mahya, puya, telavang);
        });
        egg.setOnAction(event -> {

            menuInfo(2, "egg", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem morghdaran = new MenuItem("Morghdaran");
            MenuItem porotana = new MenuItem("Porotana");
            MenuItem telavang = new MenuItem("Telavang");

            morghdaran.setOnAction(ev -> {
                menuButtonBrand.setText("morghdaran");
            });
            porotana.setOnAction(ev -> {
                menuButtonBrand.setText("porotana");
            });
            telavang.setOnAction(ev -> {
                menuButtonBrand.setText("telavang");
            });

            menuButtonBrand.getItems().addAll(morghdaran, porotana, telavang);
        });
        beef.setOnAction(event -> {

            menuInfo(2, "beef", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem khoram = new MenuItem("Khoram");
            MenuItem mahya = new MenuItem("Mahya");
            MenuItem puya = new MenuItem("Puya");

            khoram.setOnAction(ev -> {
                menuButtonBrand.setText("khoram");
            });
            mahya.setOnAction(ev -> {
                menuButtonBrand.setText("mahya");
            });
            puya.setOnAction(ev -> {
                menuButtonBrand.setText("puya");
            });

            menuButtonBrand.getItems().addAll(khoram, mahya, puya);
        });
        shrimp.setOnAction(event -> {

            menuInfo(2, "shrimp", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem marine = new MenuItem("Marine");
            MenuItem pemina = new MenuItem("Pemina");
            MenuItem tohfe = new MenuItem("Tohfe");

            marine.setOnAction(ev -> {
                menuButtonBrand.setText("marine");
            });
            pemina.setOnAction(ev -> {
                menuButtonBrand.setText("pemina");
            });
            tohfe.setOnAction(ev -> {
                menuButtonBrand.setText("tohfe");
            });

            menuButtonBrand.getItems().addAll(marine, pemina, tohfe);
        });
        tuna.setOnAction(event -> {

            menuInfo(2, "tuna", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem makenzi = new MenuItem("Makenzi");
            MenuItem shilton = new MenuItem("Shilton");
            MenuItem tohfe = new MenuItem("Tohfe");

            makenzi.setOnAction(ev -> {
                menuButtonBrand.setText("makenzi");
            });
            shilton.setOnAction(ev -> {
                menuButtonBrand.setText("shilton");
            });
            tohfe.setOnAction(ev -> {
                menuButtonBrand.setText("tohfe");
            });

            menuButtonBrand.getItems().addAll(makenzi, shilton, tohfe);
        });

        menuButtonMinorKind.getItems().addAll(bologna, lambMeet, chicken, egg, beef, shrimp, tuna);
    }

    public void dairy(MenuButton menuButtonKind, MenuButton menuButtonMinorKind, MenuButton menuButtonBrand) {

        menuInfo(1, "dairy", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

        MenuItem milk = new MenuItem("milk");
        MenuItem yogurt = new MenuItem("yogurt");
        MenuItem cheese = new MenuItem("cheese");
        MenuItem cream = new MenuItem("cream");

        milk.setOnAction(event -> {

            menuInfo(2, "milk", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem damdaran = new MenuItem("Damdaran");
            MenuItem kale = new MenuItem("Kale");
            MenuItem mihan = new MenuItem("Mihan");

            damdaran.setOnAction(ev -> {
                menuButtonBrand.setText("damdaran");
            });
            kale.setOnAction(ev -> {
                menuButtonBrand.setText("kale");
            });
            mihan.setOnAction(ev -> {
                menuButtonBrand.setText("mihan");
            });

            menuButtonBrand.getItems().addAll(damdaran, kale, mihan);
        });
        yogurt.setOnAction(event -> {

            menuInfo(2, "yogurt", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem kale = new MenuItem("Kale");
            MenuItem mihan = new MenuItem("Mihan");
            MenuItem ramak = new MenuItem("Ramak");

            kale.setOnAction(ev -> {
                menuButtonBrand.setText("kale");
            });
            mihan.setOnAction(ev -> {
                menuButtonBrand.setText("mihan");
            });
            ramak.setOnAction(ev -> {
                menuButtonBrand.setText("ramak");
            });

            menuButtonBrand.getItems().addAll(kale, mihan, ramak);
        });
        cheese.setOnAction(event -> {

            menuInfo(2, "cheese", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem kale = new MenuItem("Kale");
            MenuItem mihan = new MenuItem("Mihan");
            MenuItem ruzane = new MenuItem("Ruzane");

            kale.setOnAction(ev -> {
                menuButtonBrand.setText("kale");
            });
            mihan.setOnAction(ev -> {
                menuButtonBrand.setText("mihan");
            });
            ruzane.setOnAction(ev -> {
                menuButtonBrand.setText("ruzane");
            });

            menuButtonBrand.getItems().addAll(kale, mihan, ruzane);
        });
        cream.setOnAction(event -> {

            menuInfo(2, "cream", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem kale = new MenuItem("Kale");
            MenuItem mihan = new MenuItem("Mihan");
            MenuItem pegah = new MenuItem("Pegah");

            kale.setOnAction(ev -> {
                menuButtonBrand.setText("kale");
            });
            mihan.setOnAction(ev -> {
                menuButtonBrand.setText("mihan");
            });
            pegah.setOnAction(ev -> {
                menuButtonBrand.setText("pegah");
            });

            menuButtonBrand.getItems().addAll(kale, mihan, pegah);
        });

        menuButtonMinorKind.getItems().addAll(milk, yogurt, cheese, cream);
    }

    public void drinks(MenuButton menuButtonKind, MenuButton menuButtonMinorKind, MenuButton menuButtonBrand) {

        menuInfo(1, "drinks", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

        MenuItem tea = new MenuItem("tea");
        MenuItem herbalTea = new MenuItem("herbal tea");
        MenuItem coffee = new MenuItem("coffee");
        MenuItem water = new MenuItem("water");
        MenuItem beer = new MenuItem("beer");
        MenuItem soda = new MenuItem("soda");
        MenuItem juice = new MenuItem("juice");

        tea.setOnAction(event -> {

            menuInfo(2, "tea", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem debsh = new MenuItem("Debsh");
            MenuItem golestan = new MenuItem("Golestan");
            MenuItem shahrzad = new MenuItem("Shahrzad");

            debsh.setOnAction(ev -> {
                menuButtonBrand.setText("debsh");
            });
            golestan.setOnAction(ev -> {
                menuButtonBrand.setText("golestan");
            });
            shahrzad.setOnAction(ev -> {
                menuButtonBrand.setText("shahrzad");
            });

            menuButtonBrand.getItems().addAll(debsh, golestan, shahrzad);
        });
        herbalTea.setOnAction(event -> {

            menuInfo(2, "herbalTea", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem golestan = new MenuItem("Golestan");
            MenuItem seharKhiz = new MenuItem("Sehar Khiz");
            MenuItem shahsavand = new MenuItem("Shahsavand");

            golestan.setOnAction(ev -> {
                menuButtonBrand.setText("golestan");
            });
            shahsavand.setOnAction(ev -> {
                menuButtonBrand.setText("shahsavand");
            });
            shahsavand.setOnAction(ev -> {
                menuButtonBrand.setText("shahsavand");
            });

            menuButtonBrand.getItems().addAll(golestan, seharKhiz, shahsavand);
        });
        coffee.setOnAction(event -> {

            menuInfo(2, "coffee", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem aliCafe = new MenuItem("Ali Cafe");
            MenuItem goodDay = new MenuItem("Good Day");
            MenuItem nescafe = new MenuItem("Nescafe");

            aliCafe.setOnAction(ev -> {
                menuButtonBrand.setText("ali cafe");
            });
            goodDay.setOnAction(ev -> {
                menuButtonBrand.setText("good day");
            });
            nescafe.setOnAction(ev -> {
                menuButtonBrand.setText("nescafe");
            });

            menuButtonBrand.getItems().addAll(aliCafe, goodDay, nescafe);
        });
        water.setOnAction(event -> {

            menuInfo(2, "water", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem damavand = new MenuItem("Damavand");
            MenuItem miva = new MenuItem("Miva");
            MenuItem oxab = new MenuItem("Oxab");

            damavand.setOnAction(ev -> {
                menuButtonBrand.setText("damavand");
            });
            miva.setOnAction(ev -> {
                menuButtonBrand.setText("miva");
            });
            oxab.setOnAction(ev -> {
                menuButtonBrand.setText("oxab");
            });

            menuButtonBrand.getItems().addAll(damavand, miva, oxab);
        });
        beer.setOnAction(event -> {

            menuInfo(2, "beer", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem aalis = new MenuItem("Aalis");
            MenuItem heyDay = new MenuItem("Hey Day");
            MenuItem jojo = new MenuItem("Jojo");

            aalis.setOnAction(ev -> {
                menuButtonBrand.setText("aalis");
            });
            heyDay.setOnAction(ev -> {
                menuButtonBrand.setText("hey day");
            });
            jojo.setOnAction(ev -> {
                menuButtonBrand.setText("jojo");
            });

            menuButtonBrand.getItems().addAll(aalis, heyDay, jojo);
        });
        soda.setOnAction(event -> {

            menuInfo(2, "soda", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem cocaCola = new MenuItem("Coca Cola");
            MenuItem fantaLemon = new MenuItem("Fanta Lemon");
            MenuItem fantaOrange = new MenuItem("Fanta Orange");

            cocaCola.setOnAction(ev -> {
                menuButtonBrand.setText("coca cola");
            });
            fantaLemon.setOnAction(ev -> {
                menuButtonBrand.setText("fanta lemon");
            });
            fantaOrange.setOnAction(ev -> {
                menuButtonBrand.setText("fanta orange");
            });

            menuButtonBrand.getItems().addAll(cocaCola, fantaLemon, fantaOrange);
        });
        juice.setOnAction(event -> {

            menuInfo(2, "juice", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem mihan = new MenuItem("Mihan");
            MenuItem sanIch = new MenuItem("San Ich");
            MenuItem takDaneh = new MenuItem("Tak Daneh");

            mihan.setOnAction(ev -> {
                menuButtonBrand.setText("mihan");
            });
            sanIch.setOnAction(ev -> {
                menuButtonBrand.setText("san ich");
            });
            takDaneh.setOnAction(ev -> {
                menuButtonBrand.setText("tak daneh");
            });

            menuButtonBrand.getItems().addAll(mihan, sanIch, takDaneh);
        });

        menuButtonMinorKind.getItems().addAll(tea, herbalTea, coffee, water, beer, soda, juice);
    }

    public void snacks(MenuButton menuButtonKind, MenuButton menuButtonMinorKind, MenuButton menuButtonBrand) {

        menuInfo(1, "snacks", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

        MenuItem chocolate = new MenuItem("chocolate");
        MenuItem biscuit = new MenuItem("biscuit");
        MenuItem nuts = new MenuItem("nuts");
        MenuItem cake = new MenuItem("cake");
        MenuItem chips = new MenuItem("chips");
        MenuItem pofak = new MenuItem("pofak");
        MenuItem chewingGum = new MenuItem("chewing gum");

        chocolate.setOnAction(event -> {

            menuInfo(2, "chocolate", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem hiss = new MenuItem("Hiss");
            MenuItem rangarang = new MenuItem("Rangarang");
            MenuItem takTak = new MenuItem("Tak Daneh");

            hiss.setOnAction(ev -> {
                menuButtonBrand.setText("hiss");
            });
            rangarang.setOnAction(ev -> {
                menuButtonBrand.setText("rangarang");
            });
            takTak.setOnAction(ev -> {
                menuButtonBrand.setText("tak tak");
            });

            menuButtonBrand.getItems().addAll(hiss, rangarang, takTak);
        });
        biscuit.setOnAction(event -> {

            menuInfo(2, "biscuit", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem hiBye = new MenuItem("Hi Bye");
            MenuItem petitBeurre = new MenuItem("petit Beurre");
            MenuItem sagheTalai = new MenuItem("Saghe Talai");

            hiBye.setOnAction(ev -> {
                menuButtonBrand.setText("hi bye");
            });
            petitBeurre.setOnAction(ev -> {
                menuButtonBrand.setText("petit beurre");
            });
            sagheTalai.setOnAction(ev -> {
                menuButtonBrand.setText("saghe talai");
            });

            menuButtonBrand.getItems().addAll(hiBye, petitBeurre, sagheTalai);
        });
        nuts.setOnAction(event -> {

            menuInfo(2, "nuts", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem mani = new MenuItem("Mani");
            MenuItem mazMaz = new MenuItem("Maz Maz");
            MenuItem sanjaghak = new MenuItem("Sanjaghak");

            mani.setOnAction(ev -> {
                menuButtonBrand.setText("mani");
            });
            mazMaz.setOnAction(ev -> {
                menuButtonBrand.setText("maz maz");
            });
            sanjaghak.setOnAction(ev -> {
                menuButtonBrand.setText("sanjaghak");
            });

            menuButtonBrand.getItems().addAll(mani, mazMaz, sanjaghak);
        });
        cake.setOnAction(event -> {

            menuInfo(2, "cake", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem cakeMake = new MenuItem("Cake Make");
            MenuItem tTop = new MenuItem("T Top");
            MenuItem tiny = new MenuItem("Tiny");

            cakeMake.setOnAction(ev -> {
                menuButtonBrand.setText("cake make");
            });
            tTop.setOnAction(ev -> {
                menuButtonBrand.setText("t top");
            });
            tiny.setOnAction(ev -> {
                menuButtonBrand.setText("tiny");
            });

            menuButtonBrand.getItems().addAll(cakeMake, tTop, tiny);
        });
        chips.setOnAction(event -> {

            menuInfo(2, "chips", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem chakelz = new MenuItem("Chakelz");
            MenuItem cheetoz = new MenuItem("Cheeroz");
            MenuItem delMaze = new MenuItem("Del maze");

            chakelz.setOnAction(ev -> {
                menuButtonBrand.setText("chakelz");
            });
            cheetoz.setOnAction(ev -> {
                menuButtonBrand.setText("cheetoz");
            });
            delMaze.setOnAction(ev -> {
                menuButtonBrand.setText("del maze");
            });


            menuButtonBrand.getItems().addAll(chakelz, cheetoz, delMaze);
        });
        pofak.setOnAction(event -> {

            menuInfo(2, "pofak", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem chakelz = new MenuItem("Chakelz");
            MenuItem cheetoz = new MenuItem("Cheeroz");
            MenuItem pofakNamaki = new MenuItem("Pofak Namaki");

            chakelz.setOnAction(ev -> {
                menuButtonBrand.setText("chakelz");
            });
            cheetoz.setOnAction(ev -> {
                menuButtonBrand.setText("cheetoz");
            });
            pofakNamaki.setOnAction(ev -> {
                menuButtonBrand.setText("pofak namaki");
            });

            menuButtonBrand.getItems().addAll(chakelz, cheetoz, pofakNamaki);
        });
        chewingGum.setOnAction(event -> {

            menuInfo(2, "chewingGum", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem action = new MenuItem("Action");
            MenuItem biodent = new MenuItem("Biodent");
            MenuItem trident = new MenuItem("Trident");

            action.setOnAction(ev -> {
                menuButtonBrand.setText("action");
            });
            biodent.setOnAction(ev -> {
                menuButtonBrand.setText("biodent");
            });
            trident.setOnAction(ev -> {
                menuButtonBrand.setText("trident");
            });

            menuButtonBrand.getItems().addAll(action, biodent, trident);
        });

        menuButtonMinorKind.getItems().addAll(chocolate, biscuit, nuts, cake, chips, pofak, chewingGum);
    }

    public void menuInfo(int code, String menuTitle, MenuButton menuButtonKind, MenuButton menuButtonMinorKind, MenuButton menuButtonBrand) {

        if (code == 1) {
            menuButtonKind.setText(menuTitle);
            menuButtonMinorKind.getItems().clear();
            menuButtonMinorKind.setText("Minor Kind");
        }
        if (code == 2) {
            menuButtonMinorKind.setText(menuTitle);
        }
        menuButtonBrand.getItems().clear();
        menuButtonBrand.setText("Brand");
        if (addtxt != null) addtxt.setText("");
    }


    @FXML
    private TextField txtProductName, txtProductPrice, txtProductSize;
    @FXML
    TextArea txtProductProperty;
    @FXML
    private Label addtxt;
    @FXML
    private ImageView productImg;

    public void addProduct() throws SQLException {
        if (!menuButtonKind.getText().equals("Kind") && !menuButtonMinorKind.getText().equals("Minor Kind") && !menuButtonBrand.getText().equals("Brand") &&
                !txtProductName.getText().equals("") && !txtProductPrice.getText().equals("") && !txtProductSize.getText().equals("")) {
            Item item = new Item(
                    menuButtonKind.getText(),
                    menuButtonMinorKind.getText(),
                    menuButtonBrand.getText(),
                    txtProductName.getText(),
                    Integer.parseInt(txtProductPrice.getText()),
                    Integer.parseInt(txtProductSize.getText()),
                    txtProductProperty.getText(),
                    productImg.getImage()
            );

            if (!Application.shop.currentSeller.items.contains(item)) {
                Application.shop.currentSeller.items.add(item);
                Database.addProduct(item);

                productImg.setImage(item.image);
                addtxt.setText("add successfully");


                addNewItemToMainPage(item);

            } else {
                addtxt.setText("product is valid");
            }
        } else {
            addtxt.setText("fill all the blanks");
        }
    }

    @FXML
    private AnchorPane sellerMainPage;
    int icount = 0, jcount = 0;

    public void addNewItemToMainPage(Item item) {

        ImageView imageView = new ImageView(item.image);
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                try {
                    FXMLLoader fxmlLoader = null;

                   /* if (Application.shop.currentSeller == null)
                        fxmlLoader = new FXMLLoader(Application.class.getResource("productCustomer.fxml"));
                    if (Application.shop.currentCustomer == null)
                        fxmlLoader = new FXMLLoader(Application.class.getResource("productSeller.fxml"));*/
                    fxmlLoader = new FXMLLoader(Application.class.getResource("productCustomer.fxml"));//have to remove

                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        ImageView scoreEmoji = new ImageView(item.scoreEmoji);
        scoreEmoji.setEffect(new DropShadow());
        scoreEmoji.setFitWidth(30);
        scoreEmoji.setFitHeight(30);
        scoreEmoji.setLayoutX(5);
        scoreEmoji.setLayoutY(210);

        Label name = new Label(item.name);
        name.setFont(new Font("Arial", 20));
        name.setPrefWidth(150);
        name.setAlignment(Pos.CENTER);
        name.setLayoutX(0);
        name.setLayoutY(145);

        Label price = new Label(String.valueOf(item.price));
        price.setFont(new Font("Arial", 20));
        price.setPrefWidth(150);
        price.setAlignment(Pos.CENTER);
        price.setLayoutX(0);
        price.setLayoutY(175);

        Label score = new Label(item.score + "%");
        score.setFont(new Font("Arial", 15));
        score.setLayoutX(37);
        score.setLayoutY(217);

        Button button = new Button("auction");
        button.setEffect(new DropShadow());
        button.setPrefHeight(30);
        button.setLayoutX(90);
        button.setLayoutY(210);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setLayoutX(icount * 175 + 30);
        anchorPane.setLayoutY(jcount * 225 + 30);
        anchorPane.setPrefWidth(150);
        anchorPane.setPrefHeight(250);
        anchorPane.setStyle("-fx-background-color: #FFCF21;");
        anchorPane.setEffect(new DropShadow());
        anchorPane.setEffect(new InnerShadow());


        anchorPane.getChildren().addAll(imageView, name, price, button, scoreEmoji, score);
        sellerMainPage.getChildren().add(anchorPane);

        icount++;
        if (icount > 5) {
            jcount++;
            icount = 0;
        }
    }


    public void loadImage() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.gif)", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            productImg.setImage(image);
        }
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


    //for the spinner in cart tab see bro code
}
