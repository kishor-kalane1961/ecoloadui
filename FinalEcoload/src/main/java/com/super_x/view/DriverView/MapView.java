package com.super_x.view.DriverView;

import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapView {

    // =========================================================
    // GOOGLE MAPS API KEY
    // =========================================================
    //
    // Put your actual Google API key here.
    //
    // The key must have:
    //
    // 1. Routes API
    // 2. Maps JavaScript API
    //
    // =========================================================

    private static final String GOOGLE_MAPS_API_KEY =
            "AIzaSyCcewsn1o4zOJhBD6TQOgmyEmg0i3Soyec";


    // =========================================================
    // GOOGLE ROUTES API
    // =========================================================

    private static final String ROUTES_API_URL =
            "https://routes.googleapis.com/directions/v2:computeRoutes";


    // =========================================================
    // HTTP CLIENT
    // =========================================================

    private final HttpClient httpClient =
            HttpClient.newBuilder()
                    .connectTimeout(
                            Duration.ofSeconds(15)
                    )
                    .build();


    // =========================================================
    // SHOW MAP
    // =========================================================

    public void show(
            Stage owner,
            double pickupLat,
            double pickupLng,
            double destinationLat,
            double destinationLng) {

        // =====================================================
        // CREATE MAP WINDOW
        // =====================================================

        Stage mapStage =
                new Stage();


        mapStage.setTitle(
                "EcoLoad - Trip Route"
        );


        if (owner != null) {

            mapStage.initOwner(
                    owner
            );

            mapStage.initModality(
                    Modality.WINDOW_MODAL
            );
        }


        // =====================================================
        // BACK BUTTON
        // =====================================================

        Button backButton =
                new Button(
                        "← Back"
                );


        backButton.setStyle(
                "-fx-background-color: #0B6B2A;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 9 18 9 18;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );


        backButton.setOnAction(
                event -> mapStage.close()
        );


        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        "Trip Route Map"
                );


        title.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #17233C;"
        );


        // =====================================================
        // HEADER SPACER
        // =====================================================

        Region spacer =
                new Region();


        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        // =====================================================
        // HEADER
        // =====================================================

        HBox header =
                new HBox(
                        15,
                        backButton,
                        title,
                        spacer
                );


        header.setAlignment(
                Pos.CENTER_LEFT
        );


        header.setPadding(
                new Insets(
                        12,
                        15,
                        12,
                        15
                )
        );


        header.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E5E5E5;" +
                "-fx-border-width: 0 0 1 0;"
        );


        // =====================================================
        // WEBVIEW
        // =====================================================

        WebView webView =
                new WebView();


        webView.setContextMenuEnabled(
                false
        );


        webView.setPrefSize(
                1100,
                620
        );


        webView.setMinSize(
                0,
                0
        );


        WebEngine webEngine =
                webView.getEngine();


        webEngine.setJavaScriptEnabled(
                true
        );


        // =====================================================
        // LOADING
        // =====================================================

        ProgressIndicator progress =
                new ProgressIndicator();


        progress.setPrefSize(
                50,
                50
        );


        Label loadingLabel =
                new Label(
                        "Calculating route..."
                );


        loadingLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0B6B2A;"
        );


        VBox loadingBox =
                new VBox(
                        12,
                        progress,
                        loadingLabel
                );


        loadingBox.setAlignment(
                Pos.CENTER
        );


        // =====================================================
        // MAP CONTAINER
        // =====================================================

        StackPane mapContainer =
                new StackPane();


        mapContainer.setStyle(
                "-fx-background-color: #F1F3F1;"
        );


        mapContainer
                .getChildren()
                .add(
                        loadingBox
                );


        // =====================================================
        // DISTANCE
        // =====================================================

        Label distanceLabel =
                new Label(
                        "Distance: --"
                );


        distanceLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0B6B2A;"
        );


        // =====================================================
        // DURATION
        // =====================================================

        Label durationLabel =
                new Label(
                        "Estimated time: --"
                );


        durationLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0B6B2A;"
        );


        // =====================================================
        // ROUTE INFORMATION
        // =====================================================

        HBox routeInfo =
                new HBox(
                        30,
                        distanceLabel,
                        durationLabel
                );


        routeInfo.setAlignment(
                Pos.CENTER_LEFT
        );


        routeInfo.setPadding(
                new Insets(
                        12,
                        20,
                        12,
                        20
                )
        );


        routeInfo.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E5E5E5;" +
                "-fx-border-width: 1 0 0 0;"
        );


        // =====================================================
        // ROOT
        // =====================================================

        BorderPane root =
                new BorderPane();


        root.setTop(
                header
        );


        root.setCenter(
                mapContainer
        );


        root.setBottom(
                routeInfo
        );


        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        root,
                        1100,
                        700
                );


        mapStage.setScene(
                scene
        );


        mapStage.setWidth(
                1100
        );


        mapStage.setHeight(
                700
        );


        // =====================================================
        // CENTER WINDOW
        // =====================================================

        if (owner != null) {

            mapStage.setX(
                    owner.getX()
                            +
                            (
                                    owner.getWidth()
                                            - 1100
                            ) / 2
            );


            mapStage.setY(
                    owner.getY()
                            +
                            (
                                    owner.getHeight()
                                            - 700
                            ) / 2
            );
        }


        // =====================================================
        // SHOW WINDOW
        // =====================================================

        mapStage.show();


        // =====================================================
        // CALCULATE ROUTE
        // =====================================================

        Thread routeThread =
                new Thread(
                        () -> {

                            try {

                                RouteResult result =
                                        calculateRoute(
                                                pickupLat,
                                                pickupLng,
                                                destinationLat,
                                                destinationLng
                                        );


                                Platform.runLater(
                                        () -> {

                                            distanceLabel.setText(
                                                    "Distance: "
                                                            +
                                                    formatDistance(
                                                            result.distanceMeters
                                                    )
                                            );


                                            durationLabel.setText(
                                                    "Estimated time: "
                                                            +
                                                    formatDuration(
                                                            result.durationSeconds
                                                    )
                                            );


                                            loadGoogleMap(
                                                    webView,
                                                    result.encodedPolyline,
                                                    pickupLat,
                                                    pickupLng,
                                                    destinationLat,
                                                    destinationLng,
                                                    mapContainer
                                            );

                                        }
                                );


                            } catch (Exception ex) {

                                ex.printStackTrace();


                                Platform.runLater(
                                        () -> {

                                            showError(
                                                    ex.getMessage(),
                                                    mapContainer,
                                                    distanceLabel,
                                                    durationLabel
                                            );

                                        }
                                );
                            }

                        }
                );


        routeThread.setDaemon(
                true
        );


        routeThread.start();
    }


    // =========================================================
    // CALCULATE ROUTE
    // =========================================================

    private RouteResult calculateRoute(
            double pickupLat,
            double pickupLng,
            double destinationLat,
            double destinationLng)
            throws Exception {


        // =====================================================
        // REQUEST BODY
        // =====================================================

        String requestBody =
                String.format(
                        Locale.US,

                        """
                        {
                          "origin": {
                            "location": {
                              "latLng": {
                                "latitude": %.8f,
                                "longitude": %.8f
                              }
                            }
                          },

                          "destination": {
                            "location": {
                              "latLng": {
                                "latitude": %.8f,
                                "longitude": %.8f
                              }
                            }
                          },

                          "travelMode": "DRIVE",

                          "routingPreference": "TRAFFIC_AWARE",

                          "polylineQuality": "HIGH_QUALITY",

                          "polylineEncoding": "ENCODED_POLYLINE",

                          "computeAlternativeRoutes": false,

                          "routeModifiers": {
                            "avoidTolls": false,
                            "avoidHighways": false,
                            "avoidFerries": false
                          },

                          "languageCode": "en",

                          "units": "METRIC"
                        }
                        """,

                        pickupLat,
                        pickupLng,

                        destinationLat,
                        destinationLng
                );


        // =====================================================
        // HTTP REQUEST
        // =====================================================

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(
                                        ROUTES_API_URL
                                )
                        )
                        .timeout(
                                Duration.ofSeconds(30)
                        )
                        .header(
                                "Content-Type",
                                "application/json"
                        )
                        .header(
                                "X-Goog-Api-Key",
                                GOOGLE_MAPS_API_KEY
                        )
                        .header(
                                "X-Goog-FieldMask",
                                "routes.distanceMeters,"
                                        +
                                "routes.duration,"
                                        +
                                "routes.polyline.encodedPolyline"
                        )
                        .POST(
                                HttpRequest.BodyPublishers
                                        .ofString(
                                                requestBody
                                        )
                        )
                        .build();


        System.out.println(
                "Calling Google Routes API..."
        );


        // =====================================================
        // SEND REQUEST
        // =====================================================

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers
                                .ofString()
                );


        System.out.println(
                "Routes API status: "
                        +
                response.statusCode()
        );


        // =====================================================
        // CHECK RESPONSE
        // =====================================================

        if (response.statusCode() != 200) {

            throw new Exception(
                    "Google Routes API error "
                            +
                    response.statusCode()
                            +
                    ":\n"
                            +
                    response.body()
            );
        }


        // =====================================================
        // GET POLYLINE
        // =====================================================

        String encodedPolyline =
                extractString(
                        response.body(),

                        "\"encodedPolyline\"\\s*:\\s*\"([^\"]+)\""
                );


        if (encodedPolyline == null
                ||
                encodedPolyline.isBlank()) {

            throw new Exception(
                    "Google Routes API did not return a route polyline."
            );
        }


        System.out.println(
                "Route polyline received."
        );


        System.out.println(
                "Polyline length: "
                        +
                encodedPolyline.length()
        );


        // =====================================================
        // GET DISTANCE
        // =====================================================

        String distanceString =
                extractString(
                        response.body(),

                        "\"distanceMeters\"\\s*:\\s*(\\d+)"
                );


        double distanceMeters =
                0;


        if (distanceString != null) {

            distanceMeters =
                    Double.parseDouble(
                            distanceString
                    );
        }


        // =====================================================
        // GET DURATION
        // =====================================================

        String durationString =
                extractString(
                        response.body(),

                        "\"duration\"\\s*:\\s*\"([0-9.]+)s\""
                );


        double durationSeconds =
                0;


        if (durationString != null) {

            durationSeconds =
                    Double.parseDouble(
                            durationString
                    );
        }


        System.out.println(
                "Distance: "
                        +
                distanceMeters
                        +
                " meters"
        );


        System.out.println(
                "Duration: "
                        +
                durationSeconds
                        +
                " seconds"
        );


        // =====================================================
        // RESULT
        // =====================================================

        return new RouteResult(
                encodedPolyline,
                distanceMeters,
                durationSeconds
        );
    }


    // =========================================================
    // LOAD GOOGLE MAP
    // =========================================================

    private void loadGoogleMap(
            WebView webView,
            String encodedPolyline,
            double pickupLat,
            double pickupLng,
            double destinationLat,
            double destinationLng,
            StackPane mapContainer) {


        WebEngine webEngine =
                webView.getEngine();


        // =====================================================
        // ESCAPE POLYLINE
        // =====================================================

        String safePolyline =
                escapeJavaScriptString(
                        encodedPolyline
                );


        // =====================================================
        // CREATE HTML
        // =====================================================

        String html =
                createGoogleMapHtml(
                        safePolyline,
                        pickupLat,
                        pickupLng,
                        destinationLat,
                        destinationLng
                );


        // =====================================================
        // LOAD FAILURE LISTENER
        // =====================================================

        webEngine
                .getLoadWorker()
                .stateProperty()
                .addListener(
                        (observable,
                         oldState,
                         newState) -> {

                            if (newState ==
                                    Worker.State.FAILED) {

                                Throwable exception =
                                        webEngine
                                                .getLoadWorker()
                                                .getException();


                                Platform.runLater(
                                        () -> {

                                            showError(
                                                    "Google Maps JavaScript failed to load.\n"
                                                            +
                                                    exception,
                                                    mapContainer,
                                                    null,
                                                    null
                                            );

                                        }
                                );
                            }

                        }
                );


        // =====================================================
        // PUT WEBVIEW INTO CONTAINER
        // =====================================================

        mapContainer
                .getChildren()
                .clear();


        mapContainer
                .getChildren()
                .add(
                        webView
                );


        // =====================================================
        // LOAD HTML
        // =====================================================

        webEngine.loadContent(
                html
        );
    }


    // =========================================================
    // CREATE GOOGLE MAP HTML
    // =========================================================

    private String createGoogleMapHtml(
            String encodedPolyline,
            double pickupLat,
            double pickupLng,
            double destinationLat,
            double destinationLng) {


        String pickupLatString =
                String.format(
                        Locale.US,
                        "%.8f",
                        pickupLat
                );


        String pickupLngString =
                String.format(
                        Locale.US,
                        "%.8f",
                        pickupLng
                );


        String destinationLatString =
                String.format(
                        Locale.US,
                        "%.8f",
                        destinationLat
                );


        String destinationLngString =
                String.format(
                        Locale.US,
                        "%.8f",
                        destinationLng
                );


        // =====================================================
        // HTML BUILDER
        // =====================================================

        StringBuilder html =
                new StringBuilder();


        // =====================================================
        // HTML
        // =====================================================

        html.append(
                "<!DOCTYPE html>"
        );


        html.append(
                "<html>"
        );


        html.append(
                "<head>"
        );


        html.append(
                "<meta charset=\"UTF-8\">"
        );


        html.append(
                "<meta name=\"viewport\" "
                        +
                "content=\"initial-scale=1.0,"
                        +
                "maximum-scale=1.0,"
                        +
                "user-scalable=no\">"
        );


        // =====================================================
        // CSS
        // =====================================================

        html.append(
                "<style>"
        );


        html.append(
                "html,body{"
                        +
                "width:100%;"
                        +
                "height:100%;"
                        +
                "margin:0;"
                        +
                "padding:0;"
                        +
                "overflow:hidden;"
                        +
                "}"
        );


        html.append(
                "#map{"
                        +
                "width:100%;"
                        +
                "height:100%;"
                        +
                "}"
        );


        html.append(
                "</style>"
        );


        html.append(
                "</head>"
        );


        // =====================================================
        // BODY
        // =====================================================

        html.append(
                "<body>"
        );


        html.append(
                "<div id=\"map\"></div>"
        );


        // =====================================================
        // JAVASCRIPT
        // =====================================================

        html.append(
                "<script>"
        );


        // =====================================================
        // ROUTE
        // =====================================================

        html.append(
                "const encodedRoute='"
        );


        html.append(
                encodedPolyline
        );


        html.append(
                "';"
        );


        // =====================================================
        // PICKUP
        // =====================================================

        html.append(
                "const pickup={lat:"
                        +
                pickupLatString
                        +
                ",lng:"
                        +
                pickupLngString
                        +
                "};"
        );


        // =====================================================
        // DESTINATION
        // =====================================================

        html.append(
                "const destination={lat:"
                        +
                destinationLatString
                        +
                ",lng:"
                        +
                destinationLngString
                        +
                "};"
        );


        // =====================================================
        // INIT MAP
        // =====================================================

        html.append(
                "function initMap(){"
        );


        // =====================================================
        // CREATE MAP
        // =====================================================

        html.append(
                "const map=new google.maps.Map("
                        +
                "document.getElementById('map'),"
                        +
                "{"
                        +
                "zoom:7,"
                        +
                "center:pickup,"
                        +
                "mapTypeId:'roadmap',"
                        +
                "streetViewControl:true,"
                        +
                "fullscreenControl:true,"
                        +
                "zoomControl:true,"
                        +
                "mapTypeControl:true"
                        +
                "}"
                        +
                ");"
        );


        // =====================================================
        // DECODE ROUTE
        // =====================================================

        html.append(
                "const routePath="
                        +
                "google.maps.geometry.encoding.decodePath("
                        +
                "encodedRoute"
                        +
                ");"
        );


        // =====================================================
        // CHECK ROUTE
        // =====================================================

        html.append(
                "console.log('Route points:',routePath.length);"
        );


        // =====================================================
        // BOUNDS
        // =====================================================

        html.append(
                "const bounds="
                        +
                "new google.maps.LatLngBounds();"
        );


        html.append(
                "routePath.forEach(function(point){"
                        +
                "bounds.extend(point);"
                        +
                "});"
        );


        html.append(
                "bounds.extend(pickup);"
        );


        html.append(
                "bounds.extend(destination);"
        );


        // =====================================================
        // ROUTE WHITE OUTLINE
        // =====================================================

        html.append(
                "new google.maps.Polyline({"
                        +
                "path:routePath,"
                        +
                "geodesic:true,"
                        +
                "strokeColor:'#FFFFFF',"
                        +
                "strokeOpacity:1.0,"
                        +
                "strokeWeight:10,"
                        +
                "map:map,"
                        +
                "zIndex:1"
                        +
                "});"
        );


        // =====================================================
        // MAIN GREEN ROUTE
        // =====================================================

        html.append(
                "new google.maps.Polyline({"
                        +
                "path:routePath,"
                        +
                "geodesic:true,"
                        +
                "strokeColor:'#0B6B2A',"
                        +
                "strokeOpacity:1.0,"
                        +
                "strokeWeight:6,"
                        +
                "map:map,"
                        +
                "zIndex:2"
                        +
                "});"
        );


        // =====================================================
        // PICKUP MARKER
        // =====================================================

        html.append(
                "new google.maps.Marker({"
                        +
                "position:pickup,"
                        +
                "map:map,"
                        +
                "title:'Pickup Location',"
                        +
                "label:{"
                        +
                "text:'P',"
                        +
                "color:'#FFFFFF',"
                        +
                "fontWeight:'bold'"
                        +
                "}"
                        +
                "});"
        );


        // =====================================================
        // DESTINATION MARKER
        // =====================================================

        html.append(
                "new google.maps.Marker({"
                        +
                "position:destination,"
                        +
                "map:map,"
                        +
                "title:'Destination',"
                        +
                "label:{"
                        +
                "text:'D',"
                        +
                "color:'#FFFFFF',"
                        +
                "fontWeight:'bold'"
                        +
                "}"
                        +
                "});"
        );


        // =====================================================
        // FIT MAP TO ROUTE
        // =====================================================

        html.append(
                "map.fitBounds(bounds);"
        );


        // =====================================================
        // LIMIT MAXIMUM ZOOM
        // =====================================================

        html.append(
                "google.maps.event.addListenerOnce("
                        +
                "map,"
                        +
                "'bounds_changed',"
                        +
                "function(){"
                        +
                "if(map.getZoom()>15){"
                        +
                "map.setZoom(15);"
                        +
                "}"
                        +
                "}"
                        +
                ");"
        );


        // =====================================================
        // RESIZE
        // =====================================================

        html.append(
                "setTimeout(function(){"
                        +
                "google.maps.event.trigger(map,'resize');"
                        +
                "map.fitBounds(bounds);"
                        +
                "},500);"
        );


        // =====================================================
        // END INIT
        // =====================================================

        html.append(
                "}"
        );


        // =====================================================
        // GOOGLE AUTH FAILURE
        // =====================================================

        html.append(
                "window.gm_authFailure=function(){"
                        +
                "document.body.innerHTML="
                        +
                "'<div style=\""
                        +
                "font-family:Arial;"
                        +
                "padding:40px;"
                        +
                "text-align:center;"
                        +
                "color:#D32F2F;\">"
                        +
                "<h2>Google Maps API Error</h2>"
                        +
                "<p>Check Maps JavaScript API, "
                        +
                "billing and API key.</p>"
                        +
                "</div>';"
                        +
                "};"
        );


        // =====================================================
        // END SCRIPT
        // =====================================================

        html.append(
                "</script>"
        );


        // =====================================================
        // GOOGLE MAPS JAVASCRIPT API
        // =====================================================

        html.append(
                "<script async defer "
                        +
                "src=\"https://maps.googleapis.com/maps/api/js?"
                        +
                "key=__API_KEY__"
                        +
                "&libraries=geometry"
                        +
                "&callback=initMap\">"
                        +
                "</script>"
        );


        // =====================================================
        // END BODY
        // =====================================================

        html.append(
                "</body>"
        );


        html.append(
                "</html>"
        );


        // =====================================================
        // INSERT API KEY
        // =====================================================

        return html.toString()
                .replace(
                        "__API_KEY__",
                        GOOGLE_MAPS_API_KEY
                );
    }


    // =========================================================
    // ESCAPE JAVASCRIPT STRING
    // =========================================================

    private String escapeJavaScriptString(
            String value) {

        if (value == null) {

            return "";
        }


        return value
                .replace(
                        "\\",
                        "\\\\"
                )
                .replace(
                        "'",
                        "\\'"
                )
                .replace(
                        "\r",
                        "\\r"
                )
                .replace(
                        "\n",
                        "\\n"
                );
    }


    // =========================================================
    // EXTRACT STRING FROM JSON
    // =========================================================

    private String extractString(
            String json,
            String regex) {

        Pattern pattern =
                Pattern.compile(
                        regex
                );


        Matcher matcher =
                pattern.matcher(
                        json
                );


        if (matcher.find()) {

            return matcher.group(1);
        }


        return null;
    }


    // =========================================================
    // FORMAT DISTANCE
    // =========================================================

    private String formatDistance(
            double meters) {

        if (meters < 1000) {

            return String.format(
                    Locale.US,
                    "%.0f m",
                    meters
            );
        }


        return String.format(
                Locale.US,
                "%.1f km",
                meters / 1000.0
        );
    }


    // =========================================================
    // FORMAT DURATION
    // =========================================================

    private String formatDuration(
            double seconds) {

        long totalSeconds =
                Math.round(
                        seconds
                );


        long hours =
                totalSeconds / 3600;


        long minutes =
                (
                        totalSeconds % 3600
                ) / 60;


        if (hours > 0) {

            return hours
                    + " hr "
                    + minutes
                    + " min";
        }


        return minutes
                + " min";
    }


    // =========================================================
    // SHOW ERROR
    // =========================================================

    private void showError(
            String message,
            StackPane mapContainer,
            Label distanceLabel,
            Label durationLabel) {

        if (message == null
                ||
                message.isBlank()) {

            message =
                    "Unable to calculate route.";
        }


        Label errorTitle =
                new Label(
                        "Unable to calculate route"
                );


        errorTitle.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #D32F2F;"
        );


        Label errorMessage =
                new Label(
                        message
                );


        errorMessage.setWrapText(
                true
        );


        errorMessage.setMaxWidth(
                850
        );


        errorMessage.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #555555;"
        );


        VBox errorBox =
                new VBox(
                        10,
                        errorTitle,
                        errorMessage
                );


        errorBox.setAlignment(
                Pos.CENTER
        );


        errorBox.setPadding(
                new Insets(
                        30
                )
        );


        ScrollPane scrollPane =
                new ScrollPane(
                        errorBox
                );


        scrollPane.setFitToWidth(
                true
        );


        scrollPane.setFitToHeight(
                true
        );


        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );


        mapContainer
                .getChildren()
                .clear();


        mapContainer
                .getChildren()
                .add(
                        scrollPane
                );


        if (distanceLabel != null) {

            distanceLabel.setText(
                    "Distance: --"
            );
        }


        if (durationLabel != null) {

            durationLabel.setText(
                    "Estimated time: --"
            );
        }
    }


    // =========================================================
    // ROUTE RESULT
    // =========================================================

    private static class RouteResult {

        private final String encodedPolyline;

        private final double distanceMeters;

        private final double durationSeconds;


        RouteResult(
                String encodedPolyline,
                double distanceMeters,
                double durationSeconds) {

            this.encodedPolyline =
                    encodedPolyline;


            this.distanceMeters =
                    distanceMeters;


            this.durationSeconds =
                    durationSeconds;
        }
    }
}