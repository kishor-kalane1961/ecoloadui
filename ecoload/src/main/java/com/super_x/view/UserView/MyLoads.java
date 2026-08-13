package com.super_x.view.UserView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.stream.Collectors;

public class MyLoads {

    // =========================================================
    // COLORS
    // =========================================================

    private static final String GREEN = "#087A20";
    private static final String DARK_GREEN = "#075F1B";
    private static final String LIGHT_GREEN = "#9AF29B";
    private static final String BACKGROUND = "#F6F7F5";
    // private static final String TEXT = "#222222";
    // private static final String SECONDARY = "#777777";

    // =========================================================
    // CONTROLS
    // =========================================================

    private TextField searchField;
    private ComboBox<String> statusCombo;
    private ComboBox<String> vehicleCombo;

    private VBox loadsContainer;

    private Label activeCount;
    private Label completedCount;
    private Label pendingCount;
    private Label revenueValue;

    // =========================================================
    // LOAD DATA
    // =========================================================

    private final ObservableList<Load> allLoads = FXCollections.observableArrayList(

            new Load(
                    "Pune → Nashik",
                    "Steel Coils (6 Ton)",
                    "Container",
                    "8 Active Bids",
                    "₹9,200",
                    "Oct 24, 2023",
                    "Open"),

            new Load(
                    "Mumbai → Bengaluru",
                    "Electronics (2.5 Ton)",
                    "Reefer Truck",
                    "Assigned to: K. Logistics",
                    "₹42,000",
                    "Oct 22, 2023",
                    "In Progress"),

            new Load(
                    "Chennai → Hyderabad",
                    "Auto Parts (4 Ton)",
                    "Flatbed",
                    "15 Active Bids",
                    "₹18,500",
                    "Oct 25, 2023",
                    "Open"),

            new Load(
                    "Ahmedabad → Delhi",
                    "Textiles (5.2 Ton)",
                    "Close Body",
                    "Completed by: J.K. Transport",
                    "₹28,800",
                    "Oct 15, 2023",
                    "Completed"),

            new Load(
                    "Pune → Mumbai",
                    "Machinery (8 Ton)",
                    "Container",
                    "6 Active Bids",
                    "₹15,500",
                    "Oct 27, 2023",
                    "Open"),

            new Load(
                    "Nashik → Pune",
                    "Chemical Material (3 Ton)",
                    "Close Body",
                    "Assigned to: R.K. Transport",
                    "₹12,800",
                    "Oct 20, 2023",
                    "In Progress"));

    // =========================================================
    // MAIN SCENE
    // =========================================================

