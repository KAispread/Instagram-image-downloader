package com.instaimg.crawl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class InstagramFX  extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("instagramFX.fxml")));
        primaryStage.setTitle("Instagram Downloader");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets()
                .add(Objects.requireNonNull(getClass().getClassLoader().getResource("css/style.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
