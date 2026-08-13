package com.super_x.view.UserView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import java.io.File;

import com.super_x.view.HomePage;
import com.super_x.view.Login;

public class UserRegistration {

    private final String GREEN = "#16A34A";

    private final String BG = "#F0FDF4";
    private final String INPUT = "#F8FAF9";
    private final String BORDER = "#28322b";

    private final String TEXT = "#050505";
    private final String MUTED = "#718078";

    // =========================================================
    // MAIN SCENE
    // =========================================================

    public Scene getTransporterRegistrationScene() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";");

        // =====================================================
        // MAIN CARD
        // =====================================================

        BorderPane card = new BorderPane();

        card.setMaxWidth(1450);
        card.setMaxHeight(700);

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 25, 0.15, 0, 5);");

        // =====================================================
        // LEFT SIDE
        // =====================================================

        VBox leftPanel = createLeftPanel();

        card.setLeft(leftPanel);

        // =====================================================
        // RIGHT SIDE
        // =====================================================

        BorderPane rightPanel = createRightPanel();

        card.setCenter(rightPanel);

        // =====================================================
        // CENTER CARD
        // =====================================================

        StackPane wrapper = new StackPane(card);

        wrapper.setPadding(
                new Insets(25));

        root.setCenter(wrapper);

        return new Scene(
                root,
                1536,
                750);
    }

    // =========================================================
    // LEFT PANEL
    // =========================================================

    private VBox createLeftPanel() {

        VBox left = new VBox();

        // -----------------------------------------------------
        // ECLOAD TRANSPORT IMAGE
        // -----------------------------------------------------

        Image image = new Image(
                getClass().getResourceAsStream(
                        "/assets/images/registration.png"));

        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(350);
        imageView.setFitHeight(700);

        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        // Image container
        StackPane imageBox = new StackPane();

        imageBox.setPrefWidth(280);
        imageBox.setPrefHeight(250);

        imageBox.setMaxWidth(280);
        imageBox.setMaxHeight(250);

        imageBox.setStyle(
                "-fx-background-color: transparent;");

        imageBox.getChildren().add(imageView);

        left.getChildren().add(imageBox);

        return left;
    }

    // =========================================================
    // RIGHT PANEL
    // =========================================================

    private BorderPane createRightPanel() {

        BorderPane right = new BorderPane();

        right.setPadding(
                new Insets(28, 35, 20, 35));

        right.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 0 20 20 0;");

        // =====================================================
        // TOP
        // =====================================================

        VBox header = new VBox(5);

        Label title = new Label("Create your Account");

        title.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        27));

        title.setTextFill(
                Color.web("#151917"));

        Label subtitle = new Label(
                "Create your business account with EcoLoad "
                        + "and access our premium logistics network.");

        subtitle.setFont(
                Font.font("System", 13));

        subtitle.setTextFill(
                Color.web(MUTED));

        header.getChildren().addAll(
                title,
                subtitle);

        right.setTop(header);

        // =====================================================
        // CENTER FORM
        // =====================================================

        GridPane form = createFormGrid();

        VBox center = new VBox(form);

        VBox.setVgrow(
                form,
                Priority.ALWAYS);

        center.setPadding(
                new Insets(16, 0, 5, 0));

        right.setCenter(center);

        // =====================================================
        // BOTTOM
        // =====================================================

        VBox bottom = createBottomSection();

        right.setBottom(bottom);

        return right;
    }

    // =========================================================
    // FORM GRID
    // =========================================================

    private GridPane createFormGrid() {

        GridPane grid = new GridPane();

        grid.setHgap(25);
        grid.setVgap(9);

        ColumnConstraints col1 = new ColumnConstraints();

        col1.setPercentWidth(50);

        ColumnConstraints col2 = new ColumnConstraints();

        col2.setPercentWidth(50);

        grid.getColumnConstraints().addAll(
                col1,
                col2);

        // =====================================================
        // ROW 1
        // =====================================================

        TextField company = createField(
                "▦",
                "e.g. Green Express Logistics");

        grid.add(
                fieldGroup(
                        "Company/Individual Name",
                        company),
                0,
                0);

        // PHONE
        TextField phone = createField(
                "☎",
                "98765 43210");

        TextField code = new TextField("+91");

        code.setPrefWidth(75);
        code.setPrefHeight(44);

        styleInput(code);

        HBox phoneBox = new HBox(8);

        HBox.setHgrow(
                phone,
                Priority.ALWAYS);

        phoneBox.getChildren().addAll(
                code,
                phone);

        grid.add(
                fieldGroup(
                        "Phone Number",
                        phoneBox),
                1,
                0);

        // =====================================================
        // ROW 2
        // =====================================================

        TextField email = createField(
                "✉",
                "contact@company.com");

        grid.add(
                fieldGroup(
                        "Email Address",
                        email),
                0,
                1);

        TextField gst = createField(
                "▣",
                "22AAAAA0000A1Z5");

        grid.add(
                fieldGroup(
                        "GST Number (Optional)",
                        gst),
                1,
                1);

        // =====================================================
        // ROW 3
        // =====================================================

        ComboBox<String> businessType = new ComboBox<>();

        businessType.getItems().addAll(
                "Transport Company",
                "Logistics Company",
                "Fleet Owner",
                "Individual Transporter",
                "Other");

        businessType.setPromptText(
                "Select business type");

        businessType.setPrefHeight(44);

        businessType.setMaxWidth(
                Double.MAX_VALUE);

        styleComboBox(businessType);

        grid.add(
                fieldGroup(
                        "Business Type",
                        businessType),
                0,
                2);

        TextField license = createField(
                "▣",
                "LIC-99002233");

        grid.add(
                fieldGroup(
                        "Business License Number",
                        license),
                1,
                2);

        // =====================================================
        // ROW 4
        // =====================================================

        TextArea address = new TextArea();

        address.setPromptText(
                "Full business address");

        address.setPrefHeight(70);

        address.setWrapText(true);

        styleTextArea(address);

        grid.add(
                fieldGroup(
                        "Company Address",
                        address),
                0,
                3);

        // RIGHT CITY STATE
        VBox location = new VBox(8);

        HBox cityState = new HBox(10);

        TextField city = createSimpleField("City");

        TextField state = createSimpleField("State");

        HBox.setHgrow(
                city,
                Priority.ALWAYS);

        HBox.setHgrow(
                state,
                Priority.ALWAYS);

        cityState.getChildren().addAll(
                city,
                state);

        TextField pin = createField(
                "⌖",
                "110001");

        location.getChildren().addAll(
                cityState,
                pin);

        grid.add(
                fieldGroup(
                        "City                         State",
                        location),
                1,
                3);

        // =====================================================
        // ROW 5
        // =====================================================

        VBox upload = createUploadBox();

        grid.add(
                fieldGroup(
                        "Business License / GST Certificate",
                        upload),
                0,
                4);

        // PASSWORD
        HBox passwords = new HBox(10);

        PasswordField password = createPasswordField(
                "Password");

        PasswordField confirm = createPasswordField(
                "Confirm Password");

        HBox.setHgrow(
                password,
                Priority.ALWAYS);

        HBox.setHgrow(
                confirm,
                Priority.ALWAYS);

        passwords.getChildren().addAll(
                password,
                confirm);

        grid.add(
                fieldGroup(
                        "Password                  Confirm Password",
                        passwords),
                1,
                4);

        return grid;
    }

    // =========================================================
    // BOTTOM
    // =========================================================

    private VBox createBottomSection() {

        VBox bottom = new VBox(8);

        // =====================================================
        // TERMS
        // =====================================================

        HBox terms = new HBox(8);

        terms.setAlignment(
                Pos.CENTER_LEFT);

        CheckBox check = new CheckBox();

        Label termsText = new Label(
                "I agree to the Terms & Conditions "
                        + "and Privacy Policy of EcoLoad Logistics.");

        termsText.setFont(
                Font.font("System", 12));

        termsText.setTextFill(
                Color.web("#555D59"));

        terms.getChildren().addAll(
                check,
                termsText);

        // =====================================================
        // BUTTON
        // =====================================================

        Button create = new Button("Create Account     →");

        create.setPrefHeight(47);

        create.setMaxWidth(
                Double.MAX_VALUE);

        create.setStyle(
                "-fx-background-color: linear-gradient(to right, #22C55E, #087A3E);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 11;" +
                        "-fx-cursor: hand;");
        create.setOnAction(e -> {

            // Check Terms & Conditions
            if (!check.isSelected()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Terms Required",
                        "Please accept the Terms & Conditions.");

                return;
            }

            // Open next page
            UserDashboard dashboard = new UserDashboard();
            HomePage.homeStage.setScene(dashboard.getTransporterDashboardScene());

        });

        // =====================================================
        // LOGIN
        // =====================================================

        HBox login = new HBox(5);

        login.setAlignment(
                Pos.CENTER);

        Label already = new Label(
                "Already have an account?");

        already.setFont(
                Font.font("System", 13));

        already.setTextFill(
                Color.web(MUTED));

        Hyperlink loginLink = new Hyperlink("Login here");
        loginLink.setOnAction(e -> {
            Login login1 = new Login();
            HomePage.homeStage.setScene(login1.getScene());
        });

        loginLink.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        13));

        loginLink.setTextFill(
                Color.web(GREEN));

        login.getChildren().addAll(
                already,
                loginLink);

        bottom.getChildren().addAll(
                terms,
                create,
                login);

        return bottom;
    }

    // =========================================================
    // FIELD GROUP
    // =========================================================

    private VBox fieldGroup(
            String labelText,
            javafx.scene.Node field) {

        Label label = new Label(labelText);

        label.setFont(
                Font.font(
                        "System",
                        12));

        label.setTextFill(
                Color.web(TEXT));

        VBox box = new VBox(4);

        box.getChildren().addAll(
                label,
                field);

        return box;
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private TextField createField(
            String icon,
            String prompt) {

        TextField field = new TextField();

        field.setPromptText(
                icon + "   " + prompt);

        field.setPrefHeight(44);

        styleInput(field);

        return field;
    }

    // =========================================================
    // SIMPLE FIELD
    // =========================================================

    private TextField createSimpleField(
            String prompt) {

        TextField field = new TextField();

        field.setPromptText(prompt);

        field.setPrefHeight(44);

        styleInput(field);

        return field;
    }

    // =========================================================
    // PASSWORD
    // =========================================================

    private PasswordField createPasswordField(
            String prompt) {

        PasswordField field = new PasswordField();

        field.setPromptText(prompt);

        field.setPrefHeight(44);

        styleInput(field);

        return field;
    }

    // =========================================================
    // INPUT STYLE
    // =========================================================

    private void styleInput(TextInputControl field) {

        field.setStyle(
                "-fx-background-color: #F8FAF9;" +
                        "-fx-border-color: #CDE8D5;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;" +
                        "-fx-padding: 0 12;" +
                        "-fx-font-size: 13px;" +
                        "-fx-text-fill: #17251D;");
    }

    // =========================================================
    // TEXT AREA
    // =========================================================

    private void styleTextArea(
            TextArea area) {

        area.setStyle(
                "-fx-control-inner-background: " + INPUT + ";" +
                        "-fx-background-color: " + INPUT + ";" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;" +
                        "-fx-padding: 8;" +
                        "-fx-font-size: 13px;");
    }

    // =========================================================
    // COMBOBOX
    // =========================================================

    private void styleComboBox(
            ComboBox<String> combo) {

        combo.setStyle(
                "-fx-background-color: " + INPUT + ";" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;" +
                        "-fx-font-size: 13px;");
    }

    // =========================================================
    // UPLOAD BOX
    // =========================================================

    private VBox createUploadBox() {

        VBox box = new VBox(2);

        box.setAlignment(
                Pos.CENTER);

        box.setPrefHeight(70);

        box.setStyle(
                "-fx-background-color: #F0FDF4;" +
                        "-fx-border-color: #9AD8AE;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-style: dashed;" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;" +
                        "-fx-cursor: hand;");

        Label icon = new Label("☁");

        icon.setFont(
                Font.font(23));

        icon.setTextFill(
                Color.web(GREEN));

        Label text = new Label(
                "Drag & drop or browse");

        text.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        12));

        Label formats = new Label(
                "PDF, PNG, JPG  •  MAX 5MB");

        formats.setFont(
                Font.font("System", 9));

        formats.setTextFill(
                Color.web("#999F9B"));

        box.getChildren().addAll(
                icon,
                text,
                formats);

        box.setOnMouseClicked(e -> {

            FileChooser chooser = new FileChooser();

            chooser.setTitle(
                    "Select Business Document");

            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "Documents",
                            "*.pdf",
                            "*.png",
                            "*.jpg",
                            "*.jpeg"));

            File file = chooser.showOpenDialog(
                    box.getScene().getWindow());

            if (file != null) {

                text.setText(
                        file.getName());

                text.setTextFill(
                        Color.web(GREEN));
            }
        });

        return box;
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}