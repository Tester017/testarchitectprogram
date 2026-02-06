package com.dz.prism.utlis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.dz.core.common.utils.Reporter;
import com.microsoft.playwright.Locator;

public class TableUtlis extends WaitForLoadings {
	public static String activePanel="//*[@class='tab-pane  active' or @class='tab-content']";
	public String getAgGridTableHeaderName = "(//div[contains(@id,'#tableId')]//div[contains(@class,'ag-header-cell-label')])";
	public static String increaseFontBtn = activePanel+"//button[contains(@class,'size-inc')]";
	public static String decreaseFontBtn = activePanel+"//button[contains(@class,'size-dec')]";
	public static String excelIcon = activePanel+"//button[contains(@class,'icon-excel-file')]";
	public static String colVisBtn = activePanel+"//div[contains(@id,'col_visb_div')]";
	private static String commissionTileHeaders = "(//div[@id='equity_dash_drill_table_grosscomm_filter']//following-sibling::div//table//thead)[1]//th";
	private static String tablerowxpath = "//table[@id='equity_dash_drill_table_grosscomm']//tbody//tr";
	private static String colVisDropDownText="//following-sibling::ul//li[contains(@class,'cols')]//span";

	/**
	 * This method return table header name with position
	 * 
	 * @param headerXpath - pass table header xpath must end with th <tag>
	 * @return - map header name with position
	 * @author bhuvaneswaran
	 */
	public static LinkedHashMap<String, Integer> getTableHeaderWithPosition(String headerXpath) {
		LinkedHashMap<String, Integer> headerWithPosition = new LinkedHashMap<>();
		Locator headerRow = Reporter.getPage().locator("xpath=" + headerXpath.trim());
		int count = headerRow.count();
		//System.out.println(headerRow.allTextContents());
		String allHeaderNames = "";
		for (int i = 0; i < count; i++) {
			headerRow.nth(i).scrollIntoViewIfNeeded();
		}
		for (int i = 0; i < count; i++) {
			// headerRow.nth(i).scrollIntoViewIfNeeded();
			String headerName = headerRow.nth(i).textContent();
			// System.out.println("hearder "+headerName);
			headerWithPosition.put(headerName.trim(), (i + 1));
			if (allHeaderNames.isEmpty()) {
				allHeaderNames += headerName;
			} else {
				allHeaderNames += " ~ " + headerName;
			}
		}
		// System.out.println("allHeaderNames:"+allHeaderNames);
		return headerWithPosition;

	}

	/**
	 * This method used to get table header name with row values
	 * 
	 * @param tableHeaderWithPosition - map header with position
	 * @param tableBodyXpath          - table body xpath must end with tr <tag>
	 * @param noOfRow                 - how many row value you needs Note : Pass '0'
	 *                                in parameter noOfRows if you want all the
	 *                                Row's value present in that table
	 * @return Array list contains row value
	 * @author bhuvaneswaran
	 */
	public static ArrayList<LinkedHashMap<String, String>> getTableValue(
			LinkedHashMap<String, Integer> tableHeaderWithPosition, String tableBodyXpath, int noOfRow) {

		ArrayList<LinkedHashMap<String, String>> tableData = new ArrayList<LinkedHashMap<String, String>>();
		Locator rows = Reporter.getPage().locator("xpath=" + tableBodyXpath);
		int rowCount = rows.count();
		int loopCount = 0;
		if (noOfRow == 0 || noOfRow > rowCount) {
			loopCount = rowCount;
		} else {
			loopCount = noOfRow;
		}
		for (int i = 0; i < loopCount; i++) {
			LinkedHashMap<String, String> headerWithValue = new LinkedHashMap<>();
			for (Entry<String, Integer> headerDetails : tableHeaderWithPosition.entrySet()) {
				Locator columnLocator = Reporter.getPage().locator("xpath=" + tableBodyXpath + "[" + (i+1) + "]//td[" + headerDetails.getValue() + "]");
				String columnValue = columnLocator.textContent();
				headerWithValue.put(headerDetails.getKey(), columnValue);
			}
			tableData.add(headerWithValue);
		}
		return tableData;
	}
	
