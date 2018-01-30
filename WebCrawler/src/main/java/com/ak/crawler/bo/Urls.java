package com.ak.crawler.bo;

import java.util.HashSet;

public class Urls {
	private HashSet<String> loc = new HashSet();
	private HashSet<String> image = new HashSet();

	public HashSet<String> getLoc() {
		return loc;
	}

	public void setLoc(HashSet<String> loc) {
		this.loc = loc;
	}

	public HashSet<String> getImage() {
		return image;
	}

	public void setImage(HashSet<String> image) {
		this.image = image;
	}

}
