package com.instaimg.crawl;

import com.instaimg.crawl.controller.ImgUrlController;
import javafx.scene.control.TextArea;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class MainApplication {
    public static boolean saveImages(TextArea process, String nickname, String filePath, String imgCount) throws ParseException, IOException, InterruptedException {
        ImgUrlController imgUrlController = new ImgUrlController();
        int count = Integer.MAX_VALUE;
        if (!imgCount.isBlank()) {
            count = Integer.parseInt(imgCount);
        }
        process.setText("***Nickname : " + nickname + "\n" + "***FilePath : " + filePath + "\n");
        return imgUrlController.saveImgInLocal(nickname, filePath, count, process);
    }
}
