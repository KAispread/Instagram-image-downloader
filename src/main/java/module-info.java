module com.instaimg.crawl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires kotlin.stdlib;
    requires org.jsoup;
    requires json.simple;

    opens com.instaimg.crawl to javafx.graphics;
    opens com.instaimg.crawl.controller to javafx.fxml;
}
