package com.super_x.view.AdminView;

import com.super_x.*;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class AdminDashboard {

    private Scene dashboardScene;
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
    // COLORS
    // =========================================================

   /*  private final String DARK_GREEN = "#176B57";

    private final String HOVER_GREEN = "#2B806B";

    private final String CLICK_GREEN = "#5FAF98";

    private final String NUMBER_GREEN = "#2D9950";

    private final String BG_COLOR = "#E3F2EC";


    private final String BORDER_COLOR = "#D5DBDE";

    private final String HOVER_BORDER = "#075E49";

    private final String GRAY_COLOR = "#697278";



    private final String DARK_GREEN = "#004B3A";

        private final String HOVER_GREEN = "#075E49";

        private final String CLICK_GREEN = "#175f4d";

        private final String NUMBER_GREEN = "#278A4A";

        private final String BG_COLOR = "#EEF8F4";

        private final String BORDER_COLOR = "#D8E5E0";

        private final String HOVER_BORDER = "#4AA98C";

        private final String GRAY_COLOR = "#697278";*/
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
    

    // =========================================================
    // GET ADMIN DASHBOARD SCENE
    // =========================================================

    public Scene getAdminDashboardScene() {

        // =====================================================
        // ROOT
        // =====================================================

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

        VBox content = createDashboardContent();

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
        // CREATE SCENE
        // =====================================================

        dashboardScene = new Scene(
                root
        );

        // =====================================================
        // SIDE BAR IS FIXED FOR ALL PAGES
        // =====================================================

        // =====================================================
        // RETURN SCENE TO MAIN
        // =====================================================

        return dashboardScene;
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
                new Label(
                        "EcoLoad"
                );

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

        // =====================================================
        // DASHBOARD
        // =====================================================

        Button dashboardButton =
                createMenuButton(
                        "▦",
                        "Dashboard",
                        true
                );
        dashboardButton.setOnAction(event -> navigateTo("dashboard"));

        // =====================================================
        // DRIVERS & TRANSPORTERS
        // =====================================================

        Button driverButton =
                createMenuButton(
                        "▰",
                        "Drivers & Transporters",
                        false
                );
        driverButton.setOnAction(event -> navigateTo("drivers"));

        // =====================================================
        // LOADS & TRIPS
        // =====================================================

        Button loadsButton =
                createMenuButton(
                        "♧",
                        "Loads & Trips",
                        false
                );
        loadsButton.setOnAction(event -> navigateTo("loads"));

        // =====================================================
        // REPORTS
        // =====================================================

        Button reportsButton =
                createMenuButton(
                        "▥",
                        "Reports",
                        false
                );
        reportsButton.setOnAction(event -> navigateTo("reports"));

        // =====================================================
        // SOS ALERTS
        // =====================================================

        Button sosButton =
                createMenuButton(
                        "◇",
                        "SOS Alerts",
                        false
                );
        sosButton.setOnAction(event -> navigateTo("alerts"));

        // =====================================================
        // SUPPORT
        // =====================================================

        Button supportButton =
                createMenuButton(
                        "♧",
                        "Support",
                        false
                );
        supportButton.setOnAction(event -> navigateTo("support"));

        // =====================================================
        // SETTINGS
        // =====================================================

   

        // =====================================================
        // ADD BUTTONS
        // =====================================================

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
});

