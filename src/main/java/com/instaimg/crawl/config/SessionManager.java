package com.instaimg.crawl.config;

import com.instaimg.crawl.login.InstagramLoginHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Component
public class SessionManager {
    private Map<String, String> attributes = new ConcurrentHashMap<>();
    private final InstagramLoginHandler instagramLoginHandler;

//    @Autowired
    public SessionManager(InstagramLoginHandler instagramLoginHandler) throws InterruptedException {
        this.instagramLoginHandler = instagramLoginHandler;
//        setAttributes();
    }

    public void setAttributes() throws InterruptedException {
        Map<String, String> loginData = instagramLoginHandler.getLoginData();
        this.attributes.clear();
        this.attributes.putAll(loginData);
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