	public static ArrayList<LinkedHashMap<String, String>> getTableValues(
			LinkedHashMap<String, Integer> tableHeaderWithPosition, String tableBodyXpath, int noOfRow) {

		ArrayList<LinkedHashMap<String, String>> tableData = new ArrayList<LinkedHashMap<String, String>>();
		Locator rows = Reporter.getPage().locator("xpath=" + tableBodyXpath);
		int rowCount = rows.count();
		int loopCount = 0;
		if (noOfRow == 0 || noOfRow > rowCount) {
			loopCount = rowCount;
		} else {
			loopCount = noOfRow;
		}
		for (int i = 0; i < loopCount; i++) {
			LinkedHashMap<String, String> headerWithValue = new LinkedHashMap<>();
			for (Entry<String, Integer> headerDetails : tableHeaderWithPosition.entrySet()) {
				Locator columnLocator = Reporter.getPage().locator("xpath=" + tableBodyXpath + "[" + (i+1) + "]//td[" + headerDetails.getValue() + "]");
				String columnValue = columnLocator.getAttribute("title");
				headerWithValue.put(headerDetails.getKey(), columnValue);
			}
			tableData.add(headerWithValue);
		}
		return tableData;
	}

	/**
	 * This method is used to compare table header values
	 * 
	 * @param tableHeaderWithPosition
	 * @param headerNames             split by '~' symbol
	 * @author bhuvaneswaran
	 */
	public static void compareTableHearderColumnValue(LinkedHashMap<String, Integer> tableHeaderWithPosition,
			String headerName) {

		String column[] = headerName.trim().split("~");
		ArrayList<String> columnName = new ArrayList<String>();
		for (int i = 0; i < column.length; i++) {
			columnName.add(column[i].trim());
		}

		for (Entry<String, Integer> compColumn : tableHeaderWithPosition.entrySet()) {
			boolean columnStatus = columnName.contains(compColumn.getKey());
			if (columnStatus) {
				columnName.remove(compColumn.getKey());
			} else {
				Reporter.reportStep(compColumn.getKey() + "  - column is missing in this table", "fail");
			}
		}

		if (columnName.size() != 0) {
			Reporter.reportStep(" Table header in " + columnName.toString() + "  Extra column are displayed", "fail");
		}
	}

	public static Integer getColumnPostion(LinkedHashMap<String, Integer> tableHeaderWithPosition, String key) {
		int result;
		try {
			result = tableHeaderWithPosition.get(key.trim());
			System.out.println("result:" + result);
			return result;
		} catch (Exception e) {
			System.out.println("missing column ---->" + key);
			Reporter.reportStep("get column position of " + key + " is missing in map", "fail");
			e.printStackTrace();
			return 0;
		}

	}

	/**
	 * This method will return Header Names and its col Position
	 * 
	 * @param idValue - pass id attribute value which refers the table
	 * @return LinkedHashMap<String, Integer>
	 * @author shamilya
	 */
	public static LinkedHashMap<String, Integer> getAGTableHeaderWithPosition(String idValue, String classValue) {

		LinkedHashMap<String, Integer> headerWithPosition = new LinkedHashMap<String, Integer>();

		Locator headerLoc = null;
		Locator columnValueLoc = null;
		String[] ids = splitIDValue(idValue);
		int j = 1;
		String xpath = "div[id*=\"" + ids[0] + "\"] div.ag-center-cols-viewport";
		int widthStr = 0;
		Object widthObj = Reporter.getPage().evaluate("document.querySelector('" + xpath + "').scrollWidth");
		if (widthObj instanceof Double) {
			Double d = (Double) widthObj;
			widthStr = d.intValue();
		} else if (widthObj instanceof Integer) {
			widthStr = (Integer) widthObj;
		}

		for (int i = 1; widthStr > 0; i++) {
			headerLoc = Reporter.getPage().locator(getHeaderSelector(ids, i,classValue));
			String colXpath = getCellSelector(ids, (i + 1), j, classValue);
			columnValueLoc = Reporter.getPage().locator(colXpath);
			String headerName = headerLoc.innerText().trim();
			headerWithPosition.put(headerName, i);
			PlayWrightUtlis.scrollIntoViewIfNeeded(colXpath);
			//columnValueLoc.scrollIntoViewIfNeeded();
			if (i > 2) {
				int scrollWidth = scrollToHeader(ids[0], i);
				if (!columnValueLoc.isVisible()) {
					break;
				}
				//widthStr = widthStr - scrollWidth;
			}
		}

		scrollBack(ids[0]);
		//System.out.println("Header Map is : " + headerWithPosition);
		return headerWithPosition;

	}
	/**
	 * This method will used to get Header xpath of particular header
	 * 
	 * @param headerIndex - pass the header position which you want
	 * @return header xpath
	 * @author shamilya
	 * 
	 */
	public static String getHeaderSelector(String[] ids, int headerIndex, String classValue) {

		String selector = "";
		if (ids.length == 1) {
			selector = "//div[contains(@id,'" + ids[0] + "') and contains(@class,'" + classValue + "')] //div[@role='columnheader' and @aria-colindex='"
					+ headerIndex + "']//span[@ref='eText']";
		} else {
			selector = "//div[contains(@id,'" + ids[0] + "') and contains(@id,'"+ ids[1] + "') and contains(@class,'" + classValue + "')] //div[@role='columnheader' and @aria-colindex='"
					+ headerIndex + "']//span[@ref='eText']";
		}

		return selector;
	}

