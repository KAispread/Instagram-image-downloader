package com.instaimg.crawl

internal object AppConstants {
    const val USER_AGENT =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36"
    const val APP_ID = "936619743392459"
    const val SESSION_ID = ""

    const val IMAGE_PREFIX = "IMAGE"
    const val IMAGES_PER_REQUEST = 100
    const val MAX_PAGINATION = 15
    const val API_DELAY_MS = 1_000L
    const val CONCURRENT_DOWNLOADS = 5
}
