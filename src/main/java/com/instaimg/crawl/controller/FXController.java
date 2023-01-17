package com.instaimg.crawl.controller;

import com.instaimg.crawl.MainApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FXController implements Initializable {
    @FXML
    TextField nickname, filePath, imgCount;

    @FXML
    TextArea process;

    private MainApplication application;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void downloadButton(ActionEvent event) {
        application = new MainApplication(nickname.getText(), filePath.getText(), imgCount.getText(), process);
        application.setDaemon(true);
        application.setName("Download Thread");
        application.start();
    }

    @FXML
    private void stopButton(ActionEvent event) {
        application.interrupt();
    }

    public static void notify(String title, String headerText) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.showAndWait();
        });
    }

    public static void alert(String title, String headerText) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.showAndWait();
        });
    }

    public static void setTextArea(TextArea process, String text) {
        Platform.runLater(() -> {
            String ordinalText = process.getText();
            process.setText(ordinalText + "\n" + text);
        });
    }
}