	/**
	 * This method will used to get Column value xpath of particular column Ref
	 * SuggestedEmploymentChangeTabPage class -- testTable() method
	 * 
	 * @param headerIndex - pass the Column position which you want
	 * @return Column value xpath
	 * @author shamilya
	 */
	public static String getCellSelector(String[] ids, int cellIndex, int rowIndex, String classValue) {

		String selector = "";
		if (ids.length == 1) {
			selector = "(//div[contains(@id,'" + ids[0] + "') and contains(@class,'" + classValue + "')] //div[@ref='eBodyViewport'] //div[@role='gridcell' and @aria-colindex='"
					+ cellIndex + "'])" + "[" + rowIndex + "]";
		} else {
			selector = "(//div[contains(@id,'" + ids[0] + "') and contains(@id,'" + ids[1] + "') and contains(@class,'" + classValue + "')] //div[@ref='eBodyViewport'] //div[@role='gridcell' and @aria-colindex='"
					+ cellIndex + "'])" + "[" + rowIndex + "]";
		}
		return selector;
	}
	public static String getCellSelectorForValues(String idValue, int cellIndex, int rowIndex, String classValue) {
		String selector = "(//div[contains(@id,'" + idValue + "') and contains(@class,'" + classValue +"')]//div[@class='ag-center-cols-container']//div[@role='gridcell' and @aria-colindex='"
				+ cellIndex + "'])" + "[" + rowIndex + "]";
		return selector;
	}

	public static String[] splitIDValue(String idValue) {
		String[] ids = idValue.split("~~");
		return ids;
	}

	/**
	 * This method will return no.of rows required with Header and its column value (AG Grid Table)
	 * Ref SuggestedEmploymentChangeTabPage class -- testTable() method
	 * 
	 * @param headerWithPosition - pass LinkedHashMap value which has Header name
	 *                           with col Position
	 * @param noOfRows           - no.of rows wanted
	 * @param idValue            - pass id attribute value which refers the table
	 * @return List<LinkedHashMap<String, String>>
	 * @author shamilya
	 */
	public static List<LinkedHashMap<String, String>> getAgGridTableValues(
			LinkedHashMap<String, Integer> headerWithPosition, int noOfRows, String idValue, String classValue) {

		List<LinkedHashMap<String, String>> rowsWithData = new ArrayList<LinkedHashMap<String, String>>();

		String[] ids = splitIDValue(idValue);

		String rowpath = getRowSelector(ids, 1);
		int x = Reporter.getPage().locator(rowpath).count();
		if (noOfRows == 0 || noOfRows > x) {
			noOfRows = x;
		}
		for (int i = 1; i <= noOfRows; i++) {
			LinkedHashMap<String, String> headerWithValue = new LinkedHashMap<String, String>();
			for (Entry<String, Integer> header : headerWithPosition.entrySet()) {
				String colValueXpath = getCellSelector(ids, header.getValue(), i, classValue);
				Locator colLoc = Reporter.getPage().locator(colValueXpath);
				String colValue = colLoc.textContent();
				colLoc.scrollIntoViewIfNeeded();
				headerWithValue.put(header.getKey(), colValue.trim());
			}
			scrollBack(ids[0]);
			rowsWithData.add(headerWithValue);
			//System.out.println("Rows with Data Map is : " + rowsWithData);
		}
		return rowsWithData;
	}


	/**
	 * This method used to scroll left side
	 * 
	 * @param idValue - The id attribute which refers the table
	 * @author shamilya
	 */
	public static void scrollBack(String idValue) {
		String xpath = "div[id*=\"" + idValue + "\"] div.ag-center-cols-viewport";
		String widthStr = "";
		Object widthObj = Reporter.getPage().evaluate("document.querySelector('" + xpath + "').scrollLeft");
		if (widthObj instanceof Double) {
			widthStr = Double.toString((Double) widthObj);
		} else if (widthObj instanceof Integer) {
			widthStr = Integer.toString((Integer) widthObj);
		}
		Reporter.getPage().evaluate("document.querySelector('" + xpath + "').scrollBy(- " + widthStr + ",0)");
	}

