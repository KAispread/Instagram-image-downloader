package com.instaimg.crawl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class AppConstantsTest {

    @Test
    fun `image filename follows IMAGE-N dot png format`() {
        val filename = "${AppConstants.IMAGE_PREFIX}-1.png"
        assertEquals("IMAGE-1.png", filename)
    }

    @Test
    fun `image filename index increments correctly`() {
        val filenames = (1..3).map { "${AppConstants.IMAGE_PREFIX}-$it.png" }
        assertEquals(listOf("IMAGE-1.png", "IMAGE-2.png", "IMAGE-3.png"), filenames)
    }

    @Test
    fun `image filename matches expected pattern`() {
        val filenamePattern = Regex("${AppConstants.IMAGE_PREFIX}-\\d+\\.png")
        (1..5).forEach { index ->
            val filename = "${AppConstants.IMAGE_PREFIX}-$index.png"
            assertTrue(filename.matches(filenamePattern), "Expected '$filename' to match naming pattern")
        }
    }

    @Test
    fun `SESSION_ID is empty by default`() {
        assertEquals("", AppConstants.SESSION_ID)
    }

    @Test
    fun `pagination and delay constants are positive`() {
        assertTrue(AppConstants.MAX_PAGINATION > 0)
        assertTrue(AppConstants.IMAGES_PER_REQUEST > 0)
        assertTrue(AppConstants.API_DELAY_MS > 0)
        assertTrue(AppConstants.IMAGE_DOWNLOAD_DELAY_MS > 0)
    }
}
