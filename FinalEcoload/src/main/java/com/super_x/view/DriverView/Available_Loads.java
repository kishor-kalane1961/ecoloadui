package com.super_x.view.DriverView;

import com.super_x.controller.usercontroller.LoadController;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import com.super_x.dao.driverdao.VehicleDAO;
import com.super_x.model.drivermodel.CurrentDriver;
import com.super_x.model.drivermodel.DriverModel;
import com.super_x.model.drivermodel.VehicleModel;
import javafx.scene.shape.StrokeLineCap;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import com.google.cloud.firestore.Firestore;
import com.super_x.config.FirebaseConfig;
import com.super_x.dao.driverdao.VehicleDAO;
import com.super_x.model.drivermodel.VehicleModel;


import com.super_x.model.usermodel.Load;
import com.super_x.view.HomePage;

/** Available-loads screen for the EcoLoad driver application. */
public class Available_Loads {


    
   
    private VehicleDAO vehicleDAO;
    private VehicleModel currentVehicle;
   
    private LoadController loadController =
        new LoadController();

private List<Load> firebaseLoads =
        new ArrayList<>();

    private static final String GREEN = "#09691A";
    private static final String DARK = "#182219";
    private static final String MUTED = "#657066";
    private static final String PAGE = "#F9F9F8";
    private static final String BORDER = "#E6E8E5";
    private static final String FaintGreen = "#228670";
    private final String BG = "#e6f1e8";

    // private final List<RowData> allLoads = List.of(
    //         new RowData("Tata Motors", "AVAILABLE", "Pune", "Nashik", "212 km", "~5h 30m", "Auto Parts",
    //                 "12 Tons · Closed Container", 24500, 115, 92),
    //         new RowData("GreenWay", "URGENT", "Chakan", "Nagpur", "710 km", "~14h 00m", "Industrial Oils",
    //                 "18 Tons · Tanker", 68200, 96, 80),
    //         new RowData("Reliant Foods", "AVAILABLE", "Hadapsar", "Mumbai", "148 km", "~3h 45m", "FMCG Goods",
    //                 "8 Tons · Open Body", 14800, 100, 66));

    private final VBox loadList = new VBox(18);
    private TextField pickup;
    private TextField destination;
    private ComboBox<String> sort;
    private ComboBox<String> vehicle;

   public Available_Loads() {
    Firestore firestore = FirebaseConfig.getFireStore();
    vehicleDAO = new VehicleDAO(firestore);
}

    public Scene getAvailable_LoadsScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG + ";");
        root.setLeft(sidebar());

        VBox main = new VBox(topBar(), mainContent());
        root.setCenter(main);
        loadCurrentDriverVehicle();
        loadFirebaseLoads();
        refresh(convertFirebaseLoads());
        return new Scene(root, 1536, 750, Color.web(PAGE));
    }

    private VBox sidebar() {
        return DriverNavigation.createSidebar("Available Loads");
    }

    private HBox topBar() {
        return DriverNavigation.createNavbar();
    }

    private VBox mainContent() {

        VBox page = new VBox(18);

        page.setPadding(new Insets(15, 20, 15, 20));

        page.setMaxWidth(Double.MAX_VALUE);
        page.setMaxHeight(Double.MAX_VALUE);

        Label heading = label(
                "Find Best Loads",
                28,
                "#111111",
                false);

        Label description = label(
                "Discover high-yield routes near your current location or planned destination.",
                15,
                "#333333",
                false);

        VBox header = new VBox(8, heading, description);

        ScrollPane loadScroll = new ScrollPane(loadList);

        loadScroll.setFitToWidth(true);
        loadScroll.setFitToHeight(false);

        loadScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        loadScroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        loadScroll.setMaxWidth(Double.MAX_VALUE);
        loadScroll.setMaxHeight(Double.MAX_VALUE);

        loadScroll.setStyle(
                "-fx-background: " + PAGE + ";" +
                        "-fx-background-color: " + PAGE + ";" +
                        "-fx-border-color: transparent;");

        VBox.setVgrow(loadScroll, Priority.ALWAYS);

        page.getChildren().addAll(
                header,
                filterCard(),
                stats(),
                loadScroll);

        return page;
    }
    private void loadCurrentDriverVehicle() {

    try {

        DriverModel currentDriver =
                CurrentDriver.getInstance().getDriver();

        if (currentDriver == null ||
                currentDriver.getEmail() == null ||
                currentDriver.getEmail().trim().isEmpty()) {

            System.out.println("Current driver not available.");
            currentVehicle = null;
            return;
        }

        String driverEmail =
                currentDriver.getEmail().trim();

        currentVehicle =
                vehicleDAO.getVehicleByDriverEmail(driverEmail);

        if (currentVehicle != null) {

            System.out.println(
                    "Current Driver Email: "
                            + driverEmail);

            System.out.println(
                    "Vehicle Type: "
                            + currentVehicle.getVehicleType());

        } else {

            System.out.println(
                    "Vehicle data not found for: "
                            + driverEmail);
        }

    } catch (Exception e) {

        e.printStackTrace();
        currentVehicle = null;
    }
}
    private void loadFirebaseLoads() {

    try {

        firebaseLoads = loadController.getAllLoads();

        System.out.println(
                "Total Loads from Firebase: "
                        + firebaseLoads.size()
        );

    } catch (Exception e) {

        e.printStackTrace();

        firebaseLoads = new ArrayList<>();
    }
}

