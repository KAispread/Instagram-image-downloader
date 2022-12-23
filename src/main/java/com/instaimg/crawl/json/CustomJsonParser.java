package com.instaimg.crawl.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.instaimg.crawl.json.JsonAttr.MULTI_IMG_START_KEY;
import static com.instaimg.crawl.json.JsonAttr.SINGLE_IMG_START_KEY;

public class CustomJsonParser {
    public static List<String> getImageUrl(JSONObject jsonObject) {
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");
        List<String> imgURLs = new ArrayList<>();

        for (Object obj : jsonArray) {
            JSONObject item = (JSONObject) obj;
            if (item.containsKey(SINGLE_IMG_START_KEY.getKey())) {
                imgURLs.add(getSingleImgUrl(item));
            } else if (item.containsKey(MULTI_IMG_START_KEY.getKey())) {
                addMultiImgUrl(item, imgURLs);
            }
        }
        return imgURLs;
    }

    private static String getSingleImgUrl(JSONObject item) {
        JSONObject image_version = (JSONObject) item.get(SINGLE_IMG_START_KEY.getKey());
        JSONArray candidates = (JSONArray) image_version.get("candidates");
        JSONObject candidatesObj = (JSONObject) candidates.get(0);
        return String.valueOf(candidatesObj.get("url"));
    }

    private static void addMultiImgUrl(JSONObject item, List<String> imgURLs) {
        JSONArray carousel_media = (JSONArray) item.get(MULTI_IMG_START_KEY.getKey());
        for (Object obj : carousel_media) {
            JSONObject jsonObj = (JSONObject) obj;
            JSONObject image_version = (JSONObject) jsonObj.get(SINGLE_IMG_START_KEY.getKey());
            JSONArray candidates = (JSONArray) image_version.get("candidates");
            JSONObject candidatesObj = (JSONObject) candidates.get(0);
            String url = String.valueOf(candidatesObj.get("url"));
            imgURLs.add(url);
        }
    }
}
