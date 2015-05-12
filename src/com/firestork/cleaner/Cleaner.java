package com.firestork.cleaner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
import com.firestork.xekhach.LoTrinh;
import com.firestork.xekhach.NhaXe;
import com.firestork.xekhach.ThanhPho;
import com.firestork.xekhach.TuyenXe;

public class Cleaner {

	private HashMap<Long, ArrayList<TuyenXe>> mapTuyenXe;
	private HashMap<Long, NhaXe> mapNhaXe;
	private HashMap<Long, BenXe> mapBenXe;
	private HashMap<Long, ThanhPho> mapThanhPho;
	private HashMap<Long, ArrayList<LoTrinh>> mapLoTrinh;
	public FileOutputStream fileOut, fileError;
	private PrintWriter output;
	private PrintWriter outputError;
	private String time;
	private FileOutputStream out;

	

	public HashMap<Long, ArrayList<TuyenXe>> getMapTuyenXe() {
		return mapTuyenXe;
	}

	public void setMapTuyenXe(HashMap<Long, ArrayList<TuyenXe>> mapTuyenXe) {
		this.mapTuyenXe = mapTuyenXe;
	}

	public HashMap<Long, NhaXe> getMapNhaXe() {
		return mapNhaXe;
	}

	public void setMapNhaXe(HashMap<Long, NhaXe> mapNhaXe) {
		this.mapNhaXe = mapNhaXe;
	}

	public HashMap<Long, BenXe> getMapBenXe() {
		return mapBenXe;
	}

	public void setMapBenXe(HashMap<Long, BenXe> mapBenXe) {
		this.mapBenXe = mapBenXe;
	}

	public HashMap<Long, ThanhPho> getMapThanhPho() {
		return mapThanhPho;
	}

	public void setMapThanhPho(HashMap<Long, ThanhPho> mapThanhPho) {
		this.mapThanhPho = mapThanhPho;
	}


	public HashMap<Long, ArrayList<LoTrinh>> getMapLoTrinh() {
		return mapLoTrinh;
	}

	public void setMapLoTrinh(HashMap<Long, ArrayList<LoTrinh>> mapLoTrinh) {
		this.mapLoTrinh = mapLoTrinh;
	}

