package com.example.shopapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ControllerApplicant {


    public ControllerApplicant() {
        //showItems();
    }


    @FXML
    private Label txtFNaccount, txtLNaccount, txtPNaccount, txtUNaccount, txtPWaccount, txtEMaccount, txtwpaccount;
    @FXML
    MenuButton menuButtonKind, menuButtonMinorKind, menuButtonBrand;
    @FXML
    MenuButton filterButtonKind, filterButtonMinorKind, filterButtonBrand;

    public void displayInfo() {
        if (Application.shop.currentCustomer != null) {
            txtFNaccount.setText(Application.shop.currentCustomer.getFirstname());
            txtLNaccount.setText(Application.shop.currentCustomer.getLastname());
            txtPNaccount.setText(Application.shop.currentCustomer.getPhoneNumber());
            txtUNaccount.setText(Application.shop.currentCustomer.getUsername());
            txtPWaccount.setText(Application.shop.currentCustomer.getPassword());
            txtEMaccount.setText(Application.shop.currentCustomer.getEmail());
        } else if (Application.shop.currentSeller != null) {
            txtFNaccount.setText(Application.shop.currentSeller.getFirstname());
            txtLNaccount.setText(Application.shop.currentSeller.getLastname());
            txtPNaccount.setText(Application.shop.currentSeller.getPhoneNumber());
            txtUNaccount.setText(Application.shop.currentSeller.getUsername());
            txtPWaccount.setText(Application.shop.currentSeller.getPassword());
            txtEMaccount.setText(Application.shop.currentSeller.getEmail());
            txtwpaccount.setText(Application.shop.currentSeller.workplace);
        }
    }


    public void changeScene(ActionEvent e, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void changeToLoginScene(ActionEvent e) throws IOException {
        changeScene(e, "Login.fxml");
    }

    public void changeToCartScene(MouseEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("cart.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
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
            MenuItem newYorkBakery = new MenuItem("New York bakery");

            mazrae.setOnAction(ev -> {
                menuButtonBrand.setText("mazrae");
            });
            newYorkBakery.setOnAction(actionEvent -> {
                menuButtonBrand.setText("new york bakery");
            });

            menuButtonBrand.getItems().addAll(mazrae, newYorkBakery);
        });
        rice.setOnAction(event -> {

            menuInfo(2, "rice", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem behrooz = new MenuItem("Behrooz");
            MenuItem golestan = new MenuItem("Golestan");
            MenuItem tabiat = new MenuItem("Tabiat");
            MenuItem uncleBens = new MenuItem("Uncle Ben's");

            behrooz.setOnAction(ev -> {
                menuButtonBrand.setText("behrooz");
            });
            golestan.setOnAction(ev -> {
                menuButtonBrand.setText("golestan");
            });
            tabiat.setOnAction(ev -> {
                menuButtonBrand.setText("tabiat");
            });
            uncleBens.setOnAction(actionEvent -> {
                menuButtonBrand.setText("uncle ben's");
            });

            menuButtonBrand.getItems().addAll(behrooz, golestan, tabiat, uncleBens);
        });
        oil.setOnAction(event -> {

            menuInfo(2, "oil", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem ladan = new MenuItem("Ladan");
            MenuItem oila = new MenuItem("Oila");
            MenuItem tabiat = new MenuItem("Tabiat");
            MenuItem louAna = new MenuItem("louAna");

            ladan.setOnAction(ev -> {
                menuButtonBrand.setText("ladan");
            });
            oila.setOnAction(actionEvent -> {
                menuButtonBrand.setText("oila");
            });
            tabiat.setOnAction(actionEvent -> {
                menuButtonBrand.setText("tabiat");
            });
            louAna.setOnAction(actionEvent -> {
                menuButtonBrand.setText("louAna");
            });


            menuButtonBrand.getItems().addAll(ladan, oila, tabiat, louAna);
        });
        brokenSugar.setOnAction(event -> {

            menuInfo(2, "brokenSugar", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem azughe = new MenuItem("Azughe");
            MenuItem ferdous = new MenuItem("Ferdous");
            MenuItem mashMash = new MenuItem("Mash Mash");
            MenuItem laPerruche = new MenuItem("La Perruche");

            azughe.setOnAction(actionEvent -> {
                menuButtonBrand.setText("azughe");
            });
            ferdous.setOnAction(actionEvent -> {
                menuButtonBrand.setText("ferdous");
            });
            mashMash.setOnAction(actionEvent -> {
                menuButtonBrand.setText("mash mash");
            });
            laPerruche.setOnAction(actionEvent -> {
                menuButtonBrand.setText("la perruche");
            });

            menuButtonBrand.getItems().addAll(azughe, ferdous, mashMash, laPerruche);
        });
        sugar.setOnAction(event -> {

            menuInfo(2, "sugar", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem emruz = new MenuItem("Emruz");
            MenuItem golestan = new MenuItem("Golestan");
            MenuItem shahdane = new MenuItem("Shahdane");
            MenuItem chelsea = new MenuItem("Chelsea");

            emruz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("emruz");
            });
            golestan.setOnAction(actionEvent -> {
                menuButtonBrand.setText("golestan");
            });
            shahdane.setOnAction(actionEvent -> {
                menuButtonBrand.setText("shahdane");
            });
            chelsea.setOnAction(actionEvent -> {
                menuButtonBrand.setText("chelsea");
            });

            menuButtonBrand.getItems().addAll(emruz, golestan, shahdane, chelsea);
        });
        sauce.setOnAction(event -> {

            menuInfo(2, "sauce", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem behruz = new MenuItem("Behruz");
            MenuItem delvese = new MenuItem("Delvese");
            MenuItem mahram = new MenuItem("Mahram");
            MenuItem heinz = new MenuItem("Heinz");

            behruz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("behruz");
            });
            delvese.setOnAction(actionEvent -> {
                menuButtonBrand.setText("delvese");
            });
            mahram.setOnAction(actionEvent -> {
                menuButtonBrand.setText("mahram");
            });
            heinz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("heinz");
            });

            menuButtonBrand.getItems().addAll(behruz, delvese, mahram, heinz);
        });
        rob.setOnAction(event -> {

            menuInfo(2, "rob", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem chinChin = new MenuItem("Chin Chin");
            MenuItem tabarok = new MenuItem("Tabarok");
            MenuItem tabiat = new MenuItem("Tabiat");
            MenuItem hunts = new MenuItem("Hunts");

            chinChin.setOnAction(actionEvent -> {
                menuButtonBrand.setText("chin chin");
            });
            tabarok.setOnAction(actionEvent -> {
                menuButtonBrand.setText("tabarok");
            });
            tabiat.setOnAction(actionEvent -> {
                menuButtonBrand.setText("tabiat");
            });
            hunts.setOnAction(actionEvent -> {
                menuButtonBrand.setText("hunts");
            });

            menuButtonBrand.getItems().addAll(chinChin, tabarok, tabiat, hunts);
        });
        pickle.setOnAction(event -> {

            menuInfo(2, "pickle", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem behruz = new MenuItem("Behruz");
            MenuItem mahram = new MenuItem("Mahram");
            MenuItem yekOyek = new MenuItem("Yek & Yek");
            MenuItem vlasic = new MenuItem("Vlasic");

            behruz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("behruz");
            });
            mahram.setOnAction(actionEvent -> {
                menuButtonBrand.setText("mahram");
            });
            yekOyek.setOnAction(actionEvent -> {
                menuButtonBrand.setText("yek & yek");
            });
            vlasic.setOnAction(actionEvent -> {
                menuButtonBrand.setText("vlasic");
            });

            menuButtonBrand.getItems().addAll(behruz, mahram, yekOyek, vlasic);
        });
        lemonJuice.setOnAction(event -> {

            menuInfo(2, "lemonJuice", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem behruz = new MenuItem("Behruz");
            MenuItem mahram = new MenuItem("Mahram");
            MenuItem yekOyek = new MenuItem("Yek & Yek");
            MenuItem lakewood = new MenuItem("Lakewood");

            behruz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("behruz");
            });
            mahram.setOnAction(actionEvent -> {
                menuButtonBrand.setText("mahram");
            });
            yekOyek.setOnAction(actionEvent -> {
                menuButtonBrand.setText("yek & yek");
            });
            lakewood.setOnAction(actionEvent -> {
                menuButtonBrand.setText("lakewood");
            });

            menuButtonBrand.getItems().addAll(behruz, mahram, yekOyek, lakewood);
        });
        pasta.setOnAction(event -> {

            menuInfo(2, "pasta", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem deCecco = new MenuItem("De Cecco");
            MenuItem tak = new MenuItem("Tak Makaron");
            MenuItem zar = new MenuItem("Zar Makaron");
            MenuItem goya = new MenuItem("Goya");

            deCecco.setOnAction(actionEvent -> {
                menuButtonBrand.setText("de cecco");
            });
            tak.setOnAction(actionEvent -> {
                menuButtonBrand.setText("tak makaron");
            });
            zar.setOnAction(actionEvent -> {
                menuButtonBrand.setText("zar makaron");
            });
            goya.setOnAction(actionEvent -> {
                menuButtonBrand.setText("goya");
            });

            menuButtonBrand.getItems().addAll(deCecco, tak, zar, goya);
        });
        saffron.setOnAction(event -> {

            menuInfo(2, "saffron", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem bahraman = new MenuItem("Bahraman");
            MenuItem momtaz = new MenuItem("Momtaz");
            MenuItem seharkhiz = new MenuItem("Seharkhiz");
            MenuItem frintier = new MenuItem("Frintier");

            bahraman.setOnAction(actionEvent -> {
                menuButtonBrand.setText("bahraman");
            });
            momtaz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("momtaz");
            });
            seharkhiz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("seharkhiz");
            });
            frintier.setOnAction(actionEvent -> {
                menuButtonBrand.setText("frintier");
            });

            menuButtonBrand.getItems().addAll(bahraman, momtaz, seharkhiz, frintier);
        });
        pea.setOnAction(event -> {

            menuInfo(2, "pea", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem aaliChin = new MenuItem("Aali Chin");
            MenuItem delpazir = new MenuItem("Delpazir");
            MenuItem shahsavand = new MenuItem("Shahsavand");
            MenuItem ziyad = new MenuItem("Ziyad");

            aaliChin.setOnAction(actionEvent -> {
                menuButtonBrand.setText("aali chin");
            });
            delpazir.setOnAction(actionEvent -> {
                menuButtonBrand.setText("delpazir");
            });
            shahsavand.setOnAction(actionEvent -> {
                menuButtonBrand.setText("shahsavand");
            });
            ziyad.setOnAction(actionEvent -> {
                menuButtonBrand.setText("ziyad");
            });

            menuButtonBrand.getItems().addAll(aaliChin, delpazir, shahsavand, ziyad);
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
            MenuItem randallFamily = new MenuItem("Randall Family");

            bijan.setOnAction(ev -> {
                menuButtonBrand.setText("bijan");
            });
            mixLand.setOnAction(ev -> {
                menuButtonBrand.setText("mix land");
            });
            yekOyek.setOnAction(ev -> {
                menuButtonBrand.setText("yek & yek");
            });
            randallFamily.setOnAction(actionEvent -> {
                menuButtonBrand.setText("randall family");
            });

            menuButtonBrand.getItems().addAll(bijan, mixLand, yekOyek, randallFamily);
        });
        honey.setOnAction(event -> {

            menuInfo(2, "honey", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem rayehe = new MenuItem("Rayehe");
            MenuItem segmen = new MenuItem("Segmen");
            MenuItem shahsavand = new MenuItem("Shahsavand");
            MenuItem js = new MenuItem("JS");

            rayehe.setOnAction(ev -> {
                menuButtonBrand.setText("rayehe");
            });
            segmen.setOnAction(ev -> {
                menuButtonBrand.setText("segmen");
            });
            shahsavand.setOnAction(ev -> {
                menuButtonBrand.setText("shahsavand");
            });
            js.setOnAction(actionEvent -> {
                menuButtonBrand.setText("js");
            });

            menuButtonBrand.getItems().addAll(rayehe, segmen, shahsavand, js);
        });
        halva.setOnAction(event -> {

            menuInfo(2, "halva", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem oghab = new MenuItem("Oghab");
            MenuItem tahini = new MenuItem("Tahini");
            MenuItem taksun = new MenuItem("Taksun");
            MenuItem koska = new MenuItem("Koska");

            oghab.setOnAction(ev -> {
                menuButtonBrand.setText("oghab");
            });
            tahini.setOnAction(ev -> {
                menuButtonBrand.setText("tahini");
            });
            taksun.setOnAction(ev -> {
                menuButtonBrand.setText("taksun");
            });
            koska.setOnAction(actionEvent -> {
                menuButtonBrand.setText("koska");
            });

            menuButtonBrand.getItems().addAll(oghab, tahini, taksun, koska);
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
            MenuItem principe = new MenuItem("Principe");

            kampooreh.setOnAction(ev -> {
                menuButtonBrand.setText("kampooreh");
            });
            robat.setOnAction(ev -> {
                menuButtonBrand.setText("robat");
            });
            shamSham.setOnAction(ev -> {
                menuButtonBrand.setText("sham sham");
            });
            principe.setOnAction(actionEvent -> {
                menuButtonBrand.setText("principe");
            });

            menuButtonBrand.getItems().addAll(kampooreh, robat, shamSham, principe);
        });
        lambMeet.setOnAction(event -> {

            menuInfo(2, "lambMeet", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem ariaBahar = new MenuItem("Aria Bahar");
            MenuItem mahya = new MenuItem("Mahya");
            MenuItem puya = new MenuItem("Puya");
            MenuItem freeRange = new MenuItem("Free Range");

            ariaBahar.setOnAction(ev -> {
                menuButtonBrand.setText("aria bahar");
            });
            mahya.setOnAction(ev -> {
                menuButtonBrand.setText("mahya");
            });
            puya.setOnAction(ev -> {
                menuButtonBrand.setText("puya");
            });
            freeRange.setOnAction(actionEvent -> {
                menuButtonBrand.setText("free range");
            });

            menuButtonBrand.getItems().addAll(ariaBahar, mahya, puya, freeRange);
        });
        chicken.setOnAction(event -> {

            menuInfo(2, "chicken", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem mahya = new MenuItem("Mahya");
            MenuItem puya = new MenuItem("Puya");
            MenuItem telavang = new MenuItem("Telavang");
            MenuItem marys = new MenuItem("Mary's");

            mahya.setOnAction(ev -> {
                menuButtonBrand.setText("mahya");
            });
            puya.setOnAction(ev -> {
                menuButtonBrand.setText("puya");
            });
            telavang.setOnAction(ev -> {
                menuButtonBrand.setText("telavang");
            });
            marys.setOnAction(actionEvent -> {
                menuButtonBrand.setText("mary's");
            });

            menuButtonBrand.getItems().addAll(mahya, puya, telavang, marys);
        });
        egg.setOnAction(event -> {

            menuInfo(2, "egg", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem morghdaran = new MenuItem("Morghdaran");
            MenuItem porotana = new MenuItem("Porotana");
            MenuItem telavang = new MenuItem("Telavang");
            MenuItem organicValley = new MenuItem("Organic Valley");

            morghdaran.setOnAction(ev -> {
                menuButtonBrand.setText("morghdaran");
            });
            porotana.setOnAction(ev -> {
                menuButtonBrand.setText("porotana");
            });
            telavang.setOnAction(ev -> {
                menuButtonBrand.setText("telavang");
            });
            organicValley.setOnAction(actionEvent -> {
                menuButtonBrand.setText("organic valley");
            });

            menuButtonBrand.getItems().addAll(morghdaran, porotana, telavang, organicValley);
        });
        beef.setOnAction(event -> {

            menuInfo(2, "beef", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem khoram = new MenuItem("Khoram");
            MenuItem mahya = new MenuItem("Mahya");
            MenuItem puya = new MenuItem("Puya");
            MenuItem fresh = new MenuItem("Fresh");

            khoram.setOnAction(ev -> {
                menuButtonBrand.setText("khoram");
            });
            mahya.setOnAction(ev -> {
                menuButtonBrand.setText("mahya");
            });
            puya.setOnAction(ev -> {
                menuButtonBrand.setText("puya");
            });
            fresh.setOnAction(actionEvent -> {
                menuButtonBrand.setText("fresh");
            });

            menuButtonBrand.getItems().addAll(khoram, mahya, puya, fresh);
        });
        shrimp.setOnAction(event -> {

            menuInfo(2, "shrimp", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem marine = new MenuItem("Marine");
            MenuItem pemina = new MenuItem("Pemina");
            MenuItem tohfe = new MenuItem("Tohfe");
            MenuItem greatCatch = new MenuItem("Great Catch");

            marine.setOnAction(ev -> {
                menuButtonBrand.setText("marine");
            });
            pemina.setOnAction(ev -> {
                menuButtonBrand.setText("pemina");
            });
            tohfe.setOnAction(ev -> {
                menuButtonBrand.setText("tohfe");
            });
            greatCatch.setOnAction(actionEvent -> {
                menuButtonBrand.setText("great catch");
            });

            menuButtonBrand.getItems().addAll(marine, pemina, tohfe, greatCatch);
        });
        tuna.setOnAction(event -> {

            menuInfo(2, "tuna", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem makenzi = new MenuItem("Makenzi");
            MenuItem shilton = new MenuItem("Shilton");
            MenuItem tohfe = new MenuItem("Tohfe");
            MenuItem ayamBrand = new MenuItem("Ayam Brand");

            makenzi.setOnAction(ev -> {
                menuButtonBrand.setText("makenzi");
            });
            shilton.setOnAction(ev -> {
                menuButtonBrand.setText("shilton");
            });
            tohfe.setOnAction(ev -> {
                menuButtonBrand.setText("tohfe");
            });
            ayamBrand.setOnAction(actionEvent -> {
                menuButtonBrand.setText("ayam brand");
            });

            menuButtonBrand.getItems().addAll(makenzi, shilton, tohfe, ayamBrand);
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
            MenuItem lactaid = new MenuItem("Lactaid");

            damdaran.setOnAction(ev -> {
                menuButtonBrand.setText("damdaran");
            });
            kale.setOnAction(ev -> {
                menuButtonBrand.setText("kale");
            });
            mihan.setOnAction(ev -> {
                menuButtonBrand.setText("mihan");
            });
            lactaid.setOnAction(actionEvent -> {
                menuButtonBrand.setText("lactaid");
            });

            menuButtonBrand.getItems().addAll(damdaran, kale, mihan, lactaid);
        });
        yogurt.setOnAction(event -> {

            menuInfo(2, "yogurt", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem kale = new MenuItem("Kale");
            MenuItem mihan = new MenuItem("Mihan");
            MenuItem ramak = new MenuItem("Ramak");
            MenuItem forager = new MenuItem("Forager");

            kale.setOnAction(ev -> {
                menuButtonBrand.setText("kale");
            });
            mihan.setOnAction(ev -> {
                menuButtonBrand.setText("mihan");
            });
            ramak.setOnAction(ev -> {
                menuButtonBrand.setText("ramak");
            });
            forager.setOnAction(actionEvent -> {
                menuButtonBrand.setText("forager");
            });

            menuButtonBrand.getItems().addAll(kale, mihan, ramak, forager);
        });
        cheese.setOnAction(event -> {

            menuInfo(2, "cheese", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem kale = new MenuItem("Kale");
            MenuItem mihan = new MenuItem("Mihan");
            MenuItem ruzane = new MenuItem("Ruzane");
            MenuItem theLaughingCow = new MenuItem("The Laughing Cow");

            kale.setOnAction(ev -> {
                menuButtonBrand.setText("kale");
            });
            mihan.setOnAction(ev -> {
                menuButtonBrand.setText("mihan");
            });
            ruzane.setOnAction(ev -> {
                menuButtonBrand.setText("ruzane");
            });
            theLaughingCow.setOnAction(actionEvent -> {
                menuButtonBrand.setText("the laughing cow");
            });

            menuButtonBrand.getItems().addAll(kale, mihan, ruzane, theLaughingCow);
        });
        cream.setOnAction(event -> {

            menuInfo(2, "cream", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem kale = new MenuItem("Kale");
            MenuItem mihan = new MenuItem("Mihan");
            MenuItem pegah = new MenuItem("Pegah");
            MenuItem karoun = new MenuItem("Karoun");

            kale.setOnAction(ev -> {
                menuButtonBrand.setText("kale");
            });
            mihan.setOnAction(ev -> {
                menuButtonBrand.setText("mihan");
            });
            pegah.setOnAction(ev -> {
                menuButtonBrand.setText("pegah");
            });
            karoun.setOnAction(actionEvent -> {
                menuButtonBrand.setText("karoun");
            });

            menuButtonBrand.getItems().addAll(kale, mihan, pegah, karoun);
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
            MenuItem yorkshire = new MenuItem("Yorkshire");

            debsh.setOnAction(ev -> {
                menuButtonBrand.setText("debsh");
            });
            golestan.setOnAction(ev -> {
                menuButtonBrand.setText("golestan");
            });
            shahrzad.setOnAction(ev -> {
                menuButtonBrand.setText("shahrzad");
            });
            yorkshire.setOnAction(actionEvent -> {
                menuButtonBrand.setText("yorkshire");
            });

            menuButtonBrand.getItems().addAll(debsh, golestan, shahrzad, yorkshire);
        });
        herbalTea.setOnAction(event -> {

            menuInfo(2, "herbalTea", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem golestan = new MenuItem("Golestan");
            MenuItem seharKhiz = new MenuItem("Sehar Khiz");
            MenuItem shahsavand = new MenuItem("Shahsavand");
            MenuItem yogi = new MenuItem("Yogi");

            golestan.setOnAction(ev -> {
                menuButtonBrand.setText("golestan");
            });
            shahsavand.setOnAction(ev -> {
                menuButtonBrand.setText("shahsavand");
            });
            shahsavand.setOnAction(ev -> {
                menuButtonBrand.setText("shahsavand");
            });
            yogi.setOnAction(actionEvent -> {
                menuButtonBrand.setText("yogi");
            });

            menuButtonBrand.getItems().addAll(golestan, seharKhiz, shahsavand, yogi);
        });
        coffee.setOnAction(event -> {

            menuInfo(2, "coffee", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem aliCafe = new MenuItem("Ali Cafe");
            MenuItem goodDay = new MenuItem("Good Day");
            MenuItem nescafe = new MenuItem("Nescafe");
            MenuItem starbucks = new MenuItem("Starbucks");

            aliCafe.setOnAction(ev -> {
                menuButtonBrand.setText("ali cafe");
            });
            goodDay.setOnAction(ev -> {
                menuButtonBrand.setText("good day");
            });
            nescafe.setOnAction(ev -> {
                menuButtonBrand.setText("nescafe");
            });
            starbucks.setOnAction(actionEvent -> {
                menuButtonBrand.setText("starbucks");
            });

            menuButtonBrand.getItems().addAll(aliCafe, goodDay, nescafe, starbucks);
        });
        water.setOnAction(event -> {

            menuInfo(2, "water", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem damavand = new MenuItem("Damavand");
            MenuItem miva = new MenuItem("Miva");
            MenuItem oxab = new MenuItem("Oxab");
            MenuItem smartWater = new MenuItem("Smart Water");

            damavand.setOnAction(ev -> {
                menuButtonBrand.setText("damavand");
            });
            miva.setOnAction(ev -> {
                menuButtonBrand.setText("miva");
            });
            oxab.setOnAction(ev -> {
                menuButtonBrand.setText("oxab");
            });
            smartWater.setOnAction(actionEvent -> {
                menuButtonBrand.setText("smart water");
            });

            menuButtonBrand.getItems().addAll(damavand, miva, oxab, smartWater);
        });
        beer.setOnAction(event -> {

            menuInfo(2, "beer", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem aalis = new MenuItem("Aalis");
            MenuItem heyDay = new MenuItem("Hey Day");
            MenuItem jojo = new MenuItem("Jojo");
            MenuItem heineken = new MenuItem("Heineken");

            aalis.setOnAction(ev -> {
                menuButtonBrand.setText("aalis");
            });
            heyDay.setOnAction(ev -> {
                menuButtonBrand.setText("hey day");
            });
            jojo.setOnAction(ev -> {
                menuButtonBrand.setText("jojo");
            });
            heineken.setOnAction(actionEvent -> {
                menuButtonBrand.setText("heineken");
            });

            menuButtonBrand.getItems().addAll(aalis, heyDay, jojo, heineken);
        });
        soda.setOnAction(event -> {

            menuInfo(2, "soda", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem cocaCola = new MenuItem("Coca Cola");
            MenuItem fantaLemon = new MenuItem("Fanta Lemon");
            MenuItem fantaOrange = new MenuItem("Fanta Orange");
            MenuItem drPepper = new MenuItem("Dr Pepper");

            cocaCola.setOnAction(ev -> {
                menuButtonBrand.setText("coca cola");
            });
            fantaLemon.setOnAction(ev -> {
                menuButtonBrand.setText("fanta lemon");
            });
            fantaOrange.setOnAction(ev -> {
                menuButtonBrand.setText("fanta orange");
            });
            drPepper.setOnAction(actionEvent -> {
                menuButtonBrand.setText("dr pepper");
            });

            menuButtonBrand.getItems().addAll(cocaCola, fantaLemon, fantaOrange, drPepper);
        });
        juice.setOnAction(event -> {

            menuInfo(2, "juice", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem mihan = new MenuItem("Mihan");
            MenuItem sanIch = new MenuItem("San Ich");
            MenuItem takDaneh = new MenuItem("Tak Daneh");
            MenuItem simplyOrange = new MenuItem("Simply Orange");

            mihan.setOnAction(ev -> {
                menuButtonBrand.setText("mihan");
            });
            sanIch.setOnAction(ev -> {
                menuButtonBrand.setText("san ich");
            });
            takDaneh.setOnAction(ev -> {
                menuButtonBrand.setText("tak daneh");
            });
            simplyOrange.setOnAction(actionEvent -> {
                menuButtonBrand.setText("simply orange");
            });

            menuButtonBrand.getItems().addAll(mihan, sanIch, takDaneh, simplyOrange);
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
            MenuItem snickers = new MenuItem("Snickers");

            hiss.setOnAction(ev -> {
                menuButtonBrand.setText("hiss");
            });
            rangarang.setOnAction(ev -> {
                menuButtonBrand.setText("rangarang");
            });
            takTak.setOnAction(ev -> {
                menuButtonBrand.setText("tak tak");
            });
            snickers.setOnAction(actionEvent -> {
                menuButtonBrand.setText("snickers");
            });

            menuButtonBrand.getItems().addAll(hiss, rangarang, takTak, snickers);
        });
        biscuit.setOnAction(event -> {

            menuInfo(2, "biscuit", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem hiBye = new MenuItem("Hi Bye");
            MenuItem petitBeurre = new MenuItem("petit Beurre");
            MenuItem sagheTalai = new MenuItem("Saghe Talai");
            MenuItem oreo = new MenuItem("Oreo");

            hiBye.setOnAction(ev -> {
                menuButtonBrand.setText("hi bye");
            });
            petitBeurre.setOnAction(ev -> {
                menuButtonBrand.setText("petit beurre");
            });
            sagheTalai.setOnAction(ev -> {
                menuButtonBrand.setText("saghe talai");
            });
            oreo.setOnAction(actionEvent -> {
                menuButtonBrand.setText("oreo");
            });

            menuButtonBrand.getItems().addAll(hiBye, petitBeurre, sagheTalai, oreo);
        });
        nuts.setOnAction(event -> {

            menuInfo(2, "nuts", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem mani = new MenuItem("Mani");
            MenuItem mazMaz = new MenuItem("Maz Maz");
            MenuItem sanjaghak = new MenuItem("Sanjaghak");
            MenuItem planters = new MenuItem("Planters");


            mani.setOnAction(ev -> {
                menuButtonBrand.setText("mani");
            });
            mazMaz.setOnAction(ev -> {
                menuButtonBrand.setText("maz maz");
            });
            sanjaghak.setOnAction(ev -> {
                menuButtonBrand.setText("sanjaghak");
            });
            planters.setOnAction(actionEvent -> {
                menuButtonBrand.setText("planters");
            });

            menuButtonBrand.getItems().addAll(mani, mazMaz, sanjaghak, planters);
        });
        cake.setOnAction(event -> {

            menuInfo(2, "cake", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem cakeMake = new MenuItem("Cake Make");
            MenuItem tTop = new MenuItem("T Top");
            MenuItem tiny = new MenuItem("Tiny");
            MenuItem mrKipling = new MenuItem("Mr Kipling");

            cakeMake.setOnAction(ev -> {
                menuButtonBrand.setText("cake make");
            });
            tTop.setOnAction(ev -> {
                menuButtonBrand.setText("t top");
            });
            tiny.setOnAction(ev -> {
                menuButtonBrand.setText("tiny");
            });
            mrKipling.setOnAction(actionEvent -> {
                menuButtonBrand.setText("mr kipling");
            });

            menuButtonBrand.getItems().addAll(cakeMake, tTop, tiny, mrKipling);
        });
        chips.setOnAction(event -> {

            menuInfo(2, "chips", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem chakelz = new MenuItem("Chakelz");
            MenuItem cheetoz = new MenuItem("Cheeroz");
            MenuItem delMaze = new MenuItem("Del maze");
            MenuItem lays = new MenuItem("Lay's");

            chakelz.setOnAction(ev -> {
                menuButtonBrand.setText("chakelz");
            });
            cheetoz.setOnAction(ev -> {
                menuButtonBrand.setText("cheetoz");
            });
            delMaze.setOnAction(ev -> {
                menuButtonBrand.setText("del maze");
            });
            lays.setOnAction(actionEvent -> {
                menuButtonBrand.setText("lays");
            });


            menuButtonBrand.getItems().addAll(chakelz, cheetoz, delMaze, lays);
        });
        pofak.setOnAction(event -> {

            menuInfo(2, "pofak", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem chakelz = new MenuItem("Chakelz");
            MenuItem cheetoz = new MenuItem("Cheeroz");
            MenuItem pofakNamaki = new MenuItem("Pofak Namaki");
            MenuItem cheetos = new MenuItem("Cheetos");

            chakelz.setOnAction(ev -> {
                menuButtonBrand.setText("chakelz");
            });
            cheetoz.setOnAction(ev -> {
                menuButtonBrand.setText("cheetoz");
            });
            pofakNamaki.setOnAction(ev -> {
                menuButtonBrand.setText("pofak namaki");
            });
            cheetos.setOnAction(actionEvent -> {
                menuButtonBrand.setText("cheetos");
            });

            menuButtonBrand.getItems().addAll(chakelz, cheetoz, pofakNamaki, cheetos);
        });
        chewingGum.setOnAction(event -> {

            menuInfo(2, "chewingGum", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem action = new MenuItem("Action");
            MenuItem biodent = new MenuItem("Biodent");
            MenuItem trident = new MenuItem("Trident");
            MenuItem orbit = new MenuItem("Orbit");

            action.setOnAction(ev -> {
                menuButtonBrand.setText("action");
            });
            biodent.setOnAction(ev -> {
                menuButtonBrand.setText("biodent");
            });
            trident.setOnAction(ev -> {
                menuButtonBrand.setText("trident");
            });
            orbit.setOnAction(actionEvent -> {
                menuButtonBrand.setText("orbit");
            });

            menuButtonBrand.getItems().addAll(action, biodent, trident, orbit);
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
                    productImg.getImage(),
                    Application.shop.currentSeller.getUsername()
            );

            if (!Application.shop.currentSeller.items.contains(item)) {
                Application.shop.currentSeller.items.add(item);
                Application.shop.allItems.add(item);
                Database.addProduct(item);

                productImg.setImage(item.image);
                addtxt.setText("add successfully");


                showItems("seller", Application.shop.currentSeller.items);

            } else {
                addtxt.setText("product is valid");
            }
        } else {
            addtxt.setText("fill all the blanks");
        }
    }

    @FXML
    private AnchorPane sellerMainPage;
    static int icount = 0;
    static int jcount = 0;

    public void showItems(String applicantKind, ArrayList<Item> items) {

        sellerMainPage.getChildren().clear();
        icount = 0;
        jcount = 0;


        for (int i = items.size() - 1; i >= 0; i--) {

            ImageView imageView = new ImageView(items.get(i).image);
            int finalI = i;
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {

                    Item item = items.get(finalI);
                    Application.shop.currentItem = item;

                    try {
                        FXMLLoader fxmlLoader = null;

                   /* if (Application.shop.currentSeller == null)
                        fxmlLoader = new FXMLLoader(Application.class.getResource("productCustomer.fxml"));
                    if (Application.shop.currentCustomer == null)
                        fxmlLoader = new FXMLLoader(Application.class.getResource("productSeller.fxml"));*/
                        fxmlLoader = new FXMLLoader(Application.class.getResource("product.fxml"));//have to remove

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

            ImageView scoreEmoji = new ImageView(items.get(i).scoreEmoji);
            scoreEmoji.setEffect(new DropShadow());
            scoreEmoji.setFitWidth(30);
            scoreEmoji.setFitHeight(30);
            scoreEmoji.setLayoutX(5);
            scoreEmoji.setLayoutY(210);

            Label name = new Label(items.get(i).name);
            name.setFont(new Font("Arial", 20));
            name.setPrefWidth(150);
            name.setAlignment(Pos.CENTER);
            name.setLayoutX(0);
            name.setLayoutY(145);

            Label price = new Label(String.valueOf(items.get(i).price));
            price.setFont(new Font("Arial", 20));
            price.setPrefWidth(150);
            price.setAlignment(Pos.CENTER);
            price.setLayoutX(0);
            price.setLayoutY(175);


            DecimalFormat decimalFormat = new DecimalFormat("##.##");

            Label score = new Label(decimalFormat.format(items.get(i).score) + "%");
            score.setFont(new Font("Arial", 15));
            score.setLayoutX(37);
            score.setLayoutY(217);

            Button button = null;
            if (applicantKind.equals("seller")) {

                button = new Button("auction");


                /*button.setOnAction(ev -> {

                });*/

            }
            if (applicantKind.equals("customer")) {

                button = new Button("add");
                button.setFont(new Font(15));

                button.setOnAction(ev -> {
                    Item item = items.get(finalI);
                    Application.shop.currentCustomer.items.add(item);
                });
            }
            button.setEffect(new DropShadow());
            button.setPrefWidth(56);
            button.setPrefHeight(30);
            button.setLayoutX(90);
            button.setLayoutY(210);


            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setLayoutX(icount * 175 + 30);
            anchorPane.setLayoutY(jcount * 275 + 30);
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

    }

    public void showSeller() {
        showItems("seller", Application.shop.currentSeller.items);
    }

    public void showCustomer() {
        showItems("customer", Application.shop.allItems);
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
