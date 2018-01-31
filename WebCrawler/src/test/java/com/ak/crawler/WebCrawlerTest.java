package com.ak.crawler;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration
@TestPropertySource("application-test.properties")
public class WebCrawlerTest {

	@Autowired
	private WebCrawlerConfig config;

	@Test
	public void testExecute() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetMoreLinks() {
		// fail("Not yet implemented");
	}

}
