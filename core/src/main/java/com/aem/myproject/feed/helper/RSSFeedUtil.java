package com.aem.myproject.feed.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RSSFeedUtil implements Comparator<Map<String, String>> {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final String SDF1 = "MM/dd/yyyy hh:mm a";
	private final String SDF2 = "yyyy-MM-dd'T'HH:mm:ss";

	public String dateConvert(String date) {
		SimpleDateFormat sdf1 = new SimpleDateFormat(SDF1);
		SimpleDateFormat sdf2 = new SimpleDateFormat(SDF2);
		date = date.substring(0, date.indexOf('+'));
		try {
			date = sdf1.format(sdf2.parse(date));
		} catch (Exception e) {
			logger.error("Date format Exception " + e);
			return new Date().toString();
		}
		return date;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, String>> sortByDate(List<Map<String, String>> feedDetailsMap) {
		Comparator<Map<String, String>> c = Collections.reverseOrder(new RSSFeedUtil());
		Collections.sort(feedDetailsMap, c);
		return feedDetailsMap;
	}

	@Override
	public int compare(Map<String, String> o1, Map<String, String> o2) {

		SimpleDateFormat formatter = new SimpleDateFormat(SDF1);
		Date date1 = new Date(), date2 = new Date();
		try {
			date1 = formatter.parse(!o1.get("pubdate").isEmpty() ? o1.get("pubdate") : new Date().toString());
			date2 = formatter.parse(!o2.get("pubdate").isEmpty() ? o2.get("pubdate") : new Date().toString());
		} catch (ParseException e) {
			logger.info("ParseException " + e);
		}
		return date1.compareTo(date2);
	}

}
