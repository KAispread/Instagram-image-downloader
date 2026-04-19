package com.instaimg.crawl.service

import com.instaimg.crawl.AppConstants
import com.instaimg.crawl.DownloadProgressListener
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import java.io.IOException
import java.util.concurrent.atomic.AtomicInteger

class InstagramImageService(
    private val apiClient: InstagramApiClient = InstagramApiClient(),
    private val downloader: ImageDownloader = ImageDownloader()
) {

    suspend fun downloadImages(
        nickname: String,
        targetDir: String,
        maxCount: Int,
        listener: DownloadProgressListener
    ) {
        listener.onMessage("======$nickname: image source parsing======")

        val imageUrls = apiClient.fetchImageUrls(nickname)

        if (imageUrls.isEmpty()) {
            listener.onMessage("존재하지 않는 닉네임이거나 비공개 계정입니다.")
            listener.onError("오류", "다운로드할 이미지가 없습니다")
            return
        }

        listener.onMessage("Total number of images : ${imageUrls.size}")
        listener.onMessage("============PARSING IS DONE=============\n")
        listener.onMessage("======START DOWNLOADING THE IMAGES======")

        val targetUrls = imageUrls.take(maxCount)
        val semaphore = Semaphore(AppConstants.CONCURRENT_DOWNLOADS)
        val downloadedCount = AtomicInteger(0)

        try {
            coroutineScope {
                targetUrls.forEachIndexed { index, url ->
                    launch {
                        semaphore.withPermit {
                            downloader.download(url, targetDir, index + 1)
                            val count = downloadedCount.incrementAndGet()
                            if (count % 10 == 0) {
                                listener.onMessage("$count images have been downloaded")
                            }
                        }
                    }
                }
            }
        } catch (e: IOException) {
            listener.onError("오류", "잘못된 폴더 경로입니다.")
            listener.onClearLog()
            return
        }

        listener.onMessage("============DOWNLOAD COMPLETE===========")
        listener.onComplete("다운로드 완료", "이미지 다운로드가 완료되었습니다")
    }
}
