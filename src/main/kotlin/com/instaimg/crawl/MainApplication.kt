package com.instaimg.crawl

import com.instaimg.crawl.service.InstagramImageService

class MainApplication(
    private val nickname: String,
    private val targetDir: String,
    private val maxCount: Int,
    private val service: InstagramImageService,
    private val listener: DownloadProgressListener
) : Thread("Download Thread") {

    override fun run() {
        try {
            service.downloadImages(nickname, targetDir, maxCount, listener)
        } catch (e: InterruptedException) {
            listener.onAbort()
        } catch (e: Exception) {
            listener.onError("오류", "잘못된 요청입니다")
            listener.onClearLog()
        }
    }
}
