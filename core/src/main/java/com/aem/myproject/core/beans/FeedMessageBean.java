package com.aem.myproject.core.beans;

public class FeedMessageBean {

	private String title;
	private String description;
	private String pubdate;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	@Override
	public String toString() {
		return "[title=" + title + ", description=" + description + ", pubdate=" + pubdate + "]";
	}

}