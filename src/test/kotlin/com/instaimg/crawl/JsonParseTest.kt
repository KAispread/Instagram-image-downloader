package com.instaimg.crawl

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class JsonParseTest {

    private val parser = JSONParser()

    @Test
    fun `containsKey returns true for existing key`() {
        val json = parser.parse("""{"id":"0001","type":"donut"}""") as JSONObject
        assertTrue(json.containsKey("id"))
        assertTrue(json.containsKey("type"))
    }

    @Test
    fun `containsKey returns false for missing key`() {
        val json = parser.parse("""{"id":"0001"}""") as JSONObject
        assertFalse(json.containsKey("nonexistent"))
    }

    @Test
    fun `parse extracts string values correctly`() {
        val json = parser.parse("""{"name":"Cake","type":"donut"}""") as JSONObject
        assertTrue(json["name"] == "Cake")
        assertTrue(json["type"] == "donut")
    }
}
