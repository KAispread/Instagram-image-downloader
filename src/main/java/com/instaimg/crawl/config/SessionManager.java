package com.instaimg.crawl.config;

import com.instaimg.crawl.login.InstagramLoginHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SessionManager {
    private Map<String, String> attributes = new ConcurrentHashMap<>();
    private final InstagramLoginHandler instagramLoginHandler;

    @Autowired
    public SessionManager(InstagramLoginHandler instagramLoginHandler) throws InterruptedException {
        this.instagramLoginHandler = instagramLoginHandler;
        setAttributes();
    }

    public void setAttributes() throws InterruptedException {
        attributes.clear();
        this.attributes = instagramLoginHandler.getLoginData();
        log.info("sessionid = {}", this.attributes.get(InstagramLoginHandler.SESSION_ID_KEY));
        log.info("x-ig-app-id = {}", this.attributes.get(InstagramLoginHandler.APP_ID_KEY));
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
