package com.instaimg.crawl;

import com.instaimg.crawl.controller.FXController;
import com.instaimg.crawl.controller.ImgUrlController;
import javafx.scene.control.TextArea;

public class MainApplication extends Thread {
    private final String nickname;
    private final String filePath;
    private final int imgCount;
    private final TextArea process;

    public MainApplication(String nickname, String filePath, String imgCount, TextArea process) {
        super();
        this.nickname = nickname;
        this.filePath = filePath;
        this.imgCount = getImgCount(imgCount);
        this.process = process;
    }

    public void saveImages() {
        process.setText("***Nickname : " + nickname + "\n" + "***FilePath : " + filePath + "\n");
        try {
            if (ImgUrlController.saveImgInLocal(nickname, filePath, imgCount, process)) {
                FXController.notify("다운로드 완료", "이미지 다운로드가 완료되었습니다");
            }
        } catch (InterruptedException i) {
            FXController.notify("중지", "다운로드가 중단되었습니다.");
            FXController.setTextArea(process, "***STOP DOWNLOAD***");
        } catch (Exception e) {
            FXController.alert("오류", "잘못된 요청입니다");
            FXController.clearTextArea(process);
        }
    }

    private int getImgCount(String imgCount) {
        int count = Integer.MAX_VALUE;
        if (!imgCount.isBlank()) {
            count = Integer.parseInt(imgCount);
        }
        return count;
    }

    @Override
    public void run() {
        saveImages();
    }
}
