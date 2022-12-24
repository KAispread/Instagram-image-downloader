package com.instaimg.crawl;

import com.instaimg.crawl.controller.ImgUrlController;

import java.io.File;

public class MainApplication {
    public static void main(String[] args) {
        ImgUrlController imgUrlController = new ImgUrlController();
        String nickname = args[0];
        String filePath = args[1] + File.separator;
        int imgCount = Integer.parseInt(args[2]);

        try {
            imgUrlController.getProfileImgSourceV2(nickname, filePath, imgCount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
