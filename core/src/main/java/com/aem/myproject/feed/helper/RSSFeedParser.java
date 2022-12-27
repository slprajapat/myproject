package com.aem.myproject.feed.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.myproject.core.beans.FeedMessageBean;

public class RSSFeedParser {
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String ITEM = "item";
	static final String PUB_DATE = "updated";
	private final Logger logger = LoggerFactory.getLogger(getClass());
	final URL url;
	List<FeedMessageBean> list;
	RSSFeedUtil feedUtil = new RSSFeedUtil();

	public RSSFeedParser(String feedUrl) {
		try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			logger.error("MalformedURLException " + e);
			throw new RuntimeException(e);
		}
	}

	public List<FeedMessageBean> readFeed(int numberOfNews) {

		try {
			list = new ArrayList<FeedMessageBean>();
			int item = 0;
			boolean isFeedHeader = true;
			// Set header values intial to the empty string
			String description = "";
			String title = "";
			String pubdate = "";

			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Setup a new eventReader
			InputStream in = read();

			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			// read the XML document
			do {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();
					switch (localPart) {
					case ITEM:
						event = eventReader.nextEvent();
						break;
					case TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case DESCRIPTION:
						description = getCharacterData(event, eventReader);
						break;
					case PUB_DATE:
						pubdate = getCharacterData(event, eventReader);
						break;
					}
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart() == (ITEM)) {

						FeedMessageBean message = new FeedMessageBean();
						message.setTitle(title);
						message.setDescription(description);
						message.setPubdate(feedUtil.dateConvert(pubdate));
						// adding bean in to list
						list.add(message);
						item++;
						event = eventReader.nextEvent();
						continue;
					}
				}
			} while (numberOfNews > item);
		} catch (XMLStreamException e) {
			logger.error("XMLStreamException " + e);
			return Collections.emptyList();
		}
		return list;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}

	private InputStream read() {
		try {
			return url.openStream();
		} catch (IOException e) {
			logger.error("IOException " + e);
			// throw new RuntimeException(e);
		}
		return null;
	}
	
}
