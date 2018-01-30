package com.ak.crawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ak.crawler.bo.SiteMap;
import com.ak.crawler.bo.Urls;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class WebCrawler {
	private static final Logger logger = LoggerFactory.getLogger(WebCrawler.class);
	private static HashSet<String> links = new HashSet<String>();

	@Autowired
	private WebCrawlerConfig config;

	public void execute() throws IOException {
		logger.debug("Application Started");
		// do validation of url
		SiteMap sitemap = new SiteMap();
		int depth = 1;

		Urls url = getMoreLinks(config.getDomain(), depth, new Urls());
		sitemap.setSitemap(url);
		logger.debug("****** Links " + links.size());
		display(links);
		logger.debug("**" + sitemap.getSitemap().getLoc().size() + " " + sitemap.getSitemap().getImage().size());

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(sitemap);
		logger.info(json);

	}

	public Urls getMoreLinks(String url, int depth, Urls urls) {
		if ((!links.contains(url) && (depth < Integer.parseInt(config.getSitedepth())))) {
			links.add(url);
			Document doc = WebCrawlerUtil.getDocument(url);
			if (doc != null) {
				Elements hrefs = doc.select("a[href]");
				Elements imgs = doc.select("img");
				depth++;

				for (Element img : imgs) {
					urls.getImage().add(img.attr("abs:src"));
					links.add(img.attr("abs:src"));
				}
				for (Element href : hrefs) {
					if (WebCrawlerUtil.isExternalLinks(href.absUrl("href"), config.getIgnorexternalsites())) {
						continue;
					} else {
						urls.getLoc().add(href.absUrl("href"));
						getMoreLinks(href.absUrl("href"), depth, urls);
					}
				}
			}
		}
		return urls;
	}

	// to be removed
	private void display(HashSet links) {
		Iterator i = links.iterator();
		while (i.hasNext()) {
			System.out.println((String) i.next());
		}
	}

}
