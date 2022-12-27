package com.aem.myproject.core.models.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aem.myproject.core.models.ExternalRSSFeedModel;
import com.aem.myproject.feed.helper.RSSFeedParser;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
public class ExternalRSSFeedModelImplTest {

	private final AemContext aemContext = new AemContext();
	private ExternalRSSFeedModel externalRSSFeedModel;

	@Mock
	RSSFeedParser rssFeedParser;

	@BeforeEach
	void setUp() {
		aemContext.addModelsForClasses(ExternalRSSFeedModelImpl.class);
		aemContext.load().json("/com/aem/myproject/core/models/impl/ExternalRSSFeedModelImpl.json", "/component");
	}

	@Test
	void getNumberOfFeedsTest() {
		aemContext.currentResource("/component/rssfeed");
		externalRSSFeedModel = aemContext.request().adaptTo(ExternalRSSFeedModel.class);
		assertEquals(5, externalRSSFeedModel.getNumberOfFeeds());
	}

	@Test
	void getRssFeedUrlTest() {
		aemContext.currentResource("/component/rssfeed");
		externalRSSFeedModel = aemContext.request().adaptTo(ExternalRSSFeedModel.class);
		assertEquals("https://sports.ndtv.com/rss/cricket", externalRSSFeedModel.getRssFeedUrl());
	}

	@Test
	void getFeedListTest() {
		aemContext.currentResource("/component/rssfeed");
		externalRSSFeedModel = aemContext.request().adaptTo(ExternalRSSFeedModel.class);
		assertEquals("https://sports.ndtv.com/rss/cricket", externalRSSFeedModel.getRssFeedUrl());
		assertTrue(externalRSSFeedModel.getFeedList().size() > 0);
	}

	@Test
	void getFeedListEmptyTest() {
		aemContext.currentResource("/component/emptyRssfeed");
		externalRSSFeedModel = aemContext.request().adaptTo(ExternalRSSFeedModel.class);
		assertEquals("https://sports.ndtv.com/rss/cricket1", externalRSSFeedModel.getRssFeedUrl());
		assertTrue(externalRSSFeedModel.getFeedList().isEmpty());
	}
}
