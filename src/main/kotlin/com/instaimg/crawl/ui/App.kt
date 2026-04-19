package com.instaimg.crawl.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.instaimg.crawl.DownloadProgressListener
import com.instaimg.crawl.MainApplication
import com.instaimg.crawl.service.ImageDownloader
import com.instaimg.crawl.service.InstagramApiClient
import com.instaimg.crawl.service.InstagramImageService

@Composable
fun App() {
    var nickname by remember { mutableStateOf("") }
    var filePath by remember { mutableStateOf("") }
    var imgCount by remember { mutableStateOf("") }
    var log by remember { mutableStateOf("") }
    var downloadThread by remember { mutableStateOf<MainApplication?>(null) }

    val service = remember { InstagramImageService(InstagramApiClient(), ImageDownloader()) }

    val listener = remember {
        object : DownloadProgressListener {
            override fun onMessage(message: String) { log += "$message\n" }
            override fun onError(title: String, message: String) { log += "[$title] $message\n" }
            override fun onComplete(title: String, message: String) { log += "[$title] $message\n" }
            override fun onAbort() { log += "다운로드가 중단되었습니다.\n" }
            override fun onClearLog() { log = "" }
        }
    }

    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
            Text(
                text = "Instagram Image Downloader",
                style = MaterialTheme.typography.h5
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                label = { Text("Nickname") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = filePath,
                onValueChange = { filePath = it },
                label = { Text("File Path") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = imgCount,
                onValueChange = { imgCount = it },
                label = { Text("Image Count (optional)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {
                    val count = imgCount.takeIf { it.isNotBlank() }?.toIntOrNull() ?: Int.MAX_VALUE
                    log = "***Nickname : $nickname\n***FilePath : $filePath\n"
                    downloadThread = MainApplication(nickname, filePath, count, service, listener).also {
                        it.isDaemon = true
                        it.start()
                    }
                }) {
                    Text("Download")
                }

                Button(
                    onClick = { downloadThread?.interrupt() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFB700))
                ) {
                    Text("Stop")
                }
            }

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = log,
                onValueChange = {},
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                readOnly = true,
                label = { Text("Log") }
            )
        }
    }
}
