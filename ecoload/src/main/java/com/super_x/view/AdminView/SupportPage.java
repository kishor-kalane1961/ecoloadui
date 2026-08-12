
package com.super_x.view.AdminView;

import com.super_x.*;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.stage.Screen;
import javafx.util.Duration;

public class SupportPage {

    private NavigationService navigationService;

    public void setNavigationService(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    private void navigateTo(String pageName) {
        if (navigationService != null) {
            navigationService.navigate(pageName);
        }
    }
    private Scene supportScene;

    // =========================================================
    // COLORS
    // =========================================================

    private final String DARK_GREEN = "#004B3A";
    private final String HOVER_GREEN = "#075E49";
    private final String CLICK_GREEN = "#075E49";

    private final String LOGOUT_RED = "#C62828";
    private final String LOGOUT_RED_HOVER = "#D32F2F";

    private final String BG_COLOR = "#EEF8F4";
    private final String BORDER_COLOR = "#D5DBDE";
    private final String GRAY_COLOR = "#697278";
    private final String TEXT_COLOR = "#171A1C";

    // =========================================================
    // FILTER CONTROLS
    // =========================================================

    private TextField ticketSearchField;

    private ComboBox<String> statusFilter;
    private ComboBox<String> categoryFilter;
    private ComboBox<String> priorityFilter;

    // =========================================================
    // TABLE
    // =========================================================

    private GridPane supportTable;

    // =========================================================
    // TICKET DATA
    // =========================================================

    private final List<TicketData> ticketList = new ArrayList<>();

    // =========================================================
    // TICKET DATA CLASS
    // =========================================================

    private static class TicketData {

        String ticketId;
        String subject;
        String user;
        String email;
        String type;
        String priority;
        String status;
        String date;
        String time;

        TicketData(
                String ticketId,
                String subject,
                String user,
                String email,
                String type,
                String priority,
                String status,
                String date,
                String time
        ) {
            this.ticketId = ticketId;
            this.subject = subject;
            this.user = user;
            this.email = email;
            this.type = type;
            this.priority = priority;
            this.status = status;
            this.date = date;
            this.time = time;
        }
    }

    // =========================================================
    // GET SUPPORT SCENE
    // =========================================================

    public Scene getSupportPageScene() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG_COLOR + ";"
        );

        // =====================================================
        // SIDEBAR
        // =====================================================

        VBox sidebar = createSidebar();

        root.setLeft(sidebar);

        // =====================================================
        // RIGHT SIDE
        // =====================================================

        VBox rightSide = new VBox();

        HBox topBar = createTopBar();

        VBox content = createSupportContent();

        VBox.setVgrow(
                content,
                Priority.ALWAYS
        );

        rightSide.getChildren().addAll(
                topBar,
                content
        );

        root.setCenter(rightSide);

        // =====================================================
        // SCREEN SIZE
        // =====================================================

        Rectangle2D screenBounds =
                Screen.getPrimary().getVisualBounds();

        double screenWidth =
                screenBounds.getWidth();

        double screenHeight =
                screenBounds.getHeight();

        // =====================================================
        // SCENE
        // =====================================================

        supportScene =
                new Scene(
                        root,
                        screenWidth,
                        screenHeight
                );

        return supportScene;
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private VBox createSidebar() {

        VBox sidebar = new VBox();

        sidebar.setMinWidth(250);
        sidebar.setPrefWidth(250);
        sidebar.setMaxWidth(250);

        sidebar.setPadding(
                new Insets(
                        25,
                        16,
                        20,
                        16
                )
        );

        sidebar.setStyle(
                "-fx-background-color: " + DARK_GREEN + ";"
        );

        // =====================================================
        // LOGO
        // =====================================================

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

        ecoLoad.setTextFill(Color.WHITE);

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

        // =====================================================
        // MENU
        // =====================================================

        VBox menu = new VBox(5);

        menu.setPadding(
                new Insets(
                        45,
                        0,
                        0,
                        0
                )
        );

        menu.setFillWidth(true);

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
                        false
                );
        sosButton.setOnAction(event -> navigateTo("alerts"));

