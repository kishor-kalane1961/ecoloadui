package com.super_x.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;


public class Login {

   

    private Scene LoginScene;

    
    public Scene getScene(){

      

        // =====================================================
        // GET DESKTOP SCREEN SIZE
        // =====================================================

        Rectangle2D screenBounds =
                Screen.getPrimary().getVisualBounds();

        double screenWidth =
                screenBounds.getWidth();

        double screenHeight =
                screenBounds.getHeight();

        // =====================================================
        // MAIN CONTAINER
        // =====================================================

        HBox mainBox = new HBox();

        mainBox.setPrefSize(1200, 700);
        mainBox.setMinSize(1200, 700);
        mainBox.setMaxSize(1200, 700);

        mainBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #E5E7EB;" +
                "-fx-border-radius: 18;"
        );

        // =====================================================
        // LEFT PANEL
        // =====================================================

        VBox leftPanel = new VBox();

        leftPanel.setPrefWidth(430);
        leftPanel.setMinWidth(430);
        leftPanel.setMaxWidth(430);

        leftPanel.setPadding(
                new Insets(
                        30,
                        40,
                        35,
                        45
                )
        );

        leftPanel.setStyle(
                "-fx-background-color: #103D2F;" +
                "-fx-background-radius: 18 0 0 18;"
        );

        // =====================================================
        // ECOLOAD LOGO
        // =====================================================

        ImageView topLogo =
                createTopLogo();

        HBox logoContainer =
                new HBox();

        logoContainer.setAlignment(
                Pos.CENTER_LEFT
        );

        logoContainer.getChildren().add(
                topLogo
        );

        // =====================================================
        // BRAND BOX
        // =====================================================

        VBox brandBox =
                new VBox();

        brandBox.setAlignment(
                Pos.TOP_LEFT
        );

        brandBox.getChildren().add(
                logoContainer
        );

        // =====================================================
        // TRUCK IMAGE AREA
        // =====================================================

        StackPane truckArea =
                createTruckArea();

        VBox.setVgrow(
                truckArea,
                Priority.ALWAYS
        );

        // =====================================================
        // FEATURES
        // =====================================================

        VBox features =
                new VBox(15);

        features.setAlignment(
                Pos.BOTTOM_LEFT
        );

        features.getChildren().addAll(

                createFeature(
                        "♙",
                        "Secure & Reliable",
                        "Your data is protected with\nenterprise-grade security."
                ),

                createFeature(
                        "▥",
                        "Powerful Dashboard",
                        "Get real-time insights and\nmanage operations efficiently."
                ),

                createFeature(
                        "♧",
                        "Complete Control",
                        "Manage users, loads, trucks\nand more from one place."
                )
        );

        // =====================================================
        // ADD LEFT COMPONENTS
        // =====================================================

        leftPanel.getChildren().addAll(
                brandBox,
                truckArea,
                features
        );

        // =====================================================
        // RIGHT PANEL
        // =====================================================

        VBox rightPanel =
                createRightPanel();

        // =====================================================
        // ADD BOTH PANELS
        // =====================================================

        mainBox.getChildren().addAll(
                leftPanel,
                rightPanel
        );

        // =====================================================
        // ROOT
        // =====================================================

        StackPane root =
                new StackPane(
                        mainBox
                );

        root.setPadding(
                new Insets(25)
        );

        root.setStyle(
                "-fx-background-color: #F8FAF9;"
        );

        // =====================================================
        // SCENE
        // =====================================================

