package com.instaimg.crawl

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.instaimg.crawl.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Instagram Downloader"
    ) {
        App()
    }
}
