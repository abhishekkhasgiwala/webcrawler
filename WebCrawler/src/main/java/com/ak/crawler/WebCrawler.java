package com.ak.crawler;

import static com.ak.crawler.WebCrawlerConstants.ABS_SRC;
import static com.ak.crawler.WebCrawlerConstants.ATTR_HREF;
import static com.ak.crawler.WebCrawlerConstants.HTML_A_HREF;
import static com.ak.crawler.WebCrawlerConstants.HTML_IMG;

import java.util.HashSet;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ak.crawler.bo.SiteMap;
import com.ak.crawler.bo.Urls;

@Component
public class WebCrawler {
	private static final Logger logger = LoggerFactory.getLogger(WebCrawler.class);
	private static HashSet<String> links = new HashSet<String>();

	@Autowired
	private WebCrawlerConfig config;

	/*
	 * Crawler execute method to create sitemap
	 */
	public void execute() {
		logger.info("--Start--");

		SiteMap sitemap = new SiteMap();
		int depth = 1;
		if (WebCrawlerUtil.validateUrl(config.getDomain())) {
			Urls url = getMoreLinks(config.getDomain(), depth, new Urls());
			sitemap.setSitemap(url);

			// this will write the sitemap to given path
			WebCrawlerUtil.writeOutput(sitemap, config.getOutputFilePath());
		} else {
			logger.info("Abort as given domain is not valid");
		}
		logger.info("--End--");

	}

	/*
	 * This method will recursive get the links and img loc from webPage
	 * A->B->C->D->E with configurable depth
	 */
	public Urls getMoreLinks(String url, int depth, Urls urls) {
		if ((!links.contains(url) && (depth < Integer.parseInt(config.getSitedepth())))) {
			links.add(url);
			Document doc = WebCrawlerUtil.getDocument(url);
			if (doc != null) {
				Elements hrefs = doc.select(HTML_A_HREF);
				Elements imgs = doc.select(HTML_IMG);
				depth++;

				for (Element img : imgs) {
					urls.getImage().add(img.attr(ABS_SRC));
					links.add(img.attr(ABS_SRC));
				}
				for (Element href : hrefs) {
					if (WebCrawlerUtil.isExternalLinks(href.absUrl(ATTR_HREF), config.getIgnorexternalsites())) {
						continue;
					} else {
						urls.getLoc().add(href.absUrl(ATTR_HREF));
						getMoreLinks(href.absUrl(ATTR_HREF), depth, urls);
					}
				}
			}
		}
		return urls;
	}

}
