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
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
}
