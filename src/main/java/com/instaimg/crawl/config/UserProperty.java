package com.instaimg.crawl.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@ConfigurationProperties("user")
public class UserProperty {
    private String id;
    private String pw;

    public UserProperty(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }
}
