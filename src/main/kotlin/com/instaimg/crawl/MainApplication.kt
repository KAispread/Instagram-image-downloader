package com.instaimg.crawl

import com.instaimg.crawl.service.InstagramImageService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainApplication(
    private val nickname: String,
    private val targetDir: String,
    private val maxCount: Int,
    private val service: InstagramImageService,
    private val listener: DownloadProgressListener
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun start() {
        scope.launch {
            try {
                service.downloadImages(nickname, targetDir, maxCount, listener)
            } catch (e: CancellationException) {
                listener.onAbort()
            } catch (e: Exception) {
                listener.onError("오류", "잘못된 요청입니다")
                listener.onClearLog()
            }
        }
    }

    fun cancel() = scope.cancel()
}
