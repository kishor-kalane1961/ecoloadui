package com.super_x.view.UserView;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostLoad {

    // =========================================================
    // SCENE
    // =========================================================

    private Scene postloadScene;

    // =========================================================
    // COLORS
    // =========================================================

    private static final String GREEN = "#0B6B22";
    private static final String DARK_GREEN = "#075719";
    private static final String BORDER = "#D5D8D5";
    private static final String TEXT = "#202420";
    private static final String PAGE_BG = "#F7F8F7";
    private static final String CARD_BG = "#FFFFFF";
    private static final String RED = "#C62828";

    // =========================================================
    // CSV PATHS
    // =========================================================

    private final Path loadsFile = Paths.get("data", "loads.csv");

    private final Path driversFile = Paths.get("data", "drivers.csv");

    // =========================================================
    // FORM CONTROLS
    // =========================================================

    private TextField pickupField;
    private TextField dropField;

    private ComboBox<String> loadTypeCombo;

    private TextField weightField;

    private ComboBox<String> unitCombo;

    private ComboBox<String> truckTypeCombo;

    private TextField offerPriceField;

    private DatePicker pickupDatePicker;
    private TextField pickupTimeField;

    private DatePicker deliveryDatePicker;
    private TextField deliveryTimeField;

    // =========================================================
    // ERROR LABELS
    // =========================================================

    private Label pickupError;
    private Label dropError;
    private Label loadTypeError;
    private Label weightError;
    private Label truckTypeError;
    private Label offerPriceError;
    private Label pickupDateError;
    private Label deliveryDateError;

    // =========================================================
    // ROUTE OVERVIEW
    // =========================================================

    private Label routeLabel;
    private Label distanceLabel;
    private Label travelTimeLabel;

    // =========================================================
    // BUTTONS
    // =========================================================

    private Button postLoadButton;
    private Button draftButton;
    private Button cancelButton;

    // =========================================================
    // DATE TIME FORMAT
    // =========================================================

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "MM/dd/yyyy HH:mm");

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    // =========================================================
    // GET SCENE
    // IMPORTANT:
    // Main.java uses getpostloadScene()
    // =========================================================

    public Scene getpostloadScene() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + PAGE_BG + ";");

        // Main content
        VBox mainContent = createMainContent();

        ScrollPane scrollPane = new ScrollPane(mainContent);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-background: transparent;");

        root.setTop(UserNavigation.createNavbar());

        root.setCenter(scrollPane);

        BorderPane mainroot = new BorderPane();
        mainroot.setCenter(root);
        mainroot.setLeft(UserNavigation.createSidebar("PostLoad"));

        postloadScene = new Scene(mainroot, 1536, 750);

        return postloadScene;
    }
    // =========================================================
    // MAIN CONTENT
    // =========================================================

    private VBox createMainContent() {

        VBox main = new VBox(22);

        main.setPadding(
                new Insets(
                        28,
                        38,
                        30,
                        38));

        HBox columns = new HBox(22);

        columns.setAlignment(
                Pos.TOP_CENTER);

        VBox leftCard = createLoadDetailsCard();

        HBox.setHgrow(
                leftCard,
                Priority.ALWAYS);

        VBox rightSide = createRightSide();

        columns.getChildren().addAll(
                leftCard,
                rightSide);

        main.getChildren().add(
                columns);

        return main;
    }

    // =========================================================
    // LOAD DETAILS CARD
    // =========================================================

    private VBox createLoadDetailsCard() {

        VBox card = new VBox(20);

        card.setPadding(
                new Insets(28));

        card.setPrefWidth(
                700);

        card.setStyle(
                cardStyle());

        Label plus = new Label("+");

        plus.setAlignment(Pos.CENTER);

        plus.setPrefSize(38, 38);

        plus.setStyle(
                "-fx-border-color: #0B6B22;"
                        + "-fx-border-width: 2;"
                        + "-fx-border-radius: 50;"
                        + "-fx-background-radius: 50;"
                        + "-fx-text-fill: #0B6B22;"
                        + "-fx-font-size: 22px;"
                        + "-fx-font-weight: bold;");

        Label heading = new Label("Post New Shipment");

        heading.setPrefSize(250, 40);

        heading.setMinWidth(250);

        heading.setMinHeight(40);

        heading.setVisible(true);

        heading.setManaged(true);

        heading.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        21));

        heading.setStyle("-fx-text-fill:#0B6B22");

        HBox titleRow = new HBox();

        titleRow.setSpacing(12);

        titleRow.setAlignment(
                Pos.CENTER_LEFT);

        titleRow.getChildren().addAll(
                plus,
                heading);

        // -----------------------------------------------------
        // GRID
        // -----------------------------------------------------

        GridPane grid = new GridPane();

        grid.setHgap(16

        );
        grid.setVgap(13);

        ColumnConstraints first = new ColumnConstraints();

        first.setPercentWidth(50);

        ColumnConstraints second = new ColumnConstraints();

        second.setPercentWidth(50);

        grid.getColumnConstraints().addAll(
                first,
                second);

        // -----------------------------------------------------
        // PICKUP
        // -----------------------------------------------------

        VBox pickupBox = createFieldBox(
                "Pickup Location");

        pickupField = createTextField(
                "City, State or Warehouse");

        pickupBox.getChildren().add(
                pickupField);

        pickupError = createErrorLabel();

        pickupBox.getChildren().add(
                pickupError);

        // -----------------------------------------------------
        // DROP
        // -----------------------------------------------------

        VBox dropBox = createFieldBox(
                "Drop Location");

        dropField = createTextField(
                "Destination Address");

        dropBox.getChildren().add(
                dropField);

        dropError = createErrorLabel();

        dropBox.getChildren().add(
                dropError);

        grid.add(
                pickupBox,
                0,
                0);

        grid.add(
                dropBox,
                1,
                0);

        // -----------------------------------------------------
        // LOAD TYPE
        // -----------------------------------------------------

        VBox loadTypeBox = createFieldBox(
                "Load Type");

        loadTypeCombo = new ComboBox<>();

        loadTypeCombo.setPromptText(
                "Select Type");

        loadTypeCombo.getItems().addAll(
                "General",
                "Perishable",
                "Fragile",
                "Container",
                "Liquid",
                "Heavy Goods");

        loadTypeCombo.setPrefHeight(
                46);

        loadTypeCombo.setMaxWidth(
                Double.MAX_VALUE);

        applyComboStyle(
                loadTypeCombo);

        loadTypeError = createErrorLabel();

        loadTypeBox.getChildren().addAll(
                loadTypeCombo,
                loadTypeError);

        // -----------------------------------------------------
        // WEIGHT
        // -----------------------------------------------------

        VBox weightBox = createFieldBox(
                "Weight");

        weightField = createTextField(
                "0.00");

        weightField.setText(
                "0.00");

        weightBox.getChildren().add(
                weightField);

        weightError = createErrorLabel();

        weightBox.getChildren().add(
                weightError);

        // -----------------------------------------------------
        // UNIT
        // -----------------------------------------------------

        VBox unitBox = createFieldBox(
                "Unit");

        unitCombo = new ComboBox<>();

        unitCombo.getItems().addAll(
                "Ton",
                "Kg");

        unitCombo.setValue(
                "Ton");

        unitCombo.setPrefHeight(
                46);

        unitCombo.setMaxWidth(
                Double.MAX_VALUE);

        applyComboStyle(
                unitCombo);

        unitBox.getChildren().add(
                unitCombo);

        HBox weightUnit = new HBox(10);

        HBox.setHgrow(
                weightBox,
                Priority.ALWAYS);

        HBox.setHgrow(
                unitBox,
                Priority.NEVER);

        weightUnit.getChildren().addAll(
                weightBox,
                unitBox);

        grid.add(
                loadTypeBox,
                0,
                1);

        grid.add(
                weightUnit,
                1,
                1);

        // -----------------------------------------------------
        // TRUCK TYPE
        // -----------------------------------------------------

        VBox truckBox = createFieldBox(
                "Required Truck Type");

        truckTypeCombo = new ComboBox<>();

        truckTypeCombo.setPromptText(
                "Select Truck Type");

        truckTypeCombo.setPrefHeight(
                46);

        truckTypeCombo.setMaxWidth(
                Double.MAX_VALUE);

        loadTruckTypes();

        applyComboStyle(
                truckTypeCombo);

        truckTypeError = createErrorLabel();

        truckBox.getChildren().addAll(
                truckTypeCombo,
                truckTypeError);

        // -----------------------------------------------------
        // OFFER PRICE
        // -----------------------------------------------------

        VBox priceBox = createFieldBox(
                "Offer Price (₹)");

        HBox priceContainer = new HBox();

        priceContainer.setAlignment(
                Pos.CENTER_LEFT);

        priceContainer.setPadding(
                new Insets(
                        0,
                        12,
                        0,
                        12));

        priceContainer.setPrefHeight(
                46);

        priceContainer.setStyle(
                "-fx-background-color: #F7F5F5;"
                        + "-fx-border-color: "
                        + BORDER + ";"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;");

        Label rupee = new Label("₹");

        rupee.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        16));

        rupee.setTextFill(
                Color.web(GREEN));

        offerPriceField = createTextField(
                "Enter amount");

        offerPriceField.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-border-color: transparent;"
                        + "-fx-padding: 0;"
                        + "-fx-font-size: 13px;"
                        + "-fx-text-fill: " + TEXT + ";"
                        + "-fx-prompt-text-fill: #858985;");

        HBox.setHgrow(
                offerPriceField,
                Priority.ALWAYS);

        priceContainer.getChildren().addAll(
                rupee,
                offerPriceField);

        priceBox.getChildren().add(
                priceContainer);

        offerPriceError = createErrorLabel();

        priceBox.getChildren().add(
                offerPriceError);

        grid.add(
                truckBox,
                0,
                2);

        grid.add(
                priceBox,
                1,
                2);

        // -----------------------------------------------------
        // PICKUP DATE
        // -----------------------------------------------------

        VBox pickupDateBox = createFieldBox(
                "Pickup Date & Time");

        HBox pickupDateTime = createDateTimeInput();

        pickupDatePicker = (DatePicker) pickupDateTime
                .getChildren()
                .get(0);

        pickupTimeField = (TextField) pickupDateTime
                .getChildren()
                .get(1);

        pickupDateBox.getChildren().add(
                pickupDateTime);

        pickupDateError = createErrorLabel();

        pickupDateBox.getChildren().add(
                pickupDateError);

        // -----------------------------------------------------
        // DELIVERY DATE
        // -----------------------------------------------------

        VBox deliveryDateBox = createFieldBox(
                "Delivery Date & Time");

        HBox deliveryDateTime = createDateTimeInput();

        deliveryDatePicker = (DatePicker) deliveryDateTime
                .getChildren()
                .get(0);

        deliveryTimeField = (TextField) deliveryDateTime
                .getChildren()
                .get(1);

        deliveryDateBox.getChildren().add(
                deliveryDateTime);

        deliveryDateError = createErrorLabel();

        deliveryDateBox.getChildren().add(
                deliveryDateError);

        grid.add(
                pickupDateBox,
                0,
                3);

        grid.add(
                deliveryDateBox,
                1,
                3);

        // -----------------------------------------------------
        // ADD
        // -----------------------------------------------------

        card.getChildren().addAll(
                titleRow,
                grid);

        // -----------------------------------------------------
        // ROUTE LISTENER
        // -----------------------------------------------------

        ChangeListener<String> routeListener = (observable, oldValue, newValue) -> updateRouteOverview();

        pickupField.textProperty()
                .addListener(
                        routeListener);

        dropField.textProperty()
                .addListener(
                        routeListener);

        return card;
    }

    // =========================================================
    // FIELD BOX
    // =========================================================

    private VBox createFieldBox(
            String text) {

        VBox box = new VBox(7);

        // Label label =
        // new Label(text);

        Text t1 = new Text(text);
        t1.setVisible(true);
        t1.setManaged(true);

        t1.setFont(
                Font.font(
                        "System",
                        FontWeight.NORMAL,
                        12));

        // label.setTextFill(
        // Color.web("#353935")
        // );
        t1.setFill(
                Color.BLACK);
        t1.setStyle(
                "-fx-font-size: 15px;"
                        + "-fx-font-weight: bold;");
        box.setAlignment(
                Pos.TOP_LEFT);

        box.getChildren().add(
                t1);

        return box;
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private TextField createTextField(
            String prompt) {

        TextField field = new TextField();

        field.setPromptText(
                prompt);

        field.setPrefHeight(
                46);

        field.setFont(
                Font.font(
                        "System",
                        13));

        field.setStyle(
                "-fx-background-color: #F7F5F5;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
                        + "-fx-padding: 0 13 0 13;"
                        + "-fx-text-fill: " + TEXT + ";"
                        + "-fx-prompt-text-fill: #858985;");

        field.focusedProperty()
                .addListener(
                        (obs, oldValue, focused) -> {

                            if (focused) {

                                field.setStyle(
                                        "-fx-background-color: white;"
                                                + "-fx-border-color: "
                                                + GREEN + ";"
                                                + "-fx-border-width: 1.5;"
                                                + "-fx-border-radius: 8;"
                                                + "-fx-background-radius: 8;"
                                                + "-fx-padding: 0 13 0 13;"
                                                + "-fx-text-fill: "
                                                + TEXT + ";"
                                                + "-fx-prompt-text-fill: "
                                                + "#858985;");

                            } else {

                                field.setStyle(
                                        "-fx-background-color: #F7F5F5;"
                                                + "-fx-border-color: "
                                                + BORDER + ";"
                                                + "-fx-border-radius: 8;"
                                                + "-fx-background-radius: 8;"
                                                + "-fx-padding: 0 13 0 13;"
                                                + "-fx-text-fill: "
                                                + TEXT + ";"
                                                + "-fx-prompt-text-fill: "
                                                + "#858985;");
                            }
                        });

        return field;
    }

    // =========================================================
    // DATE TIME
    // =========================================================

    private HBox createDateTimeInput() {

        DatePicker datePicker = new DatePicker();

        datePicker.setPrefHeight(
                46);

        datePicker.setPromptText(
                "mm/dd/yyyy");

        datePicker.setMaxWidth(
                Double.MAX_VALUE);

        HBox.setHgrow(
                datePicker,
                Priority.ALWAYS);

        TextField timeField = createTextField(
                "--:--");

        timeField.setPrefWidth(
                95);

        timeField.setPromptText(
                "--:--");

        HBox box = new HBox(8);

        box.setAlignment(
                Pos.CENTER_LEFT);

        box.getChildren().addAll(
                datePicker,
                timeField);

        return box;
    }

    // =========================================================
    // COMBO STYLE
    // =========================================================

    private void applyComboStyle(
            ComboBox<String> combo) {

        combo.setStyle(
                "-fx-background-color: #F7F5F5;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
                        + "-fx-font-size: 13px;"
                        + "-fx-text-fill: " + TEXT + ";");
    }

    // =========================================================
    // ERROR LABEL
    // =========================================================

    private Label createErrorLabel() {

        Label label = new Label();

        label.setTextFill(
                Color.web(RED));

        label.setFont(
                Font.font(
                        "System",
                        10));

        label.setWrapText(
                true);

        return label;
    }

    // =========================================================
    // RIGHT SIDE
    // =========================================================

    private VBox createRightSide() {

        VBox right = new VBox(20);

        right.setPrefWidth(
                380);

        VBox routeCard = createRouteOverview();

        // -----------------------------------------------------
        // POST LOAD
        // -----------------------------------------------------

        postLoadButton = new Button(
                "Post Load  🚀");

        postLoadButton.setMaxWidth(
                Double.MAX_VALUE);

        postLoadButton.setPrefHeight(
                58);

        postLoadButton.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        18));

        setPrimaryButtonStyle(
                postLoadButton);

        postLoadButton.setOnAction(
                event -> handlePostLoad());

        // -----------------------------------------------------
        // DRAFT
        // -----------------------------------------------------

        draftButton = new Button(
                "Save as Draft");

        // -----------------------------------------------------
        // CANCEL
        // -----------------------------------------------------

        cancelButton = new Button(
                "Cancel");

        draftButton.setPrefHeight(
                38);

        cancelButton.setPrefHeight(
                38);

        HBox.setHgrow(
                draftButton,
                Priority.ALWAYS);

        HBox.setHgrow(
                cancelButton,
                Priority.ALWAYS);

        setSecondaryButtonStyle(
                draftButton,
                false);

        setSecondaryButtonStyle(
                cancelButton,
                true);

        draftButton.setOnAction(
                event -> handleSaveDraft());

        cancelButton.setOnAction(
                event -> handleCancel());

        HBox actions = new HBox(12);

        actions.getChildren().addAll(
                draftButton,
                cancelButton);

        // -----------------------------------------------------
        // GUARANTEE
        // -----------------------------------------------------

        VBox guarantee = createGuaranteeCard();

        right.getChildren().addAll(
                routeCard,
                postLoadButton,
                actions,
                guarantee);

        return right;
    }

    // =========================================================
    // ROUTE OVERVIEW
    // =========================================================

    private VBox createRouteOverview() {

        VBox card = new VBox();

        card.setPrefWidth(380);
        card.setMinWidth(380);

        card.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 16;"
                        + "-fx-border-radius: 16;"
                        + "-fx-border-color: #E5E8E5;");

        // =====================================================
        // GREEN TOP
        // =====================================================

        VBox top = new VBox(10);

        top.setPadding(
                new Insets(24, 22, 24, 22));

        top.setPrefHeight(140);

        top.setStyle(
                "-fx-background-color: linear-gradient("
                        + "to right, #8BB29A, #0A3718"
                        + ");"
                        + "-fx-background-radius: 16 16 0 0;");

        Label routeTitle = new Label(
                "ROUTE OVERVIEW");

        routeTitle.setStyle(
                "-fx-text-fill: white;"
                        + "-fx-font-size: 11px;"
                        + "-fx-font-weight: bold;");

        routeLabel = new Label(
                "Pickup → Destination");

        routeLabel.setStyle(
                "-fx-text-fill: white;"
                        + "-fx-font-size: 17px;"
                        + "-fx-font-weight: bold;");

        top.getChildren().addAll(
                routeTitle,
                routeLabel);

        // =====================================================
        // DISTANCE
        // =====================================================

        HBox distanceRow = new HBox();

        distanceRow.setAlignment(
                Pos.CENTER_LEFT);

        distanceRow.setPadding(
                new Insets(14, 22, 14, 22));

        distanceRow.setPrefHeight(55);

        distanceRow.setMinHeight(55);

        Label distanceIcon = new Label(
                "⌁");

        distanceIcon.setStyle(
                "-fx-text-fill: #0B6B22;"
                        + "-fx-font-size: 22px;"
                        + "-fx-font-weight: bold;");

        Label distanceText = new Label(
                "Distance");

        distanceText.setStyle(
                "-fx-text-fill: #333333;"
                        + "-fx-font-size: 13px;");

        distanceLabel = new Label(
                "1,422 KM");

        distanceLabel.setStyle(
                "-fx-text-fill: #333333;"
                        + "-fx-font-size: 13px;");

        Region distanceSpace = new Region();

        HBox.setHgrow(
                distanceSpace,
                Priority.ALWAYS);

        distanceRow.getChildren().addAll(
                distanceIcon,
                distanceText,
                distanceSpace,
                distanceLabel);

        // =====================================================
        // DIVIDER
        // =====================================================

        Region divider = new Region();

        divider.setPrefHeight(1);

        divider.setMinHeight(1);

        divider.setStyle(
                "-fx-background-color: #EEEEEE;");

        // =====================================================
        // TRAVEL TIME
        // =====================================================

        HBox timeRow = new HBox();

        timeRow.setAlignment(
                Pos.CENTER_LEFT);

        timeRow.setPadding(
                new Insets(14, 22, 14, 22));

        timeRow.setPrefHeight(55);

        timeRow.setMinHeight(55);

        Label timeIcon = new Label(
                "◷");

        timeIcon.setStyle(
                "-fx-text-fill: #0B6B22;"
                        + "-fx-font-size: 22px;"
                        + "-fx-font-weight: bold;");

        Label timeText = new Label(
                "Est. Travel Time");

        timeText.setStyle(
                "-fx-text-fill: #333333;"
                        + "-fx-font-size: 13px;");

        travelTimeLabel = new Label(
                "~28 HRS");

        travelTimeLabel.setStyle(
                "-fx-text-fill: #333333;"
                        + "-fx-font-size: 13px;");

        Region timeSpace = new Region();

        HBox.setHgrow(
                timeSpace,
                Priority.ALWAYS);

        timeRow.getChildren().addAll(
                timeIcon,
                timeText,
                timeSpace,
                travelTimeLabel);

        // =====================================================
        // ADD ALL TO CARD
        // =====================================================

        card.getChildren().addAll(
                top,
                distanceRow,
                divider,
                timeRow);

        return card;
    }
    // =========================================================
    // ECO SAFE CARD
    // =========================================================

    private VBox createGuaranteeCard() {

        VBox card = new VBox();

        card.setPadding(
                new Insets(16));

        card.setStyle(
                "-fx-background-color: #0B6B22;"
                        + "-fx-background-radius: 15;"
                        + "-fx-border-radius: 15;");

        Label icon = new Label("♢");

        icon.setAlignment(
                Pos.CENTER);

        icon.setPrefSize(
                38,
                38);

        icon.setStyle(
                "-fx-background-color: #E1F0E4;"
                        + "-fx-background-radius: 10;"
                        + "-fx-text-fill: #0B6B22;"
                        + "-fx-font-size: 20px;"
                        + "-fx-font-weight: bold;");

        Label title = new Label(
                "EcoSafe Guarantee");

        title.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        12));

        Label description = new Label(
                "Your transaction is protected. Payments are only\n"
                        + "released upon successful delivery verification.");

        description.setFont(
                Font.font(
                        "System",
                        10));

        description.setTextFill(
                Color.web("#777B77"));

        VBox text = new VBox(
                4,
                title,
                description);

        HBox row = new HBox(
                14,
                icon,
                text);

        row.setAlignment(
                Pos.CENTER_LEFT);

        card.getChildren().add(
                row);

        return card;
    }

    // =========================================================
    // PRIMARY BUTTON STYLE
    // =========================================================

    private void setPrimaryButtonStyle(
            Button button) {

        button.setStyle(
                "-fx-background-color: " + GREEN + ";"
                        + "-fx-background-radius: 12;"
                        + "-fx-border-radius: 12;"
                        + "-fx-text-fill: white;"
                        + "-fx-cursor: hand;");

        button.setOnMouseEntered(
                event -> button.setStyle(
                        "-fx-background-color: "
                                + DARK_GREEN + ";"
                                + "-fx-background-radius: 12;"
                                + "-fx-border-radius: 12;"
                                + "-fx-text-fill: white;"
                                + "-fx-cursor: hand;"));

        button.setOnMouseExited(
                event -> button.setStyle(
                        "-fx-background-color: "
                                + GREEN + ";"
                                + "-fx-background-radius: 12;"
                                + "-fx-border-radius: 12;"
                                + "-fx-text-fill: white;"
                                + "-fx-cursor: hand;"));
    }

    // =========================================================
    // SECONDARY BUTTON STYLE
    // =========================================================

    private void setSecondaryButtonStyle(
            Button button,
            boolean danger) {

        String color = danger
                ? RED
                : "#404440";

        String hover = danger
                ? "#FFF0F0"
                : "#F0F3F0";

        button.setMaxWidth(
                Double.MAX_VALUE);

        button.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + color + ";"
                        + "-fx-border-width: 1;"
                        + "-fx-border-radius: 20;"
                        + "-fx-background-radius: 20;"
                        + "-fx-text-fill: " + color + ";"
                        + "-fx-font-size: 12px;"
                        + "-fx-cursor: hand;");

        button.setOnMouseEntered(
                event -> button.setStyle(
                        "-fx-background-color: "
                                + hover + ";"
                                + "-fx-border-color: "
                                + color + ";"
                                + "-fx-border-width: 1;"
                                + "-fx-border-radius: 20;"
                                + "-fx-background-radius: 20;"
                                + "-fx-text-fill: "
                                + color + ";"
                                + "-fx-font-size: 12px;"
                                + "-fx-cursor: hand;"));

        button.setOnMouseExited(
                event -> button.setStyle(
                        "-fx-background-color: white;"
                                + "-fx-border-color: "
                                + color + ";"
                                + "-fx-border-width: 1;"
                                + "-fx-border-radius: 20;"
                                + "-fx-background-radius: 20;"
                                + "-fx-text-fill: "
                                + color + ";"
                                + "-fx-font-size: 12px;"
                                + "-fx-cursor: hand;"));
    }

    // =========================================================
    // ROUTE UPDATE
    // =========================================================

    private void updateRouteOverview() {

        if (routeLabel == null) {
            return;
        }

        String pickup = pickupField
                .getText()
                .trim();

        String destination = dropField
                .getText()
                .trim();

        String pickupText = pickup.isEmpty()
                ? "Pickup"
                : pickup;

        String destinationText = destination.isEmpty()
                ? "Destination"
                : destination;

        routeLabel.setText(
                pickupText
                        + "  →  "
                        + destinationText);

        if (pickup.isEmpty()
                ||
                destination.isEmpty()) {

            distanceLabel.setText(
                    "—");

            travelTimeLabel.setText(
                    "—");

            return;
        }

        /*
         * Temporary route service.
         *
         * This is kept separate from the UI.
         * When Google Maps integration exists,
         * replace this method with the real service.
         */

        RouteEstimate estimate = getRouteEstimate(
                pickup,
                destination);

        distanceLabel.setText(
                estimate.distance
                        + " KM");

        travelTimeLabel.setText(
                "~"
                        + estimate.hours
                        + " HRS");
    }

    // =========================================================
    // TEMPORARY ROUTE SERVICE
    // =========================================================

    private RouteEstimate getRouteEstimate(
            String pickup,
            String destination) {

        String p = pickup.toLowerCase(
                Locale.ROOT);

        String d = destination.toLowerCase(
                Locale.ROOT);

        // Screenshot example
        if (p.contains("delhi")
                &&
                d.contains("mumbai")) {

            return new RouteEstimate(
                    1422,
                    28);
        }

        // Pune - Nashik
        if (p.contains("pune")
                &&
                d.contains("nashik")) {

            return new RouteEstimate(
                    210,
                    5);
        }

        // Mumbai - Pune
        if (p.contains("mumbai")
                &&
                d.contains("pune")) {

            return new RouteEstimate(
                    150,
                    4);
        }

        /*
         * Temporary testing estimate only.
         * NOT a real distance calculation.
         */

        int hash = Math.abs(
                (pickup
                        + "|"
                        + destination).hashCode());

        int distance = 100 + (hash % 1400);

        int hours = Math.max(
                2,
                (int) Math.ceil(
                        distance / 55.0));

        return new RouteEstimate(
                distance,
                hours);
    }

    // =========================================================
    // POST LOAD
    // =========================================================

    private void handlePostLoad() {

        clearErrors();

        if (!validateFullForm()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please correct the highlighted fields.");

            return;
        }

        try {

            String loadId = saveLoadToCsv(
                    "Available");

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Load Posted",
                    "Load posted successfully.\n\n"
                            + "Load ID : "
                            + loadId);

            clearForm();

        } catch (Exception e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Save Error",
                    "Unable to save the load.\n\n"
                            + e.getMessage());
        }
    }

    // =========================================================
    // SAVE DRAFT
    // =========================================================

    private void handleSaveDraft() {

        clearErrors();

        if (!validateDraftForm()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Draft Validation",
                    "Enter at least pickup and destination.");

            return;
        }

        try {

            String loadId = saveLoadToCsv(
                    "Draft");

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Draft Saved",
                    "Load saved as draft.\n\n"
                            + "Load ID : "
                            + loadId);

            clearForm();

        } catch (Exception e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Draft Error",
                    "Unable to save draft.\n\n"
                            + e.getMessage());
        }
    }

    // =========================================================
    // CANCEL
    // =========================================================

    private void handleCancel() {

        if (!hasEnteredData()) {

            clearForm();

            return;
        }

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION);

        alert.setTitle(
                "Cancel Shipment");

        alert.setHeaderText(
                "Cancel this shipment?");

        alert.setContentText(
                "Entered information will be cleared.\n"
                        + "No data will be written.");

        alert.showAndWait()
                .ifPresent(
                        result -> {

                            if (result.getButtonData()
                                    .isDefaultButton()) {

                                clearForm();
                            }
                        });
    }

    // =========================================================
    // FULL VALIDATION
    // =========================================================

    private boolean validateFullForm() {

        boolean valid = true;

        // Pickup
        if (pickupField
                .getText()
                .trim()
                .isEmpty()) {

            pickupError.setText(
                    "Pickup location is required.");

            valid = false;
        }

        // Destination
        if (dropField
                .getText()
                .trim()
                .isEmpty()) {

            dropError.setText(
                    "Destination is required.");

            valid = false;
        }

        // Same location
        if (!pickupField
                .getText()
                .trim()
                .isEmpty()
                &&
                !dropField
                        .getText()
                        .trim()
                        .isEmpty()
                &&
                pickupField
                        .getText()
                        .trim()
                        .equalsIgnoreCase(
                                dropField
                                        .getText()
                                        .trim())) {

            dropError.setText(
                    "Pickup and destination must be different.");

            valid = false;
        }

        // Load Type
        if (loadTypeCombo
                .getValue() == null) {

            loadTypeError.setText(
                    "Please select load type.");

            valid = false;
        }

        // Weight
        Double weight = parsePositiveNumber(
                weightField
                        .getText());

        if (weight == null) {

            weightError.setText(
                    "Weight must be greater than 0.");

            valid = false;
        }

        // Truck
        if (truckTypeCombo
                .getValue() == null) {

            truckTypeError.setText(
                    "Please select truck type.");

            valid = false;
        }

        // Price
        Double price = parsePositiveNumber(
                offerPriceField
                        .getText());

        if (price == null) {

            offerPriceError.setText(
                    "Offer price must be greater than 0.");

            valid = false;
        }

        // Pickup date
        LocalDateTime pickup = parseDateTime(
                pickupDatePicker,
                pickupTimeField);

        if (pickup == null) {

            pickupDateError.setText(
                    "Enter valid pickup date and time.");

            valid = false;
        }

        // Delivery date
        LocalDateTime delivery = parseDateTime(
                deliveryDatePicker,
                deliveryTimeField);

        if (delivery == null) {

            deliveryDateError.setText(
                    "Enter valid delivery date and time.");

            valid = false;
        }

        // Delivery after pickup
        if (pickup != null
                &&
                delivery != null
                &&
                !delivery.isAfter(pickup)) {

            deliveryDateError.setText(
                    "Delivery must be after pickup.");

            valid = false;
        }

        return valid;
    }

    // =========================================================
    // DRAFT VALIDATION
    // =========================================================

    private boolean validateDraftForm() {

        boolean valid = true;

        String pickup = pickupField
                .getText()
                .trim();

        String drop = dropField
                .getText()
                .trim();

        if (pickup.isEmpty()) {

            pickupError.setText(
                    "Pickup location is required.");

            valid = false;
        }

        if (drop.isEmpty()) {

            dropError.setText(
                    "Destination is required.");

            valid = false;
        }

        if (!pickup.isEmpty()
                &&
                !drop.isEmpty()
                &&
                pickup.equalsIgnoreCase(
                        drop)) {

            dropError.setText(
                    "Pickup and destination must be different.");

            valid = false;
        }

        return valid;
    }

    // =========================================================
    // NUMBER VALIDATION
    // =========================================================

    private Double parsePositiveNumber(
            String value) {

        if (value == null
                ||
                value.trim().isEmpty()) {

            return null;
        }

        try {

            double number = Double.parseDouble(
                    value.trim());

            if (number <= 0
                    ||
                    Double.isNaN(number)
                    ||
                    Double.isInfinite(number)) {

                return null;
            }

            return number;

        } catch (NumberFormatException e) {

            return null;
        }
    }

    // =========================================================
    // DATE TIME PARSER
    // =========================================================

    private LocalDateTime parseDateTime(
            DatePicker datePicker,
            TextField timeField) {

        if (datePicker == null
                ||
                datePicker.getValue() == null) {

            return null;
        }

        String time = timeField
                .getText()
                .trim();

        if (time.isEmpty()) {

            return null;
        }

        try {

            LocalTime localTime = LocalTime.parse(
                    time,
                    TIME_FORMATTER);

            return LocalDateTime.of(
                    datePicker.getValue(),
                    localTime);

        } catch (DateTimeParseException e) {

            return null;
        }
    }

    // =========================================================
    // SAVE LOAD TO CSV
    // =========================================================

    private String saveLoadToCsv(
            String status) throws IOException {

        if (!Files.exists(loadsFile)) {

            throw new IOException(
                    "data/loads.csv not found.");
        }

        List<String> lines = Files.readAllLines(
                loadsFile,
                StandardCharsets.UTF_8);

        if (lines.isEmpty()) {

            throw new IOException(
                    "loads.csv is empty.");
        }

        String headerLine = lines.get(0);

        List<String> headers = parseCsvLine(
                headerLine);

        if (headers.isEmpty()) {

            throw new IOException(
                    "Invalid loads.csv header.");
        }

        int loadIdIndex = findHeaderIndex(
                headers,
                "loadid",
                "load_id",
                "loadno",
                "loadnumber");

        if (loadIdIndex < 0) {

            throw new IOException(
                    "LoadID column not found in loads.csv.");
        }

        String loadId = generateNextLoadId(
                lines,
                loadIdIndex);

        double enteredWeight = Double.parseDouble(
                weightField
                        .getText()
                        .trim());

        double weightTon = convertToTon(
                enteredWeight,
                unitCombo.getValue());

        double price = Double.parseDouble(
                offerPriceField
                        .getText()
                        .trim());

        LocalDateTime pickupDateTime = parseDateTime(
                pickupDatePicker,
                pickupTimeField);

        LocalDateTime deliveryDateTime = parseDateTime(
                deliveryDatePicker,
                deliveryTimeField);

        List<String> row = new ArrayList<>();

        for (String header : headers) {

            row.add(
                    getCsvValue(
                            normalizeHeader(header),
                            loadId,
                            weightTon,
                            price,
                            pickupDateTime,
                            deliveryDateTime,
                            status));
        }

        try (
                BufferedWriter writer = Files.newBufferedWriter(
                        loadsFile,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.APPEND)) {

            writer.newLine();

            writer.write(
                    buildCsvLine(row));
        }

        return loadId;
    }

    // =========================================================
    // CSV VALUE
    // =========================================================

    private String getCsvValue(
            String header,
            String loadId,
            double weightTon,
            double price,
            LocalDateTime pickupDateTime,
            LocalDateTime deliveryDateTime,
            String status) {

        String pickup = pickupField
                .getText()
                .trim();

        String destination = dropField
                .getText()
                .trim();

        String loadType = loadTypeCombo
                .getValue();

        String truckType = truckTypeCombo
                .getValue();

        switch (header) {

            case "loadid":
            case "load_id":
            case "loadno":
            case "loadnumber":

                return loadId;

            case "source":
            case "pickup":
            case "pickuplocation":
            case "origin":

                return pickup;

            case "destination":
            case "drop":
            case "droplocation":

                return destination;

            case "loadtype":
            case "load_type":

                return loadType == null
                        ? ""
                        : loadType;

            case "weightton":
            case "weight_ton":
            case "weighttons":

                return String.format(
                        Locale.US,
                        "%.2f",
                        weightTon);

            case "weight":
            case "weightkg":

                if (unitCombo
                        .getValue()
                        .equalsIgnoreCase("Kg")) {

                    return String.format(
                            Locale.US,
                            "%.2f",
                            Double.parseDouble(
                                    weightField
                                            .getText()
                                            .trim()));
                }

                return String.format(
                        Locale.US,
                        "%.2f",
                        weightTon);

            case "unit":
            case "weightunit":

                return unitCombo
                        .getValue();

            case "trucktype":
            case "truck_type":
            case "requiredtrucktype":
            case "vehicletype":
            case "vehicle_type":

                return truckType == null
                        ? ""
                        : truckType;

            case "offerprice":
            case "offer_price":
            case "price":

                return String.format(
                        Locale.US,
                        "%.2f",
                        price);

            case "status":

                return status;

            case "pickupdate":
            case "pickup_date":

                return pickupDateTime == null
                        ? ""
                        : pickupDateTime
                                .toLocalDate()
                                .toString();

            case "pickuptime":
            case "pickup_time":

                return pickupDateTime == null
                        ? ""
                        : pickupDateTime
                                .toLocalTime()
                                .format(
                                        TIME_FORMATTER);

            case "deliverydate":
            case "delivery_date":

                return deliveryDateTime == null
                        ? ""
                        : deliveryDateTime
                                .toLocalDate()
                                .toString();

            case "deliverytime":
            case "delivery_time":

                return deliveryDateTime == null
                        ? ""
                        : deliveryDateTime
                                .toLocalTime()
                                .format(
                                        TIME_FORMATTER);

            case "pickupdateandtime":
            case "pickup_datetime":

                return pickupDateTime == null
                        ? ""
                        : pickupDateTime
                                .format(
                                        DATE_TIME_FORMATTER);

            case "deliverydateandtime":
            case "delivery_datetime":

                return deliveryDateTime == null
                        ? ""
                        : deliveryDateTime
                                .format(
                                        DATE_TIME_FORMATTER);

            default:

                // Preserve unknown existing columns.
                return "";
        }
    }

    // =========================================================
    // GENERATE LOAD ID
    // =========================================================

    private String generateNextLoadId(
            List<String> lines,
            int loadIdIndex) {

        int highest = 0;

        Pattern pattern = Pattern.compile(
                "^L(\\d+)$",
                Pattern.CASE_INSENSITIVE);

        for (int i = 1; i < lines.size(); i++) {

            if (lines.get(i) == null
                    ||
                    lines.get(i)
                            .trim()
                            .isEmpty()) {

                continue;
            }

            List<String> values = parseCsvLine(
                    lines.get(i));

            if (loadIdIndex >= values.size()) {

                continue;
            }

            String id = values
                    .get(loadIdIndex)
                    .trim();

            Matcher matcher = pattern.matcher(id);

            if (matcher.matches()) {

                try {

                    int number = Integer.parseInt(
                            matcher.group(1));

                    if (number > highest) {

                        highest = number;
                    }

                } catch (NumberFormatException ignored) {
                }
            }
        }

        int next = highest + 1;

        String id;

        do {

            id = String.format(
                    Locale.US,
                    "L%03d",
                    next);

            next++;

        } while (loadIdExists(
                lines,
                loadIdIndex,
                id));

        return id;
    }

    // =========================================================
    // DUPLICATE CHECK
    // =========================================================

    private boolean loadIdExists(
            List<String> lines,
            int index,
            String loadId) {

        for (int i = 1; i < lines.size(); i++) {

            List<String> values = parseCsvLine(
                    lines.get(i));

            if (index < values.size()
                    &&
                    values.get(index)
                            .trim()
                            .equalsIgnoreCase(
                                    loadId)) {

                return true;
            }
        }

        return false;
    }

    // =========================================================
    // LOAD TRUCK TYPES
    // =========================================================

    private void loadTruckTypes() {

        Set<String> truckTypes = new LinkedHashSet<>();

        if (Files.exists(
                driversFile)) {

            try (
                    BufferedReader reader = Files.newBufferedReader(
                            driversFile,
                            StandardCharsets.UTF_8)) {

                String headerLine = reader.readLine();

                if (headerLine != null) {

                    List<String> headers = parseCsvLine(
                            headerLine);

                    int truckIndex = findHeaderIndex(
                            headers,
                            "trucktype",
                            "truck_type",
                            "vehicletype",
                            "vehicle_type",
                            "vehicle");

                    if (truckIndex >= 0) {

                        String line;

                        while ((line = reader.readLine()) != null) {

                            if (line.trim()
                                    .isEmpty()) {

                                continue;
                            }

                            List<String> values = parseCsvLine(
                                    line);

                            if (truckIndex < values.size()) {

                                String type = values
                                        .get(
                                                truckIndex)
                                        .trim();

                                if (!type.isEmpty()) {

                                    truckTypes.add(
                                            type);
                                }
                            }
                        }
                    }
                }

            } catch (IOException ignored) {
            }
        }

        // Fallback
        if (truckTypes.isEmpty()) {

            truckTypes.addAll(
                    Arrays.asList(
                            "LPT 2518 (10 Wheeler)",
                            "Heavy Truck",
                            "Container Truck",
                            "Tanker",
                            "Refrigerated Truck",
                            "Mini Truck"));
        }

        truckTypeCombo
                .getItems()
                .setAll(
                        truckTypes);
    }

    // =========================================================
    // HEADER SEARCH
    // =========================================================

    private int findHeaderIndex(
            List<String> headers,
            String... names) {

        for (int i = 0; i < headers.size(); i++) {

            String current = normalizeHeader(
                    headers.get(i));

            for (String name : names) {

                if (current.equals(
                        normalizeHeader(name))) {

                    return i;
                }
            }
        }

        return -1;
    }

    // =========================================================
    // NORMALIZE HEADER
    // =========================================================

    private String normalizeHeader(
            String header) {

        if (header == null) {

            return "";
        }

        return header
                .replace(
                        "\uFEFF",
                        "")
                .trim()
                .toLowerCase(
                        Locale.ROOT)
                .replace(
                        " ",
                        "")
                .replace(
                        "-",
                        "")
                .replace(
                        ".",
                        "");
    }

    // =========================================================
    // CSV PARSER
    // =========================================================

    private List<String> parseCsvLine(
            String line) {

        List<String> result = new ArrayList<>();

        if (line == null) {

            return result;
        }

        StringBuilder current = new StringBuilder();

        boolean quotes = false;

        for (int i = 0; i < line.length(); i++) {

            char c = line.charAt(i);

            if (c == '"') {

                if (quotes
                        &&
                        i + 1 < line.length()
                        &&
                        line.charAt(i + 1) == '"') {

                    current.append(
                            '"');

                    i++;

                } else {

                    quotes = !quotes;
                }

            } else if (c == ','
                    &&
                    !quotes) {

                result.add(
                        current.toString());

                current.setLength(
                        0);

            } else {

                current.append(
                        c);
            }
        }

        result.add(
                current.toString());

        return result;
    }

    // =========================================================
    // CSV BUILDER
    // =========================================================

    private String buildCsvLine(
            List<String> values) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < values.size(); i++) {

            if (i > 0) {

                result.append(",");
            }

            String value = values.get(i);

            if (value == null) {

                value = "";
            }

            if (value.contains(",")
                    ||
                    value.contains("\"")
                    ||
                    value.contains("\n")
                    ||
                    value.contains("\r")) {

                result.append("\"");

                result.append(
                        value.replace(
                                "\"",
                                "\"\""));

                result.append("\"");

            } else {

                result.append(
                        value);
            }
        }

        return result.toString();
    }

    // =========================================================
    // WEIGHT CONVERSION
    // =========================================================

    private double convertToTon(
            double value,
            String unit) {

        if (unit != null
                &&
                unit.equalsIgnoreCase(
                        "Kg")) {

            return value / 1000.0;
        }

        return value;
    }

    // =========================================================
    // CLEAR ERRORS
    // =========================================================

    private void clearErrors() {

        pickupError.setText("");
        dropError.setText("");
        loadTypeError.setText("");
        weightError.setText("");
        truckTypeError.setText("");
        offerPriceError.setText("");
        pickupDateError.setText("");
        deliveryDateError.setText("");
    }

    // =========================================================
    // CHECK ENTERED DATA
    // =========================================================

    private boolean hasEnteredData() {

        return !pickupField
                .getText()
                .trim()
                .isEmpty()

                ||

                !dropField
                        .getText()
                        .trim()
                        .isEmpty()

                ||

                loadTypeCombo
                        .getValue() != null

                ||

                !weightField
                        .getText()
                        .trim()
                        .equals("0.00")

                ||

                truckTypeCombo
                        .getValue() != null

                ||

                !offerPriceField
                        .getText()
                        .trim()
                        .isEmpty()

                ||

                pickupDatePicker
                        .getValue() != null

                ||

                deliveryDatePicker
                        .getValue() != null

                ||

                !pickupTimeField
                        .getText()
                        .trim()
                        .isEmpty()

                ||

                !deliveryTimeField
                        .getText()
                        .trim()
                        .isEmpty();
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================

    private void clearForm() {

        pickupField.clear();

        dropField.clear();

        loadTypeCombo
                .getSelectionModel()
                .clearSelection();

        weightField.setText(
                "0.00");

        unitCombo.setValue(
                "Ton");

        truckTypeCombo
                .getSelectionModel()
                .clearSelection();

        offerPriceField.clear();

        pickupDatePicker.setValue(
                null);

        deliveryDatePicker.setValue(
                null);

        pickupTimeField.clear();

        deliveryTimeField.clear();

        clearErrors();

        updateRouteOverview();
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert = new Alert(type);

        alert.setTitle(
                title);

        alert.setHeaderText(
                null);

        alert.setContentText(
                message);

        alert.showAndWait();
    }

    // =========================================================
    // CARD STYLE
    // =========================================================

    private String cardStyle() {

        return "-fx-background-color: "
                + CARD_BG + ";"
                + "-fx-background-radius: 16;"
                + "-fx-border-radius: 16;"
                + "-fx-border-color: #ECEFEC;"
                + "-fx-effect: dropshadow("
                + "gaussian,"
                + "rgba(0,0,0,0.07),"
                + "14,0,0,4);";
    }

    // =========================================================
    // ROUTE ESTIMATE CLASS
    // =========================================================

    private static class RouteEstimate {

        private final int distance;
        private final int hours;

        private RouteEstimate(
                int distance,
                int hours) {

            this.distance = distance;

            this.hours = hours;
        }
    }
}