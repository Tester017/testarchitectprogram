package com.dz.prism.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TableUtils {
	/**
	 * Return Table rows size
	 * @param selectorName - upto tr
	 * @param byVal
	 * @return
	 * @throws InterruptedException
	 * @author ilayaraja
	 */
	public static int countOfTableBodyRows(String selectorName, String byVal) {
		try {
			Thread.sleep(2000);
			List<WebElement> rows = null;
			switch (byVal.toLowerCase()) {
			case "id":
				rows = SeleniumUtils.webDriver.findElements(By.id(selectorName));
				break;
			case "xpath":
				rows = SeleniumUtils.webDriver.findElements(By.xpath(selectorName));
				break;
			case "class":
				rows = SeleniumUtils.webDriver.findElements(By.className(selectorName));
				break;
			}
			return rows.size();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Any of given column matched
	 * @param xpathOfTableBody
	 * @param compareValue
	 * @return
	 * @author ilayaraja
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray tableColumCompare(String xpathOfTableBody, Map<String, String> compareValue) {
		try {
			String source = "<table>"
					+ SeleniumUtils.webDriver.findElement(By.xpath(xpathOfTableBody)).getAttribute("innerHTML")
					+ "<table>";
			Document doc = Jsoup.parse(source, "UTF-8");
			Elements headerRow = doc.getElementsByTag("tr").get(0).getElementsByTag("th");
			JSONArray dataArray = new JSONArray();

			for (Element rowElmt : doc.getElementsByTag("tr")) {
				Elements cols = rowElmt.getElementsByTag("th");
				if (cols.size() == 0)
					cols = rowElmt.getElementsByTag("td");
				JSONObject valObj = new JSONObject();
				for (int i = 0; i < cols.size(); i++) {
					if (compareValue != null && compareValue.containsKey(headerRow.get(i).text())) {
						if (cols.get(i).text().equalsIgnoreCase(compareValue.get(headerRow.get(i).text()))) {
							valObj.put(headerRow.get(i).text(), cols.get(i).text());
						}
					}
				}
				if (valObj != null && !valObj.isEmpty()) {
					dataArray.add(valObj);
				}
			}
			return dataArray;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * All the columns data will be compared
	 * @param xpathOfTableBody
	 * @param compareValue
	 * @return
	 * @author ilayaraja
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray allColumnCompare(String xpathOfTableBody, Map<String, String> compareValue) {
		try {
			String source = "<table>"
					+ SeleniumUtils.webDriver.findElement(By.xpath(xpathOfTableBody)).getAttribute("innerHTML")
					+ "<table>";
			Document doc = Jsoup.parse(source, "UTF-8");
			Elements headerRow = doc.getElementsByTag("tr").get(0).getElementsByTag("th");
			Map<String, Integer> headerPos = new HashMap<String, Integer>();
			for (int i = 0; i < headerRow.size(); i++) {
				headerPos.put(headerRow.get(i).text(), i);
			}
			JSONArray dataArray = new JSONArray();
			for (Element rowElmt : doc.getElementsByTag("tr")) {
				Elements cols = rowElmt.getElementsByTag("th");
				if (cols.size() == 0)
					cols = rowElmt.getElementsByTag("td");
				JSONObject valObj = new JSONObject();
				int checkCount = 0;
				boolean allow = false;
				for (Entry<String, String> entry : compareValue.entrySet()) {
					if (headerPos.containsKey(entry.getKey())
							&& cols.get(headerPos.get(entry.getKey())).text().equalsIgnoreCase(entry.getValue())) {
						checkCount++;
					} else {
						continue;
					}
				}
				allow = (compareValue.size()==checkCount) ? true:false;
				if (compareValue != null && allow) {
					for (int i = 0; i < cols.size(); i++) {
						valObj.put(headerRow.get(i).text(), cols.get(i).text());
					}
				}
				if (valObj != null && !valObj.isEmpty()) {
					dataArray.add(valObj);
				}
			}
			return dataArray;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * it will return all the table data as JSON
	 * @param xpathOfTableBody
	 * @return
	 * @author ilayaraja
	 */
	@SuppressWarnings("unchecked")
	public static JSONArray getAllTableDataAsJSON(String xpathOfTableBody) {
		try {
			String source = "<table>"
					+ SeleniumUtils.webDriver.findElement(By.xpath(xpathOfTableBody)).getAttribute("innerHTML")
					+ "<table>";
			Document doc = Jsoup.parse(source, "UTF-8");
			Elements headerRow = doc.getElementsByTag("tr").get(0).getElementsByTag("th");
			JSONArray dataArray = new JSONArray();
			int count = 0;
			for (Element rowElmt : doc.getElementsByTag("tr")) {
				if(count  == 0) {
					count++;
					continue;
				}
				Elements cols = rowElmt.getElementsByTag("th");
				if (cols.size() == 0)
					cols = rowElmt.getElementsByTag("td");
				JSONObject valObj = new JSONObject();
				for (int i = 0; i < cols.size(); i++) {
					valObj.put(headerRow.get(i).text(), cols.get(i).text());
				}
				dataArray.add(valObj);
			}
			return dataArray;
		} catch (Exception e) {
			return null;
		}
	}
}