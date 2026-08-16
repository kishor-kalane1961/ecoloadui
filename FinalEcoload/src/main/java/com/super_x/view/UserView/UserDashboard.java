package com.super_x.view.UserView;

import com.super_x.view.HomePage;

import com.super_x.model.usermodel.UserModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UserDashboard {

        private UserModel user;

        public UserDashboard(UserModel user) {
                this.user = user;
        }
        public UserDashboard() {
                
        }

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
                "-fx-background-color: " + VERY_LIGHT_GREEN + ";");

        // =====================================================
        // SIDEBAR
        // =====================================================

        root.setLeft(
                UserNavigation.createSidebar("Dashboard"));

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        BorderPane mainContent = new BorderPane();

        // =====================================================
        // NAVBAR
        // =====================================================

        mainContent.setTop(
                UserNavigation.createNavbar());

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
                ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background-color: " + VERY_LIGHT_GREEN + ";" +
                        "-fx-border-color: transparent;");

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
                new Insets(24, 28, 30, 28));

        content.setFillWidth(true);

        content.setStyle(
                "-fx-background-color: " + VERY_LIGHT_GREEN + ";");

        // =====================================================
        // STAT CARDS
        // =====================================================

        GridPane stats = new GridPane();

        stats.setHgap(18);
        stats.setVgap(16);

        for (int i = 0; i < 3; i++) {

            ColumnConstraints column = new ColumnConstraints();

            column.setPercentWidth(33.33);
            column.setHgrow(Priority.ALWAYS);

            stats.getColumnConstraints().add(column);
        }

        stats.add(
                createStatCard(
                        "↗",
                        "Open Loads",
                        "03",
                        false),
                0,
                0);

        stats.add(
                createStatCard(
                        "▣",
                        "Loads Posted",
                        "12",
                        false),
                1,
                0);

        stats.add(
                createStatCard(
                        "⌁",
                        "In Progress",
                        "02",
                        false),
                2,
                0);

        // =====================================================
        // ACTION BUTTONS
        // =====================================================

        GridPane actions = new GridPane();

        actions.setHgap(14);

        for (int i = 0; i < 3; i++) {

            ColumnConstraints column = new ColumnConstraints();

            column.setPercentWidth(33.3333);
            column.setHgrow(Priority.ALWAYS);

            actions.getColumnConstraints().add(column);
        }

        Button postLoad = createActionButton(
                "⊕   Post New Load",
                true);

        Button myLoads = createActionButton(
                "▣   My Loads",
                false);

        Button trackTrips = createActionButton(
                "⌁   Track Trips",
                false);

        // -----------------------------------------------------
        // ACTIONS
        // -----------------------------------------------------

        postLoad.setOnAction(event -> {

            Scene scene = new PostLoad().getpostloadScene();

            HomePage.homeStage.setScene(scene);
            HomePage.homeStage.show();
        });

        myLoads.setOnAction(event -> {

            Scene scene = new MyLoads().getMyLoadsScene();

            HomePage.homeStage.setScene(scene);
            HomePage.homeStage.show();
        });

        trackTrips.setOnAction(event -> {

            Scene scene = new UserPlaceholder(
                    "Trip Tracking").getScene();

            HomePage.homeStage.setScene(scene);
            HomePage.homeStage.show();
        });

        actions.add(
                postLoad,
                0,
                0);

        actions.add(
                myLoads,
                1,
                0);

        actions.add(
                trackTrips,
                2,
                0);

        // =====================================================
        // RECENT LOADS + QUICK INSIGHTS
        // =====================================================

        GridPane middle = new GridPane();

        middle.setHgap(22);
        middle.setVgap(20);

        ColumnConstraints left = new ColumnConstraints();

        left.setPercentWidth(67);
        left.setHgrow(Priority.ALWAYS);

        ColumnConstraints right = new ColumnConstraints();

        right.setPercentWidth(33);
        right.setHgrow(Priority.ALWAYS);

        middle.getColumnConstraints().addAll(
                left,
                right);

        VBox recentLoads = createRecentLoads();

        VBox quickInsights = createQuickInsights();

        middle.add(
                recentLoads,
                0,
                0);

        middle.add(
                quickInsights,
                1,
                0);

        // =====================================================
        // ACTIVE TRIPS TITLE
        // =====================================================

        HBox activeTitle = new HBox();

        activeTitle.setAlignment(
                Pos.CENTER_LEFT);

        Label activeLabel = createLabel(
                "Active Trips",
                TEXT,
                15,
                true);

        Region activeSpacer = new Region();

        HBox.setHgrow(
                activeSpacer,
                Priority.ALWAYS);

        Label live = createLabel(
                "●  LIVE MONITORING",
                GREEN,
                12,
                true);

        activeTitle.getChildren().addAll(
                activeLabel,
                activeSpacer,
                live);

        // =====================================================
        // ACTIVE TRIPS
        // =====================================================

        GridPane trips = new GridPane();

        trips.setHgap(16);
        trips.setVgap(16);

        ColumnConstraints trip1 = new ColumnConstraints();

        trip1.setPercentWidth(50);
        trip1.setHgrow(Priority.ALWAYS);

        ColumnConstraints trip2 = new ColumnConstraints();

        trip2.setPercentWidth(50);
        trip2.setHgrow(Priority.ALWAYS);

        trips.getColumnConstraints().addAll(
                trip1,
                trip2);

        trips.add(
                createTripCard(
                        "Pune → Bangalore",
                        "MH-12-PQ-9980 | Arjun K.",
                        "In Transit",
                        "ETA: 6h 30m",
                        "Kolhapur",
                        "840 km remaining",
                        0.68),
                0,
                0);

        trips.add(
                createTripCard(
                        "Chennai → Hyderabad",
                        "TN-07-AL-4521 | Rajesh M.",
                        "Loading",
                        "Departing in 40m",
                        "Origin",
                        "Documents pending",
                        0.15),
                1,
                0);

        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        content.getChildren().addAll(
                stats,
                actions,
                middle,
                activeTitle,
                trips);

        return content;
    }

    // =========================================================
    // LABEL HELPER
    // =========================================================

    private Label createLabel(
            String text,
            String color,
            double size,
            boolean bold) {

        Label label = new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        bold
                                ? FontWeight.BOLD
                                : FontWeight.NORMAL,
                        size));

        label.setStyle(
                "-fx-text-fill: " + color + ";");

        return label;
    }

    // =========================================================
    // CARD HOVER EFFECT
    // =========================================================

    private void addCardHoverEffect(
            Region card,
            String normalBorder,
            String radius) {

        card.setOnMouseEntered(event -> {

            card.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-background-radius: " + radius + ";" +
                            "-fx-border-color: " + GREEN + ";" +
                            "-fx-border-radius: " + radius + ";" +
                            "-fx-border-width: 1.5;");
        });

        card.setOnMouseExited(event -> {

            card.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-background-radius: " + radius + ";" +
                            "-fx-border-color: " + normalBorder + ";" +
                            "-fx-border-radius: " + radius + ";" +
                            "-fx-border-width: 1;");
        });
    }

    // =========================================================
    // STAT CARD
    // =========================================================
    private VBox createStatCard(
            String icon,
            String title,
            String value,
            boolean darkIcon) {

        VBox card = new VBox(10);

        card.setPadding(
                new Insets(20));

        card.setMinHeight(120);
        card.setPrefHeight(120);

        card.setMaxWidth(
                Double.MAX_VALUE);

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 18;" +
                        "-fx-border-color: " + CARD_BORDER + ";" +
                        "-fx-border-radius: 18;" +
                        "-fx-border-width: 1;");

        // =====================================================
        // ROW
        // =====================================================

        HBox row = new HBox(18);

        row.setAlignment(
                Pos.CENTER_LEFT);

        // =====================================================
        // ICON
        // =====================================================

        StackPane iconBox = new StackPane();

        Circle circle = new Circle(25);

        circle.setFill(
                darkIcon
                        ? Color.web(GREEN)
                        : Color.web(LIGHT_GREEN));

        Label iconLabel = new Label(icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19));

        iconLabel.setStyle(
                "-fx-text-fill: " +
                        (darkIcon
                                ? WHITE
                                : GREEN)
                        +
                        ";");

        iconBox.getChildren().addAll(
                circle,
                iconLabel);

        // =====================================================
        // TEXT
        // =====================================================

        VBox texts = new VBox(4);

        Label titleLabel = createLabel(
                title,
                TEXT,
                15,
                false);

        Label valueLabel = createLabel(
                value,
                TEXT,
                21,
                true);

        texts.getChildren().addAll(
                titleLabel,
                valueLabel);

        row.getChildren().addAll(
                iconBox,
                texts);

        card.getChildren().add(
                row);

        // =====================================================
        // HOVER
        // =====================================================

        addCardHoverEffect(
                card,
                CARD_BORDER,
                "18");

        return card;
    }
    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private Button createActionButton(
            String text,
            boolean primary) {

        Button button = new Button(text);

        button.setPrefHeight(54);

        button.setMaxWidth(
                Double.MAX_VALUE);

        button.setCursor(
                Cursor.HAND);

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14));

        if (primary) {

            button.setStyle(
                    "-fx-background-color: " +
                            GREEN + ";" +
                            "-fx-background-radius: 27;" +
                            "-fx-text-fill: white;" +
                            "-fx-border-color: transparent;");

        } else {

            button.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-background-radius: 27;" +
                            "-fx-text-fill: " +
                            TEXT + ";" +
                            "-fx-border-color: #D7E1DA;" +
                            "-fx-border-radius: 27;" +
                            "-fx-border-width: 1;");
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
                                "-fx-border-color: transparent;");

            } else {

                button.setStyle(
                        "-fx-background-color: #F0F7F1;" +
                                "-fx-background-radius: 27;" +
                                "-fx-text-fill: " +
                                TEXT + ";" +
                                "-fx-border-color: #B8D2BF;" +
                                "-fx-border-radius: 27;" +
                                "-fx-border-width: 1;");
            }
        });

        button.setOnMouseExited(event -> {

            if (primary) {

                button.setStyle(
                        "-fx-background-color: " +
                                GREEN + ";" +
                                "-fx-background-radius: 27;" +
                                "-fx-text-fill: white;" +
                                "-fx-border-color: transparent;");

            } else {

                button.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-background-radius: 27;" +
                                "-fx-text-fill: " +
                                TEXT + ";" +
                                "-fx-border-color: #D7E1DA;" +
                                "-fx-border-radius: 27;" +
                                "-fx-border-width: 1;");
            }
        });

        return button;
    }

    // =========================================================
    // RECENT LOADS
    // =========================================================

    private VBox createRecentLoads() {

        VBox box = new VBox(12);

        Label title = createLabel(
                "Recent Loads",
                TEXT,
                18,
                true);

        VBox load1 = createLoadCard(
                "Pune",
                "Nashik",
                "MH",
                "7T Container",
                "Steel Coils",
                "₹9,200");

        VBox load2 = createLoadCard(
                "Mumbai",
                "Surat",
                "MH",
                "19ft Open",
                "Pharma",
                "₹12,500");

        box.getChildren().addAll(
                title,
                load1,
                load2);

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
            String price) {

        VBox card = new VBox(12);

        card.setPadding(
                new Insets(
                        16,
                        18,
                        16,
                        18));

        card.setMaxWidth(
                Double.MAX_VALUE);

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 17;" +
                        "-fx-border-color: #B7D4BF;" +
                        "-fx-border-radius: 17;" +
                        "-fx-border-width: 1.5;");

        // =====================================================
        // ROUTE
        // =====================================================

        HBox route = new HBox(12);

        route.setAlignment(
                Pos.CENTER_LEFT);

        VBox fromBox = new VBox(2);

        Label fromLabel = createLabel(
                from,
                TEXT,
                16,
                true);

        Label fromState = createLabel(
                state,
                MUTED,
                12,
                false);

        fromBox.getChildren().addAll(
                fromLabel,
                fromState);

        Label arrow = createLabel(
                "→",
                GREEN,
                24,
                true);

        VBox toBox = new VBox(2);

        Label toLabel = createLabel(
                to,
                TEXT,
                16,
                true);

        Label toState = createLabel(
                state,
                MUTED,
                12,
                false);

        toBox.getChildren().addAll(
                toLabel,
                toState);

        Region routeSpacer = new Region();

        HBox.setHgrow(
                routeSpacer,
                Priority.ALWAYS);

        Label open = new Label("Open");

        open.setPadding(
                new Insets(
                        7,
                        14,
                        7,
                        14));

        open.setStyle(
                "-fx-background-color: #A7F2A7;" +
                        "-fx-background-radius: 20;" +
                        "-fx-text-fill: #087C2F;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 12px;");

        route.getChildren().addAll(
                fromBox,
                arrow,
                toBox,
                routeSpacer,
                open);

        Separator separator1 = new Separator();

        // =====================================================
        // DETAILS
        // =====================================================

        HBox details = new HBox(18);

        details.setAlignment(
                Pos.CENTER_LEFT);

        Label vehicleLabel = createLabel(
                "▣  " + vehicle,
                "#59655E",
                12,
                false);

        Label materialLabel = createLabel(
                "♢  " + material,
                "#59655E",
                12,
                false);

        Region priceSpacer = new Region();

        HBox.setHgrow(
                priceSpacer,
                Priority.ALWAYS);

        Label priceLabel = createLabel(
                price,
                GREEN,
                16,
                true);

        details.getChildren().addAll(
                vehicleLabel,
                materialLabel,
                priceSpacer,
                priceLabel);

        Separator separator2 = new Separator();

        // =====================================================
        // BUTTONS
        // =====================================================

        HBox buttons = new HBox(12);

        Button detailsButton = new Button("View Details");

        detailsButton.setPrefHeight(40);

        detailsButton.setMaxWidth(
                Double.MAX_VALUE);

        HBox.setHgrow(
                detailsButton,
                Priority.ALWAYS);

        detailsButton.setCursor(
                Cursor.HAND);

        detailsButton.setStyle(
                "-fx-background-color: #FAFCFA;" +
                        "-fx-background-radius: 20;" +
                        "-fx-text-fill: #344039;" +
                        "-fx-font-size: 13px;" +
                        "-fx-border-color: #E0E8E2;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-width: 1;");

        // =====================================================
        // VIEW DETAILS BUTTON HOVER
        // =====================================================

        detailsButton.setOnMouseEntered(event -> {

            detailsButton.setStyle(
                    "-fx-background-color: #F0F7F1;" +
                            "-fx-background-radius: 20;" +
                            "-fx-text-fill: " + GREEN + ";" +
                            "-fx-font-size: 13px;" +
                            "-fx-border-color: " + GREEN + ";" +
                            "-fx-border-radius: 20;" +
                            "-fx-border-width: 1.5;");
        });

        detailsButton.setOnMouseExited(event -> {

            detailsButton.setStyle(
                    "-fx-background-color: #FAFCFA;" +
                            "-fx-background-radius: 20;" +
                            "-fx-text-fill: #344039;" +
                            "-fx-font-size: 13px;" +
                            "-fx-border-color: #E0E8E2;" +
                            "-fx-border-radius: 20;" +
                            "-fx-border-width: 1;");
        });

        // =====================================================
        // VIEW DETAILS ACTION
        // =====================================================

        detailsButton.setOnAction(event -> {

            System.out.println(
                    "Viewing load: " +
                            from +
                            " → " +
                            to);

            showLoadDetailsDialog(
                    from,
                    to,
                    state,
                    vehicle,
                    material,
                    price);
        });

        buttons.getChildren().add(
                detailsButton);

        // =====================================================
        // CARD CONTENT
        // =====================================================

        card.getChildren().addAll(
                route,
                separator1,
                details,
                separator2,
                buttons);

        // Green border when hovering entire load card
        addCardHoverEffect(
                card,
                "#B7D4BF",
                "17");

        return card;
    }

    // =========================================================
    // VIEW LOAD DETAILS DIALOG
    // =========================================================

    private void showLoadDetailsDialog(
            String from,
            String to,
            String state,
            String vehicle,
            String material,
            String price) {

        Dialog<Void> dialog = new Dialog<>();

        dialog.setTitle(
                "View Load Details");

        DialogPane dialogPane = dialog.getDialogPane();

        dialogPane.setPrefWidth(560);
        dialogPane.setPrefHeight(470);

        dialogPane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: " + GREEN + ";" +
                        "-fx-border-width: 1.5;" +
                        "-fx-border-radius: 18;" +
                        "-fx-background-radius: 18;");

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox content = new VBox(18);

        content.setPadding(
                new Insets(24));

        // =====================================================
        // TITLE
        // =====================================================

        Label title = createLabel(
                "View Load Details",
                TEXT,
                20,
                true);

        Label subtitle = createLabel(
                "Complete information about this load",
                MUTED,
                12,
                false);

        VBox heading = new VBox(4);

        heading.getChildren().addAll(
                title,
                subtitle);

        // =====================================================
        // ROUTE CARD
        // =====================================================

        VBox routeBox = new VBox(8);

        routeBox.setPadding(
                new Insets(16));

        routeBox.setStyle(
                "-fx-background-color: " +
                        VERY_LIGHT_GREEN + ";" +
                        "-fx-background-radius: 14;" +
                        "-fx-border-color: " +
                        CARD_BORDER + ";" +
                        "-fx-border-radius: 14;" +
                        "-fx-border-width: 1;");

        Label routeTitle = createLabel(
                "ROUTE",
                MUTED,
                10,
                true);

        Label routeLabel = createLabel(
                from + "  →  " + to,
                TEXT,
                17,
                true);

        Label routeState = createLabel(
                state,
                GREEN,
                11,
                true);

        routeBox.getChildren().addAll(
                routeTitle,
                routeLabel,
                routeState);

        // =====================================================
        // ROUTE BOX HOVER
        // =====================================================

        routeBox.setOnMouseEntered(event -> {

            routeBox.setStyle(
                    "-fx-background-color: #EDF8EF;" +
                            "-fx-background-radius: 14;" +
                            "-fx-border-color: " + GREEN + ";" +
                            "-fx-border-radius: 14;" +
                            "-fx-border-width: 1.5;");
        });

        routeBox.setOnMouseExited(event -> {

            routeBox.setStyle(
                    "-fx-background-color: " +
                            VERY_LIGHT_GREEN + ";" +
                            "-fx-background-radius: 14;" +
                            "-fx-border-color: " +
                            CARD_BORDER + ";" +
                            "-fx-border-radius: 14;" +
                            "-fx-border-width: 1;");
        });

        // =====================================================
        // DETAILS GRID
        // =====================================================

        GridPane detailsGrid = new GridPane();

        detailsGrid.setHgap(14);
        detailsGrid.setVgap(14);

        detailsGrid.setPadding(
                new Insets(4, 0, 4, 0));

        addDetailRow(
                detailsGrid,
                "Vehicle",
                vehicle,
                0,
                0);

        addDetailRow(
                detailsGrid,
                "Material",
                material,
                1,
                0);

        addDetailRow(
                detailsGrid,
                "Load Status",
                state,
                0,
                1);

        addDetailRow(
                detailsGrid,
                "Freight Price",
                price,
                1,
                1);

        // =====================================================
        // PRICE SECTION
        // =====================================================

        HBox priceBox = new HBox();

        priceBox.setAlignment(
                Pos.CENTER_RIGHT);

        Label priceTitle = createLabel(
                "TOTAL FREIGHT",
                MUTED,
                10,
                true);

        Label priceValue = createLabel(
                price,
                GREEN,
                22,
                true);

        VBox priceContent = new VBox(2);

        priceContent.setAlignment(
                Pos.CENTER_RIGHT);

        priceContent.getChildren().addAll(
                priceTitle,
                priceValue);

        priceBox.getChildren().add(
                priceContent);

        // =====================================================
        // ADD CONTENT
        // =====================================================

        content.getChildren().addAll(
                heading,
                routeBox,
                detailsGrid,
                priceBox);

        dialogPane.setContent(
                content);

        // =====================================================
        // CLOSE BUTTON
        // =====================================================

        dialogPane.getButtonTypes().add(
                ButtonType.CLOSE);

        Button closeButton = (Button) dialogPane.lookupButton(
                ButtonType.CLOSE);

        closeButton.setCursor(
                Cursor.HAND);

        closeButton.setStyle(
                "-fx-background-color: " + GREEN + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 8 24 8 24;");

        // =====================================================
        // CLOSE BUTTON HOVER
        // =====================================================

        closeButton.setOnMouseEntered(event -> {

            closeButton.setStyle(
                    "-fx-background-color: #096F2B;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 20;" +
                            "-fx-padding: 8 24 8 24;");
        });

        closeButton.setOnMouseExited(event -> {

            closeButton.setStyle(
                    "-fx-background-color: " + GREEN + ";" +
                            "-fx-text-fill: white;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 20;" +
                            "-fx-padding: 8 24 8 24;");
        });

        // =====================================================
        // SHOW DIALOG
        // =====================================================

        dialog.showAndWait();
    }

    // =========================================================
    // DETAIL ROW
    // =========================================================

    private void addDetailRow(
            GridPane grid,
            String labelText,
            String valueText,
            int column,
            int row) {

        VBox box = new VBox(5);

        box.setPadding(
                new Insets(12));

        box.setMinWidth(225);
        box.setPrefWidth(225);

        box.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-color: " + CARD_BORDER + ";" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-width: 1;");

        Label label = createLabel(
                labelText.toUpperCase(),
                MUTED,
                10,
                true);

        Label value = createLabel(
                valueText,
                TEXT,
                13,
                true);

        box.getChildren().addAll(
                label,
                value);

        // =====================================================
        // DETAIL BOX HOVER
        // =====================================================

        box.setOnMouseEntered(event -> {

            box.setStyle(
                    "-fx-background-color: " +
                            VERY_LIGHT_GREEN + ";" +
                            "-fx-background-radius: 12;" +
                            "-fx-border-color: " + GREEN + ";" +
                            "-fx-border-radius: 12;" +
                            "-fx-border-width: 1.5;");
        });

        box.setOnMouseExited(event -> {

            box.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-background-radius: 12;" +
                            "-fx-border-color: " + CARD_BORDER + ";" +
                            "-fx-border-radius: 12;" +
                            "-fx-border-width: 1;");
        });

        grid.add(
                box,
                column,
                row);
    }

    // =========================================================
    // QUICK INSIGHTS
    // =========================================================

    private VBox createQuickInsights() {

        VBox box = new VBox(12);

        Label title = createLabel(
                "Quick Insights",
                TEXT,
                18,
                true);

        // =====================================================
        // TOP ROUTE
        // =====================================================

        VBox routeCard = new VBox(9);

        routeCard.setPadding(
                new Insets(18));

        routeCard.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 17;" +
                        "-fx-border-color: " +
                        CARD_BORDER + ";" +
                        "-fx-border-radius: 17;" +
                        "-fx-border-width: 1;");

        Label routeTitle = createLabel(
                "TOP PERFORMING ROUTE",
                MUTED,
                11,
                true);

        HBox routeRow = new HBox();

        Label route = createLabel(
                "Pune - Mumbai",
                TEXT,
                13,
                true);

        Region routeSpacer = new Region();

        HBox.setHgrow(
                routeSpacer,
                Priority.ALWAYS);

        Label percentage = createLabel(
                "+12%",
                GREEN,
                13,
                true);

        routeRow.getChildren().addAll(
                route,
                routeSpacer,
                percentage);

        routeCard.getChildren().addAll(
                routeTitle,
                routeRow);

        // Green hover border
        addCardHoverEffect(
                routeCard,
                CARD_BORDER,
                "17");

        // =====================================================
        // BEST DRIVER
        // =====================================================

        VBox driverCard = new VBox(10);

        driverCard.setPadding(
                new Insets(18));

        driverCard.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 17;" +
                        "-fx-border-color: " +
                        CARD_BORDER + ";" +
                        "-fx-border-radius: 17;" +
                        "-fx-border-width: 1;");

        Label driverTitle = createLabel(
                "BEST DRIVER",
                MUTED,
                11,
                true);

        HBox driverRow = new HBox(10);

        driverRow.setAlignment(
                Pos.CENTER_LEFT);

        StackPane avatar = new StackPane();

        Circle circle = new Circle(
                18,
                Color.web("#DDEBDD"));

        Label initials = createLabel(
                "AK",
                GREEN,
                11,
                true);

        avatar.getChildren().addAll(
                circle,
                initials);

        Label driver = createLabel(
                "Arjun K. (4.9★)",
                TEXT,
                13,
                true);

        driverRow.getChildren().addAll(
                avatar,
                driver);

        driverCard.getChildren().addAll(
                driverTitle,
                driverRow);

        // Green hover border
        addCardHoverEffect(
                driverCard,
                CARD_BORDER,
                "17");

        // =====================================================
        // RECENT ACTIVITY
        // =====================================================

        Label activityTitle = createLabel(
                "Recent Activity",
                TEXT,
                13,
                true);

        VBox activity = new VBox(14);

        activity.getChildren().addAll(

                createActivity(
                        "Load Posted: Pune to Nashik",
                        "10 mins ago",
                        true),

                createActivity(
                        "Trip Completed: Mumbai - Surat",
                        "2 hours ago",
                        false));

        box.getChildren().addAll(
                title,
                routeCard,
                driverCard,
                activityTitle,
                activity);

        return box;
    }

    // =========================================================
    // ACTIVITY
    // =========================================================

    private HBox createActivity(
            String text,
            String time,
            boolean green) {

        HBox row = new HBox(10);

        row.setAlignment(
                Pos.CENTER_LEFT);

        row.setMaxWidth(
                Double.MAX_VALUE);

        row.setPadding(
                new Insets(10, 12, 10, 12));

        // Normal style
        row.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-color: " + CARD_BORDER + ";" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-width: 1;");

        // =====================================================
        // ACTIVITY DOT
        // =====================================================

        Circle dot = new Circle(
                5,
                Color.web(
                        green
                                ? GREEN
                                : "#68736D"));

        // =====================================================
        // ACTIVITY TEXT
        // =====================================================

        VBox texts = new VBox(3);

        Label main = createLabel(
                text,
                TEXT,
                12,
                false);

        main.setWrapText(true);

        Label small = createLabel(
                time,
                MUTED,
                10,
                false);

        texts.getChildren().addAll(
                main,
                small);

        row.getChildren().addAll(
                dot,
                texts);

        // =====================================================
        // HOVER EFFECT
        // =====================================================

        row.setOnMouseEntered(event -> {

            row.setStyle(
                    "-fx-background-color: " + VERY_LIGHT_GREEN + ";" +
                            "-fx-background-radius: 12;" +
                            "-fx-border-color: " + GREEN + ";" +
                            "-fx-border-radius: 12;" +
                            "-fx-border-width: 1.5;");
        });

        row.setOnMouseExited(event -> {

            row.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-background-radius: 12;" +
                            "-fx-border-color: " + CARD_BORDER + ";" +
                            "-fx-border-radius: 12;" +
                            "-fx-border-width: 1;");
        });

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
            double progress) {

        VBox card = new VBox(10);

        card.setPadding(
                new Insets(
                        18,
                        20,
                        18,
                        20));

        card.setMaxWidth(
                Double.MAX_VALUE);

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 18;" +
                        "-fx-border-color: " +
                        CARD_BORDER + ";" +
                        "-fx-border-radius: 18;" +
                        "-fx-border-width: 1;");

        // =====================================================
        // TOP
        // =====================================================

        HBox top = new HBox();

        VBox routeBox = new VBox(4);

        Label routeLabel = createLabel(
                route,
                TEXT,
                14,
                true);

        Label vehicleLabel = createLabel(
                vehicle,
                MUTED,
                11,
                false);

        routeBox.getChildren().addAll(
                routeLabel,
                vehicleLabel);

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        VBox statusBox = new VBox(3);

        statusBox.setAlignment(
                Pos.CENTER_RIGHT);

        Label statusLabel = createLabel(
                status,
                GREEN,
                11,
                true);

        Label etaLabel = createLabel(
                eta,
                MUTED,
                9,
                false);

        statusBox.getChildren().addAll(
                statusLabel,
                etaLabel);

        top.getChildren().addAll(
                routeBox,
                spacer,
                statusBox);

        // =====================================================
        // PROGRESS
        // =====================================================

        ProgressBar progressBar = new ProgressBar(progress);

        progressBar.setMaxWidth(
                Double.MAX_VALUE);

        progressBar.setPrefHeight(10);

        progressBar.setStyle(
                "-fx-accent: " + GREEN + ";");

        // =====================================================
        // BOTTOM
        // =====================================================

        HBox bottom = new HBox();

        Label locationLabel = createLabel(
                location,
                TEXT,
                12,
                true);

        Region bottomSpacer = new Region();

        HBox.setHgrow(
                bottomSpacer,
                Priority.ALWAYS);

        Label remainingLabel = createLabel(
                remaining,
                MUTED,
                11,
                false);

        bottom.getChildren().addAll(
                locationLabel,
                bottomSpacer,
                remainingLabel);

        card.getChildren().addAll(
                top,
                progressBar,
                bottom);

        // Green border on hover
        addCardHoverEffect(
                card,
                CARD_BORDER,
                "18");

        return card;
    }
}