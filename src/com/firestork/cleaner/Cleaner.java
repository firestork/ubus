package com.firestork.cleaner;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.firestork.xekhach.TuyenXe;

public class Cleaner {

	public void parserCleaner(String html, HashMap<String, String> map)
			throws XPatherException {
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		props.setAllowHtmlInsideAttributes(false);
		props.setAllowMultiWordAttributes(true);
		props.setRecognizeUnicodeChars(true);
		props.setOmitComments(true);

		TagNode node = null;
		URL url;
		try {
			url = new URL(html);
			node = cleaner.clean(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TuyenXe tuyenXe = new TuyenXe();

		// ArrayList<TuyenXe> tuyenXes = new ArrayList<TuyenXe>();
		int i = 1;
		int leng = 1;
		while (leng > 0) {
			leng = node
					.evaluateXPath("//div[@class='result-list clearfix']/div["
							+ i + "]").length;
			for (Object k : node
					.evaluateXPath("//div[@class='result-list clearfix']/div["
							+ i + "]")) {
				for (String keymap : map.keySet()) {
					// System.out.println(keymap);
					for (Object o : ((TagNode) k)
							.evaluateXPath(map.get(keymap))) {
						System.out.println(keymap + ": "
								+ ((TagNode) o).getText());
					}
				}
				i = i + 1;
			}
		}

	}

}
