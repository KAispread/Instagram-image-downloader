package com.instaimg.crawl.controller

import com.instaimg.crawl.DownloadProgressListener
import com.instaimg.crawl.MainApplication
import com.instaimg.crawl.service.InstagramImageService
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.TextArea
import javafx.scene.control.TextField

class FXController : DownloadProgressListener {

    @field:FXML private lateinit var nickname: TextField
    @field:FXML private lateinit var filePath: TextField
    @field:FXML private lateinit var imgCount: TextField
    @field:FXML private lateinit var process: TextArea

    private val service = InstagramImageService()
    private var downloadThread: MainApplication? = null

    @FXML
    private fun downloadButton(event: ActionEvent) {
        val count = imgCount.text.takeIf { it.isNotBlank() }?.toIntOrNull() ?: Int.MAX_VALUE
        process.text = "***Nickname : ${nickname.text}\n***FilePath : ${filePath.text}\n"
        downloadThread = MainApplication(nickname.text, filePath.text, count, service, this).also {
            it.isDaemon = true
            it.start()
        }
    }

    @FXML
    private fun stopButton(event: ActionEvent) {
        downloadThread?.interrupt()
    }

    override fun onMessage(message: String) = runOnFxThread {
        process.appendText("\n$message")
    }

    override fun onError(title: String, message: String) = runOnFxThread {
        showAlert(Alert.AlertType.ERROR, title, message)
    }

    override fun onComplete(title: String, message: String) = runOnFxThread {
        showAlert(Alert.AlertType.INFORMATION, title, message)
    }

    override fun onAbort() = runOnFxThread {
        process.appendText("\n***STOP DOWNLOAD***")
        showAlert(Alert.AlertType.INFORMATION, "중지", "다운로드가 중단되었습니다.")
    }

    override fun onClearLog() = runOnFxThread {
        process.text = "Please try again"
    }

    private fun runOnFxThread(action: () -> Unit) = Platform.runLater(action)

    private fun showAlert(type: Alert.AlertType, title: String, message: String) {
        Alert(type).apply {
            this.title = title
            headerText = message
            showAndWait()
        }
    }
}
