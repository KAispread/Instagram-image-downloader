package com.instaimg.crawl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class JsonParseTest {
    @Test
    void name() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse("{\n" +
                "    \"id\": \"0001\",\n" +
                "    \"type\": \"donut\",\n" +
                "    \"name\": \"Cake\",\n" +
                "    \"ppu\": 0.55\n" +
                "}");

        System.out.println(jsonObject.containsKey("sasdf"));
    }
}
