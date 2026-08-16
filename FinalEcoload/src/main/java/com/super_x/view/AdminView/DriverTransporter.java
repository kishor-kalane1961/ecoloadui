
package com.super_x.view.AdminView;

import com.super_x.*;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DriverTransporter {

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

    /*private final String DARK_GREEN = "#155B4B";
    private final String HOVER_GREEN = "#237563";
    private final String CLICK_GREEN = "#26806b";
    private final String NUMBER_GREEN = "#278A4A";
    private final String BG_COLOR = "#EEF8F4";
    private final String BORDER_COLOR = "#D8E5E0";
    private final String HOVER_BORDER = "#4AA98C";
    private final String GRAY_COLOR = "#697278";

    private static final String GRAY = "#657078";
    private static final String BORDER = "#D8E5E0";*/
     private final String DARK_GREEN = "#004B3A";

    // Light green when cursor is on button
    private final String HOVER_GREEN = "#075E49";

    // Dark green when button is clicked
    private final String CLICK_GREEN = "#075E49";

    private final String NUMBER_GREEN = "#2D9950";

    private final String BG_COLOR = "#EEF8F4";

    private final String BORDER_COLOR = "#D5DBDE";

    private final String HOVER_BORDER = "#075E49";

    private final String GRAY_COLOR = "#697278";
     private final String TEXT_COLOR = "#171A1C";

    // =====================================================
    // CONTROLS
    // =====================================================

    private ComboBox<String> typeCombo;
    private ComboBox<String> statusCombo;

    private GridPane requestGrid;

    private Label requestCountLabel;

    // =====================================================
    // DATA
    // =====================================================

    private final List<RequestData> allRequests =
            new ArrayList<>();

    // =====================================================
    // REQUEST DATA
    // =====================================================

    private static class RequestData {

        String id;
        String type;
        String name;
        String phone;
        String applied;
        String status;
        String documents;

        RequestData(
                String id,
                String type,
                String name,
                String phone,
                String applied,
                String status,
                String documents
        ) {
            this.id = id;
            this.type = type;
            this.name = name;
            this.phone = phone;
            this.applied = applied;
            this.status = status;
            this.documents = documents;
        }
    }

    // =====================================================
    // MAIN SCENE METHOD
    // =====================================================

    public Scene getDriverTransporterScene() {

        loadRequestData();

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

        // =================================================
        // SCENE
        // =================================================

        Scene scene = new Scene(root);

        return scene;
    }

    // =====================================================
    // START METHOD
    // =====================================================

    public void start(Stage stage) {

        Scene scene = getDriverTransporterScene();

        stage.setScene(scene);

        stage.setTitle(
                "EcoLoad - Drivers & Transporters"
        );

        // =================================================
        // RESPONSIVE SCREEN SIZE
        // =================================================

        Rectangle2D screenBounds =
                getScreenBounds();

        double screenWidth =
                screenBounds.getWidth();

        double screenHeight =
                screenBounds.getHeight();

        // Leave small space for OS taskbar
        double windowWidth =
                Math.min(
                        1600,
                        screenWidth - 40
                );

        double windowHeight =
                Math.min(
                        950,
                        screenHeight - 80
                );

        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);

        stage.setMinWidth(1000);
        stage.setMinHeight(650);

        stage.setResizable(true);

        // Center window
        stage.centerOnScreen();

        stage.show();

        // =================================================
        // MAXIMIZE IF LARGE SCREEN
        // =================================================

        if (screenWidth >= 1300 &&
                screenHeight >= 750) {

            stage.setMaximized(true);
        }
    }

    // =====================================================
    // SCREEN BOUNDS
    // =====================================================

    private Rectangle2D getScreenBounds() {

        return Screen
                .getPrimary()
                .getVisualBounds();
    }

    // =====================================================
    // LOAD DATA
    // =====================================================

    private void loadRequestData() {

        allRequests.clear();

        allRequests.add(
                new RequestData(
                        "#TR-1001",
                        "Transporter",
                        "Jindal Logistics",
                        "+91 98765 43210",
                        "27 May 2025\n10:30 AM",
                        "Pending",
                        "3/3 Uploaded"
                )
        );

        allRequests.add(
                new RequestData(
                        "#DR-1002",
                        "Driver",
                        "Ramesh Patil",
                        "+91 87654 32109",
                        "27 May 2025\n09:15 AM",
                        "New",
                        "2/3 Uploaded"
                )
        );

        allRequests.add(
                new RequestData(
                        "#TR-1003",
                        "Transporter",
                        "Shree Transport",
                        "+91 65432 10987",
                        "26 May 2025\n04:45 PM",
                        "Pending",
                        "3/3 Uploaded"
                )
        );

        allRequests.add(
                new RequestData(
                        "#DR-1004",
                        "Driver",
                        "Suresh Yadav",
                        "+91 76543 21098",
                        "26 May 2025\n02:20 PM",
                        "Incomplete",
                        "1/3 Uploaded"
                )
        );

        allRequests.add(
                new RequestData(
                        "#TR-1005",
                        "Transporter",
                        "Vikram Singh",
                        "+91 54321 09876",
                        "25 May 2025\n11:05 AM",
                        "Verified",
                        "3/3 Uploaded"
                )
        );

        allRequests.add(
                new RequestData(
                        "#DR-1006",
                        "Driver",
                        "Mahesh Kumar",
                        "+91 93456 78901",
                        "24 May 2025\n05:10 PM",
                        "Pending",
                        "2/3 Uploaded"
                )
        );

        allRequests.add(
                new RequestData(
                        "#TR-1007",
                        "Transporter",
                        "Om Logistics",
                        "+91 99887 66554",
                        "24 May 2025\n03:40 PM",
                        "Verified",
                        "3/3 Uploaded"
                )
        );

        allRequests.add(
                new RequestData(
                        "#DR-1008",
                        "Driver",
                        "Ajay Chauhan",
                        "+91 88776 65544",
                        "23 May 2025\n12:30 PM",
                        "New",
                        "2/3 Uploaded"
                )
        );
    }

    // =====================================================
    // SIDEBAR
    // =====================================================

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
                        true
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

        Region spacer = new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        Button logout =
                createMenuButton(
                        "↪",
                        "Logout",
                        false
                );

        logout.setStyle(
                "-fx-background-color: #B42335;" +
                "-fx-background-radius: 9;"
        );

        logout.setOnMouseEntered(event -> {

            logout.setStyle(
                    "-fx-background-color: #921E2D;" +
                    "-fx-background-radius: 9;"
            );

            ScaleTransition grow =
                    new ScaleTransition(
                            Duration.millis(120),
                            logout
                    );

            grow.setToX(1.02);
            grow.setToY(1.02);

            grow.play();
        });

        logout.setOnMouseExited(event -> {

            logout.setStyle(
                    "-fx-background-color: #B42335;" +
                    "-fx-background-radius: 9;"
            );

            ScaleTransition shrink =
                    new ScaleTransition(
                            Duration.millis(120),
                            logout
                    );

            shrink.setToX(1.0);
            shrink.setToY(1.0);

            shrink.play();
        });

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
                    HOVER_GREEN +
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
                    "-fx-background-color: transparent;" +
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
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #D8E5E0;" +
                "-fx-border-width: 0 0 1 0;"
        );

        // =================================================
        // SEARCH
        // =================================================

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
                "-fx-background-color: #F5F9F7;" +
                "-fx-border-color: #C9D8D2;" +
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

        // =================================================
        // PROFILE
        // =================================================

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
    // ADMIN PROFILE
    // =====================================================

    private Button createAdminProfileImage() {

        Button profileButton = new Button();

        profileButton.setPrefSize(45, 45);
        profileButton.setMinSize(45, 45);
        profileButton.setMaxSize(45, 45);

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

            System.out.println(
                    "ERROR: Admin profile image not found: "
                    + imagePath
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
                    + "-fx-cursor: hand;"
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
                    "Admin Profile button clicked"
            );
        });

        return profileButton;
    }

    // =====================================================
    // PROFILE BUTTON STYLE
    // =====================================================

    private void setProfileButtonNormalStyle(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: transparent;"
                + "-fx-border-color: transparent;"
                + "-fx-padding: 0;"
                + "-fx-background-radius: 50;"
                + "-fx-border-radius: 50;"
                + "-fx-cursor: hand;"
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

        // =================================================
        // HEADING
        // =================================================

        VBox titleBox =
                new VBox(4);

        Label title =
                new Label(
                        "Drivers & Transporters"
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
                        "Manage and verify all driver and transporter registrations"
                );

        subtitle.setFont(
                Font.font(14)
        );

        subtitle.setTextFill(
                Color.web("#6B7280")
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        // =================================================
        // FILTERS
        // =================================================

        HBox filters =
                new HBox(15);

        VBox typeBox =
                createTypeFilter();

        VBox statusBox =
                createStatusFilter();

        HBox.setHgrow(
                typeBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                statusBox,
                Priority.ALWAYS
        );

        Label swap =
                new Label("⇄");

        swap.setFont(
                Font.font(27)
        );

        swap.setTextFill(
                Color.web(HOVER_GREEN)
        );

        StackPane swapPane =
                new StackPane(swap);

        swapPane.setPrefWidth(35);

        filters.getChildren().addAll(
                typeBox,
                swapPane,
                statusBox
        );

        // =================================================
        // TABLE
        // =================================================

        VBox table =
                createRequestsTable();

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        content.getChildren().addAll(
                titleBox,
                filters,
                table
        );

        return content;
    }

    // =====================================================
    // TYPE FILTER
    // =====================================================

    private VBox createTypeFilter() {

        VBox box =
                createClickableBlock();

        HBox labelRow =
                new HBox(9);

        labelRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon =
                new Label("♟");

        icon.setFont(
                Font.font(17)
        );

        icon.setTextFill(
                Color.web(NUMBER_GREEN)
        );

        Label label =
                new Label(
                        "1. Select Type"
                );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        labelRow.getChildren().addAll(
                icon,
                label
        );

        typeCombo =
                new ComboBox<>();

        typeCombo.getItems().addAll(
                "All (Drivers & Transporters)",
                "Drivers",
                "Transporters"
        );

        typeCombo.setValue(
                "All (Drivers & Transporters)"
        );

        typeCombo.setMaxWidth(
                Double.MAX_VALUE
        );

        typeCombo.setPrefHeight(43);

        typeCombo.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #D4DCDD;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-font-size: 13px;"
        );

        typeCombo.setOnAction(event -> {

            applyFilters();

            animateBlock(box);
        });

        box.getChildren().addAll(
                labelRow,
                typeCombo
        );

        return box;
    }

    // =====================================================
    // STATUS FILTER
    // =====================================================

    private VBox createStatusFilter() {

        VBox box =
                createClickableBlock();

        HBox labelRow =
                new HBox(9);

        labelRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label icon =
                new Label("▼");

        icon.setFont(
                Font.font(17)
        );

        icon.setTextFill(
                Color.web(NUMBER_GREEN)
        );

        Label label =
                new Label(
                        "2. Select Status"
                );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        labelRow.getChildren().addAll(
                icon,
                label
        );

        statusCombo =
                new ComboBox<>();

        // Under Review removed
        statusCombo.getItems().addAll(
                "All Status",
                "New",
                "Pending",
                "Incomplete",
                "Verified"
        );

        statusCombo.setValue(
                "All Status"
        );

        statusCombo.setMaxWidth(
                Double.MAX_VALUE
        );

        statusCombo.setPrefHeight(43);

        statusCombo.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #D4DCDD;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-font-size: 13px;"
        );

        statusCombo.setOnAction(event -> {

            applyFilters();

            animateBlock(box);
        });

        box.getChildren().addAll(
                labelRow,
                statusCombo
        );

        return box;
    }

    // =====================================================
    // CLICKABLE BLOCK
    // =====================================================

    private VBox createClickableBlock() {

        VBox box =
                new VBox(10);

        box.setPadding(
                new Insets(
                        15,
                        20,
                        17,
                        20
                )
        );

        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                BORDER_COLOR +
                ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        box.setOnMouseEntered(event -> {

            box.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: " +
                    HOVER_BORDER +
                    ";" +
                    "-fx-border-width: 1.5;" +
                    "-fx-border-radius: 10;" +
                    "-fx-background-radius: 10;"
            );
        });

        box.setOnMouseExited(event -> {

            box.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: " +
                    BORDER_COLOR +
                    ";" +
                    "-fx-border-radius: 10;" +
                    "-fx-background-radius: 10;"
            );
        });

        return box;
    }

    // =====================================================
    // ANIMATION
    // =====================================================

    private void animateBlock(VBox box) {

        ScaleTransition grow =
                new ScaleTransition(
                        Duration.millis(100),
                        box
                );

        grow.setToX(1.015);
        grow.setToY(1.015);

        grow.setAutoReverse(true);
        grow.setCycleCount(2);

        grow.play();
    }

    // =====================================================
    // APPLY FILTERS
    // =====================================================

    private void applyFilters() {

        if (requestGrid == null) {
            return;
        }

        requestGrid.getChildren().clear();

        addTableHeaders();

        String selectedType =
                typeCombo.getValue();

        String selectedStatus =
                statusCombo.getValue();

        int row = 1;

        for (RequestData request : allRequests) {

            boolean typeMatches =
                    selectedType.equals(
                            "All (Drivers & Transporters)"
                    )
                    ||
                    (
                            selectedType.equals("Drivers")
                            &&
                            request.type.equals("Driver")
                    )
                    ||
                    (
                            selectedType.equals("Transporters")
                            &&
                            request.type.equals("Transporter")
                    );

            boolean statusMatches =
                    selectedStatus.equals("All Status")
                    ||
                    request.status.equals(
                            selectedStatus
                    );

            if (typeMatches && statusMatches) {

                addRequestRow(
                        requestGrid,
                        row,
                        request
                );

                row++;
            }
        }

        int resultCount = row - 1;

        if (requestCountLabel != null) {

            requestCountLabel.setText(
                    "All Requests (" +
                    resultCount +
                    ")"
            );
        }
    }

    // =====================================================
    // TABLE
    // =====================================================

    private VBox createRequestsTable() {

        VBox tableBox =
                new VBox();

        tableBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                BORDER_COLOR +
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

        requestCountLabel =
                new Label(
                        "All Requests (8)"
                );

        requestCountLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        requestCountLabel.setTextFill(
                Color.web("#172026")
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button export =
                new Button(
                        "⇩  Export"
                );

        export.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #83A99A;" +
                "-fx-text-fill: #176C4F;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 7 14 7 14;"
        );

        export.setOnAction(event ->
                System.out.println(
                        "Export clicked"
                )
        );

        titleRow.getChildren().addAll(
                requestCountLabel,
                spacer,
                export
        );

        // =================================================
        // GRID
        // =================================================

        requestGrid =
                new GridPane();

        requestGrid.setMaxWidth(
                Double.MAX_VALUE
        );

        requestGrid.getColumnConstraints().addAll(
                percentage(9),
                percentage(13),
                percentage(13),
                percentage(13),
                percentage(13),
                percentage(11),
                percentage(12),
                percentage(16)
        );

        addTableHeaders();

        addRequestRowsInitial();

        // =================================================
        // SCROLL
        // =================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        requestGrid
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
    // HEADERS
    // =====================================================

    private void addTableHeaders() {

        addTableHeader(requestGrid, "ID", 0);
        addTableHeader(requestGrid, "TYPE", 1);
        addTableHeader(requestGrid, "NAME", 2);
        addTableHeader(requestGrid, "PHONE", 3);
        addTableHeader(requestGrid, "APPLIED ON", 4);
        addTableHeader(requestGrid, "STATUS", 5);
        addTableHeader(requestGrid, "DOCUMENTS", 6);
        addTableHeader(requestGrid, "ACTIONS", 7);
    }

    // =====================================================
    // INITIAL ROWS
    // =====================================================

    private void addRequestRowsInitial() {

        int row = 1;

        for (RequestData request : allRequests) {

            addRequestRow(
                    requestGrid,
                    row,
                    request
            );

            row++;
        }
    }

    // =====================================================
    // REQUEST ROW
    // =====================================================

    private void addRequestRow(
            GridPane grid,
            int row,
            RequestData request
    ) {

        addCell(grid, request.id, 0, row);

        addTypeCell(
                grid,
                request.type,
                1,
                row
        );

        addCell(
                grid,
                request.name,
                2,
                row
        );

        addCell(
                grid,
                request.phone,
                3,
                row
        );

        addCell(
                grid,
                request.applied,
                4,
                row
        );

        addStatusCell(
                grid,
                request.status,
                5,
                row
        );

        addDocumentCell(
                grid,
                request.documents,
                6,
                row
        );

        addActionCell(
                grid,
                request,
                7,
                row
        );
    }

    // =====================================================
    // COLUMN PERCENTAGE
    // =====================================================

    private ColumnConstraints percentage(
            double value
    ) {

        ColumnConstraints c =
                new ColumnConstraints();

        c.setPercentWidth(value);

        return c;
    }

    // =====================================================
    // HEADER CELL
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
    // TYPE CELL
    // =====================================================

    private void addTypeCell(
            GridPane grid,
            String type,
            int column,
            int row
    ) {

        Label label =
                new Label(
                        type.equals("Driver")
                                ? "♟ Driver"
                                : "▰ Transporter"
                );

        label.setPadding(
                new Insets(
                        5,
                        7,
                        5,
                        7
                )
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        if (type.equals("Driver")) {

            label.setStyle(
                    "-fx-background-color: #EAF2FB;" +
                    "-fx-text-fill: #2463A4;" +
                    "-fx-background-radius: 6;"
            );

        } else {

            label.setStyle(
                    "-fx-background-color: #E6F6EC;" +
                    "-fx-text-fill: #21834A;" +
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
                        8
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
                        7,
                        5,
                        7
                )
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        String background;
        String textColor;

        switch (status) {

            case "Pending":
                background = "#FFF1DD";
                textColor = "#D27A12";
                break;

            case "New":
                background = "#E5F0FC";
                textColor = "#2569A7";
                break;

            case "Incomplete":
                background = "#FBE5E5";
                textColor = "#D03939";
                break;

            default:
                background = "#E5F5EB";
                textColor = "#21824A";
        }

        label.setStyle(
                "-fx-background-color: " +
                background +
                ";" +
                "-fx-text-fill: " +
                textColor +
                ";" +
                "-fx-background-radius: 6;"
        );

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
    // DOCUMENT CELL
    // =====================================================

    private void addDocumentCell(
            GridPane grid,
            String documents,
            int column,
            int row
    ) {

        VBox box =
                new VBox(2);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        Label document =
                new Label(documents);

        document.setFont(
                Font.font(10)
        );

        Label view =
                new Label("View");

        view.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        view.setTextFill(
                Color.web("#116A45")
        );

        view.setCursor(
                javafx.scene.Cursor.HAND
        );

        view.setOnMouseClicked(event ->
                System.out.println(
                        "Documents clicked: " +
                        documents
                )
        );

        box.getChildren().addAll(
                document,
                view
        );

        StackPane pane =
                new StackPane(box);

        pane.setMinHeight(55);

        pane.setAlignment(
                Pos.CENTER_LEFT
        );

        pane.setPadding(
                new Insets(
                        0,
                        6,
                        0,
                        8
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
            RequestData request,
            int column,
            int row
    ) {

        HBox actions =
                new HBox(5);

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

        Button view =
                smallActionButton(
                        "◉",
                        "#E8F1FA",
                        "#2367A5"
                );

        Button approve =
                smallActionButton(
                        "✓",
                        "#FFFFFF",
                        "#18834B"
                );

        Button reject =
                smallActionButton(
                        "×",
                        "#FFFFFF",
                        "#D32D37"
                );

        view.setOnAction(event ->
                showRequestDetails(request)
        );

        approve.setOnAction(event -> {

            request.status = "Verified";

            applyFilters();

            System.out.println(
                    request.name +
                    " approved"
            );
        });

        reject.setOnAction(event -> {

            request.status = "Incomplete";

            applyFilters();

            System.out.println(
                    request.name +
                    " rejected"
            );
        });

        actions.getChildren().addAll(
                view,
                approve,
                reject
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
    // REQUEST DETAILS
    // =====================================================

    private void showRequestDetails(
            RequestData request
    ) {

        Stage detailsStage =
                new Stage();

        detailsStage.setTitle(
                "Request Details - " +
                request.name
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
                        "Request Details"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        Label id =
                detailLabel(
                        "ID: " +
                        request.id
                );

        Label type =
                detailLabel(
                        "Type: " +
                        request.type
                );

        Label name =
                detailLabel(
                        "Name: " +
                        request.name
                );

        Label phone =
                detailLabel(
                        "Phone: " +
                        request.phone
                );

        Label applied =
                detailLabel(
                        "Applied On: " +
                        request.applied.replace(
                                "\n",
                                " "
                        )
                );

        Label status =
                detailLabel(
                        "Status: " +
                        request.status
                );

        Label documents =
                detailLabel(
                        "Documents: " +
                        request.documents
                );

        Button close =
                new Button("Close");

        close.setStyle(
                "-fx-background-color: " +
                DARK_GREEN +
                ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 8 20;"
        );

        close.setOnAction(
                event ->
                        detailsStage.close()
        );

        root.getChildren().addAll(
                title,
                id,
                type,
                name,
                phone,
                applied,
                status,
                documents,
                close
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

    // =====================================================
    // SMALL ACTION BUTTON
    // =====================================================

    private Button smallActionButton(
            String text,
            String background,
            String textColor
    ) {

        Button button =
                new Button(text);

        button.setPrefSize(
                32,
                30
        );

        button.setMinSize(
                32,
                30
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
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
                "-fx-background-radius: 6;"
        );

        button.setOnMouseEntered(event -> {

            ScaleTransition grow =
                    new ScaleTransition(
                            Duration.millis(100),
                            button
                    );

            grow.setToX(1.08);
            grow.setToY(1.08);

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
}