logout.setOnMouseExited(event -> {

    logout.setStyle(
            "-fx-background-color: #B42335;" +
            "-fx-background-radius: 9;"
    );
});

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

        java.io.InputStream logoStream =
                getClass().getResourceAsStream(
                        "/assets/Logo-removebg-preview.png"
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
                    "/assets/Logo-removebg-preview.png"
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
            boolean active
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

        // =====================================================
        // ICON
        // =====================================================

        Label iconLabel =
                new Label(icon);

        iconLabel.setPrefWidth(28);

        iconLabel.setFont(
                Font.font(20)
        );

        iconLabel.setTextFill(
                Color.WHITE
        );

        // =====================================================
        // TEXT
        // =====================================================

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

        // =====================================================
        // BUTTON CONTENT
        // =====================================================

        HBox box =
                new HBox(
                        12,
                        iconLabel,
                        textLabel
                );

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        button.setGraphic(
                box
        );

        // =====================================================
        // INITIAL COLOR
        // =====================================================

        if (active) {

            button.setStyle(
                    "-fx-background-color: " +
                    CLICK_GREEN + ";" +
                    "-fx-background-radius: 9;"
            );

        } else {

    button.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background-radius: 9;"
    );
}

        // =====================================================
        // HOVER
        // =====================================================

        button.setOnMouseEntered(event -> {

            button.setStyle(
                    "-fx-background-color: " +
                    HOVER_GREEN + ";" +
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

        // =====================================================
        // MOUSE EXIT
        // =====================================================

        button.setOnMouseExited(event -> {

            if (active) {

                button.setStyle(
                        "-fx-background-color: " +
                        CLICK_GREEN + ";" +
                        "-fx-background-radius: 9;"
                );

            } else {

                button.setStyle(
                        "-fx-background-color: " +
                        DARK_GREEN + ";" +
                        "-fx-background-radius: 9;"
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

        // =====================================================
        // CLICK
        // =====================================================

        button.setOnAction(event -> {

            button.setStyle(
                    "-fx-background-color: " +
                    CLICK_GREEN + ";" +
                    "-fx-background-radius: 9;"
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
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #FFFFFF;" +
                "-fx-border-width: 0 0 1 0;"
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
                "-fx-background-color:  #F5F9F7;" +
                "-fx-border-color: #C9D8D2;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );

        // =====================================================
        // SEARCH ICON
        // =====================================================

        Label searchIcon =
                new Label("⌕");

        searchIcon.setFont(
                Font.font(28)
        );

        // =====================================================
        // SEARCH FIELD
        // =====================================================

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
        // ADMIN PROFILE TEXT
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
                Color.web(
                        GRAY_COLOR
                )
        );

        profileText.getChildren().addAll(
                adminProfile,
                adminRole
        );

        // =====================================================
        // ADMIN PROFILE BUTTON
        // =====================================================

        Button profileButton =
                createAdminProfileImage();

        // =====================================================
        // ADD TO NAVBAR
        // =====================================================

        topBar.getChildren().addAll(

                searchBox,

                spacer,

                divider,

                profileText,

                profileButton
        );

        // =====================================================
        // MARGINS
        // =====================================================

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
    // ADMIN PROFILE IMAGE BUTTON
    // =========================================================

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

        profileButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-padding: 0;" +
                "-fx-background-radius: 50;" +
                "-fx-border-radius: 50;"
        );

        String imagePath =
                "/assets/Admin_Profile_Logo.png";

        java.io.InputStream profileStream =
                getClass().getResourceAsStream(
                        imagePath
                );

        if (profileStream != null) {

            Image profileImage =
                    new Image(
                            profileStream
                    );

            ImageView profileImageView =
                    new ImageView(
                            profileImage
                    );

            profileImageView.setFitWidth(
                    45
            );

            profileImageView.setFitHeight(
                    45
            );

            profileImageView.setPreserveRatio(
                    false
            );

            profileImageView.setSmooth(
                    true
            );

            Circle clip =
                    new Circle(
                            22.5,
                            22.5,
                            22.5
                    );

            profileImageView.setClip(
                    clip
            );

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

        // =====================================================
        // PROFILE HOVER
        // =====================================================

        profileButton.setOnMouseEntered(event -> {

            profileButton.setStyle(
                    "-fx-background-color: #E4F0EB;" +
                    "-fx-border-color: #075E49;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 50;" +
                    "-fx-background-radius: 50;" +
                    "-fx-padding: 0;"
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

        // =====================================================
        // PROFILE MOUSE EXIT
        // =====================================================

        profileButton.setOnMouseExited(event -> {

            profileButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-border-color: transparent;" +
                    "-fx-padding: 0;" +
                    "-fx-background-radius: 50;" +
                    "-fx-border-radius: 50;"
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

        // =====================================================
        // PROFILE CLICK
        // =====================================================

        profileButton.setOnAction(event -> {

            System.out.println(
                    "Admin Profile button clicked"
            );
        });

        return profileButton;
    }

    // =========================================================
    // DASHBOARD CONTENT
    // =========================================================

    private VBox createDashboardContent() {

        VBox content =
                new VBox(20);

        content.setPadding(
                new Insets(
                        30,
                        30,
                        25,
                        30
                )
        );

        content.setFillWidth(true);

        // =====================================================
        // TITLE
        // =====================================================

        HBox titleRow =
                new HBox();

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox titles =
                new VBox(5);

        Label dashboard =
                new Label(
                        "Dashboard"
                );

        dashboard.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        31
                )
        );

        dashboard.setTextFill(
                Color.web(TEXT_COLOR)
        );

        Label description =
                new Label(
                        "Overview of fleet operations and platform health."
                );

        description.setFont(
                Font.font(15)
        );

        description.setTextFill(
                Color.web(
                        GRAY_COLOR
                )
        );

        titles.getChildren().addAll(
                dashboard,
                description
        );

        Region titleSpacer =
                new Region();

        HBox.setHgrow(
                titleSpacer,
                Priority.ALWAYS
        );

        titleRow.getChildren().addAll(
                titles,
                titleSpacer
        );

        // =====================================================
        // CARDS
        // =====================================================

        HBox cards =
                new HBox(20);

        cards.setFillHeight(true);

        VBox activeUsers =
                createCard(
                        "Active Users",
                        "1,204",
                        "↗ +12% this week",
                        false
                );

        VBox trips =
                createCard(
                        "In-Transit Trips",
                        "86",
                        "Across 12 routes",
                        false
                );

        VBox revenue =
                createCard(
                        "Revenue This Month",
                        "₹8.4L",
                        "↗ +5.2% vs last mo",
                        false
                );

        VBox sos =
                createCard(
                        "●  Open SOS Alerts",
                        "1",
                        "ACTION REQUIRED",
                        true
                );

        HBox.setHgrow(
                activeUsers,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                trips,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                revenue,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                sos,
                Priority.ALWAYS
        );

        cards.getChildren().addAll(
                activeUsers,
                trips,
                revenue,
                sos
        );

        // =====================================================
        // LOWER SECTION
        // =====================================================

        HBox lowerSection =
                new HBox(20);

        VBox verification =
                createVerificationPanel();

        VBox support =
                createSupportPanel();

        HBox.setHgrow(
                verification,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                support,
                Priority.ALWAYS
        );

        lowerSection.getChildren().addAll(
                verification,
                support
        );

        VBox.setVgrow(
                lowerSection,
                Priority.ALWAYS
        );

        content.getChildren().addAll(
                titleRow,
                cards,
                lowerSection
        );

        return content;
    }

    // =========================================================
    // INTERACTIVE CARD
    // =========================================================

    private VBox createCard(
            String title,
            String number,
            String bottomText,
            boolean sos
    ) {

        VBox card =
                new VBox(8);

        card.setPadding(
                new Insets(
                        18,
                        20,
                        15,
                        20
                )
        );

        card.setMinHeight(125);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                BORDER_COLOR + ";" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14
                )
        );

        titleLabel.setTextFill(
                Color.web(
                        GRAY_COLOR
                )
        );

        Label numberLabel =
                new Label(number);

        numberLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        if (sos) {

            numberLabel.setTextFill(
                    Color.web("#B42335")
            );

        } else {

            numberLabel.setTextFill(
                    Color.web(NUMBER_GREEN)
            );
        }

        Label bottomLabel =
                new Label(bottomText);

        bottomLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        if (sos) {

            bottomLabel.setTextFill(
                    Color.web("#9B3442")
            );

        } else {

            bottomLabel.setTextFill(
                    Color.web(GRAY_COLOR)
            );
        }

        card.getChildren().addAll(
                titleLabel,
                numberLabel,
                bottomLabel
        );

        // =====================================================
        // CARD HOVER
        // =====================================================

        card.setOnMouseEntered(event -> {

            card.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: " +
                    "#4AA98C" + ";" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 10;" +
                    "-fx-background-radius: 10;"
            );

            ScaleTransition transition =
                    new ScaleTransition(
                            Duration.millis(150),
                            card
                    );

            transition.setToX(0.97);

            transition.setToY(0.97);

            transition.play();
        });

        // =====================================================
        // CARD MOUSE EXIT
        // =====================================================

        card.setOnMouseExited(event -> {

            card.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: " +
                    BORDER_COLOR + ";" +
                    "-fx-border-width: 1;" +
                    "-fx-border-radius: 10;" +
                    "-fx-background-radius: 10;"
            );

            ScaleTransition transition =
                    new ScaleTransition(
                            Duration.millis(150),
                            card
                    );

            transition.setToX(1.0);

            transition.setToY(1.0);

            transition.play();
        });

        return card;
    }

    // =========================================================
    // NEW VERIFICATIONS
    // =========================================================

    private VBox createVerificationPanel() {

        VBox panel =
                new VBox(8);

        panel.setPadding(
                new Insets(15)
        );

        panel.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                BORDER_COLOR + ";" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        addPanelHoverEffect(panel);

        Label heading =
                new Label(
                        "New Verifications"
                );

        heading.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        heading.setTextFill(
                Color.web("#354052")
        );

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        Label viewAll =
                new Label(
                        "View All"
                );

        viewAll.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        viewAll.setTextFill(
                Color.web("#426AA3")
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        header.getChildren().addAll(
                heading,
                spacer,
                viewAll
        );

        HBox tableHeader =
                createTableRow(
                        "TYPE",
                        "NAME",
                        "DATE",
                        "STATUS",
                        true
                );

        panel.getChildren().addAll(

                header,

                tableHeader,

                createTableRow(
                        "Driver",
                        "Rahul Sharma",
                        "Oct 24, 2023",
                        "Verified",
                        false
                ),

                createTableRow(
                        "Transporter",
                        "Apex Logistics",
                        "Oct 24, 2023",
                        "Pending",
                        false
                ),

                createTableRow(
                        "Truck",
                        "MH-12-AB-1234",
                        "Oct 23, 2023",
                        "Rejected",
                        false
                ),

                createTableRow(
                        "Driver",
                        "Amit Patel",
                        "Oct 22, 2023",
                        "Pending",
                        false
                )
        );

        return panel;
    }

    // =========================================================
    // TABLE ROW
    // =========================================================

    private HBox createTableRow(
            String type,
            String name,
            String date,
            String status,
            boolean header
    ) {

        HBox row =
                new HBox();

        row.setMinHeight(38);

        Label typeLabel =
                new Label(type);

        Label nameLabel =
                new Label(name);

        Label dateLabel =
                new Label(date);

        Label statusLabel =
                new Label(status);

        typeLabel.setPrefWidth(90);

        nameLabel.setPrefWidth(150);

        dateLabel.setPrefWidth(110);

        statusLabel.setPrefWidth(90);

        Label[] labels = {

                typeLabel,

                nameLabel,

                dateLabel,

                statusLabel
        };

        for (Label label : labels) {

            label.setWrapText(true);

            if (header) {

                label.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.BOLD,
                                11
                        )
                );

                label.setTextFill(
                        Color.web("#697278")
                );

            } else {

                label.setFont(
                        Font.font(
                                "Arial",
                                FontWeight.NORMAL,
                                12
                        )
                );

                label.setTextFill(
                        Color.web("#454D52")
                );
            }
        }

        if (!header) {

            if (status.equalsIgnoreCase("Verified")) {

                statusLabel.setStyle(
                        "-fx-background-color: #DFF4E5;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 5 8 5 8;" +
                        "-fx-text-fill: #218838;" +
                        "-fx-font-weight: bold;"
                );

            } else if (
                    status.equalsIgnoreCase("Pending")
            ) {

                statusLabel.setStyle(
                        "-fx-background-color: #FFF3CD;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 5 8 5 8;" +
                        "-fx-text-fill: #B77900;" +
                        "-fx-font-weight: bold;"
                );

            } else if (
                    status.equalsIgnoreCase("Rejected")
            ) {

                statusLabel.setStyle(
                        "-fx-background-color: #FDE2E2;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 5 8 5 8;" +
                        "-fx-text-fill: #C62828;" +
                        "-fx-font-weight: bold;"
                );
            }
        }

        row.getChildren().addAll(

                typeLabel,

                nameLabel,

                dateLabel,

                statusLabel
        );

        return row;
    }

    // =========================================================
    // SUPPORT PANEL
    // =========================================================

    private VBox createSupportPanel() {

        VBox panel =
                new VBox(8);

        panel.setPadding(
                new Insets(15)
        );

        panel.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " +
                BORDER_COLOR + ";" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );

        addPanelHoverEffect(panel);

        HBox heading =
                new HBox();

        Label title =
                new Label(
                        "Recent Support Tickets"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        title.setTextFill(
                Color.web("#354052")
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label view =
                new Label(
                        "View All"
                );

        view.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        view.setTextFill(
                Color.web("#426AA3")
        );

        heading.getChildren().addAll(

                title,

                spacer,

                view
        );

        HBox header =
                new HBox();

        header.setMinHeight(35);

        Label id =
                new Label("ID");

        Label subject =
                new Label("SUBJECT");

        Label user =
                new Label("USER");

        Label status =
                new Label("STATUS");

        id.setPrefWidth(65);

        subject.setPrefWidth(150);

        user.setPrefWidth(100);

        status.setPrefWidth(70);

        Label[] headers = {

                id,

                subject,

                user,

                status
        };

        for (Label label : headers) {

            label.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            11
                    )
            );

            label.setTextFill(
                    Color.web("#697278")
            );
        }

        header.getChildren().addAll(

                id,

                subject,

                user,

                status
        );

        panel.getChildren().addAll(

                heading,

                header,

                createTicketRow(
                        "#T-904",
                        "App crash on...",
                        "Vikram S.",
                        "Open"
                ),

                createTicketRow(
                        "#T-903",
                        "Payment del...",
                        "Blue Dart Log.",
                        "Open"
                ),

                createTicketRow(
                        "#T-902",
                        "Change regis...",
                        "Suresh K.",
                        "Closed"
                ),

                createTicketRow(
                        "#T-901",
                        "Update KYC...",
                        "FastTrack...",
                        "Closed"
                )
        );

        return panel;
    }

    // =========================================================
    // PANEL HOVER EFFECT
    // =========================================================

    private void addPanelHoverEffect(
            VBox panel
    ) {

        panel.setOnMouseEntered(event -> {

            panel.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: " +
                    HOVER_BORDER + ";" +
                    "-fx-border-width: 2;" +
                    "-fx-border-radius: 10;" +
                    "-fx-background-radius: 10;"
            );

            ScaleTransition transition =
                    new ScaleTransition(
                            Duration.millis(150),
                            panel
                    );

            transition.setToX(0.985);

            transition.setToY(0.985);

            transition.play();
        });

        panel.setOnMouseExited(event -> {

            panel.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: " +
                    BORDER_COLOR + ";" +
                    "-fx-border-width: 1;" +
                    "-fx-border-radius: 10;" +
                    "-fx-background-radius: 10;"
            );

            ScaleTransition transition =
                    new ScaleTransition(
                            Duration.millis(150),
                            panel
                    );

            transition.setToX(1.0);

            transition.setToY(1.0);

            transition.play();
        });
    }

    // =========================================================
    // SUPPORT TICKET ROW
    // =========================================================

    private HBox createTicketRow(
            String idText,
            String subjectText,
            String userText,
            String statusText
    ) {

        HBox row =
                new HBox();

        row.setMinHeight(40);

        Label id =
                new Label(idText);

        Label subject =
                new Label(subjectText);

        Label user =
                new Label(userText);

        Label status =
                new Label(statusText);

        id.setPrefWidth(65);

        subject.setPrefWidth(150);

        user.setPrefWidth(100);

        status.setPrefWidth(70);

        Label[] labels = {

                id,

                subject,

                user,

                status
        };

        for (Label label : labels) {

            label.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.NORMAL,
                            11
                    )
            );

            label.setTextFill(
                    Color.web("#454D52")
            );

            label.setWrapText(true);
        }

        // =====================================================
        // OPEN / CLOSED STATUS
        // =====================================================

        if (statusText.equals("Open")) {

            status.setStyle(
                    "-fx-background-color: #F6E6E8;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 5 8 5 8;"
            );

        } else {

            status.setStyle(
                    "-fx-background-color: #E8ECEE;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 5 8 5 8;"
            );
        }

        row.getChildren().addAll(

                id,

                subject,

                user,

                status
        );

        return row;
    }
}