private int calculateMatch(Load load) {

    if (load == null ||
            currentVehicle == null ||
            currentVehicle.getVehicleType() == null ||
            load.getTruckType() == null) {

        return 0;
    }

    String driverVehicle =
            currentVehicle.getVehicleType().trim();

    String requiredTruck =
            load.getTruckType().trim();

    System.out.println(
            "Driver Vehicle Type: " + driverVehicle
    );

    System.out.println(
            "Load Required Truck Type: " + requiredTruck
    );

    // Exact match
    if (driverVehicle.equalsIgnoreCase(requiredTruck)) {
        return 100;
    }

    // Generic Truck compatibility
    if (driverVehicle.equalsIgnoreCase("Truck")) {

        if (requiredTruck.equalsIgnoreCase("Container Truck")) {
            return 90;
        }

        if (requiredTruck.equalsIgnoreCase("Heavy Truck")) {
            return 90;
        }

        if (requiredTruck.equalsIgnoreCase("Medium Truck")) {
            return 80;
        }

        if (requiredTruck.equalsIgnoreCase("Pickup Truck")) {
            return 80;
        }

        if (requiredTruck.equalsIgnoreCase("LCV")) {
            return 70;
        }

        if (requiredTruck.equalsIgnoreCase("Mini Truck")) {
            return 60;
        }

        if (requiredTruck.equalsIgnoreCase("Trailer")) {
            return 90;
        }

        if (requiredTruck.equalsIgnoreCase("Tanker")) {
            return 90;
        }
    }

    return 0;
}
    private List<RowData> convertFirebaseLoads() {

    List<RowData> rows =
            new ArrayList<>();

    for (Load load : firebaseLoads) {

        // =====================================================
        // HIDE ACCEPTED / COMPLETED LOADS
        // =====================================================

        if ("ACCEPTED".equalsIgnoreCase(load.getStatus())
                || "COMPLETED".equalsIgnoreCase(load.getStatus())) {

            continue;
        }

        // =====================================================
        // LOAD STATUS
        // =====================================================

        String status =
                load.getStatus();

        if (status == null ||
                status.trim().isEmpty()) {

            status = "AVAILABLE";
        }

        // =====================================================
        // LOAD DETAILS
        // =====================================================

        String source =
                load.getPickupLocation();

        String destination =
                load.getDestination();

        String userEmail =
                load.getUserId();

        String receiverName =
                load.getReceiverName();

        String transporterName =
                load.getTransporterName();

        String truckType =
                load.getTruckType();

        String cargo =
                load.getLoadType();

        String weight =
                load.getWeight()
                        + " "
                        + load.getWeightUnit();

        int offer =
                (int) load.getOfferPrice();

        int match =
                calculateMatch(load);

        // =====================================================
        // ADD ROW
        // =====================================================

        rows.add(
                new RowData(
                        load.getLoadId(),
                        userEmail,
                        transporterName,
                        receiverName,
                        truckType,
                        status,
                        source,
                        destination,
                        "",
                        "",
                        cargo,
                        weight,
                        offer,
                        0,
                        match,
                        load
                )
        );
    }

    return rows;
}


    // =========================================================
    // REUSABLE CARD HOVER EFFECT
    // =========================================================

    private void addCardHoverEffect(Region card, int radius) {

        String normalStyle = "-fx-background-color: white;" +
                "-fx-background-radius: " + radius + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: " + radius + ";" +
                "-fx-cursor: hand;";

        String hoverStyle = "-fx-background-color: white;" +
                "-fx-background-radius: " + radius + ";" +
                "-fx-border-color: " + GREEN + ";" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: " + radius + ";" +
                "-fx-cursor: hand;";

        card.setStyle(normalStyle);

        card.setOnMouseEntered(e -> {
            card.setStyle(hoverStyle);
        });

        card.setOnMouseExited(e -> {
            card.setStyle(normalStyle);
        });
    }

    private VBox filterCard() {
        GridPane fields = new GridPane();
        fields.setHgap(12);
        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(25);
            column.setHgrow(Priority.ALWAYS);
            fields.getColumnConstraints().add(column);
        }
        pickup = field("");
        pickup.setPromptText("⌖  Enter the location");

        destination = field("");
        destination.setPromptText("➤  Enter the destination");

       vehicle = combo(
        List.of(
                "All",
                "Mini Truck",
                "Pickup Truck",
                "LCV",
                "Medium Truck",
                "Heavy Truck",
                "Trailer",
                "Container Truck",
                "Tanker"
        ));

        sort = combo(
                List.of(
                        "Highest Price First",
                        "Lowest Price First",
                        "Shortest Distance",
                        "Highest Match"));

        fields.add(fieldBox("Pickup Location", pickup), 0, 0);
        fields.add(fieldBox("Destination", destination), 1, 0);
        fields.add(fieldBox("Vehicle Type", vehicle), 2, 0);
        fields.add(fieldBox("Sort By", sort), 3, 0);

        Region line = new Region();
        line.setPrefHeight(1);
        line.setStyle("-fx-background-color: #D6D8D5;");

        Label reset = label("Reset Filters", 16, DARK, false);

        reset.setOnMouseClicked(e -> {
            pickup.clear();
            destination.clear();
            sort.getSelectionModel().selectFirst();
            refresh(convertFirebaseLoads());
        });

        Button search = button("⌕  Search Loads", GREEN, Color.WHITE);
        search.setPrefSize(195, 44);
        search.setOnAction(e -> applyFilters());

        Region grow = new Region();
        HBox.setHgrow(grow, Priority.ALWAYS);

        HBox actions = new HBox(18, reset, grow, search);
        actions.setAlignment(Pos.CENTER_LEFT);

        VBox card = new VBox(12, fields, line, actions);
        card.setPadding(new Insets(16));

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 24;" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-width: 1;" +
                        "-fx-border-radius: 24;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,.04), 22, .1, 0, 8);");

        return card;
    }

    private HBox stats() {
        HBox stats = new HBox(32);
        stats.getChildren().addAll(stat("▰", "NEARBY LOADS", "24", "#D8FFD9", false),
                stat("☆", "BEST OFFER", "₹32,400", "#EAF1EB", true),
                stat("♧", "AVG. DISTANCE", "420 km", "#E5E4E2", false),
                stat("▣", "EARNINGS\nPOTENTIAL", "₹2.4L", "#E5E4E2", false));
        return stats;
    }

    private VBox stat(String icon, String caption, String value,
            String color, boolean selected) {

        Label glyph = label(icon, 24, GREEN, false);
        glyph.setMinSize(50, 50);
        glyph.setAlignment(Pos.CENTER);

        glyph.setStyle(
                "-fx-background-color: " + color + ";" +
                        "-fx-background-radius: 14;");

        Label cap = label(caption, 12, DARK, false);

        Label amount = label(
                value,
                25,
                "#111111",
                true);
        VBox text = new VBox(3, cap, amount);

        HBox inside = new HBox(16, glyph, text);
        inside.setAlignment(Pos.CENTER_LEFT);

        VBox card = new VBox(inside);

        card.setPadding(new Insets(18));
        card.setPrefHeight(94);
        card.setMinHeight(94);
        card.setMaxHeight(94);

        card.setMinWidth(0);
        card.setPrefWidth(240);

        HBox.setHgrow(card, Priority.ALWAYS);

        addCardHoverEffect(card, 23);

        return card;
    }

    private void refresh(List<RowData> loads) {
        loadList.getChildren().clear();
        if (loads.isEmpty()) {
            loadList.getChildren().add(label("No loads found for the selected filters.", 18, MUTED, false));
            return;
        }
        loads.forEach(load -> loadList.getChildren().add(loadCard(load)));
    }

    private HBox loadCard(RowData row) {
     VBox identity = new VBox(
        5,
        label("Transporter: " + row.transporterName,
                15, "#111111", true),

        label(row.userEmail,
                12, "#657066", false),

        label("Receiver: " + row.receiverName,
                13, "#333333", false),

        label(row.company,
                11, "#657066", false),

        status(row.status)
);  
        identity.setMinWidth(170);
        identity.setPrefWidth(180);
        VBox route = column("Route", row.source + "  →  " + row.destination, row.distance + " · " + row.time);
        VBox details = column("Load Details", row.cargo, row.weight);
        VBox price = column("Offer Price", "₹" + String.format("%,d", row.offer), "₹" + row.rate + " / KM");
        route.setMinWidth(170);
        route.setPrefWidth(180);
        details.setMinWidth(170);
        details.setPrefWidth(185);
        price.setMinWidth(135);
        price.setPrefWidth(145);
        VBox match = match(row.match);
        Button accept = button("Accept Load", FaintGreen, Color.WHITE);

        accept.setPrefWidth(150);
        accept.setMinWidth(150);
        accept.setMaxWidth(150);

        accept.setCursor(Cursor.HAND);
        accept.setStyle(
                "-fx-background-color: " + FaintGreen + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-cursor: hand;");

        accept.setOnMouseEntered(e -> {
            accept.setStyle(
                    "-fx-background-color: " + GREEN + ";" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 10;");
        });
       AcceptLoad acceptLoad = new AcceptLoad();

accept.setOnAction(e -> {

    acceptLoad.show(
            HomePage.homeStage,
            row.load()
    );

    if (acceptLoad.isLoadAccepted()) {

        System.out.println(
                "Load accepted!"
        );

        // =========================================
        // REFRESH LOADS FROM FIREBASE
        // =========================================

        loadFirebaseLoads();

        // =========================================
        // REFRESH AVAILABLE LOAD LIST
        // ACCEPTED LOAD WILL DISAPPEAR
        // =========================================

        refresh(
                convertFirebaseLoads()
        );

    } else {

        System.out.println(
                "Load not accepted."
        );
    }
});

        accept.setOnMouseExited(e -> {
            accept.setStyle(
                    "-fx-background-color: " + FaintGreen + ";" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 10;" +
                            "-fx-cursor: hand;");
        });

        Button detailsButton = new Button("Details");
        detailsButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #1B261D; -fx-font-size: 16px;");
        detailsButton.setOnAction(
                e -> message("Load Details", row.cargo + "\n" + row.weight + "\n" + row.distance + " · " + row.time));
        VBox controls = new VBox(11, accept, detailsButton);
        controls.setAlignment(Pos.CENTER);
        controls.setMinWidth(150);
        controls.setPrefWidth(150);
        controls.setMaxWidth(150);
        detailsButton.setMinWidth(150);
        detailsButton.setPrefWidth(150);
        detailsButton.setMaxWidth(150);
        HBox card = new HBox(28, identity, route, details, price, divider(), match, controls);
        card.setCursor(Cursor.HAND);

        // Green border appears only while hovering.
        // This also fixes the old issue where the final setStyle()
        // below could overwrite the hover style.
        addCardHoverEffect(card, 24);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(15, 20, 15, 30));
        card.setPrefHeight(105);
        card.setMinHeight(105);
        card.setMaxHeight(105);
        HBox.setHgrow(identity, Priority.SOMETIMES);
        HBox.setHgrow(route, Priority.ALWAYS);
        HBox.setHgrow(details, Priority.ALWAYS);
        HBox.setHgrow(price, Priority.SOMETIMES);
        return card;
    }

    private VBox column(String title, String main, String sub) {
        Label top = label(title, 14, "#333333", false);
        Label body = label(main, 19, "#101010", true);
        Label bottom = label(sub, 14, "#444444", false);
        VBox box = new VBox(8, top, body, bottom);
        box.setMinWidth(135);
        box.setPrefWidth(150);
        HBox.setHgrow(box, Priority.ALWAYS);
        return box;
    }

  private VBox match(int value) {

    double size = 32;
    double stroke = 4;
    double circumference = 2 * Math.PI * size;

    // Background circle
    Circle base = new Circle(size);

    base.setFill(Color.TRANSPARENT);
    base.setStroke(Color.web("#E0E3DF"));
    base.setStrokeWidth(stroke);

    // Progress circle
    Circle progress = new Circle(size);

    progress.setFill(Color.TRANSPARENT);
    progress.setStroke(Color.web(GREEN));
    progress.setStrokeWidth(stroke);
    progress.setStrokeLineCap(StrokeLineCap.ROUND);

    progress.getStrokeDashArray().addAll(
        circumference,
        circumference
);

    progress.setStrokeDashOffset(
            circumference * (1 - value / 100.0)
    );

    // Start progress from top
    progress.setRotate(-90);

    VBox words = new VBox(
            0,
            label(
                    value + "%",
                    14,
                    GREEN,
                    true
            ),
            label(
                    "Match",
                    10,
                    DARK,
                    false
            )
    );

    words.setAlignment(Pos.CENTER);

    StackPane ring =
            new StackPane(
                    base,
                    progress,
                    words
            );

    ring.setPrefSize(68, 68);
    ring.setMinSize(68, 68);
    ring.setMaxSize(68, 68);

    VBox wrap =
            new VBox(ring);

    wrap.setAlignment(Pos.CENTER);

    wrap.setMinWidth(80);
    wrap.setPrefWidth(80);
    wrap.setMaxWidth(80);

    return wrap;
}
    private Label status(String text) {
        Label status = label(text, 11, "#124A20", false);
        status.setPadding(new Insets(5, 10, 5, 10));
        status.setStyle("-fx-background-color: " + ("URGENT".equals(text) ? "#FFD8D8" : "#D8FFD9")
                + "; -fx-background-radius: 12;");
        return status;
    }

    private Region divider() {
        Region line = new Region();
        line.setPrefWidth(1);
        line.setPrefHeight(91);
        line.setStyle("-fx-background-color: " + BORDER + ";");
        return line;
    }

    private VBox fieldBox(String name, javafx.scene.Node field) {
        VBox box = new VBox(7, label(name, 13, MUTED, false), field);
        box.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(box, Priority.ALWAYS);
        return box;
    }

    private TextField field(String value) {
        TextField f = new TextField(value);
        f.setPrefHeight(60);
        f.setMaxWidth(Double.MAX_VALUE);
        f.setStyle(inputStyle());
        return f;
    }

    private ComboBox<String> combo(List<String> values) {
        ComboBox<String> box = new ComboBox<>();
        box.getItems().addAll(values);
        box.getSelectionModel().selectFirst();
        box.setPrefHeight(60);
        box.setMaxWidth(Double.MAX_VALUE);
        box.setStyle(inputStyle());
        return box;
    }

    private String inputStyle() {
        return "-fx-background-color: #F8F5F5; -fx-border-color: #BAC4BA; -fx-border-radius: 15; -fx-background-radius: 15; -fx-padding: 0 16; -fx-font-size: 17px;";
    }

    private Label label(String text, double size, String color, boolean bold) {
        Label label = new Label(text);
        label.setTextFill(Color.web(color));
        label.setFont(Font.font("Segoe UI",
                bold ? javafx.scene.text.FontWeight.BOLD : javafx.scene.text.FontWeight.NORMAL, size));
        return label;
    }

    private Button button(String text, String background, Color foreground) {
        Button button = new Button(text);
        button.setTextFill(foreground);
        button.setFont(Font.font("Segoe UI", javafx.scene.text.FontWeight.BOLD, 16));
        button.setPadding(new Insets(13, 22, 13, 22));
        button.setStyle("-fx-background-color: " + background + "; -fx-background-radius: 12; -fx-cursor: hand;");
        return button;
    }

    private void message(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, content);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void applyFilters() {
        String from = pickup.getText().replace("⌖", "").trim().toLowerCase(Locale.ROOT);
        String to = destination.getText().replace("➤", "").trim().toLowerCase(Locale.ROOT);
       List<RowData> result = convertFirebaseLoads().stream()
        .filter(x -> from.isEmpty()
                || x.source.toLowerCase(Locale.ROOT).contains(from))
        .filter(x -> to.isEmpty()
                || x.destination.toLowerCase(Locale.ROOT).contains(to))
        .filter(x ->
                vehicle.getValue() == null
                        || vehicle.getValue().equals("All")
                        || x.truckType.equalsIgnoreCase(vehicle.getValue()))
        .collect(Collectors.toList());
        Comparator<RowData> order = switch (sort.getValue()) {
            case "Lowest Price First" -> Comparator.comparingInt(x -> x.offer);
            case "Shortest Distance" -> Comparator.comparingInt(x -> Integer.parseInt(x.distance.split(" ")[0]));
            case "Highest Match" -> Comparator.comparingInt((RowData x) -> x.match).reversed();
            default -> Comparator.comparingInt((RowData x) -> x.offer).reversed();
        };
        result.sort(order);
        refresh(result);
    }

    private record RowData(String company,String userEmail,String transporterName,String receiverName,String truckType, String status, String source, String destination, String distance,
            String time, String cargo, String weight, int offer, int rate, int match,Load load) {
    }
}