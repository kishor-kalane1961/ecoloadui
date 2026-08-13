package com.super_x.view.UserView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TermsAndConditions {

    // =====================================================
    // COLORS
    // =====================================================

    private static final String GREEN = "#0B6B22";
    private static final String DARK_GREEN = "#075719";

    // =====================================================
    // CALLBACK
    // =====================================================

    private final Runnable onAgree;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public TermsAndConditions(
            Runnable onAgree
    ) {

        this.onAgree = onAgree;
    }

    // =====================================================
    // SHOW TERMS & CONDITIONS
    // =====================================================

    public void show() {

        Stage termsStage =
                new Stage();

        termsStage.initModality(
                Modality.APPLICATION_MODAL
        );

        termsStage.setTitle(
                "Terms & Conditions"
        );

        // =================================================
        // TITLE
        // =================================================

        Text title =
                new Text(
                        "Terms & Conditions"
                );

        title.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        24
                )
        );

        title.setFill(
                Color.web(GREEN)
        );

        // =================================================
        // SUBTITLE
        // =================================================

        Text subtitle =
                new Text(
                        "Please read and accept the following terms "
                                + "before posting your load."
                );

        subtitle.setFont(
                Font.font(
                        "System",
                        13
                )
        );

        subtitle.setFill(
                Color.web("#666666")
        );

        // =================================================
        // TERMS FLOW
        // =================================================

        TextFlow termsFlow =
                new TextFlow();

        termsFlow.setPadding(
                new Insets(18)
        );

        termsFlow.setLineSpacing(
                5
        );

        termsFlow.setStyle(
                "-fx-background-color: #F8FAF8;"
                        + "-fx-background-radius: 10;"
                        + "-fx-border-color: #DDE4DD;"
                        + "-fx-border-radius: 10;"
        );

        // =================================================
        // 1. ACCURATE INFORMATION
        // =================================================

        Text heading1 =
                new Text(
                        "1. 📦 Accurate Load Information\n"
                );

        heading1.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        heading1.setFill(
                Color.web(GREEN)
        );

        Text description1 =
                new Text(
                        "Provide correct information about the load type, "
                                + "quantity, weight, pickup location, destination, "
                                + "and delivery schedule.\n\n"
                );

        description1.setFont(
                Font.font(
                        "System",
                        13
                )
        );

        description1.setFill(
                Color.web("#333333")
        );

        // =================================================
        // 2. HANDLE WITH CARE
        // =================================================

        Text heading2 =
                new Text(
                        "2. ⚠️ Handle With Care\n"
                );

        heading2.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        heading2.setFill(
                Color.web(GREEN)
        );

        Text description2 =
                new Text(
                        "You must clearly mention if the shipment requires "
                                + "special handling. Drivers should handle the "
                                + "shipment carefully during loading, transportation, "
                                + "and unloading.\n\n"
                );

        description2.setFont(
                Font.font(
                        "System",
                        13
                )
        );

        description2.setFill(
                Color.web("#333333")
        );

        // =================================================
        // 3. FRAGILE ITEMS
        // =================================================

        Text heading3 =
                new Text(
                        "3. 🥂 Fragile Items\n"
                );

        heading3.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        heading3.setFill(
                Color.web(GREEN)
        );

        Text description3 =
                new Text(
                        "Clearly identify fragile items such as glass, "
                                + "electronics, furniture, or other breakable goods. "
                                + "Proper packaging is the sender's responsibility.\n\n"
                );

        description3.setFont(
                Font.font(
                        "System",
                        13
                )
        );

        description3.setFill(
                Color.web("#333333")
        );

        // =================================================
        // 4. PROPER PACKAGING
        // =================================================

        Text heading4 =
                new Text(
                        "4. 📋 Proper Packaging\n"
                );

        heading4.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        heading4.setFill(
                Color.web(GREEN)
        );

        Text description4 =
                new Text(
                        "All goods must be securely packed and protected "
                                + "before pickup. EcoLoad is not responsible for "
                                + "damage caused by inadequate packaging.\n\n"
                );

        description4.setFont(
                Font.font(
                        "System",
                        13
                )
        );

        description4.setFill(
                Color.web("#333333")
        );

        // =================================================
        // 5. PROHIBITED GOODS
        // =================================================

        Text heading5 =
                new Text(
                        "5. 🚫 Prohibited & Hazardous Goods\n"
                );

        heading5.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        heading5.setFill(
                Color.web(GREEN)
        );

        Text description5 =
                new Text(
                        "Illegal, dangerous, explosive, toxic, or otherwise "
                                + "prohibited items must not be posted through "
                                + "the platform.\n\n"
                );

        description5.setFont(
                Font.font(
                        "System",
                        13
                )
        );

        description5.setFill(
                Color.web("#333333")
        );

        // =================================================
        // 6. DRIVER ASSIGNMENT
        // =================================================

        Text heading6 =
                new Text(
                        "6. 🔍 Driver Assignment\n"
                );

        heading6.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        heading6.setFill(
                Color.web(GREEN)
        );

        Text description6 =
                new Text(
                        "After posting the load, eligible drivers may "
                                + "receive requests or offers for the shipment.\n\n"
                );

        description6.setFont(
                Font.font(
                        "System",
                        13
                )
        );

        description6.setFill(
                Color.web("#333333")
        );

        // =================================================
        // 7. CANCELLATION
        // =================================================

        Text heading7 =
                new Text(
                        "7. ❌ Cancellation\n"
                );

        heading7.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        heading7.setFill(
                Color.web(GREEN)
        );

        Text description7 =
                new Text(
                        "Cancellation after driver acceptance may be "
                                + "subject to applicable platform rules "
                                + "or charges.\n\n"
                );

        description7.setFont(
                Font.font(
                        "System",
                        13
                )
        );

        description7.setFill(
                Color.web("#333333")
        );

        // =================================================
        // 8. ECOSAFE GUARANTEE
        // =================================================

        Text heading8 =
                new Text(
                        "8. 🛡️ EcoSafe Guarantee\n"
                );

        heading8.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        heading8.setFill(
                Color.web(GREEN)
        );

        Text description8 =
                new Text(
                        "Payment protection may apply according to "
                                + "the EcoSafe delivery verification process.\n\n"
                );

        description8.setFont(
                Font.font(
                        "System",
                        13
                )
        );

        description8.setFill(
                Color.web("#333333")
        );

        // =================================================
        // 9. DELIVERY VERIFICATION
        // =================================================

        Text heading9 =
                new Text(
                        "9. 📸 Delivery Verification\n"
                );

        heading9.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14
                )
        );

        heading9.setFill(
                Color.web(GREEN)
        );

        Text description9 =
                new Text(
                        "Photos, signatures, OTP or other delivery "
                                + "confirmation may be required to verify "
                                + "successful delivery and resolve shipment disputes."
        );

        description9.setFont(
                Font.font(
                        "System",
                        13
                )
        );

        description9.setFill(
                Color.web("#333333")
        );

        // =================================================
        // ADD ALL TERMS
        // =================================================

        termsFlow.getChildren().addAll(

                heading1,
                description1,

                heading2,
                description2,

                heading3,
                description3,

                heading4,
                description4,

                heading5,
                description5,

                heading6,
                description6,

                heading7,
                description7,

                heading8,
                description8,

                heading9,
                description9
        );

        // =================================================
        // SCROLL PANE
        // =================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        termsFlow
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setPrefHeight(
                350
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background: white;"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        // =================================================
        // AGREEMENT CHECKBOX
        // =================================================

        CheckBox agreeCheckBox =
                new CheckBox(
                        "I have read and agree to the Terms & Conditions."
                );

        agreeCheckBox.setFont(
                Font.font(
                        "System",
                        13
                )
        );

        agreeCheckBox.setTextFill(
                Color.web("#333333")
        );

        // =================================================
        // CANCEL BUTTON
        // =================================================

        Button cancelButton =
                new Button(
                        "Cancel"
                );

        cancelButton.setPrefWidth(
                120
        );

        cancelButton.setPrefHeight(
                42
        );

        cancelButton.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: #C62828;"
                        + "-fx-border-width: 1;"
                        + "-fx-border-radius: 20;"
                        + "-fx-background-radius: 20;"
                        + "-fx-text-fill: #C62828;"
                        + "-fx-font-size: 13px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-cursor: hand;"
        );

        cancelButton.setOnAction(
                event ->
                        termsStage.close()
        );

        // =================================================
        // AGREE BUTTON
        // =================================================

        Button agreeButton =
                new Button(
                        "Agree & Continue"
                );

        agreeButton.setPrefWidth(
                160
        );

        agreeButton.setPrefHeight(
                42
        );

        agreeButton.setDisable(
                true
        );

        setAgreeButtonStyle(
                agreeButton
        );

        // =================================================
        // CHECKBOX LISTENER
        // =================================================

        agreeCheckBox.selectedProperty()
                .addListener(
                        (observable,
                         oldValue,
                         selected) -> {

                            agreeButton.setDisable(
                                    !selected
                            );
                        }
                );

        // =================================================
        // AGREE ACTION
        // =================================================

        agreeButton.setOnAction(
                event -> {

                    termsStage.close();

                    if (onAgree != null) {

                        onAgree.run();
                    }
                }
        );

        // =================================================
        // BUTTON BOX
        // =================================================

        HBox buttonBox =
                new HBox(
                        12
                );

        buttonBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        buttonBox.getChildren().addAll(
                cancelButton,
                agreeButton
        );

        // =================================================
        // POPUP
        // =================================================

        VBox popup =
                new VBox(
                        18
                );

        popup.setPadding(
                new Insets(
                        25
                )
        );

        popup.setStyle(
                "-fx-background-color: white;"
        );

        popup.getChildren().addAll(
                title,
                subtitle,
                scrollPane,
                agreeCheckBox,
                buttonBox
        );

        // =================================================
        // SCENE
        // =================================================

        Scene scene =
                new Scene(
                        popup,
                        620,
                        650
                );

        termsStage.setScene(
                scene
        );

        termsStage.setResizable(
                false
        );

        // =================================================
        // SHOW
        // =================================================

        termsStage.showAndWait();
    }

    // =====================================================
    // AGREE BUTTON STYLE
    // =====================================================

    private void setAgreeButtonStyle(
            Button button
    ) {

        button.setStyle(
                "-fx-background-color: " + GREEN + ";"
                        + "-fx-background-radius: 20;"
                        + "-fx-border-radius: 20;"
                        + "-fx-text-fill: white;"
                        + "-fx-font-size: 13px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(
                event -> {

                    if (!button.isDisabled()) {

                        button.setStyle(
                                "-fx-background-color: "
                                        + DARK_GREEN + ";"
                                        + "-fx-background-radius: 20;"
                                        + "-fx-border-radius: 20;"
                                        + "-fx-text-fill: white;"
                                        + "-fx-font-size: 13px;"
                                        + "-fx-font-weight: bold;"
                                        + "-fx-cursor: hand;"
                        );
                    }
                }
        );

        button.setOnMouseExited(
                event -> {

                    button.setStyle(
                            "-fx-background-color: "
                                    + GREEN + ";"
                                    + "-fx-background-radius: 20;"
                                    + "-fx-border-radius: 20;"
                                    + "-fx-text-fill: white;"
                                    + "-fx-font-size: 13px;"
                                    + "-fx-font-weight: bold;"
                                    + "-fx-cursor: hand;"
                    );
                }
        );
    }
}