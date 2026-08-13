package com.super_x.view.UserView;

import com.super_x.view.HomePage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class UserDashboard {

    // =========================================================
    // COLORS
    // =========================================================
    private static final String GREEN = "#087C2F";
    private static final String LIGHT_GREEN = "#E8F5EA";
    private static final String VERY_LIGHT_GREEN = "#F5FAF6";
    private static final String TEXT = "#26332C";
    private static final String MUTED = "#7B867F";
    private static final String WHITE = "#FFFFFF";
    private static final String CARD_BORDER = "#E0EAE3";


    // =========================================================
    // SCENE
    // =========================================================

    public Scene getTransporterDashboardScene() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + VERY_LIGHT_GREEN + ";"
        );


        // =====================================================
        // SIDEBAR
        // =====================================================

        root.setLeft(
                UserNavigation.createSidebar("Dashboard")
        );


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        BorderPane mainContent = new BorderPane();


        // =====================================================
        // NAVBAR
        // =====================================================

        mainContent.setTop(
                UserNavigation.createNavbar()
        );


        // =====================================================
        // DASHBOARD
        // =====================================================

        VBox dashboard = createDashboardContent();


        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setContent(dashboard);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background-color: " + VERY_LIGHT_GREEN + ";" +
                "-fx-border-color: transparent;"
        );


        mainContent.setCenter(scrollPane);

        root.setCenter(mainContent);


        // =====================================================
        // SCENE
        // =====================================================

        return new Scene(root, 1536, 750);
    }


    // =========================================================
    // DASHBOARD CONTENT
    // =========================================================

    private VBox createDashboardContent() {

        VBox content = new VBox(20);

        content.setPadding(
                new Insets(24, 28, 30, 28)
        );

        content.setFillWidth(true);

        content.setStyle(
                "-fx-background-color: " + VERY_LIGHT_GREEN + ";"
        );


        // =====================================================
        // STAT CARDS
        // =====================================================

        GridPane stats = new GridPane();

        stats.setHgap(16);
        stats.setVgap(16);

        for (int i = 0; i < 4; i++) {

            ColumnConstraints column =
                    new ColumnConstraints();

            column.setPercentWidth(25);
            column.setHgrow(Priority.ALWAYS);

            stats.getColumnConstraints().add(column);
        }


        stats.add(
                createStatCard(
                        "↗",
                        "Open Loads",
                        "03",
                        false
                ),
                0,
                0
        );


        stats.add(
                createStatCard(
                        "▣",
                        "Loads Posted",
                        "12",
                        false
                ),
                1,
                0
        );


        stats.add(
                createStatCard(
                        "⌁",
                        "In Progress",
                        "02",
                        false
                ),
                2,
                0
        );


        stats.add(
                createStatCard(
                        "₹",
                        "Monthly Revenue",
                        "₹1,22,000",
                        true
                ),
                3,
                0
        );


        // =====================================================
        // ACTION BUTTONS
        // =====================================================

        GridPane actions = new GridPane();

        actions.setHgap(14);

        for (int i = 0; i < 3; i++) {

            ColumnConstraints column =
                    new ColumnConstraints();

            column.setPercentWidth(33.3333);
            column.setHgrow(Priority.ALWAYS);

            actions.getColumnConstraints().add(column);
        }


        Button postLoad =
                createActionButton(
                        "⊕   Post New Load",
                        true
                );


        Button myLoads =
                createActionButton(
                        "▣   My Loads",
                        false
                );


        Button trackTrips =
                createActionButton(
                        "⌁   Track Trips",
                        false
                );


        // -----------------------------------------------------
        // ACTIONS
        // -----------------------------------------------------

        postLoad.setOnAction(event -> {

            Scene scene =
                    new UserPlaceholder(
                            "Post Load"
                    ).getScene();

            HomePage.homeStage.setScene(scene);
            HomePage.homeStage.show();
        });


        myLoads.setOnAction(event -> {

            Scene scene =
                    new MyLoads().getMyLoadsScene();

            HomePage.homeStage.setScene(scene);
            HomePage.homeStage.show();
        });


        trackTrips.setOnAction(event -> {

            Scene scene =
                    new UserPlaceholder(
                            "Trip Tracking"
                    ).getScene();

            HomePage.homeStage.setScene(scene);
            HomePage.homeStage.show();
        });


        actions.add(postLoad, 0, 0);
        actions.add(myLoads, 1, 0);
        actions.add(trackTrips, 2, 0);


        // =====================================================
        // RECENT LOADS + QUICK INSIGHTS
        // =====================================================

        GridPane middle = new GridPane();

        middle.setHgap(22);
        middle.setVgap(20);


        ColumnConstraints left =
                new ColumnConstraints();

        left.setPercentWidth(67);
        left.setHgrow(Priority.ALWAYS);


        ColumnConstraints right =
                new ColumnConstraints();

        right.setPercentWidth(33);
        right.setHgrow(Priority.ALWAYS);


        middle.getColumnConstraints().addAll(
                left,
                right
        );


        VBox recentLoads =
                createRecentLoads();


        VBox quickInsights =
                createQuickInsights();


        middle.add(
                recentLoads,
                0,
                0
        );


        middle.add(
                quickInsights,
                1,
                0
        );


        // =====================================================
        // ACTIVE TRIPS TITLE
        // =====================================================

        HBox activeTitle =
                new HBox();

        activeTitle.setAlignment(
                Pos.CENTER_LEFT
        );


        Label activeLabel =
                createLabel(
                        "Active Trips",
                        TEXT,
                        15,
                        true
                );


        Region activeSpacer =
                new Region();

        HBox.setHgrow(
                activeSpacer,
                Priority.ALWAYS
        );


        Label live =
                createLabel(
                        "●  LIVE MONITORING",
                        GREEN,
                        12,
                        true
                );


        activeTitle.getChildren().addAll(
                activeLabel,
                activeSpacer,
                live
        );


        // =====================================================
        // ACTIVE TRIPS
        // =====================================================

        GridPane trips = new GridPane();

        trips.setHgap(16);
        trips.setVgap(16);


        ColumnConstraints trip1 =
                new ColumnConstraints();

        trip1.setPercentWidth(50);
        trip1.setHgrow(Priority.ALWAYS);


        ColumnConstraints trip2 =
                new ColumnConstraints();

        trip2.setPercentWidth(50);
        trip2.setHgrow(Priority.ALWAYS);


        trips.getColumnConstraints().addAll(
                trip1,
                trip2
        );


        trips.add(
                createTripCard(
                        "Pune → Bangalore",
                        "MH-12-PQ-9980 | Arjun K.",
                        "In Transit",
                        "ETA: 6h 30m",
                        "Kolhapur",
                        "840 km remaining",
                        0.68
                ),
                0,
                0
        );


        trips.add(
                createTripCard(
                        "Chennai → Hyderabad",
                        "TN-07-AL-4521 | Rajesh M.",
                        "Loading",
                        "Departing in 40m",
                        "Origin",
                        "Documents pending",
                        0.15
                ),
                1,
                0
        );


        // =====================================================
        // EARNINGS
        // =====================================================

        VBox earnings =
                createEarningsCard();


        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        content.getChildren().addAll(
                stats,
                actions,
                middle,
                activeTitle,
                trips,
                earnings
        );


        return content;
    }


    // =========================================================
    // LABEL HELPER
    // =========================================================

    private Label createLabel(
            String text,
            String color,
            double size,
            boolean bold
    ) {

        Label label =
                new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        bold
                                ? FontWeight.BOLD
                                : FontWeight.NORMAL,
                        size
                )
        );

        label.setStyle(
                "-fx-text-fill: " + color + ";"
        );

        return label;
    }


    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox createStatCard(
            String icon,
            String title,
            String value,
            boolean darkIcon
    ) {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(18)
        );

        card.setMinHeight(100);
        card.setPrefHeight(100);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: " + CARD_BORDER + ";" +
                "-fx-border-radius: 18;"
        );


        HBox row =
                new HBox(14);

        row.setAlignment(
                Pos.CENTER_LEFT
        );


        StackPane iconBox =
                new StackPane();


        Circle circle =
                new Circle(22);


        circle.setFill(
                darkIcon
                        ? Color.web(GREEN)
                        : Color.web(LIGHT_GREEN)
        );


        Label iconLabel =
                new Label(icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );

        iconLabel.setStyle(
                "-fx-text-fill: " +
                        (darkIcon
                                ? WHITE
                                : GREEN) +
                        ";"
        );


        iconBox.getChildren().addAll(
                circle,
                iconLabel
        );


        VBox texts =
                new VBox(3);


        Label titleLabel =
                createLabel(
                        title,
                        TEXT,
                        13,
                        false
                );


        Label valueLabel =
                createLabel(
                        value,
                        TEXT,
                        16,
                        true
                );


        texts.getChildren().addAll(
                titleLabel,
                valueLabel
        );


        row.getChildren().addAll(
                iconBox,
                texts
        );


        card.getChildren().add(row);


        return card;
    }


    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private Button createActionButton(
            String text,
            boolean primary
    ) {

        Button button =
                new Button(text);


        button.setPrefHeight(54);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setCursor(
                Cursor.HAND
        );


        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );


        if (primary) {

            button.setStyle(
                    "-fx-background-color: " +
                            GREEN + ";" +
                    "-fx-background-radius: 27;" +
                    "-fx-text-fill: white;" +
                    "-fx-border-color: transparent;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-background-radius: 27;" +
                    "-fx-text-fill: " +
                            TEXT + ";" +
                    "-fx-border-color: #D7E1DA;" +
                    "-fx-border-radius: 27;" +
                    "-fx-border-width: 1;"
            );
        }


        // -----------------------------------------------------
        // HOVER
        // -----------------------------------------------------

        button.setOnMouseEntered(event -> {

            if (primary) {

                button.setStyle(
                        "-fx-background-color: #096F2B;" +
                        "-fx-background-radius: 27;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: transparent;"
                );

            } else {

                button.setStyle(
                        "-fx-background-color: #F0F7F1;" +
                        "-fx-background-radius: 27;" +
                        "-fx-text-fill: " +
                                TEXT + ";" +
                        "-fx-border-color: #B8D2BF;" +
                        "-fx-border-radius: 27;" +
                        "-fx-border-width: 1;"
                );
            }
        });


        button.setOnMouseExited(event -> {

            if (primary) {

                button.setStyle(
                        "-fx-background-color: " +
                                GREEN + ";" +
                        "-fx-background-radius: 27;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: transparent;"
                );

            } else {

                button.setStyle(
                        "-fx-background-color: white;" +
                        "-fx-background-radius: 27;" +
                        "-fx-text-fill: " +
                                TEXT + ";" +
                        "-fx-border-color: #D7E1DA;" +
                        "-fx-border-radius: 27;" +
                        "-fx-border-width: 1;"
                );
            }
        });


        return button;
    }


    // =========================================================
    // RECENT LOADS
    // =========================================================

    private VBox createRecentLoads() {

        VBox box =
                new VBox(12);


        Label title =
                createLabel(
                        "Recent Loads",
                        TEXT,
                        15,
                        true
                );


        VBox load1 =
                createLoadCard(
                        "Pune",
                        "Nashik",
                        "MH",
                        "7T Container",
                        "Steel Coils",
                        "8 Bids",
                        "₹9,200"
                );


        VBox load2 =
                createLoadCard(
                        "Mumbai",
                        "Surat",
                        "MH",
                        "19ft Open",
                        "Pharma",
                        "5 Bids",
                        "₹12,500"
                );


        box.getChildren().addAll(
                title,
                load1,
                load2
        );


        return box;
    }


    // =========================================================
    // LOAD CARD
    // =========================================================

    private VBox createLoadCard(
            String from,
            String to,
            String state,
            String vehicle,
            String material,
            String bids,
            String price
    ) {

        VBox card =
                new VBox(12);


        card.setPadding(
                new Insets(16, 18, 16, 18)
        );


        card.setMaxWidth(
                Double.MAX_VALUE
        );


        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 17;" +
                "-fx-border-color: #B7D4BF;" +
                "-fx-border-radius: 17;" +
                "-fx-border-width: 1.5;"
        );


        // =====================================================
        // ROUTE
        // =====================================================

        HBox route =
                new HBox(12);

        route.setAlignment(
                Pos.CENTER_LEFT
        );


        VBox fromBox =
                new VBox(2);


        Label fromLabel =
                createLabel(
                        from,
                        TEXT,
                        14,
                        true
                );


        Label fromState =
                createLabel(
                        state,
                        MUTED,
                        10,
                        false
                );


        fromBox.getChildren().addAll(
                fromLabel,
                fromState
        );


        Label arrow =
                createLabel(
                        "→",
                        GREEN,
                        22,
                        true
                );


        VBox toBox =
                new VBox(2);


        Label toLabel =
                createLabel(
                        to,
                        TEXT,
                        14,
                        true
                );


        Label toState =
                createLabel(
                        state,
                        MUTED,
                        10,
                        false
                );


        toBox.getChildren().addAll(
                toLabel,
                toState
        );


        Region routeSpacer =
                new Region();

        HBox.setHgrow(
                routeSpacer,
                Priority.ALWAYS
        );


        Label open =
                new Label("Open");

        open.setPadding(
                new Insets(7, 14, 7, 14)
        );

        open.setStyle(
                "-fx-background-color: #A7F2A7;" +
                "-fx-background-radius: 20;" +
                "-fx-text-fill: #087C2F;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12px;"
        );


        route.getChildren().addAll(
                fromBox,
                arrow,
                toBox,
                routeSpacer,
                open
        );


        Separator separator1 =
                new Separator();


        // =====================================================
        // DETAILS
        // =====================================================

        HBox details =
                new HBox(18);

        details.setAlignment(
                Pos.CENTER_LEFT
        );


        Label vehicleLabel =
                createLabel(
                        "▣  " + vehicle,
                        "#59655E",
                        12,
                        false
                );


        Label materialLabel =
                createLabel(
                        "♢  " + material,
                        "#59655E",
                        12,
                        false
                );


        Label bidsLabel =
                createLabel(
                        "⌁  " + bids,
                        "#59655E",
                        12,
                        false
                );


        Region priceSpacer =
                new Region();

        HBox.setHgrow(
                priceSpacer,
                Priority.ALWAYS
        );


        Label priceLabel =
                createLabel(
                        price,
                        GREEN,
                        16,
                        true
                );


        details.getChildren().addAll(
                vehicleLabel,
                materialLabel,
                bidsLabel,
                priceSpacer,
                priceLabel
        );


        Separator separator2 =
                new Separator();


        // =====================================================
        // BUTTONS
        // =====================================================

        HBox buttons =
                new HBox(12);


        Button detailsButton =
                new Button("View Details");


        detailsButton.setPrefHeight(40);

        detailsButton.setMaxWidth(
                Double.MAX_VALUE
        );

        HBox.setHgrow(
                detailsButton,
                Priority.ALWAYS
        );


        detailsButton.setStyle(
                "-fx-background-color: #FAFCFA;" +
                "-fx-background-radius: 20;" +
                "-fx-text-fill: #344039;" +
                "-fx-font-size: 13px;" +
                "-fx-border-color: #E0E8E2;" +
                "-fx-border-radius: 20;"
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        detailsButton.setOnAction(event -> {

            System.out.println(
                    "Viewing load: " +
                    from + " → " + to
            );

            Scene scene =
                    new UserPlaceholder(
                            "Load Details"
                    ).getScene();

            HomePage.homeStage.setScene(scene);
            HomePage.homeStage.show();
        });


        buttons.getChildren().add(
                detailsButton
        );


        // =====================================================
        // CARD CONTENT
        // =====================================================

        card.getChildren().addAll(
                route,
                separator1,
                details,
                separator2,
                buttons
        );


        return card;
    }


    // =========================================================
    // QUICK INSIGHTS
    // =========================================================

    private VBox createQuickInsights() {

        VBox box =
                new VBox(12);


        Label title =
                createLabel(
                        "Quick Insights",
                        TEXT,
                        15,
                        true
                );


        // =====================================================
        // TOP ROUTE
        // =====================================================

        VBox routeCard =
                new VBox(9);


        routeCard.setPadding(
                new Insets(18)
        );


        routeCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 17;" +
                "-fx-border-color: " +
                        CARD_BORDER + ";" +
                "-fx-border-radius: 17;"
        );


        Label routeTitle =
                createLabel(
                        "TOP PERFORMING ROUTE",
                        MUTED,
                        11,
                        true
                );


        HBox routeRow =
                new HBox();


        Label route =
                createLabel(
                        "Pune - Mumbai",
                        TEXT,
                        13,
                        true
                );


        Region routeSpacer =
                new Region();

        HBox.setHgrow(
                routeSpacer,
                Priority.ALWAYS
        );


        Label percentage =
                createLabel(
                        "+12%",
                        GREEN,
                        13,
                        true
                );


        routeRow.getChildren().addAll(
                route,
                routeSpacer,
                percentage
        );


        routeCard.getChildren().addAll(
                routeTitle,
                routeRow
        );


        // =====================================================
        // BEST DRIVER
        // =====================================================

        VBox driverCard =
                new VBox(10);


        driverCard.setPadding(
                new Insets(18)
        );


        driverCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 17;" +
                "-fx-border-color: " +
                        CARD_BORDER + ";" +
                "-fx-border-radius: 17;"
        );


        Label driverTitle =
                createLabel(
                        "BEST DRIVER",
                        MUTED,
                        11,
                        true
                );


        HBox driverRow =
                new HBox(10);

        driverRow.setAlignment(
                Pos.CENTER_LEFT
        );


        StackPane avatar =
                new StackPane();


        Circle circle =
                new Circle(
                        18,
                        Color.web("#DDEBDD")
                );


        Label initials =
                createLabel(
                        "AK",
                        GREEN,
                        11,
                        true
                );


        avatar.getChildren().addAll(
                circle,
                initials
        );


        Label driver =
                createLabel(
                        "Arjun K. (4.9★)",
                        TEXT,
                        13,
                        true
                );


        driverRow.getChildren().addAll(
                avatar,
                driver
        );


        driverCard.getChildren().addAll(
                driverTitle,
                driverRow
        );


        // =====================================================
        // RECENT ACTIVITY
        // =====================================================

        Label activityTitle =
                createLabel(
                        "Recent Activity",
                        TEXT,
                        13,
                        true
                );


        VBox activity =
                new VBox(14);


        activity.getChildren().addAll(

                createActivity(
                        "Load Posted: Pune to Nashik",
                        "10 mins ago",
                        true
                ),

                createActivity(
                        "Bid Received: ₹8,900 from V. Travels",
                        "45 mins ago",
                        true
                ),

                createActivity(
                        "Trip Completed: Mumbai - Surat",
                        "2 hours ago",
                        false
                )
        );


        box.getChildren().addAll(
                title,
                routeCard,
                driverCard,
                activityTitle,
                activity
        );


        return box;
    }


    // =========================================================
    // ACTIVITY
    // =========================================================

    private HBox createActivity(
            String text,
            String time,
            boolean green
    ) {

        HBox row =
                new HBox(10);

        row.setAlignment(
                Pos.TOP_LEFT
        );


        Circle dot =
                new Circle(
                        5,
                        Color.web(
                                green
                                        ? GREEN
                                        : "#68736D"
                        )
                );


        VBox texts =
                new VBox(3);


        Label main =
                createLabel(
                        text,
                        TEXT,
                        12,
                        false
                );

        main.setWrapText(true);


        Label small =
                createLabel(
                        time,
                        MUTED,
                        10,
                        false
                );


        texts.getChildren().addAll(
                main,
                small
        );


        row.getChildren().addAll(
                dot,
                texts
        );


        return row;
    }


    // =========================================================
    // ACTIVE TRIP CARD
    // =========================================================

    private VBox createTripCard(
            String route,
            String vehicle,
            String status,
            String eta,
            String location,
            String remaining,
            double progress
    ) {

        VBox card =
                new VBox(10);


        card.setPadding(
                new Insets(18, 20, 18, 20)
        );


        card.setMaxWidth(
                Double.MAX_VALUE
        );


        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: " +
                        CARD_BORDER + ";" +
                "-fx-border-radius: 18;"
        );


        // =====================================================
        // TOP
        // =====================================================

        HBox top =
                new HBox();


        VBox routeBox =
                new VBox(4);


        Label routeLabel =
                createLabel(
                        route,
                        TEXT,
                        14,
                        true
                );


        Label vehicleLabel =
                createLabel(
                        vehicle,
                        MUTED,
                        11,
                        false
                );


        routeBox.getChildren().addAll(
                routeLabel,
                vehicleLabel
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        VBox statusBox =
                new VBox(3);

        statusBox.setAlignment(
                Pos.CENTER_RIGHT
        );


        Label statusLabel =
                createLabel(
                        status,
                        GREEN,
                        11,
                        true
                );


        Label etaLabel =
                createLabel(
                        eta,
                        MUTED,
                        9,
                        false
                );


        statusBox.getChildren().addAll(
                statusLabel,
                etaLabel
        );


        top.getChildren().addAll(
                routeBox,
                spacer,
                statusBox
        );


        // =====================================================
        // PROGRESS
        // =====================================================

        ProgressBar progressBar =
                new ProgressBar(progress);


        progressBar.setMaxWidth(
                Double.MAX_VALUE
        );


        progressBar.setPrefHeight(8);


        progressBar.setStyle(
                "-fx-accent: " + GREEN + ";"
        );


        // =====================================================
        // BOTTOM
        // =====================================================

        HBox bottom =
                new HBox();


        Label locationLabel =
                createLabel(
                        location,
                        TEXT,
                        12,
                        true
                );


        Region bottomSpacer =
                new Region();

        HBox.setHgrow(
                bottomSpacer,
                Priority.ALWAYS
        );


        Label remainingLabel =
                createLabel(
                        remaining,
                        MUTED,
                        11,
                        false
                );


        bottom.getChildren().addAll(
                locationLabel,
                bottomSpacer,
                remainingLabel
        );


        card.getChildren().addAll(
                top,
                progressBar,
                bottom
        );


        return card;
    }


    // =========================================================
    // EARNINGS
    // =========================================================

    private VBox createEarningsCard() {

        VBox card =
                new VBox(12);


        card.setPadding(
                new Insets(20)
        );


        card.setMinHeight(270);


        card.setMaxWidth(
                Double.MAX_VALUE
        );


        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: " +
                        CARD_BORDER + ";" +
                "-fx-border-radius: 20;"
        );


        // =====================================================
        // HEADER
        // =====================================================

        HBox header =
                new HBox();


        VBox titleBox =
                new VBox(5);


        Label title =
                createLabel(
                        "Earnings Overview",
                        TEXT,
                        14,
                        true
                );


        Label subtitle =
                createLabel(
                        "Financial performance vs. logistics efficiency",
                        MUTED,
                        11,
                        false
                );


        titleBox.getChildren().addAll(
                title,
                subtitle
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        HBox legend =
                new HBox(14);

        legend.setAlignment(
                Pos.CENTER_RIGHT
        );


        Label completed =
                createLabel(
                        "● Completed",
                        GREEN,
                        12,
                        false
                );


        Label cancelled =
                createLabel(
                        "● Cancelled",
                        "#B82C2C",
                        12,
                        false
                );


        ComboBox<String> filter =
                new ComboBox<>();


        filter.getItems().addAll(
                "Last 6 Months",
                "Last 12 Months",
                "This Year"
        );


        filter.setValue(
                "Last 6 Months"
        );


        filter.setPrefHeight(32);


        legend.getChildren().addAll(
                completed,
                cancelled,
                filter
        );


        header.getChildren().addAll(
                titleBox,
                spacer,
                legend
        );


        // =====================================================
        // CHART
        // =====================================================

        StackPane chartArea =
                new StackPane();


        chartArea.setMinHeight(170);
        chartArea.setPrefHeight(170);


        VBox chartBox =
                new VBox();


        chartBox.setSpacing(5);


        // Grid lines

        for (int i = 1; i <= 4; i++) {

            Region line =
                    new Region();

            line.setPrefHeight(1);

            line.setMaxWidth(
                    Double.MAX_VALUE
            );

            line.setStyle(
                    "-fx-background-color: #E8EEE9;"
            );

            chartBox.getChildren().add(line);
        }


        // Simple responsive line

        Polyline polyline =
                new Polyline();


        polyline.setStroke(
                Color.web(GREEN)
        );

        polyline.setStrokeWidth(3);

        polyline.setFill(
                Color.TRANSPARENT
        );


        polyline.getPoints().addAll(

                10.0, 125.0,

                100.0, 100.0,

                190.0, 115.0,

                280.0, 75.0,

                370.0, 95.0,

                460.0, 45.0,

                550.0, 65.0,

                640.0, 30.0
        );


        chartArea.getChildren().add(
                polyline
        );


        // =====================================================
        // MONTHS
        // =====================================================

        HBox months =
                new HBox();


        String[] monthNames = {

                "JAN",
                "FEB",
                "MAR",
                "APR",
                "MAY",
                "JUN"
        };


        for (String monthName :
                monthNames) {

            Label monthLabel =
                    createLabel(
                            monthName,
                            monthName.equals("MAY")
                                    ? GREEN
                                    : MUTED,
                            11,
                            monthName.equals("MAY")
                    );


            HBox.setHgrow(
                    monthLabel,
                    Priority.ALWAYS
            );


            monthLabel.setAlignment(
                    Pos.CENTER
            );


            months.getChildren().add(
                    monthLabel
            );
        }


        // =====================================================
        // ADD
        // =====================================================

        card.getChildren().addAll(
                header,
                chartArea,
                months
        );


        return card;
    }
}