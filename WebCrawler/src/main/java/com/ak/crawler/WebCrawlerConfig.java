package com.ak.crawler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

public class WebCrawlerConfig {

	@Value("${webcrawler.domain}")
	// @NotNull(message = "Domain name is mandatory")
	// @Pattern()
	private String domain;
	@Value("${webcrawler.sitedepth}")
	private String sitedepth;
	@Value("${webcrawler.ignorexternalsites}")
	private String ignorexternalsites;
	@Value("${webcrawler.output.filepath}")
	private String outputFilePath;

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

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}
}
