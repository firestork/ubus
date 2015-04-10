package com.firestork.xekhach;

import java.util.HashMap;
import java.util.Scanner;

import org.htmlcleaner.XPatherException;

import com.firestork.cleaner.Cleaner;
import com.firestork.config.ConfigurationXpath;

public class main {

	private static String html;
	private static HashMap<String, String> map,map1;
	private static HashMap<Long, TuyenXe> mapTuyenXe;
	private static HashMap<Long, NhaXe> mapNhaXe;

	/**
	 * @param args
	 * @throws XPatherException
	 */
////
	public static void main(String[] args) throws XPatherException {

		ConfigurationXpath config = new ConfigurationXpath();
		map = config.insertMap("config.xml");
		map1 = config.insertMap("config1.xml");

		Scanner scan = new Scanner(System.in);
		System.out.println("Nhập link: ");
		//html = scan.nextLine();
		html= "http://vexere.com/vi-VN/ve-xe-khach-tu-ha-noi-di-hai-phong-ngay-10-04-2015-124t1271.html";
		Cleaner cleaner = new Cleaner();
		cleaner.parserCleaner(html, map,map1);
		mapNhaXe=cleaner.mapNhaXe;
		
	}

}
