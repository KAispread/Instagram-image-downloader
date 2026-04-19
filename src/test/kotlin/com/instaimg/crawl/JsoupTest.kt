package com.instaimg.crawl

import org.jsoup.Connection
import org.jsoup.Jsoup
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class JsoupTest {

    @Disabled("네트워크 통합 테스트")
    @Test
    fun naver() {
        val cookies = Jsoup.connect("https://www.naver.com/").execute().cookies()
        cookies.keys.forEach { println("coo = $it") }
    }

    @Disabled("Instagram 세션 필요 - 네트워크 통합 테스트")
    @Test
    fun instaJson() {
        val accept = Jsoup
            .connect("https://www.instagram.com/api/v1/feed/user/minn.__.ju/username/?count=100")
            .userAgent(AppConstants.USER_AGENT)
            .timeout(3000)
            .ignoreContentType(true)
            .cookie("sessionid", AppConstants.SESSION_ID)
            .header("Accept", "application/json")
            .header("x-ig-app-id", AppConstants.APP_ID)
            .method(Connection.Method.GET)
            .execute().body()
        println("accept = $accept")
    }

    @Test
    fun formatPath() {
        val path = "C:/Users/Kracken/Desktop/buffer"
        println("$path/${AppConstants.IMAGE_PREFIX}-1.png")
    }
}
