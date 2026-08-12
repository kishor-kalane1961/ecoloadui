package com.super_x;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class NavigationService {

    private final Stage stage;
    private final Map<String, Scene> scenes = new HashMap<>();
    private final Map<String, String> titles = new HashMap<>();

    public NavigationService(Stage stage) {
        this.stage = stage;
    }

    public void register(String key, Scene scene, String title) {
        scenes.put(key, scene);
        titles.put(key, title);
    }

    public void navigate(String key) {
        Scene scene = scenes.get(key);
        if (scene == null) {
            return;
        }

        stage.setScene(scene);

        String title = titles.get(key);
        if (title != null) {
            stage.setTitle(title);
        }
    }
}
