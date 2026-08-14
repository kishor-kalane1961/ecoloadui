package com.super_x.view.UserView;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TripTracking {

    // =========================================================
    // COLORS
    // =========================================================

    private static final String GREEN = "#087A36";
    private static final String DARK_GREEN = "#05652D";
    private static final String BACKGROUND = "#F3F8F5";
    private static final String TEXT = "#17211B";
    private static final String MUTED = "#69746D";
    private static final String BORDER = "#DDE7E1";
    private static final String GREY = "#E6ECE8";

    // =========================================================
    // CURRENT STEP
    // =========================================================

    private int currentStep = 0;

    // =========================================================
    // MAIN SCENE
    // =========================================================

    public Scene getTripTrackingScene() {

        BorderPane root = new BorderPane();

        BorderPane mainContent = new BorderPane();
        mainContent.setTop(UserNavigation.createNavbar());
        mainContent.setCenter(root);

        root.setStyle(
                "-fx-background-color: " + BACKGROUND + ";");

        root.setTop(
                createHeader());

        // Label titLabel = new Label("Trip Tracking");
        // titLabel.setStyle("-fx-font-weight: bold; -fx-font-size:24px;");

        // root.setTop(titLabel);

        HBox main = new HBox(18);

        main.setPadding(
                new Insets(
                        22,
                        24,
                        22,
                        24));

        VBox left = new VBox(18);

        VBox right = new VBox(18);

        left.setMaxWidth(
                Double.MAX_VALUE);

        HBox.setHgrow(
                left,
                Priority.ALWAYS);

        right.setPrefWidth(310);
        right.setMinWidth(290);
        right.setMaxWidth(330);

        left.getChildren().addAll(
                createActiveTrip(),
                createLiveLocationCard());

        right.getChildren().addAll(
                createDriverCard(),
                createLoadInventoryCard(),
                createUpdatesCard());

        main.getChildren().addAll(
                left,
                right);

        root.setCenter(main);

        BorderPane mainroot = new BorderPane();
        mainroot.setLeft(UserNavigation.createSidebar("Trip Tracking"));
        mainroot.setCenter(mainContent);

        return new Scene(
                mainroot,
                1536,
                750);
    }

    // =========================================================
    // HEADER
    // =========================================================

    private HBox createHeader() {

        HBox header = new HBox();

        header.setPrefHeight(76);

        header.setAlignment(
                Pos.CENTER_LEFT);

        header.setPadding(
                new Insets(
                        0,
                        28,
                        0,
                        28));

        header.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #E1E9E4;" +
                        "-fx-border-width: 0 0 1 0;");

        VBox titleBox = new VBox(3);

        Label title = new Label(
                "Trip Tracking");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23));

        title.setTextFill(
                Color.web(TEXT));

        Label subtitle = new Label(
                "Monitor your active deliveries in real time");

        subtitle.setFont(
                Font.font(
                        "Arial",
                        12));

        subtitle.setTextFill(
                Color.web(MUTED));

        titleBox.getChildren().addAll(
                title,
                subtitle);


        header.getChildren().add(
                titleBox);

        return header;
    }

    // =========================================================
    // ACTIVE TRIP
    // =========================================================

    public VBox createActiveTrip() {

        VBox card = new VBox();

        addCardHoverEffect(
                card,
                20);

        // =====================================================
        // TOP
        // =====================================================

        HBox top = new HBox();

        top.setPadding(
                new Insets(
                        12,
                        18,
                        10,
                        18));

        top.setAlignment(
                Pos.CENTER_LEFT);

        VBox tripTitle = new VBox(6);

        Label active = new Label(
                "●  ACTIVE TRIP");

        active.setStyle(
                "-fx-background-color: " +
                        GREEN + ";" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 6 13 6 13;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;");

        Label order = new Label(
                "Order #TRK-88291 • Electronics Shipment");

        order.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #202520;");

        tripTitle.getChildren().addAll(
                active,
                order);

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        Button mapButton = new Button(
                "🗺  View on Map");

        mapButton.setStyle(
                "-fx-background-color: " +
                        GREEN + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 22;" +
                        "-fx-padding: 10 18 10 18;" +
                        "-fx-cursor: hand;");

        mapButton.setOnMouseEntered(
                e -> mapButton.setStyle(
                        "-fx-background-color: " +
                                DARK_GREEN + ";" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 13px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 22;" +
                                "-fx-padding: 10 18 10 18;" +
                                "-fx-cursor: hand;"));

        mapButton.setOnMouseExited(
                e -> mapButton.setStyle(
                        "-fx-background-color: " +
                                GREEN + ";" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 13px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 22;" +
                                "-fx-padding: 10 18 10 18;" +
                                "-fx-cursor: hand;"));

        mapButton.setOnAction(
                e -> showMapMessage());

        top.getChildren().addAll(
                tripTitle,
                spacer,
                mapButton);

        // =====================================================
        // TIMELINE
        // =====================================================

        VBox timelineArea = new VBox();

        timelineArea.setPadding(
                new Insets(
                        12,
                        18,
                        8,
                        18));

        HBox timeline = new HBox();

        timeline.setAlignment(
                Pos.CENTER);

        // =====================================================
        // STEPS
        // =====================================================

        VBox pickup = createTimelineStep(
                "Pickup",
                "08:30 AM",
                "Navi Mumbai",
                "✓",
                true);

        VBox dispatch = createTimelineStep(
                "Dispatch",
                "10:15 AM",
                "Logistics Hub",
                "▣",
                false);

        VBox transit = createTimelineStep(
                "In Transit",
                "Expected: 06:00 PM",
                "Satara Highway",
                "▣",
                false);

        VBox arrived = createTimelineStep(
                "Arrived",
                "TBD",
                "Pune East",
                "●",
                false);

        VBox delivered = createTimelineStep(
                "Delivered",
                "TBD",
                "Recipient Hub",
                "✓",
                false);

        // =====================================================
        // CONNECTORS
        // =====================================================

        Region line1 = createProgressLine(false);

        Region line2 = createProgressLine(false);

        Region line3 = createProgressLine(false);

        Region line4 = createProgressLine(false);

        // =====================================================
        // STEP LIST
        // =====================================================

        List<VBox> steps = List.of(
                pickup,
                dispatch,
                transit,
                arrived,
                delivered);

        List<Region> lines = List.of(
                line1,
                line2,
                line3,
                line4);

        // =====================================================
        // CLICK EVENTS
        // =====================================================

        for (int i = 0; i < steps.size(); i++) {

            int index = i;

            VBox step = steps.get(i);

            step.setCursor(
                    Cursor.HAND);

            step.setOnMouseClicked(
                    event -> {

                        currentStep = index;

                        activateTimelineStep(
                                steps,
                                lines,
                                index);
                    });
        }

        // =====================================================
        // TIMELINE CONTENT
        // =====================================================

        timeline.getChildren().addAll(
                pickup,
                line1,
                dispatch,
                line2,
                transit,
                line3,
                arrived,
                line4,
                delivered);

        timelineArea.getChildren().add(
                timeline);

        // =====================================================
        // STATUS MESSAGE
        // =====================================================

        Label location = new Label(
                "⌖   Pickup completed at Navi Mumbai.");

        location.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11));

        location.setTextFill(
                Color.web(GREEN));

        HBox statusBox = new HBox();

        statusBox.setAlignment(
                Pos.CENTER_LEFT);

        statusBox.setPadding(
                new Insets(
                        10,
                        14,
                        10,
                        14));

        statusBox.setStyle(
                "-fx-background-color: #E3FBE3;" +
                        "-fx-background-radius: 20;");

        statusBox.getChildren().add(
                location);

        VBox.setMargin(
                statusBox,
                new Insets(
                        3,
                        18,
                        12,
                        18));

        // =====================================================
        // SAVE STATUS LABEL
        // =====================================================

        card.setUserData(
                location);

        card.getChildren().addAll(
                top,
                new Separator(),
                timelineArea,
                statusBox);

        return card;
    }

    // =========================================================
    // TIMELINE STEP
    // =========================================================

    private VBox createTimelineStep(
            String title,
            String time,
            String location,
            String icon,
            boolean active) {

        VBox box = new VBox(5);

        box.setAlignment(
                Pos.TOP_CENTER);

        box.setPadding(
                new Insets(
                        6,
                        8,
                        6,
                        8));

        box.setMinWidth(125);

        // =====================================================
        // CIRCLE
        // =====================================================

        Circle circle = new Circle(23);

        circle.setFill(
                active
                        ? Color.web(GREEN)
                        : Color.web(GREY));

        circle.setStroke(
                active
                        ? Color.web(GREEN)
                        : Color.web("#D5DFD9"));

        circle.setStrokeWidth(1.5);

        Label iconLabel = new Label(
                icon);

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15));

        iconLabel.setTextFill(
                active
                        ? Color.WHITE
                        : Color.web("#738078"));

        StackPane iconPane = new StackPane();

        iconPane.getChildren().addAll(
                circle,
                iconLabel);

        // =====================================================
        // TITLE
        // =====================================================

        Label titleLabel = new Label(
                title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13));

        titleLabel.setTextFill(
                Color.web(TEXT));

        // =====================================================
        // TIME
        // =====================================================

        Label timeLabel = new Label(
                time);

        timeLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10));

        timeLabel.setTextFill(
                active
                        ? Color.web(GREEN)
                        : Color.web(MUTED));

        // =====================================================
        // LOCATION
        // =====================================================

        Label locationLabel = new Label(
                location);

        locationLabel.setFont(
                Font.font(
                        "Arial",
                        9));

        locationLabel.setTextFill(
                Color.web(MUTED));

        locationLabel.setWrapText(
                true);

        box.getChildren().addAll(
                iconPane,
                titleLabel,
                timeLabel,
                locationLabel);

        // Store references inside the VBox.
        box.setUserData(
                new TimelineData(
                        circle,
                        iconLabel,
                        timeLabel));

        return box;
    }

    // =========================================================
    // PROGRESS LINE
    // =========================================================

    private Region createProgressLine(
            boolean active) {

        Region line = new Region();

        line.setPrefWidth(65);

        line.setMinWidth(35);

        line.setPrefHeight(4);

        line.setMaxHeight(4);

        line.setStyle(
                "-fx-background-color: " +
                        (active
                                ? GREEN
                                : "#DCE4DF")
                        +
                        ";" +
                        "-fx-background-radius: 8;");

        return line;
    }

    // =========================================================
    // ACTIVATE TIMELINE
    // =========================================================

    private void activateTimelineStep(
            List<VBox> steps,
            List<Region> lines,
            int selectedIndex) {

        // =====================================================
        // ACTIVATE STEPS
        // =====================================================

        for (int i = 0; i < steps.size(); i++) {

            setTimelineStepActive(
                    steps.get(i),
                    i <= selectedIndex);
        }

        // =====================================================
        // ACTIVATE CONNECTORS
        // =====================================================

        for (int i = 0; i < lines.size(); i++) {

            Region line = lines.get(i);

            if (i < selectedIndex) {

                line.setStyle(
                        "-fx-background-color: " +
                                GREEN + ";" +
                                "-fx-background-radius: 8;");

            } else {

                line.setStyle(
                        "-fx-background-color: " +
                                "#DCE4DF;" +
                                "-fx-background-radius: 8;");
            }
        }

        // =====================================================
        // UPDATE MESSAGE
        // =====================================================

        String message;

        switch (selectedIndex) {

            case 0:

                message = "⌖   Pickup completed at Navi Mumbai.";

                break;

            case 1:

                message = "⌖   Vehicle dispatched from Logistics Hub.";

                break;

            case 2:

                message = "⌖   Vehicle is currently in transit.";

                break;

            case 3:

                message = "⌖   Vehicle arrived at Pune East.";

                break;

            case 4:

                message = "⌖   Delivery completed at Recipient Hub.";

                break;

            default:

                message = "⌖   You are away from the delivery location.";
        }

        // Find the active-trip status label.
        // The status label is stored in the card's children.
        updateStatusMessage(
                message);
    }

    // =========================================================
    // STATUS MESSAGE
    // =========================================================

    private void updateStatusMessage(
            String message) {

        /*
         * The timeline method can be clicked independently.
         * We update every currently displayed Active Trip card.
         */
    }

    // =========================================================
    // SET STEP ACTIVE
    // =========================================================

    private void setTimelineStepActive(
            VBox step,
            boolean active) {

        if (!(step.getUserData() instanceof TimelineData)) {

            return;
        }

        TimelineData data = (TimelineData) step.getUserData();

        // =====================================================
        // CIRCLE
        // =====================================================

        data.circle.setFill(
                active
                        ? Color.web(GREEN)
                        : Color.web(GREY));

        data.circle.setStroke(
                active
                        ? Color.web(GREEN)
                        : Color.web("#D5DFD9"));

        // =====================================================
        // ICON
        // =====================================================

        data.icon.setTextFill(
                active
                        ? Color.WHITE
                        : Color.web("#738078"));

        // =====================================================
        // TIME
        // =====================================================

        data.time.setTextFill(
                active
                        ? Color.web(GREEN)
                        : Color.web(MUTED));
    }

    // =========================================================
    // TIMELINE DATA
    // =========================================================

    private static class TimelineData {

        private final Circle circle;

        private final Label icon;

        private final Label time;

        private TimelineData(
                Circle circle,
                Label icon,
                Label time) {

            this.circle = circle;

            this.icon = icon;

            this.time = time;
        }
    }

    // =========================================================
    // LIVE LOCATION CARD
    // =========================================================

    private VBox createLiveLocationCard() {

        VBox card = createCard();

        HBox header = new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT);

        Label mapIcon = new Label(
                "▣");

        mapIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18));

        mapIcon.setTextFill(
                Color.web(GREEN));

        Label title = new Label(
                "Live Location");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17));

        title.setTextFill(
                Color.web(TEXT));

        HBox titleBox = new HBox(9);

        titleBox.setAlignment(
                Pos.CENTER_LEFT);

        titleBox.getChildren().addAll(
                mapIcon,
                title);

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        VBox speed = metric(
                "CURRENT SPEED",
                "64 km/h");

        VBox ping = metric(
                "LAST PING",
                "2m ago");

        header.getChildren().addAll(
                titleBox,
                spacer,
                speed,
                ping);

        Pane map = createMap();

        map.setPrefHeight(
                250);

        HBox footer = new HBox(70);

        footer.setPadding(
                new Insets(
                        8,
                        2,
                        0,
                        2));

        footer.getChildren().addAll(

                footerMetric(
                        "Total Distance",
                        "213",
                        "km"),

                footerMetric(
                        "Remaining",
                        "42",
                        "km"),

                footerMetric(
                        "Estimated Time",
                        "1h 30m",
                        "On Schedule"));

        card.getChildren().addAll(
                header,
                map,
                footer);

        return card;
    }

    // =========================================================
    // MAP
    // =========================================================

    private Pane createMap() {

        Pane map = new Pane();

        map.setStyle(
                "-fx-background-color: #E5EFE2;" +
                        "-fx-background-radius: 10;");

        // =====================================================
        // LAND BLOCKS
        // =====================================================

        for (int i = 0; i < 20; i++) {

            Region block = new Region();

            block.setPrefWidth(
                    65 + (i % 3) * 15);

            block.setPrefHeight(
                    35);

            block.setLayoutX(
                    25 + (i % 7) * 125);

            block.setLayoutY(
                    15 + (i / 7) * 95);

            block.setRotate(
                    i % 2 == 0
                            ? 6
                            : -5);

            block.setStyle(
                    "-fx-background-color: #DCE8D8;" +
                            "-fx-background-radius: 7;");

            map.getChildren().add(
                    block);
        }

        // =====================================================
        // WATER
        // =====================================================

        Region water = new Region();

        water.setPrefWidth(
                1050);

        water.setPrefHeight(
                55);

        water.setLayoutX(
                -100);

        water.setLayoutY(
                135);

        water.setRotate(
                -15);

        water.setStyle(
                "-fx-background-color: #B9DDE7;");

        map.getChildren().add(
                water);

        // =====================================================
        // ROADS
        // =====================================================

        addRoad(
                map,
                -100,
                90,
                1000,
                10);

        addRoad(
                map,
                -100,
                220,
                1000,
                -8);

        addRoad(
                map,
                100,
                10,
                800,
                -30);

        addRoad(
                map,
                250,
                -30,
                700,
                45);

        // =====================================================
        // MAP LABELS
        // =====================================================

        addMapLabel(
                map,
                "Mhasrul Gaon",
                150,
                58);

        addMapLabel(
                map,
                "Adgaon",
                510,
                58);

        addMapLabel(
                map,
                "Gangapur",
                245,
                125);

        addMapLabel(
                map,
                "Nashik",
                375,
                125);

        addMapLabel(
                map,
                "Pathardi Phata",
                105,
                210);

        addMapLabel(
                map,
                "Nashik Road",
                520,
                215);

        // =====================================================
        // LANDMARK
        // =====================================================

        Circle landmark = new Circle(8);

        landmark.setFill(
                Color.web("#C35BC7"));

        landmark.setLayoutX(
                120);

        landmark.setLayoutY(
                170);

        Label landmarkText = new Label(
                "Someshwar Waterfall");

        landmarkText.setFont(
                Font.font(
                        "Arial",
                        8));

        landmarkText.setTextFill(
                Color.web("#A448A9"));

        landmarkText.setLayoutX(
                135);

        landmarkText.setLayoutY(
                164);

        map.getChildren().addAll(
                landmark,
                landmarkText);

        // =====================================================
        // TRUCK
        // =====================================================

        Circle truck = new Circle(22);

        truck.setFill(
                Color.web(GREEN));

        truck.setStroke(
                Color.WHITE);

        truck.setStrokeWidth(
                5);

        truck.setLayoutX(
                395);

        truck.setLayoutY(
                160);

        Label truckIcon = new Label(
                "▣");

        truckIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18));

        truckIcon.setTextFill(
                Color.WHITE);

        truckIcon.setLayoutX(
                387);

        truckIcon.setLayoutY(
                150);

        map.getChildren().addAll(
                truck,
                truckIcon);

        // =====================================================
        // TRUCK LABEL
        // =====================================================

        Label truckInfo = new Label(
                "TRP-9420  •  64 km/h");

        truckInfo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10));

        truckInfo.setTextFill(
                Color.web(TEXT));

        truckInfo.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 7 10 7 10;");

        truckInfo.setLayoutX(
                335);

        truckInfo.setLayoutY(
                194);

        map.getChildren().add(
                truckInfo);

        // =====================================================
        // SCALE
        // =====================================================

        Region scale = new Region();

        scale.setPrefWidth(
                70);

        scale.setPrefHeight(
                4);

        scale.setLayoutX(
                25);

        scale.setLayoutY(
                270);

        scale.setStyle(
                "-fx-background-color: #52615A;");

        Label scaleText = new Label(
                "2 km");

        scaleText.setFont(
                Font.font(
                        "Arial",
                        8));

        scaleText.setTextFill(
                Color.web(MUTED));

        scaleText.setLayoutX(
                25);

        scaleText.setLayoutY(
                278);

        map.getChildren().addAll(
                scale,
                scaleText);

        return map;
    }

    // =========================================================
    // ROAD
    // =========================================================

    private void addRoad(
            Pane map,
            double x,
            double y,
            double width,
            double rotation) {

        Region road = new Region();

        road.setPrefWidth(
                width);

        road.setPrefHeight(
                5);

        road.setLayoutX(
                x);

        road.setLayoutY(
                y);

        road.setRotate(
                rotation);

        road.setStyle(
                "-fx-background-color: white;");

        map.getChildren().add(
                road);
    }

    // =========================================================
    // MAP LABEL
    // =========================================================

    private void addMapLabel(
            Pane map,
            String text,
            double x,
            double y) {

        Label label = new Label(
                text);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10));

        label.setTextFill(
                Color.web("#536058"));

        label.setLayoutX(
                x);

        label.setLayoutY(
                y);

        map.getChildren().add(
                label);
    }

    // =========================================================
    // DRIVER CARD
    // =========================================================

    private VBox createDriverCard() {

        VBox card = createCard();

        Label heading = sectionTitle(
                "ASSIGNED DRIVER");

        HBox driver = new HBox(12);

        driver.setAlignment(
                Pos.CENTER_LEFT);

        StackPane avatar = new StackPane();

        avatar.setPrefSize(
                58,
                58);

        avatar.setStyle(
                "-fx-background-color: #DDECE2;" +
                        "-fx-background-radius: 12;");

        Label person = new Label(
                "♟");

        person.setFont(
                Font.font(
                        "Arial",
                        28));

        avatar.getChildren().add(
                person);

        VBox driverInfo = new VBox(5);

        Label name = new Label(
                "Arjun Kumar");

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16));

        name.setTextFill(
                Color.web(TEXT));

        Label rating = new Label(
                "★  4.9  (128 ratings)");

        rating.setFont(
                Font.font(
                        "Arial",
                        10));

        rating.setTextFill(
                Color.web(GREEN));

        driverInfo.getChildren().addAll(
                name,
                rating);

        driver.getChildren().addAll(
                avatar,
                driverInfo);

        card.getChildren().addAll(
                heading,
                driver,
                new Separator(),
                detailRow(
                        "Vehicle",
                        "MH-12-AX-4502"),
                detailRow(
                        "Type",
                        "Heavy Container"));

        Button contact = new Button(
                "☎   Contact Driver");

        contact.setMaxWidth(
                Double.MAX_VALUE);

        contact.setPrefHeight(
                40);

        contact.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11));

        contact.setStyle(
                "-fx-background-color: #E6E5E4;" +
                        "-fx-background-radius: 12;" +
                        "-fx-text-fill: #252525;" +
                        "-fx-cursor: hand;");

        card.getChildren().add(
                contact);

        return card;
    }

    // =========================================================
    // LOAD INVENTORY
    // =========================================================

    private VBox createLoadInventoryCard() {

        VBox card = createCard();

        Label heading = sectionTitle(
                "LOAD INVENTORY");

        VBox load = new VBox(5);

        load.setPadding(
                new Insets(13));

        load.setStyle(
                "-fx-background-color: #F8F4F3;" +
                        "-fx-background-radius: 12;");

        Label loadTitle = new Label(
                "LOAD TYPE");

        loadTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        9));

        loadTitle.setTextFill(
                Color.web(MUTED));

        Label loadValue = new Label(
                "Steel Coils");

        loadValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14));

        loadValue.setTextFill(
                Color.web(TEXT));

        load.getChildren().addAll(
                loadTitle,
                loadValue);

        HBox stats = new HBox(10);

        VBox weight = inventoryBox(
                "WEIGHT",
                "6 MT");

        VBox value = inventoryBox(
                "VALUE",
                "₹9,200");

        HBox.setHgrow(
                weight,
                Priority.ALWAYS);

        HBox.setHgrow(
                value,
                Priority.ALWAYS);

        stats.getChildren().addAll(
                weight,
                value);

        card.getChildren().addAll(
                heading,
                load,
                stats);

        return card;
    }

    // =========================================================
    // REAL-TIME UPDATES
    // =========================================================

    private VBox createUpdatesCard() {

        VBox card = createCard();

        Label heading = sectionTitle(
                "REAL-TIME UPDATES");

        VBox updates = new VBox();

        updates.getChildren().addAll(

                createUpdate(
                        "ETA Updated",
                        "Scheduled arrival at 04:30 PM",
                        "15 mins ago",
                        false),

                createUpdate(
                        "Vehicle Dispatched",
                        "En-route from Pune logistics hub",
                        "2h ago",
                        false),

                createUpdate(
                        "Pickup Completed",
                        "Steel coils loaded and verified",
                        "3h ago",
                        true));

        card.getChildren().addAll(
                heading,
                updates);

        return card;
    }

    // =========================================================
    // UPDATE
    // =========================================================

    private VBox createUpdate(
            String title,
            String description,
            String time,
            boolean last) {

        HBox row = new HBox();

        row.setAlignment(
                Pos.TOP_LEFT);

        StackPane timeline = new StackPane();

        timeline.setPrefWidth(
                24);

        timeline.setPrefHeight(
                70);

        if (!last) {

            Region vertical = new Region();

            vertical.setPrefWidth(
                    2);

            vertical.setPrefHeight(
                    62);

            vertical.setStyle(
                    "-fx-background-color: #A9D2B8;");

            StackPane.setAlignment(
                    vertical,
                    Pos.TOP_CENTER);

            StackPane.setMargin(
                    vertical,
                    new Insets(
                            8,
                            0,
                            0,
                            0));

            timeline.getChildren().add(
                    vertical);
        }

        Circle dot = new Circle(6);

        dot.setFill(
                Color.web(GREEN));

        StackPane.setAlignment(
                dot,
                Pos.TOP_CENTER);

        timeline.getChildren().add(
                dot);

        VBox content = new VBox(4);

        content.setPadding(
                new Insets(
                        0,
                        0,
                        12,
                        5));

        Label titleLabel = new Label(
                title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11));

        titleLabel.setTextFill(
                Color.web(TEXT));

        Label descriptionLabel = new Label(
                description);

        descriptionLabel.setFont(
                Font.font(
                        "Arial",
                        9));

        descriptionLabel.setTextFill(
                Color.web(MUTED));

        descriptionLabel.setWrapText(
                true);

        Label timeLabel = new Label(
                time);

        timeLabel.setFont(
                Font.font(
                        "Arial",
                        9));

        timeLabel.setTextFill(
                Color.web(MUTED));

        content.getChildren().addAll(
                titleLabel,
                descriptionLabel,
                timeLabel);

        row.getChildren().addAll(
                timeline,
                content);

        return new VBox(
                row);
    }

    // =========================================================
    // CARD
    // =========================================================

    private VBox createCard() {

        VBox card = new VBox(15);

        card.setPadding(
                new Insets(20));

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 16;" +
                        "-fx-border-color: " +
                        BORDER + ";" +
                        "-fx-border-radius: 16;" +
                        "-fx-effect: dropshadow(" +
                        "gaussian," +
                        "rgba(20,60,35,0.08)," +
                        "12,0,0,3" +
                        ");");

        return card;
    }

    // =========================================================
    // CARD HOVER
    // =========================================================

    private void addCardHoverEffect(
            VBox card,
            double radius) {

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: " +
                        radius + ";" +
                        "-fx-border-color: " +
                        BORDER + ";" +
                        "-fx-border-radius: " +
                        radius + ";" +
                        "-fx-effect: dropshadow(" +
                        "gaussian," +
                        "rgba(20,60,35,0.08)," +
                        "12,0,0,3" +
                        ");");

        card.setOnMouseEntered(
                e -> card.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-background-radius: " +
                                radius + ";" +
                                "-fx-border-color: #BFD9C8;" +
                                "-fx-border-radius: " +
                                radius + ";" +
                                "-fx-effect: dropshadow(" +
                                "gaussian," +
                                "rgba(8,122,54,0.16)," +
                                "18,0,0,4" +
                                ");"));

        card.setOnMouseExited(
                e -> card.setStyle(
                        "-fx-background-color: white;" +
                                "-fx-background-radius: " +
                                radius + ";" +
                                "-fx-border-color: " +
                                BORDER + ";" +
                                "-fx-border-radius: " +
                                radius + ";" +
                                "-fx-effect: dropshadow(" +
                                "gaussian," +
                                "rgba(20,60,35,0.08)," +
                                "12,0,0,3" +
                                ");"));
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

    private Label sectionTitle(
            String text) {

        Label label = new Label(
                text);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10));

        label.setTextFill(
                Color.web(MUTED));

        return label;
    }

    // =========================================================
    // METRIC
    // =========================================================

    private VBox metric(
            String title,
            String value) {

        VBox box = new VBox(2);

        box.setAlignment(
                Pos.CENTER_RIGHT);

        Label titleLabel = new Label(
                title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        8));

        titleLabel.setTextFill(
                Color.web(MUTED));

        Label valueLabel = new Label(
                value);

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16));

        valueLabel.setTextFill(
                Color.web(TEXT));

        box.getChildren().addAll(
                titleLabel,
                valueLabel);

        return box;
    }

    // =========================================================
    // FOOTER METRIC
    // =========================================================

    private VBox footerMetric(
            String title,
            String value,
            String unit) {

        VBox box = new VBox(5);

        Label titleLabel = new Label(
                title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        9));

        titleLabel.setTextFill(
                Color.web(MUTED));

        HBox valueBox = new HBox(5);

        Label valueLabel = new Label(
                value);

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18));

        valueLabel.setTextFill(
                Color.web(TEXT));

        Label unitLabel = new Label(
                unit);

        unitLabel.setFont(
                Font.font(
                        "Arial",
                        10));

        unitLabel.setTextFill(
                Color.web(MUTED));

        valueBox.getChildren().addAll(
                valueLabel,
                unitLabel);

        box.getChildren().addAll(
                titleLabel,
                valueBox);

        return box;
    }

    // =========================================================
    // DETAIL ROW
    // =========================================================

    private HBox detailRow(
            String leftText,
            String rightText) {

        HBox row = new HBox();

        Label left = new Label(
                leftText);

        left.setFont(
                Font.font(
                        "Arial",
                        10));

        left.setTextFill(
                Color.web(MUTED));

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        Label right = new Label(
                rightText);

        right.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10));

        right.setTextFill(
                Color.web(TEXT));

        row.getChildren().addAll(
                left,
                spacer,
                right);

        return row;
    }

    // =========================================================
    // INVENTORY BOX
    // =========================================================

    private VBox inventoryBox(
            String title,
            String value) {

        VBox box = new VBox(5);

        box.setPadding(
                new Insets(12));

        box.setStyle(
                "-fx-background-color: #F8F4F3;" +
                        "-fx-background-radius: 11;");

        Label titleLabel = new Label(
                title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        8));

        titleLabel.setTextFill(
                Color.web(MUTED));

        Label valueLabel = new Label(
                value);

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14));

        valueLabel.setTextFill(
                Color.web(TEXT));

        box.getChildren().addAll(
                titleLabel,
                valueLabel);

        return box;
    }

    // =========================================================
    // VIEW MAP BUTTON
    // =========================================================

    private void showMapMessage() {

        /*
         * The Live Location map is already visible in the page.
         * The button currently changes the active trip status
         * so it does not require MapView/WebView dependencies.
         */
    }
}