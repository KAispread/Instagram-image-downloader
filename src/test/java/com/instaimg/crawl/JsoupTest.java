package com.instaimg.crawl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

class JsoupTest {
    private final WebClient webClient = WebClient.create("https://www.instagram.com");
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
    void insta() throws IOException {
        Connection.Response accept = Jsoup.connect("https://www.instagram.com")
                .userAgent(USER_AGENT)
                .timeout(3000)
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .method(Connection.Method.GET)
                .execute();

        System.out.println("accept = " + accept);
    }

//    @Test
    void instaProfileJson() throws ParseException {
        String jsonData = Objects.requireNonNull(webClient.get()
                .uri("/api/v1/feed/user/kiw.112/username/?count=100")
                .accept(MediaType.APPLICATION_JSON)
                .header("x-ig-app-id", "value")
                .cookie("sessionId", "value")
                .retrieve()
                .toEntity(String.class).block()).getBody();
        JSONParser parser = new JSONParser();
        JSONObject parse = (JSONObject) parser.parse(jsonData);

        System.out.println(parse.toJSONString());
    }
}