	/**
	 * This method used to scroll right side to a particular header Ref
	 * SuggestedEmploymentChangeTabPage class -- testTable() method
	 * 
	 * @param headerWithPosition - pass LinkedHashMap value which has Header name
	 *                           with col Position
	 * @param colName            - Header Name
	 * @param idValue            - The id attribute which refers the table
	 * @author shamilya
	 */
	public static void scrollToParticularHeader(LinkedHashMap<String, Integer> headerWithPosition, String colName,
			String idValue, String classValue) {

		int colPosition = headerWithPosition.get(colName);
		String[] ids = splitIDValue(idValue);
		for (int i = 1; i <= colPosition; i++) {
			Locator colLoc = Reporter.getPage().locator(getCellSelector(ids, i, 1, classValue));
			if (colLoc.isVisible()) {
				colLoc.scrollIntoViewIfNeeded();
			} else {

			}
		}
	}

	/**
	 * This method is used to get the column value of a particular header in a
	 * HashMap
	 * 
	 * Ref SuggestedEmploymentChangeTabPage class -- testTable() method
	 * 
	 * @param rowsData   - List<LinkedHashMap<String, String>>
	 * @param headerName - The header name which you want data
	 * @param rowNo      - From which row no you want the Data.
	 * @return The column value String will be returned
	 * @author shamilya
	 */
	public static String getParticularHeaderValue(List<LinkedHashMap<String, String>> rowsData, String headerName,
			int rowNo) {

		String value = "";
		try {
			LinkedHashMap<String, String> headerWithData = rowsData.get(rowNo - 1);
			value = headerWithData.get(headerName);
		} catch (NullPointerException e) {
			System.out.println("No value found in Map for header : " + headerName);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("List size is : " + rowsData.size() + "But row no is : " + rowNo);
		}
		return value;
	}