        LoginScene =
                new Scene(
                        root,
                        screenWidth,
                        screenHeight
                );
        return LoginScene;

    }

    // =========================================================
    // TOP ECOLOAD LOGO
    // =========================================================

    private ImageView createTopLogo() {

        java.io.InputStream imageStream =
                getClass().getResourceAsStream(
                        "/assets/translogo.png"
                );

        if (imageStream == null) {

            System.out.println(
                    " /assets/translogo.png "
            );

            return new ImageView();
        }

        Image logoImage =
                new Image(
                        imageStream
                );

        ImageView logo =
                new ImageView(
                        logoImage
                );

        logo.setFitWidth(
                115
        );

        logo.setFitHeight(
                70
        );

        logo.setPreserveRatio(
                true
        );

        logo.setSmooth(
                true
        );

        return logo;
    }

    // =========================================================
    // LEFT SIDE TRUCK / IMAGE AREA
    // =========================================================

    private StackPane createTruckArea() {

        StackPane area =
                new StackPane();

        area.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // LOAD IMAGE
        // =====================================================

        java.io.InputStream imageStream =
                getClass().getResourceAsStream(
                        "/assets/images/welcomeback.jpeg"
                );

        // =====================================================
        // IMAGE NOT FOUND
        // =====================================================

        if (imageStream == null) {

            System.out.println(
                    "/assets/images/welcomeback.jpeg"
            );

            Label errorLabel =
                    new Label(
                            "Logo image not found"
                    );

            errorLabel.setTextFill(
                    Color.WHITE
            );

            errorLabel.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            16
                    )
            );

            area.getChildren().add(
                    errorLabel
            );

            return area;
        }

        // =====================================================
        // CREATE IMAGE
        // =====================================================

        Image image =
                new Image(
                        imageStream
                );

        ImageView truckImage =
                new ImageView(
                        image
                );

        truckImage.setFitWidth(
                300
        );

        truckImage.setFitHeight(
                200
        );

        truckImage.setPreserveRatio(
                true
        );

        truckImage.setSmooth(
                true
        );

        // =====================================================
        // ADD IMAGE
        // =====================================================

        area.getChildren().add(
                truckImage
        );

        // =====================================================
        // TAGLINE
        // =====================================================

        Label tagline =
                new Label(
                        "Smart Logistics. Better Future."
                );

        tagline.setTextFill(
                Color.WHITE
        );

        tagline.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        15
                )
        );

        tagline.setOpacity(
                0.90
        );

        // =====================================================
        // MOVE ONLY TAGLINE
        // =====================================================

        tagline.setTranslateY(
                -125
        );

        // =====================================================
        // ADD TAGLINE
        // =====================================================

        area.getChildren().add(
                tagline
        );

        return area;
    }

    // =========================================================
    // FEATURE
    // =========================================================

    private HBox createFeature(
            String icon,
            String title,
            String description
    ) {

        HBox feature =
                new HBox(15);

        feature.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // ICON BOX
        // =====================================================

        StackPane iconBox =
                new StackPane();

        iconBox.setPrefSize(
                52,
                52
        );

        iconBox.setMinSize(
                52,
                52
        );

        iconBox.setMaxSize(
                52,
                52
        );

        iconBox.setStyle(
                "-fx-background-color: #1B563F;" +
                "-fx-background-radius: 12;"
        );

        Label iconLabel =
                new Label(
                        icon
                );

        iconLabel.setFont(
                Font.font(22)
        );

        iconLabel.setTextFill(
                Color.web("#9AE67E")
        );

        iconBox.getChildren().add(
                iconLabel
        );

        // =====================================================
        // TEXT
        // =====================================================

        VBox textBox =
                new VBox(3);

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setTextFill(
                Color.WHITE
        );

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setTextFill(
                Color.WHITE
        );

        descriptionLabel.setOpacity(
                0.85
        );

        descriptionLabel.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        textBox.getChildren().addAll(
                titleLabel,
                descriptionLabel
        );

        feature.getChildren().addAll(
                iconBox,
                textBox
        );

        return feature;
    }

    // =========================================================
    // RIGHT PANEL
    // =========================================================

    private VBox createRightPanel() {

        VBox rightPanel =
                new VBox();

        rightPanel.setPrefWidth(
                770
        );

        rightPanel.setMinWidth(
                770
        );

        rightPanel.setMaxWidth(
                770
        );

        rightPanel.setPadding(
                new Insets(
                        65,
                        70,
                        45,
                        70
                )
        );

        rightPanel.setAlignment(
                Pos.TOP_CENTER
        );

        rightPanel.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 0 18 18 0;"
        );

        // =====================================================
        // ADMIN ICON
        // =====================================================

        Label adminIcon =
                new Label(
                        "♙"
                );

        adminIcon.setFont(
                Font.font(58)
        );

        adminIcon.setTextFill(
                Color.web("#3A9B55")
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        "Welcome Back"
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        36
                )
        );

        title.setTextFill(
                Color.web("#111827")
        );

        // =====================================================
        // SUBTITLE
        // =====================================================

        Label subtitle =
                new Label(
                        "Welcome back! Please login to continue"
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        18
                )
        );

        subtitle.setTextFill(
                Color.web("#667085")
        );

        // =====================================================
        // HEADING
        // =====================================================

        VBox heading =
                new VBox(7);

        heading.setAlignment(
                Pos.CENTER
        );

        heading.getChildren().addAll(
                adminIcon,
                title,
                subtitle
        );

        // =====================================================
        // USERNAME
        // =====================================================

        Label usernameLabel =
                createLabel(
                        "Username"
                );

        TextField username =
                new TextField();

        username.setPromptText(
                "Enter your username"
        );

        username.setPrefHeight(
                58
        );

        styleTextField(
                username
        );

        // =====================================================
        // PASSWORD HEADER
        // =====================================================

        HBox passwordHeader =
                new HBox();

        passwordHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        Label passwordLabel =
                createLabel(
                        "Password"
                );

        Region space =
                new Region();

        HBox.setHgrow(
                space,
                Priority.ALWAYS
        );

        Label forgotPassword =
                new Label(
                        "Forgot Password?"
                );

        forgotPassword.setTextFill(
                Color.web("#287A4A")
        );

        forgotPassword.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        passwordHeader.getChildren().addAll(
                passwordLabel,
                space,
                forgotPassword
        );

        // =====================================================
        // PASSWORD
        // =====================================================

        PasswordField password =
                new PasswordField();

        password.setPromptText(
                "Enter your password"
        );

        password.setPrefHeight(
                58
        );

        styleTextField(
                password
        );

        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        Button loginButton =
                new Button(
                        "🔒   Login"
                );

        loginButton.setPrefHeight(
                60
        );

        loginButton.setMaxWidth(
                Double.MAX_VALUE
        );

        loginButton.setTextFill(
                Color.WHITE
        );

        loginButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        loginButton.setStyle(
                "-fx-background-color: #2D9950;" +
                "-fx-background-radius: 9;" +
                "-fx-cursor: hand;"
        );

        // =====================================================
        // LOGIN BUTTON HOVER
        // =====================================================

        loginButton.setOnMouseEntered(
                e -> loginButton.setStyle(
                        "-fx-background-color: #247F42;" +
                        "-fx-background-radius: 9;" +
                        "-fx-cursor: hand;"
                )
        );

        loginButton.setOnMouseExited(
                e -> loginButton.setStyle(
                        "-fx-background-color: #2D9950;" +
                        "-fx-background-radius: 9;" +
                        "-fx-cursor: hand;"
                )
        );

        // =====================================================
        // SECURE ACCESS LINE
        // =====================================================

        Region line1 =
                new Region();

        line1.setPrefHeight(
                1
        );

        line1.setStyle(
                "-fx-background-color: #E5E7EB;"
        );

        Region line2 =
                new Region();

        line2.setPrefHeight(
                1
        );

        line2.setStyle(
                "-fx-background-color: #E5E7EB;"
        );

        Label secureAccess =
                new Label(
                        "Secure Access"
                );

        secureAccess.setTextFill(
                Color.web("#667085")
        );

        secureAccess.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        HBox secureBox =
                new HBox(15);

        secureBox.setAlignment(
                Pos.CENTER
        );

        HBox.setHgrow(
                line1,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                line2,
                Priority.ALWAYS
        );

        secureBox.getChildren().addAll(
                line1,
                secureAccess,
                line2
        );

        // =====================================================
        // AUTHORIZED PERSONNEL
        // =====================================================

        Label authorized =
                new Label(
                        "◇   Restricted to authorized personnel only"
                );

        authorized.setTextFill(
                Color.web("#667085")
        );

        authorized.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        // =====================================================
        // FORM
        // =====================================================

        VBox form =
                new VBox(10);

        form.setPrefWidth(
                600
        );

        form.setMaxWidth(
                600
        );

        form.getChildren().addAll(
                usernameLabel,
                username,

                passwordHeader,
                password,

                loginButton
        );

        // =====================================================
        // SPACING
        // =====================================================

        Region gap1 =
                new Region();

        gap1.setPrefHeight(
                35
        );

        Region gap2 =
                new Region();

        gap2.setPrefHeight(
                25
        );

        Region gap3 =
                new Region();

        gap3.setPrefHeight(
                30
        );

        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        rightPanel.getChildren().addAll(
                heading,
                gap1,
                form,
                gap2,
                secureBox,
                gap3,
                authorized
        );

        return rightPanel;
    }

    // =========================================================
    // CREATE LABEL
    // =========================================================

    private Label createLabel(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setTextFill(
                Color.web("#344054")
        );

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        return label;
    }

    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleTextField(
            TextField field
    ) {

        field.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #D0D5DD;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 0 16;" +
                "-fx-font-size: 16px;"
        );
    }

}