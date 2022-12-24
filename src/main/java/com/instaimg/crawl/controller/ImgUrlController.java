package com.instaimg.crawl.controller;

import com.instaimg.crawl.json.CustomJsonParser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Getter
@NoArgsConstructor
public class ImgUrlController {
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
    private final String IMAGE_PREFIX = "IMAGE";
    private final int MAX_IMAGE = 15;

//    @Autowired
//    public ImgUrlController(SessionManager sessionManager) {
//        this.sessionManager = sessionManager;
//        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
//                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1)) // to unlimited memory size
//                .build();
//        this.webClient = WebClient.builder()
//                .baseUrl("https://www.instagram.com")
//                .exchangeStrategies(exchangeStrategies)
//                .build();
//    }

    public void getProfileImgSourceV2(String nickname, String filePath, int imgCount) throws ParseException, InterruptedException, IOException {
        List<String> imageUrl = new ArrayList<>();
        String nextMaxId = "";
        int max = MAX_IMAGE;
        while (max > 0) {
            String jsonData = Jsoup.connect("https://www.instagram.com/api/v1/feed/user/" + nickname + "/username/?count=100&max_id=" + nextMaxId)
                    .userAgent(USER_AGENT)
                    .timeout(100000)
                    .ignoreContentType(true)
                    .cookie("sessionid", "value")
                    .header("Accept", "application/json")
                    .header("x-ig-app-id", "value")
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

        int count = 1;
        for (String url : imageUrl) {
            saveImage(url, filePath, count);
            count++;
            if (count > imgCount) {
                break;
            }
        }
    }

    private void saveImage(final String givenUrl, final String filePath, int count) throws IOException, InterruptedException {
        URL url = new URL(givenUrl);
        BufferedImage image = ImageIO.read(url);
        ImageIO.write(image, "png", new File(String.format("%s-%d.png", filePath + IMAGE_PREFIX, count)));
        Thread.sleep(500);
    }

    public JSONObject sge() throws ParseException, IOException {
        String jsonData = Jsoup.connect("https://www.instagram.com/api/v1/feed/user/minn.__.ju/username/?count=100")
                .userAgent(USER_AGENT)
                .timeout(3000)
                .ignoreContentType(true)
                .cookie("sessionid", "value")
                .header("Accept", "application/json")
                .header("x-ig-app-id", "value")
                .method(Connection.Method.GET)
                .execute().body();

        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(jsonData);
    }
}
