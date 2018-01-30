package com.ak.crawler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebCrawlerApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(WebCrawlerApplication.class);
	@Autowired
	private WebCrawler webcrawler;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(WebCrawlerApplication.class, args);

	}

	@Override
	public void run(String[] args) {
		try {
			this.webcrawler.execute();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

}
