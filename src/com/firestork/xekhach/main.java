package com.firestork.xekhach;

import java.util.HashMap;
import java.util.Scanner;

import org.htmlcleaner.XPatherException;

import com.firestork.cleaner.Cleaner;
import com.firestork.config.ConfigurationXpath;

public class main {

	private static String html;
	private static HashMap<String, String> map;

	/**
	 * @param args
	 * @throws XPatherException
	 */
////
	public static void main(String[] args) throws XPatherException {

		ConfigurationXpath config = new ConfigurationXpath();
		map = config.insertMap("config.xml");

		Scanner scan = new Scanner(System.in);
		System.out.println("Nhập link: ");
		html = scan.nextLine();

		Cleaner cleaner = new Cleaner();
		cleaner.parserCleaner(html, map);
	}

}
