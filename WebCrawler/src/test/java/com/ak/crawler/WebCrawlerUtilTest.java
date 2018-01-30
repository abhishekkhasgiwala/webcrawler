package com.ak.crawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.ak.crawler.WebCrawlerUtil;

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
		String url = "www.facebook.com";
		boolean flag = WebCrawlerUtil.isExternalLinks(url);
		assertEquals(flag, true);
	}

	@Test
	public void testIsExternalLinksFailure() {
		String url = "www.wipro.com";
		boolean flag = WebCrawlerUtil.isExternalLinks(url);
		assertEquals(flag, false);
	}
}
