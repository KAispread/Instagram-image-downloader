package com.instaimg.crawl.controller;

import com.instaimg.crawl.config.SessionManager;
import com.instaimg.crawl.json.CustomJsonParser;
import com.instaimg.crawl.login.InstagramLoginHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Slf4j
@RestController
public class LoginController {
    private WebClient webClient;
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
    private final SessionManager sessionManager;

    @Autowired
    public LoginController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1)) // to unlimited memory size
                .build();
        this.webClient = WebClient.builder()
                .baseUrl("https://www.instagram.com")
                .exchangeStrategies(exchangeStrategies)
                .build();
    }

    @GetMapping("/jsonData")
    public List<String> getProfileJson(@RequestParam String nickname) throws ParseException, InterruptedException {
        Map<String, String> loginData = sessionManager.getAttributes();

        String jsonData = Objects.requireNonNull(webClient.get()
                .uri("/api/v1/feed/user/"+nickname+"/username/?count=10000")
                .accept(MediaType.APPLICATION_JSON)
                .header("x-ig-app-id", loginData.get(InstagramLoginHandler.APP_ID_KEY))
                .cookie("sessionId", loginData.get(InstagramLoginHandler.SESSION_ID_KEY))
                .retrieve()
                .toEntity(String.class).block()).getBody();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonData);

        return CustomJsonParser.getImageUrl(jsonObject);
    }
}
