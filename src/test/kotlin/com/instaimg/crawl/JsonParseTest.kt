package com.instaimg.crawl

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.junit.jupiter.api.Test

class JsonParseTest {
    @Test
    fun parseBasicJson() {
        val parser = JSONParser()
        val jsonObject = parser.parse(
            """{"id":"0001","type":"donut","name":"Cake","ppu":0.55}"""
        ) as JSONObject
        println(jsonObject.containsKey("sasdf"))
    }
}
