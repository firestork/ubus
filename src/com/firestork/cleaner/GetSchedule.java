package com.firestork.cleaner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.lang.StringEscapeUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class GetSchedule {

	private long scheduleId;
	private long fromStopId;
	private long toStopId;

	public GetSchedule(long scheduleId, long fromStopId, long toStopId) {
		super();
		this.scheduleId = scheduleId;
		this.fromStopId = fromStopId;
		this.toStopId = toStopId;
	}

	public String schedule() throws XPatherException {
		String html = "http://vexere.com/vi-VN/Booking/GetBoardingPointTable/"
				+ scheduleId + "/" + fromStopId + "/" + toStopId
				+ "/False?index=0&getFromBookingPage=false";

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

		String str = "[";
		int i = 0;
		for (Object o : node.evaluateXPath("//td")) {

			i = i + 1;
			if (i % 4 == 0) {
				str = str
						+ listFromTime(listTime(((TagNode) o).getText()
								.toString())) + "]\t";
				// System.out.println(str);
			} else {
				if (i % 5 == 0) {
					str = str
							+ "["
							+ StringEscapeUtils.unescapeHtml(
									((TagNode) o).getText().toString()).trim()+"\t";
				} else {

					str = str
							+ StringEscapeUtils.unescapeHtml(
									((TagNode) o).getText().toString()).trim()+"\t";

				}
			}
		}

		return str.trim();
	}

	public static ArrayList<Long> listTime(String time) {
		time = time.trim();
		ArrayList<Long> list = new ArrayList<>();
		int dem = 0;
		long tgu = 0;
		long tg = 0;
		// System.out.println(time);
		// System.out.println(Long.parseLong("20"));
		for (String thoigian : time.split("[\n]")) {
			thoigian = thoigian.trim();
			thoigian = thoigian.replaceAll("\\s+", " ");
			dem = 0;
			for (String q : thoigian.split("[:]")) {
				if (q != null) {

					dem = dem + 1;
					try {

						if (dem == 1) {
							tgu = Long.parseLong(q) * 3600;
						} else if (dem == 2) {
							tg = Long.parseLong(q) * 60;
						}

					} catch (Exception e) {

					}
					// System.out.println(q);
				}

			}
			list.add(tgu + tg);
			// System.out.println(thoigian + ": " + tgu + tg);
		}

		// System.out.println(listFromTime(list));
		return list;

	}

	public static String listFromTime(ArrayList<Long> startTime) {
		String str = "[";
		for (int i = 0; i < startTime.size(); i++) {
			if (i == startTime.size() - 1) {
				str = str + startTime.get(i);
			} else {
				str = str + startTime.get(i) + ",";
			}
		}
		str = str + "]";
		return str;
	}

}
