package com.example.shopapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
import java.util.Random;


public class ControllerApplicant {

    Client client;
    @FXML
    private TextArea chatTextArea;
    @FXML
    private Label chatText;

    private DropShadow dropShadow = new DropShadow();
    private InnerShadow innerShadow = new InnerShadow();


    public ControllerApplicant() {

        dropShadow.setWidth(10);
        dropShadow.setHeight(10);
        innerShadow.setWidth(10);
        innerShadow.setHeight(10);

        if (Application.shop.currentSeller != null) {
            client = new Client();
            client.start();
        }
    }

    public void send() throws SQLException {
        String msg = chatTextArea.getText();
        chatText.setText(chatText.getText() + "\n" + Application.shop.currentSeller.getUsername() + " : " + msg);
        Application.shop.currentSeller.chat = chatText.getText();
        Database.updateSeller(Application.shop.currentSeller);
        chatTextArea.setText("");

        if (client.clientSocket != null)
            client.sendMessageToServer(Application.shop.currentSeller.getUsername() + " : " + msg);
    }

    public void receive() throws IOException {
        if (client.clientSocket != null) {
            String msg = client.getMessageFromServer();
            if (!msg.equals("")) chatText.setText(chatText.getText() + "\n" + msg);
        }
    }


    @FXML
    private Label txtFNaccount, txtLNaccount, txtPNaccount, txtUNaccount, txtPWaccount, txtEMaccount, txtwpaccount, txtWBaccount, txtsellerWB;
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
            txtWBaccount.setText(String.valueOf(Application.shop.currentCustomer.wallet));
            imgcustomer.setImage(Application.shop.currentCustomer.image);

