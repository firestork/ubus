package com.firestork.xekhach;

import java.util.HashMap;
import java.util.Scanner;

import org.htmlcleaner.XPatherException;

import com.firestork.cleaner.Cleaner;
import com.firestork.config.ConfigurationXpath;

public class main {

	private static String html;
	private static HashMap<String, String> map, map1;
	private static HashMap<Long, TuyenXe> mapTuyenXe;
	private static HashMap<Long, NhaXe> mapNhaXe;
	private static HashMap<Long, BenXe> mapBenXe;
	private static HashMap<Long, ThanhPho> mapThanhPho;

	/**
	 * @param args
	 * @throws XPatherException
	 */

	public static void main(String[] args) throws XPatherException {

		ConfigurationXpath config = new ConfigurationXpath();
		map = config.insertMap("config.xml");
		map1 = config.insertMap("config1.xml");

		Scanner scan = new Scanner(System.in);
		System.out.println("Nhập link: ");
		// html = scan.nextLine();
		html = "http://vexere.com/vi-VN/ve-xe-khach-tu-ha-noi-di-hai-phong-124t1271.html";
		Cleaner cleaner = new Cleaner();
		cleaner.parserCleaner(html, map, map1);
		mapThanhPho = cleaner.mapThanhPho;
		mapBenXe = cleaner.mapBenXe;
		mapTuyenXe = cleaner.mapTuyenXe;
		mapNhaXe = cleaner.mapNhaXe;
		
		//xuất danh sách thành phố
		for (Long key: mapThanhPho.keySet()){
			System.out.println(mapThanhPho.get(key).getId()+"\t"+mapThanhPho.get(key).getName());
		}
		
		//danh sách bến xe
		

		//danh sách nhà xe
		
		
		//danh sách tuyến xe

	}

}
