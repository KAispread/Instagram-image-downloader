# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Instagram Image Downloader — a JavaFX desktop application that downloads images from public Instagram accounts. Written in Java 11+, built with Gradle. Max 500 recent posts per account; private accounts are not supported.

## Build & Run Commands

```bash
./gradlew build       # Build the project
./gradlew run         # Run the JavaFX application
./gradlew test        # Run tests
./gradlew jlink       # Create standalone executable via jlink
```

Gradle project name is `crawl` (defined in settings.gradle).

## Architecture

The app follows a three-layer pattern: UI → Controller → Download Logic, with a separate thread for downloads to keep the UI responsive.

- **InstagramFX** — JavaFX `Application` entry point; loads `instagramFX.fxml` and `style.css`
- **FXController** — FXML controller binding UI events (download/stop buttons) to business logic
- **MainApplication** — `Thread` subclass that orchestrates the download flow
- **ImgUrlController** — core logic: fetches image URLs from Instagram's API via Jsoup, then saves images with `ImageIO`
- **CustomJsonParser** — parses Instagram API JSON responses, handles both single-image and carousel posts
- **JsonAttr** — enum for Instagram JSON keys (`image_versions2`, `carousel_media`)

## Key Dependencies

- JavaFX 17 (via `org.openjfx.javafxplugin`)
- Jsoup 1.15.3 (HTML/HTTP)
- json-simple 1.1.1 (JSON parsing)
- Selenium 4.7.1 (imported but unused in main flow)
- Lombok (annotation processing)

## Notes

- The codebase and README are in Korean.
- `InstagramLoginHandler` contains hardcoded credentials and is unused — it was an experimental Selenium-based login approach.
- `ImgUrlController` references config classes (`UserProperty`, `WebProperty`) that don't exist in the source tree; related fields (`value`) are undefined. This is a known incomplete area.
