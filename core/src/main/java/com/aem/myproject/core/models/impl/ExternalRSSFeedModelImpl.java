package com.aem.myproject.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.myproject.core.beans.FeedMessageBean;
import com.aem.myproject.core.models.ExternalRSSFeedModel;
import com.aem.myproject.feed.helper.RSSFeedParser;

@Model(adaptables = SlingHttpServletRequest.class, adapters = ExternalRSSFeedModel.class, resourceType = ExternalRSSFeedModelImpl.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ExternalRSSFeedModelImpl implements ExternalRSSFeedModel {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	final protected static String RESOURCE_TYPE = "myproject/components/content/rssfeed";

	@SlingObject
	private Resource currentResource;

	@SlingObject
	private ResourceResolver resourceResolver;

	@ValueMapValue
	private String rssFeedUrl;

	@ValueMapValue
	private int numberOfFeeds;

	private List<FeedMessageBean> feedList = new ArrayList<FeedMessageBean>();
	RSSFeedParser parser;
	
	public boolean flag;
	
	@PostConstruct
	protected void init() {
		logger.info("init method starts..");
		if(StringUtils.isNotBlank(rssFeedUrl)) {
			parser = new RSSFeedParser(rssFeedUrl);
			feedList = parser.readFeed(numberOfFeeds);
		} else {
			flag = false;
		}
	}

	@Override
	public List<FeedMessageBean> getFeedList() {
		if (feedList != null) {
			return new ArrayList<FeedMessageBean>(feedList);
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public String getRssFeedUrl() {
		return rssFeedUrl;
	}

	@Override
	public int getNumberOfFeeds() {
		return numberOfFeeds;
	}

	public void setRssFeedUrl(String rssFeedUrl) {
		this.rssFeedUrl = rssFeedUrl;
	}

	public void setNumberOfFeeds(int numberOfFeeds) {
		this.numberOfFeeds = numberOfFeeds;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}
