package com.firestork.cleaner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.lang.StringEscapeUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.firestork.xekhach.BenXe;
import com.firestork.xekhach.NhaXe;
import com.firestork.xekhach.ThanhPho;
import com.firestork.xekhach.TuyenXe;

public class Cleaner {

	public HashMap<Long, TuyenXe> mapTuyenXe;
	public HashMap<Long, NhaXe> mapNhaXe;
	public HashMap<Long, BenXe> mapBenXe;
	public HashMap<Long, ThanhPho> mapThanhPho;

	public void parserCleaner(String html, HashMap<String, String> map,
			HashMap<String, String> map1) throws XPatherException {

		mapTuyenXe = new HashMap<>();
		mapNhaXe = new HashMap<>();
		mapBenXe = new HashMap<>();
		mapThanhPho = new HashMap<>();
		String html1 = null;
		Scanner scan = new Scanner(System.in);
		System.out.println("Nhập link: ");
		html1 = scan.nextLine();
		while ((!html1.equals("k"))&& (!html1.equals(""))) {

			HtmlCleaner cleaner = new HtmlCleaner();
			CleanerProperties props = cleaner.getProperties();
			props.setAllowHtmlInsideAttributes(false);
			props.setAllowMultiWordAttributes(true);
			props.setRecognizeUnicodeChars(true);
			props.setOmitComments(true);

			TagNode node = null;
			try {
				node = cleaner.clean(new URL(html1));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			boolean kt = true;
			TuyenXe tuyenXe;
			NhaXe nhaXe;
			BenXe benXe;
			ThanhPho thanhPho;

			// xpath tất cả kết quả tìm được
			String s = "//div[@class='result-list clearfix']";
			// div[@class='result-list clearfix']
			int i = 1;
			// tuyến xe đầu tiên tìm đc
			int leng = node.evaluateXPath(s + "/div[1]").length;
			System.out.println(leng);
		

			// kiểm tra kết quả trống (ko có tuyến xe nào)
			
			//if node.evaluateXPath(s+"/div[1]").
			
			// xây dựng map tuyến xe, nhà xe, bến xe.
			while (leng > 0) {

				tuyenXe = new TuyenXe();
				nhaXe = new NhaXe();
				nhaXe.listRoute = new ArrayList<>();
				benXe = new BenXe();
				thanhPho = new ThanhPho();
				thanhPho.listBen = new ArrayList<>();

				Object[] obj = null;
				/*
				 * // id nhà xe Object[] obj = node.evaluateXPath(s + "/div[" +
				 * i + "]" + map.get("xekhach.operator-id"));
				 * tuyenXe.setOperatorID(Long.parseLong(obj[0].toString()));
				 * 
				 * // id tuyen xe obj = node.evaluateXPath(s + "/div[" + i + "]"
				 * + map.get("xekhach.route-id"));
				 * tuyenXe.setRouteID(Long.parseLong(obj[0].toString()));
				 * 
				 * // thêm tuyến vào nhà xe if
				 * (mapNhaXe.get(tuyenXe.getOperatorID()) == null) {
				 * nhaXe.listRoute.add(Long.parseLong(obj[0].toString())); }
				 * else { nhaXe.listRoute =
				 * mapNhaXe.get(tuyenXe.getOperatorID()).listRoute;
				 * nhaXe.listRoute.add(Long.parseLong(obj[0].toString())); }
				 * 
				 * // tên nhà xe obj = node.evaluateXPath(s + "/div[" + i + "]"
				 * + map.get("xekhach.operator-name"));
				 * nhaXe.setTransportName(obj[0].toString());
				 */

				// id thành phố đi

				obj = node.evaluateXPath(s + "/div[" + i + "]/@fromstate-id");

				thanhPho.setId(Long.parseLong(obj[0].toString()));
				benXe.setIdThanhPho(Long.parseLong(obj[0].toString()));

				// tên thành phố đi
				obj = node.evaluateXPath(s + "/div[" + i + "]"
						+ map.get("xekhach.fromstop-name"));
				thanhPho.setName(StringEscapeUtils.unescapeHtml(obj[0]
						.toString()));

				// id bến đi
				obj = node.evaluateXPath(s + "/div[" + i + "]"
						+ map.get("xekhach.fromstop-id"));
				tuyenXe.setFromStopID(Long.parseLong(obj[0].toString()));
				benXe.setId(Long.parseLong(obj[0].toString()));
				// thêm bến đi vào thành phố đi

				if (mapThanhPho.get(benXe.getIdThanhPho()) == null) {
					thanhPho.listBen.add(Long.parseLong(obj[0].toString()));
				} else {
					thanhPho.listBen = mapThanhPho.get(benXe.getIdThanhPho()).listBen;
					kt = true;

					// kiểm tra xem bến có trong thành phố đó chưa?
					for (int j = 0; j < thanhPho.listBen.size(); j++) {
						if (thanhPho.listBen.get(j) == Long.parseLong(obj[0]
								.toString())) {
							kt = false;
							break;
						}

					}

					if (kt) {
						thanhPho.listBen.add(Long.parseLong(obj[0].toString()));
					}

				}

				mapBenXe.put(benXe.getId(), benXe);
				if (mapThanhPho.get(thanhPho.getId()) == null) {

					mapThanhPho.put((thanhPho.getId()), thanhPho);
					System.out.println(thanhPho.getId() + "\t"
							+ thanhPho.getName());
				}

				// id thành phố đến
				thanhPho = new ThanhPho();
				thanhPho.listBen = new ArrayList<>();
				benXe = new BenXe();

				obj = node.evaluateXPath(s + "/div[" + i + "]/@tostate-id");
				thanhPho.setId(Long.parseLong(obj[0].toString()));
				benXe.setIdThanhPho(Long.parseLong(obj[0].toString()));

				// tên thành phố đến
				obj = node.evaluateXPath(s + "/div[" + i + "]"
						+ map.get("xekhach.tostop-name"));
				thanhPho.setName(StringEscapeUtils.unescapeHtml(obj[0]
						.toString()));

				// id bến đến
				obj = node.evaluateXPath(s + "/div[" + i + "]"
						+ map.get("xekhach.tostop-id"));
				tuyenXe.setFromStopID(Long.parseLong(obj[0].toString()));
				benXe.setId(Long.parseLong(obj[0].toString()));

				// thêm bến đến vào thành phố đến

				if (mapThanhPho.get(benXe.getIdThanhPho()) == null) {
					thanhPho.listBen.add(Long.parseLong(obj[0].toString()));
				} else {
					thanhPho.listBen = mapThanhPho.get(benXe.getIdThanhPho()).listBen;
					kt = true;

					// kiểm tra xem bến có trong thành phố đó chưa?
					for (int j = 0; j < thanhPho.listBen.size(); j++) {
						if (thanhPho.listBen.get(j) == Long.parseLong(obj[0]
								.toString())) {
							kt = false;
							break;
						}

					}

					if (kt) {
						thanhPho.listBen.add(Long.parseLong(obj[0].toString()));
					}

				}
				// System.out.println(benXe.getId());

				mapBenXe.put(benXe.getId(), benXe);
				if (mapThanhPho.get(thanhPho.getId()) == null) {

					mapThanhPho.put(thanhPho.getId(), thanhPho);
					System.out.println(thanhPho.getId() + "\t"
							+ thanhPho.getName());
				}

				// map1
				/*for (Object k : node
						.evaluateXPath("//div[@class='result-list clearfix']/div["
								+ i + "]")) {
					for (String keymap : map1.keySet()) {
						// System.out.println(keymap);
						switch (keymap) {

						case "xekhach.route-name":
							for (Object o : ((TagNode) k).evaluateXPath(map1
									.get(keymap))) {
								tuyenXe.setRouteName(((TagNode) o).getText()
										.toString());
							}
							break;
						case "xekhach.start-time":
							for (Object o : ((TagNode) k).evaluateXPath(map1
									.get(keymap))) {
								tuyenXe.setStartTime(((TagNode) o).getText()
										.toString());
								// System.out.println(tuyenXe.getStartTime());
							}
							break;
						case "xekhach.stop-time":
							for (Object o : ((TagNode) k).evaluateXPath(map1
									.get(keymap))) {
								tuyenXe.setStopTime(((TagNode) o).getText()
										.toString());
							}
							break;
						case "xekhach.total-time":
							for (Object o : ((TagNode) k).evaluateXPath(map1
									.get(keymap))) {
								tuyenXe.setTotalTime(((TagNode) o).getText()
										.toString());
							}
							break;
						case "xekhach.price":
							for (Object o : ((TagNode) k).evaluateXPath(map1
									.get(keymap))) {
								tuyenXe.setPrice(((TagNode) o).getText()
										.toString());
							}
							break;
						case "xekhach.seat-info":
							for (Object o : ((TagNode) k).evaluateXPath(map1
									.get(keymap))) {
								tuyenXe.setSeatInfo(((TagNode) o).getText()
										.toString());
							}
							break;
						case "xekhach.benifit":
							for (Object o : ((TagNode) k).evaluateXPath(map1
									.get(keymap))) {
								tuyenXe.setBenifit(((TagNode) o).getText()
										.toString());
								// System.out.println(((TagNode) o).getText());
							}
							break;
						default:
							break;
						}

					}
					mapTuyenXe.put(tuyenXe.getRouteID(), tuyenXe);
					mapNhaXe.put(nhaXe.getId(), nhaXe);
					i = i + 1;
					leng = node.evaluateXPath(s + "/div[" + i + "]").length;
				}*/
				i = i + 1;
				leng = node.evaluateXPath(s + "/div[" + i + "]").length;
				
			}

			System.out.println("Nhập link: ");
			html1 = scan.nextLine();
		}

	}
}
