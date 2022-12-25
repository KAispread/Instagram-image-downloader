package com.instaimg.crawl;

import com.instaimg.crawl.controller.ImgUrlController;
import javafx.scene.control.TextArea;

public class MainApplication {
    public static boolean saveImages(TextArea process, String nickname, String filePath, String imgCount) {
        ImgUrlController imgUrlController = new ImgUrlController();
        int count = Integer.MAX_VALUE;
        if (!imgCount.isBlank()) {
            count = Integer.parseInt(imgCount);
        }
        process.setText("***Nickname : " + nickname + "\n" + "***FilePath : " + filePath + "\n");
        try {
            return imgUrlController.saveImgInLocal(nickname, filePath, count, process);
        } catch (Exception e) {
            return false;
        }
    }
}
