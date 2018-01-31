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
		String url = "http://localhost:8080";
		boolean out = WebCrawlerUtil.validateUrl(url);
		assertEquals(true, out);
	}

	@Test(expected = IllegalArgumentException.class)
	public void throwsOnMalformedUrl() {
		WebCrawlerUtil.validateUrl("adsfad");
	}

	@Test
	public void testIsExternalLinksSuccess() {
		String url = "www.google.com";
		WebCrawlerConfig config = new WebCrawlerConfig();
		config.setIgnorexternalsites("google,facebook,linkedin,twitter");
		boolean flag = WebCrawlerUtil.isExternalLinks(url, config.getIgnorexternalsites());
		assertEquals(true, flag);
	}

	@Test
	public void testIsExternalLinksFailure() {
		String url = "www.wipro1.com";
		WebCrawlerConfig config = new WebCrawlerConfig();
		config.setIgnorexternalsites("google,facebook,linkedin,twitter");
		boolean flag = WebCrawlerUtil.isExternalLinks(url, config.getIgnorexternalsites());
		assertEquals(false, flag);
	}
}
