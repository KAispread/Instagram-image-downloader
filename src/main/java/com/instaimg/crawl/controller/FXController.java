package com.instaimg.crawl.controller;

import com.instaimg.crawl.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXController implements Initializable {
    @FXML
    TextField nickname, filePath, imgCount;

    @FXML
    TextArea process;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainApplication main = new MainApplication();
    }

    @FXML
    private void downloadButton(ActionEvent event) {
        boolean downFlag;
        try {
            downFlag = MainApplication.saveImages(process, nickname.getText(), filePath.getText(), imgCount.getText());
        } catch (Exception e) {
            alert("오류", "잘못된 요청입니다");
            return;
        }

        if (downFlag) {
            notify("다운로드 완료", "이미지 다운로드가 완료되었습니다");
        }
    }

    public static void notify(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public static void alert(String title, String headerText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
