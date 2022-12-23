package com.instaimg.crawl.json;

public enum JsonAttr {
    SINGLE_IMG_START_KEY("image_versions2"),
    MULTI_IMG_START_KEY("carousel_media");

    private String key;

    JsonAttr(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
