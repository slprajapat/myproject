package com.aem.myproject.core.models.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.myproject.core.models.ManualFeedsModel;
import com.aem.myproject.feed.helper.RSSFeedUtil;

@Model(adaptables = SlingHttpServletRequest.class, adapters = ManualFeedsModel.class, resourceType = ManualFeedsModelImpl.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ManualFeedsModelImpl implements ManualFeedsModel {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	final protected static String RESOURCE_TYPE = "myproject/components/content/rssfeed";

	RSSFeedUtil feedUtil = new RSSFeedUtil();

	@SlingObject
	Resource resource;

	@SlingObject
	ResourceResolver resourceResolver;

	@Self
	SlingHttpServletRequest slingHttpServletRequest;

	@Override
	public List<Map<String, String>> getFeedDetailsWithMap() {
		List<Map<String, String>> feedDetailsMap = new ArrayList<Map<String, String>>();
		logger.info("Resource path : " + resource.getPath());
		try {
			Resource feedDetail = resource.getChild("feedDetails");
			if (feedDetail != null) {
				for (Resource feed : feedDetail.getChildren()) {
					Map<String, String> feedMap = new HashMap<>();
					feedMap.put("title", feed.getValueMap().get("title", String.class));
					feedMap.put("description", feed.getValueMap().get("description", String.class));
					feedMap.put("pubdate", feedUtil.dateConvert(feed.getValueMap().get("pubdate", String.class)));
					feedDetailsMap.add(feedMap);
				}
			}
		} catch (Exception e) {
			logger.info("\n ERROR while getting feed Details {} ", e.getMessage());
		}

		return feedUtil.sortByDate(feedDetailsMap);
	}

}
