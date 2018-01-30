package com.ak.crawler;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ak.crawler.bo.SiteMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

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
		} catch (HttpStatusException e) {
			logger.error(e.getMessage());
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return doc;
	}

	/*
	 * Validator method to check giving urls is reachable for success response
	 * back i.e HTTP Status code =200 it will retrun true and for rest 40X & 50X
	 * it will return false
	 */
	public static boolean validateUrl(String url) {
		boolean flag = false;
		try {
			Connection con = Jsoup.connect(url);
			Response response = con.execute();
			if (response.statusCode() == 200) {
				flag = true;
			}
		} catch (HttpStatusException e) {
			logger.error(e.getMessage());
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return flag;

	}

	/*
	 * Util method to check weather given url is from external sites eg:
	 * google,facebook,linkedin,twitter, if it is then it will return true else
	 * false.
	 */
	public static boolean isExternalLinks(String url, String ignoreSites) {
		String blacklist[] = ignoreSites.split(",");
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

	public static void writeOutput(SiteMap sitemap, String path) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json;
		try {
			json = ow.writeValueAsString(sitemap);
			ow.writeValue(new File(path), sitemap);
			logger.info(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
