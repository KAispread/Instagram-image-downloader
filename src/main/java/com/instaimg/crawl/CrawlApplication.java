package com.instaimg.crawl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(
		value = {"classpath:properties/crawl.properties"}
)
public class CrawlApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrawlApplication.class, args);
	}
}