    public Scene getMyLoadsScene() {

        BorderPane mainroot = new BorderPane();
        mainroot.setLeft(UserNavigation.createSidebar("My Loads"));

        BorderPane mainContent = new BorderPane();
        mainContent.setTop(UserNavigation.createNavbar());

        mainroot.setCenter(mainContent);

        VBox root = new VBox(20);

        mainContent.setCenter(root);

        root.setPadding(
                new Insets(28, 35, 28, 35));

        root.setStyle(
                "-fx-background-color: " + BACKGROUND + ";");

        // =====================================================
        // HEADER
        // =====================================================

        VBox header = new VBox(4);

        Label title = new Label("My Loads");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27));

        title.setTextFill(
                Color.web(DARK_GREEN));

        Label subtitle = new Label(
                "Manage all your posted loads and monitor their status.");

        subtitle.setFont(
                Font.font("Arial", 14));

        subtitle.setTextFill(
                Color.web("#666666"));

        header.getChildren().addAll(
                title,
                subtitle);

        // =====================================================
        // SUMMARY CARDS
        // =====================================================

        HBox summary = new HBox(18);

        activeCount = new Label("05");
        completedCount = new Label("142");
        pendingCount = new Label("12");
        revenueValue = new Label("₹4,52,000");

        summary.getChildren().addAll(

                createSummaryCard(
                        "Active Loads",
                        activeCount,
                        "🚚"),

                createSummaryCard(
                        "Completed Loads",
                        completedCount,
                        "✓"),

                createSummaryCard(
                        "Pending Bids",
                        pendingCount,
                        "⚒"),

                createSummaryCard(
                        "Total Revenue",
                        revenueValue,
                        "₹"));

        // =====================================================
        // FILTER
        // =====================================================

        VBox filterBox = createFilterBox();

        // =====================================================
        // LOAD CONTAINER
        // =====================================================

        loadsContainer = new VBox(14);

        loadsContainer.setFillWidth(true);

        refreshLoads(allLoads);

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane = new ScrollPane(loadsContainer);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: transparent;");

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS);

        // =====================================================
        // ROOT
        // =====================================================

        root.getChildren().addAll(
                header,
                summary,
                filterBox,
                scrollPane);

        updateSummary();

        return new Scene(
                mainroot,
                1536,
                750);
    }

    // =========================================================
    // SUMMARY CARD
    // =========================================================

    private VBox createSummaryCard(
            String title,
            Label value,
            String icon) {

        VBox card = new VBox(8);

        card.setPadding(
                new Insets(17, 20, 17, 20));

        card.setPrefHeight(120);

        card.setMinWidth(200);

        card.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(
                card,
                Priority.ALWAYS);

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 18;" +
                        "-fx-border-color: #E5E9E5;" +
                        "-fx-border-radius: 18;" +
                        "-fx-border-width: 1;");

        Label titleLabel = new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14));

        titleLabel.setTextFill(
                Color.web("#666666"));

        HBox bottom = new HBox();

        bottom.setAlignment(
                Pos.CENTER_LEFT);

        value.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27));

        value.setTextFill(
                Color.web(DARK_GREEN));

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        Label iconLabel = new Label(icon);

        iconLabel.setAlignment(
                Pos.CENTER);

        iconLabel.setPrefSize(
                46,
                46);

        iconLabel.setStyle(
                "-fx-background-color: " +
                        LIGHT_GREEN + ";" +
                        "-fx-background-radius: 14;" +
                        "-fx-font-size: 19px;");

        bottom.getChildren().addAll(
                value,
                spacer,
                iconLabel);

        card.getChildren().addAll(
                titleLabel,
                bottom);

        return card;
    }

    // =========================================================
    // FILTER BOX
    // =========================================================

    private VBox createFilterBox() {

        VBox box = new VBox();

        box.setPadding(
                new Insets(17, 20, 20, 20));

        box.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 18;" +
                        "-fx-border-color: #E5E9E5;" +
                        "-fx-border-radius: 18;");

        HBox filters = new HBox(25);

        filters.setAlignment(
                Pos.BOTTOM_LEFT);

        // SEARCH
        VBox searchBox = new VBox(6);

        Label searchLabel = new Label("Search Route or Type");

        searchLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12));

        searchField = new TextField();

        searchField.setPromptText(
                "e.g. Pune to Nashik...");

        searchField.setPrefWidth(300);

        searchField.setPrefHeight(42);

        searchBox.getChildren().addAll(
                searchLabel,
                searchField);

        // STATUS
        VBox statusBox = new VBox(6);

        Label statusLabel = new Label("Status");

        statusLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12));

        statusCombo = new ComboBox<>();

        statusCombo.getItems().addAll(
                "All Status",
                "Open",
                "In Progress",
                "Completed");

        statusCombo.setValue(
                "All Status");

        statusCombo.setPrefWidth(145);

        statusCombo.setPrefHeight(42);

        statusBox.getChildren().addAll(
                statusLabel,
                statusCombo);

        // VEHICLE
        VBox vehicleBox = new VBox(6);

        Label vehicleLabel = new Label("Vehicle");

        vehicleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12));

        vehicleCombo = new ComboBox<>();

        vehicleCombo.getItems().addAll(
                "All Types",
                "Container",
                "Reefer Truck",
                "Flatbed",
                "Close Body");

        vehicleCombo.setValue(
                "All Types");

        vehicleCombo.setPrefWidth(150);

        vehicleCombo.setPrefHeight(42);

        vehicleBox.getChildren().addAll(
                vehicleLabel,
                vehicleCombo);

        // SEARCH BUTTON
        Button searchButton = new Button("Search");

        searchButton.setPrefWidth(140);

        searchButton.setPrefHeight(42);

        searchButton.setStyle(
                "-fx-background-color: " + GREEN + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;");

        searchButton.setOnAction(
                e -> applyFilters());

        // RESET BUTTON
        Button resetButton = new Button("Reset");

        resetButton.setPrefWidth(120);

        resetButton.setPrefHeight(42);

        resetButton.setStyle(
                "-fx-background-color: #E8E8E8;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;");

        resetButton.setOnAction(e -> {

            searchField.clear();

            statusCombo.setValue(
                    "All Status");

            vehicleCombo.setValue(
                    "All Types");

            refreshLoads(allLoads);
        });

        filters.getChildren().addAll(
                searchBox,
                statusBox,
                vehicleBox,
                searchButton,
                resetButton);

        box.getChildren().add(
                filters);

        return box;
    }

    // =========================================================
    // SEARCH + FILTER
    // =========================================================

    private void applyFilters() {

        String search = searchField.getText()
                .trim()
                .toLowerCase();

        String status = statusCombo.getValue();

        String vehicle = vehicleCombo.getValue();

        ObservableList<Load> filtered = allLoads.stream()

                .filter(load ->

                search.isEmpty()

                        ||

                        load.route
                                .toLowerCase()
                                .contains(search)

                        ||

                        load.cargo
                                .toLowerCase()
                                .contains(search)

                        ||

                        load.vehicle
                                .toLowerCase()
                                .contains(search))

                .filter(load ->

                status.equals("All Status")

                        ||

                        load.status.equals(status))

                .filter(load ->

                vehicle.equals("All Types")

                        ||

                        load.vehicle.equals(vehicle))

                .collect(
                        Collectors.toCollection(
                                FXCollections::observableArrayList));

        refreshLoads(filtered);
    }

    // =========================================================
    // REFRESH LOADS
    // =========================================================

    private void refreshLoads(
            ObservableList<Load> loads) {

        loadsContainer.getChildren().clear();

        if (loads.isEmpty()) {

            VBox emptyBox = new VBox(8);

            emptyBox.setAlignment(
                    Pos.CENTER);

            emptyBox.setPadding(
                    new Insets(50));

            Label message = new Label("No loads found");

            message.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            18));

            Label hint = new Label(
                    "Try changing your search or filters.");

            hint.setTextFill(
                    Color.web("#777777"));

            emptyBox.getChildren().addAll(
                    message,
                    hint);

            loadsContainer.getChildren().add(
                    emptyBox);

            return;
        }

        for (Load load : loads) {

            loadsContainer.getChildren().add(
                    createLoadCard(load));
        }
    }

    // =========================================================
    // LOAD CARD
    // =========================================================
    //
    // IMPORTANT:
    // GridPane is used instead of HBox.
    // This prevents the information from disappearing.
    //
    // =========================================================

    private VBox createLoadCard(Load load) {

        // =====================================================
        // MAIN CARD
        // =====================================================

        VBox card = new VBox(12);

        card.setPadding(
                new Insets(18, 22, 18, 22));

        card.setMinHeight(155);
        card.setPrefHeight(155);

        card.setMaxWidth(
                Double.MAX_VALUE);

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 18;" +
                        "-fx-border-color: #E4E8E4;" +
                        "-fx-border-radius: 18;" +
                        "-fx-border-width: 1;");

        // =====================================================
        // TOP ROW
        // =====================================================

        HBox topRow = new HBox();

        topRow.setAlignment(
                Pos.CENTER_LEFT);

        // -----------------------------------------------------
        // ROUTE
        // -----------------------------------------------------

        VBox routeBox = new VBox(4);

        Label routeHeading = new Label("ROUTE & CARGO");

        routeHeading.setStyle(
                "-fx-font-family: Arial;" +
                        "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #777777;");

        Label routeLabel = new Label(load.route);

        routeLabel.setStyle(
                "-fx-font-family: Arial;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222222;");

        routeLabel.setWrapText(true);

        Label cargoLabel = new Label(load.cargo);

        cargoLabel.setStyle(
                "-fx-font-family: Arial;" +
                        "-fx-font-size: 13px;" +
                        "-fx-text-fill: #777777;");

        cargoLabel.setWrapText(true);

        routeBox.getChildren().addAll(
                routeHeading,
                routeLabel,
                cargoLabel);

        routeBox.setPrefWidth(300);
        routeBox.setMinWidth(260);

        // -----------------------------------------------------
        // VEHICLE
        // -----------------------------------------------------

        VBox vehicleBox = new VBox(4);

        Label vehicleHeading = new Label("VEHICLE & BIDS");

        vehicleHeading.setStyle(
                "-fx-font-family: Arial;" +
                        "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #777777;");

        Label vehicleLabel = new Label(load.vehicle);

        vehicleLabel.setStyle(
                "-fx-font-family: Arial;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222222;");

        vehicleLabel.setWrapText(true);

        Label bidsLabel = new Label(load.bids);

        bidsLabel.setStyle(
                "-fx-font-family: Arial;" +
                        "-fx-font-size: 13px;" +
                        "-fx-text-fill: #28733A;");

        bidsLabel.setWrapText(true);

        vehicleBox.getChildren().addAll(
                vehicleHeading,
                vehicleLabel,
                bidsLabel);

        vehicleBox.setPrefWidth(260);
        vehicleBox.setMinWidth(220);

        // -----------------------------------------------------
        // PRICE
        // -----------------------------------------------------

        VBox priceBox = new VBox(4);

        Label priceHeading = new Label("PRICING & DATE");

        priceHeading.setStyle(
                "-fx-font-family: Arial;" +
                        "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #777777;");

        Label priceLabel = new Label(load.price);

        priceLabel.setStyle(
                "-fx-font-family: Arial;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222222;");

        Label dateLabel = new Label(load.date);

        dateLabel.setStyle(
                "-fx-font-family: Arial;" +
                        "-fx-font-size: 13px;" +
                        "-fx-text-fill: #777777;");

        priceBox.getChildren().addAll(
                priceHeading,
                priceLabel,
                dateLabel);

        priceBox.setPrefWidth(180);
        priceBox.setMinWidth(160);

        // -----------------------------------------------------
        // STATUS
        // -----------------------------------------------------

        Label status = createStatusBadge(load.status);

        // -----------------------------------------------------
        // ACTION BUTTON
        // -----------------------------------------------------

        Button action = new Button(
                getActionText(load.status));

        action.setPrefWidth(125);
        action.setMinWidth(125);
        action.setPrefHeight(40);

        action.setStyle(
                getActionStyle(load.status));

        action.setOnAction(
                e -> handleAction(load));

        // -----------------------------------------------------
        // SPACER
        // -----------------------------------------------------

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        // -----------------------------------------------------
        // TOP ROW CONTENT
        // -----------------------------------------------------

        topRow.getChildren().addAll(
                routeBox,
                vehicleBox,
                priceBox,
                spacer,
                status,
                new Region(),
                action);

        // =====================================================
        // BOTTOM LINE
        // =====================================================

        Separator separator = new Separator();

        separator.setStyle(
                "-fx-background-color: #EEEEEE;");

        // =====================================================
        // BOTTOM INFORMATION
        // =====================================================

        HBox bottomRow = new HBox(12);

        bottomRow.setAlignment(
                Pos.CENTER_LEFT);

        Label loadId = new Label("Load ID: " + load.route);

        loadId.setStyle(
                "-fx-font-family: Arial;" +
                        "-fx-font-size: 11px;" +
                        "-fx-text-fill: #999999;");

        Region bottomSpacer = new Region();

        HBox.setHgrow(
                bottomSpacer,
                Priority.ALWAYS);

        Label info = new Label(
                load.status.equals("Open")
                        ? "Ready for driver bids"
                        : load.status.equals("In Progress")
                                ? "Load is currently in transit"
                                : "This load has been completed");

        info.setStyle(
                "-fx-font-family: Arial;" +
                        "-fx-font-size: 11px;" +
                        "-fx-text-fill: #777777;");

        bottomRow.getChildren().addAll(
                loadId,
                bottomSpacer,
                info);

        // =====================================================
        // ADD TO CARD
        // =====================================================

        card.getChildren().addAll(
                topRow,
                separator,
                bottomRow);

        // =====================================================
        // HOVER EFFECT
        // =====================================================

        card.setOnMouseEntered(e -> {

            card.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-background-radius: 18;" +
                            "-fx-border-color: #72D47C;" +
                            "-fx-border-radius: 18;" +
                            "-fx-border-width: 1.5;" +
                            "-fx-effect: dropshadow(" +
                            "gaussian, rgba(0,0,0,0.10), 12, 0, 0, 3);");
        });

        card.setOnMouseExited(e -> {

            card.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-background-radius: 18;" +
                            "-fx-border-color: #E4E8E4;" +
                            "-fx-border-radius: 18;" +
                            "-fx-border-width: 1;");
        });

        return card;
    }

    // =========================================================
    // STATUS BADGE
    // =========================================================

    private Label createStatusBadge(
            String status) {

        Label badge = new Label("●  " + status);

        badge.setAlignment(
                Pos.CENTER);

        badge.setPrefHeight(32);

        badge.setMinWidth(92);

        badge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11));

        if (status.equals("Open")) {

            badge.setStyle(
                    "-fx-background-color: #9AF29B;" +
                            "-fx-text-fill: #087A20;" +
                            "-fx-background-radius: 20;" +
                            "-fx-padding: 0 10;");

        } else if (status.equals("In Progress")) {

            badge.setStyle(
                    "-fx-background-color: #E4F0E6;" +
                            "-fx-text-fill: #26733A;" +
                            "-fx-background-radius: 20;" +
                            "-fx-padding: 0 8;");

        } else {

            badge.setStyle(
                    "-fx-background-color: #E7E7E7;" +
                            "-fx-text-fill: #777777;" +
                            "-fx-background-radius: 20;" +
                            "-fx-padding: 0 10;");
        }

        return badge;
    }

    // =========================================================
    // ACTION TEXT
    // =========================================================

    private String getActionText(
            String status) {

        switch (status) {

            case "Open":
                return "Manage Bids";

            case "In Progress":
                return "Track Load";

            default:
                return "View Invoice";
        }
    }

    // =========================================================
    // ACTION STYLE
    // =========================================================

    private String getActionStyle(
            String status) {

        if (status.equals("Open")) {

            return "-fx-background-color: #247C35;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 12px;" +
                    "-fx-background-radius: 8;" +
                    "-fx-cursor: hand;";
        }

        return "-fx-background-color: #E9E9E9;" +
                "-fx-text-fill: #444444;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12px;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;";
    }

    // =========================================================
    // BUTTON ACTION
    // =========================================================

    private void handleAction(
            Load load) {

        switch (load.status) {

            case "Open":

                showAlert(
                        "Manage Bids",
                        "Viewing bids for:\n\n" +
                                load.route +
                                "\n\n" +
                                load.bids);

                break;

            case "In Progress":

                showAlert(
                        "Track Load",
                        "Tracking load:\n\n" +
                                load.route);

                break;

            case "Completed":

                showAlert(
                        "Invoice",
                        "Invoice for:\n\n" +
                                load.route +
                                "\n\nAmount: " +
                                load.price);

                break;
        }
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            String title,
            String message) {

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }

    // =========================================================
    // SUMMARY
    // =========================================================

    private void updateSummary() {

        long active = allLoads.stream()
                .filter(
                        l -> l.status.equals("Open"))
                .count();

        long pending = allLoads.stream()
                .filter(
                        l -> l.status.equals("Open"))
                .count();

        activeCount.setText(
                String.format(
                        "%02d",
                        active));

        // Keep your dashboard value
        completedCount.setText("142");

        pendingCount.setText(
                String.valueOf(
                        pending * 4));
    }

    // =========================================================
    // LOAD MODEL
    // =========================================================

    private static class Load {

        String route;
        String cargo;
        String vehicle;
        String bids;
        String price;
        String date;
        String status;

        Load(
                String route,
                String cargo,
                String vehicle,
                String bids,
                String price,
                String date,
                String status) {

            this.route = route;
            this.cargo = cargo;
            this.vehicle = vehicle;
            this.bids = bids;
            this.price = price;
            this.date = date;
            this.status = status;
        }
    }
}