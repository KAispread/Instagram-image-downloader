package com.instaimg.crawl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class JsonParseTest {
    @Test
    void name() throws JSONException {
        JSONObject jsonObject = new JSONObject("{\n" +
                "    \"id\": \"0001\",\n" +
                "    \"type\": \"donut\",\n" +
                "    \"name\": \"Cake\",\n" +
                "    \"ppu\": 0.55,\n" +
                "    \"batters\":\n" +
                "        {\n" +
                "            \"batter\":\n" +
                "                [\n" +
                "                    { \"id\": \"1001\", \"type\": \"Regular\" },\n" +
                "                    { \"id\": \"1002\", \"type\": \"Chocolate\" },\n" +
                "                    { \"id\": \"1003\", \"type\": \"Blueberry\" },\n" +
                "                    { \"id\": \"1004\", \"type\": \"Devil's Food\" }\n" +
                "                ]\n" +
                "        },\n" +
                "    \"topping\":\n" +
                "        [\n" +
                "            { \"id\": \"5001\", \"type\": \"None\" },\n" +
                "            { \"id\": \"5002\", \"type\": \"Glazed\" },\n" +
                "            { \"id\": \"5005\", \"type\": \"Sugar\" },\n" +
                "            { \"id\": \"5007\", \"type\": \"Powdered Sugar\" },\n" +
                "            { \"id\": \"5006\", \"type\": \"Chocolate with Sprinkles\" },\n" +
                "            { \"id\": \"5003\", \"type\": \"Chocolate\" },\n" +
                "            { \"id\": \"5004\", \"type\": \"Maple\" }\n" +
                "        ]\n" +
                "}");
        JSONObject sdf = jsonObject.getJSONObject("batters");

        System.out.println(sdf.has("sasdf"));
    }
}
