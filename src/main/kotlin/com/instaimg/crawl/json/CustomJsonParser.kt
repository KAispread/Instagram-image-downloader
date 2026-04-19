package com.instaimg.crawl.json

import com.instaimg.crawl.json.JsonAttr.MULTI_IMG_START_KEY
import com.instaimg.crawl.json.JsonAttr.SINGLE_IMG_START_KEY
import org.json.simple.JSONArray
import org.json.simple.JSONObject

object CustomJsonParser {

    fun getImageUrl(jsonObject: JSONObject): List<String> =
        (jsonObject["items"] as JSONArray).flatMap { obj ->
            val item = obj as JSONObject
            when {
                item.containsKey(SINGLE_IMG_START_KEY.key) -> listOf(extractFirstCandidateUrl(item, SINGLE_IMG_START_KEY))
                item.containsKey(MULTI_IMG_START_KEY.key) -> extractCarouselUrls(item)
                else -> emptyList()
            }
        }

    private fun extractCarouselUrls(item: JSONObject): List<String> =
        (item[MULTI_IMG_START_KEY.key] as JSONArray).map { obj ->
            extractFirstCandidateUrl(obj as JSONObject, SINGLE_IMG_START_KEY)
        }

    private fun extractFirstCandidateUrl(item: JSONObject, attr: JsonAttr): String {
        val imageVersion = item[attr.key] as JSONObject
        val candidates = imageVersion["candidates"] as JSONArray
        return (candidates[0] as JSONObject)["url"].toString()
    }
}