	/**
	 * This method is used to get the Value using Key from map
	 * 
	 * @param linkedhashmap variable - List<LinkedHashMap<String, String>>
	 * @param key           - String
	 * @return String
	 * @author hussains
	 */
	public static String getMapValueByKey(LinkedHashMap<String, String> map, String key) {
		try {
			if (map.containsKey(key)) {
				return map.get(key);
			} else {
				Reporter.reportStep("The key <b>" + key +  "</b> is not present in the Map. Below the keys in given Map <br>" + map.keySet() , "fail");
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("Exception occured : " + e.getMessage(), "fail");
			return "";
		}

	}

	/**
	 * This method used to get uniqe value in column
	 * 
	 * @param bodyXpath - "
	 *                  //table[contains(@id,'dataTable_activitylist')]//tbody//tr//td[2]"
	 *                  must map td position
	 * @return
	 * @author bhuvaneswaran
	 */
	public static Set<String> getColumnValue(String bodyXpath) {
		Set<String> uniqueColumnValue = new LinkedHashSet<String>();
		Locator column = Reporter.getPage().locator(bodyXpath);
		int count = Reporter.getPage().locator(bodyXpath+"//span").count();
		int iteration = 10;
		int rowcount = column.count();
		int rows = 10;
		if (rowcount < rows) {
			iteration = rowcount;
		}
		for (int i = 0; i < iteration; i++) {
			uniqueColumnValue.add(column.nth(i).textContent());
			System.out.println("--->" + uniqueColumnValue);
		}

		return uniqueColumnValue;
	}

	public static int scrollToHeader(String idValue, int headerNo) {
		String xpath = "div[id*=\"" + idValue + "\"] div.ag-center-cols-viewport";
		String eleXpath = "'div[id*=\""+ idValue + "\"] div.ag-center-cols-viewport div div div[aria-colindex=\""+ headerNo + "\"]'";
		try {
			int width = (int) Reporter.getPage().evaluate("document.querySelector(" + eleXpath + ").scrollWidth");
			Reporter.getPage().evaluate("document.querySelector('" + xpath + "').scrollBy(" + width + ",0)");
			return width;
		} catch(Exception ex) {
			//System.out.println(ex);
			return 0;
		}
	}

	/**
	 * To verify a particular header in the Linked map of HeaderWithPosition
	 * 
	 * @param headers
	 * @param particularHeader
	 * @return boolean
	 * @author lakshmi
	 */
	public boolean checkParticularHeaderValueInMap(LinkedHashMap<String, Integer> headers, String particularHeader) {
		boolean status = false;
		if (headers.containsKey(particularHeader)) {
			Reporter.reportStep("The " + particularHeader + " is present in the given map", "pass");
			status = true;
		} else {
			Reporter.reportStep("The " + particularHeader + " is not present in the given map", "fail");
		}

		return status;
	}

	/**
	 * To get all values from a map
	 * 
	 * @author lakshmi
	 * @param map
	 * @return
	 */
	public Set<String> getAllValuesFromMap(LinkedHashMap<String, String> map) {
		Set<String> keys = new HashSet<String>();
		try {
			for (Entry<String, String> map1 : map.entrySet()) {
				String val = map1.getValue();
				keys.add(val);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("Map = " + map + " has no key ", "fail");
			return null;
		}
		return keys;

	}

	/**
	 * To get all keys from a map
	 * 
	 * @author kowsalya
	 * @param map
	 * @return
	 */
	public List<String> getAllkeysFromMap(LinkedHashMap<String, String> map) {
		List<String> keys = new ArrayList<String>();
		try {
			for (Entry<String, String> map1 : map.entrySet()) {
				String val = map1.getKey();
				keys.add(val);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("Map = " + map + " has no key ", "fail");
			return null;
		}
		return keys;

	}

	public static boolean waitForVisible(Locator loc) {
		try {
			WaitForLoadings.waitForLocatorUntilVisisble(loc);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method is used to get all the values of particular column
	 * 
	 * @param map
	 * @return
	 */
	public Set<String> getAllValuesFromMapOfColumn(ArrayList<LinkedHashMap<String, String>> mapList, String colName) {
		Set<String> values = new HashSet<String>();
		try {
			for (LinkedHashMap<String, String> map : mapList) {
				String val = map.get(colName);
				values.add(val);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("The key : " + colName + " is not present Map ", "fail");
			return null;
		}
		return values;

	}

	/**
	 * This method used to select all column in table
	 * 
	 * @param tableColumnVisiblity         - '#col_visb_div' button id or xpath
	 * @param tableColumnVisiblityDropDown dropdown xpath should end with input tag
	 *                                     '//div[@id='col_visb_div']/ul/li[@data-column]/input'
	 */
	public static void selectAllCloumnVisiblity(String tableColumnVisiblity, String tableColumnVisiblityDropDown) {
		try {
			PlayWrightUtlis.waitForAppearance(tableColumnVisiblity, "Column Visibility Button");;
			PlayWrightUtlis.click(tableColumnVisiblity, "Column Visibility");
			// PlayWrightUtlis.waitForAppearance(tableColumnVisiblityDropDown);
			Locator dropDown = Reporter.getPage().locator(tableColumnVisiblityDropDown);
			int count = dropDown.count();
			for (int i = 0; i < count; i++) {
				if (!dropDown.nth(i).isDisabled()) {
					dropDown.nth(i).scrollIntoViewIfNeeded();
					if (!dropDown.nth(i).isChecked()) {
						dropDown.nth(i).click();
						Thread.sleep(1000);
					}
				}
			}
			PlayWrightUtlis.click(tableColumnVisiblity, "Close Column visibility");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<String> getColumnVisiblityDuplicates(String tableColumnVisiblity) {
		try {
			PlayWrightUtlis.waitForAppearance(tableColumnVisiblity, "Column Visibility Button");
			PlayWrightUtlis.click(tableColumnVisiblity, "Column Visibility");
			Locator dropValues = Reporter.getPage().locator(tableColumnVisiblity+colVisDropDownText);
			List<String> values = dropValues.allTextContents();
			List<String> duplicatesInList = ListUtils.getDuplicatesInList(values);
			System.out.println(values);
			System.out.println(duplicatesInList);
			return duplicatesInList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * To get all keys from a map
	 * 
	 * @author kowsalya
	 * @param key map
	 * @return
	 */
	public List<String> getAllkeysFromHeaderMap(LinkedHashMap<String, Integer> map) {
		List<String> keys = new ArrayList<String>();
		try {
			for (Entry<String, Integer> map1 : map.entrySet()) {
				String val = map1.getKey();
				keys.add(val);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("Map = " + map + " has no key ", "fail");
			return null;
		}
		return keys;

	}

	// For Revenue module - to get the values using 'data-order' attribute
	public static ArrayList<LinkedHashMap<String, String>> getAllTableValue(
			LinkedHashMap<String, Integer> tableHeaderWithPosition, String tableBodyXpath, int noOfRow) {

		ArrayList<LinkedHashMap<String, String>> tableData = new ArrayList<LinkedHashMap<String, String>>();
		Locator rows = Reporter.getPage().locator("xpath=" + tableBodyXpath);
		int rowCount = rows.count();
		int loopCount = 0;
		if (noOfRow == 0 || noOfRow > rowCount) {
			loopCount = rowCount;
		} else {
			loopCount = noOfRow;
		}
		for (int i = 0; i < loopCount; i++) {
			LinkedHashMap<String, String> headerWithValue = new LinkedHashMap<String, String>();
			// LinkedHashMap<String, String> acctsCoveredHeaders = new LinkedHashMap<>();
			for (Entry<String, Integer> headerDetails : tableHeaderWithPosition.entrySet()) {
				if (headerDetails.getKey().contains("Name") || headerDetails.getKey().contains("Product")) {
					String columnLocator = tableBodyXpath + "[" + (i + 1) + "]//td[" + headerDetails.getValue() + "]";
					String columnValue = PlayWrightUtlis.getTextContent(columnLocator, "Name");
					headerWithValue.put(headerDetails.getKey(), columnValue);
				} else {
					String columnLocator = tableBodyXpath + "[" + (i + 1) + "]//td[" + headerDetails.getValue() + "]";
					String columnValue = PlayWrightUtlis.getAttribute(columnLocator, "data-order");
					headerWithValue.put(headerDetails.getKey(), columnValue);
				}

			}
			tableData.add(headerWithValue);
		}
		return tableData;

	}

	public static void compareTableValue(String columnName, String Element,
			LinkedHashMap<String, String> headerWithValue) {
		String columnValue = getColumnValue(columnName, headerWithValue);
		// check column name is present in headerWithValue map
		if (columnValue.isEmpty()) {
			Reporter.reportStep(columnName + " column name not present in table", "pass");
			return;
		}
		// check column value is empty or not
		if (columnValue.equalsIgnoreCase("-")) {
			Reporter.reportStep(columnName + " column is empty in table", "skip");
			return;
		}
		Locator scroll = Reporter.getPage().locator(Element);
		scroll.scrollIntoViewIfNeeded();
		// PlayWrightUtlis.scrollUntilElementView(Element, "xpath");
		// check element is displayed or not
		if (PlayWrightUtlis.waitForAppearance(Element, Element)) {
			Reporter.reportStep(columnName + " field is displayed", "pass");
			String UiValue = PlayWrightUtlis.getTextContent(Element, "valuesss").trim();
			if (UiValue.isEmpty()) {
				UiValue = PlayWrightUtlis.getAttribute(Element, "value").trim();
			}
			if (UiValue.equalsIgnoreCase(columnValue) | UiValue.contains(columnValue)) {
				Reporter.reportStep(columnName + " column table value '" + columnValue + "' and Ui value '" + UiValue
						+ "' is matched", "pass");
			} else {
				Reporter.reportStep(columnName + " column table value '" + columnValue + "' and Ui value '" + UiValue
						+ "' is not matched", "fail");
			}
		} else {
			Reporter.reportStep(columnName + " field is not displayed", "fail");
		}

	}

	private static String getColumnValue(String columnName, LinkedHashMap<String, String> headerWithValue) {
		try {
			String value = headerWithValue.get(columnName).trim();
			return value;
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * This method used to scroll To Table header given Column Name
	 * 
	 * @param "String" as table header xpath =
	 *                 //div[contains(@id,'dataTable_activitylist')]//table)[1]/thead/tr/th
	 *                 and String as Column Name
	 * @author hussain
	 */
	public void scrollToTableColumnHeader(String headerXpath, String colName) {
		try {
			Integer pos = TableUtlis.getTableHeaderWithPosition(headerXpath).get(colName);
			for (int i = 0; i < pos; i++) {
				PlayWrightUtlis.scrollIntoViewIfNeeded(headerXpath + "[" + (i + 1) + "]");
			}
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void validateTableButtons() {

		Locator inc = Reporter.getPage().locator(increaseFontBtn);
		Locator dec = Reporter.getPage().locator(decreaseFontBtn);
		Locator excel = Reporter.getPage().locator(excelIcon);
		Locator col = Reporter.getPage().locator(colVisBtn);
		waitForLocatorUntilVisisble(inc);
		if (inc.isVisible() && dec.isVisible() && excel.isVisible() && col.isVisible()) {
			Reporter.reportStep("Table Icons - IncreaseFont, DecreaseFont, ExcelIcon, ColumnVisibility are displayed",
					"pass");
		} else {
			Reporter.reportStep("Table Icons are not displayed", "fail");
		}

	}

	public static void validateCurrencySymbolInTable(LinkedHashMap<String, Integer> tableHeaderWithPosition,
			String tablerowxpath, String symbolColumnName) {

		String[] columnName = symbolColumnName.split("~");
		String configCurrencySymbol = Reporter.getPropertyValue(Reporter.envProperty, "CurrencyFormat");

		for (String tableColumnNames : columnName) {
			// int columPosition=tableHeaderWithPosition.get(tableColumnNames);
			int columnPosition = getColumnPostion(tableHeaderWithPosition, tableColumnNames);
			if (columnPosition != 0) {
				String rowXpath = tablerowxpath + "//td[" + columnPosition + "]";
				Locator rowCount = Reporter.getPage().locator(rowXpath);
				int totalRowCount = rowCount.count();

				for (int i = 0; i < totalRowCount; i++) {
					if (i == 10) {
						break;
					}
					String commisionValue = rowCount.nth(i).textContent();
					if (!commisionValue.isEmpty()) {
						if (commisionValue.contains(configCurrencySymbol)) {
							Reporter.reportStep("Table Column Name :" + tableColumnNames + " value- " + commisionValue
									+ " Symbol Verified" + configCurrencySymbol, "pass");
						} else {
							Reporter.reportStep("Table Column Name :" + tableColumnNames + " value- " + commisionValue
									+ " Expected Symbol : " + configCurrencySymbol + " symbol not verified :Row no -"
									+ totalRowCount + " Table Column Name :" + tableColumnNames, "fail");
						}
					} else {
						Reporter.reportStep("Table Column Name :" + tableColumnNames
								+ " : Commission Value is Empty in Row Number- " + totalRowCount, "fail");
					}
				}

			} else {
				Reporter.reportStep("Table Column Name :" + tableColumnNames + "Not Available", "fail");
			}
		}

	}

	public static void validatePercentageSymbolInTable(LinkedHashMap<String, Integer> tableHeaderWithPosition,
			String tablerowxpath, String percentageSymbolColumnName) {

		String percentageSymbol="%";
		String[] columnName = percentageSymbolColumnName.split("~");
		for (String tableColumnNames : columnName) {
			// int columPosition=tableHeaderWithPosition.get(tableColumnNames);
			int columnPosition = getColumnPostion(tableHeaderWithPosition, tableColumnNames);
			if (columnPosition != 0) {
				String rowXpath = tablerowxpath + "//td[" + columnPosition + "]";
				Locator rowCount = Reporter.getPage().locator(rowXpath);
				int totalRowCount = rowCount.count();

				for (int i = 0; i < totalRowCount; i++) {
					if (i == 10) {
						break;
					}
					String commisionValue = rowCount.nth(i).textContent();
					if (!commisionValue.isEmpty()) {
						if (commisionValue.contains(percentageSymbol)) {
							Reporter.reportStep("Table Column Name :" + tableColumnNames + " value- " + commisionValue
									+ " Symbol Verified" + percentageSymbol, "pass");
						} else {
							Reporter.reportStep("Table Column Name :" + tableColumnNames + " value- " + commisionValue
									+ " Expected Symbol : " + percentageSymbol + " symbol not verified :Row no -"
									+ totalRowCount + " Table Column Name :" + tableColumnNames, "fail");
						}
					} else {
						Reporter.reportStep("Table Column Name :" + tableColumnNames
								+ " : Commission Value is Empty in Row Number- " + totalRowCount, "fail");
					}
				}

			} else {
				Reporter.reportStep("Table Column Name :" + tableColumnNames + "Not Available", "fail");
			}
		}

	}

	public static void selectAGTableAllColumnVisibility(String tableIdAttr) {

		String scrollHeightXpath = "div[id*=\"" + tableIdAttr + "\"] div.ag-virtual-list-viewport.ag-column-select-virtual-list-viewport.ag-focus-managed";

		int x = 2;

		for (int i=1; x > 0; i++) {
			String checkBoxXpath = getCheckBoxXpath(i, tableIdAttr);
			String nxtCol = getCheckBoxXpath(i+1, tableIdAttr);
			Locator colCheckBoxLoc = Reporter.getPage().locator(checkBoxXpath);
			boolean isVisible = colCheckBoxLoc.isVisible();
			boolean isEnabled = colCheckBoxLoc.isEnabled();
			if (isEnabled) {
				if (!colCheckBoxLoc.isChecked()) {
					colCheckBoxLoc.check();
				}
			}
			scrollToColumn(tableIdAttr, i+1);
			boolean isNxtColPresent = Reporter.getPage().locator(nxtCol).isVisible();
			//boolean isChecked = 
			if (!isNxtColPresent) {
				break;
			}
		}

	}

	public static String getCheckBoxXpath(int colNum, String tableIDAttr) {
		String tableIdValue = "//div[contains(@id,'" + tableIDAttr + "')]";
		String checkBoxXpath = tableIdValue + "//div[@class='ag-column-select-list']//div[@aria-posinset='" + colNum +"'] //input[@type='checkbox']";
		return checkBoxXpath;
	}

	public static int scrollToColumn(String idValue, int colNo) {
		String scrollHeightXpath = "div[id*=\"" + idValue + "\"] div.ag-virtual-list-viewport.ag-column-select-virtual-list-viewport.ag-focus-managed";
		String eleXpath = scrollHeightXpath + " div div[aria-posinset=\""+ colNo + "\"]";
		try {
			int width = (int) Reporter.getPage().evaluate("document.querySelector('" + eleXpath + "').scrollHeight");
			Reporter.getPage().evaluate("document.querySelector('" + scrollHeightXpath + "').scroll(0," + width + ")");
			return width;
		} catch(Exception ex) {
			//System.out.println(ex);
			return 0;
		}
	}

	public static List<String> getParticularColTableValue(int headerIndex, String tableBodyXpath) {

		List<String> colValues = new ArrayList<String>();
		
		Locator columnLocator = Reporter.getPage().locator("xpath=" + tableBodyXpath + "/td[" + headerIndex + "]");
		colValues.addAll(columnLocator.allTextContents());
		return ListUtils.trimList(colValues);

	}

	/**
	 * This method is used to get all the values of particular column
	 * 
	 * @param map
	 * @return
	 */
	public static List<String> getAllValuesFromMapOfColumn(List<LinkedHashMap<String, String>> mapList, String colName) {
		List<String> values = new ArrayList<String>();
		try {
			for (LinkedHashMap<String, String> map : mapList) {
				String val = map.get(colName);
				values.add(val);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("The key : " + colName + " is not present Map ", "fail");
			return null;
		}
		return values;

	}

	public static String getRowSelector(String[] ids, int cellIndex) {

		String selector = "";
		if (ids.length == 1) {
			selector = "(//div[contains(@id,'" + ids[0] + "')] //div[@ref='eBodyViewport'] //div[@role='gridcell' and @aria-colindex='"
					+ cellIndex + "'])";
		} else {
			selector = "(//div[contains(@id,'" + ids[0] + "') and contains(@id,'" + ids[1] + "')] //div[@ref='eBodyViewport'] //div[@role='gridcell' and @aria-colindex='"
					+ cellIndex + "'])";
		}

		return selector;
	}

	public static List<LinkedHashMap<String, String>> getAgGridTableValuesUpload(LinkedHashMap<String, Integer> headerWithPosition, int noOfRows, String idValue, String classValue) {

		List<LinkedHashMap<String, String>> rowsWithData = new ArrayList<LinkedHashMap<String, String>>();
		String[] ids = splitIDValue(idValue);
		String rowpath = getRowSelector(ids, 1);
		int x = Reporter.getPage().locator(rowpath).count();
		if (noOfRows == 0 || noOfRows > x) {
			noOfRows = x;
		}
		for (int i = 1; i <= noOfRows; i++) {
			LinkedHashMap<String, String> headerWithValue = new LinkedHashMap<String, String>();
			for (Entry<String, Integer> header : headerWithPosition.entrySet()) {
				String colValue = "";
				if (header.getKey().equals("Prism Account Name")) {
					String colValueXpath = getCellSelector(ids, header.getValue(), i, classValue) + "//input";
					colValue = Reporter.getPage().locator(colValueXpath).getAttribute("acctname");
				} else {
					String colValueXpath = getCellSelector(ids, header.getValue(), i, classValue);
					Locator colLoc = Reporter.getPage().locator(colValueXpath);
					colValue = colLoc.textContent();
				}
				headerWithValue.put(header.getKey(), colValue.trim());
			}
			scrollBack(idValue);
			rowsWithData.add(headerWithValue);
			//System.out.println("Rows with Data Map is : " + rowsWithData);
		}
		return rowsWithData;
	}
}
