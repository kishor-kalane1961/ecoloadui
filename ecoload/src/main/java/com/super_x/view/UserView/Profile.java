package com.super_x.view.UserView;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Profile {

    private Scene profileScene;

    // EcoLoad colors
    private final String GREEN = "#0F7A3D";
    private final String DARK_GREEN = "#063B2E";
    private final String LIGHT_GREEN = "#F4FBF6";
    private final String BORDER = "#D8E8DE";

    public Profile() {
        createProfilePage();
    }

    public Scene getProfileScene() {
        return profileScene;
    }

    private void createProfilePage() {

        // =========================================================
        // MAIN ROOT
        // =========================================================

        BorderPane mainroot = new BorderPane();
        mainroot.setLeft(UserNavigation.createSidebar("Profile"));


        BorderPane mainContent = new BorderPane();
        mainContent.setTop(UserNavigation.createNavbar());

        VBox root = new VBox(18);
        mainContent.setCenter(root);
        root.setPadding(new Insets(25));
        root.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web(LIGHT_GREEN),
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        // =========================================================
        // PAGE HEADER
        // =========================================================

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);

        VBox titleBox = new VBox(4);

        Label title = new Label("Manage Profile");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setTextFill(Color.web(DARK_GREEN));

        Label subtitle = new Label(
                "View and manage your personal transporter information."
        );
        subtitle.setFont(Font.font("Arial", 15));
        subtitle.setTextFill(Color.web("#53645B"));

        titleBox.getChildren().addAll(title, subtitle);

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        Button cancelButton = new Button("Cancel");
        styleOutlineButton(cancelButton);

        Button saveButton = new Button("✓  Save Changes");
        styleGreenButton(saveButton);

        HBox actionButtons = new HBox(12);
        actionButtons.setAlignment(Pos.CENTER_RIGHT);
        actionButtons.getChildren().addAll(cancelButton, saveButton);

        header.getChildren().addAll(
                titleBox,
                headerSpacer,
                actionButtons
        );

        // =========================================================
        // PERSONAL INFORMATION
        // =========================================================

        VBox personalCard = createCard();

        Label personalTitle = sectionTitle("Personal Information");

        GridPane personalGrid = new GridPane();
        personalGrid.setHgap(28);
        personalGrid.setVgap(14);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);

        personalGrid.getColumnConstraints().addAll(col1, col2);

        TextField fullName = createTextField("Ramesh Kadam");
        TextField phone = createTextField("+91 98765 43210");
        TextField email = createTextField("ramesh@example.com");

        DatePicker dob = new DatePicker();
        dob.setPromptText("Date of birth");
        styleDatePicker(dob);

        TextArea address = new TextArea("Pune, Maharashtra");
        address.setPrefRowCount(2);
        address.setWrapText(true);
        styleTextArea(address);

        personalGrid.add(fieldBox("Full Name", fullName), 0, 0);
        personalGrid.add(fieldBox("Phone Number", phone), 1, 0);

        personalGrid.add(fieldBox("Email Address", email), 0, 1);
        personalGrid.add(fieldBox("Date of Birth", dob), 1, 1);

        personalGrid.add(fieldBox("Address", address), 0, 2, 2, 1);

        personalCard.getChildren().addAll(
                personalTitle,
                personalGrid
        );

        // =========================================================
        // PROFILE SUMMARY CARD
        // =========================================================

        VBox profileCard = createCard();
        profileCard.setPrefWidth(300);
        profileCard.setMaxWidth(300);

        CirclePane avatar = new CirclePane("RK");

        Label name = new Label("Ramesh Kadam");
        name.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        name.setTextFill(Color.web(DARK_GREEN));

        Label transporterType = new Label("PERSONAL TRANSPORTER");
        transporterType.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        transporterType.setTextFill(Color.web(GREEN));

        transporterType.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#DDF5E6"),
                                new CornerRadii(20),
                                Insets.EMPTY
                        )
                )
        );

        transporterType.setPadding(new Insets(7, 12, 7, 12));

        HBox badgeBox = new HBox(transporterType);
        badgeBox.setAlignment(Pos.CENTER);

        Separator separator = new Separator();

        Label status = infoRow("Account Status", "● Active");
        Label memberSince = infoRow("Member Since", "12 Jan 2024");
        Label updated = infoRow("Last Updated", "2 mins ago");

        profileCard.setAlignment(Pos.TOP_CENTER);

        profileCard.getChildren().addAll(
                avatar,
                name,
                badgeBox,
                separator,
                status,
                memberSince,
                updated
        );

        // =========================================================
        // TOP CONTENT
        // =========================================================

        HBox topContent = new HBox(22);
        topContent.setFillHeight(true);

        HBox.setHgrow(personalCard, Priority.ALWAYS);

        topContent.getChildren().addAll(
                profileCard,
                personalCard
        );

        // =========================================================
        // TRANSPORT SERVICES
        // =========================================================

        VBox transportCard = createCard();

        Label transportTitle = sectionTitle("Transport Services");

        GridPane transportGrid = new GridPane();
        transportGrid.setHgap(28);

        ColumnConstraints transportCol1 = new ColumnConstraints();
        transportCol1.setPercentWidth(50);

        ColumnConstraints transportCol2 = new ColumnConstraints();
        transportCol2.setPercentWidth(50);

        transportGrid.getColumnConstraints().addAll(
                transportCol1,
                transportCol2
        );

        // ComboBox<String> transporterType = new ComboBox<String>();
        // transporterType.getItems().add("Personal / Individual");
        // transporterType.setValue("Personal / Individual");
        // transporterType.setDisable(true);
        // transporterType.setMaxWidth(Double.MAX_VALUE);
        // styleComboBox(transporterType);

        ComboBox<String> primaryService = new ComboBox<>();
        primaryService.getItems().addAll(
                "House Shifting",
                "Local Transport",
                "Goods Transport",
                "Commercial Transport"
        );
        primaryService.setValue("House Shifting");
        primaryService.setMaxWidth(Double.MAX_VALUE);
        styleComboBox(primaryService);

        transportGrid.add(
                fieldBox("Transporter Type", transporterType),
                0, 0
        );

        transportGrid.add(
                fieldBox("Primary Service", primaryService),
                1, 0
        );

        transportCard.getChildren().addAll(
                transportTitle,
                transportGrid
        );

        // =========================================================
        // HOUSE-SHIFTING MATERIALS
        // =========================================================

        VBox materialsCard = createCard();

        Label materialsTitle = sectionTitle("House-Shifting Materials");

        Label materialsDescription = new Label(
                "Select the types of materials you are equipped to handle."
        );
        materialsDescription.setFont(Font.font("Arial", 14));
        materialsDescription.setTextFill(Color.web("#64756C"));

        HBox materialButtons = new HBox(10);
        materialButtons.setAlignment(Pos.CENTER_LEFT);

        ToggleButton furniture = materialButton("Furniture");
        ToggleButton appliances = materialButton("Appliances");
        ToggleButton electronics = materialButton("Electronics");
        ToggleButton boxes = materialButton("Boxes / Cartons");
        ToggleButton household = materialButton("Household Items");
        ToggleButton other = materialButton("Other");

        materialButtons.getChildren().addAll(
                furniture,
                appliances,
                electronics,
                boxes,
                household,
                other
        );

        materialsCard.getChildren().addAll(
                materialsTitle,
                materialsDescription,
                materialButtons
        );

        // =========================================================
        // ACCOUNT SECURITY
        // =========================================================

        VBox securityCard = createCard();

        Label securityTitle = sectionTitle("Account Security");

        GridPane securityGrid = new GridPane();
        securityGrid.setHgap(28);

        ColumnConstraints securityCol1 = new ColumnConstraints();
        securityCol1.setPercentWidth(50);

        ColumnConstraints securityCol2 = new ColumnConstraints();
        securityCol2.setPercentWidth(50);

        securityGrid.getColumnConstraints().addAll(
                securityCol1,
                securityCol2
        );

        PasswordField newPassword = new PasswordField();
        newPassword.setPromptText("New Password");
        stylePasswordField(newPassword);

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");
        stylePasswordField(confirmPassword);

        securityGrid.add(
                fieldBox("New Password", newPassword),
                0, 0
        );

        securityGrid.add(
                fieldBox("Confirm Password", confirmPassword),
                1, 0
        );

        securityCard.getChildren().addAll(
                securityTitle,
                securityGrid
        );

        // =========================================================
        // IDENTITY VERIFICATION
        // =========================================================

        VBox verificationCard = createCard();

        Label verificationTitle = sectionTitle("Identity Verification");

        HBox verificationRow = new HBox(15);
        verificationRow.setAlignment(Pos.CENTER_LEFT);

        Label documentIcon = new Label("▣");
        documentIcon.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        documentIcon.setTextFill(Color.web(GREEN));

        VBox documentInfo = new VBox(4);

        Label documentName = new Label("Identity Verification");
        documentName.setFont(
                Font.font("Arial", FontWeight.BOLD, 15)
        );

        Label documentStatus = new Label(
                "Verification completed"
        );
        documentStatus.setFont(Font.font("Arial", 13));
        documentStatus.setTextFill(Color.web("#64756C"));

        documentInfo.getChildren().addAll(
                documentName,
                documentStatus
        );

        Region verificationSpacer = new Region();
        HBox.setHgrow(
                verificationSpacer,
                Priority.ALWAYS
        );

        Label verified = new Label("✓ Verified");
        verified.setFont(
                Font.font("Arial", FontWeight.BOLD, 13)
        );
        verified.setTextFill(Color.web(GREEN));

        verified.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web("#DDF5E6"),
                                new CornerRadii(20),
                                Insets.EMPTY
                        )
                )
        );

        verified.setPadding(
                new Insets(8, 15, 8, 15)
        );

        verificationRow.getChildren().addAll(
                documentIcon,
                documentInfo,
                verificationSpacer,
                verified
        );

        verificationCard.getChildren().addAll(
                verificationTitle,
                verificationRow
        );

        // =========================================================
        // BOTTOM CONTENT
        // =========================================================

        HBox bottomContent = new HBox(22);

        HBox.setHgrow(securityCard, Priority.ALWAYS);
        HBox.setHgrow(verificationCard, Priority.ALWAYS);

        bottomContent.getChildren().addAll(
                securityCard,
                verificationCard
        );

        // =========================================================
        // BUTTON ACTIONS
        // =========================================================

        saveButton.setOnAction(event -> {

            Alert alert = new Alert(
                    Alert.AlertType.INFORMATION
            );

            alert.setTitle("EcoLoad");
            alert.setHeaderText("Profile Updated");
            alert.setContentText(
                    "Your profile changes have been saved successfully."
            );

            alert.showAndWait();
        });

        cancelButton.setOnAction(event -> {

            fullName.setText("Ramesh Kadam");
            phone.setText("+91 98765 43210");
            email.setText("ramesh@example.com");
            address.setText("Pune, Maharashtra");
            dob.setValue(null);

            primaryService.setValue("House Shifting");

            furniture.setSelected(false);
            appliances.setSelected(false);
            electronics.setSelected(false);
            boxes.setSelected(false);
            household.setSelected(false);
            other.setSelected(false);
        });

        // =========================================================
        // ADD EVERYTHING TO ROOT
        // =========================================================

        root.getChildren().addAll(
                header,
                topContent,
                //transportCard,
                //materialsCard,
                bottomContent
        );

        // =========================================================
        // SCROLL VIEW
        // =========================================================

        ScrollPane scrollPane = new ScrollPane(mainContent);
        mainroot.setCenter(scrollPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: " + LIGHT_GREEN + ";"
        );

        // =========================================================
        // SCENE
        // =========================================================

        profileScene = new Scene(
                mainroot,
                1536,
                750
        );

        profileScene.setFill(
                Color.web(LIGHT_GREEN)
        );
    }

    // =============================================================
    // CARD
    // =============================================================

    private VBox createCard() {

        VBox card = new VBox(15);

        card.setPadding(new Insets(25));

        card.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(18),
                                Insets.EMPTY
                        )
                )
        );

        card.setBorder(
                new Border(
                        new BorderStroke(
                                Color.web(BORDER),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(18),
                                new BorderWidths(1)
                        )
                )
        );

        return card;
    }

    // =============================================================
    // SECTION TITLE
    // =============================================================

    private Label sectionTitle(String text) {

        Label label = new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        label.setTextFill(
                Color.web("#101C16")
        );

        return label;
    }

    // =============================================================
    // FIELD BOX
    // =============================================================

    private VBox fieldBox(
            String labelText,
            Control control
    ) {

        VBox box = new VBox(7);

        Label label = new Label(labelText);

        label.setFont(
                Font.font("Arial", 13)
        );

        label.setTextFill(
                Color.web("#33443B")
        );

        box.getChildren().addAll(
                label,
                control
        );

        VBox.setVgrow(
                control,
                Priority.NEVER
        );

        return box;
    }

    // =============================================================
    // TEXT FIELD
    // =============================================================

    private TextField createTextField(String value) {

        TextField field = new TextField(value);

        field.setPrefHeight(48);
        field.setMaxWidth(Double.MAX_VALUE);

        field.setStyle(
                "-fx-background-color: #FAFCFB;" +
                "-fx-border-color: #D8E8DE;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-padding: 0 14;" +
                "-fx-font-size: 14px;"
        );

        return field;
    }

    // =============================================================
    // TEXT AREA
    // =============================================================

    private void styleTextArea(TextArea area) {

        area.setPrefHeight(75);
        area.setMaxWidth(Double.MAX_VALUE);

        area.setStyle(
                "-fx-background-color: #FAFCFB;" +
                "-fx-border-color: #D8E8DE;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-padding: 8;" +
                "-fx-font-size: 14px;"
        );
    }

    // =============================================================
    // DATE PICKER
    // =============================================================

    private void styleDatePicker(DatePicker picker) {

        picker.setPrefHeight(48);
        picker.setMaxWidth(Double.MAX_VALUE);

        picker.setStyle(
                "-fx-background-color: #FAFCFB;" +
                "-fx-border-color: #D8E8DE;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 14px;"
        );
    }

    // =============================================================
    // COMBO BOX
    // =============================================================

    private void styleComboBox(
            ComboBox<String> combo
    ) {

        combo.setPrefHeight(48);

        combo.setStyle(
                "-fx-background-color: #FAFCFB;" +
                "-fx-border-color: #D8E8DE;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 14px;"
        );
    }

    // =============================================================
    // PASSWORD
    // =============================================================

    private void stylePasswordField(
            PasswordField field
    ) {

        field.setPrefHeight(48);
        field.setMaxWidth(Double.MAX_VALUE);

        field.setStyle(
                "-fx-background-color: #FAFCFB;" +
                "-fx-border-color: #D8E8DE;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-padding: 0 14;" +
                "-fx-font-size: 14px;"
        );
    }

    // =============================================================
    // GREEN BUTTON
    // =============================================================

    private void styleGreenButton(Button button) {

        button.setPrefHeight(48);
        button.setPadding(
                new Insets(0, 22, 0, 22)
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        button.setTextFill(Color.WHITE);

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.web(GREEN),
                                new CornerRadii(24),
                                Insets.EMPTY
                        )
                )
        );
    }

    // =============================================================
    // OUTLINE BUTTON
    // =============================================================

    private void styleOutlineButton(Button button) {

        button.setPrefHeight(48);
        button.setPadding(
                new Insets(0, 22, 0, 22)
        );

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        14
                )
        );

        button.setTextFill(
                Color.web(DARK_GREEN)
        );

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(24),
                                Insets.EMPTY
                        )
                )
        );

        button.setBorder(
                new Border(
                        new BorderStroke(
                                Color.web("#A7C4B4"),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(24),
                                new BorderWidths(1)
                        )
                )
        );
    }

    // =============================================================
    // MATERIAL BUTTON
    // =============================================================

    private ToggleButton materialButton(
            String text
    ) {

        ToggleButton button =
                new ToggleButton(text);

        button.setPrefHeight(42);

        button.setPadding(
                new Insets(0, 17, 0, 17)
        );

        button.setFont(
                Font.font("Arial", 13)
        );

        button.setTextFill(
                Color.web("#52645A")
        );

        button.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.WHITE,
                                new CornerRadii(22),
                                Insets.EMPTY
                        )
                )
        );

        button.setBorder(
                new Border(
                        new BorderStroke(
                                Color.web("#D0DED6"),
                                BorderStrokeStyle.SOLID,
                                new CornerRadii(22),
                                new BorderWidths(1)
                        )
                )
        );

        button.selectedProperty().addListener(
                (obs, oldValue, selected) -> {

                    if (selected) {

                        button.setText("✓  " + text);

                        button.setTextFill(
                                Color.web(GREEN)
                        );

                        button.setBackground(
                                new Background(
                                        new BackgroundFill(
                                                Color.web("#DDF5E6"),
                                                new CornerRadii(22),
                                                Insets.EMPTY
                                        )
                                )
                        );

                        button.setBorder(
                                new Border(
                                        new BorderStroke(
                                                Color.web("#72C394"),
                                                BorderStrokeStyle.SOLID,
                                                new CornerRadii(22),
                                                new BorderWidths(1)
                                        )
                                )
                        );

                    } else {

                        button.setText(text);

                        button.setTextFill(
                                Color.web("#52645A")
                        );

                        button.setBackground(
                                new Background(
                                        new BackgroundFill(
                                                Color.WHITE,
                                                new CornerRadii(22),
                                                Insets.EMPTY
                                        )
                                )
                        );
                    }
                }
        );

        return button;
    }

    // =============================================================
    // PROFILE INFO ROW
    // =============================================================

    private Label infoRow(
            String labelText,
            String value
    ) {

        Label label = new Label(
                labelText + "                         " + value
        );

        label.setMaxWidth(Double.MAX_VALUE);

        label.setFont(
                Font.font("Arial", 13)
        );

        label.setTextFill(
                Color.web("#53645B")
        );

        label.setPadding(
                new Insets(8, 0, 8, 0)
        );

        return label;
    }

    // =============================================================
    // SIMPLE AVATAR
    // =============================================================

    private static class CirclePane extends StackPane {

        CirclePane(String initials) {

            setPrefSize(90, 90);
            setMinSize(90, 90);
            setMaxSize(90, 90);

            setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.web("#CDEFD9"),
                                    new CornerRadii(50),
                                    Insets.EMPTY
                            )
                    )
            );

            Label label = new Label(initials);

            label.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            28
                    )
            );

            label.setTextFill(
                    Color.web("#0F7A3D")
            );

            getChildren().add(label);
        }
    }
}