package com.instaimg.crawl

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class InstagramFX : Application() {

    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<Parent>(
            checkNotNull(javaClass.getResource("instagramFX.fxml")) {
                "Cannot find instagramFX.fxml on classpath"
            }
        )
        primaryStage.title = "Instagram Downloader"
        val scene = Scene(root, 800.0, 600.0)
        scene.stylesheets.add(
            checkNotNull(javaClass.classLoader.getResource("css/style.css")) {
                "Cannot find css/style.css on classpath"
            }.toExternalForm()
        )
        primaryStage.scene = scene
        primaryStage.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(InstagramFX::class.java, *args)
        }
    }
}
