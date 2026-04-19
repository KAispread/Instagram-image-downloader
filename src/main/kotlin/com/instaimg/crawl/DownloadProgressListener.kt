package com.instaimg.crawl

interface DownloadProgressListener {
    fun onMessage(message: String)
    fun onError(title: String, message: String)
    fun onComplete(title: String, message: String)
    fun onAbort()
    fun onClearLog()
}
