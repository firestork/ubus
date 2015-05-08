package com.firestork.xekhach;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
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
	 * @throws IOException
	 */

	public static void main(String[] args) throws XPatherException, IOException {

		ConfigurationXpath config = new ConfigurationXpath();
		map = config.insertMap("config.xml");
		map1 = config.insertMap("config1.xml");

		// Scanner scan = new Scanner(System.in);
		// System.out.println("Nhập link: ");
		// html = scan.nextLine();
		// html =
		// "http://vexere.com/vi-VN/ve-xe-khach-tu-ha-noi-di-hai-phong-124t1271.html";
		Cleaner cleaner = new Cleaner();
		cleaner.LayDuLieu(map, map1);
		// cleaner.parserCleaner(html, map, map1);
		mapThanhPho = cleaner.getMapThanhPho();
		mapBenXe = cleaner.getMapBenXe();
		mapTuyenXe = cleaner.getMapTuyenXe();
		mapNhaXe = cleaner.getMapNhaXe();

		ArrayList<Long> arr = new ArrayList<>();
		// xuất danh sách thành phố ~> done

		/*
		 * FileOutputStream out = new FileOutputStream("Thanhpho.text");
		 * 
		 * PrintWriter output = new PrintWriter(out, true); for (Long key :
		 * mapThanhPho.keySet()) {
		 * 
		 * arr = mapThanhPho.get(key).getListBen();
		 * System.out.println(mapThanhPho.get(key).getId() + "\t" +
		 * mapThanhPho.get(key).getName());
		 * output.println(mapThanhPho.get(key).getId() + "\t" +
		 * mapThanhPho.get(key).getName());
		 * 
		 * 
		 * for (int i = 0; i < arr.size(); i++) {
		 * System.out.println(arr.get(i)); } }
		 * 
		 * out.close(); output.close();
		 */

		// danh sách bến xe

		FileOutputStream out = new FileOutputStream("BenXe.text");

		PrintWriter output = new PrintWriter(out, true);
		for (Long key : mapBenXe.keySet()) {

			System.out.println(mapBenXe.get(key).getId() + "\t"
					+ mapBenXe.get(key).getIdThanhPho() + "\t"
					+ mapBenXe.get(key).getName() + "\t"
					+ mapBenXe.get(key).getAddress());
			output.println(mapBenXe.get(key).getId() + "\t"
					+ mapBenXe.get(key).getIdThanhPho() + "\t"
					+ mapBenXe.get(key).getName() + "\t"
					+ mapBenXe.get(key).getAddress());

		}

		out.close();
		output.close();

		out = new FileOutputStream("NhaXe.text");
		output = new PrintWriter(out, true);

		for (Long key : mapNhaXe.keySet()) {
			System.out.println(mapNhaXe.get(key).getId() + "\t"
					+ mapNhaXe.get(key).getTransportName());
			output.println(mapNhaXe.get(key).getId() + "\t"
					+ mapNhaXe.get(key).getTransportName());
		}
		out.close();
		output.close();

		/*out = new FileOutputStream("TuyenXe.text");
		output = new PrintWriter(out, true);

		for (Long key : mapTuyenXe.keySet()) {
			System.out.println(mapTuyenXe.get(key).getRouteID() + "\t"
					+ mapTuyenXe.get(key).getOperatorID() + "\t"
					+ mapTuyenXe.get(key).getFromStopID() + "\t"
					+ mapTuyenXe.get(key).getTostopID() + "\t"
					+ mapTuyenXe.get(key).listBenefit() + "\t"
					+ mapTuyenXe.get(key).listFromTime() + "\t"
					+ mapTuyenXe.get(key).listStopTime());
			
			output.println(mapTuyenXe.get(key).getRouteID() + "\t"
					+ mapTuyenXe.get(key).getOperatorID() + "\t"
					+ mapTuyenXe.get(key).getFromStopID() + "\t"
					+ mapTuyenXe.get(key).getTostopID() + "\t"
					+ mapTuyenXe.get(key).listBenefit() + "\t"
					+ mapTuyenXe.get(key).listFromTime() + "\t"
					+ mapTuyenXe.get(key).listStopTime());
		}
		out.close();
		output.close();*/

	}

}
