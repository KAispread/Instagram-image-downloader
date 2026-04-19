package com.instaimg.crawl.service

import com.instaimg.crawl.AppConstants
import com.instaimg.crawl.json.CustomJsonParser
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.jsoup.Connection
import org.jsoup.Jsoup

class InstagramApiClient(private val sessionId: String = AppConstants.SESSION_ID) {

    fun fetchImageUrls(nickname: String): List<String> {
        val urls = mutableListOf<String>()
        var nextMaxId = ""
        var remaining = AppConstants.MAX_PAGINATION

        while (remaining > 0) {
            val page = fetchPage(nickname, nextMaxId)
            urls += CustomJsonParser.getImageUrl(page)

            if (page.containsKey("next_max_id")) {
                nextMaxId = page["next_max_id"].toString()
                remaining--
                Thread.sleep(AppConstants.API_DELAY_MS)
            } else {
                break
            }
        }
        return urls
    }

    private fun fetchPage(nickname: String, maxId: String): JSONObject {
        val url = "https://www.instagram.com/api/v1/feed/user/$nickname/username/" +
                "?count=${AppConstants.IMAGES_PER_REQUEST}&max_id=$maxId"

        val body = Jsoup.connect(url)
            .userAgent(AppConstants.USER_AGENT)
            .timeout(100_000)
            .ignoreContentType(true)
            .cookie("sessionid", sessionId)
            .header("Accept", "application/json")
            .header("x-ig-app-id", AppConstants.APP_ID)
            .method(Connection.Method.GET)
            .execute()
            .body()

        return JSONParser().parse(body) as JSONObject
    }
}
