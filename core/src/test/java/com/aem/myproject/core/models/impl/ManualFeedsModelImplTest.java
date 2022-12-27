package com.aem.myproject.core.models.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aem.myproject.core.models.ManualFeedsModel;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
public class ManualFeedsModelImplTest {

	private final AemContext aemContext = new AemContext();

	private ManualFeedsModel manualFeedsModel;

	@BeforeEach
	void setUp() {
		aemContext.addModelsForClasses(ManualFeedsModelImpl.class);
		aemContext.load().json("/com/aem/myproject/core/models/impl/ManualFeedsModel.json", "/component");
	}

	@Test
	void getFeedDetailsWithMapTest() {
		aemContext.currentResource("/component/rssfeed");
		manualFeedsModel = aemContext.request().adaptTo(ManualFeedsModel.class);
		assertEquals(2, manualFeedsModel.getFeedDetailsWithMap().size());
		//assertEquals("Cameron Green After Being Picked", manualFeedsModel.getFeedDetailsWithMap().get(0).get("title"));
	}

	@Test
	void getFeedDetailsWithZeroMapSizeTest() {
		aemContext.currentResource("/component/empty_rssfeed");
		manualFeedsModel = aemContext.request().adaptTo(ManualFeedsModel.class);
		assertEquals(0, manualFeedsModel.getFeedDetailsWithMap().size());
	}

	@Test
	void getFeedDetailsWithZeroMapSizeExceptionTest() {
		aemContext.currentResource("/component/rssfeedException");
		manualFeedsModel = aemContext.request().adaptTo(ManualFeedsModel.class);
	}

}
