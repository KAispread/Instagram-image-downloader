package com.instaimg.crawl;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

class JsoupTest {
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";

    @Test
    void naver() throws IOException {
        Map<String, String> cookies = Jsoup.connect("https://www.naver.com/")
                .execute().cookies();

        for (String coo : cookies.keySet()) {
            System.out.println("coo = " + coo);
        }
    }

    @Test
    void instaJson() throws IOException {
        String accept = Jsoup.connect("https://www.instagram.com/api/v1/feed/user/minn.__.ju/username/?count=100")
                .userAgent(USER_AGENT)
                .timeout(3000)
                .ignoreContentType(true)
                .cookie("sessionid", "56999113234%3AN95grMEAoBjzDN%3A3%3AAYfd1vZWhuYtYJBOFYD0Lfz6BkJUJrzvYD1UylHISw")
                .header("Accept", "application/json")
                .header("x-ig-app-id", "936619743392459")
                .method(Connection.Method.GET)
                .execute().body();

        System.out.println("accept = " + accept);
    }

//    @Test
//    void instaProfileJson() throws ParseException {
//        String jsonData = Objects.requireNonNull(webClient.get()
//                .uri("/api/v1/feed/user/kiw.112/username/?count=100")
//                .accept(MediaType.APPLICATION_JSON)
//                .header("x-ig-app-id", "value")
//                .cookie("sessionId", "value")
//                .retrieve()
//                .toEntity(String.class).block()).getBody();
//        JSONParser parser = new JSONParser();
//        JSONObject parse = (JSONObject) parser.parse(jsonData);
//
//        System.out.println(parse.toJSONString());
//    }

    @Test
    void name() {
        System.out.println(String.format("%s-%d.png", "C:/Users/Kracken/Desktop/buffer/" + "IMAGE", 1));
    }
}