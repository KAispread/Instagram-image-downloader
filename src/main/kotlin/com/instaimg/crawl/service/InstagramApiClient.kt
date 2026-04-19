package com.instaimg.crawl.service

import com.instaimg.crawl.AppConstants
import com.instaimg.crawl.json.CustomJsonParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import org.jsoup.Connection
import org.jsoup.Jsoup

data class FetchResult(
    val urls: List<String>,
    val pagesFetched: Int,
    val hitPageLimit: Boolean
)

class InstagramApiClient(private val sessionId: String = AppConstants.SESSION_ID) {

    suspend fun fetchImageUrls(
        nickname: String,
        onPageFetched: (pageNum: Int, pageCount: Int, totalSoFar: Int) -> Unit = { _, _, _ -> }
    ): FetchResult {
        val urls = mutableListOf<String>()
        var nextMaxId = ""
        var remaining = AppConstants.MAX_PAGINATION
        var pageNum = 0

        while (remaining > 0) {
            val page = withContext(Dispatchers.IO) { fetchPage(nickname, nextMaxId) }
            val pageUrls = CustomJsonParser.getImageUrl(page)
            urls += pageUrls
            pageNum++
            onPageFetched(pageNum, pageUrls.size, urls.size)

            if (page.containsKey("next_max_id")) {
                nextMaxId = page["next_max_id"].toString()
                remaining--
                delay(AppConstants.API_DELAY_MS)
            } else {
                return FetchResult(urls, pageNum, hitPageLimit = false)
            }
        }
        return FetchResult(urls, pageNum, hitPageLimit = true)
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
