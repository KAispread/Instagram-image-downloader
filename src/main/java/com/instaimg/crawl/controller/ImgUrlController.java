package com.instaimg.crawl.controller;

import com.instaimg.crawl.json.CustomJsonParser;
import javafx.scene.control.TextArea;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.instaimg.crawl.controller.FXController.*;

@Getter
@NoArgsConstructor
public class ImgUrlController {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
    private static final String IMAGE_PREFIX = "IMAGE";
    private static final int MAX_IMAGE = 15;

    public static boolean saveImgInLocal(String nickname, String filePath, int imgCount, TextArea process) throws ParseException, InterruptedException, IOException {
        setTextArea(process,"======" + nickname + ": image source parsing======");
        List<String> imageUrl = getImageURLs(nickname);

        if (!validateImgUrl(imageUrl.size(), process)) {
            return false;
        }

        setTextArea(process,"Total number of images : " + imageUrl.size());
        setTextArea(process,"============PARSING IS DONE=============" + "\n");
        setTextArea(process, "======START DOWNLOADING THE IMAGES======");

        int count = 1;
        int downCount = Math.min(imgCount, imageUrl.size());
        for (String url : imageUrl) {
            try {
                saveImage(url, filePath, count);
            } catch (IOException ioException) {
                alert("오류", "잘못된 폴더 경로입니다.");
                clearTextArea(process);
                return false;
            }
            if (count >= 10 && count % 10 == 0) {
                setTextArea(process, count + " images have been downloaded");
            }
            count++;
            if (count > downCount) {
                break;
            }
        }
        setTextArea(process, "============DOWNLOAD COMPLETE===========");
        return true;
    }

    private static boolean validateImgUrl(int size, TextArea process) {
        if (size == 0) {
            setTextArea(process,"존재하지 않는 닉네임이거나 비공개 계정입니다.");
            alert("오류", "다운로드할 이미지가 없습니다");
            return false;
        }
        return true;
    }

    private static List<String> getImageURLs(String nickname) throws ParseException, InterruptedException, IOException {
        List<String> imageUrl = new ArrayList<>();
        String nextMaxId = "";
        int max = MAX_IMAGE;
        while (max > 0) {
            String jsonData = Jsoup.connect("https://www.instagram.com/api/v1/feed/user/" + nickname + "/username/?count=100&max_id=" + nextMaxId)
                    .userAgent(USER_AGENT)
                    .timeout(100000)
                    .ignoreContentType(true)
                    .cookie("sessionid", value)
                    .header("Accept", "application/json")
                    .header("x-ig-app-id", value)
                    .method(Connection.Method.GET)
                    .execute().body();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonData);
            imageUrl.addAll(CustomJsonParser.getImageUrl(jsonObject));

            if (jsonObject.containsKey("next_max_id")) {
                nextMaxId = String.valueOf(jsonObject.get("next_max_id"));
                max--;
                Thread.sleep(1000);
                continue;
            }
            break;
        }
        return imageUrl;
    }

    private static void saveImage(final String givenUrl, final String filePath, final int count) throws IOException, InterruptedException {
        URL url = new URL(givenUrl);
        BufferedImage image = ImageIO.read(url);
        ImageIO.write(image, "png", new File(String.format("%s-%d.png", filePath + File.separator + IMAGE_PREFIX, count)));
        Thread.sleep(500);
    }
}
