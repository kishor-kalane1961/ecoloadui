package com.super_x.view.DriverView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Support {

    // =========================================================
    // COLORS
    // =========================================================

    private static final String GREEN = "#087A3A";
    private static final String DARK_GREEN = "#064C38";
    private static final String GREEN_HOVER = "#0A8A4A";
    private static final String LIGHT_GREEN = "#EAF8EF";

    private static final String PAGE_BG = "#F7F9F7";
    private static final String BORDER = "#DDE5DF";
    private static final String TEXT = "#26312D";
    private static final String MUTED = "#707875";

    private static final String BLUE = "#3267C7";

    // =========================================================
    // FORM CONTROLS
    // =========================================================

    private ComboBox<String> issueType;

    private RadioButton low;
    private RadioButton medium;
    private RadioButton high;

    private TextField subjectField;
    private TextArea descriptionArea;

    private Label attachmentLabel;

    private File selectedAttachment;

    // =========================================================
    // TICKETS
    // =========================================================

    private final List<SupportTicket> tickets = new ArrayList<>();

    private VBox ticketList;

    private Label ticketCountLabel;

    // =========================================================
    // DATE FORMAT
    // =========================================================

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy  hh:mm a");

    // =========================================================
    // SUPPORT PAGE
    // =========================================================

    public Scene getSupportPageScene() {

        initializeTickets();

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + PAGE_BG + ";");

        // =====================================================
        // FIXED SIDEBAR
        // =====================================================

        VBox sidebar =
                DriverNavigation.createSidebar("Support");

        root.setLeft(sidebar);

        // =====================================================
        // FIXED NAVBAR
        // =====================================================

        HBox navbar =
                DriverNavigation.createNavbar();

        // =====================================================
        // SUPPORT PAGE CONTENT
        // =====================================================

        VBox supportContent =
                new VBox(10);

        supportContent.setPadding(
                new Insets(
                        14,
                        20,
                        15,
                        20));

        supportContent.setFillWidth(true);

        supportContent.setStyle(
                "-fx-background-color: " +
                        PAGE_BG +
                        ";");

        // =====================================================
        // PAGE HEADER
        // =====================================================

        Label title =
                new Label("Support Center");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22));

        title.setTextFill(
                Color.web(TEXT));

        Label subtitle =
                new Label(
                        "Need help? Our support team is ready to assist you.");

        subtitle.setFont(
                Font.font(
                        "Arial",
                        12));

        subtitle.setTextFill(
                Color.web(MUTED));

        VBox header =
                new VBox(
                        2,
                        title,
                        subtitle);

        // =====================================================
        // TWO COLUMN CONTENT
        // =====================================================

        HBox content =
                new HBox(16);

        content.setFillHeight(false);

        // =====================================================
        // LEFT COLUMN
        // =====================================================

        VBox leftColumn =
                new VBox(12);

        VBox createTicketCard =
                createTicketCard();

        VBox previousTicketsCard =
                createPreviousTicketsCard();

        leftColumn.getChildren().addAll(
                createTicketCard,
                previousTicketsCard);

        // =====================================================
        // RIGHT COLUMN
        // =====================================================

        VBox rightColumn =
                new VBox(10);

        VBox faqCard =
                createFAQCard();

        VBox contactCard =
                createContactCard();

        rightColumn.getChildren().addAll(
                faqCard,
                contactCard);

        rightColumn.setPrefWidth(390);
        rightColumn.setMinWidth(350);
        rightColumn.setMaxWidth(410);

        HBox.setHgrow(
                leftColumn,
                Priority.ALWAYS);

        content.getChildren().addAll(
                leftColumn,
                rightColumn);

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
        // ADD SUPPORT CONTENT
        // =====================================================

        supportContent.getChildren().addAll(
                header,
                content,
                informationBar,
                securityBar);

        // =====================================================
        // FULL SUPPORT CONTENT SCROLL
        // =====================================================

        ScrollPane supportScroll =
                new ScrollPane(
                        supportContent);

        supportScroll.setFitToWidth(true);

        supportScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        supportScroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        supportScroll.setPannable(true);

        supportScroll.setStyle(
                "-fx-background-color: " +
                        PAGE_BG +
                        ";" +
                        "-fx-background-insets: 0;" +
                        "-fx-padding: 0;");

        supportScroll.skinProperty().addListener(
                (obs, oldSkin, newSkin) -> {

                    if (newSkin != null) {

                        Node viewport =
                                supportScroll.lookup(
                                        ".viewport");

                        if (viewport != null) {

                            viewport.setStyle(
                                    "-fx-background-color: " +
                                            PAGE_BG +
                                            ";");
                        }
                    }
                });

        // =====================================================
        // CENTER AREA
        // =====================================================

        VBox center =
                new VBox();

        center.setFillWidth(true);
        center.setMinHeight(0);

        center.getChildren().addAll(
                navbar,
                supportScroll);

        VBox.setVgrow(
                supportScroll,
                Priority.ALWAYS);

        root.setCenter(center);

        // =====================================================
        // SCENE
        // =====================================================

        return new Scene(
                root,
                1536,
                750);
    }

    // =========================================================
    // CREATE SUPPORT TICKET
    // =========================================================

    private VBox createTicketCard() {

        VBox card = createCard();

        // =====================================================
        // CARD HEADER
        // =====================================================

        Label icon = new Label("▣");

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18));

        icon.setTextFill(
                Color.web(GREEN));

        StackPane iconBox = new StackPane(icon);

        iconBox.setPrefSize(
                34,
                34);

        iconBox.setMaxSize(
                34,
                34);

        iconBox.setStyle(
                "-fx-background-color: " +
                        LIGHT_GREEN +
                        ";" +
                        "-fx-background-radius: 9;");

        Label title = new Label(
                "Create Support Ticket");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16));

        title.setTextFill(
                Color.web(TEXT));

        HBox cardTitle = new HBox(
                10,
                iconBox,
                title);

        cardTitle.setAlignment(
                Pos.CENTER_LEFT);

        // =====================================================
        // ISSUE TYPE
        // =====================================================

        Label issueLabel = createFieldLabel(
                "Issue Type");

        issueType = new ComboBox<>();

        issueType.getItems().addAll(
                "Technical Issue",
                "Payment Issue",
                "Trip Issue",
                "Vehicle Issue",
                "Load Issue",
                "Account Issue",
                "Document Issue",
                "Other");

        issueType.setValue(
                "Technical Issue");

        issueType.setMaxWidth(
                Double.MAX_VALUE);

        issueType.setPrefHeight(
                38);

        styleControl(issueType);

        VBox issueBox = new VBox(
                4,
                issueLabel,
                issueType);

        // =====================================================
        // PRIORITY
        // =====================================================

        Label priorityLabel = createFieldLabel(
                "Priority");

        ToggleGroup priorityGroup = new ToggleGroup();

        low = createRadioButton(
                "Low",
                priorityGroup);

        medium = createRadioButton(
                "Medium",
                priorityGroup);

        high = createRadioButton(
                "High",
                priorityGroup);

        medium.setSelected(true);

        HBox priorityBox = new HBox(
                10,
                low,
                medium,
                high);

        priorityBox.setAlignment(
                Pos.CENTER_LEFT);

        VBox priorityContainer = new VBox(
                6,
                priorityLabel,
                priorityBox);

        HBox.setHgrow(
                issueBox,
                Priority.ALWAYS);

        HBox.setHgrow(
                priorityContainer,
                Priority.ALWAYS);

        HBox issuePriority = new HBox(
                16,
                issueBox,
                priorityContainer);

        // =====================================================
        // SUBJECT
        // =====================================================

        Label subjectLabel = createFieldLabel(
                "Subject");

        subjectField = new TextField();

        subjectField.setPromptText(
                "Brief summary of the issue");

        subjectField.setPrefHeight(
                38);

        styleControl(subjectField);

        VBox subjectBox = new VBox(
                4,
                subjectLabel,
                subjectField);

        // =====================================================
        // DESCRIPTION
        // =====================================================

        Label descriptionLabel = createFieldLabel(
                "Description");

        descriptionArea = new TextArea();

        descriptionArea.setPromptText(
                "Provide as much detail as possible...");

        descriptionArea.setWrapText(true);

        descriptionArea.setPrefHeight(70);
        descriptionArea.setMinHeight(70);
        descriptionArea.setMaxHeight(70);

        descriptionArea.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 9;" +
                        "-fx-background-radius: 9;" +
                        "-fx-font-size: 13px;");

        VBox descriptionBox = new VBox(
                4,
                descriptionLabel,
                descriptionArea);

        // =====================================================
        // ATTACHMENT
        // =====================================================

        Label attachmentTitle = createFieldLabel(
                "Attachments");

        Button uploadButton = new Button();

        uploadButton.setMaxWidth(
                Double.MAX_VALUE);

        uploadButton.setPrefHeight(90);
        uploadButton.setMinHeight(90);
        uploadButton.setMaxHeight(90);

        uploadButton.setCursor(
                Cursor.HAND);

        attachmentLabel = new Label(
                "☁   Drag & Drop files here\n" +
                        "PDF, PNG, JPG (Max 5MB)");

        attachmentLabel.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER);

        attachmentLabel.setAlignment(
                Pos.CENTER);

        attachmentLabel.setFont(
                Font.font(
                        "Arial",
                        12));

        attachmentLabel.setTextFill(
                Color.web(TEXT));

        uploadButton.setGraphic(
                attachmentLabel);

        setUploadButtonStyle(
                uploadButton,
                false);

        uploadButton.setOnMouseEntered(
                e -> setUploadButtonStyle(
                        uploadButton,
                        true));

        uploadButton.setOnMouseExited(
                e -> setUploadButtonStyle(
                        uploadButton,
                        false));

        uploadButton.setOnAction(
                e -> chooseAttachment());

        VBox attachmentBox = new VBox(
                4,
                attachmentTitle,
                uploadButton);

        // =====================================================
        // RESET BUTTON
        // =====================================================

        Button reset = new Button("Reset");

        reset.setPrefWidth(95);
        reset.setPrefHeight(38);

        reset.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12));

        reset.setCursor(
                Cursor.HAND);

        setResetButtonStyle(
                reset,
                false);

        reset.setOnMouseEntered(
                e -> setResetButtonStyle(
                        reset,
                        true));

        reset.setOnMouseExited(
                e -> setResetButtonStyle(
                        reset,
                        false));

        reset.setOnAction(
                e -> resetForm());

        // =====================================================
        // SUBMIT BUTTON
        // =====================================================

        Button submit = new Button(
                "Submit Ticket");

        submit.setPrefHeight(38);

        submit.setMaxWidth(
                Double.MAX_VALUE);

        submit.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12));

        submit.setTextFill(
                Color.WHITE);

        submit.setCursor(
                Cursor.HAND);

        setSubmitButtonStyle(
                submit,
                false);

        submit.setOnMouseEntered(
                e -> setSubmitButtonStyle(
                        submit,
                        true));

        submit.setOnMouseExited(
                e -> setSubmitButtonStyle(
                        submit,
                        false));

        submit.setOnAction(
                e -> submitTicket());

        HBox buttons = new HBox(
                12,
                reset,
                submit);

        HBox.setHgrow(
                submit,
                Priority.ALWAYS);

        // =====================================================
        // ADD
        // =====================================================

        card.getChildren().addAll(
                cardTitle,
                issuePriority,
                subjectBox,
                descriptionBox,
                attachmentBox,
                buttons);

        return card;
    }

    // =========================================================
    // PREVIOUS TICKETS
    // =========================================================

    private VBox createPreviousTicketsCard() {

        VBox card =
                createCard();

        // =====================================================
        // HEADER
        // =====================================================

        HBox header =
                new HBox();

        VBox titleBox =
                new VBox(2);

        Label title =
                new Label(
                        "My Support Tickets");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16));

        title.setTextFill(
                Color.web(TEXT));

        Label subtitle =
                new Label(
                        "Track your previous requests and support actions");

        subtitle.setFont(
                Font.font(
                        "Arial",
                        11));

        subtitle.setTextFill(
                Color.web(MUTED));

        titleBox.getChildren().addAll(
                title,
                subtitle);

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        ticketCountLabel =
                new Label(
                        tickets.size() +
                                (tickets.size() == 1
                                        ? " Ticket"
                                        : " Tickets"));

        ticketCountLabel.setPadding(
                new Insets(
                        5,
                        10,
                        5,
                        10));

        ticketCountLabel.setStyle(
                "-fx-background-color: " +
                        LIGHT_GREEN +
                        ";" +
                        "-fx-background-radius: 20;");

        ticketCountLabel.setTextFill(
                Color.web(GREEN));

        ticketCountLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11));

        header.getChildren().addAll(
                titleBox,
                spacer,
                ticketCountLabel);

        // =====================================================
        // TICKET LIST
        // =====================================================

        ticketList =
                new VBox(7);

        refreshTicketList();

        // =====================================================
        // ADD DIRECTLY
        // The whole Support page scrolls.
        // No nested ticket ScrollPane is needed.
        // =====================================================

        card.getChildren().addAll(
                header,
                ticketList);

        return card;
    }

    // =========================================================
    // REFRESH TICKET LIST
    // =========================================================

    private void refreshTicketList() {

        if (ticketList == null) {
            return;
        }

        ticketList.getChildren().clear();

        for (SupportTicket ticket : tickets) {

            ticketList.getChildren().add(
                    createTicketRow(ticket));
        }

        if (ticketCountLabel != null) {

            ticketCountLabel.setText(
                    tickets.size() +
                            (tickets.size() == 1
                                    ? " Ticket"
                                    : " Tickets"));
        }
    }

    // =========================================================
    // TICKET ROW
    // =========================================================

    private HBox createTicketRow(
            SupportTicket ticket) {

        HBox row = new HBox(10);

        row.setPadding(
                new Insets(
                        7,
                        9,
                        7,
                        9));

        row.setAlignment(
                Pos.CENTER_LEFT);

        row.setCursor(
                Cursor.HAND);

        row.setStyle(
                ticketNormalStyle());

        // =====================================================
        // ICON
        // =====================================================

        Label icon = new Label(
                getTicketIcon(
                        ticket.issueType));

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15));

        icon.setTextFill(
                Color.web(GREEN));

        StackPane iconBox = new StackPane(icon);

        iconBox.setPrefSize(
                32,
                32);

        iconBox.setMaxSize(
                32,
                32);

        iconBox.setStyle(
                "-fx-background-color: " +
                        LIGHT_GREEN +
                        ";" +
                        "-fx-background-radius: 9;");

        // =====================================================
        // INFORMATION
        // =====================================================

        VBox info = new VBox(2);

        HBox subjectLine = new HBox(8);

        Label id = new Label(
                "#" + ticket.id);

        id.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11));

        id.setTextFill(
                Color.web(GREEN));

        Label subject = new Label(
                ticket.subject);

        subject.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13));

        subject.setTextFill(
                Color.web(TEXT));

        subjectLine.getChildren().addAll(
                id,
                subject);

        Label details = new Label(
                ticket.issueType +
                        "  •  " +
                        ticket.priority +
                        "  •  " +
                        ticket.created);

        details.setFont(
                Font.font(
                        "Arial",
                        10));

        details.setTextFill(
                Color.web(MUTED));

        info.getChildren().addAll(
                subjectLine,
                details);

        HBox.setHgrow(
                info,
                Priority.ALWAYS);

        // =====================================================
        // SPACER
        // =====================================================

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        // =====================================================
        // STATUS
        // =====================================================

        Label status = createStatusBadge(
                ticket.status);

        // =====================================================
        // VIEW DETAILS
        // =====================================================

        Button view = new Button(
                "View Details");

        view.setPrefHeight(30);

        view.setCursor(
                Cursor.HAND);

        setDetailsButtonStyle(
                view,
                false);

        view.setOnMouseEntered(
                e -> setDetailsButtonStyle(
                        view,
                        true));

        view.setOnMouseExited(
                e -> setDetailsButtonStyle(
                        view,
                        false));

        view.setOnAction(
                e -> showTicketDetails(ticket));

        row.getChildren().addAll(
                iconBox,
                info,
                spacer,
                status,
                view);

        // =====================================================
        // CLICK WHOLE ROW
        // =====================================================

        row.setOnMouseClicked(
                e -> {

                    if (!isInsideNode(
                            view,
                            e.getTarget())) {

                        showTicketDetails(ticket);
                    }
                });

        row.setOnMouseEntered(
                e -> row.setStyle(
                        ticketHoverStyle()));

        row.setOnMouseExited(
                e -> row.setStyle(
                        ticketNormalStyle()));

        return row;
    }

    // =========================================================
    // CHECK NODE
    // =========================================================

    private boolean isInsideNode(
            Node node,
            Object target) {

        if (!(target instanceof Node)) {
            return false;
        }

        Node current = (Node) target;

        while (current != null) {

            if (current == node) {
                return true;
            }

            current = current.getParent();
        }

        return false;
    }

    // =========================================================
    // TICKET DETAILS
    // =========================================================

    private void showTicketDetails(
            SupportTicket ticket) {

        Stage dialog = new Stage();

        dialog.initModality(
                Modality.APPLICATION_MODAL);

        dialog.setTitle(
                "Ticket #" + ticket.id);

        VBox content = new VBox(14);

        content.setPadding(
                new Insets(22));

        content.setStyle(
                "-fx-background-color: " +
                        PAGE_BG +
                        ";");

        // =====================================================
        // HEADER
        // =====================================================

        HBox header = new HBox();

        VBox titleBox = new VBox(3);

        Label ticketId = new Label(
                "Ticket #" +
                        ticket.id);

        ticketId.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20));

        ticketId.setTextFill(
                Color.web(TEXT));

        Label date = new Label(
                "Created " +
                        ticket.created);

        date.setFont(
                Font.font(
                        "Arial",
                        11));

        date.setTextFill(
                Color.web(MUTED));

        titleBox.getChildren().addAll(
                ticketId,
                date);

        Region headerSpacer = new Region();

        HBox.setHgrow(
                headerSpacer,
                Priority.ALWAYS);

        Label status = createStatusBadge(
                ticket.status);

        header.getChildren().addAll(
                titleBox,
                headerSpacer,
                status);

        // =====================================================
        // SUBJECT
        // =====================================================

        Label subject = new Label(
                ticket.subject);

        subject.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18));

        subject.setTextFill(
                Color.web(TEXT));

        // =====================================================
        // METADATA
        // =====================================================

        HBox metadata = new HBox(
                8,
                createInfoBadge(
                        ticket.issueType),
                createInfoBadge(
                        ticket.priority));

        // =====================================================
        // DESCRIPTION
        // =====================================================

        VBox descriptionCard = createCard();

        Label descriptionTitle = new Label(
                "Issue Description");

        descriptionTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14));

        Label description = new Label(
                ticket.description);

        description.setWrapText(true);

        description.setFont(
                Font.font(
                        "Arial",
                        12));

        description.setTextFill(
                Color.web(MUTED));

        descriptionCard.getChildren().addAll(
                descriptionTitle,
                description);

        // =====================================================
        // ATTACHMENT
        // =====================================================

        if (ticket.attachment != null &&
                !ticket.attachment.isEmpty()) {

            VBox attachmentCard = createCard();

            Label attachmentTitle = new Label(
                    "Attachment");

            attachmentTitle.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            14));

            Label attachment = new Label(
                    "📎 " +
                            ticket.attachment);

            attachment.setFont(
                    Font.font(
                            "Arial",
                            12));

            attachment.setTextFill(
                    Color.web(GREEN));

            attachmentCard.getChildren().addAll(
                    attachmentTitle,
                    attachment);

            content.getChildren().add(
                    attachmentCard);
        }

        // =====================================================
        // TIMELINE
        // =====================================================

        VBox timelineCard = createCard();

        Label timelineTitle = new Label(
                "Support Activity");

        timelineTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15));

        VBox timeline = new VBox(0);

        for (int i = 0; i < ticket.timeline.size(); i++) {

            TimelineEvent event = ticket.timeline.get(i);

            timeline.getChildren().add(
                    createTimelineEvent(
                            event,
                            i == ticket.timeline.size() - 1));
        }

        timelineCard.getChildren().addAll(
                timelineTitle,
                timeline);

        // =====================================================
        // ACTION TAKEN
        // =====================================================

        VBox actionCard = createCard();

        Label actionTitle = new Label(
                "Action Taken / Resolution");

        actionTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15));

        Label actionText = new Label(
                ticket.resolution);

        actionText.setWrapText(true);

        actionText.setFont(
                Font.font(
                        "Arial",
                        12));

        actionText.setTextFill(
                Color.web(MUTED));

        actionCard.getChildren().addAll(
                actionTitle,
                actionText);

        // =====================================================
        // ADD MAIN CONTENT
        // =====================================================

        content.getChildren().addAll(
                header,
                subject,
                metadata,
                descriptionCard,
                timelineCard,
                actionCard);

        // =====================================================
        // FEEDBACK
        // =====================================================

        if (ticket.status.equals("Resolved") ||
                ticket.status.equals("Closed")) {

            content.getChildren().add(
                    createFeedbackCard(ticket));
        }

        // =====================================================
        // CLOSE
        // =====================================================

        Button close = new Button(
                "Close");

        close.setPrefWidth(90);
        close.setPrefHeight(36);

        close.setCursor(
                Cursor.HAND);

        close.setOnAction(
                e -> dialog.close());

        HBox bottom = new HBox(close);

        bottom.setAlignment(
                Pos.CENTER_RIGHT);

        content.getChildren().add(
                bottom);

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scroll = new ScrollPane(content);

        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scroll.setStyle(
                "-fx-background-color: " +
                        PAGE_BG +
                        ";");

        Scene scene = new Scene(
                scroll,
                720,
                760);

        dialog.setScene(scene);

        dialog.showAndWait();
    }

    // =========================================================
    // TIMELINE EVENT
    // =========================================================

    private HBox createTimelineEvent(
            TimelineEvent event,
            boolean last) {

        HBox container = new HBox(12);

        container.setPadding(
                new Insets(
                        8,
                        0,
                        8,
                        0));

        VBox line = new VBox();

        line.setAlignment(
                Pos.TOP_CENTER);

        Circle circle = new Circle(
                6,
                Color.web(GREEN));

        Region vertical = new Region();

        vertical.setPrefWidth(2);

        vertical.setPrefHeight(
                last ? 0 : 45);

        vertical.setStyle(
                "-fx-background-color: #B8D7C7;");

        line.getChildren().addAll(
                circle,
                vertical);

        VBox info = new VBox(2);

        Label action = new Label(
                event.action);

        action.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13));

        action.setTextFill(
                Color.web(TEXT));

        Label time = new Label(
                event.time);

        time.setFont(
                Font.font(
                        "Arial",
                        10));

        time.setTextFill(
                Color.web(MUTED));

        Label description = new Label(
                event.description);

        description.setWrapText(true);

        description.setFont(
                Font.font(
                        "Arial",
                        11));

        description.setTextFill(
                Color.web(MUTED));

        info.getChildren().addAll(
                action,
                time,
                description);

        HBox.setHgrow(
                info,
                Priority.ALWAYS);

        container.getChildren().addAll(
                line,
                info);

        return container;
    }

    // =========================================================
    // FEEDBACK
    // =========================================================

    private VBox createFeedbackCard(
            SupportTicket ticket) {

        VBox card = createCard();

        Label title = new Label(
                ticket.rating > 0
                        ? "Your Support Feedback"
                        : "How was your support experience?");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14));

        HBox stars = new HBox(5);

        ToggleGroup group = new ToggleGroup();

        List<RadioButton> starButtons = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {

            final int rating = i;

            RadioButton star = new RadioButton("★");

            star.setToggleGroup(group);

            star.setUserData(rating);

            star.setFont(
                    Font.font(
                            "Arial",
                            25));

            star.setCursor(
                    Cursor.HAND);

            star.setTextFill(
                    Color.web(
                            rating <= ticket.rating
                                    ? "#F4B400"
                                    : "#D1D8D5"));

            star.setOnAction(
                    e -> {

                        for (RadioButton s : starButtons) {

                            int value = (int) s.getUserData();

                            s.setTextFill(
                                    Color.web(
                                            value <= rating
                                                    ? "#F4B400"
                                                    : "#D1D8D5"));
                        }
                    });

            starButtons.add(star);

            stars.getChildren().add(star);
        }

        TextArea comment = new TextArea();

        comment.setPromptText(
                "Tell us about your experience...");

        comment.setPrefRowCount(2);

        comment.setWrapText(true);

        comment.setText(
                ticket.feedback == null
                        ? ""
                        : ticket.feedback);

        comment.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;");

        Button submit = new Button(
                ticket.rating > 0
                        ? "Update Feedback"
                        : "Submit Feedback");

        submit.setPrefHeight(35);

        submit.setCursor(
                Cursor.HAND);

        submit.setTextFill(
                Color.WHITE);

        submit.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11));

        submit.setStyle(
                "-fx-background-color: " +
                        GREEN +
                        ";" +
                        "-fx-background-radius: 8;");

        submit.setOnAction(
                e -> {

                    if (group.getSelectedToggle() == null &&
                            ticket.rating == 0) {

                        showAlert(
                                "Feedback Required",
                                "Please select a rating before submitting your feedback.");

                        return;
                    }

                    if (group.getSelectedToggle() != null) {

                        ticket.rating = (int) group
                                .getSelectedToggle()
                                .getUserData();
                    }

                    ticket.feedback = comment.getText()
                            .trim();

                    showAlert(
                            "Feedback Submitted",
                            "Thank you for your feedback.");
                });

        card.getChildren().addAll(
                title,
                stars,
                comment,
                submit);

        return card;
    }

    // =========================================================
    // FAQ
    // =========================================================

    private VBox createFAQCard() {

        VBox card = createCard();

        Label title = new Label(
                "Frequently Asked Questions");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16));

        VBox faq1 = createFAQ(
                "How do I accept a load?",
                "Open the Available Loads section, select the load you want, review its details and select Accept Load.");

        VBox faq2 = createFAQ(
                "How do I cancel an active trip?",
                "Open Active Trip and select the trip you want to cancel. Follow the cancellation instructions shown by the application.");

        VBox faq3 = createFAQ(
                "How long does payout take?",
                "Payout processing depends on successful trip completion and payment verification. You can check the payment status from the relevant trip information.");

        card.getChildren().addAll(
                title,
                faq1,
                faq2,
                faq3);

        return card;
    }

    // =========================================================
    // FAQ ITEM
    // =========================================================

    private VBox createFAQ(
            String question,
            String answer) {

        VBox box = new VBox();

        box.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: " + BORDER + ";" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;");

        Button questionButton = new Button(
                question + "   ⌄");

        questionButton.setMaxWidth(
                Double.MAX_VALUE);

        questionButton.setAlignment(
                Pos.CENTER_LEFT);

        questionButton.setPrefHeight(36);

        questionButton.setCursor(
                Cursor.HAND);

        questionButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12));

        questionButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: " +
                        TEXT +
                        ";");

        Label answerLabel = new Label(answer);

        answerLabel.setWrapText(true);

        answerLabel.setFont(
                Font.font(
                        "Arial",
                        11));

        answerLabel.setTextFill(
                Color.web(MUTED));

        answerLabel.setPadding(
                new Insets(
                        2,
                        10,
                        10,
                        10));

        VBox answerBox = new VBox(
                answerLabel);

        questionButton.setOnAction(
                e -> {

                    if (box.getChildren()
                            .contains(answerBox)) {

                        box.getChildren()
                                .remove(answerBox);

                        questionButton.setText(
                                question + "   ⌄");

                    } else {

                        box.getChildren()
                                .add(answerBox);

                        questionButton.setText(
                                question + "   ⌃");
                    }
                });

        box.getChildren().add(
                questionButton);

        return box;
    }

    // =========================================================
    // CONTACT CARD
    // =========================================================

    private VBox createContactCard() {

        VBox card = createCard();

        Label title = new Label(
                "Other Ways To Contact");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16));

        // =====================================================
        // PHONE
        // =====================================================

        Label phoneIcon = new Label("☎");

        phoneIcon.setFont(
                Font.font(
                        "Arial",
                        17));

        phoneIcon.setTextFill(
                Color.web(GREEN));

        StackPane phoneCircle = new StackPane(
                phoneIcon);

        phoneCircle.setPrefSize(
                36,
                36);

        phoneCircle.setMaxSize(
                36,
                36);

        phoneCircle.setStyle(
                "-fx-background-color: " +
                        LIGHT_GREEN +
                        ";" +
                        "-fx-background-radius: 50;");

        Label phoneTitle = new Label(
                "SUPPORT PHONE");

        phoneTitle.setFont(
                Font.font(
                        "Arial",
                        10));

        phoneTitle.setTextFill(
                Color.web(MUTED));

        Label phone = new Label(
                "1800-123-4567");

        phone.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14));

        VBox phoneText = new VBox(
                2,
                phoneTitle,
                phone);

        HBox phoneRow = new HBox(
                12,
                phoneCircle,
                phoneText);

        phoneRow.setAlignment(
                Pos.CENTER_LEFT);

        // =====================================================
        // EMAIL
        // =====================================================

        Label emailIcon = new Label("✉");

        emailIcon.setFont(
                Font.font(
                        "Arial",
                        17));

        StackPane emailCircle = new StackPane(
                emailIcon);

        emailCircle.setPrefSize(
                36,
                36);

        emailCircle.setMaxSize(
                36,
                36);

        emailCircle.setStyle(
                "-fx-background-color: #EEF1F0;" +
                        "-fx-background-radius: 50;");

        Label emailTitle = new Label(
                "EMAIL ADDRESS");

        emailTitle.setFont(
                Font.font(
                        "Arial",
                        10));

        emailTitle.setTextFill(
                Color.web(MUTED));

        Label email = new Label(
                "support@ecoload.com");

        email.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13));

        VBox emailText = new VBox(
                2,
                emailTitle,
                email);

        HBox emailRow = new HBox(
                12,
                emailCircle,
                emailText);

        emailRow.setAlignment(
                Pos.CENTER_LEFT);

        Separator separator = new Separator();

        // =====================================================
        // CHAT BOT
        // =====================================================

        Button chat = new Button(
                "▣  Chat Bot");

        chat.setMaxWidth(
                Double.MAX_VALUE);

        chat.setPrefHeight(38);

        chat.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12));

        chat.setTextFill(
                Color.WHITE);

        chat.setCursor(
                Cursor.HAND);

        chat.setStyle(
                "-fx-background-color: " +
                        DARK_GREEN +
                        ";" +
                        "-fx-background-radius: 9;");

        chat.setOnMouseEntered(
                e -> chat.setStyle(
                        "-fx-background-color: " +
                                GREEN +
                                ";" +
                                "-fx-background-radius: 9;"));

        chat.setOnMouseExited(
                e -> chat.setStyle(
                        "-fx-background-color: " +
                                DARK_GREEN +
                                ";" +
                                "-fx-background-radius: 9;"));

        chat.setOnAction(
                e -> showAlert(
                        "ChatBot",
                        "ChatBot will be available shortly."));

        Label response = new Label(
                "Response time: Usually within 2-4 hours");

        response.setFont(
                Font.font(
                        "Arial",
                        FontPosture.ITALIC,
                        11));

        response.setTextFill(
                Color.web(MUTED));

        card.getChildren().addAll(
                title,
                phoneRow,
                emailRow,
                separator,
                chat,
                response);

        return card;
    }

    // =========================================================
    // INFORMATION BAR
    // =========================================================

    private HBox createInformationBar() {

        Label icon = new Label("ⓘ");

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19));

        icon.setTextFill(
                Color.web(GREEN));

        Label text = new Label(
                "Our support team usually replies within a few hours. " +
                        "Please have your Trip ID ready for faster resolution.");

        text.setFont(
                Font.font(
                        "Arial",
                        12));

        text.setTextFill(
                Color.web(TEXT));

        HBox bar = new HBox(
                12,
                icon,
                text);

        bar.setPadding(
                new Insets(
                        8,
                        14,
                        8,
                        14));

        bar.setAlignment(
                Pos.CENTER_LEFT);

        bar.setStyle(
                "-fx-background-color: #F4FBF6;" +
                        "-fx-border-color: #C8E2D0;" +
                        "-fx-border-radius: 14;" +
                        "-fx-background-radius: 14;");

        return bar;
    }

    // =========================================================
    // SECURITY BAR
    // =========================================================

    private HBox createSecurityBar() {

        Label icon = new Label("⬟");

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18));

        icon.setTextFill(
                Color.web(BLUE));

        Label text = new Label(
                "Security Alert: Never share your password or OTP " +
                        "with anyone, including EcoLoad support staff.");

        text.setFont(
                Font.font(
                        "Arial",
                        13));

        text.setTextFill(
                Color.web("#304D87"));

        HBox bar = new HBox(
                12,
                icon,
                text);

        bar.setPadding(
                new Insets(
                        8,
                        14,
                        8,
                        14));

        bar.setAlignment(
                Pos.CENTER_LEFT);

        bar.setStyle(
                "-fx-background-color: #F0F6FF;" +
                        "-fx-border-color: #C7DAF5;" +
                        "-fx-border-radius: 14;" +
                        "-fx-background-radius: 14;");

        return bar;
    }

    // =========================================================
    // SUBMIT TICKET
    // =========================================================

    private void submitTicket() {

        String subject = subjectField.getText()
                .trim();

        String description = descriptionArea.getText()
                .trim();

        // =====================================================
        // VALIDATION
        // =====================================================

        if (subject.isEmpty()) {

            showAlert(
                    "Missing Subject",
                    "Please enter the subject of your issue.");

            subjectField.requestFocus();

            return;
        }

        if (description.isEmpty()) {

            showAlert(
                    "Missing Description",
                    "Please describe your issue.");

            descriptionArea.requestFocus();

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

        // =====================================================
        // CREATE TICKET
        // =====================================================

        String ticketId = generateTicketId();

        String now = LocalDateTime.now()
                .format(dateFormatter);

        SupportTicket ticket = new SupportTicket();

        ticket.id = ticketId;

        ticket.issueType = issueType.getValue();

        ticket.priority = priority;

        ticket.subject = subject;

        ticket.description = description;

        ticket.status = "Open";

        ticket.created = now;

        ticket.resolution = "Your request has been received. " +
                "Our support team will review the issue and take the necessary action.";

        // =====================================================
        // FIRST TIMELINE EVENT
        // =====================================================

        ticket.timeline.add(
                new TimelineEvent(
                        "Ticket Created",
                        now,
                        "Your support ticket was submitted successfully."));

        // =====================================================
        // ATTACHMENT
        // =====================================================

        if (selectedAttachment != null) {

            ticket.attachment = selectedAttachment.getName();
        }

        // =====================================================
        // ADD TO TOP
        // =====================================================

        tickets.add(
                0,
                ticket);

        refreshTicketList();

        resetForm();

        showAlert(
                "Support Ticket Submitted",
                "Your support ticket has been submitted successfully.\n\n" +
                        "Ticket ID: #" +
                        ticketId);
    }

    // =========================================================
    // TICKET ID
    // =========================================================

    private String generateTicketId() {

        int number = 1000 +
                tickets.size() +
                1;

        return "TK-" + number;
    }

    // =========================================================
    // ATTACHMENT
    // =========================================================

    private void chooseAttachment() {

        FileChooser chooser = new FileChooser();

        chooser.setTitle(
                "Select Attachment");

        chooser.getExtensionFilters()
                .add(
                        new FileChooser.ExtensionFilter(
                                "Supported Files",
                                "*.pdf",
                                "*.png",
                                "*.jpg",
                                "*.jpeg"));

        File file = chooser.showOpenDialog(null);

        if (file == null) {
            return;
        }

        long size = file.length();

        // 5 MB
        if (size > 5 * 1024 * 1024) {

            showAlert(
                    "File Too Large",
                    "Please select a file smaller than 5 MB.");

            return;
        }

        selectedAttachment = file;

        attachmentLabel.setText(
                "✓  " +
                        file.getName());
    }

    // =========================================================
    // RESET FORM
    // =========================================================

    private void resetForm() {

        issueType.setValue(
                "Technical Issue");

        medium.setSelected(true);

        subjectField.clear();

        descriptionArea.clear();

        selectedAttachment = null;

        attachmentLabel.setText(
                "☁   Drag & Drop files here\n" +
                        "PDF, PNG, JPG (Max 5MB)");
    }

    // =========================================================
    // SAMPLE / PREVIOUS TICKETS
    // =========================================================

    private void initializeTickets() {

        if (!tickets.isEmpty()) {
            return;
        }

        // =====================================================
        // TICKET 1
        // =====================================================

        SupportTicket ticket1 = new SupportTicket();

        ticket1.id = "TK-1042";

        ticket1.issueType = "Technical Issue";

        ticket1.priority = "High";

        ticket1.subject = "App not loading data";

        ticket1.description = "The application is not displaying the latest load information.";

        ticket1.status = "Resolved";

        ticket1.created = "Aug 12, 2026 10:32 AM";

        ticket1.resolution = "The application data was refreshed and the issue was verified successfully.";

        ticket1.timeline.add(
                new TimelineEvent(
                        "Ticket Created",
                        "Aug 12, 2026 10:32 AM",
                        "Support request submitted by driver."));

        ticket1.timeline.add(
                new TimelineEvent(
                        "Ticket Reviewed",
                        "Aug 12, 2026 10:48 AM",
                        "Support team reviewed the reported application issue."));

        ticket1.timeline.add(
                new TimelineEvent(
                        "Action Taken",
                        "Aug 12, 2026 11:45 AM",
                        "Application data synchronization was refreshed."));

        ticket1.timeline.add(
                new TimelineEvent(
                        "Resolved",
                        "Aug 12, 2026 12:30 PM",
                        "The issue was verified and the ticket was marked as resolved."));

        // =====================================================
        // TICKET 2
        // =====================================================

        SupportTicket ticket2 = new SupportTicket();

        ticket2.id = "TK-1038";

        ticket2.issueType = "Load Issue";

        ticket2.priority = "Medium";

        ticket2.subject = "Unable to accept load";

        ticket2.description = "I am unable to accept an available load from the Available Loads section.";

        ticket2.status = "In Progress";

        ticket2.created = "Aug 11, 2026 09:15 AM";

        ticket2.resolution = "The support team is checking the load acceptance process and will update the ticket after verification.";

        ticket2.timeline.add(
                new TimelineEvent(
                        "Ticket Created",
                        "Aug 11, 2026 09:15 AM",
                        "Driver reported an issue accepting a load."));

        ticket2.timeline.add(
                new TimelineEvent(
                        "Ticket Reviewed",
                        "Aug 11, 2026 10:05 AM",
                        "Support team reviewed the reported load details."));

        ticket2.timeline.add(
                new TimelineEvent(
                        "Action Taken",
                        "Aug 11, 2026 04:20 PM",
                        "The issue was forwarded to the load operations team."));

        // =====================================================
        // TICKET 3
        // =====================================================

        SupportTicket ticket3 = new SupportTicket();

        ticket3.id = "TK-1031";

        ticket3.issueType = "Payment Issue";

        ticket3.priority = "Medium";

        ticket3.subject = "Payment not received";

        ticket3.description = "Payment for my completed trip has not been received yet.";

        ticket3.status = "Open";

        ticket3.created = "Aug 09, 2026 02:45 PM";

        ticket3.resolution = "The payment-related request has been received and is awaiting review by the support team.";

        ticket3.timeline.add(
                new TimelineEvent(
                        "Ticket Created",
                        "Aug 09, 2026 02:45 PM",
                        "Payment-related support request submitted."));

        // =====================================================
        // ADD
        // =====================================================

        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
    }

    // =========================================================
    // CARD
    // =========================================================

    private VBox createCard() {

        VBox card = new VBox(9);

        card.setPadding(
                new Insets(16));

        card.setMaxWidth(
                Double.MAX_VALUE);

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 16;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 16;");

        return card;
    }

    // =========================================================
    // FIELD LABEL
    // =========================================================

    private Label createFieldLabel(
            String text) {

        Label label = new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13));

        label.setTextFill(
                Color.web(TEXT));

        return label;
    }

    // =========================================================
    // RADIO BUTTON
    // =========================================================

    private RadioButton createRadioButton(
            String text,
            ToggleGroup group) {

        RadioButton radio = new RadioButton(text);

        radio.setToggleGroup(group);

        radio.setFont(
                Font.font(
                        "Arial",
                        13));

        radio.setTextFill(
                Color.web(TEXT));

        return radio;
    }

    // =========================================================
    // CONTROL STYLE
    // =========================================================

    private void styleControl(
            Control control) {

        control.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: " +
                        BORDER +
                        ";" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-font-size: 14px;");
    }

    // =========================================================
    // STATUS BADGE
    // =========================================================

    private Label createStatusBadge(
            String status) {

        Label badge = new Label(status);

        badge.setPadding(
                new Insets(
                        5,
                        9,
                        5,
                        9));

        badge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10));

        String background;
        String color;

        switch (status) {

            case "Resolved":
                background = "#DFF5E7";
                color = "#087A3A";
                break;

            case "In Progress":
                background = "#FFF0D5";
                color = "#C87500";
                break;

            case "Open":
                background = "#E2EEFF";
                color = "#2865B0";
                break;

            case "Closed":
                background = "#E9EEEC";
                color = "#596765";
                break;

            default:
                background = LIGHT_GREEN;
                color = GREEN;
        }

        badge.setStyle(
                "-fx-background-color: " +
                        background +
                        ";" +
                        "-fx-background-radius: 20;");

        badge.setTextFill(
                Color.web(color));

        return badge;
    }

    // =========================================================
    // INFO BADGE
    // =========================================================

    private Label createInfoBadge(
            String text) {

        Label badge = new Label(text);

        badge.setPadding(
                new Insets(
                        5,
                        9,
                        5,
                        9));

        badge.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10));

        badge.setStyle(
                "-fx-background-color: " +
                        LIGHT_GREEN +
                        ";" +
                        "-fx-background-radius: 20;");

        badge.setTextFill(
                Color.web(GREEN));

        return badge;
    }

    // =========================================================
    // TICKET ICON
    // =========================================================

    private String getTicketIcon(
            String issueType) {

        switch (issueType) {

            case "Payment Issue":
                return "₹";

            case "Load Issue":
                return "▣";

            case "Trip Issue":
                return "↗";

            case "Vehicle Issue":
                return "▤";

            case "Account Issue":
                return "●";

            case "Document Issue":
                return "▤";

            default:
                return "✦";
        }
    }

    // =========================================================
    // TICKET NORMAL STYLE
    // =========================================================

    private String ticketNormalStyle() {

        return "-fx-background-color: white;" +
                "-fx-border-color: #E0E7E3;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;";
    }

    // =========================================================
    // TICKET HOVER STYLE
    // =========================================================

    private String ticketHoverStyle() {

        return "-fx-background-color: #F5FBF7;" +
                "-fx-border-color: #0A8A57;" +
                "-fx-border-radius: 9;" +
                "-fx-background-radius: 9;";
    }

    // =========================================================
    // UPLOAD BUTTON STYLE
    // =========================================================

    private void setUploadButtonStyle(
            Button button,
            boolean hover) {

        if (hover) {

            button.setStyle(
                    "-fx-background-color: #F8FCF9;" +
                            "-fx-border-color: " +
                            GREEN +
                            ";" +
                            "-fx-border-style: dashed;" +
                            "-fx-border-width: 1.5;" +
                            "-fx-border-radius: 10;" +
                            "-fx-background-radius: 10;");

        } else {

            button.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-color: #BFC8C1;" +
                            "-fx-border-style: dashed;" +
                            "-fx-border-width: 1.5;" +
                            "-fx-border-radius: 10;" +
                            "-fx-background-radius: 10;");
        }
    }

    // =========================================================
    // RESET BUTTON STYLE
    // =========================================================

    private void setResetButtonStyle(
            Button button,
            boolean hover) {

        if (hover) {

            button.setStyle(
                    "-fx-background-color: #F3F6F4;" +
                            "-fx-text-fill: " +
                            TEXT +
                            ";" +
                            "-fx-border-color: #AEBBB4;" +
                            "-fx-border-radius: 9;" +
                            "-fx-background-radius: 9;");

        } else {

            button.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-text-fill: " +
                            TEXT +
                            ";" +
                            "-fx-border-color: #CDD5CF;" +
                            "-fx-border-radius: 9;" +
                            "-fx-background-radius: 9;");
        }
    }

    // =========================================================
    // SUBMIT BUTTON STYLE
    // =========================================================

    private void setSubmitButtonStyle(
            Button button,
            boolean hover) {

        button.setStyle(
                "-fx-background-color: " +
                        (hover ? GREEN_HOVER : GREEN) +
                        ";" +
                        "-fx-background-radius: 9;");
    }

    // =========================================================
    // DETAILS BUTTON STYLE
    // =========================================================

    private void setDetailsButtonStyle(
            Button button,
            boolean hover) {

        if (hover) {

            button.setStyle(
                    "-fx-background-color: " +
                            LIGHT_GREEN +
                            ";" +
                            "-fx-border-color: " +
                            GREEN +
                            ";" +
                            "-fx-border-radius: 7;" +
                            "-fx-background-radius: 7;" +
                            "-fx-font-size: 11px;" +
                            "-fx-font-weight: bold;");

        } else {

            button.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-color: #CDD8D2;" +
                            "-fx-border-radius: 7;" +
                            "-fx-background-radius: 7;" +
                            "-fx-font-size: 11px;" +
                            "-fx-font-weight: bold;");
        }
    }

    // =========================================================
    // ALERT
    // =========================================================

    private void showAlert(
            String title,
            String message) {

        Alert alert = new Alert(
                Alert.AlertType.INFORMATION);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }

    // =========================================================
    // SUPPORT TICKET MODEL
    // =========================================================

    private static class SupportTicket {

        String id;

        String issueType;

        String priority;

        String subject;

        String description;

        String status;

        String created;

        String resolution;

        String attachment;

        int rating;

        String feedback;

        List<TimelineEvent> timeline = new ArrayList<>();
    }

    // =========================================================
    // TIMELINE MODEL
    // =========================================================

    private static class TimelineEvent {

        String action;

        String time;

        String description;

        TimelineEvent(
                String action,
                String time,
                String description) {

            this.action = action;

            this.time = time;

            this.description = description;
        }
    }
}