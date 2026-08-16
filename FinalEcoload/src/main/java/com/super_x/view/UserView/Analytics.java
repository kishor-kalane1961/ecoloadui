package com.super_x.view.UserView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Analytics {

    // =========================================================
    // COLORS
    // =========================================================

    private static final String GREEN = "#087F43";
    private static final String GREEN_LIGHT = "#55D895";
    private static final String GREEN_PALE = "#BDEED2";
    private static final String GREEN_BG = "#EAF7F0";

    private static final String RED = "#F44343";
    private static final String RED_BG = "#FFF0F0";

    private static final String TEXT = "#18352B";
    private static final String TEXT_DARK = "#10251C";
    private static final String GREY = "#71807A";

    private static final String BORDER = "#DFE9E4";
    private static final String LINE = "#E5ECE8";

    private static final String BACKGROUND = "#F3FAF6";

    // =========================================================
    // MAIN SCENE
    // =========================================================

    public Scene getAnalyticsScene() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BACKGROUND + ";");

        // Header
        // root.setTop(createHeader());

        // =====================================================
        // CONTENT
        // =====================================================

        VBox content = new VBox(14);

        content.setPadding(
                new Insets(
                        18,
                        18,
                        25,
                        18));

        content.setFillWidth(true);

        content.getChildren().addAll(
                createPageTitle(),
                createKpiSection(),
                createMiddleSection(),
                createRouteSection(),
                createBottomSection());

        // =====================================================
        // IMPORTANT:
        // FORCE LABEL COLORS AFTER ALL CONTROLS ARE CREATED
        // =====================================================

        forceLabelColors(content);

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scrollPane = new ScrollPane(content);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setPannable(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-background: transparent;"
                        + "-fx-border-color: transparent;");

        root.setCenter(scrollPane);

        BorderPane mainContent = new BorderPane();
        mainContent.setTop(UserNavigation.createNavbar());
        mainContent.setCenter(root);

        BorderPane mainroot = new BorderPane();
        mainroot.setLeft(UserNavigation.createSidebar("Analytics"));

        mainroot.setCenter(mainContent);

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene = new Scene(
                mainroot,
                1536,
                750);

        scene.setFill(
                Color.web(BACKGROUND));

        return scene;
    }

    // =========================================================
    // FORCE LABEL COLORS
    // =========================================================

    private void forceLabelColors(
            javafx.scene.Node node) {

        if (node instanceof Label) {

            Label label = (Label) node;

            /*
             * IMPORTANT:
             * Inline CSS prevents your external stylesheet
             * from making the dashboard text white.
             */

            if (label.getText() != null
                    && label.getText().equals("ADMIN PANEL")) {

                label.setStyle(
                        "-fx-text-fill: " + GREEN + ";");

            } else {

                label.setStyle(
                        "-fx-text-fill: " + TEXT + ";");
            }
        }

        if (node instanceof Parent) {

            Parent parent = (Parent) node;

            for (javafx.scene.Node child : parent.getChildrenUnmodifiable()) {

                forceLabelColors(child);
            }
        }
    }

    // =========================================================
    // PAGE TITLE
    // =========================================================

    private VBox createPageTitle() {

        VBox box = new VBox(4);

        Label title = new Label(
                "Analytics Overview");

        title.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        24));

        title.setStyle(
                "-fx-text-fill: " + TEXT_DARK + ";");

        Label subtitle = new Label(
                "Monitor your business performance and transport insights.");

        subtitle.setFont(
                Font.font(
                        "System",
                        FontWeight.NORMAL,
                        11));

        subtitle.setStyle(
                "-fx-text-fill: " + GREY + ";");

        box.getChildren().addAll(
                title,
                subtitle);

        return box;
    }

    // =========================================================
    // KPI SECTION
    // =========================================================

    private HBox createKpiSection() {

        HBox row = new HBox(12);

        row.setAlignment(
                Pos.TOP_LEFT);

        VBox total = createKpiCard(
                "TOTAL LOADS",
                "18",
                "▣",
                "All transport loads",
                GREEN);

        VBox delivered = createKpiCard(
                "DELIVERED",
                "11",
                "✓",
                "↑ 12% vs last month",
                GREEN);

        VBox cancelled = createKpiCard(
                "CANCELLED",
                "1",
                "×",
                "↓ -5% vs last month",
                RED);

        HBox.setHgrow(
                total,
                Priority.ALWAYS);

        HBox.setHgrow(
                delivered,
                Priority.ALWAYS);

        HBox.setHgrow(
                cancelled,
                Priority.ALWAYS);

        row.getChildren().addAll(
                total,
                delivered,
                cancelled);

        return row;
    }

    // =========================================================
    // KPI CARD
    // =========================================================

    private VBox createKpiCard(
            String heading,
            String number,
            String iconText,
            String bottomText,
            String color) {

        VBox card = new VBox(5);

        card.setPadding(
                new Insets(
                        16,
                        18,
                        16,
                        18));

        card.setPrefHeight(125);
        card.setMinHeight(125);
        card.setMaxHeight(125);

        card.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 10;"
                        + "-fx-background-radius: 10;");

        HBox top = new HBox();

        top.setAlignment(
                Pos.CENTER_LEFT);

        Label headingLabel = new Label(
                heading);

        headingLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        10));

        headingLabel.setStyle(
                "-fx-text-fill: " + GREY + ";");

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        Circle iconCircle = new Circle(
                14,
                Color.web(
                        color.equals(RED)
                                ? RED_BG
                                : GREEN_BG));

        Label icon = new Label(
                iconText);

        icon.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        10));

        icon.setStyle(
                "-fx-text-fill: " + color + ";");

        StackPane iconPane = new StackPane(
                iconCircle,
                icon);

        top.getChildren().addAll(
                headingLabel,
                spacer,
                iconPane);

        Label value = new Label(
                number);

        value.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        30));

        value.setStyle(
                "-fx-text-fill: " + TEXT_DARK + ";");

        Label bottom = new Label(
                bottomText);

        bottom.setFont(
                Font.font(
                        "System",
                        FontWeight.NORMAL,
                        9));

        bottom.setStyle(
                "-fx-text-fill: " + color + ";");

        card.getChildren().addAll(
                top,
                value,
                bottom);

        return card;
    }

    // =========================================================
    // MIDDLE SECTION
    // =========================================================

    private HBox createMiddleSection() {

        HBox row = new HBox(12);

        VBox status = createStatusCard();

        status.setPrefWidth(440);
        status.setMinWidth(400);

        HBox insights = createInsightColumn();

        HBox.setHgrow(
                insights,
                Priority.ALWAYS);

        row.getChildren().addAll(
                status,
                insights);

        return row;
    }

    // =========================================================
    // STATUS CARD
    // =========================================================

    private VBox createStatusCard() {

        VBox card = whiteCard();

        card.setPrefHeight(215);
        card.setMinHeight(215);

        Label title = sectionTitle(
                "Load Status Breakdown");

        Label menu = new Label("⋮");

        menu.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        16));

        menu.setStyle(
                "-fx-text-fill: " + GREY + ";");

        HBox heading = new HBox();

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        heading.getChildren().addAll(
                title,
                spacer,
                menu);

        Line line = new Line(
                0,
                0,
                390,
                0);

        line.setStroke(
                Color.web(LINE));

        HBox chartArea = new HBox(28);

        chartArea.setAlignment(
                Pos.CENTER_LEFT);

        chartArea.setPadding(
                new Insets(
                        8,
                        10,
                        5,
                        10));

        chartArea.getChildren().addAll(
                createDonut(),
                createLegend());

        card.getChildren().addAll(
                heading,
                line,
                chartArea);

        return card;
    }

    // =========================================================
    // DONUT
    // =========================================================

    private StackPane createDonut() {

        StackPane container = new StackPane();

        container.setPrefSize(
                145,
                145);

        PieChart.Data delivered = new PieChart.Data(
                "Delivered",
                11);

        PieChart.Data inTransit = new PieChart.Data(
                "In Transit",
                6);

        PieChart.Data open = new PieChart.Data(
                "Open",
                0);

        PieChart.Data cancelled = new PieChart.Data(
                "Cancelled",
                1);

        PieChart chart = new PieChart();

        chart.getData().addAll(
                delivered,
                inTransit,
                open,
                cancelled);

        chart.setPrefSize(
                135,
                135);

        chart.setMinSize(
                135,
                135);

        chart.setMaxSize(
                135,
                135);

        chart.setLegendVisible(false);
        chart.setLabelsVisible(false);
        chart.setAnimated(false);
        chart.setStartAngle(90);

        chart.setStyle(
                "-fx-background-color: transparent;");

        Circle center = new Circle(
                39,
                Color.WHITE);

        Label number = new Label("18");

        number.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        26));

        number.setStyle(
                "-fx-text-fill: " + TEXT_DARK + ";");

        Label total = new Label(
                "TOTAL LOADS");

        total.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        6));

        total.setStyle(
                "-fx-text-fill: " + GREY + ";");

        VBox centerContent = new VBox(
                0,
                number,
                total);

        centerContent.setAlignment(
                Pos.CENTER);

        container.getChildren().addAll(
                chart,
                center,
                centerContent);

        chart.applyCss();
        chart.layout();

        if (delivered.getNode() != null) {

            delivered.getNode().setStyle(
                    "-fx-pie-color: " + GREEN + ";");
        }

        if (inTransit.getNode() != null) {

            inTransit.getNode().setStyle(
                    "-fx-pie-color: " + GREEN_LIGHT + ";");
        }

        if (open.getNode() != null) {

            open.getNode().setStyle(
                    "-fx-pie-color: " + GREEN_PALE + ";");
        }

        if (cancelled.getNode() != null) {

            cancelled.getNode().setStyle(
                    "-fx-pie-color: " + RED + ";");
        }

        return container;
    }

    // =========================================================
    // LEGEND
    // =========================================================

    private VBox createLegend() {

        VBox legend = new VBox(12);

        legend.setPrefWidth(125);

        legend.getChildren().addAll(

                legendRow(
                        GREEN,
                        "Delivered",
                        "11"),

                legendRow(
                        GREEN_LIGHT,
                        "In Transit",
                        "6"),

                legendRow(
                        GREEN_PALE,
                        "Open",
                        "0"),

                legendRow(
                        RED,
                        "Cancelled",
                        "1"));

        return legend;
    }

    private HBox legendRow(
            String color,
            String name,
            String number) {

        Circle dot = new Circle(
                4.5,
                Color.web(color));

        Label nameLabel = new Label(name);

        nameLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.NORMAL,
                        13));

        nameLabel.setStyle(
                "-fx-text-fill: " + TEXT + ";");

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        Label value = new Label(number);

        value.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        9));

        value.setStyle(
                "-fx-text-fill: " + TEXT + ";");

        HBox row = new HBox(
                8,
                dot,
                nameLabel,
                spacer,
                value);

        row.setAlignment(
                Pos.CENTER_LEFT);

        return row;
    }

    // =========================================================
    // INSIGHT CARDS
    // =========================================================

    private HBox createInsightColumn() {

        HBox row = new HBox(12);

        VBox best = createInsight(
                "BEST ROUTE",
                "Pune → Nashik",
                "Highest on-time delivery rate.",
                "➜");
        best.setPrefHeight(150);
        best.setMinHeight(150);
        best.setMaxHeight(150);

        VBox average = createInsight(
                "AVG DELIVERY TIME",
                "4.2 Days",
                "-0.3 days from average.",
                "◷");
        average.setPrefHeight(150);
        average.setMinHeight(150);
        average.setMaxHeight(150);

        VBox requested = createInsight(
                "MOST REQUESTED",
                "12-Ton Truck",
                "Accounts for 65% of volume.",
                "▣");
        requested.setPrefHeight(150);
        requested.setMinHeight(150);
        requested.setMaxHeight(150);

        HBox.setHgrow(
                best,
                Priority.ALWAYS);

        HBox.setHgrow(
                average,
                Priority.ALWAYS);

        HBox.setHgrow(
                requested,
                Priority.ALWAYS);

        row.getChildren().addAll(
                best,
                average,
                requested);

        return row;
    }

    // =========================================================
    // SINGLE INSIGHT
    // =========================================================

    private VBox createInsight(
            String heading,
            String value,
            String subtitle,
            String iconText) {

        VBox card = new VBox(7);

        card.setPadding(
                new Insets(
                        13,
                        14,
                        12,
                        14));

        card.setPrefHeight(105);
        card.setMinHeight(105);

        card.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-radius: 10;"
                        + "-fx-background-radius: 10;");

        HBox top = new HBox(9);

        top.setAlignment(
                Pos.CENTER_LEFT);

        Circle circle = new Circle(
                13,
                Color.web(GREEN_BG));

        Label icon = new Label(iconText);

        icon.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        9));

        icon.setStyle(
                "-fx-text-fill: " + GREEN + ";");

        StackPane iconPane = new StackPane(
                circle,
                icon);

        Label headingLabel = new Label(heading);

        headingLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        9));

        headingLabel.setStyle(
                "-fx-text-fill: " + GREY + ";");

        top.getChildren().addAll(
                iconPane,
                headingLabel);

        Label valueLabel = new Label(value);

        valueLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        16));

        valueLabel.setStyle(
                "-fx-text-fill: " + TEXT_DARK + ";");

        Label subtitleLabel = new Label(subtitle);

        subtitleLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.NORMAL,
                        8));

        subtitleLabel.setWrapText(true);

        subtitleLabel.setStyle(
                "-fx-text-fill: " + GREY + ";");

        card.getChildren().addAll(
                top,
                valueLabel,
                subtitleLabel);

        return card;
    }

    // =========================================================
    // ROUTE SECTION
    // =========================================================

    private VBox createRouteSection() {

        VBox card = whiteCard();

        card.setPrefHeight(200);
        card.setMinHeight(200);
        card.setMaxHeight(200);

        card.setPadding(
                new Insets(
                        14,
                        18,
                        14,
                        18));

        // =====================================================
        // TITLE
        // =====================================================

        Label title = new Label(
                "Top Routes");

        title.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        14));

        title.setStyle(
                "-fx-text-fill: " + TEXT + ";");

        // =====================================================
        // ROUTES
        // =====================================================

        VBox routes = new VBox(
                10);

        routes.getChildren().addAll(

                routeRow(
                        "Pune → Nashik",
                        "₹31,200",
                        3.00,
                        GREEN),

                routeRow(
                        "Mumbai → Bengaluru",
                        "₹29,500",
                        2.30,
                        GREEN),

                routeRow(
                        "Delhi → Jaipur",
                        "₹18,000",
                        1.38,
                        GREEN));

        card.getChildren().addAll(
                title,
                routes);

        return card;
    }

    // =========================================================
    // ROUTE ROW
    // =========================================================

    private VBox routeRow(
            String route,
            String revenue,
            double percentage,
            String color) {

        VBox row = new VBox(5);

        // =====================================================
        // TOP LINE : ROUTE + REVENUE
        // =====================================================

        HBox topLine = new HBox(8);

        Label routeLabel = new Label(
                route);

        routeLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        12));

        routeLabel.setStyle(
                "-fx-text-fill: " + TEXT + ";");

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        Label revenueLabel = new Label(
                revenue);

        revenueLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        12));

        revenueLabel.setStyle(
                "-fx-text-fill: " + TEXT + ";");

        topLine.getChildren().addAll(
                routeLabel,
                spacer,
                revenueLabel);

        topLine.setAlignment(
                Pos.CENTER_LEFT);

        // =====================================================
        // FULL WIDTH BACKGROUND BAR
        // =====================================================

        // StackPane bar =
        // new StackPane();

        // bar.setPrefHeight(6);
        // bar.setMinHeight(6);
        // bar.setMaxHeight(6);

        // bar.setMaxWidth(
        // Double.MAX_VALUE
        // );

        // Region background =
        // new Region();

        // background.setMaxWidth(
        // Double.MAX_VALUE
        // );

        // background.setPrefHeight(6);
        // background.setMaxHeight(6);

        // background.setStyle(
        // "-fx-background-color: #E2E7E4;"
        // + "-fx-background-radius: 6;"
        // );

        // // =====================================================
        // // GREEN PROGRESS
        // // =====================================================

        // Region fill =
        // new Region();

        // fill.setPrefHeight(6);
        // fill.setMaxHeight(6);

        // fill.prefWidthProperty().bind(
        // bar.widthProperty()
        // .multiply(
        // percentage
        // )
        // );

        // fill.setStyle(
        // "-fx-background-color: " + color + ";"
        // + "-fx-background-radius: 6;"
        // );

        // StackPane.setAlignment(
        // fill,
        // Pos.CENTER_LEFT
        // );

        // bar.getChildren().addAll(
        // background,
        // fill
        // );

        // // =====================================================
        // // ROW
        // // =====================================================

        StackPane bar = new StackPane();

        double barWidth = 320 * percentage;

        bar.setPrefWidth(barWidth);
        bar.setMinWidth(barWidth);
        bar.setMaxWidth(barWidth);

        bar.setPrefHeight(6);
        bar.setMinHeight(6);
        bar.setMaxHeight(6);

        Region fill = new Region();

        fill.setPrefWidth(barWidth);
        fill.setMinWidth(barWidth);
        fill.setMaxWidth(barWidth);

        fill.setPrefHeight(6);
        fill.setMinHeight(6);
        fill.setMaxHeight(6);

        fill.setStyle(
                "-fx-background-color: " + color + ";"
                        + "-fx-background-radius: 6;");

        bar.getChildren().add(
                fill);
        row.getChildren().addAll(
                topLine,
                bar);

        return row;
    }

    // =========================================================
    // BOTTOM SECTION
    // =========================================================

    private HBox createBottomSection() {

        HBox row = new HBox(12);

        VBox monthly = createMonthly();

        VBox recent = createRecent();

        HBox.setHgrow(
                monthly,
                Priority.ALWAYS);

        HBox.setHgrow(
                recent,
                Priority.ALWAYS);

        row.getChildren().addAll(
                monthly,
                recent);

        return row;
    }

    // =========================================================
    // MONTHLY
    // =========================================================

    private VBox createMonthly() {

        VBox card = whiteCard();

        card.setPrefHeight(210);
        card.setMinHeight(210);

        HBox heading = new HBox();

        Label title = sectionTitle(
                "Monthly Performance");

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        Label report = new Label(
                "View Report");

        report.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        8));

        report.setStyle(
                "-fx-text-fill: " + GREEN + ";");

        heading.getChildren().addAll(
                title,
                spacer,
                report);

        GridPane table = new GridPane();

        table.setHgap(35);
        table.setVgap(12);

        String[] headers = {
                "MONTH",
                "LOADS\nPOSTED",
                "DELIVERED",
                "CANCELLED",
                "GROWTH"
        };

        for (int i = 0; i < headers.length; i++) {

            Label header = new Label(
                    headers[i]);

            header.setFont(
                    Font.font(
                            "System",
                            FontWeight.BOLD,
                            7));

            header.setStyle(
                    "-fx-text-fill: " + GREY + ";");

            table.add(
                    header,
                    i,
                    0);
        }

        addMonthlyRow(
                table,
                1,
                "Jan",
                "18",
                "11",
                "1",
                "+18.5%");

        addMonthlyRow(
                table,
                2,
                "Dec",
                "15",
                "14",
                "0",
                "+5.2%");

        addMonthlyRow(
                table,
                3,
                "Nov",
                "14",
                "12",
                "2",
                "-2.1%");

        card.getChildren().addAll(
                heading,
                table);

        return card;
    }

    // =========================================================
    // MONTHLY ROW
    // =========================================================

    private void addMonthlyRow(
            GridPane table,
            int row,
            String month,
            String loads,
            String delivered,
            String cancelled,
            String growth) {

        String[] values = {
                month,
                loads,
                delivered,
                cancelled,
                growth
        };

        for (int i = 0; i < values.length; i++) {

            Label label = new Label(
                    values[i]);

            label.setFont(
                    Font.font(
                            "System",
                            i == 0
                                    ? FontWeight.BOLD
                                    : FontWeight.NORMAL,
                            12));

            String color = i == 4
                    ? (growth.startsWith("-")
                            ? RED
                            : GREEN)
                    : TEXT;

            label.setStyle(
                    "-fx-text-fill: " + color + ";");

            table.add(
                    label,
                    i,
                    row);
        }
    }

    // =========================================================
    // RECENT ACTIVITY
    // =========================================================

    private VBox createRecent() {

        VBox card = whiteCard();

        card.setPrefHeight(260);
        card.setMinHeight(260);
        card.setMaxHeight(260);

        Label title = sectionTitle(
                "Recent Activity");

        VBox activities = new VBox(12);

        activities.getChildren().addAll(

                activityRow(
                        GREEN,
                        "Load #EL-9821 Delivered",
                        "Pune → Nashik • 2 mins ago"),

                activityRow(
                        GREEN_LIGHT,
                        "Transport Request Accepted",
                        "Driver: Rajesh • 30 mins ago"),

                activityRow(
                        GREEN,
                        "Trip Started – Nashik Branch",
                        "Driver: Harsh • 4 hours ago"),

                activityRow(
                        GREEN_PALE,
                        "Load Assigned to Driver",
                        "Load #EL-9830 • 6 hours ago"));

        card.getChildren().addAll(
                title,
                activities);

        return card;
    }

    // =========================================================
    // ACTIVITY ROW
    // =========================================================

    private HBox activityRow(
            String color,
            String title,
            String subtitle) {

        // =====================================================
        // CIRCLE
        // =====================================================

        Circle dot = new Circle(
                6,
                Color.web(color));

        // =====================================================
        // VERTICAL LINE
        // =====================================================

        Region line = new Region();

        line.setPrefWidth(2);
        line.setMinWidth(2);
        line.setMaxWidth(2);

        line.setPrefHeight(45);

        line.setStyle(
                "-fx-background-color: #CDE8D9;");

        // =====================================================
        // TIMELINE
        // =====================================================

        VBox timeline = new VBox();

        timeline.setAlignment(
                Pos.TOP_CENTER);

        timeline.setPrefWidth(14);
        timeline.setMinWidth(14);
        timeline.setMaxWidth(14);

        timeline.getChildren().addAll(
                dot,
                line);

        // =====================================================
        // TITLE
        // =====================================================

        Label titleLabel = new Label(
                title);

        titleLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        12));

        titleLabel.setStyle(
                "-fx-text-fill: " + TEXT + ";");

        // =====================================================
        // SUBTITLE
        // =====================================================

        Label subtitleLabel = new Label(
                subtitle);

        subtitleLabel.setFont(
                Font.font(
                        "System",
                        FontWeight.NORMAL,
                        10));

        subtitleLabel.setStyle(
                "-fx-text-fill: " + GREY + ";");

        // =====================================================
        // TEXT
        // =====================================================

        VBox text = new VBox(
                3);

        text.getChildren().addAll(
                titleLabel,
                subtitleLabel);

        // =====================================================
        // MAIN ROW
        // =====================================================

        HBox row = new HBox(
                10,
                timeline,
                text);

        row.setAlignment(
                Pos.TOP_LEFT);

        return row;
    }

    // =========================================================
    // WHITE CARD
    // =========================================================

    private VBox whiteCard() {

        VBox card = new VBox(10);

        card.setPadding(
                new Insets(
                        14,
                        15,
                        14,
                        15));

        card.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: " + BORDER + ";"
                        + "-fx-border-width: 1;"
                        + "-fx-border-radius: 10;"
                        + "-fx-background-radius: 10;"
                        + "-fx-effect: dropshadow("
                        + "gaussian,"
                        + "rgba(0,0,0,0.025),"
                        + "5,"
                        + "0,"
                        + "0,"
                        + "1"
                        + ");");

        return card;
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

    private Label sectionTitle(
            String text) {

        Label title = new Label(text);

        title.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        16));

        title.setStyle(
                "-fx-text-fill: " + TEXT_DARK + ";");

        return title;
    }
}