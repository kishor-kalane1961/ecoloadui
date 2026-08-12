package com.super_x.view.UserView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import java.io.File;

public class Support {

    private static final String GREEN = "#087A3A";
    private static final String DARK_GREEN = "#064C38";
    private static final String LIGHT_GREEN = "#EAF8EF";
    private static final String PAGE_BG = "#F7F9F7";
    private static final String BORDER = "#DDE5DF";
    private static final String TEXT = "#26312D";
    private static final String MUTED = "#707875";

    private final String BG = "#e6f1e8";

    private ComboBox<String> issueType;

    private RadioButton low;
    private RadioButton medium;
    private RadioButton high;

    private TextField subjectField;
    private TextArea descriptionArea;

    private Label attachmentLabel;

    private Scene supportScene;


    // =========================================================
    // TRANSPORTER SUPPORT PAGE SCENE
    // =========================================================

    public Scene getSupportPageScene() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );


        // =====================================================
        // TRANSPORTER SIDEBAR
        // =====================================================

        root.setLeft(UserNavigation.createSidebar("Support")
        );


        // =====================================================
        // NAVBAR
        // =====================================================

        VBox mainContent = new VBox();

        HBox navbar = UserNavigation.createNavbar();


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox main = new VBox(10);

        main.setPadding(
                new Insets(14, 20, 10, 20)
        );

        main.setFillWidth(true);

        main.setStyle(
                "-fx-background-color: " + PAGE_BG + ";"
        );

        VBox.setVgrow(
                main,
                Priority.ALWAYS
        );


        mainContent.getChildren().addAll(
                navbar,
                main
        );

        root.setCenter(mainContent);


        // =====================================================
        // PAGE HEADER
        // =====================================================

        Label title =
                new Label("Transporter Support Center");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );

        title.setTextFill(
                Color.web(TEXT)
        );


        Label subtitle =
                new Label(
                        "Need help with your shipments, vehicles, or settlements? "
                        + "Our support team is ready to assist you."
                );

        subtitle.setFont(
                Font.font("Arial", 12)
        );

        subtitle.setTextFill(
                Color.web(MUTED)
        );


        VBox header =
                new VBox(
                        2,
                        title,
                        subtitle
                );


        // =====================================================
        // TWO COLUMN AREA
        // =====================================================

        HBox content =
                new HBox(16);


        VBox ticketCard =
                createTicketCard();


        VBox rightColumn =
                new VBox(10);


        VBox faqCard =
                createFAQCard();


        VBox contactCard =
                createContactCard();


        rightColumn.getChildren().addAll(
                faqCard,
                contactCard
        );


        HBox.setHgrow(
                ticketCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                rightColumn,
                Priority.ALWAYS
        );


        content.getChildren().addAll(
                ticketCard,
                rightColumn
        );


        VBox.setVgrow(
                content,
                Priority.ALWAYS
        );


        // =====================================================
        // INFORMATION BAR
        // =====================================================

        HBox informationBar =
                createInformationBar();


        // =====================================================
        // SECURITY BAR
        // =====================================================

        HBox securityBar =
                createSecurityBar();


        // =====================================================
        // ADD CONTENT
        // =====================================================

        main.getChildren().addAll(
                header,
                content,
                informationBar,
                securityBar
        );


        // =====================================================
        // SCENE
        // =====================================================

        supportScene =
                new Scene(
                        root,
                        1536,
                        750
                );


        return supportScene;
    }


    // =========================================================
    // CREATE SUPPORT TICKET
    // =========================================================

    private VBox createTicketCard() {

        VBox card =
                new VBox(9);

        card.setPadding(
                new Insets(18)
        );

        card.setMaxHeight(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 16;"
        );


        // =====================================================
        // CARD TITLE
        // =====================================================

        Label icon =
                new Label("▣");

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        icon.setTextFill(
                Color.web(GREEN)
        );


        StackPane iconBox =
                new StackPane(icon);

        iconBox.setPrefSize(
                34,
                34
        );

        iconBox.setMaxSize(
                34,
                34
        );

        iconBox.setStyle(
                "-fx-background-color: " + LIGHT_GREEN + ";" +
                "-fx-background-radius: 9;"
        );


        Label title =
                new Label("Create Support Ticket");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        title.setTextFill(
                Color.web(TEXT)
        );


        HBox cardTitle =
                new HBox(
                        10,
                        iconBox,
                        title
                );

        cardTitle.setAlignment(
                Pos.CENTER_LEFT
        );


        // =====================================================
        // ISSUE TYPE
        // =====================================================

        Label issueLabel =
                createFieldLabel("Issue Type");


        issueType =
                new ComboBox<>();


        issueType.getItems().addAll(

                "Technical Issue",

                "Shipment Issue",

                "Pickup Issue",

                "Delivery Issue",

                "Vehicle / Fleet Issue",

                "Payment & Settlement Issue",

                "Load Assignment Issue",

                "Document Issue",

                "Account Issue",

                "Other"
        );


        issueType.setValue(
                "Technical Issue"
        );

        issueType.setMaxWidth(
                Double.MAX_VALUE
        );

        issueType.setPrefHeight(
                38
        );


        styleControl(
                issueType
        );


        VBox issueBox =
                new VBox(
                        4,
                        issueLabel,
                        issueType
                );


        // =====================================================
        // PRIORITY
        // =====================================================

        Label priorityLabel =
                createFieldLabel("Priority");


        ToggleGroup priorityGroup =
                new ToggleGroup();


        low =
                createRadioButton(
                        "Low",
                        priorityGroup
                );


        medium =
                createRadioButton(
                        "Medium",
                        priorityGroup
                );


        high =
                createRadioButton(
                        "High",
                        priorityGroup
                );


        medium.setSelected(true);


        HBox priorityBox =
                new HBox(
                        10,
                        low,
                        medium,
                        high
                );


        priorityBox.setAlignment(
                Pos.CENTER_LEFT
        );


        VBox priorityContainer =
                new VBox(
                        6,
                        priorityLabel,
                        priorityBox
                );


        HBox issuePriority =
                new HBox(
                        16,
                        issueBox,
                        priorityContainer
                );


        HBox.setHgrow(
                issueBox,
                Priority.ALWAYS
        );


        HBox.setHgrow(
                priorityContainer,
                Priority.ALWAYS
        );


        // =====================================================
        // SUBJECT
        // =====================================================

        Label subjectLabel =
                createFieldLabel("Subject");


        subjectField =
                new TextField();


        subjectField.setPromptText(
                "Brief summary of the issue"
        );


        subjectField.setPrefHeight(
                38
        );


        styleControl(
                subjectField
        );


        VBox subjectBox =
                new VBox(
                        4,
                        subjectLabel,
                        subjectField
                );


        // =====================================================
        // DESCRIPTION
        // =====================================================

        Label descriptionLabel =
                createFieldLabel("Description");


        descriptionArea =
                new TextArea();


        descriptionArea.setPromptText(
                "Provide shipment ID, vehicle number, trip details, "
                + "and as much information as possible..."
        );


        descriptionArea.setWrapText(true);

        descriptionArea.setPrefHeight(
                70
        );

        descriptionArea.setMinHeight(
                70
        );

        descriptionArea.setMaxHeight(
                70
        );


        descriptionArea.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;" +
                "-fx-font-size: 13px;"
        );


        VBox descriptionBox =
                new VBox(
                        4,
                        descriptionLabel,
                        descriptionArea
                );


        // =====================================================
        // ATTACHMENT
        // =====================================================

        Label attachmentTitle =
                createFieldLabel("Attachments");


        Button uploadButton =
                new Button();


        uploadButton.setMaxWidth(
                Double.MAX_VALUE
        );


        uploadButton.setPrefHeight(
                125
        );


        uploadButton.setMinHeight(
                125
        );


        uploadButton.setMaxHeight(
                125
        );


        uploadButton.setCursor(
                javafx.scene.Cursor.HAND
        );


        attachmentLabel =
                new Label(
                        "☁   Drag & Drop files here\n" +
                        "PDF, PNG, JPG (Max 5MB)"
                );


        attachmentLabel.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );


        attachmentLabel.setAlignment(
                Pos.CENTER
        );


        attachmentLabel.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );


        attachmentLabel.setTextFill(
                Color.web(TEXT)
        );


        uploadButton.setGraphic(
                attachmentLabel
        );


        uploadButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #BFC8C1;" +
                "-fx-border-style: dashed;" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );


        uploadButton.setOnAction(
                e -> chooseAttachment()
        );


        VBox attachmentBox =
                new VBox(
                        4,
                        attachmentTitle,
                        uploadButton
                );


        // =====================================================
        // RESET BUTTON
        // =====================================================

        Button reset =
                new Button("Reset");


        reset.setPrefWidth(
                95
        );


        reset.setPrefHeight(
                38
        );


        reset.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );


        reset.setCursor(
                javafx.scene.Cursor.HAND
        );


        reset.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-border-color: #CDD5CF;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;"
        );


        reset.setOnAction(
                e -> resetForm()
        );


        // =====================================================
        // SUBMIT BUTTON
        // =====================================================

        Button submit =
                new Button("Submit Ticket");


        submit.setPrefHeight(
                38
        );


        submit.setMaxWidth(
                Double.MAX_VALUE
        );


        submit.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );


        submit.setTextFill(
                Color.WHITE
        );


        submit.setCursor(
                javafx.scene.Cursor.HAND
        );


        submit.setStyle(
                "-fx-background-color: " + GREEN + ";" +
                "-fx-background-radius: 9;"
        );


        submit.setOnAction(
                e -> submitTicket()
        );


        HBox.setHgrow(
                submit,
                Priority.ALWAYS
        );


        HBox buttons =
                new HBox(
                        12,
                        reset,
                        submit
                );


        // =====================================================
        // ADD CONTENT
        // =====================================================

        card.getChildren().addAll(

                cardTitle,

                issuePriority,

                subjectBox,

                descriptionBox,

                attachmentBox,

                buttons
        );


        return card;
    }


    // =========================================================
    // FAQ CARD
    // =========================================================

    private VBox createFAQCard() {

        VBox card =
                new VBox(15);


        card.setPadding(
                new Insets(16)
        );


        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 16;"
        );


        Label title =
                new Label(
                        "Frequently Asked Questions"
                );


        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );


        VBox faq1 =
                createFAQ(
                        "How do I accept a shipment?",
                        "Go to the Available Loads or Shipments section "
                        + "from the sidebar. Review the shipment details, "
                        + "pickup location, delivery location, and payment "
                        + "information before accepting the shipment."
                );


        VBox faq2 =
                createFAQ(
                        "How do I update shipment status?",
                        "Open your active shipment and select the relevant "
                        + "status option such as Picked Up, In Transit, "
                        + "or Delivered. Make sure the status is updated "
                        + "after completing each stage of the shipment."
                );


        VBox faq3 =
                createFAQ(
                        "When will I receive my payment?",
                        "Transporter payments are generally processed after "
                        + "successful shipment completion and verification. "
                        + "You can check the payment and settlement status "
                        + "from the Earnings or Payments section."
                );


        VBox faq4 =
                createFAQ(
                        "What should I do if I have a vehicle problem?",
                        "If your vehicle develops a problem during an active "
                        + "shipment, contact support immediately and provide "
                        + "your shipment ID, vehicle number, and current location."
                );


        card.getChildren().addAll(
                title,
                faq1,
                faq2,
                faq3,
                faq4
        );


        return card;
    }


    // =========================================================
    // FAQ ITEM
    // =========================================================

    private VBox createFAQ(
            String question,
            String answer
    ) {

        VBox box =
                new VBox();


        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;"
        );


        Button questionButton =
                new Button(
                        question + "   ⌄"
                );


        questionButton.setMaxWidth(
                Double.MAX_VALUE
        );


        questionButton.setAlignment(
                Pos.CENTER_LEFT
        );


        questionButton.setPrefHeight(
                36
        );


        questionButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );


        questionButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + TEXT + ";"
        );


        Label answerLabel =
                new Label(answer);


        answerLabel.setWrapText(true);


        answerLabel.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );


        answerLabel.setTextFill(
                Color.web(MUTED)
        );


        answerLabel.setPadding(
                new Insets(
                        2,
                        10,
                        10,
                        10
                )
        );


        VBox answerBox =
                new VBox(
                        answerLabel
                );


        // Start collapsed
        box.getChildren().add(
                questionButton
        );


        questionButton.setOnAction(
                e -> {

                    if (
                            box.getChildren()
                                    .contains(answerBox)
                    ) {

                        box.getChildren()
                                .remove(answerBox);


                        questionButton.setText(
                                question + "   ⌄"
                        );

                    } else {

                        box.getChildren()
                                .add(answerBox);


                        questionButton.setText(
                                question + "   ⌃"
                        );
                    }
                }
        );


        return box;
    }


    // =========================================================
    // CONTACT CARD
    // =========================================================

    private VBox createContactCard() {

        VBox card =
                new VBox(10);


        card.setPadding(
                new Insets(16)
        );


        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 16;"
        );


        Label title =
                new Label(
                        "Other Ways To Contact"
                );


        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );


        // =====================================================
        // PHONE
        // =====================================================

        Label phoneIcon =
                new Label("☎");


        phoneIcon.setFont(
                Font.font(
                        "Arial",
                        17
                )
        );


        phoneIcon.setTextFill(
                Color.web(GREEN)
        );


        StackPane phoneCircle =
                new StackPane(
                        phoneIcon
                );


        phoneCircle.setPrefSize(
                36,
                36
        );


        phoneCircle.setMaxSize(
                36,
                36
        );


        phoneCircle.setStyle(
                "-fx-background-color: " + LIGHT_GREEN + ";" +
                "-fx-background-radius: 50;"
        );


        Label phoneTitle =
                new Label(
                        "SUPPORT PHONE"
                );


        phoneTitle.setFont(
                Font.font(
                        "Arial",
                        10
                )
        );


        phoneTitle.setTextFill(
                Color.web(MUTED)
        );


        Label phone =
                new Label(
                        "1800-123-4567"
                );


        phone.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );


        phone.setTextFill(
                Color.web(TEXT)
        );


        VBox phoneText =
                new VBox(
                        2,
                        phoneTitle,
                        phone
                );


        HBox phoneRow =
                new HBox(
                        12,
                        phoneCircle,
                        phoneText
                );


        phoneRow.setAlignment(
                Pos.CENTER_LEFT
        );


        // =====================================================
        // EMAIL
        // =====================================================

        Label emailIcon =
                new Label("✉");


        emailIcon.setFont(
                Font.font(
                        "Arial",
                        17
                )
        );


        emailIcon.setTextFill(
                Color.web(GREEN)
        );


        StackPane emailCircle =
                new StackPane(
                        emailIcon
                );


        emailCircle.setPrefSize(
                36,
                36
        );


        emailCircle.setMaxSize(
                36,
                36
        );


        emailCircle.setStyle(
                "-fx-background-color: #EEF1F0;" +
                "-fx-background-radius: 50;"
        );


        Label emailTitle =
                new Label(
                        "EMAIL ADDRESS"
                );


        emailTitle.setFont(
                Font.font(
                        "Arial",
                        10
                )
        );


        emailTitle.setTextFill(
                Color.web(MUTED)
        );


        Label email =
                new Label(
                        "support@ecoload.com"
                );


        email.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );


        email.setTextFill(
                Color.web(TEXT)
        );


        VBox emailText =
                new VBox(
                        2,
                        emailTitle,
                        email
                );


        HBox emailRow =
                new HBox(
                        12,
                        emailCircle,
                        emailText
                );


        emailRow.setAlignment(
                Pos.CENTER_LEFT
        );


        Separator separator =
                new Separator();


        // =====================================================
        // LIVE CHAT
        // =====================================================

        Button chat =
                new Button(
                        "▣   Chat Bot"
                );


        chat.setMaxWidth(
                Double.MAX_VALUE
        );


        chat.setPrefHeight(
                38
        );


        chat.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );


        chat.setTextFill(
                Color.WHITE
        );


        chat.setCursor(
                javafx.scene.Cursor.HAND
        );


        chat.setStyle(
                "-fx-background-color: " + DARK_GREEN + ";" +
                "-fx-background-radius: 9;"
        );


        chat.setOnAction(
                e -> showAlert(
                        "ChatBot",
                        "ChatBot will be available"
                )
        );


        Label response =
                new Label(
                        "Response time: Usually within 2-4 hours"
                );


        response.setFont(
                Font.font(
                        "Arial",
                        FontPosture.ITALIC,
                        11
                )
        );


        response.setTextFill(
                Color.web(MUTED)
        );


        card.getChildren().addAll(

                title,

                phoneRow,

                emailRow,

                separator,

                chat,

                response
        );


        return card;
    }


    // =========================================================
    // INFORMATION BAR
    // =========================================================

    private HBox createInformationBar() {

        Label icon =
                new Label("ⓘ");


        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20
                )
        );


        icon.setTextFill(
                Color.web(GREEN)
        );


        Label text =
                new Label(
                        "Our support team usually replies within a few hours. "
                        + "Please have your Shipment ID, Vehicle Number, "
                        + "or Trip ID ready for faster resolution."
                );


        text.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );


        text.setTextFill(
                Color.web(TEXT)
        );


        HBox bar =
                new HBox(
                        12,
                        icon,
                        text
                );


        bar.setPadding(
                new Insets(
                        8,
                        14,
                        8,
                        14
                )
        );


        bar.setAlignment(
                Pos.CENTER_LEFT
        );


        bar.setStyle(
                "-fx-background-color: #F4FBF6;" +
                "-fx-border-color: #C8E2D0;" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;"
        );


        return bar;
    }


    // =========================================================
    // SECURITY BAR
    // =========================================================

    private HBox createSecurityBar() {

        Label icon =
                new Label("⬟");


        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );


        icon.setTextFill(
                Color.web("#3267C7")
        );


        Label text =
                new Label(
                        "Security Alert: Never share your password, OTP, "
                        + "banking PIN, or payment credentials with anyone, "
                        + "including EcoLoad support staff."
                );


        text.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );


        text.setTextFill(
                Color.web("#304D87")
        );


        HBox bar =
                new HBox(
                        12,
                        icon,
                        text
                );


        bar.setPadding(
                new Insets(
                        8,
                        14,
                        8,
                        14
                )
        );


        bar.setAlignment(
                Pos.CENTER_LEFT
        );


        bar.setStyle(
                "-fx-background-color: #F0F6FF;" +
                "-fx-border-color: #C7DAF5;" +
                "-fx-border-radius: 14;" +
                "-fx-background-radius: 14;"
        );


        return bar;
    }


    // =========================================================
    // FIELD LABEL
    // =========================================================

    private Label createFieldLabel(
            String text
    ) {

        Label label =
                new Label(text);


        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );


        label.setTextFill(
                Color.web(TEXT)
        );


        return label;
    }


    // =========================================================
    // RADIO BUTTON
    // =========================================================

    private RadioButton createRadioButton(
            String text,
            ToggleGroup group
    ) {

        RadioButton radio =
                new RadioButton(text);


        radio.setToggleGroup(
                group
        );


        radio.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );


        radio.setTextFill(
                Color.web(TEXT)
        );


        return radio;
    }


    // =========================================================
    // CONTROL STYLE
    // =========================================================

    private void styleControl(
            Control control
    ) {

        control.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-font-size: 14px;"
        );
    }


    // =========================================================
    // ATTACHMENT
    // =========================================================

    private void chooseAttachment() {

        FileChooser chooser =
                new FileChooser();


        chooser.setTitle(
                "Select Attachment"
        );


        chooser.getExtensionFilters()
                .add(
                        new FileChooser.ExtensionFilter(
                                "Supported Files",
                                "*.pdf",
                                "*.png",
                                "*.jpg",
                                "*.jpeg"
                        )
                );


        File file =
                chooser.showOpenDialog(
                        null
                );


        if (file != null) {

            long size =
                    file.length();


            // 5 MB limit
            if (
                    size >
                    5 * 1024 * 1024
            ) {

                showAlert(
                        "File Too Large",
                        "Please select a file smaller than 5 MB."
                );


                return;
            }


            attachmentLabel.setText(
                    "✓  " + file.getName()
            );
        }
    }


    // =========================================================
    // RESET
    // =========================================================

    private void resetForm() {

        issueType.setValue(
                "Technical Issue"
        );


        medium.setSelected(
                true
        );


        subjectField.clear();


        descriptionArea.clear();


        attachmentLabel.setText(
                "☁   Drag & Drop files here\n" +
                "PDF, PNG, JPG (Max 5MB)"
        );
    }


    // =========================================================
    // SUBMIT
    // =========================================================

    private void submitTicket() {

        if (
                subjectField.getText()
                        .trim()
                        .isEmpty()
        ) {

            showAlert(
                    "Missing Subject",
                    "Please enter the subject of your issue."
            );


            return;
        }


        if (
                descriptionArea.getText()
                        .trim()
                        .isEmpty()
        ) {

            showAlert(
                    "Missing Description",
                    "Please describe your issue."
            );


            return;
        }


        String priority;


        if (low.isSelected()) {

            priority = "Low";

        } else if (high.isSelected()) {

            priority = "High";

        } else {

            priority = "Medium";
        }


        showAlert(

                "Support Ticket Submitted",

                "Your transporter support ticket has been "
                + "submitted successfully.\n\n"

                + "Issue: "
                + issueType.getValue()
                + "\n"

                + "Priority: "
                + priority
                + "\n"

                + "Subject: "
                + subjectField.getText()
        );


        resetForm();
    }


    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );


        alert.setTitle(
                title
        );


        alert.setHeaderText(
                null
        );


        alert.setContentText(
                message
        );


        alert.showAndWait();
    }
}