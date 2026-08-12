package com.super_x.view.DriverView;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.layout.BorderPane;

public class TruckWala {

    public Scene getTruckWalaScene() {

        BorderPane mainroot = new BorderPane();

        mainroot.setLeft(
                DriverNavigation.createSidebar("TruckWala")
        );

        WebView webView = new WebView();

        WebEngine webEngine = webView.getEngine();

        webEngine.setJavaScriptEnabled(true);

        webEngine.getLoadWorker().stateProperty().addListener(
                (obs, oldState, newState) -> {

                    if (newState == javafx.concurrent.Worker.State.SUCCEEDED) {

                        webEngine.executeScript(
                                "document.body.style.backgroundColor = '#080F10';"
                        );
                    }
                }
        );

        webEngine.load("https://hornokplease.xyz/");

        BorderPane main = new BorderPane();

        main.setTop(
                DriverNavigation.createNavbar()
        );

        main.setCenter(webView);

        mainroot.setCenter(main);

        return new Scene(mainroot, 1536, 750);
    }
}