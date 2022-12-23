package com.instaimg.crawl.config;

import com.instaimg.crawl.login.InstagramLoginHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
