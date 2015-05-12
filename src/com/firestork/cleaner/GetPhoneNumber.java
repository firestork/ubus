package com.firestork.cleaner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class GetPhoneNumber {

	private long routeId;
	private long fromStopId;
	private long toStopId;
	private String departureDateTime;

	
	
	public GetPhoneNumber(long routeId, long fromStopId, long toStopId,
			String departureDateTime) {
		super();
		this.routeId = routeId;
		this.fromStopId = fromStopId;
		this.toStopId = toStopId;
		this.departureDateTime = departureDateTime;
	}


	public String PhoneNumber() throws XPatherException {

		String phone = "";
		String html = "http://vexere.com/vi-VN/Booking/GetCallCentreInfoWithDateTime/routeId/"
				+ routeId
				+ "/fromStopId/"
				+ fromStopId
				+ "/toStopId/"
				+ toStopId
				+ "/departureDateTime/"
				+ departureDateTime
				+ "/busTicketStatus/0";

		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		props.setAllowHtmlInsideAttributes(false);
		props.setAllowMultiWordAttributes(true);
		props.setRecognizeUnicodeChars(true);
		props.setOmitComments(true);

		TagNode node = null;

		try {
			node = cleaner.clean(new URL(html));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Object o : node.evaluateXPath("//a[@class='orange-link']")) {
			phone = phone + "\t" + ((TagNode) o).getText().toString();
		}
		return phone.trim();
	}

}
