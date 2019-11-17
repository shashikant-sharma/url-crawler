package com.tsystem.urlcrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UrlCrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlCrawlerApplication.class, args);
	}

}
