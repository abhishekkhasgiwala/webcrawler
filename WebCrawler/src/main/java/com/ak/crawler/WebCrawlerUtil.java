package com.ak.crawler;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * WebCrawler Util class
 */
public class WebCrawlerUtil {

	private static final Logger logger = LoggerFactory.getLogger(WebCrawlerUtil.class);

	/*
	 * Util method for retrieving Html document from given url
	 */
	public static Document getDocument(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			logger.error("Url " + url + " " + e.getMessage(), e.getCause());
		}
		return doc;
	}

	/*
	 * Validator method to check giving urls is reachable for success response
	 * back i.e HTTP Status code =200 it will retrun true and for rest 40X & 50X
	 * it will return false
	 */
	public static boolean validateUrl(String url) {
		Connection.Response response = null;
		boolean flag = false;
		try {
			response = Jsoup.connect(url).execute();
			if (response.statusCode() == 200) {
				flag = true;
			}
		} catch (IOException e) {
			logger.error("Url " + url + " " + response.statusCode() + " " + response.statusMessage());
		}
		return flag;

	}

	/*
	 * Util method to check weather given url is from external sites eg:
	 * google,facebook,linkedin,twitter, if it is then it will return true else
	 * false.
	 */
	public static boolean isExternalLinks(String url) {
		String blacklistConfig = "google,linkedin,facebook,twitter";
		String blacklist[] = blacklistConfig.split(",");
		boolean flag = false;
		for (int i = 0; i < blacklist.length; i++) {
			if (url.contains(blacklist[i])) {
				flag = true;
			} else {
				continue;
			}
		}
		return flag;
	}
}
