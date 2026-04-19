package com.instaimg.crawl.service

import com.instaimg.crawl.AppConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.net.URL
import javax.imageio.ImageIO

class ImageDownloader {

    @Throws(IOException::class)
    suspend fun download(imageUrl: String, targetDir: String, index: Int) {
        withContext(Dispatchers.IO) {
            val image: BufferedImage = ImageIO.read(URL(imageUrl))
            val file = File(targetDir, "${AppConstants.IMAGE_PREFIX}-$index.png")
            ImageIO.write(image, "png", file)
        }
    }
}
