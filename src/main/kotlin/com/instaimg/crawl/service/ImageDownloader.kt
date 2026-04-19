package com.instaimg.crawl.service

import com.instaimg.crawl.AppConstants
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.net.URL
import javax.imageio.ImageIO

class ImageDownloader {

    @Throws(IOException::class)
    fun download(imageUrl: String, targetDir: String, index: Int) {
        val image: BufferedImage = ImageIO.read(URL(imageUrl))
        val file = File(targetDir, "${AppConstants.IMAGE_PREFIX}-$index.png")
        ImageIO.write(image, "png", file)
        Thread.sleep(AppConstants.IMAGE_DOWNLOAD_DELAY_MS)
    }
}