        Button supportButton =
                createMenuButton(
                        "♧",
                        "Support",
                        true
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

        // =====================================================
        // SPACER
        // =====================================================

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        // =====================================================
        // LOGOUT
        // =====================================================

        Button logout = createLogoutButton();

        // =====================================================
        // SIDEBAR CHILDREN
        // =====================================================

        sidebar.getChildren().addAll(
                logoBox,
                menu,
                spacer,
                logout
        );

        return sidebar;
    }

    // =========================================================
    // LOGOUT BUTTON
    // =========================================================

    private Button createLogoutButton() {

        Button button = new Button();

        button.setPrefHeight(48);
        button.setMinHeight(44);
        button.setMaxWidth(Double.MAX_VALUE);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setCursor(Cursor.HAND);

        Label iconLabel =
                new Label("↪");

        iconLabel.setPrefWidth(28);

        iconLabel.setFont(
                Font.font(20)
        );

        iconLabel.setTextFill(
                Color.WHITE
        );

        Label textLabel =
                new Label("Logout");

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

        setLogoutStyle(
                button,
                LOGOUT_RED
        );

        button.setOnMouseEntered(event -> {

            setLogoutStyle(
                    button,
                    LOGOUT_RED_HOVER
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

            setLogoutStyle(
                    button,
                    LOGOUT_RED
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

            setLogoutStyle(
                    button,
                    LOGOUT_RED
            );
        });

        return button;
    }

    // =========================================================
    // LOGOUT STYLE
    // =========================================================

    private void setLogoutStyle(
            Button button,
            String color
    ) {

        button.setStyle(
                "-fx-background-color: " + color + ";"
                        + "-fx-background-radius: 9;"
                        + "-fx-text-fill: white;"
                        + "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // SIDEBAR LOGO
    // =========================================================

    private ImageView createSidebarLogo() {

        ImageView logoView =
                new ImageView();

        String imagePath =
                "/assets/Logo-removebg-preview.png";

        java.io.InputStream logoStream =
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
                    "ERROR: Logo not found: "
                            + imagePath
            );
        }

        return logoView;
    }

    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private Button createMenuButton(
            String icon,
            String text,
            boolean active
    ) {

        Button button =
                new Button();

        button.setPrefHeight(48);
        button.setMinHeight(44);
        button.setMaxWidth(Double.MAX_VALUE);

        button.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setCursor(
                Cursor.HAND
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

        if (active) {

            button.setStyle(
                    "-fx-background-color: "
                            + CLICK_GREEN
                            + ";"
                            + "-fx-background-radius: 9;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: "
                            + DARK_GREEN
                            + ";"
                            + "-fx-background-radius: 9;"
            );
        }

        button.setOnMouseEntered(event -> {

            button.setStyle(
                    "-fx-background-color: "
                            + HOVER_GREEN
                            + ";"
                            + "-fx-background-radius: 9;"
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

            if (active) {

                button.setStyle(
                        "-fx-background-color: "
                                + CLICK_GREEN
                                + ";"
                                + "-fx-background-radius: 9;"
                );

            } else {

                button.setStyle(
                        "-fx-background-color: "
                                + DARK_GREEN
                                + ";"
                                + "-fx-background-radius: 9;"
                );
            }

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

            button.setStyle(
                    "-fx-background-color: "
                            + CLICK_GREEN
                            + ";"
                            + "-fx-background-radius: 9;"
            );
        });

        return button;
    }

    // =========================================================
    // TOP BAR
    // =========================================================

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
                "-fx-background-color: white;"
                        + "-fx-border-color: #D7DCDE;"
                        + "-fx-border-width: 0 0 1 0;"
        );

        // =====================================================
        // SEARCH BOX
        // =====================================================

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
                "-fx-background-color: #F8FAFA;"
                        + "-fx-border-color: #C8CFD2;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
        );

        Label searchIcon =
                new Label("⌕");

        searchIcon.setFont(
                Font.font(28)
        );

        TextField search =
                new TextField();

        search.setPromptText(
                "Search trips, loads, drivers..."
        );

        search.setPrefHeight(40);

        search.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-border-color: transparent;"
                        + "-fx-font-size: 15px;"
        );

        HBox.setHgrow(
                search,
                Priority.ALWAYS
        );

        searchBox.getChildren().addAll(
                searchIcon,
                search
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Region divider =
                new Region();

        divider.setPrefWidth(1);
        divider.setPrefHeight(35);

        divider.setStyle(
                "-fx-background-color: #C8CED0;"
        );

        VBox profileText =
                new VBox(1);

        profileText.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label adminProfile =
                new Label("Admin Profile");

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

    // =========================================================
    // PROFILE IMAGE
    // =========================================================

    private Button createAdminProfileImage() {

        Button profileButton =
                new Button();

        profileButton.setPrefSize(45, 45);
        profileButton.setMinSize(45, 45);
        profileButton.setMaxSize(45, 45);

        profileButton.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-border-color: transparent;"
                        + "-fx-padding: 0;"
                        + "-fx-background-radius: 50;"
                        + "-fx-border-radius: 50;"
        );

        String imagePath =
                "/assets/Admin_Profile_Logo.png";

        java.io.InputStream profileStream =
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

            System.out.println(
                    "ERROR: Admin profile image not found: "
                            + imagePath
            );

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
                    "-fx-background-color: #E4F0EB;"
                            + "-fx-border-color: #075E49;"
                            + "-fx-border-width: 2;"
                            + "-fx-border-radius: 50;"
                            + "-fx-background-radius: 50;"
                            + "-fx-padding: 0;"
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

            profileButton.setStyle(
                    "-fx-background-color: transparent;"
                            + "-fx-border-color: transparent;"
                            + "-fx-padding: 0;"
                            + "-fx-background-radius: 50;"
                            + "-fx-border-radius: 50;"
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

        return profileButton;
    }

    // =========================================================
    // SUPPORT CONTENT
    // MAIN PAGE IS NOT VERTICALLY SCROLLABLE
    // =========================================================

    private VBox createSupportContent() {

        VBox wrapper =
                new VBox();

        wrapper.setFillWidth(true);

        // =====================================================
        // MAIN CONTENT
        // NO MAIN SCROLLPANE HERE
        // =====================================================

        VBox content =
                new VBox(20);

        content.setFillWidth(true);

        content.setPadding(
                new Insets(
                        22,
                        28,
                        25,
                        28
                )
        );

        content.setStyle(
                "-fx-background-color: #F7F9FA;"
        );

        // =====================================================
        // HEADING
        // =====================================================

        VBox headingText =
                new VBox(5);

        Label title =
                new Label("Support Center");

        title.setTextFill(
                Color.web(TEXT_COLOR)
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        31
                )
        );

        Label subtitle =
                new Label(
                        "Manage all support tickets and user queries"
                );

        subtitle.setTextFill(
                Color.web("#667085")
        );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        headingText.getChildren().addAll(
                title,
                subtitle
        );

        HBox heading =
                new HBox(headingText);

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // STAT CARDS
        // =====================================================

        HBox cards =
                new HBox(15);

        cards.setFillHeight(true);

        cards.getChildren().addAll(

                statCard(
                        "▤",
                        "Total Tickets",
                        "124",
                        "↑ 12.5% from last month",
                        "#EAF3FF",
                        "#1671D9",
                        "#278B54"
                ),

                statCard(
                        "⌛",
                        "In Progress",
                        "36",
                        "↑ 8.2% from last month",
                        "#FFF2E2",
                        "#E79A22",
                        "#278B54"
                ),

                statCard(
                        "✓",
                        "Resolved",
                        "72",
                        "↑ 15.4% from last month",
                        "#EAF8EF",
                        "#22934F",
                        "#278B54"
                ),

                statCard(
                        "×",
                        "Open",
                        "14",
                        "↓ 5.6% from last month",
                        "#FDECEE",
                        "#D94C54",
                        "#D94C54"
                ),

                statCard(
                        "◷",
                        "Avg. Response Time",
                        "2h 15m",
                        "↓ 10% from last month",
                        "#F1ECFF",
                        "#7446C5",
                        "#278B54"
                )
        );

        for (Node node : cards.getChildren()) {

            HBox.setHgrow(
                    node,
                    Priority.ALWAYS
            );
        }

        // =====================================================
        // TICKET PANEL
        // =====================================================

        VBox ticketPanel =
                new VBox(15);

        ticketPanel.setPadding(
                new Insets(18)
        );

        ticketPanel.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: "
                        + BORDER_COLOR
                        + ";"
                        + "-fx-border-radius: 10;"
                        + "-fx-background-radius: 10;"
        );

        // =====================================================
        // FILTERS
        // =====================================================

        HBox filters =
                new HBox(15);

        filters.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // SEARCH
        // =====================================================

        ticketSearchField =
                new TextField();

        ticketSearchField.setPromptText(
                "⌕   Search tickets by ID, name or email..."
        );

        ticketSearchField.setPrefHeight(40);
        ticketSearchField.setPrefWidth(365);
        ticketSearchField.setMinWidth(365);

        ticketSearchField.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: #D9DEE5;"
                        + "-fx-border-radius: 6;"
                        + "-fx-background-radius: 6;"
                        + "-fx-font-size: 13px;"
                        + "-fx-padding: 0 12;"
        );

