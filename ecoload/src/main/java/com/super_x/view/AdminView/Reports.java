
package com.super_x.view.AdminView;

import com.super_x.*;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Reports {

    private NavigationService navigationService;

    public void setNavigationService(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    private void navigateTo(String pageName) {
        if (navigationService != null) {
            navigationService.navigate(pageName);
        }
    }

    // =========================================================
    // SCENE
    // =========================================================

    private Scene reportsScene;

    // =========================================================
    // STAGE
    // =========================================================

    private Stage reportsStage;

    // =========================================================
    // COLORS
    // =========================================================

    private static final String DARK_GREEN = "#004B3A";
    private static final String HOVER_GREEN = "#075E49";
    private static final String CLICK_GREEN = "#075E49";

    private static final String MAIN_BG = "#E3F2EC";
    // private static final String BG_COLOR = "#EEF8F4";

    private static final String BORDER = "#D5DBDE";
    // private static final String BORDER_COLOR = "#D5DBDE";
    // private static final String HOVER_BORDER = "#075E49";

    private static final String TEXT = "#171A1C";
    private static final String GRAY = "#697278";
    // private static final String GRAY_COLOR = "#697278";

    private static final String GREEN = "#249447";
    private static final String BLUE = "#3478D4";
    private static final String ORANGE = "#F79A1B";
    private static final String RED = "#D93636";

    private static final String TEXT_COLOR = "#171A1C";
    // private static final String NUMBER_GREEN = "#2D9950";

    // Logout colors
    private static final String LOGOUT_RED = "#C62828";
    private static final String LOGOUT_HOVER_RED = "#B71C1C";

    // =========================================================
    // SET STAGE
    // =========================================================

    public void setStage(Stage stage) {

        this.reportsStage = stage;

        Rectangle2D screenBounds =
                Screen.getPrimary().getVisualBounds();

        double screenWidth =
                screenBounds.getWidth();

        double screenHeight =
                screenBounds.getHeight();

        double stageWidth =
                screenWidth * 0.90;

        double stageHeight =
                screenHeight * 0.90;

        stageWidth =
                Math.max(stageWidth, 1000);

        stageHeight =
                Math.max(stageHeight, 650);

        stageWidth =
                Math.min(stageWidth, screenWidth);

        stageHeight =
                Math.min(stageHeight, screenHeight);

        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);

        stage.setX(
                screenBounds.getMinX()
                        + (screenWidth - stageWidth) / 2
        );

        stage.setY(
                screenBounds.getMinY()
                        + (screenHeight - stageHeight) / 2
        );

        stage.setMinWidth(1000);
        stage.setMinHeight(650);

        stage.setResizable(true);
    }

    // =========================================================
    // GET REPORTS SCENE
    // =========================================================

    public Scene getReportsScene() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: " +
                        MAIN_BG + ";"
        );

        VBox sidebar =
                createSidebar();

        root.setLeft(sidebar);

        VBox rightSide =
                new VBox();

        rightSide.setStyle(
                "-fx-background-color: " +
                        MAIN_BG + ";"
        );

        HBox topBar =
                createTopBar();

        VBox content =
                createMainContent();

        VBox.setVgrow(
                content,
                Priority.ALWAYS
        );

        rightSide.getChildren().addAll(
                topBar,
                content
        );

        root.setCenter(rightSide);

        reportsScene =
                new Scene(
                        root,
                        1200,
                        750
                );

        reportsScene.setFill(
                Color.web(MAIN_BG)
        );

        return reportsScene;
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private VBox createSidebar() {

        VBox sidebar =
                new VBox();

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
                        DARK_GREEN + ";"
        );

        // =====================================================
        // LOGO
        // =====================================================

        HBox logoBox =
                new HBox(12);

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        ImageView logoImageView =
                createSidebarLogo();

        VBox logoText =
                new VBox(1);

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

        // =====================================================
        // MENU
        // =====================================================

        VBox menu =
                new VBox(5);

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
                        false,
                        false
                );
        dashboardButton.setOnAction(event -> navigateTo("dashboard"));

        Button driverButton =
                createMenuButton(
                        "▰",
                        "Drivers & Transporters",
                        false,
                        false
                );
        driverButton.setOnAction(event -> navigateTo("drivers"));

        Button loadsButton =
                createMenuButton(
                        "♧",
                        "Loads & Trips",
                        false,
                        false
                );
        loadsButton.setOnAction(event -> navigateTo("loads"));

        Button reportsButton =
                createMenuButton(
                        "▥",
                        "Reports",
                        true,
                        false
                );
        reportsButton.setOnAction(event -> navigateTo("reports"));

        Button sosButton =
                createMenuButton(
                        "◇",
                        "SOS Alerts",
                        false,
                        false
                );
        sosButton.setOnAction(event -> navigateTo("alerts"));

        Button supportButton =
                createMenuButton(
                        "♧",
                        "Support",
                        false,
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

        // =====================================================
        // SPACER
        // =====================================================

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        // =====================================================
        // LOGOUT
        // =====================================================

        Button logout =
                createMenuButton(
                        "↪",
                        "Logout",
                        false,
                        true
                );

        sidebar.getChildren().addAll(
                logoBox,
                menu,
                spacer,
                logout
        );

        return sidebar;
    }

    // =========================================================
    // SIDEBAR LOGO
    // =========================================================

    private ImageView createSidebarLogo() {

        ImageView logoView =
                new ImageView();

        String logoPath =
                "/assets/Logo-removebg-preview.png";

        java.io.InputStream logoStream =
                getClass().getResourceAsStream(
                        logoPath
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
                            + logoPath
            );
        }

        return logoView;
    }

    // =========================================================
    // SIDEBAR MENU BUTTON
    // =========================================================

    private Button createMenuButton(
            String icon,
            String text,
            boolean active,
            boolean logoutButton
    ) {

        Button button =
                new Button();

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

        // =====================================================
        // NORMAL STYLE
        // =====================================================

        String normalStyle;

        if (logoutButton) {

            // Logout is always RED
            normalStyle =
                    "-fx-background-color: "
                            + LOGOUT_RED + ";"
                            + "-fx-background-radius: 9;";

        } else if (active) {

            normalStyle =
                    "-fx-background-color: "
                            + CLICK_GREEN + ";"
                            + "-fx-background-radius: 9;";

        } else {

            normalStyle =
                    "-fx-background-color: "
                            + DARK_GREEN + ";"
                            + "-fx-background-radius: 9;";
        }

        button.setStyle(
                normalStyle
        );

        // =====================================================
        // HOVER
        // =====================================================

        button.setOnMouseEntered(event -> {

            String hoverStyle;

            if (logoutButton) {

                // Logout remains RED on hover
                hoverStyle =
                        "-fx-background-color: "
                                + LOGOUT_HOVER_RED + ";"
                                + "-fx-background-radius: 9;";

            } else {

                hoverStyle =
                        "-fx-background-color: "
                                + HOVER_GREEN + ";"
                                + "-fx-background-radius: 9;";
            }

            button.setStyle(
                    hoverStyle
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

        // =====================================================
        // EXIT
        // =====================================================

        button.setOnMouseExited(event -> {

            button.setStyle(
                    normalStyle
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

        // =====================================================
        // LOGOUT ACTION
        // =====================================================

        if (logoutButton) {

            button.setOnAction(event -> {

                Alert alert =
                        new Alert(
                                Alert.AlertType.CONFIRMATION
                        );

                alert.setTitle("Logout");
                alert.setHeaderText("Logout from EcoLoad?");
                alert.setContentText(
                        "Are you sure you want to logout?"
                );

                alert.showAndWait();
            });
        }

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
        // SEARCH
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

        // =====================================================
        // SPACER
        // =====================================================

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        // =====================================================
        // DIVIDER
        // =====================================================

        Region divider =
                new Region();

        divider.setPrefWidth(1);
        divider.setPrefHeight(35);

        divider.setStyle(
                "-fx-background-color: #C8CED0;"
        );

        // =====================================================
        // PROFILE
        // =====================================================

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
                Color.web(TEXT)
        );

        Label adminRole =
                new Label(
                        "System Administrator"
                );

        adminRole.setFont(
                Font.font(12)
        );

        adminRole.setTextFill(
                Color.web(GRAY)
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
    // ADMIN PROFILE
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

        profileButton.setOnAction(event -> {

            showMessage(
                    "Admin Profile",
                    "Admin Profile button clicked."
            );
        });

        return profileButton;
    }

    // =========================================================
    // MAIN CONTENT
    // =========================================================

    private VBox createMainContent() {

        VBox wrapper =
                new VBox();

        wrapper.setStyle(
                "-fx-background-color: " +
                        MAIN_BG + ";"
        );

        ScrollPane scrollPane =
                new ScrollPane();

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background-color: " +
                        MAIN_BG + ";"
                        + "-fx-border-color: transparent;"
        );

        VBox content =
                new VBox(18);

        content.setPadding(
                new Insets(
                        22,
                        28,
                        22,
                        28
                )
        );

        content.setFillWidth(true);

        content.setStyle(
                "-fx-background-color: " +
                        MAIN_BG + ";"
        );

        // =====================================================
        // TITLE
        // =====================================================

        HBox titleRow =
                new HBox();

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titleBox =
                new VBox(4);

        Label title =
                new Label(
                        "Reports & Analytics"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27
                )
        );

        title.setTextFill(
                Color.web(TEXT_COLOR)
        );

        Label subtitle =
                new Label(
                        "Overview of platform performance and key metrics"
                );

        subtitle.setFont(
                Font.font(14)
        );

        subtitle.setTextFill(
                Color.web(GRAY)
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        Region titleSpacer =
                new Region();

        HBox.setHgrow(
                titleSpacer,
                Priority.ALWAYS
        );

        Button date =
                new Button(
                        "▣   20 May 2025 - 27 May 2025   ⌄"
                );

        date.setPrefHeight(40);

        date.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 6;"
                        + "-fx-background-radius: 6;"
        );

        date.setOnAction(event -> {

            showMessage(
                    "Date Range",
                    "Date range selector clicked."
            );
        });

        Button download =
                new Button(
                        "⇩  Download Report"
                );

        download.setPrefHeight(40);

        download.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        download.setTextFill(
                Color.WHITE
        );

        download.setStyle(
                "-fx-background-color: " + GREEN + ";"
                        + "-fx-background-radius: 6;"
        );

        addButtonAnimation(
                download,
                GREEN,
                "#1D7638"
        );

        download.setOnAction(event -> {

            showMessage(
                    "Download Report",
                    "Report download started."
            );
        });

        HBox actions =
                new HBox(
                        10,
                        date,
                        download
                );

        actions.setAlignment(
                Pos.CENTER
        );

        titleRow.getChildren().addAll(
                titleBox,
                titleSpacer,
                actions
        );

        // =====================================================
        // STAT CARDS
        // =====================================================

        FlowPane cards =
                new FlowPane();

        cards.setHgap(12);
        cards.setVgap(12);

        cards.setPrefWrapLength(1100);

        cards.setMaxWidth(
                Double.MAX_VALUE
        );

        VBox totalUsers =
                statCard(
                        "Total Users",
                        "1,204",
                        "↑ 12.5% from last month",
                        GREEN
                );

        VBox totalDrivers =
                statCard(
                        "Total Drivers",
                        "86",
                        "↑ 8.3% from last month",
                        BLUE
                );

        VBox totalTransporters =
                statCard(
                        "Total Transporters",
                        "42",
                        "↑ 10.7% from last month",
                        "#7B38D8"
                );

        VBox totalTrucks =
                statCard(
                        "Total Trucks",
                        "64",
                        "↑ 6.4% from last month",
                        ORANGE
                );

        VBox activeUsers =
                statCard(
                        "Active Users",
                        "312",
                        "↑ 9.1% from last month",
                        "#21845D"
                );

        VBox totalRevenue =
                statCard(
                        "Total Revenue",
                        "₹8.45L",
                        "↑ 18.6% from last month",
                        RED
                );

        makeCardClickable(
                totalUsers,
                "Total Users",
                "1,204",
                "Complete platform user statistics."
        );

        makeCardClickable(
                totalDrivers,
                "Total Drivers",
                "86",
                "Registered and active driver statistics."
        );

        makeCardClickable(
                totalTransporters,
                "Total Transporters",
                "42",
                "Transporter registration and activity statistics."
        );

        makeCardClickable(
                totalTrucks,
                "Total Trucks",
                "64",
                "Registered truck and fleet statistics."
        );

        makeCardClickable(
                activeUsers,
                "Active Users",
                "312",
                "Currently active platform users."
        );

        makeCardClickable(
                totalRevenue,
                "Total Revenue",
                "₹8.45L",
                "Platform revenue and financial performance."
        );

        cards.getChildren().addAll(
                totalUsers,
                totalDrivers,
                totalTransporters,
                totalTrucks,
                activeUsers,
                totalRevenue
        );

        // =====================================================
        // CHART ROW
        // =====================================================

        HBox chartRow =
                new HBox(15);

        VBox revenue =
                createRevenueChart();

        VBox loadStatus =
                createLoadStatusChart();

        HBox.setHgrow(
                revenue,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                loadStatus,
                Priority.ALWAYS
        );

        revenue.setMaxWidth(
                Double.MAX_VALUE
        );

        loadStatus.setMaxWidth(
                Double.MAX_VALUE
        );

        chartRow.getChildren().addAll(
                revenue,
                loadStatus
        );

        // =====================================================
        // USER GROWTH
        // =====================================================

        VBox growth =
                createUserGrowthChart();

        growth.setMaxWidth(
                Double.MAX_VALUE
        );

        content.getChildren().addAll(
                titleRow,
                cards,
                chartRow,
                growth
        );

        scrollPane.setContent(
                content
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        wrapper.getChildren().add(
                scrollPane
        );

        return wrapper;
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private VBox statCard(
            String title,
            String value,
            String growth,
            String color
    ) {

        VBox card =
                new VBox(7);

        card.setPrefHeight(125);

        card.setPrefWidth(160);

        card.setMinWidth(150);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setPadding(
                new Insets(14)
        );

        card.setCursor(
                javafx.scene.Cursor.HAND
        );

        card.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
        );

        if (reportsStage != null) {

            card.prefWidthProperty().bind(
                    reportsStage.widthProperty()
                            .multiply(0.13)
            );
        }

        HBox heading =
                new HBox(7);

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Circle icon =
                new Circle(
                        18,
                        Color.web(
                                color,
                                0.12
                        )
                );

        Label iconText =
                new Label("●");

        iconText.setTextFill(
                Color.web(color)
        );

        StackPane iconBox =
                new StackPane(
                        icon,
                        iconText
                );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        titleLabel.setTextFill(
                Color.web("#4E585D")
        );

        heading.getChildren().addAll(
                iconBox,
                titleLabel
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        valueLabel.setTextFill(
                Color.web(TEXT)
        );

        Label growthLabel =
                new Label(growth);

        growthLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        growthLabel.setTextFill(
                Color.web(GREEN)
        );

        card.getChildren().addAll(
                heading,
                valueLabel,
                growthLabel
        );

        return card;
    }

    // =========================================================
    // CARD CLICK
    // =========================================================

    private void makeCardClickable(
            VBox card,
            String title,
            String value,
            String description
    ) {

        card.setOnMouseEntered(event -> {

            card.setStyle(
                    "-fx-background-color: #FFFFFF;"
                            + "-fx-border-color: #249447;"
                            + "-fx-border-width: 1.5;"
                            + "-fx-border-radius: 8;"
                            + "-fx-background-radius: 8;"
                            + "-fx-effect: dropshadow("
                            + "gaussian,"
                            + "rgba(0,0,0,0.16),"
                            + "12,"
                            + "0.15,"
                            + "0,"
                            + "4);"
            );

            ScaleTransition grow =
                    new ScaleTransition(
                            Duration.millis(150),
                            card
                    );

            grow.setToX(1.035);
            grow.setToY(1.035);

            grow.play();
        });

        card.setOnMouseExited(event -> {

            card.setStyle(
                    "-fx-background-color: white;"
                            + "-fx-border-color: " + BORDER + ";"
                            + "-fx-border-radius: 8;"
                            + "-fx-background-radius: 8;"
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

        card.setOnMousePressed(event -> {

            ScaleTransition press =
                    new ScaleTransition(
                            Duration.millis(80),
                            card
                    );

            press.setToX(0.97);
            press.setToY(0.97);

            press.setAutoReverse(true);
            press.setCycleCount(2);

            press.play();
        });

        card.setOnMouseClicked(event -> {

            showCardDetails(
                    title,
                    value,
                    description
            );
        });
    }

    // =========================================================
    // CARD DETAILS
    // =========================================================

    private void showCardDetails(
            String title,
            String value,
            String description
    ) {

        BorderPane detailRoot =
                new BorderPane();

        detailRoot.setStyle(
                "-fx-background-color: " +
                        MAIN_BG + ";"
        );

        HBox top =
                new HBox();

        top.setPadding(
                new Insets(
                        18,
                        25,
                        18,
                        25
                )
        );

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        top.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: #D7DCDE;"
                        + "-fx-border-width: 0 0 1 0;"
        );

        Button back =
                new Button(
                        "←  Back to Reports"
                );

        back.setPrefHeight(40);

        back.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        back.setTextFill(
                Color.WHITE
        );

        back.setStyle(
                "-fx-background-color: "
                        + DARK_GREEN + ";"
                        + "-fx-background-radius: 7;"
        );

        addButtonAnimation(
                back,
                DARK_GREEN,
                HOVER_GREEN
        );

        back.setOnAction(event -> {

            showReportsAgain();
        });

        Label pageTitle =
                new Label(title);

        pageTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24
                )
        );

        pageTitle.setTextFill(
                Color.web(TEXT)
        );

        HBox.setMargin(
                pageTitle,
                new Insets(
                        0,
                        0,
                        0,
                        25
                )
        );

        top.getChildren().addAll(
                back,
                pageTitle
        );

        detailRoot.setTop(top);

        VBox detailContent =
                new VBox(20);

        detailContent.setPadding(
                new Insets(30)
        );

        Label heading =
                new Label(
                        title + " Details"
                );

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );

        heading.setTextFill(
                Color.web(TEXT)
        );

        Label desc =
                new Label(description);

        desc.setFont(
                Font.font(15)
        );

        desc.setTextFill(
                Color.web(GRAY)
        );

        VBox mainCard =
                new VBox(10);

        mainCard.setPadding(
                new Insets(25)
        );

        mainCard.setPrefWidth(420);

        mainCard.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 12;"
                        + "-fx-background-radius: 12;"
                        + "-fx-effect: dropshadow("
                        + "gaussian,"
                        + "rgba(0,0,0,0.10),"
                        + "12,"
                        + "0.1,"
                        + "0,"
                        + "3);"
        );

        Label valueTitle =
                new Label(
                        "Current Value"
                );

        valueTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        valueTitle.setTextFill(
                Color.web(GRAY)
        );

        Label bigValue =
                new Label(value);

        bigValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        42
                )
        );

        bigValue.setTextFill(
                Color.web(DARK_GREEN)
        );

        Label status =
                new Label(
                        "↑ Positive growth compared with last month"
                );

        status.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        status.setTextFill(
                Color.web(GREEN)
        );

        mainCard.getChildren().addAll(
                valueTitle,
                bigValue,
                status
        );

        detailContent.getChildren().addAll(
                heading,
                desc,
                mainCard
        );

        ScrollPane scroll =
                new ScrollPane(
                        detailContent
                );

        scroll.setFitToWidth(true);

        scroll.setStyle(
                "-fx-background-color: " +
                        MAIN_BG + ";"
                        + "-fx-border-color: transparent;"
        );

        detailRoot.setCenter(scroll);

        reportsScene =
                new Scene(detailRoot);

        reportsScene.setFill(
                Color.web(MAIN_BG)
        );

        if (reportsStage != null) {

            reportsStage.setScene(
                    reportsScene
            );

            reportsStage.setTitle(
                    "EcoLoad - " + title
            );
        }
    }

    // =========================================================
    // BACK TO REPORTS
    // =========================================================

    private void showReportsAgain() {

        if (reportsStage != null) {

            reportsStage.setScene(
                    getReportsScene()
            );

            reportsStage.setTitle(
                    "EcoLoad - Reports & Analytics"
            );
        }
    }

    // =========================================================
    // BUTTON ANIMATION
    // =========================================================

    private void addButtonAnimation(
            Button button,
            String normalColor,
            String hoverColor
    ) {

        button.setOnMouseEntered(event -> {

            button.setStyle(
                    "-fx-background-color: "
                            + hoverColor + ";"
                            + "-fx-background-radius: 7;"
            );

            ScaleTransition grow =
                    new ScaleTransition(
                            Duration.millis(120),
                            button
                    );

            grow.setToX(1.04);
            grow.setToY(1.04);

            grow.play();
        });

        button.setOnMouseExited(event -> {

            button.setStyle(
                    "-fx-background-color: "
                            + normalColor + ";"
                            + "-fx-background-radius: 7;"
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
    }

    // =========================================================
    // REVENUE CHART
    // =========================================================

    private VBox createRevenueChart() {

        VBox box =
                new VBox(8);

        box.setPrefHeight(280);
        box.setMinHeight(260);

        box.setPadding(
                new Insets(15)
        );

        box.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
        );

        makeChartBlockInteractive(box);

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label(
                        "Total Revenue Overview"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button week =
                new Button(
                        "This Week   ⌄"
                );

        week.setCursor(
                javafx.scene.Cursor.HAND
        );

        week.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 5;"
                        + "-fx-background-radius: 5;"
        );

        week.setOnAction(event -> {

            showMessage(
                    "Revenue - This Week",
                    "Showing revenue data for this week."
            );
        });

        addSimpleButtonHover(week);

        heading.getChildren().addAll(
                title,
                spacer,
                week
        );

        CategoryAxis xAxis =
                new CategoryAxis();

        NumberAxis yAxis =
                new NumberAxis();

        yAxis.setLabel(
                "Revenue (₹)"
        );

        yAxis.setAutoRanging(false);

        yAxis.setLowerBound(0);
        yAxis.setUpperBound(100000);
        yAxis.setTickUnit(20000);

        LineChart<String, Number> chart =
                new LineChart<>(
                        xAxis,
                        yAxis
                );

        chart.setLegendVisible(false);
        chart.setAnimated(false);
        chart.setCreateSymbols(true);

        chart.setHorizontalGridLinesVisible(true);
        chart.setVerticalGridLinesVisible(false);

        chart.setMinHeight(200);

        XYChart.Series<String, Number> series =
                new XYChart.Series<>();

        series.getData().addAll(

                new XYChart.Data<>(
                        "21 May",
                        30000
                ),

                new XYChart.Data<>(
                        "22 May",
                        48000
                ),

                new XYChart.Data<>(
                        "23 May",
                        33000
                ),

                new XYChart.Data<>(
                        "24 May",
                        62000
                ),

                new XYChart.Data<>(
                        "25 May",
                        70000
                ),

                new XYChart.Data<>(
                        "26 May",
                        58000
                ),

                new XYChart.Data<>(
                        "27 May",
                        92000
                )
        );

        chart.getData().add(series);

        chart.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-padding: 0;"
        );

        VBox.setVgrow(
                chart,
                Priority.ALWAYS
        );

        box.getChildren().addAll(
                heading,
                chart
        );

        return box;
    }

    // =========================================================
    // LOAD STATUS PIE CHART
    // =========================================================

    private VBox createLoadStatusChart() {

        VBox box =
                new VBox(8);

        box.setPrefHeight(280);
        box.setMinHeight(260);

        box.setPadding(
                new Insets(15)
        );

        box.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
        );

        makeChartBlockInteractive(box);

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label(
                        "Load Status Distribution"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button week =
                new Button(
                        "This Week   ⌄"
                );

        week.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 5;"
                        + "-fx-background-radius: 5;"
        );

        week.setCursor(
                javafx.scene.Cursor.HAND
        );

        week.setOnAction(event -> {

            showMessage(
                    "Load Status - This Week",
                    "Showing current week's load distribution."
            );
        });

        addSimpleButtonHover(week);

        heading.getChildren().addAll(
                title,
                spacer,
                week
        );

        PieChart pie =
                new PieChart();

        pie.setLabelsVisible(false);
        pie.setLegendVisible(true);
        pie.setStartAngle(90);

        pie.setMinHeight(210);

        pie.setData(
                FXCollections.observableArrayList(

                        new PieChart.Data(
                                "Active Load  189",
                                189
                        ),

                        new PieChart.Data(
                                "In Transit  132",
                                132
                        ),

                        new PieChart.Data(
                                "Delivered  98",
                                98
                        ),

                        new PieChart.Data(
                                "Unanswered  51",
                                51
                        )
                )
        );

        pie.setStyle(
                "-fx-background-color: transparent;"
        );

        VBox.setVgrow(
                pie,
                Priority.ALWAYS
        );

        box.getChildren().addAll(
                heading,
                pie
        );

        return box;
    }

    // =========================================================
    // USER GROWTH CHART
    // =========================================================

    private VBox createUserGrowthChart() {

        VBox box =
                new VBox(8);

        box.setPrefHeight(270);
        box.setMinHeight(260);

        box.setPadding(
                new Insets(15)
        );

        box.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
        );

        makeChartBlockInteractive(box);

        HBox heading =
                new HBox();

        heading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label title =
                new Label(
                        "Monthly User Growth"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button year =
                new Button(
                        "This Year   ⌄"
                );

        year.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 5;"
                        + "-fx-background-radius: 5;"
        );

        year.setCursor(
                javafx.scene.Cursor.HAND
        );

        year.setOnAction(event -> {

            showMessage(
                    "User Growth - This Year",
                    "Showing monthly user growth for this year."
            );
        });

        addSimpleButtonHover(year);

        heading.getChildren().addAll(
                title,
                spacer,
                year
        );

        CategoryAxis xAxis =
                new CategoryAxis();

        NumberAxis yAxis =
                new NumberAxis();

        yAxis.setLabel(
                "Users"
        );

        BarChart<String, Number> chart =
                new BarChart<>(
                        xAxis,
                        yAxis
                );

        chart.setLegendVisible(false);
        chart.setAnimated(false);

        chart.setCategoryGap(15);
        chart.setBarGap(3);

        chart.setMinHeight(200);

        XYChart.Series<String, Number> series =
                new XYChart.Series<>();

        series.getData().addAll(

                new XYChart.Data<>(
                        "Jun 2024",
                        320
                ),

                new XYChart.Data<>(
                        "Jul 2024",
                        450
                ),

                new XYChart.Data<>(
                        "Aug 2024",
                        280
                ),

                new XYChart.Data<>(
                        "Sep 2024",
                        120
                ),

                new XYChart.Data<>(
                        "Oct 2024",
                        610
                ),

                new XYChart.Data<>(
                        "Nov 2024",
                        430
                ),

                new XYChart.Data<>(
                        "Dec 2024",
                        510
                ),

                new XYChart.Data<>(
                        "Jan 2025",
                        780
                ),

                new XYChart.Data<>(
                        "Feb 2025",
                        210
                ),

                new XYChart.Data<>(
                        "Mar 2025",
                        530
                ),

                new XYChart.Data<>(
                        "Apr 2025",
                        620
                ),

                new XYChart.Data<>(
                        "May 2025",
                        410
                )
        );

        chart.getData().add(series);

        chart.setStyle(
                "-fx-background-color: transparent;"
        );

        VBox.setVgrow(
                chart,
                Priority.ALWAYS
        );

        box.getChildren().addAll(
                heading,
                chart
        );

        return box;
    }

    // =========================================================
    // CHART HOVER
    // =========================================================

    private void makeChartBlockInteractive(
            VBox block
    ) {

        block.setCursor(
                javafx.scene.Cursor.HAND
        );

        block.setOnMouseEntered(event -> {

            block.setStyle(
                    "-fx-background-color: #FFFFFF;"
                            + "-fx-border-color: #249447;"
                            + "-fx-border-width: 1.5;"
                            + "-fx-border-radius: 8;"
                            + "-fx-background-radius: 8;"
                            + "-fx-effect: dropshadow("
                            + "gaussian,"
                            + "rgba(0,0,0,0.16),"
                            + "12,"
                            + "0.15,"
                            + "0,"
                            + "4);"
            );

            ScaleTransition grow =
                    new ScaleTransition(
                            Duration.millis(150),
                            block
                    );

            grow.setToX(1.015);
            grow.setToY(1.015);

            grow.play();
        });

        block.setOnMouseExited(event -> {

            block.setStyle(
                    "-fx-background-color: white;"
                            + "-fx-border-color: " + BORDER + ";"
                            + "-fx-border-radius: 8;"
                            + "-fx-background-radius: 8;"
            );

            ScaleTransition shrink =
                    new ScaleTransition(
                            Duration.millis(150),
                            block
                    );

            shrink.setToX(1.0);
            shrink.setToY(1.0);

            shrink.play();
        });
    }

    // =========================================================
    // SIMPLE BUTTON HOVER
    // =========================================================

    private void addSimpleButtonHover(
            Button button
    ) {

        button.setOnMouseEntered(event -> {

            button.setStyle(
                    "-fx-background-color: #EAF3EF;"
                            + "-fx-border-color: #249447;"
                            + "-fx-border-radius: 5;"
                            + "-fx-background-radius: 5;"
            );

            ScaleTransition grow =
                    new ScaleTransition(
                            Duration.millis(100),
                            button
                    );

            grow.setToX(1.03);
            grow.setToY(1.03);

            grow.play();
        });

        button.setOnMouseExited(event -> {

            button.setStyle(
                    "-fx-background-color: white;"
                            + "-fx-border-color: " + BORDER + ";"
                            + "-fx-border-radius: 5;"
                            + "-fx-background-radius: 5;"
            );

            ScaleTransition shrink =
                    new ScaleTransition(
                            Duration.millis(100),
                            button
                    );

            shrink.setToX(1.0);
            shrink.setToY(1.0);

            shrink.play();
        });
    }

    // =========================================================
    // MESSAGE
    // =========================================================

    private void showMessage(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}
