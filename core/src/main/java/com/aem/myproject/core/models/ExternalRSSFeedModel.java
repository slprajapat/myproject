package com.aem.myproject.core.models;

import java.util.List;

import com.aem.myproject.core.beans.FeedMessageBean;

public interface ExternalRSSFeedModel {
	
	public String getRssFeedUrl();

	public int getNumberOfFeeds();
	
	public List<FeedMessageBean> getFeedList();
	
	public boolean isEmpty();

}