            showDiscountCode();
        } else if (Application.shop.currentSeller != null) {
            txtFNaccount.setText(Application.shop.currentSeller.getFirstname());
            txtLNaccount.setText(Application.shop.currentSeller.getLastname());
            txtPNaccount.setText(Application.shop.currentSeller.getPhoneNumber());
            txtUNaccount.setText(Application.shop.currentSeller.getUsername());
            txtPWaccount.setText(Application.shop.currentSeller.getPassword());
            txtEMaccount.setText(Application.shop.currentSeller.getEmail());
            txtwpaccount.setText(Application.shop.currentSeller.workplace);
            txtsellerWB.setText(String.valueOf(Application.shop.currentSeller.walletbalance));
            imgseller.setImage(Application.shop.currentSeller.image);


            chatText.setText(Application.shop.currentSeller.chat);
        }

        txtincreaseamount.setOpacity(0);
        labelincreasewalleterror.setText("");
    }

    @FXML
    private AnchorPane customerAccountPage;

    public void showDiscountCode() {

        TableColumn<DiscountCode, String> discountCode = new TableColumn<>("Discount Code");
        discountCode.setCellValueFactory(new PropertyValueFactory<>("discountCode"));
        discountCode.setPrefWidth(128);

        TableColumn<DiscountCode, Double> discountAmount = new TableColumn<>("Discount Amount");
        discountAmount.setCellValueFactory(new PropertyValueFactory<>("discountAmount"));
        discountAmount.setPrefWidth(170);


        TableView<DiscountCode> discountTable = new TableView<>();
        discountTable.getColumns().addAll(discountCode, discountAmount);

        ObservableList<DiscountCode> dis = FXCollections.observableArrayList(
                new DiscountCode(100_000),
                new DiscountCode(70_000),
                new DiscountCode(200_000)
        );
        discountTable.setItems(dis);

        discountTable.setLayoutX(1160);
        discountTable.setLayoutY(470);
        discountTable.setPrefWidth(300);
        discountTable.setPrefHeight(200);
        discountTable.setEffect(new InnerShadow());


        TableColumn<Item, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(120);

        TableColumn<Item, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brandColumn.setPrefWidth(120);

        TableColumn<Item, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(100);

        TableColumn<Item, String> sizeColumn = new TableColumn<>("Size");
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        sizeColumn.setPrefWidth(58);


        TableView<Item> purchaseTable = new TableView<>();
        purchaseTable.setItems(FXCollections.observableArrayList(Application.shop.currentCustomer.purchase));
        purchaseTable.getColumns().addAll(nameColumn, brandColumn, priceColumn, sizeColumn);

        purchaseTable.setLayoutX(705);
        purchaseTable.setLayoutY(100);
        purchaseTable.setPrefWidth(400);
        purchaseTable.setPrefHeight(570);
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setWidth(10);
        innerShadow.setHeight(10);
        purchaseTable.setEffect(innerShadow);


        customerAccountPage.getChildren().addAll(discountTable, purchaseTable);
    }


    public void changeScene(ActionEvent e, String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxml));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Online Shop Application");
        stage.getIcons().add(new Image("shop.png"));
        stage.setResizable(false);
        stage.setOnCloseRequest(ev -> {
            if (client != null) client.closeClientStreams();
            System.exit(0);
        });
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void changeToLoginScene(ActionEvent e) throws IOException {
        removeFilters();
        if (client != null) client.closeClientStreams();
        changeScene(e, "Login.fxml");
    }


    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private TextField txtincreaseamount;
    @FXML
    private Label labelincreasewalleterror;

    public void changeToBankPortalScene(ActionEvent e) throws IOException, NumberFormatException {

        if (Application.shop.currentCustomer != null) Application.shop.pageURL = "customer.fxml";
        if (Application.shop.currentSeller != null) Application.shop.pageURL = "seller.fxml";

        txtincreaseamount.setOpacity(1.0);

        if (txtincreaseamount.getText() == "") {
            labelincreasewalleterror.setText("please enter your desired amount");
        } else if (isNumeric(txtincreaseamount.getText())) {
            Application.shop.currentCustomer.increaseAmount = Long.valueOf(txtincreaseamount.getText());
            changeScene(e, "bankportal.fxml");
        } else if (!isNumeric(txtincreaseamount.getText()) && txtincreaseamount.getText() != "")
            labelincreasewalleterror.setText("please enter a valid amount and\nmore than 0");
    }

    public void changeToCartScene(MouseEvent e) throws IOException {

        Application.shop.pageURL = "customer.fxml";

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


    String kind;

    public void filterGrocery() {
        grocery(filterButtonKind, filterButtonMinorKind, filterButtonBrand);
        kind = "grocery";
    }

    public void filterBreakfast() {
        breakfast(filterButtonKind, filterButtonMinorKind, filterButtonBrand);
        kind = "breakfast";
    }

    public void filterProtein() {
        protein(filterButtonKind, filterButtonMinorKind, filterButtonBrand);
        kind = "protein";
    }

    public void filterDairy() {
        dairy(filterButtonKind, filterButtonMinorKind, filterButtonBrand);
        kind = "dairy";
    }

    public void filterDrinks() {
        drinks(filterButtonKind, filterButtonMinorKind, filterButtonBrand);
        kind = "drink";
    }

    public void filterSnacks() {
        snacks(filterButtonKind, filterButtonMinorKind, filterButtonBrand);
        kind = "snack";
    }

    public void filterPurchase() {

        if (Application.shop.pageURL.equals("customer.fxml")) {
            Application.shop.tempItems.clear();
            Application.shop.tempItems.addAll(Application.shop.currentCustomer.purchase);
            showItems("customer", Application.shop.tempItems);
        }
    }

    public void applyFilters() {

        if (kind != null) {

            if (Application.shop.pageURL.equals("customer.fxml")) {
                Shop.SortByKind(Application.shop.allItems, Application.shop.tempItems, kind);
                showItems("customer", Application.shop.tempItems);
            }
            if (Application.shop.pageURL.equals("seller.fxml")) {
                Shop.SortByKind(Application.shop.currentSeller.allItems, Application.shop.currentSeller.tempItems, kind);
                showItems("seller", Application.shop.currentSeller.tempItems);
            }

            kind = null;
        }

    }

    private void filterScore(int sort) {

        if (Application.shop.pageURL.equals("customer.fxml")) {
            Shop.SortByScore(Application.shop.tempItems, sort);
            showItems("customer", Application.shop.tempItems);
        }
        if (Application.shop.pageURL.equals("seller.fxml")) {
            Shop.SortByScore(Application.shop.currentSeller.tempItems, sort);
            showItems("seller", Application.shop.currentSeller.tempItems);
        }
    }

    private void filterPrice(int sort) {

        if (Application.shop.pageURL.equals("customer.fxml")) {
            Shop.SortByPrice(Application.shop.tempItems, sort);
            showItems("customer", Application.shop.tempItems);
        }
        if (Application.shop.pageURL.equals("seller.fxml")) {
            Shop.SortByPrice(Application.shop.currentSeller.tempItems, sort);
            showItems("seller", Application.shop.currentSeller.tempItems);
        }
    }

    public void scoreHighToLow() {
        filterScore(1);
    }

    public void scoreLowToHigh() {
        filterScore(-1);
    }

    public void priceHighToLow() {
        filterPrice(1);
    }

    public void priceLowToHigh() {
        filterPrice(-1);
    }

    public void filterBrand() {//set the filter brand menuButton

        filterButtonBrand.getItems().clear();

        //bread
        createMenuItem("mazrae");
        createMenuItem("new york bakery");

        //rice
        createMenuItem("behrooz");
        createMenuItem("golestan");
        createMenuItem("tabiat");
        createMenuItem("uncle ben's");

        //oil
        createMenuItem("ladan");
        createMenuItem("oila");
        createMenuItem("tabiat");
        createMenuItem("louAna");

        //broken sugar
        createMenuItem("azughe");
        createMenuItem("ferdous");
        createMenuItem("mash mash");
        createMenuItem("la perruche");

        //sugar
        createMenuItem("emruz");
        createMenuItem("golestan");
        createMenuItem("shahdane");
        createMenuItem("chelsea");

        //sauce
        createMenuItem("behrooz");
        createMenuItem("delvese");
        createMenuItem("mahram");
        createMenuItem("heinz");

        //rob
        createMenuItem("chin chin");
        createMenuItem("tabarok");
        createMenuItem("tabiat");
        createMenuItem("hunts");

        //pickle
        createMenuItem("behrooz");
        createMenuItem("mahram");
        createMenuItem("yek o yek");
        createMenuItem("vlasic");

        //lemon juice
        createMenuItem("behrooz");
        createMenuItem("mahram");
        createMenuItem("yek o yek");
        createMenuItem("lakewood");

        //pasta
        createMenuItem("de cecco");
        createMenuItem("tak makaron");
        createMenuItem("zar makaron");
        createMenuItem("goya");

        //saffron
        createMenuItem("bahraman");
        createMenuItem("momtaz");
        createMenuItem("seharkhiz");
        createMenuItem("frintier");

        //pea
        createMenuItem("aali chin");
        createMenuItem("delpazir");
        createMenuItem("shahsavand");
        createMenuItem("ziyad");

        //jam
        createMenuItem("bijan");
        createMenuItem("mix land");
        createMenuItem("yek o yek");
        createMenuItem("randall family");

        //honey
        createMenuItem("rayehe");
        createMenuItem("segmen");
        createMenuItem("shahsavand");
        createMenuItem("js");

        //halva
        createMenuItem("oghab");
        createMenuItem("tahini");
        createMenuItem("taksun");
        createMenuItem("koska");

        //bologna
        createMenuItem("kampooreh");
        createMenuItem("robat");
        createMenuItem("sham sham");
        createMenuItem("principe");

        //lamb meet
        createMenuItem("aria bahar");
        createMenuItem("mahya");
        createMenuItem("poya");
        createMenuItem("free range");

        //chicken
        createMenuItem("mahya");
        createMenuItem("puya");
        createMenuItem("telavang");
        createMenuItem("mary's");

        //egg
        createMenuItem("morghdaran");
        createMenuItem("porotana");
        createMenuItem("telavang");
        createMenuItem("organic valley");

        //beef
        createMenuItem("khoram");
        createMenuItem("mahya");
        createMenuItem("puya");
        createMenuItem("fresh");

        //shrimp
        createMenuItem("marine");
        createMenuItem("pemina");
        createMenuItem("tohfe");
        createMenuItem("great catch");

        //tuna
        createMenuItem("makenzi");
        createMenuItem("shilton");
        createMenuItem("tohfe");
        createMenuItem("ayam brand");

        //milk
        createMenuItem("damdaran");
        createMenuItem("kale");
        createMenuItem("mihan");
        createMenuItem("lactaid");

        //yogurt
        createMenuItem("kale");
        createMenuItem("mihan");
        createMenuItem("ramak");
        createMenuItem("forager");

        //cheese
        createMenuItem("kale");
        createMenuItem("mihan");
        createMenuItem("ruzane");
        createMenuItem("the laughing cow");

        //cream
        createMenuItem("kale");
        createMenuItem("mihan");
        createMenuItem("pegah");
        createMenuItem("karoun");

        //tea
        createMenuItem("debsh");
        createMenuItem("golestan");
        createMenuItem("shahrzad");
        createMenuItem("yorkshire");

        //herbal tea
        createMenuItem("golestan");
        createMenuItem("sahar khiz");
        createMenuItem("shahsavand");
        createMenuItem("yogi");

        //coffee
        createMenuItem("ali cafe");
        createMenuItem("good day");
        createMenuItem("nescafe");
        createMenuItem("starbucks");

        //water
        createMenuItem("damavand");
        createMenuItem("miva");
        createMenuItem("oxab");
        createMenuItem("smart water");

        //beer
        createMenuItem("aalis");
        createMenuItem("hey day");
        createMenuItem("jojo");
        createMenuItem("heineken");

        //soda
        createMenuItem("coca cola");
        createMenuItem("fanta orange");
        createMenuItem("fanta lemon");
        createMenuItem("dr pepper");

        //juice
        createMenuItem("mihan");
        createMenuItem("san ich");
        createMenuItem("tak daneh");
        createMenuItem("simply orange");

        //chocolate
        createMenuItem("hiss");
        createMenuItem("rangarang");
        createMenuItem("tak tak");
        createMenuItem("snickers");

        //biscuit
        createMenuItem("hi bye");
        createMenuItem("petit beurre");
        createMenuItem("saghe talai");
        createMenuItem("oreo");

        //nuts
        createMenuItem("mani");
        createMenuItem("maz maz");
        createMenuItem("sanjaghak");
        createMenuItem("planters");

        //cake
        createMenuItem("cake make");
        createMenuItem("t top");
        createMenuItem("tiny");
        createMenuItem("mr kipling");

        //chips
        createMenuItem("chakelz");
        createMenuItem("cheetoz");
        createMenuItem("del maze");
        createMenuItem("lays");

        //pofak
        createMenuItem("chakelz");
        createMenuItem("cheetoz");
        createMenuItem("pofak namaki");
        createMenuItem("cheetos");

        //chewing gum
        createMenuItem("action");
        createMenuItem("biodent");
        createMenuItem("trident");
        createMenuItem("orbit");
    }

    private void createMenuItem(String text) {

        MenuItem menuItem = new MenuItem(text);

        menuItem.setOnAction(ev -> {
            filterButtonBrand.setText(text);
            if (Application.shop.currentCustomer != null)
                Shop.sortByBrand(Application.shop.allItems, Application.shop.tempItems, menuItem.getText());
            if (Application.shop.currentSeller != null)
                Shop.sortByBrand(Application.shop.currentSeller.allItems, Application.shop.currentSeller.tempItems, menuItem.getText());
        });

        filterButtonBrand.getItems().add(menuItem);
    }

    @FXML
    private TextField searchBoxText;

    public void filterName() {

        searchBoxText.setOnKeyPressed(ev -> {
            if (ev.getCode() == KeyCode.ENTER) {

                if (Application.shop.pageURL.equals("customer.fxml")) {
                    Shop.sortByName(Application.shop.allItems, Application.shop.tempItems, searchBoxText.getText());
                    showItems("customer", Application.shop.tempItems);
                }
                if (Application.shop.pageURL.equals("seller.fxml")) {
                    Shop.sortByName(Application.shop.currentSeller.allItems, Application.shop.currentSeller.tempItems, searchBoxText.getText());
                    showItems("seller", Application.shop.currentSeller.tempItems);
                }
            }
        });
    }


    public void removeFilters() {
        if (Application.shop.pageURL.equals("customer.fxml")) {
            Application.shop.tempItems.clear();
            Shop.SortByDate(Application.shop.allItems, Application.shop.tempItems);
            showItems("customer", Application.shop.tempItems);
        }
        if (Application.shop.pageURL.equals("seller.fxml")) {
            Application.shop.currentSeller.tempItems.clear();
            Shop.SortByDate(Application.shop.currentSeller.allItems, Application.shop.currentSeller.tempItems);
            showItems("seller", Application.shop.currentSeller.tempItems);
        }

        filterButtonKind.setText("Kind");
        filterButtonMinorKind.setText("Minor Kind");
        filterButtonBrand.setText("Brand");
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

            kind = "bread";

            menuInfo(2, "bread", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem mazrae = new MenuItem("mazrae");
            MenuItem newYorkBakery = new MenuItem("new york bakery");

            mazrae.setOnAction(ev -> {
                menuButtonBrand.setText("mazrae");
            });
            newYorkBakery.setOnAction(actionEvent -> {
                menuButtonBrand.setText("new york bakery");
            });

            menuButtonBrand.getItems().addAll(mazrae, newYorkBakery);
        });
        rice.setOnAction(event -> {

            kind = "rice";

            menuInfo(2, "rice", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem behrooz = new MenuItem("behrooz");
            MenuItem golestan = new MenuItem("golestan");
            MenuItem tabiat = new MenuItem("tabiat");
            MenuItem uncleBens = new MenuItem("uncle ben's");

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

            kind = "oil";

            menuInfo(2, "oil", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem ladan = new MenuItem("ladan");
            MenuItem oila = new MenuItem("oila");
            MenuItem tabiat = new MenuItem("tabiat");
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

            kind = "broken sugar";

            menuInfo(2, "broken sugar", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem azughe = new MenuItem("azughe");
            MenuItem ferdous = new MenuItem("ferdous");
            MenuItem mashMash = new MenuItem("mash mash");
            MenuItem laPerruche = new MenuItem("la perruche");

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

            kind = "sugar";

            menuInfo(2, "sugar", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem emruz = new MenuItem("emruz");
            MenuItem golestan = new MenuItem("golestan");
            MenuItem shahdane = new MenuItem("shahdane");
            MenuItem chelsea = new MenuItem("chelsea");

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

            kind = "sauce";

            menuInfo(2, "sauce", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem behrooz = new MenuItem("behrooz");
            MenuItem delvese = new MenuItem("delvese");
            MenuItem mahram = new MenuItem("mahram");
            MenuItem heinz = new MenuItem("heinz");

            behrooz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("behrooz");
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

            menuButtonBrand.getItems().addAll(behrooz, delvese, mahram, heinz);
        });
        rob.setOnAction(event -> {

            kind = "rob";

            menuInfo(2, "rob", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem chinChin = new MenuItem("chin chin");
            MenuItem tabarok = new MenuItem("tabarok");
            MenuItem tabiat = new MenuItem("tabiat");
            MenuItem hunts = new MenuItem("hunts");

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

            kind = "pickle";

            menuInfo(2, "pickle", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem behrooz = new MenuItem("behrooz");
            MenuItem mahram = new MenuItem("mahram");
            MenuItem yekOyek = new MenuItem("yek o yek");
            MenuItem vlasic = new MenuItem("vlasic");

            behrooz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("behruz");
            });
            mahram.setOnAction(actionEvent -> {
                menuButtonBrand.setText("mahram");
            });
            yekOyek.setOnAction(actionEvent -> {
                menuButtonBrand.setText("yek o yek");
            });
            vlasic.setOnAction(actionEvent -> {
                menuButtonBrand.setText("vlasic");
            });

            menuButtonBrand.getItems().addAll(behrooz, mahram, yekOyek, vlasic);
        });
        lemonJuice.setOnAction(event -> {

            kind = "lemon juice";

            menuInfo(2, "lemon juice", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem behruz = new MenuItem("behruz");
            MenuItem mahram = new MenuItem("mahram");
            MenuItem yekOyek = new MenuItem("yek o yek");
            MenuItem lakewood = new MenuItem("lakewood");

            behruz.setOnAction(actionEvent -> {
                menuButtonBrand.setText("behrooz");
            });
            mahram.setOnAction(actionEvent -> {
                menuButtonBrand.setText("mahram");
            });
            yekOyek.setOnAction(actionEvent -> {
                menuButtonBrand.setText("yek o yek");
            });
            lakewood.setOnAction(actionEvent -> {
                menuButtonBrand.setText("lakewood");
            });

            menuButtonBrand.getItems().addAll(behruz, mahram, yekOyek, lakewood);
        });
        pasta.setOnAction(event -> {

            kind = "pasta";

            menuInfo(2, "pasta", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem deCecco = new MenuItem("de cecco");
            MenuItem tak = new MenuItem("tak makaron");
            MenuItem zar = new MenuItem("zar makaron");
            MenuItem goya = new MenuItem("goya");

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

            kind = "saffron";

            menuInfo(2, "saffron", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem bahraman = new MenuItem("bahraman");
            MenuItem momtaz = new MenuItem("momtaz");
            MenuItem seharkhiz = new MenuItem("seharkhiz");
            MenuItem frintier = new MenuItem("frintier");

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

            kind = "pea";

            menuInfo(2, "pea", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem aaliChin = new MenuItem("aali Chin");
            MenuItem delpazir = new MenuItem("delpazir");
            MenuItem shahsavand = new MenuItem("shahsavand");
            MenuItem ziyad = new MenuItem("ziyad");

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

        menuInfo(1, "breakfast", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

        MenuItem jam = new MenuItem("jam");
        MenuItem honey = new MenuItem("honey");
        MenuItem halva = new MenuItem("halva");

        jam.setOnAction(event -> {

            kind = "jam";

            menuInfo(2, "jam", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem bijan = new MenuItem("bijan");
            MenuItem mixLand = new MenuItem("mix land");
            MenuItem yekOyek = new MenuItem("yek o yek");
            MenuItem randallFamily = new MenuItem("randall family");

            bijan.setOnAction(ev -> {
                menuButtonBrand.setText("bijan");
            });
            mixLand.setOnAction(ev -> {
                menuButtonBrand.setText("mix land");
            });
            yekOyek.setOnAction(ev -> {
                menuButtonBrand.setText("yek o yek");
            });
            randallFamily.setOnAction(actionEvent -> {
                menuButtonBrand.setText("randall family");
            });

            menuButtonBrand.getItems().addAll(bijan, mixLand, yekOyek, randallFamily);
        });
        honey.setOnAction(event -> {

            kind = "honey";

            menuInfo(2, "honey", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem rayehe = new MenuItem("rayehe");
            MenuItem segmen = new MenuItem("segmen");
            MenuItem shahsavand = new MenuItem("shahsavand");
            MenuItem js = new MenuItem("js");

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

            kind = "halva";

            menuInfo(2, "halva", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem oghab = new MenuItem("oghab");
            MenuItem tahini = new MenuItem("tahini");
            MenuItem taksun = new MenuItem("taksun");
            MenuItem koska = new MenuItem("koska");

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

        menuInfo(1, "protein", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

        MenuItem bologna = new MenuItem("bologna");
        MenuItem lambMeet = new MenuItem("lamb meet");
        MenuItem chicken = new MenuItem("chicken");
        MenuItem egg = new MenuItem("egg");
        MenuItem beef = new MenuItem("beef");
        MenuItem shrimp = new MenuItem("shrimp");
        MenuItem tuna = new MenuItem("tuna");

        bologna.setOnAction(event -> {

            kind = "bologna";

            menuInfo(2, "bologna", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem kampooreh = new MenuItem("kampooreh");
            MenuItem robat = new MenuItem("robat");
            MenuItem shamSham = new MenuItem("sham sham");
            MenuItem principe = new MenuItem("principe");

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

            kind = "lamb meet";

            menuInfo(2, "lambMeet", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem ariaBahar = new MenuItem("aria bahar");
            MenuItem mahya = new MenuItem("mahya");
            MenuItem puya = new MenuItem("puya");
            MenuItem freeRange = new MenuItem("free range");

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

            kind = "chicken";

            menuInfo(2, "chicken", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem mahya = new MenuItem("mahya");
            MenuItem puya = new MenuItem("puya");
            MenuItem telavang = new MenuItem("telavang");
            MenuItem marys = new MenuItem("mary's");

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

            kind = "egg";

            menuInfo(2, "egg", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem morghdaran = new MenuItem("morghdaran");
            MenuItem porotana = new MenuItem("porotana");
            MenuItem telavang = new MenuItem("telavang");
            MenuItem organicValley = new MenuItem("organic valley");

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

            kind = "beef";

            menuInfo(2, "beef", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem khoram = new MenuItem("khoram");
            MenuItem mahya = new MenuItem("mahya");
            MenuItem puya = new MenuItem("puya");
            MenuItem fresh = new MenuItem("fresh");

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

            kind = "shrimp";

            menuInfo(2, "shrimp", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem marine = new MenuItem("marine");
            MenuItem pemina = new MenuItem("pemina");
            MenuItem tohfe = new MenuItem("tohfe");
            MenuItem greatCatch = new MenuItem("great catch");

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

            kind = "tuna";

            menuInfo(2, "tuna", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem makenzi = new MenuItem("makenzi");
            MenuItem shilton = new MenuItem("shilton");
            MenuItem tohfe = new MenuItem("tohfe");
            MenuItem ayamBrand = new MenuItem("ayam brand");

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

            kind = "milk";

            menuInfo(2, "milk", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem damdaran = new MenuItem("damdaran");
            MenuItem kale = new MenuItem("kale");
            MenuItem mihan = new MenuItem("mihan");
            MenuItem lactaid = new MenuItem("lactaid");

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

            kind = "yogurt";

            menuInfo(2, "yogurt", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem kale = new MenuItem("kale");
            MenuItem mihan = new MenuItem("mihan");
            MenuItem ramak = new MenuItem("ramak");
            MenuItem forager = new MenuItem("forager");

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

            kind = "cheese";

            menuInfo(2, "cheese", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem kale = new MenuItem("kale");
            MenuItem mihan = new MenuItem("mihan");
            MenuItem ruzane = new MenuItem("ruzane");
            MenuItem theLaughingCow = new MenuItem("the laughing cow");

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

            kind = "cream";

            menuInfo(2, "cream", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem kale = new MenuItem("kale");
            MenuItem mihan = new MenuItem("mihan");
            MenuItem pegah = new MenuItem("pegah");
            MenuItem karoun = new MenuItem("karoun");

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

        menuInfo(1, "drink", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

        MenuItem tea = new MenuItem("tea");
        MenuItem herbalTea = new MenuItem("herbal tea");
        MenuItem coffee = new MenuItem("coffee");
        MenuItem water = new MenuItem("water");
        MenuItem beer = new MenuItem("beer");
        MenuItem soda = new MenuItem("soda");
        MenuItem juice = new MenuItem("juice");

        tea.setOnAction(event -> {

            kind = "tea";

            menuInfo(2, "tea", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem debsh = new MenuItem("debsh");
            MenuItem golestan = new MenuItem("golestan");
            MenuItem shahrzad = new MenuItem("shahrzad");
            MenuItem yorkshire = new MenuItem("yorkshire");

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

            kind = "herbal tea";

            menuInfo(2, "herbalTea", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem golestan = new MenuItem("golestan");
            MenuItem seharKhiz = new MenuItem("sehar khiz");
            MenuItem shahsavand = new MenuItem("shahsavand");
            MenuItem yogi = new MenuItem("yogi");

            golestan.setOnAction(ev -> {
                menuButtonBrand.setText("golestan");
            });
            seharKhiz.setOnAction(ev -> {
                menuButtonBrand.setText("sehar khiz");
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

            kind = "coffee";

            menuInfo(2, "coffee", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem aliCafe = new MenuItem("ali cafe");
            MenuItem goodDay = new MenuItem("good day");
            MenuItem nescafe = new MenuItem("nescafe");
            MenuItem starbucks = new MenuItem("starbucks");

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

            kind = "water";

            menuInfo(2, "water", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem damavand = new MenuItem("damavand");
            MenuItem miva = new MenuItem("miva");
            MenuItem oxab = new MenuItem("oxab");
            MenuItem smartWater = new MenuItem("smart water");

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

            kind = "beer";

            menuInfo(2, "beer", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem aalis = new MenuItem("aalis");
            MenuItem heyDay = new MenuItem("hey day");
            MenuItem jojo = new MenuItem("jojo");
            MenuItem heineken = new MenuItem("heineken");

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

            kind = "soda";

            menuInfo(2, "soda", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem cocaCola = new MenuItem("coca cola");
            MenuItem fantaLemon = new MenuItem("fanta lemon");
            MenuItem fantaOrange = new MenuItem("fanta orange");
            MenuItem drPepper = new MenuItem("dr pepper");

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

            kind = "juice";

            menuInfo(2, "juice", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem mihan = new MenuItem("mihan");
            MenuItem sanIch = new MenuItem("san ich");
            MenuItem takDaneh = new MenuItem("tak daneh");
            MenuItem simplyOrange = new MenuItem("simply orange");

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

        menuInfo(1, "snack", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

        MenuItem chocolate = new MenuItem("chocolate");
        MenuItem biscuit = new MenuItem("biscuit");
        MenuItem nuts = new MenuItem("nuts");
        MenuItem cake = new MenuItem("cake");
        MenuItem chips = new MenuItem("chips");
        MenuItem pofak = new MenuItem("pofak");
        MenuItem chewingGum = new MenuItem("chewing gum");

        chocolate.setOnAction(event -> {

            kind = "chocolate";

            menuInfo(2, "chocolate", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem hiss = new MenuItem("hiss");
            MenuItem rangarang = new MenuItem("rangarang");
            MenuItem takTak = new MenuItem("tak tak");
            MenuItem snickers = new MenuItem("snickers");

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

            kind = "biscuit";

            menuInfo(2, "biscuit", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem hiBye = new MenuItem("hi bye");
            MenuItem petitBeurre = new MenuItem("petit beurre");
            MenuItem sagheTalai = new MenuItem("saghe talai");
            MenuItem oreo = new MenuItem("oreo");

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

            kind = "nuts";

            menuInfo(2, "nuts", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem mani = new MenuItem("mani");
            MenuItem mazMaz = new MenuItem("maz maz");
            MenuItem sanjaghak = new MenuItem("sanjaghak");
            MenuItem planters = new MenuItem("planters");


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

            kind = "cake";

            menuInfo(2, "cake", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem cakeMake = new MenuItem("cake make");
            MenuItem tTop = new MenuItem("t top");
            MenuItem tiny = new MenuItem("tiny");
            MenuItem mrKipling = new MenuItem("mr kipling");

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

            kind = "chips";

            menuInfo(2, "chips", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem chakelz = new MenuItem("chakelz");
            MenuItem cheetoz = new MenuItem("cheeroz");
            MenuItem delMaze = new MenuItem("del maze");
            MenuItem lays = new MenuItem("lay's");

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

            kind = "pofak";

            menuInfo(2, "pofak", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem chakelz = new MenuItem("chakelz");
            MenuItem cheetoz = new MenuItem("cheetoz");
            MenuItem pofakNamaki = new MenuItem("pofak namaki");
            MenuItem cheetos = new MenuItem("cheetos");

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

            kind = "chewing gum";

            menuInfo(2, "chewing gum", menuButtonKind, menuButtonMinorKind, menuButtonBrand);

            MenuItem action = new MenuItem("action");
            MenuItem biodent = new MenuItem("biodent");
            MenuItem trident = new MenuItem("trident");
            MenuItem orbit = new MenuItem("orbit");

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

            if (!Application.shop.currentSeller.allItems.contains(item)) {
                Application.shop.currentSeller.allItems.add(item);
                Application.shop.currentSeller.tempItems.add(item);
                Application.shop.allItems.add(item);
                Application.shop.tempItems.add(item);
                Database.addProduct("seller_items_", item, Application.shop.currentSeller.getUsername());

                productImg.setImage(item.image);
                addtxt.setText("add successfully");

                showItems("seller", Application.shop.currentSeller.tempItems);

            } else {
                addtxt.setText("product is valid");
            }
        } else {
            addtxt.setText("fill all the blanks");
        }

        txtProductName.setText("");
        txtProductPrice.setText("");
        txtProductSize.setText("");
        txtProductProperty.setText("");

        menuButtonKind.setText("Kind");
        menuButtonMinorKind.setText("Minor Kind");
        menuButtonBrand.setText("Brand");
    }

    @FXML
    private ImageView imgcustomer, imgseller;

    public void addProfile() throws SQLException {

        if (Application.shop.currentSeller != null) {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.gif)", "*.png", "*.jpg", "*.gif");
            fileChooser.getExtensionFilters().add(extFilter);

            File selectedFile = fileChooser.showOpenDialog(new Stage());

            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imgseller.setImage(image);
                Application.shop.currentSeller.image = image;
                Database.updateApplicantImage(Application.shop.currentSeller);
            }
        }

        if (Application.shop.currentCustomer != null) {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.gif)", "*.png", "*.jpg", "*.gif");
            fileChooser.getExtensionFilters().add(extFilter);

            File selectedFile = fileChooser.showOpenDialog(new Stage());

            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imgcustomer.setImage(image);
                Application.shop.currentCustomer.image = image;
                Database.updateApplicantImage(Application.shop.currentCustomer);
            }
        }
    }

    @FXML
    private AnchorPane mainPage;
    @FXML
    private TabPane tabPane;
    static int icount = 0;
    static int jcount = 0;

    public void showItems(String applicantKind, ArrayList<Item> items) {

        mainPage.getChildren().clear();
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
                        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("product.fxml"));
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

                button.setOnAction(ev -> {
                    Item item = items.get(finalI);
                    if (Application.shop.currentSeller.auction == null) {
                        Application.shop.currentSeller.auction = item;
                        item.isAuction = true;
                    } else {
                        if (!Application.shop.currentSeller.auction.equals(item)) {
                            Application.shop.currentSeller.auction = item;
                            item.isAuction = true;
                        }
                    }
                    try {
                        Database.updateItemAuction(item);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    for (int j = 0; j < Application.shop.currentSeller.allItems.size(); j++) {
                        if (!Application.shop.currentSeller.allItems.get(j).equals(item)) {
                            Application.shop.currentSeller.allItems.get(j).isAuction = false;
                            try {
                                Database.updateItemAuction(Application.shop.currentSeller.allItems.get(j));
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    displayauction();
                    tabPane.getSelectionModel().select(1);
                });

            }
            if (applicantKind.equals("customer")) {

                button = new Button("add");
                button.setFont(new Font(15));

                button.setOnAction(ev -> {
                    Item item = items.get(finalI);
                    if (!Application.shop.currentCustomer.cartItems.contains(item)) {
                        Application.shop.currentCustomer.cartItems.add(item);
                    }
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
            anchorPane.setEffect(dropShadow);
            anchorPane.setEffect(innerShadow);


            anchorPane.getChildren().addAll(imageView, name, price, button, scoreEmoji, score);
            mainPage.getChildren().add(anchorPane);

            icount++;
            if (icount > 5) {
                jcount++;
                icount = 0;
            }
        }

    }

    public void showSeller() {
        showItems("seller", Application.shop.currentSeller.tempItems);
    }

    public void showCustomer() {
        showItems("customer", Application.shop.tempItems);
    }


    @FXML
    private AnchorPane auctionPage;

    public void auction() {

        Shop.sortByAuction(Application.shop.allItems, Application.shop.tempItems);


        icount = 0;
        jcount = 0;
        auctionPage.getChildren().clear();

        for (int i = 0; i < Application.shop.tempItems.size(); i++) {

            int finalI = i;


            ImageView imageView = new ImageView(Application.shop.tempItems.get(i).image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);


            Label name = new Label("Name :  " + Application.shop.tempItems.get(i).name);
            name.setFont(new Font("Arial", 25));
            name.setLayoutX(200);
            name.setLayoutY(10);

            Label brand = new Label("Brand :  " + Application.shop.tempItems.get(i).brand);
            brand.setFont(new Font("Arial", 25));
            brand.setLayoutX(200);
            brand.setLayoutY(60);


            Label price = new Label("Max Bid  :  " + Application.shop.tempItems.get(i).tempPrice);
            price.setFont(new Font("Arial", 25));
            price.setLayoutX(200);
            price.setLayoutY(110);

            TextField bid = new TextField();
            bid.setPromptText("offer a price");
            bid.setEffect(new DropShadow());
            bid.setPrefWidth(150);
            bid.setPrefHeight(30);
            bid.setLayoutX(400);
            bid.setLayoutY(160);


            Button button = new Button("bid");
            button.setFont(new Font(15));
            button.setOnAction(ev -> {

                //if (Application.shop.currentCustomer.wallet > Integer.parseInt(bid.getText())) {
                if (Integer.parseInt(bid.getText()) > Application.shop.tempItems.get(finalI).tempPrice) {
                    Application.shop.currentCustomer.wallet -= Application.shop.tempItems.get(finalI).tempPrice;
                    Application.shop.tempItems.get(finalI).tempPrice = Integer.parseInt(bid.getText());
                    price.setText("Max Bid  :  " + Application.shop.tempItems.get(finalI).tempPrice);
                    Application.shop.currentCustomer.AuctionItems.add(Application.shop.tempItems.get(finalI));
                    try {
                        Database.updateItemAuction(Application.shop.tempItems.get(finalI));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                //}

            });
            button.setEffect(new DropShadow());
            button.setPrefWidth(50);
            button.setPrefHeight(30);
            button.setLayoutX(580);
            button.setLayoutY(160);


            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setLayoutX(50);
            anchorPane.setLayoutX(icount * 700 + 60);
            anchorPane.setLayoutY(jcount * 240 + 50);
            anchorPane.setPrefWidth(660);
            anchorPane.setPrefHeight(200);
            anchorPane.setStyle("-fx-background-color: #FFCF21;");
            anchorPane.setEffect(new DropShadow());


            anchorPane.getChildren().addAll(imageView, name, brand, price, bid, button);
            auctionPage.getChildren().add(anchorPane);

            icount++;

            if (icount > 1) {
                jcount++;
                icount = 0;
            }

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

    @FXML
    private Label labelauctionname, labelauctionbrand, labelauctionmaxbid;
    @FXML
    private ImageView imgauctionimage;

    public void displayauction() {

        if (Application.shop.currentSeller.auction != null) {

            labelauctionname.setText(Application.shop.currentSeller.auction.name);
            labelauctionbrand.setText(Application.shop.currentSeller.auction.brand);
            labelauctionmaxbid.setText(String.valueOf(Application.shop.currentSeller.auction.tempPrice));
            imgauctionimage.setImage(Application.shop.currentSeller.auction.image);
        }
    }

    @FXML
    private Label txtsellmessage;

    public void sellAuction() throws SQLException {

        Item item;
        outerloop:
        for (int i = 0; i < Application.shop.customers.size(); i++) {
            for (int j = 0; j < Application.shop.customers.get(i).AuctionItems.size(); j++) {

                if (Application.shop.currentSeller.auction == Application.shop.customers.get(i).AuctionItems.get(j)) {

                    for (int k = 0; k < Application.shop.allItems.size(); k++) {

                        if (Application.shop.customers.get(i).AuctionItems.get(j).tempPrice == Application.shop.tempItems.get(k).tempPrice) {

                            Application.shop.currentSeller.walletbalance += Application.shop.tempItems.get(k).tempPrice;
                            Application.shop.customers.get(i).wallet -= Application.shop.tempItems.get(k).tempPrice;
                            Database.updateCustomerWallet(Application.shop.customers.get(i));
                            Database.updateSellerWallet(Application.shop.currentSeller);
                            item = Application.shop.currentSeller.auction;
                            Database.updateItemAuction(item);
                            Application.shop.customers.get(i).AuctionItems.remove(j);
                            Application.shop.currentSeller.allItems.remove(Application.shop.currentSeller.auction);
                            Application.shop.allItems.remove(Application.shop.currentSeller.auction);
                            Application.shop.currentSeller.auction = null;
                            Database.removeProduct(item.sellerUsername, item.getCode());
                            txtsellmessage.setText("Auction Saled Successfully");

                            break outerloop;
                        }
                    }
                }
            }
        }
    }


    //------------------inner classes-----------------


    public static class DiscountCode {
        private String discountCode;
        private long discountAmount;


        private static final String CHARACTERS = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz123456789";
        private static final int CAPTCHA_LENGTH = 10;

        public DiscountCode(long amount) {
            discountCode = generateCaptchaText();
            discountAmount = amount;
        }

        public String getDiscountCode() {
            return discountCode;
        }

        public long getDiscountAmount() {
            return discountAmount;
        }

        private String generateCaptchaText() {
            Random random = new Random();
            StringBuilder sb = new StringBuilder(CAPTCHA_LENGTH);
            for (int i = 0; i < CAPTCHA_LENGTH; i++) {
                sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            return sb.toString();
        }

        public boolean equals(Object o) {
            if (o instanceof DiscountCode) {
                DiscountCode other = (DiscountCode) o;
                if (this.discountCode.equals(other.discountCode)) return true;
            }
            return false;
        }
    }

}