        // =====================================================
        // STATUS FILTER
        // =====================================================

        statusFilter =
                comboBox(
                        "All Status",
                        "Open",
                        "In Progress",
                        "Resolved",
                        "Closed"
                );

        // =====================================================
        // CATEGORY FILTER
        // =====================================================

        categoryFilter =
                comboBox(
                        "All Categories",
                        "Tracking",
                        "Payment",
                        "Trip Update",
                        "Technical",
                        "Data Issue",
                        "Assignment"
                );

        // =====================================================
        // PRIORITY FILTER
        // =====================================================

        priorityFilter =
                comboBox(
                        "All Priority",
                        "High",
                        "Medium",
                        "Low"
                );

        // =====================================================
        // EXPORT
        // =====================================================

        Button export =
                new Button("⇩  Export");

        export.setPrefHeight(40);
        export.setMinWidth(100);

        export.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: #B8D9C8;"
                        + "-fx-border-radius: 6;"
                        + "-fx-text-fill: #26774C;"
                        + "-fx-font-weight: bold;"
                        + "-fx-cursor: hand;"
        );

        export.setOnMouseEntered(event -> {

            export.setStyle(
                    "-fx-background-color: #EAF7EF;"
                            + "-fx-border-color: #26774C;"
                            + "-fx-border-radius: 6;"
                            + "-fx-text-fill: #26774C;"
                            + "-fx-font-weight: bold;"
                            + "-fx-cursor: hand;"
            );
        });

        export.setOnMouseExited(event -> {

            export.setStyle(
                    "-fx-background-color: white;"
                            + "-fx-border-color: #B8D9C8;"
                            + "-fx-border-radius: 6;"
                            + "-fx-text-fill: #26774C;"
                            + "-fx-font-weight: bold;"
                            + "-fx-cursor: hand;"
            );
        });

        filters.getChildren().addAll(
                ticketSearchField,
                statusFilter,
                categoryFilter,
                priorityFilter,
                export
        );

        // =====================================================
        // FILTER SCROLL
        // ONLY HORIZONTAL SCROLL IF NEEDED
        // =====================================================

        ScrollPane filterScroll =
                new ScrollPane();

        filterScroll.setContent(filters);

        filterScroll.setFitToHeight(true);
        filterScroll.setFitToWidth(false);

        filterScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        filterScroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        filterScroll.setPannable(true);

        filterScroll.setPrefHeight(60);
        filterScroll.setMinHeight(55);
        filterScroll.setMaxHeight(65);

        filterScroll.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-border-color: transparent;"
                        + "-fx-padding: 0;"
        );

        // =====================================================
        // TABLE
        // =====================================================

        supportTable =
                new GridPane();

        supportTable.setHgap(0);
        supportTable.setVgap(0);

        // =====================================================
        // TABLE SCROLL
        // TABLE CAN SCROLL VERTICALLY
        // =====================================================

        ScrollPane tableScroll =
                new ScrollPane();

        tableScroll.setContent(
                supportTable
        );

        tableScroll.setFitToHeight(false);
        tableScroll.setFitToWidth(false);

        tableScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        tableScroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        tableScroll.setPannable(true);

        tableScroll.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: transparent;"
        );

        VBox.setVgrow(
                tableScroll,
                Priority.ALWAYS
        );

        // =====================================================
        // LOAD DATA
        // =====================================================

        loadTicketData();

        // =====================================================
        // INITIAL TABLE
        // =====================================================

        refreshTicketTable();

        // =====================================================
        // FILTER EVENTS
        // =====================================================

        statusFilter.setOnAction(
                event -> refreshTicketTable()
        );

        categoryFilter.setOnAction(
                event -> refreshTicketTable()
        );

        priorityFilter.setOnAction(
                event -> refreshTicketTable()
        );

        ticketSearchField
                .textProperty()
                .addListener(
                        (
                                observable,
                                oldValue,
                                newValue
                        ) -> refreshTicketTable()
                );

        // =====================================================
        // ADD TO PANEL
        // =====================================================

        ticketPanel.getChildren().addAll(
                filterScroll,
                tableScroll
        );

        // =====================================================
        // CONTENT
        // =====================================================

        content.getChildren().addAll(
                heading,
                cards,
                ticketPanel
        );

        VBox.setVgrow(
                ticketPanel,
                Priority.ALWAYS
        );

        // =====================================================
        // IMPORTANT
        // NO OUTER SCROLLPANE
        // =====================================================

        VBox.setVgrow(
                content,
                Priority.ALWAYS
        );

        wrapper.getChildren().add(
                content
        );

        VBox.setVgrow(
                content,
                Priority.ALWAYS
        );

        return wrapper;
    }

    // =========================================================
    // LOAD TICKET DATA
    // =========================================================

    private void loadTicketData() {

        ticketList.clear();

        ticketList.add(
                new TicketData(
                        "#SUP-1024",
                        "Unable to Track Load",
                        "Ramesh Patil",
                        "ramesh@email.com",
                        "Tracking",
                        "High",
                        "Open",
                        "27 May 2025",
                        "10:30 AM"
                )
        );

        ticketList.add(
                new TicketData(
                        "#SUP-1023",
                        "Payment Not Received",
                        "Jindal Logistics",
                        "contact@jindal.com",
                        "Payment",
                        "Medium",
                        "In Progress",
                        "27 May 2025",
                        "09:15 AM"
                )
        );

        ticketList.add(
                new TicketData(
                        "#SUP-1022",
                        "Trip Completed but Not Updated",
                        "Suresh Yadav",
                        "suresh@email.com",
                        "Trip Update",
                        "Medium",
                        "In Progress",
                        "26 May 2025",
                        "04:45 PM"
                )
        );

        ticketList.add(
                new TicketData(
                        "#SUP-1021",
                        "App Login Issue",
                        "Vikram Singh",
                        "vikram@email.com",
                        "Technical",
                        "High",
                        "Open",
                        "26 May 2025",
                        "02:20 PM"
                )
        );

        ticketList.add(
                new TicketData(
                        "#SUP-1020",
                        "Incorrect Load Details",
                        "Shree Transport",
                        "info@shreetransport.com",
                        "Data Issue",
                        "Low",
                        "Resolved",
                        "25 May 2025",
                        "11:05 AM"
                )
        );

        ticketList.add(
                new TicketData(
                        "#SUP-1019",
                        "Pickup Person Not Assigned",
                        "Mahesh Kumar",
                        "mahesh@email.com",
                        "Assignment",
                        "Medium",
                        "Resolved",
                        "25 May 2025",
                        "09:30 AM"
                )
        );

        ticketList.add(
                new TicketData(
                        "#SUP-1018",
                        "Vehicle Details Update",
                        "Om Logistics",
                        "om@omlogistics.com",
                        "Data Issue",
                        "Low",
                        "Closed",
                        "24 May 2025",
                        "06:40 PM"
                )
        );
    }

    // =========================================================
    // REFRESH TABLE
    // =========================================================

    private void refreshTicketTable() {

        if (supportTable == null) {
            return;
        }

        supportTable.getChildren().clear();

        supportTable.getColumnConstraints().clear();

        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        supportTable.getColumnConstraints().addAll(

                column(0.10),
                column(0.15),
                column(0.17),
                column(0.10),
                column(0.10),
                column(0.11),
                column(0.07),
                column(0.07)
        );

        // =====================================================
        // HEADERS
        // =====================================================

        String[] headers = {

                "TICKET ID",
                "SUBJECT",
                "USER",
                "TYPE",
                "PRIORITY",
                "STATUS",
                "CREATED ON",
                "ACTIONS"
        };

        for (
                int i = 0;
                i < headers.length;
                i++
        ) {

            Label header =
                    new Label(
                            headers[i]
                    );

            header.setTextFill(
                    Color.web("#475467")
            );

            header.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            11
                    )
            );

            header.setPadding(
                    new Insets(
                            12,
                            7,
                            12,
                            7
                    )
            );

            HBox headerBox =
                    new HBox(header);

            headerBox.setAlignment(
                    Pos.CENTER_LEFT
            );

            headerBox.setStyle(
                    "-fx-background-color: #F8FAFC;"
            );

            supportTable.add(
                    headerBox,
                    i,
                    0
            );
        }

        // =====================================================
        // FILTER VALUES
        // =====================================================

        String searchText =
                ticketSearchField
                        .getText()
                        .trim()
                        .toLowerCase();

        String selectedStatus =
                statusFilter.getValue();

        String selectedCategory =
                categoryFilter.getValue();

        String selectedPriority =
                priorityFilter.getValue();

        if (selectedStatus == null) {
            selectedStatus = "All Status";
        }

        if (selectedCategory == null) {
            selectedCategory = "All Categories";
        }

        if (selectedPriority == null) {
            selectedPriority = "All Priority";
        }

        // =====================================================
        // ROW
        // =====================================================

        int row = 1;

        // =====================================================
        // FILTER
        // =====================================================

        for (TicketData ticket : ticketList) {

            boolean matchesSearch =

                    searchText.isEmpty()

                            ||

                    ticket.ticketId
                            .toLowerCase()
                            .contains(searchText)

                            ||

                    ticket.subject
                            .toLowerCase()
                            .contains(searchText)

                            ||

                    ticket.user
                            .toLowerCase()
                            .contains(searchText)

                            ||

                    ticket.email
                            .toLowerCase()
                            .contains(searchText)

                            ||

                    ticket.type
                            .toLowerCase()
                            .contains(searchText);

            boolean matchesStatus =

                    selectedStatus.equals("All Status")

                            ||

                    ticket.status.equals(
                            selectedStatus
                    );

            boolean matchesCategory =

                    selectedCategory.equals(
                            "All Categories"
                    )

                            ||

                    ticket.type.equals(
                            selectedCategory
                    );

            boolean matchesPriority =

                    selectedPriority.equals(
                            "All Priority"
                    )

                            ||

                    ticket.priority.equals(
                            selectedPriority
                    );

            if (
                    matchesSearch
                            &&
                    matchesStatus
                            &&
                    matchesCategory
                            &&
                    matchesPriority
            ) {

                addRow(
                        supportTable,
                        row,
                        ticket.ticketId,
                        ticket.subject,
                        ticket.user,
                        ticket.email,
                        ticket.type,
                        ticket.priority,
                        ticket.status,
                        ticket.date,
                        ticket.time
                );

                row++;
            }
        }
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox statCard(
            String icon,
            String title,
            String value,
            String change,
            String iconBg,
            String iconColor,
            String changeColor
    ) {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(16)
        );

        card.setPrefHeight(145);
        card.setMinHeight(135);

        card.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: #E5E7EB;"
                        + "-fx-border-radius: 10;"
                        + "-fx-background-radius: 10;"
        );

        card.setOnMouseEntered(event -> {

            card.setStyle(
                    "-fx-background-color: white;"
                            + "-fx-border-color: #075E49;"
                            + "-fx-border-width: 2;"
                            + "-fx-border-radius: 10;"
                            + "-fx-background-radius: 10;"
            );

            ScaleTransition grow =
                    new ScaleTransition(
                            Duration.millis(150),
                            card
                    );

            grow.setToX(0.98);
            grow.setToY(0.98);

            grow.play();
        });

        card.setOnMouseExited(event -> {

            card.setStyle(
                    "-fx-background-color: white;"
                            + "-fx-border-color: #E5E7EB;"
                            + "-fx-border-width: 1;"
                            + "-fx-border-radius: 10;"
                            + "-fx-background-radius: 10;"
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

        HBox top =
                new HBox(12);

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        StackPane iconPane =
                new StackPane();

        iconPane.setPrefSize(55, 55);
        iconPane.setMinSize(55, 55);
        iconPane.setMaxSize(55, 55);

        iconPane.setStyle(
                "-fx-background-color: "
                        + iconBg
                        + ";"
                        + "-fx-background-radius: 12;"
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setTextFill(
                Color.web(iconColor)
        );

        iconLabel.setFont(
                Font.font(
                        "Arial",
                        25
                )
        );

        iconPane.getChildren().add(
                iconLabel
        );

        VBox textBox =
                new VBox(3);

        Label titleLabel =
                new Label(title);

        titleLabel.setTextFill(
                Color.web("#475467")
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setTextFill(
                Color.web(iconColor)
        );

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25
                )
        );

        textBox.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        top.getChildren().addAll(
                iconPane,
                textBox
        );

        Label changeLabel =
                new Label(change);

        changeLabel.setTextFill(
                Color.web(changeColor)
        );

        changeLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        card.getChildren().addAll(
                top,
                changeLabel
        );

        return card;
    }

    // =========================================================
    // COMBO BOX
    // =========================================================

    private ComboBox<String> comboBox(
            String first,
            String... values
    ) {

        ComboBox<String> combo =
                new ComboBox<>();

        combo.getItems().add(first);

        for (String value : values) {
            combo.getItems().add(value);
        }

        combo.setValue(first);

        combo.setPrefHeight(40);
        combo.setPrefWidth(185);
        combo.setMinWidth(185);

        combo.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: #D9DEE5;"
                        + "-fx-border-radius: 6;"
                        + "-fx-background-radius: 6;"
                        + "-fx-font-size: 13px;"
        );

        return combo;
    }

    // =========================================================
    // COLUMN
    // =========================================================

    private ColumnConstraints column(
            double percentage
    ) {

        ColumnConstraints column =
                new ColumnConstraints();

        column.setPercentWidth(
                percentage * 100
        );

        return column;
    }

    // =========================================================
    // TABLE ROW
    // =========================================================

    private void addRow(
            GridPane table,
            int row,
            String ticketId,
            String subject,
            String user,
            String email,
            String type,
            String priority,
            String status,
            String date,
            String time
    ) {

        addCell(
                table,
                ticketId,
                0,
                row,
                false
        );

        addCell(
                table,
                subject,
                1,
                row,
                false
        );

        // =====================================================
        // USER
        // =====================================================

        VBox userBox =
                new VBox(3);

        Label userName =
                new Label(user);

        userName.setTextFill(
                Color.web("#172033")
        );

        userName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        Label userEmail =
                new Label(email);

        userEmail.setTextFill(
                Color.web("#667085")
        );

        userEmail.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        userBox.getChildren().addAll(
                userName,
                userEmail
        );

        addCustomCell(
                table,
                userBox,
                2,
                row
        );

        // =====================================================
        // BADGES
        // =====================================================

        addBadgeCell(
                table,
                type,
                3,
                row,
                "type"
        );

        addBadgeCell(
                table,
                priority,
                4,
                row,
                "priority"
        );

        addBadgeCell(
                table,
                status,
                5,
                row,
                "status"
        );

        // =====================================================
        // DATE
        // =====================================================

        VBox dateBox =
                new VBox(3);

        Label dateLabel =
                new Label(date);

        dateLabel.setTextFill(
                Color.web("#172033")
        );

        dateLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        Label timeLabel =
                new Label(time);

        timeLabel.setTextFill(
                Color.web("#667085")
        );

        timeLabel.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        dateBox.getChildren().addAll(
                dateLabel,
                timeLabel
        );

        addCustomCell(
                table,
                dateBox,
                6,
                row
        );

        // =====================================================
        // VIEW BUTTON
        // =====================================================

        Button viewButton =
                new Button("◉");

        viewButton.setPrefSize(
                45,
                34
        );

        viewButton.setCursor(
                Cursor.HAND
        );

        viewButton.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: #D9E1E8;"
                        + "-fx-border-radius: 6;"
                        + "-fx-text-fill: #1970B8;"
                        + "-fx-font-size: 16px;"
                        + "-fx-cursor: hand;"
        );

        viewButton.setOnMouseEntered(event -> {

            viewButton.setStyle(
                    "-fx-background-color: #EAF7EF;"
                            + "-fx-border-color: #075E49;"
                            + "-fx-border-radius: 6;"
                            + "-fx-text-fill: #075E49;"
                            + "-fx-font-size: 16px;"
                            + "-fx-cursor: hand;"
            );
        });

        viewButton.setOnMouseExited(event -> {

            viewButton.setStyle(
                    "-fx-background-color: white;"
                            + "-fx-border-color: #D9E1E8;"
                            + "-fx-border-radius: 6;"
                            + "-fx-text-fill: #1970B8;"
                            + "-fx-font-size: 16px;"
                            + "-fx-cursor: hand;"
            );
        });

        HBox actionBox =
                new HBox(viewButton);

        actionBox.setAlignment(
                Pos.CENTER_LEFT
        );

        actionBox.setPadding(
                new Insets(7)
        );

        table.add(
                actionBox,
                7,
                row
        );

        // =====================================================
        // ROW SEPARATOR
        // =====================================================

        for (
                int i = 0;
                i < 8;
                i++
        ) {

            Region line =
                    new Region();

            line.setPrefHeight(1);

            line.setStyle(
                    "-fx-background-color: #EEF0F3;"
            );

            table.add(
                    line,
                    i,
                    row + 1
            );
        }
    }

    // =========================================================
    // NORMAL CELL
    // =========================================================

    private void addCell(
            GridPane table,
            String text,
            int column,
            int row,
            boolean center
    ) {

        Label label =
                new Label(text);

        label.setWrapText(true);

        label.setTextFill(
                Color.web("#172033")
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        HBox box =
                new HBox(label);

        if (center) {

            box.setAlignment(
                    Pos.CENTER
            );

        } else {

            box.setAlignment(
                    Pos.CENTER_LEFT
            );
        }

        box.setPadding(
                new Insets(7)
        );

        table.add(
                box,
                column,
                row
        );
    }

    // =========================================================
    // CUSTOM CELL
    // =========================================================

    private void addCustomCell(
            GridPane table,
            Node node,
            int column,
            int row
    ) {

        HBox box =
                new HBox(node);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        box.setPadding(
                new Insets(7)
        );

        table.add(
                box,
                column,
                row
        );
    }

    // =========================================================
    // BADGE CELL
    // =========================================================

    private void addBadgeCell(
            GridPane table,
            String text,
            int column,
            int row,
            String badgeType
    ) {

        Label badge =
                new Label(text);

        String bg = "#F2F4F7";
        String color = "#475467";

        // =====================================================
        // PRIORITY
        // =====================================================

        if (badgeType.equals("priority")) {

            if (text.equals("High")) {

                bg = "#FDEBED";
                color = "#C73D47";

            } else if (text.equals("Medium")) {

                bg = "#FFF1DB";
                color = "#D98A19";

            } else {

                bg = "#EAF7EF";
                color = "#32804D";
            }

        // =====================================================
        // STATUS
        // =====================================================

        } else if (badgeType.equals("status")) {

            if (text.equals("Open")) {

                bg = "#FDEBED";
                color = "#C73D47";

            } else if (text.equals("In Progress")) {

                bg = "#EAF3FC";
                color = "#2D6CA5";

            } else if (text.equals("Resolved")) {

                bg = "#EAF7EF";
                color = "#32804D";

            } else if (text.equals("Closed")) {

                bg = "#F1F3F5";
                color = "#5C6672";
            }

        // =====================================================
        // TYPE
        // =====================================================

        } else if (badgeType.equals("type")) {

            if (text.equals("Tracking")) {

                bg = "#EAF3FC";
                color = "#286CA4";

            } else if (text.equals("Payment")) {

                bg = "#EAF7EF";
                color = "#32804D";

            } else if (text.equals("Trip Update")) {

                bg = "#F3EEFF";
                color = "#7353A9";

            } else if (text.equals("Technical")) {

                bg = "#F1F3F5";
                color = "#4B5563";

            } else if (text.equals("Data Issue")) {

                bg = "#FFF7DF";
                color = "#B98618";

            } else if (text.equals("Assignment")) {

                bg = "#EAF3FC";
                color = "#286CA4";
            }
        }

        // =====================================================
        // BADGE STYLE
        // =====================================================

        badge.setStyle(
                "-fx-background-color: "
                        + bg
                        + ";"
                        + "-fx-text-fill: "
                        + color
                        + ";"
                        + "-fx-background-radius: 5;"
                        + "-fx-padding: 6 10;"
                        + "-fx-font-size: 11px;"
                        + "-fx-font-weight: bold;"
        );

        HBox box =
                new HBox(badge);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        box.setPadding(
                new Insets(7)
        );

        table.add(
                box,
                column,
                row
        );
    }
}
