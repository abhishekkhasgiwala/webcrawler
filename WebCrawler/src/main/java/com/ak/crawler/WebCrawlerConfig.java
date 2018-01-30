package com.ak.crawler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebCrawlerConfig {

	@Value("${webcrawler.doamin}")
	private String domain;
	@Value("${webcrawler.sitedepth}")
	private String sitedepth;
	@Value("${webcrawler.ignorexternalsites}")
	private String ignorexternalsites;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getSitedepth() {
		return sitedepth;
	}

	public void setSitedepth(String sitedepth) {
		this.sitedepth = sitedepth;
	}

	public String getIgnorexternalsites() {
		return ignorexternalsites;
	}

	public void setIgnorexternalsites(String ignorexternalsites) {
		this.ignorexternalsites = ignorexternalsites;
	}

}
