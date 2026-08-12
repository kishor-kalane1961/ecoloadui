
package com.super_x.view.AdminView;

import com.super_x.*;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SOSAlerts {

    private NavigationService navigationService;

    public void setNavigationService(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    private void navigateTo(String pageName) {
        if (navigationService != null) {
            navigationService.navigate(pageName);
        }
    }

    // =====================================================
    // COLORS
    // =====================================================
     private final String DARK_GREEN = "#004B3A";

    // Light green when cursor is on button
    // private final String HOVER_GREEN = "#075E49";

    // Dark green when button is clicked
    private final String CLICK_GREEN = "#075E49";

    // private final String NUMBER_GREEN = "#2D9950";

    private final String BG_COLOR = "#EEF8F4";

    // private final String BORDER_COLOR = "#D5DBDE";

    // private final String HOVER_BORDER = "#075E49";

    private final String GRAY_COLOR = "#697278";
     private final String TEXT_COLOR = "#171A1C";
    
/* 
    private final String DARK_GREEN = "#004B3A";
    private final String ACTIVE_GREEN = "#075E49";
    private final String CLICK_GREEN = "#003528";

    private final String BG_COLOR = "#E3F2EC";
    private final String GRAY_COLOR = "#697278";
    private final String BORDER = "#D9E0E2";
    */

    // =====================================================
    // DATA
    // =====================================================

    private final List<SOSData> allAlerts = new ArrayList<>();

    private GridPane sosGrid;

    private Label totalLabel;
    private Label activeLabel;
    private Label resolvedLabel;
    private Label responseLabel;

    // =====================================================
    // SOS DATA CLASS
    // =====================================================

    private static class SOSData {

        String id;
        String driver;
        String phone;
        String location;
        String time;
        String status;
        String priority;

        SOSData(
                String id,
                String driver,
                String phone,
                String location,
                String time,
                String status,
                String priority
        ) {
            this.id = id;
            this.driver = driver;
            this.phone = phone;
            this.location = location;
            this.time = time;
            this.status = status;
            this.priority = priority;
        }
    }

    // =====================================================
    // GET SCENE
    // =====================================================

    public Scene getSOSAlertsScene() {

        loadSOSData();

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG_COLOR + ";"
        );

        // =================================================
        // SIDEBAR
        // =================================================

        VBox sidebar = createSidebar();

        root.setLeft(sidebar);

        // =================================================
        // RIGHT SIDE
        // =================================================

        VBox rightSide = new VBox();

        HBox topBar = createTopBar();

        VBox content = createMainContent();

        VBox.setVgrow(
                content,
                Priority.ALWAYS
        );

        rightSide.getChildren().addAll(
                topBar,
                content
        );

        root.setCenter(rightSide);

        return new Scene(root);
    }

    // =====================================================
    // START
    // =====================================================

    public void start(Stage stage) {

        Scene scene = getSOSAlertsScene();

        stage.setScene(scene);

        stage.setTitle(
                "EcoLoad - SOS Alerts"
        );

        stage.setMinWidth(1000);
        stage.setMinHeight(650);

        stage.setResizable(true);
        stage.setMaximized(true);

        stage.show();
    }

    // =====================================================
    // LOAD DATA
    // =====================================================

    private void loadSOSData() {

        allAlerts.clear();

        allAlerts.add(
                new SOSData(
                        "#SOS-1001",
                        "Ramesh Patil",
                        "+91 98765 43210",
                        "Pune, Maharashtra",
                        "10:32 AM",
                        "Active",
                        "High"
                )
        );

        allAlerts.add(
                new SOSData(
                        "#SOS-1002",
                        "Suresh Yadav",
                        "+91 87654 32109",
                        "Mumbai, Maharashtra",
                        "09:48 AM",
                        "Active",
                        "Critical"
                )
        );

        allAlerts.add(
                new SOSData(
                        "#SOS-1003",
                        "Mahesh Kumar",
                        "+91 76543 21098",
                        "Nashik, Maharashtra",
                        "08:15 AM",
                        "Resolved",
                        "Medium"
                )
        );

        allAlerts.add(
                new SOSData(
                        "#SOS-1004",
                        "Ajay Chauhan",
                        "+91 99887 66554",
                        "Satara, Maharashtra",
                        "Yesterday",
                        "Resolved",
                        "High"
                )
        );

        allAlerts.add(
                new SOSData(
                        "#SOS-1005",
                        "Vikram Singh",
                        "+91 88776 65544",
                        "Kolhapur, Maharashtra",
                        "Yesterday",
                        "Active",
                        "Critical"
                )
        );
    }

    // =====================================================
    // SIDEBAR
    // =====================================================

    private VBox createSidebar() {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(250);
        sidebar.setMinWidth(220);
        sidebar.setMaxWidth(300);

        sidebar.setPadding(
                new Insets(
                        25,
                        16,
                        20,
                        16
                )
        );

        sidebar.setStyle(
                "-fx-background-color: " +
                DARK_GREEN +
                ";"
        );

        // =================================================
        // LOGO
        // =================================================

        HBox logoBox = new HBox(12);

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        ImageView logoImageView =
                createSidebarLogo();

        VBox logoText = new VBox(1);

        logoText.setAlignment(
                Pos.CENTER_LEFT
        );

        Label ecoLoad =
                new Label("EcoLoad");

        ecoLoad.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25
                )
        );

        ecoLoad.setTextFill(
                Color.WHITE
        );

        Label precision =
                new Label(
                        "PRECISION LOGISTICS"
                );

        precision.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        precision.setTextFill(
                Color.web("#A7C7BC")
        );

        logoText.getChildren().addAll(
                ecoLoad,
                precision
        );

        logoBox.getChildren().addAll(
                logoImageView,
                logoText
        );

        // =================================================
        // MENU
        // =================================================

        VBox menu = new VBox(5);

        menu.setPadding(
                new Insets(
                        45,
                        0,
                        0,
                        0
                )
        );

        Button dashboardButton =
                createMenuButton(
                        "▦",
                        "Dashboard",
                        false
                );
        dashboardButton.setOnAction(event -> navigateTo("dashboard"));

        Button driverButton =
                createMenuButton(
                        "▰",
                        "Drivers & Transporters",
                        false
                );
        driverButton.setOnAction(event -> navigateTo("drivers"));

        Button loadsButton =
                createMenuButton(
                        "♧",
                        "Loads & Trips",
                        false
                );
        loadsButton.setOnAction(event -> navigateTo("loads"));

        Button reportsButton =
                createMenuButton(
                        "▥",
                        "Reports",
                        false
                );
        reportsButton.setOnAction(event -> navigateTo("reports"));

        Button sosButton =
                createMenuButton(
                        "◇",
                        "SOS Alerts",
                        true
                );
        sosButton.setOnAction(event -> navigateTo("alerts"));

        Button supportButton =
                createMenuButton(
                        "♧",
                        "Support",
                        false
                );
        supportButton.setOnAction(event -> navigateTo("support"));

        menu.getChildren().addAll(
                dashboardButton,
                driverButton,
                loadsButton,
                reportsButton,
                sosButton,
                supportButton
        );

        // =================================================
        // SPACER
        // =================================================

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        // =================================================
        // LOGOUT
        // =================================================

        Button logout =
                createMenuButton(
                        "↪",
                        "Logout",
                        false
                );

        logout.setStyle(
                "-fx-background-color: #C62828;" +
                "-fx-background-radius: 9;"
        );

        sidebar.getChildren().addAll(
                logoBox,
                menu,
                spacer,
                logout
        );

        return sidebar;
    }

    // =====================================================
    // SIDEBAR LOGO
    // =====================================================

    private ImageView createSidebarLogo() {

        ImageView logoView =
                new ImageView();

        String imagePath =
                "/assets/Logo-removebg-preview.png";

        InputStream logoStream =
                getClass().getResourceAsStream(
                        imagePath
                );

        if (logoStream != null) {

            Image logoImage =
                    new Image(logoStream);

            logoView.setImage(
                    logoImage
            );

            logoView.setFitWidth(70);
            logoView.setFitHeight(70);

            logoView.setPreserveRatio(true);
            logoView.setSmooth(true);

        } else {

            System.out.println(
                    "ERROR: Logo not found: " +
                    imagePath
            );
        }

        return logoView;
    }

    // =====================================================
    // MENU BUTTON
    // =====================================================

    private Button createMenuButton(
            String icon,
            String text,
            boolean active
    ) {

        Button button = new Button();

        button.setPrefHeight(48);
        button.setMinHeight(44);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setPrefWidth(28);

        iconLabel.setFont(
                Font.font(20)
        );

        iconLabel.setTextFill(
                Color.WHITE
        );

        Label textLabel =
                new Label(text);

        textLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        textLabel.setTextFill(
                Color.WHITE
        );

        HBox box =
                new HBox(
                        12,
                        iconLabel,
                        textLabel
                );

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setGraphic(box);

        setMenuButtonStyle(
                button,
                active
        );

        button.setOnMouseEntered(event -> {

            button.setStyle(
                    "-fx-background-color: " +
                    "#075E49" +
                    ";" +
                    "-fx-background-radius: 9;"
            );

            ScaleTransition grow =
                    new ScaleTransition(
                            Duration.millis(120),
                            button
                    );

            grow.setToX(1.02);
            grow.setToY(1.02);

            grow.play();
        });

        button.setOnMouseExited(event -> {

            setMenuButtonStyle(
                    button,
                    active
            );

            ScaleTransition shrink =
                    new ScaleTransition(
                            Duration.millis(120),
                            button
                    );

            shrink.setToX(1.0);
            shrink.setToY(1.0);

            shrink.play();
        });

        button.setOnAction(event -> {

            System.out.println(
                    text + " clicked"
            );
        });

        return button;
    }

    // =====================================================
    // MENU STYLE
    // =====================================================

    private void setMenuButtonStyle(
            Button button,
            boolean active
    ) {

        if (active) {

            button.setStyle(
                    "-fx-background-color: " +
                    CLICK_GREEN +
                    ";" +
                    "-fx-background-radius: 9;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: " +
                    DARK_GREEN +
                    ";" +
                    "-fx-background-radius: 9;"
            );
        }
    }

    // =====================================================
    // TOP BAR
    // =====================================================

    private HBox createTopBar() {

        HBox topBar =
                new HBox();

        topBar.setPrefHeight(70);
        topBar.setMinHeight(65);
        topBar.setMaxHeight(75);

        topBar.setPadding(
                new Insets(
                        10,
                        25,
                        10,
                        25
                )
        );

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #D7DCDE;" +
                "-fx-border-width: 0 0 1 0;"
        );

        // SEARCH

        HBox searchBox =
                new HBox();

        searchBox.setPrefWidth(400);
        searchBox.setMinWidth(250);
        searchBox.setMaxWidth(500);

        searchBox.setPrefHeight(44);

        searchBox.setAlignment(
                Pos.CENTER_LEFT
        );

        searchBox.setPadding(
                new Insets(
                        0,
                        12,
                        0,
                        12
                )
        );

        searchBox.setStyle(
                "-fx-background-color: #F8FAFA;" +
                "-fx-border-color: #C8CFD2;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );

        Label searchIcon =
                new Label("⌕");

        searchIcon.setFont(
                Font.font(28)
        );

        searchIcon.setTextFill(
                Color.web("#59646A")
        );

        TextField search =
                new TextField();

        search.setPromptText(
                "Search trips, loads, drivers..."
        );

        search.setPrefHeight(40);

        search.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-font-size: 15px;"
        );

        HBox.setHgrow(
                search,
                Priority.ALWAYS
        );

        searchBox.getChildren().addAll(
                searchIcon,
                search
        );

        // SPACER

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // DIVIDER

        Region divider =
                new Region();

        divider.setPrefWidth(1);
        divider.setPrefHeight(35);

        divider.setStyle(
                "-fx-background-color: #C8CED0;"
        );

        // PROFILE

        VBox profileText =
                new VBox(1);

        profileText.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label adminProfile =
                new Label(
                        "Admin Profile"
                );

        adminProfile.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        adminProfile.setTextFill(
                Color.web("#171A1C")
        );

        Label adminRole =
                new Label(
                        "System Administrator"
                );

        adminRole.setFont(
                Font.font(12)
        );

        adminRole.setTextFill(
                Color.web(GRAY_COLOR)
        );

        profileText.getChildren().addAll(
                adminProfile,
                adminRole
        );

        Button profileButton =
                createAdminProfileImage();

        topBar.getChildren().addAll(
                searchBox,
                spacer,
                divider,
                profileText,
                profileButton
        );

        HBox.setMargin(
                divider,
                new Insets(
                        0,
                        20,
                        0,
                        0
                )
        );

        HBox.setMargin(
                profileText,
                new Insets(
                        0,
                        15,
                        0,
                        0
                )
        );

        return topBar;
    }

    // =====================================================
    // PROFILE IMAGE
    // =====================================================

    private Button createAdminProfileImage() {

        Button profileButton =
                new Button();

        profileButton.setPrefSize(
                45,
                45
        );

        profileButton.setMinSize(
                45,
                45
        );

        profileButton.setMaxSize(
                45,
                45
        );

        setProfileButtonNormalStyle(
                profileButton
        );

        String imagePath =
                "/assets/Admin_Profile_Logo.png";

        InputStream profileStream =
                getClass().getResourceAsStream(
                        imagePath
                );

        if (profileStream != null) {

            Image profileImage =
                    new Image(profileStream);

            ImageView profileImageView =
                    new ImageView(profileImage);

            profileImageView.setFitWidth(45);
            profileImageView.setFitHeight(45);

            profileImageView.setPreserveRatio(false);
            profileImageView.setSmooth(true);

            Circle clip =
                    new Circle(
                            22.5,
                            22.5,
                            22.5
                    );

            profileImageView.setClip(clip);

            profileButton.setGraphic(
                    profileImageView
            );

        } else {

            Label profileIcon =
                    new Label("♙");

            profileIcon.setFont(
                    Font.font(24)
            );

            profileButton.setGraphic(
                    profileIcon
            );
        }

        profileButton.setOnMouseEntered(event -> {

            profileButton.setStyle(
                    "-fx-background-color: #E4F0EB;" +
                    "-fx-border-color: #075E49;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 50;" +
                    "-fx-background-radius: 50;" +
                    "-fx-padding: 0;" +
                    "-fx-cursor: hand;"
            );

            ScaleTransition grow =
                    new ScaleTransition(
                            Duration.millis(120),
                            profileButton
                    );

            grow.setToX(1.08);
            grow.setToY(1.08);

            grow.play();
        });

        profileButton.setOnMouseExited(event -> {

            setProfileButtonNormalStyle(
                    profileButton
            );

            ScaleTransition shrink =
                    new ScaleTransition(
                            Duration.millis(120),
                            profileButton
                    );

            shrink.setToX(1.0);
            shrink.setToY(1.0);

            shrink.play();
        });

        profileButton.setOnAction(event -> {

            System.out.println(
                    "Admin Profile clicked"
            );
        });

        return profileButton;
    }

    // =====================================================
    // PROFILE NORMAL STYLE
    // =====================================================

    private void setProfileButtonNormalStyle(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-padding: 0;" +
                "-fx-background-radius: 50;" +
                "-fx-border-radius: 50;" +
                "-fx-cursor: hand;"
        );
    }

    // =====================================================
    // MAIN CONTENT
    // =====================================================

    private VBox createMainContent() {

        VBox content =
                new VBox(18);

        content.setPadding(
                new Insets(
                        25,
                        30,
                        20,
                        30
                )
        );

        Label title =
                new Label(
                        "SOS Alerts"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );

        title.setTextFill(
                Color.web(TEXT_COLOR)
        );

        Label subtitle =
                new Label(
                        "Monitor and respond to emergency alerts in real time"
                );

        subtitle.setFont(
                Font.font(14)
        );

        subtitle.setTextFill(
                Color.web("#657078")
        );

        VBox titleBox =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        // STAT CARDS

        HBox cards =
                createStatCards();

        // ACTIVE TITLE

        HBox activeTitle =
                new HBox(8);

        activeTitle.setAlignment(
                Pos.CENTER_LEFT
        );

        Label activeIcon =
                new Label("●");

        activeIcon.setFont(
                Font.font(17)
        );

        activeIcon.setTextFill(
                Color.web("#D32F2F")
        );

        Label activeText =
                new Label(
                        "Active Emergency Alerts"
                );

        activeText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        activeText.setTextFill(
                Color.web("#172026")
        );

        activeTitle.getChildren().addAll(
                activeIcon,
                activeText
        );

        // ACTIVE ALERT CARDS

        HBox alertCards =
                createActiveAlertCards();

        // TABLE

        VBox table =
                createAlertsTable();

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        content.getChildren().addAll(
                titleBox,
                cards,
                activeTitle,
                alertCards,
                table
        );

        return content;
    }

    // =====================================================
    // STAT CARDS
    // =====================================================

    private HBox createStatCards() {

        HBox cards =
                new HBox(15);

        cards.setMaxWidth(
                Double.MAX_VALUE
        );

        VBox total =
                createStatCard(
                        "Total Alerts",
                        String.valueOf(allAlerts.size()),
                        "Total emergency alerts",
                        "#E8F1FA",
                        "#2463A4",
                        "TOTAL"
                );

        VBox active =
                createStatCard(
                        "Active",
                        String.valueOf(countActive()),
                        "Need immediate attention",
                        "#FBE8E8",
                        "#D32F2F",
                        "ACTIVE"
                );

        VBox resolved =
                createStatCard(
                        "Resolved",
                        String.valueOf(countResolved()),
                        "Successfully handled",
                        "#E7F5EC",
                        "#21824A",
                        "RESOLVED"
                );

        VBox response =
                createStatCard(
                        "Avg. Response",
                        "4.2 min",
                        "Average response time",
                        "#FFF3DF",
                        "#D27A12",
                        "RESPONSE"
                );

        totalLabel = findValueLabel(total);
        activeLabel = findValueLabel(active);
        resolvedLabel = findValueLabel(resolved);
        responseLabel = findValueLabel(response);

        cards.getChildren().addAll(
                total,
                active,
                resolved,
                response
        );

        /*
         * IMPORTANT:
         *
         * cards.getChildren() returns ObservableList<Node>.
         *
         * So Node must be used here.
         *
         * NOT:
         *
         * for (Region card : cards.getChildren())
         *
         * Correct:
         */

        for (Node node : cards.getChildren()) {

            HBox.setHgrow(
                    node,
                    Priority.ALWAYS
            );
        }

        return cards;
    }

    // =====================================================
    // COUNT ACTIVE
    // =====================================================

    private int countActive() {

        int count = 0;

        for (SOSData alert : allAlerts) {

            if ("Active".equals(alert.status)) {
                count++;
            }
        }

        return count;
    }

    // =====================================================
    // COUNT RESOLVED
    // =====================================================

    private int countResolved() {

        int count = 0;

        for (SOSData alert : allAlerts) {

            if ("Resolved".equals(alert.status)) {
                count++;
            }
        }

        return count;
    }

    // =====================================================
    // STAT CARD
    // =====================================================

    private VBox createStatCard(
            String title,
            String value,
            String subtitle,
            String iconBackground,
            String valueColor,
            String cardType
    ) {

        VBox card =
                new VBox(6);

        card.setPadding(
                new Insets(16)
        );

        card.setMinHeight(110);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setCursor(
                javafx.scene.Cursor.HAND
        );

        setStatCardNormalStyle(
                card
        );

        HBox top =
                new HBox();

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon =
                new Label("●");

        icon.setFont(
                Font.font(14)
        );

        icon.setTextFill(
                Color.web(valueColor)
        );

        StackPane iconBox =
                new StackPane(icon);

        iconBox.setPrefSize(
                34,
                34
        );

        iconBox.setMinSize(
                34,
                34
        );

        iconBox.setStyle(
                "-fx-background-color: " +
                iconBackground +
                ";" +
                "-fx-background-radius: 8;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        titleLabel.setTextFill(
                Color.web("#657078")
        );

        top.getChildren().addAll(
                iconBox,
                titleLabel
        );

        top.setSpacing(10);

        Label valueLabel =
                new Label(value);

        valueLabel.setId(
                "valueLabel"
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25
                )
        );

        valueLabel.setTextFill(
                Color.web(valueColor)
        );

        Label sub =
                new Label(subtitle);

        sub.setFont(
                Font.font(11)
        );

        sub.setTextFill(
                Color.web("#697278")
        );

        card.getChildren().addAll(
                top,
                valueLabel,
                sub
        );

        // =================================================
        // INTERACTIVE HOVER
        // =================================================

        card.setOnMouseEntered(event -> {

            card.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: " +
                    valueColor +
                    ";" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 12;" +
                    "-fx-background-radius: 12;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 12, 0.2, 0, 4);"
            );

            ScaleTransition grow =
                    new ScaleTransition(
                            Duration.millis(150),
                            card
                    );

            grow.setToX(1.025);
            grow.setToY(1.025);

            grow.play();
        });

        card.setOnMouseExited(event -> {

            setStatCardNormalStyle(
                    card
            );

            ScaleTransition shrink =
                    new ScaleTransition(
                            Duration.millis(150),
                            card
                    );

            shrink.setToX(1.0);
            shrink.setToY(1.0);

            shrink.play();
        });

        // =================================================
        // CLICK
        // =================================================

        card.setOnMouseClicked(event -> {

            handleStatCardClick(
                    cardType
            );
        });

        return card;
    }

    // =====================================================
    // NORMAL STAT CARD STYLE
    // =====================================================

    private void setStatCardNormalStyle(
            VBox card
    ) {

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                "#D9E0E2" +
                ";" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 5, 0.1, 0, 2);"
        );
    }

    // =====================================================
    // STAT CARD CLICK
    // =====================================================

    private void handleStatCardClick(
            String type
    ) {

        switch (type) {

            case "TOTAL":

                showInformation(
                        "Total SOS Alerts",
                        "Total emergency alerts: " +
                        allAlerts.size()
                );

                break;

            case "ACTIVE":

                showInformation(
                        "Active SOS Alerts",
                        "Alerts requiring immediate attention: " +
                        countActive()
                );

                break;

            case "RESOLVED":

                showInformation(
                        "Resolved SOS Alerts",
                        "Successfully handled alerts: " +
                        countResolved()
                );

                break;

            case "RESPONSE":

                showInformation(
                        "Average Response",
                        "Current average response time: 4.2 minutes"
                );

                break;

            default:
                break;
        }
    }

    // =====================================================
    // FIND VALUE LABEL
    // =====================================================

    private Label findValueLabel(
            VBox card
    ) {

        for (Node node :
                card.getChildren()) {

            if (node instanceof Label) {

                Label label =
                        (Label) node;

                if ("valueLabel".equals(
                        label.getId()
                )) {

                    return label;
                }
            }
        }

        return null;
    }

    // =====================================================
    // ACTIVE ALERT CARDS
    // =====================================================

    private HBox createActiveAlertCards() {

        HBox cards =
                new HBox(15);

        cards.setMaxWidth(
                Double.MAX_VALUE
        );

        for (SOSData alert :
                allAlerts) {

            if (!"Active".equals(
                    alert.status
            )) {
                continue;
            }

            VBox card =
                    createActiveAlertCard(
                            alert
                    );

            cards.getChildren().add(
                    card
            );
        }

        for (Node node :
                cards.getChildren()) {

            HBox.setHgrow(
                    node,
                    Priority.ALWAYS
            );
        }

        return cards;
    }

    // =====================================================
    // SINGLE ACTIVE ALERT CARD
    // =====================================================

    private VBox createActiveAlertCard(
            SOSData alert
    ) {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(15)
        );

        card.setMinHeight(135);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setCursor(
                javafx.scene.Cursor.HAND
        );

        setAlertCardNormalStyle(
                card
        );

        // HEADING

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label sos =
                new Label("SOS");

        sos.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        sos.setTextFill(
                Color.WHITE
        );

        sos.setPadding(
                new Insets(
                        5,
                        9,
                        5,
                        9
                )
        );

        sos.setStyle(
                "-fx-background-color: #D32F2F;" +
                "-fx-background-radius: 6;"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label priority =
                new Label(
                        alert.priority
                );

        priority.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        priority.setTextFill(
                Color.web("#D32F2F")
        );

        heading.getChildren().addAll(
                sos,
                spacer,
                priority
        );

        Label driver =
                new Label(
                        alert.driver
                );

        driver.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        driver.setTextFill(
                Color.web("#172026")
        );

        Label location =
                new Label(
                        "📍 " +
                        alert.location
                );

        location.setFont(
                Font.font(12)
        );

        location.setTextFill(
                Color.web("#657078")
        );

        Label time =
                new Label(
                        "Time: " +
                        alert.time
                );

        time.setFont(
                Font.font(11)
        );

        time.setTextFill(
                Color.web("#697278")
        );

        Button view =
                new Button(
                        "View Details"
                );

        view.setMaxWidth(
                Double.MAX_VALUE
        );

        view.setStyle(
                "-fx-background-color: #004B3A;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 6;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        view.setOnAction(
                event ->
                        showAlertDetails(alert)
        );

        card.getChildren().addAll(
                heading,
                driver,
                location,
                time,
                view
        );

        // =================================================
        // CARD HOVER
        // =================================================

        card.setOnMouseEntered(event -> {

            card.setStyle(
                    "-fx-background-color: #FFFFFF;" +
                    "-fx-border-color: #D32F2F;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 12;" +
                    "-fx-background-radius: 12;" +
                    "-fx-effect: dropshadow(gaussian, rgba(211,47,47,0.22), 14, 0.25, 0, 4);"
            );

            ScaleTransition grow =
                    new ScaleTransition(
                            Duration.millis(150),
                            card
                    );

            grow.setToX(1.025);
            grow.setToY(1.025);

            grow.play();
        });

        card.setOnMouseExited(event -> {

            setAlertCardNormalStyle(
                    card
            );

            ScaleTransition shrink =
                    new ScaleTransition(
                            Duration.millis(150),
                            card
                    );

            shrink.setToX(1.0);
            shrink.setToY(1.0);

            shrink.play();
        });

        // =================================================
        // CARD CLICK
        // =================================================

        card.setOnMouseClicked(event -> {

            if (event.getTarget() instanceof Button) {
                return;
            }

            showAlertDetails(
                    alert
            );
        });

        return card;
    }

    // =====================================================
    // ALERT CARD NORMAL STYLE
    // =====================================================

    private void setAlertCardNormalStyle(
            VBox card
    ) {

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E6CACA;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 5, 0.1, 0, 2);"
        );
    }

    // =====================================================
    // TABLE
    // =====================================================

    private VBox createAlertsTable() {

        VBox tableBox =
                new VBox();

        tableBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                "#D9E0E2" +
                ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        HBox titleRow =
                new HBox();

        titleRow.setPadding(
                new Insets(
                        14,
                        18,
                        10,
                        18
                )
        );

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label(
                        "SOS Alerts Table"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        title.setTextFill(
                Color.web("#172026")
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button refresh =
                new Button(
                        "↻ Refresh"
                );

        refresh.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #83A99A;" +
                "-fx-text-fill: #176C4F;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 7 14 7 14;" +
                "-fx-cursor: hand;"
        );

        refresh.setOnAction(
                event ->
                        refreshTable()
        );

        titleRow.getChildren().addAll(
                title,
                spacer,
                refresh
        );

        // GRID

        sosGrid =
                new GridPane();

        sosGrid.setMaxWidth(
                Double.MAX_VALUE
        );

        sosGrid.getColumnConstraints().addAll(
                percentage(10),
                percentage(15),
                percentage(15),
                percentage(19),
                percentage(12),
                percentage(12),
                percentage(17)
        );

        addSOSHeaders();

        addSOSRows();

        // SCROLL

        ScrollPane scrollPane =
                new ScrollPane(
                        sosGrid
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        tableBox.getChildren().addAll(
                titleRow,
                scrollPane
        );

        return tableBox;
    }

    // =====================================================
    // TABLE HEADERS
    // =====================================================

    private void addSOSHeaders() {

        addTableHeader(
                sosGrid,
                "ID",
                0
        );

        addTableHeader(
                sosGrid,
                "DRIVER",
                1
        );

        addTableHeader(
                sosGrid,
                "PHONE",
                2
        );

        addTableHeader(
                sosGrid,
                "LOCATION",
                3
        );

        addTableHeader(
                sosGrid,
                "TIME",
                4
        );

        addTableHeader(
                sosGrid,
                "STATUS",
                5
        );

        addTableHeader(
                sosGrid,
                "ACTION",
                6
        );
    }

    // =====================================================
    // TABLE ROWS
    // =====================================================

    private void addSOSRows() {

        int row = 1;

        for (SOSData alert :
                allAlerts) {

            addSOSRow(
                    alert,
                    row
            );

            row++;
        }
    }

    // =====================================================
    // ADD ROW
    // =====================================================

    private void addSOSRow(
            SOSData alert,
            int row
    ) {

        addCell(
                sosGrid,
                alert.id,
                0,
                row
        );

        addCell(
                sosGrid,
                alert.driver,
                1,
                row
        );

        addCell(
                sosGrid,
                alert.phone,
                2,
                row
        );

        addCell(
                sosGrid,
                alert.location,
                3,
                row
        );

        addCell(
                sosGrid,
                alert.time,
                4,
                row
        );

        addStatusCell(
                sosGrid,
                alert.status,
                5,
                row
        );

        addActionCell(
                sosGrid,
                alert,
                6,
                row
        );
    }

    // =====================================================
    // COLUMN WIDTH
    // =====================================================

    private ColumnConstraints percentage(
            double value
    ) {

        ColumnConstraints c =
                new ColumnConstraints();

        c.setPercentWidth(
                value
        );

        return c;
    }

    // =====================================================
    // TABLE HEADER
    // =====================================================

    private void addTableHeader(
            GridPane grid,
            String text,
            int column
    ) {

        Label label =
                new Label(text);

        label.setMaxWidth(
                Double.MAX_VALUE
        );

        label.setPrefHeight(38);

        label.setAlignment(
                Pos.CENTER_LEFT
        );

        label.setPadding(
                new Insets(
                        0,
                        7,
                        0,
                        10
                )
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        label.setTextFill(
                Color.web("#59646A")
        );

        label.setStyle(
                "-fx-background-color: #F6F8F8;" +
                "-fx-border-color: #E0E5E6;" +
                "-fx-border-width: 1 0 1 0;"
        );

        grid.add(
                label,
                column,
                0
        );
    }

    // =====================================================
    // NORMAL CELL
    // =====================================================

    private void addCell(
            GridPane grid,
            String text,
            int column,
            int row
    ) {

        Label label =
                new Label(text);

        label.setWrapText(true);

        label.setMaxWidth(
                Double.MAX_VALUE
        );

        label.setMinHeight(55);

        label.setAlignment(
                Pos.CENTER_LEFT
        );

        label.setPadding(
                new Insets(
                        0,
                        7,
                        0,
                        10
                )
        );

        label.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        label.setTextFill(
                Color.web("#1C2529")
        );

        label.setStyle(
                "-fx-border-color: #E5E9EA;" +
                "-fx-border-width: 0 0 1 0;"
        );

        grid.add(
                label,
                column,
                row
        );
    }

    // =====================================================
    // STATUS CELL
    // =====================================================

    private void addStatusCell(
            GridPane grid,
            String status,
            int column,
            int row
    ) {

        Label label =
                new Label(status);

        label.setPadding(
                new Insets(
                        5,
                        8,
                        5,
                        8
                )
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        if ("Active".equals(status)) {

            label.setStyle(
                    "-fx-background-color: #FBE5E5;" +
                    "-fx-text-fill: #D32F2F;" +
                    "-fx-background-radius: 6;"
            );

        } else {

            label.setStyle(
                    "-fx-background-color: #E5F5EB;" +
                    "-fx-text-fill: #21824A;" +
                    "-fx-background-radius: 6;"
            );
        }

        StackPane pane =
                new StackPane(label);

        pane.setMinHeight(55);

        pane.setAlignment(
                Pos.CENTER_LEFT
        );

        pane.setPadding(
                new Insets(
                        0,
                        6,
                        0,
                        6
                )
        );

        pane.setStyle(
                "-fx-border-color: #E5E9EA;" +
                "-fx-border-width: 0 0 1 0;"
        );

        grid.add(
                pane,
                column,
                row
        );
    }

    // =====================================================
    // ACTION CELL
    // =====================================================

    private void addActionCell(
            GridPane grid,
            SOSData alert,
            int column,
            int row
    ) {

        HBox actions =
                new HBox(6);

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

        Button view =
                smallButton(
                        "View",
                        "#E8F1FA",
                        "#2367A5"
                );

        Button resolve =
                smallButton(
                        "Resolve",
                        "#E7F5EC",
                        "#18834B"
                );

        view.setOnAction(
                event ->
                        showAlertDetails(alert)
        );

        resolve.setOnAction(event -> {

            if ("Resolved".equals(
                    alert.status
            )) {
                return;
            }

            alert.status =
                    "Resolved";

            refreshTable();

            System.out.println(
                    alert.id +
                    " resolved"
            );
        });

        actions.getChildren().addAll(
                view,
                resolve
        );

        StackPane pane =
                new StackPane(actions);

        pane.setMinHeight(55);

        pane.setAlignment(
                Pos.CENTER_LEFT
        );

        pane.setPadding(
                new Insets(
                        0,
                        4,
                        0,
                        5
                )
        );

        pane.setStyle(
                "-fx-border-color: #E5E9EA;" +
                "-fx-border-width: 0 0 1 0;"
        );

        grid.add(
                pane,
                column,
                row
        );
    }

    // =====================================================
    // SMALL BUTTON
    // =====================================================

    private Button smallButton(
            String text,
            String background,
            String textColor
    ) {

        Button button =
                new Button(text);

        button.setPrefHeight(30);

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        button.setTextFill(
                Color.web(textColor)
        );

        button.setStyle(
                "-fx-background-color: " +
                background +
                ";" +
                "-fx-border-color: " +
                textColor +
                ";" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(event -> {

            ScaleTransition grow =
                    new ScaleTransition(
                            Duration.millis(100),
                            button
                    );

            grow.setToX(1.05);
            grow.setToY(1.05);

            grow.play();
        });

        button.setOnMouseExited(event -> {

            ScaleTransition shrink =
                    new ScaleTransition(
                            Duration.millis(100),
                            button
                    );

            shrink.setToX(1.0);
            shrink.setToY(1.0);

            shrink.play();
        });

        return button;
    }

    // =====================================================
    // REFRESH TABLE
    // =====================================================

    private void refreshTable() {

        if (sosGrid == null) {
            return;
        }

        sosGrid.getChildren().clear();

        addSOSHeaders();

        addSOSRows();

        updateStatistics();
    }

    // =====================================================
    // UPDATE STATISTICS
    // =====================================================

    private void updateStatistics() {

        int total =
                allAlerts.size();

        int active =
                countActive();

        int resolved =
                countResolved();

        if (totalLabel != null) {

            totalLabel.setText(
                    String.valueOf(total)
            );
        }

        if (activeLabel != null) {

            activeLabel.setText(
                    String.valueOf(active)
            );
        }

        if (resolvedLabel != null) {

            resolvedLabel.setText(
                    String.valueOf(resolved)
            );
        }

        if (responseLabel != null) {

            responseLabel.setText(
                    "4.2 min"
            );
        }
    }

    // =====================================================
    // INFORMATION POPUP
    // =====================================================

    private void showInformation(
            String title,
            String message
    ) {

        Stage stage =
                new Stage();

        stage.setTitle(
                title
        );

        VBox root =
                new VBox(18);

        root.setAlignment(
                Pos.CENTER
        );

        root.setPadding(
                new Insets(25)
        );

        root.setStyle(
                "-fx-background-color: white;"
        );

        Label heading =
                new Label(title);

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );

        heading.setTextFill(
                Color.web(DARK_GREEN)
        );

        Label text =
                new Label(message);

        text.setWrapText(true);

        text.setFont(
                Font.font(14)
        );

        text.setTextFill(
                Color.web("#465057")
        );

        Button close =
                new Button("Close");

        close.setStyle(
                "-fx-background-color: " +
                DARK_GREEN +
                ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 8 22;" +
                "-fx-cursor: hand;"
        );

        close.setOnAction(
                event ->
                        stage.close()
        );

        root.getChildren().addAll(
                heading,
                text,
                close
        );

        Scene scene =
                new Scene(
                        root,
                        400,
                        220
                );

        stage.setScene(scene);

        stage.show();
    }

    // =====================================================
    // ALERT DETAILS
    // =====================================================

    private void showAlertDetails(
            SOSData alert
    ) {

        Stage detailsStage =
                new Stage();

        detailsStage.setTitle(
                "SOS Alert - " +
                alert.id
        );

        VBox root =
                new VBox(15);

        root.setPadding(
                new Insets(25)
        );

        root.setStyle(
                "-fx-background-color: white;"
        );

        Label title =
                new Label(
                        "SOS Alert Details"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        title.setTextFill(
                Color.web("#004B3A")
        );

        Label id =
                detailLabel(
                        "Alert ID: " +
                        alert.id
                );

        Label driver =
                detailLabel(
                        "Driver: " +
                        alert.driver
                );

        Label phone =
                detailLabel(
                        "Phone: " +
                        alert.phone
                );

        Label location =
                detailLabel(
                        "Location: " +
                        alert.location
                );

        Label time =
                detailLabel(
                        "Time: " +
                        alert.time
                );

        Label priority =
                detailLabel(
                        "Priority: " +
                        alert.priority
                );

        Label status =
                detailLabel(
                        "Status: " +
                        alert.status
                );

        Button resolve =
                new Button(
                        "Resolve Alert"
                );

        resolve.setStyle(
                "-fx-background-color: #21824A;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 8 20;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        resolve.setOnAction(event -> {

            alert.status =
                    "Resolved";

            detailsStage.close();

            refreshTable();
        });

        Button close =
                new Button(
                        "Close"
                );

        close.setStyle(
                "-fx-background-color: " +
                DARK_GREEN +
                ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 8 20;" +
                "-fx-cursor: hand;"
        );

        close.setOnAction(
                event ->
                        detailsStage.close()
        );

        HBox buttons =
                new HBox(
                        10,
                        resolve,
                        close
                );

        buttons.setAlignment(
                Pos.CENTER_RIGHT
        );

        root.getChildren().addAll(
                title,
                id,
                driver,
                phone,
                location,
                time,
                priority,
                status,
                buttons
        );

        Scene scene =
                new Scene(
                        root,
                        420,
                        430
                );

        detailsStage.setScene(scene);

        detailsStage.show();
    }

    // =====================================================
    // DETAIL LABEL
    // =====================================================

    private Label detailLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        label.setTextFill(
                Color.web("#263238")
        );

        return label;
    }
}

