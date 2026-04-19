package com.instaimg.crawl.service

import com.instaimg.crawl.DownloadProgressListener
import java.io.IOException

class InstagramImageService(
    private val apiClient: InstagramApiClient = InstagramApiClient(),
    private val downloader: ImageDownloader = ImageDownloader()
) {

    fun downloadImages(
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

        imageUrls.take(maxCount).forEachIndexed { index, url ->
            val count = index + 1
            try {
                downloader.download(url, targetDir, count)
            } catch (e: IOException) {
                listener.onError("오류", "잘못된 폴더 경로입니다.")
                listener.onClearLog()
                return
            }
            if (count % 10 == 0) {
                listener.onMessage("$count images have been downloaded")
            }
        }

        listener.onMessage("============DOWNLOAD COMPLETE===========")
        listener.onComplete("다운로드 완료", "이미지 다운로드가 완료되었습니다")
    }
}
