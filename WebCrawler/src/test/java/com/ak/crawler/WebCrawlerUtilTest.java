package com.ak.crawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class WebCrawlerUtilTest {

	// @Test
	public void testGetDocument() {
		fail("Not yet implemented");
	}

	// @Test
	public void testValidateUrl() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsExternalLinksSuccess() {
		String url = "www.google.com";
		WebCrawlerConfig config = new WebCrawlerConfig();
		config.setIgnorexternalsites("google,facebook,linkedin,twitter");
		boolean flag = WebCrawlerUtil.isExternalLinks(url, config.getIgnorexternalsites());
		assertEquals(flag, true);
	}

	@Test
	public void testIsExternalLinksFailure() {
		String url = "www.wipro1.com";
		WebCrawlerConfig config = new WebCrawlerConfig();
		config.setIgnorexternalsites("google,facebook,linkedin,twitter");
		boolean flag = WebCrawlerUtil.isExternalLinks(url, config.getIgnorexternalsites());
		assertEquals(flag, false);
	}
}