	public void LayDuLieu(HashMap<String, String> map,
			HashMap<String, String> map1) throws IOException, XPatherException {

		mapTuyenXe = new HashMap<>();
		mapNhaXe = new HashMap<>();
		mapBenXe = new HashMap<>();
		mapThanhPho = new HashMap<>();
		mapLoTrinh = new HashMap<>();

		ThanhPho thanhPho = new ThanhPho();
		ArrayList<ThanhPho> listThanhPho = new ArrayList<>();
		String html1 = null;

		thanhPho.setId(1);
		thanhPho.setName("An Giang");
		thanhPho.setLink("an-giang");
		listThanhPho.add(thanhPho);

		// lấy dữ liệu từ map thành phố đã tạo

		FileInputStream file = new FileInputStream("Thanhpho.text");
		Scanner input = new Scanner(file, "UTF-8");

		int count = 0;
		input.nextLine();
		while (input.hasNextLine()) {
			count = 0;
			thanhPho = new ThanhPho();
			for (String rs : input.nextLine().split("[\t]")) {

				count = count + 1;

				try {

					if (count == 1) {
						thanhPho.setId(Long.parseLong(rs));

					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
				}

				if (count == 2) {
					thanhPho.setName(rs);
				}
				if (count == 3) {
					thanhPho.setLink(rs);
				}
			}
			listThanhPho.add(thanhPho);

		}
		file.close();
		input.close();

		System.out.println(listThanhPho.size());

		//out = new FileOutputStream("TuyenXe.text");
		//output = new PrintWriter(out, true);
		for (int i = 0; i < listThanhPho.size()-1; i++) {
			for (int j = i + 1; j < listThanhPho.size(); j++) {
				// for (int j = 26; j <= 26; j++) {
				// int j = 14;
				// System.out.println(i + "->" + j);
				if (listThanhPho.get(i).getId() != listThanhPho.get(j).getId()) {
					System.out.println(listThanhPho.get(i).getId() + "->"
							+ listThanhPho.get(j).getId()+": "+listThanhPho.get(i).getName()+"->"+listThanhPho.get(j).getName());
					html1 = "http://vexere.com/vi-VN/ve-xe-khach-tu-"
							+ listThanhPho.get(i).getLink() + "-di-"
							+ listThanhPho.get(j).getLink()
							+ "-ngay-13-05-2015-1"
							+ listThanhPho.get(i).getId() + "t1"
							+ listThanhPho.get(j).getId() + "1.html";

					//System.out.println(html1);
					// fileOut = new FileOutputStream("BenXe.text");
					// fileError = new FileOutputStream("Error.text");
					// output = new PrintWriter(fileOut, true);
					// outputError = new PrintWriter(fileError, true);
					parserCleaner(html1, map, map1);

				}
			}
		}
		//out.close();
		//output.close();
		// fileOut.close();
		// fileError.close();
		// output.close();
		// }

	}

	public void parserCleaner(String html, HashMap<String, String> map,
			HashMap<String, String> map1) throws XPatherException, IOException {

		ThanhPho thanhPho = new ThanhPho();
		
		
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

		String str = null;
		boolean kt = true;
		TuyenXe tuyenXe;
		NhaXe nhaXe;
		BenXe benXeDi, benXeDen;
		LoTrinh loTrinh;

		// xpath tất cả kết quả tìm được
		String s = "//div[@class='result-list clearfix']";
		// div[@class='result-listclearfix']
		int k = 1;
		// tuyến xe đầu tiên tìm đc
		int leng = node.evaluateXPath(s + "/div[1]").length;

		// xây dựng map tuyến xe, nhà xe, bến xe.
		while (leng > 0) {
			//System.out.println("Tuyen: " + k);
			ArrayList<TuyenXe> listTuyen = new ArrayList<>(); 
			ArrayList<LoTrinh> listLoTrinh = new ArrayList<>();
			tuyenXe = new TuyenXe();
			ArrayList<Long> startTime = new ArrayList<>();
			ArrayList<Long> stopTime = new ArrayList<>();
			nhaXe = new NhaXe();
			nhaXe.listRoute = new ArrayList<>();
			benXeDi = new BenXe();
			thanhPho = new ThanhPho();
			thanhPho.listBen = new ArrayList<>();
			loTrinh = new LoTrinh();

			String xpath;
			Object[] obj = null;
			try {

				// id thành phố đi
				xpath = s + "/div[" + k + "]/@fromstate-id";
				if (node.evaluateXPath(xpath).length == 0) {
					break;
				}
				else
				{

					obj = node.evaluateXPath(xpath);
					thanhPho.setId(Long.parseLong(obj[0].toString()));
					benXeDi.setIdThanhPho(Long.parseLong(obj[0].toString()));

					/*
					 * // tên thành phố đi
					 * 
					 * obj = node.evaluateXPath(s + "/div[" + k + "]" +
					 * map.get("xekhach.fromstop-name"));
					 * thanhPho.setName(StringEscapeUtils.unescapeHtml(obj[0]
					 * .toString()));
					 */

					// id bến đi
					obj = node.evaluateXPath(s + "/div[" + k + "]"
							+ map.get("xekhach.fromstop-id"));
					tuyenXe.setFromStopID(Long.parseLong(obj[0].toString()));
					benXeDi.setId(Long.parseLong(obj[0].toString()));
					loTrinh.setFromStopID(Long.parseLong(obj[0].toString()));
				

					// tên bến đi

					for (Object o : node.evaluateXPath(s + "/div[" + k + "]"
							+ map1.get("xekhach.depart-station"))) {
						benXeDi.setName(StringEscapeUtils
								.unescapeHtml(((TagNode) o).getText()
										.toString()));
					}

					// địa chỉ bến đi

					for (Object o : node.evaluateXPath(s + "/div[" + k + "]"
							+ map1.get("xekhach.depart-station-add"))) {
						str = StringEscapeUtils.unescapeHtml(((TagNode) o)
								.getText().toString());
						str = str.trim();
						str = str.replaceAll("\\s+", " ");
						benXeDi.setAddress(str);
					}

					/*
					 * // thêm bến đi vào thành phố đi
					 * 
					 * if (mapThanhPho.get(benXeDi.getIdThanhPho()) == null) {
					 * thanhPho.listBen.add(Long.parseLong(obj[0].toString()));
					 * } else { thanhPho.listBen =
					 * mapThanhPho.get(benXeDi.getIdThanhPho()).listBen; kt =
					 * true;
					 * 
					 * // kiểm tra xem bến có trong thành phố đó chưa? for (int
					 * l = 0; l < thanhPho.listBen.size(); l++) { if
					 * (thanhPho.listBen.get(l) == Long.parseLong(obj[0]
					 * .toString())) { kt = false; break; }
					 * 
					 * }
					 * 
					 * if (kt) {
					 * thanhPho.listBen.add(Long.parseLong(obj[0].toString()));
					 * }
					 * 
					 * }
					 */

					if (mapBenXe.get(benXeDi.getId()) == null) {
						mapBenXe.put(benXeDi.getId(), benXeDi);

						// System.out.println(benXeDi.getId() + "\t"
						// + benXeDi.getIdThanhPho() + "\t"
						// + benXeDi.getName() + "\t" + benXeDi.getAddress());
					}

					thanhPho = new ThanhPho();
					thanhPho.listBen = new ArrayList<>();
					benXeDen = new BenXe();

					// id thành phố đến
					obj = node.evaluateXPath(s + "/div[" + k + "]/@tostate-id");
					thanhPho.setId(Long.parseLong(obj[0].toString()));
					benXeDen.setIdThanhPho(Long.parseLong(obj[0].toString()));

					// tên thành phố đến
					obj = node.evaluateXPath(s + "/div[" + k + "]"
							+ map.get("xekhach.tostop-name"));
					thanhPho.setName(StringEscapeUtils.unescapeHtml(obj[0]
							.toString()));

					// id bến đến
					obj = node.evaluateXPath(s + "/div[" + k + "]"
							+ map.get("xekhach.tostop-id"));
					tuyenXe.setTostopID(Long.parseLong(obj[0].toString()));
					benXeDen.setId(Long.parseLong(obj[0].toString()));
					loTrinh.setToStopID(Long.parseLong(obj[0].toString()));
					
					
					// tên bến đến

					for (Object o : node.evaluateXPath(s + "/div[" + k + "]"
							+ map1.get("xekhach.destination-station"))) {
						benXeDen.setName(StringEscapeUtils
								.unescapeHtml(((TagNode) o).getText()
										.toString()));
					}

					// địa chỉ bến đến

					for (Object o : node.evaluateXPath(s + "/div[" + k + "]"
							+ map1.get("xekhach.destination-station-add"))) {
						str = StringEscapeUtils.unescapeHtml(((TagNode) o)
								.getText().toString());
						str = str.trim();
						str = str.replaceAll("\\s+", " ");
						benXeDen.setAddress(str);
					}

					/*
					 * // thêm bến đến vào thành phố đến
					 * 
					 * if (mapThanhPho.get(benXeDen.getIdThanhPho()) == null) {
					 * thanhPho.listBen.add(Long.parseLong(obj[0].toString()));
					 * } else { thanhPho.listBen = mapThanhPho
					 * .get(benXeDen.getIdThanhPho()).listBen; kt = true;
					 * 
					 * // kiểm tra xem bến có trong thành phố đó chưa? for (int
					 * l = 0; l < thanhPho.listBen.size(); l++) { if
					 * (thanhPho.listBen.get(l) == Long.parseLong(obj[0]
					 * .toString())) { kt = false; break; }
					 * 
					 * }
					 * 
					 * if (kt) {
					 * thanhPho.listBen.add(Long.parseLong(obj[0].toString()));
					 * }
					 * 
					 * }
					 */

					if (mapBenXe.get(benXeDen.getId()) == null) {
						mapBenXe.put(benXeDen.getId(), benXeDen);

						// System.out
						// .println(benXeDen.getId() + "\t"
						// + benXeDen.getIdThanhPho() + "\t"
						// + benXeDen.getName() + "\t"
						// + benXeDen.getAddress());
					}

					nhaXe = new NhaXe();

					// id nhà xe

					obj = node.evaluateXPath(s + "/div[" + k + "]"
							+ map.get("xekhach.operator-id"));
					nhaXe.setId(Long.parseLong(obj[0].toString()));
					tuyenXe.setOperatorID(Long.parseLong(obj[0].toString()));

					// tên nhà xe

					for (Object o : node.evaluateXPath(s + "/div[" + k + "]"
							+ map1.get("xekhach.transporter-name"))) {
						nhaXe.setTransportName(StringEscapeUtils
								.unescapeHtml(((TagNode) o).getText()
										.toString()));
					}

					if (mapNhaXe.get(nhaXe.getId()) == null) {
						mapNhaXe.put(nhaXe.getId(), nhaXe);

						// System.out.println(nhaXe.getId() + "\t"
						// +nhaXe.getTransportName());
					}
					
					// id lộ trình
					
					obj = node.evaluateXPath(s + "/div[" + k + "]"
							+ map.get("xekhach.schedule-id"));
					loTrinh.setScheduleID(Long.parseLong(obj[0].toString()));
					tuyenXe.setScheduleID(Long.parseLong(obj[0].toString()));
					
					//chi tiết lộ trình
					
					GetSchedule gets = new GetSchedule(loTrinh.getScheduleID(), benXeDi.getId(), benXeDen.getId());
					loTrinh.setStr(gets.schedule());
					
					// add vào map lộ trình
					
					if (mapLoTrinh.get(loTrinh.getScheduleID()) == null) {
						 listLoTrinh.add(loTrinh);

					 }
					 else {
						 listLoTrinh = mapLoTrinh.get(loTrinh.getScheduleID());
						 listLoTrinh.add(loTrinh);
					 }
					
					mapLoTrinh.put(loTrinh.getScheduleID(), listLoTrinh);
					// id tuyến xe

					obj = node.evaluateXPath(s + "/div[" + k + "]"
							+ map.get("xekhach.route-id"));
					tuyenXe.setRouteID(Long.parseLong(obj[0].toString()));
					loTrinh.setRouterID(Long.parseLong(obj[0].toString()));
					

					// get departure datetime
					
					obj = node.evaluateXPath(s + "/div[" + k + "]"
							+ map.get("xekhach.route-time"));
					
					GetPhoneNumber get = new GetPhoneNumber(tuyenXe.getRouteID(), benXeDi.getId(), benXeDen.getId(), obj[0].toString());
					//nhaXe.setPhoneNumber(get.PhoneNumber());
					tuyenXe.setPhone(get.PhoneNumber());

				

					// tiện ích của tuyến xe
					ArrayList<String> listBenifit = new ArrayList<>();

					try {
						for (Object o : node.evaluateXPath(s + "/div[" + k
								+ "]" + map1.get("xekhach.seat-info"))) {
							listBenifit.add(StringEscapeUtils
									.unescapeHtml(((TagNode) o).getText()
											.toString()));
						}
						for (Object o : node.evaluateXPath(s + "/div[" + k
								+ "]" + map1.get("xekhach.benifit"))) {
							listBenifit.add(StringEscapeUtils
									.unescapeHtml(((TagNode) o).getText()
											.toString()));
						}
					} catch (Exception e) {
						System.out.println("Lỗi tiện ích");
					}
					tuyenXe.setBenifit(listBenifit);

					// thời gian đi tuyến xe
					if (node.evaluateXPath(s + "/div[" + k + "]"
							+ map1.get("xekhach.start-time")).length != 0) {

						for (Object o : node.evaluateXPath(s + "/div[" + k
								+ "]" + map1.get("xekhach.start-time"))) {
							time = StringEscapeUtils.unescapeHtml(((TagNode) o)
									.getText().toString());
							// System.out.println(time);

						}
					} else {
						for (Object o : node
								.evaluateXPath(s
										+ "/div["
										+ k
										+ "]"
										+ "//div[@class='depart-info-col fl-l clearfix']/div[@class='input-set fl-l']/span")) {
							time = StringEscapeUtils.unescapeHtml(((TagNode) o)
									.getText().toString());
						}
						//System.out.println(time);
					}

					tuyenXe.setStartTime(listTime(time, startTime));

					// thời gian đến tuyến xe
					if (node.evaluateXPath(s + "/div[" + k + "]"
							+ map1.get("xekhach.stop-time")).length != 0) {

						for (Object o : node.evaluateXPath(s + "/div[" + k
								+ "]" + map1.get("xekhach.stop-time"))) {
							time = StringEscapeUtils.unescapeHtml(((TagNode) o)
									.getText().toString());
							// System.out.println(time);

						}
					} else {

						for (Object o : node
								.evaluateXPath(s
										+ "/div["
										+ k
										+ "]"
										+ "//div[@class='destination-info-col fl-l']/div[@class='input-set fl-l']/span")) {
							time = StringEscapeUtils.unescapeHtml(((TagNode) o)
									.getText().toString());
							//System.out.println(time);
						}

					}
					tuyenXe.setStopTime(listTime(time, stopTime));

					
					
					 if (mapTuyenXe.get(tuyenXe.getRouteID()) == null) {
						 listTuyen.add(tuyenXe);

					 }
					 else {
						 listTuyen = mapTuyenXe.get(tuyenXe.getRouteID());
						 listTuyen.add(tuyenXe);
					 }
					 mapTuyenXe.put(tuyenXe.getRouteID(), listTuyen);
//					System.out.println(tuyenXe.getRouteID()
//							+ "\t"
//							+ tuyenXe.getOperatorID()
//						
//							+ "\t"
//							+ tuyenXe.getFromStopID()
//							+ "\t"
//							+ tuyenXe.getTostopID()
//							+ "\t"
//							+tuyenXe.listBenefit()
//							+ "\t"
//							+ tuyenXe.listFromTime()
//							+ "\t"
//							+ tuyenXe.listStopTime()+"\t"+tuyenXe.getPhone());
					// }

					
					
					
						
				}
				
			} catch (XPatherException e) {
				System.out.println("Không có kết quả " + e.getMessage());
				// outputError.println(html);
			}
			k = k + 1;
			leng = node.evaluateXPath(s + "/div[" + k + "]").length;
			//System.out.println("div["+k+"], leng: "+node.evaluateXPath(s + "/div[" + k + "]").length);
		}

	}

	public static ArrayList<Long> listTime(String time, ArrayList<Long> list) {
		time = time.trim();
		// ArrayList<Long> list = new ArrayList<>();
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

		//System.out.println(listFromTime(list));
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
