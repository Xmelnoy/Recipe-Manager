package com.recipemanager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFxLauncher extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("Recipe Manager (JavaFX) — стартовый экран");
        StackPane root = new StackPane(label);
        stage.setTitle("Recipe Manager UI");
        stage.setScene(new Scene(root, 480, 240));
        stage.show();
    }
}
