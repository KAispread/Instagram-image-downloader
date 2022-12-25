package com.instaimg.crawl.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@Component
@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@ConfigurationProperties("web.driver")
public class WebProperty {
    private String id;
    private String path;

    public WebProperty(String id, String path) {
        this.id = id;
        this.path = path;
    }
}
