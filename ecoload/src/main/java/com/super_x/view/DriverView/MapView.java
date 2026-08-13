package com.super_x.view.DriverView;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

public class MapView {

    private WebEngine webEngine;

    public void show(
            Stage owner,
            double pickupLat,
            double pickupLng,
            double destinationLat,
            double destinationLng) {

        // =====================================================
        // CREATE MAP WINDOW
        // =====================================================

        Stage mapStage = new Stage();

        mapStage.setTitle(
                "EcoLoad - Trip Route");

        /*
         * Make this window belong to your
         * EcoLoad application window.
         */
        mapStage.initOwner(owner);

        /*
         * User must close/back from map before
         * interacting with TripHistory.
         */
        mapStage.initModality(
                Modality.WINDOW_MODAL);

        // =====================================================
        // WEBVIEW
        // =====================================================

        WebView webView = new WebView();

        webView.setContextMenuEnabled(false);

        webView.setPrefSize(
                1000,
                600);

        webEngine = webView.getEngine();

        webEngine.setJavaScriptEnabled(true);

        // =====================================================
        // LOAD MAP.HTML
        // =====================================================

        URL mapUrl = getClass()
                .getResource(
                        "/map/map.html");

        if (mapUrl == null) {

            System.err.println(
                    "ERROR: map.html not found!");

            return;
        }

        System.out.println(
                "Loading map: " +
                        mapUrl);

        webEngine.load(
                mapUrl.toExternalForm());

        // =====================================================
        // WAIT FOR MAP.HTML
        // =====================================================

        webEngine
                .getLoadWorker()
                .stateProperty()
                .addListener(
                        (observable,
                                oldState,
                                newState) -> {

                            if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {

                                System.out.println(
                                        "Map HTML loaded successfully.");

                                Platform.runLater(() -> {

                                    loadRoute(
                                            pickupLat,
                                            pickupLng,
                                            destinationLat,
                                            destinationLng);

                                });

                            }

                        });

        // =====================================================
        // BACK BUTTON
        // =====================================================

        Button backButton = new Button(
                "← Back");

        backButton.setStyle(
                "-fx-background-color: #17233c;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 10 18 10 18;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;");

        /*
         * Back simply closes the map window.
         *
         * TripHistory is still open underneath.
         */
        backButton.setOnAction(
                e -> mapStage.close());

        // =====================================================
        // TITLE
        // =====================================================

        Label title = new Label(
                "Trip Route Map");

        title.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #17233c;");

        // =====================================================
        // SPACER
        // =====================================================

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS);

        // =====================================================
        // HEADER
        // =====================================================

        HBox header = new HBox(
                15,
                backButton,
                title,
                spacer);

        header.setStyle(
                "-fx-background-color: white;" +
                        "-fx-padding: 15;" +
                        "-fx-border-color: #eeeeee;" +
                        "-fx-border-width: 0 0 1 0;");

        // =====================================================
        // ROOT
        // =====================================================

        BorderPane root = new BorderPane();

        root.setTop(header);

        root.setCenter(
                webView);

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene = new Scene(
                root,
                1000,
                600);

        mapStage.setScene(
                scene);

        // =====================================================
        // WINDOW SIZE
        // =====================================================

        mapStage.setWidth(
                1000);

        mapStage.setHeight(
                600);

        // =====================================================
        // CENTER MAP WINDOW OVER ECLOAD
        // =====================================================

        if (owner != null) {

            mapStage.setX(
                    owner.getX()
                            +
                            (owner.getWidth() - 1000) / 2);

            mapStage.setY(
                    owner.getY()
                            +
                            (owner.getHeight() - 600) / 2);

        }

        // =====================================================
        // SHOW
        // =====================================================

        mapStage.show();

        /*
         * Make sure the map receives its final
         * WebView dimensions.
         */
        Platform.runLater(() -> {

            if (webEngine != null) {

                try {

                    webEngine.executeScript(
                            "if (typeof refreshMap === 'function') {" +
                                    "refreshMap();" +
                                    "}");

                } catch (Exception ex) {

                    System.out.println(
                            "Map refresh skipped.");

                }

            }

        });

    }

    // =========================================================
    // LOAD ROUTE
    // =========================================================

    private void loadRoute(
            double pickupLat,
            double pickupLng,
            double destinationLat,
            double destinationLng) {

        if (webEngine == null) {
            return;
        }

        String javascript = String.format(
                "showRoute(%f,%f,%f,%f);",

                pickupLat,
                pickupLng,

                destinationLat,
                destinationLng);

        System.out.println(
                "Executing: " +
                        javascript);

        try {

            webEngine.executeScript(
                    javascript);

        } catch (Exception e) {

            System.err.println(
                    "Unable to execute route JavaScript.");

            e.printStackTrace();

        }

    }

}