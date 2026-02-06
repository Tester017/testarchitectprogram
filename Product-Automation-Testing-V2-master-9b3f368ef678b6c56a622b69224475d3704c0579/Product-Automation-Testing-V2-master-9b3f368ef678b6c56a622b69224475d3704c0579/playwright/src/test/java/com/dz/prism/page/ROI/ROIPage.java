package com.dz.prism.page.ROI;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.dz.core.common.utils.Reporter;
import com.dz.prism.constant.ProductConstant;
import com.dz.prism.utlis.PlayWrightUtlis;
import com.microsoft.playwright.Locator;

public class ROIPage extends Reporter{

	private String emptytabledata="(//table[contains(@id,'dataTable_ROI_result')]//tbody//tr//td[@class='dataTables_empty'])";
	private String tableXpth="//*[@id='roi_report']//table[contains(@id,'dataTable_ROI_result')]";
	private String unableToFetch = "(//*[text()='Unable to fetch Data'])[1]";
	private String roiCountChartIcon ="//button[@data-type='count']";
	private String clickOnChartButton="//button[@data-type='roi_chart']";
	private String roiPopupIcon ="//button[@title='Popup']";
	private String roiWeightsPopupPage ="//*[contains(@class,'title') and contains(text(),'ROI Weights')]";

	private String overemptytabledata ="//*[contains(@class,'over inner-content')]//table[contains(@id,'over-roi-table') or contains(@class,'roi-table dataTable')]//tbody//tr//td[@class='dataTables_empty']";
	private String idealemptytabledata="//*[contains(@class,'ideal inner-content')]//table[contains(@id,'ideal-roi-table') or contains(@class,'roi-table dataTable')]//tbody//tr//td[@class='dataTables_empty']";
	private String underemptytabledata="//*[contains(@class,'under inner-content')]//table[contains(@id,'under-roi-table') or contains(@class,'roi-table dataTable')]//tbody//tr//td[@class='dataTables_empty']";
	private String overTableXpath ="//*[contains(@class,'over inner-content')]//div[@class='dataTables_scrollBody']//table[contains(@id,'over-roi-table') or contains(@class,'roi-table dataTable')]";
	private String idealTableXpath="//*[contains(@class,'ideal inner-content')]//div[@class='dataTables_scrollBody']//table[contains(@id,'ideal-roi-table') or contains(@class,'roi-table dataTable')]";
	private String underTableXpath="//*[contains(@class,'under inner-content')]//div[@class='dataTables_scrollBody']//table[contains(@id,'under-roi-table') or contains(@class,'roi-table dataTable')]";

	private String roiCountChartXpath ="//*[contains(@id,'roi-count-chart')]//*[contains(@id,'scatterChartId')]";
	private String roiWeightsPopupPageTableXpath ="//*[contains(@id,'roi_report')]//table[contains(@id,'category_list_table')]";
	private String popupemptytabledata ="//*[contains(@id,'roi_report')]//table[contains(@id,'category_list_table')]//tbody//tr//td[@class='dataTables_empty']";
	
	private String roiModuleTableXpath="//*[@id='roi-desc-table']";
	private String rowsXpath="//*[@id='dataTable_ROI_result']//tbody//tr";	
	private String headerXpath="//*[@id='roi_report']//div[@class='dataTables_scroll']//div[@class='dataTables_scrollHead']//table//tr[3]//th";
	private String roiTableSearchXpath="//*[@id='roi-desc-table']//div[@id='dataTable_ROI_result_filter']//input";
	private String superAccToggleSwitchXpath="//*[contains(@class,'super-account-switch')]";
	
	private String clickROITableBtnXpath="//button[@data-type='roi_table']";
	//Chart Page Xpaths
	private String clickChartPageTypeSelector="//*[@id='heatFilter_type_Selector']";
	private String clickChartPageTypeSelectorDrpdown="//*[@id='heatFilter_type_Selector']/option";
	private String clickChartPageTypeSelected="//*[@id='heatFilter_type_Selected']";
	private String clickChartPageTypeSelectedDrpdown="//*[@id='heatFilter_type_Selected']/option";
	private String chartOverWidgetClientName="//*[@id='over-roi-table']//tbody//tr[@class='group']//td";
	
	
	//Count Page Xpaths
	private String clickCountPageTypeSelector="//*[@id='scatterFilter_type_Selector']";
	private String clickCountPageTypeSelectorDrpdown="//*[@id='scatterFilter_type_Selector']/option";
	private String clickCountPageTypeSelected="//*[@id='scatterFilter_type_Selected']";
	private String filterAccountNameSearch="//*[@id='roiScatterSearchAcct']";
	private String accountNameSearchDrpdown="//*[@id='roiScatterSearchAcct']//following::ul//span";
	private String countPageScatteredChartDisp="//*[@id='scatterChartId']";
	private String scatteredChartTableHeaderXpath="//*[@id='scatter_chart_table']//table//tr[2]//th[@tabindex='0']";
	private String scatteredChartTableRowsXpath="//*[@id='scatter_chart_table']//table//tr[@class='odd']";
	private String scatteredChartRefreshBtn="//*[@id='scatterFilter']//button";
	
	public boolean isDataDisplayedInROITable() {
		boolean dispStatus=false;
		String noDataXpath = emptytabledata;
		String tableXpath = tableXpth;

		Locator loc = getPage().locator(tableXpath);
		Locator noDataLoc = getPage().locator(noDataXpath);
		if (loc.isVisible()) {
			reportStep("ROI Module Table is displayed", "pass");
			dispStatus = true;
		}else { 
			reportStep("ROI Module Table is not displayed: ", "fail");
			dispStatus=false;
		}
		if (noDataLoc.isVisible()) {
			String value = noDataLoc.textContent();
			if (value.equalsIgnoreCase("No Data Available")||value.equalsIgnoreCase("No data available in table")) {
				reportStep("No Data Available in ROI Table" , "pass");
				dispStatus=false;
			} else {
				boolean status = getPage().locator(unableToFetch).isVisible();
				if (status) {
					reportStep("Unable to fetch Data is displayed ROI Module Table" , "fail");
					dispStatus=false;
				}
			}
		}
		return dispStatus;
	}

	public boolean isDataDisplayedInROIChartIcon_OverTable() {
		boolean dispStatus=false;
		String noDataXpath = overemptytabledata;
		String tableXpath = overTableXpath;

		Locator loc = getPage().locator(tableXpath);
		Locator noDataLoc = getPage().locator(noDataXpath);
		if (loc.isVisible()) {
			reportStep("ROI Module Table -Chart Icon page -> Over Table is displayed", "pass");
			dispStatus = true;
		}else { 
			reportStep("ROI Module Table -Chart Icon page ->Over Table is not displayed: ", "fail");
			dispStatus=false;
		}
		if (noDataLoc.isVisible()) {
			String value = noDataLoc.textContent();
			if (value.equalsIgnoreCase("No Data Available")||value.equalsIgnoreCase("No data available in table")) {
				reportStep("No Data Available in ROI Module Table -Chart Icon page ->Over Table Table" , "pass");
				dispStatus=false;
			} else {
				boolean status = getPage().locator(unableToFetch).isVisible();
				if (status) {
					reportStep("Unable to fetch Data is displayed ROI Module Table -Chart Icon page ->Over Table " , "fail");
					dispStatus=false;
				}
			}
		}
		return dispStatus;
	}


	public boolean isDataDisplayedInROIChartIcon_IdealTable() {
		boolean dispStatus=false;
		String noDataXpath = idealemptytabledata;
		String tableXpath = idealTableXpath;

		Locator loc = getPage().locator(tableXpath);
		Locator noDataLoc = getPage().locator(noDataXpath);
		if (loc.isVisible()) {
			reportStep("ROI Module Table -Chart Icon page -> Ideal Table is displayed", "pass");
			dispStatus = true;
		}else { 
			reportStep("ROI Module Table -Chart Icon page ->Ideal Table is not displayed: ", "fail");
			dispStatus=false;
		}
		if (noDataLoc.isVisible()) {
			String value = noDataLoc.textContent();
			if (value.equalsIgnoreCase("No Data Available")||value.equalsIgnoreCase("No data available in table")) {
				reportStep("No Data Available in ROI Module Table - Chart Icon page ->Ideal Table" , "pass");
				dispStatus=false;
			} else {
				boolean status = getPage().locator(unableToFetch).isVisible();
				if (status) {
					reportStep("Unable to fetch Data is displayed ROI Module Table - Chart Icon page ->Ideal Table" , "fail");
					dispStatus=false;
				}
			}
		}
		return dispStatus;
	}

	public boolean isDataDisplayedInROIChartIcon_UnderTable() {
		boolean dispStatus=false;
		String noDataXpath = underemptytabledata;
		String tableXpath = underTableXpath;

		Locator loc = getPage().locator(tableXpath);
		Locator noDataLoc = getPage().locator(noDataXpath);
		if (loc.isVisible()) {
			reportStep("ROI Module Table -Chart Icon page -> Under Table is displayed", "pass");
			dispStatus = true;
		}else { 
			reportStep("ROI Module Table -Chart Icon page ->Ideal Table is not displayed: ", "fail");
			dispStatus=false;
		}
		if (noDataLoc.isVisible()) {
			String value = noDataLoc.textContent();
			if (value.equalsIgnoreCase("No Data Available")||value.equalsIgnoreCase("No data available in table")) {
				reportStep("No Data Available in ROI Module Table - Chart Icon page ->Under Table" , "pass");
				dispStatus=false;
			} else {
				boolean status = getPage().locator(unableToFetch).isVisible();
				if (status) {
					reportStep("Unable to fetch Data is displayed ROI Module Table - Chart Icon page ->Under Table" , "fail");
					dispStatus=false;
				}
			}
		}
		return dispStatus;
	}

	public boolean clickChartIcon() {
		waitForAppearance(clickOnChartButton);
		threadSleep(1);
		click(clickOnChartButton, "Roi Module ->Roi Chart Icon Button");
		waitForTrianleLoading();
		waitForSpinnerLoad();
		threadSleep(1);
		boolean contains = getAttribute(clickOnChartButton, "class").contains("active");
		if (contains) {
			reportStep("ROI Module Table -Chart Icon page is  displayed ", "pass");
			return true;
		} else {
			reportStep("ROI Module Table -Chart Icon page is not displayed ", "fail");
			return false;
		}
	}

	public boolean clickCountIcon() {
		threadSleep(1);
		click(roiCountChartIcon, "Roi Module ->Roi Count Icon Button");
		waitForTrianleLoading();
		waitForSpinnerLoad();
		boolean contains = getAttribute(roiCountChartIcon, "class").contains("active");
		if (contains) {
			reportStep("ROI Module Table -Count Icon chart page is  displayed ", "pass");
			return true;
		} else {
			reportStep("ROI Module Table -Count Icon chart page is not displayed ", "fail");
			return false;
		}
	}
	public boolean clickRoiPopupIcon() {
		threadSleep(1);
		click(roiPopupIcon, "Roi Module ->Roi Popup Icon ");
		waitForTrianleLoading();
		waitForSpinnerLoad();
		boolean contains = isVisible(roiWeightsPopupPage);
		if (contains) {
			reportStep("ROI Module Table -Popup Icon page is  displayed ", "pass");
			return true;
		} else {
			reportStep("ROI Module Table -Popup Icon page is not displayed ", "fail");
			return false;
		}
	}

	public void isDataDisplayedInROICountChart() {
		String pieChartXpath = roiCountChartXpath;

		Locator loc = getPage().locator(pieChartXpath);
		if (loc.isVisible()) {
			String value = loc.innerText();
			reportStep("ROI Module Table -Count Icon page -> Count chart is displayed Values are : " + value, "pass");
		} else {
			boolean status = getPage().locator(unableToFetch).isVisible();
			if (status) {
				reportStep("Unable to fetch Data is displayed in ROI Module Table -Count Icon page -> Count chart" , "fail");
			}
		}
	}
	
	public boolean isDataDisplayedInROIWeightsPopUpPageTable() {
		boolean dispStatus=false;
		String noDataXpath = popupemptytabledata;
		String tableXpath = roiWeightsPopupPageTableXpath;
		Locator loc = getPage().locator(tableXpath);
		Locator noDataLoc = getPage().locator(noDataXpath);
		if (loc.isVisible()) {
			reportStep("ROI Module Table -popup Icon page Table is displayed", "pass");
			dispStatus = true;
		}else { 
			reportStep("ROI Module Table -popup Icon page Table is not displayed: ", "fail");
			dispStatus=false;
		}
		if (noDataLoc.isVisible()) {
			String value = noDataLoc.textContent();
			if (value.equalsIgnoreCase("No Data Available")||value.equalsIgnoreCase("No data available in table")) {
				reportStep("No Data Available in ROI Module Table -popup Icon page Table" , "pass");
				dispStatus=false;
			} else {
				boolean status = getPage().locator(unableToFetch).isVisible();
				if (status) {
					reportStep("Unable to fetch Data is displayed in ROI Module Table -popup Icon page ->" , "fail");
					dispStatus=false;
				}
			}
		}
		return dispStatus;
	}

	public boolean isROIV1V2TableDisp() {
		if(waitForAppearance(roiModuleTableXpath,5000)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Used to get all the column header names of Activity table ROI - Table
	 * @return - LinkedHashMap<String, Integer>
	 * @author Salman
	 */
	public LinkedHashMap<String, Integer> getHeaderNamesWithPos() {
		LinkedHashMap<String, Integer> headers = getTableHeaderWithPosition(headerXpath);
		return headers;
	}
	
	public ArrayList<LinkedHashMap<String, String>> getROITableData(LinkedHashMap<String, Integer> headers,
			int noOfRows) {
		waitForAppearance(roiModuleTableXpath);
		if (isDataDisplayedInROITable()) {
			ArrayList<LinkedHashMap<String, String>> tableData = getTableValue(headers, rowsXpath, noOfRows);
			return tableData;
		} else {
			return null;
		}
	}
	
	public boolean enterTableSearchROI(String clientName) {
		if(waitForAppearance(roiTableSearchXpath,5000)) {
			PlayWrightUtlis.type(roiTableSearchXpath, clientName, "ROI Table search");
			return true;
		}else {
			reportStep("ROI Table search field is not displayed", "fail");
			return false;
		}
	}
	
	public boolean isROITableEmpty() {
		if(waitForAppearance(emptytabledata,5000)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isSuperAccToggleDispForSalesRoles() {
		if(waitForAppearance(superAccToggleSwitchXpath,5000)) {
			reportStep("Consolidated Super Accounts toggle switch is displayed for: "+ProductConstant.role, "fail");
			return false;
		}else {
			reportStep("Consolidated Super Accounts toggle switch is not displayed for: "+ProductConstant.role , "pass");
			return true;
		}
	}
	
	public List<String> getChartPageFilterTypesInROI() {
		List<String> chartFilterTypes=new ArrayList<String>();
		if(waitForAppearance(clickChartPageTypeSelector,5000)) {
			click(clickChartPageTypeSelector, "Chart page type selector filter");
			Locator loc=getPage().locator(clickChartPageTypeSelectorDrpdown);
			if(waitForAppearance(clickChartPageTypeSelector,5000)) {
				chartFilterTypes=loc.allTextContents();
				return chartFilterTypes;
			}else {
				reportStep("Chart page type selector dropdown options is not displayed in ROI module", "fail");
				return null;
			}
		}else {
			reportStep("Chart page type selector dropdown filed is not displayed in ROI module", "fail");
			return null;
		}	
	}
	
	public List<String> getCountPageFilterTypesInROI() {
		List<String> countFilterTypes=new ArrayList<String>();
		if(waitForAppearance(clickCountPageTypeSelector,5000)) {
			click(clickCountPageTypeSelector, "Count page type selector filter");
			Locator loc=getPage().locator(clickCountPageTypeSelectorDrpdown);
			if(waitForAppearance(clickCountPageTypeSelector,5000)) {
				countFilterTypes=loc.allTextContents();
				return countFilterTypes;
			}else {
				reportStep("Count page type scattered chart selector dropdown options is not displayed in ROI module", "fail");
				return null;
			}
		}else {
			reportStep("Count page type scattered chart selector dropdown filed is not displayed in ROI module", "fail");
			return null;
		}	
	}
	
	public String getFirstIntrCountInROI() {
		closePrismUpdateToastMsg();
		if (isDataDisplayedInROITable()) {
			String headerName = getPropertyValue(roiProperty, "ROI_TABLE_SRT_INTERACTION_NAME_HEADER");;
			String xpath = rowsXpath + "[1]//td["+ getColumnPostion(getHeaderNamesWithPos(), headerName) +"]";
			xpath.toLowerCase().trim();
			Locator loc = getPage().locator(xpath);
			if (waitForAppearance(xpath)) {
				String firstSRTInteraction = loc.textContent();
				return firstSRTInteraction.trim();
			} else {
				return null;
			}
		} else {
			reportStep("ROI Table doesn't contains data", "fail");
			return null;
		}
	}
	
	public boolean chooseChartPageSelectedFiltrName(String filterOption) {
		Locator loc=getPage().locator(clickChartPageTypeSelected);
		if(waitForAppearance(clickChartPageTypeSelected, 5000)) {
			loc.selectOption(filterOption);
			reportStep("Selected Type filter option is selected successfully in chart page as expected", "pass");
			return true;
		}else {
			reportStep("Selected Type filter is not displayed", "fail");
			return false;
		}
	}
	
	public boolean chooseChartPageSelectorFiltrName(String filterOption) {
		Locator loc=getPage().locator(clickChartPageTypeSelector);
		if(waitForAppearance(clickChartPageTypeSelector, 5000)) {
			loc.selectOption(filterOption);
			reportStep("Selector Type filter option is selected successfully in chart page as expected", "pass");
			return true;
		}else {
			reportStep("Selector filter is not displayed", "fail");
			return false;
		}
	}
	
	public boolean chooseCountPageSelectedFiltrName(String filterOption) {
		Locator loc=getPage().locator(clickCountPageTypeSelected);
		if(waitForAppearance(clickCountPageTypeSelected, 5000)) {
			loc.selectOption(filterOption);
			reportStep("Selected Type filter option is selected successfully in chart page as expected", "pass");
			return true;
		}else {
			reportStep("Selected Type filter is not displayed", "fail");
			return false;
		}
	}
	
	public boolean chooseCountPageSelectorFiltrName(String filterOption) {
		Locator loc=getPage().locator(clickCountPageTypeSelector);
		if(waitForAppearance(clickCountPageTypeSelector, 5000)) {
			loc.selectOption(filterOption);
			reportStep("Selector Type filter option is selected successfully in chart page as expected", "pass");
			return true;
		}else {
			reportStep("Selector filter is not displayed", "fail");
			return false;
		}
	}
	
	public List<String> chartPageOverWidgetClientName() {
		Locator loc=getPage().locator(chartOverWidgetClientName);
		if(waitForAppearance(chartOverWidgetClientName,5000)) {
			List<String> values=loc.allTextContents();
			return values;
		}else {
			reportStep("Chart page over widget client name is not displayed", "fail");
			return null;
		}
	}
	
	public boolean enterAccountNameSearchCountPage(String value) {
		if(waitForAppearance(filterAccountNameSearch,5000)) {
			PlayWrightUtlis.type(filterAccountNameSearch, value, "ROI Count Page search filter");
			return true;
		}else {
			return false;
		}
	}
	
	public boolean clickCountAccNameSearchDrpdown() {
		if(waitForAppearance(accountNameSearchDrpdown,5000)) {
			click(accountNameSearchDrpdown, "Account Name search dropdown");
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkScatteredChartDisp() {
		Locator loc=getPage().locator(countPageScatteredChartDisp);
		String value=loc.textContent();
		if(value.contains("No Data Available")) {
			reportStep("ROI table has commission values but Scattered chart is not displayed", "fail");
			return false;
		}else {
			return true;
		}
		
	}
	
	public ArrayList<LinkedHashMap<String, String>> getROIScatteredChartTableData(LinkedHashMap<String, Integer> headers,
			int noOfRows) {
		if (isScatteredChartTableHasData()) {
			ArrayList<LinkedHashMap<String, String>> tableData = getTableValue(headers, scatteredChartTableRowsXpath, noOfRows);
			return tableData;
		} else {
			return null;
		}
	}
	
	public LinkedHashMap<String, Integer> getScatteredTableHeaderNamesWithPos() {
		LinkedHashMap<String, Integer> headers = getTableHeaderWithPosition(scatteredChartTableHeaderXpath);
		return headers;
	}
	
	public boolean clickOnCountPageRefreshBtn() {
		if(waitForAppearance(scatteredChartRefreshBtn,5000)) {
			click(scatteredChartRefreshBtn, "Count Page Scattered Chart Refresh Button");
			return true;
		}else {
			reportStep("Count Page Scattered Chart Refresh Button is not displayed", "fail");
			return false;
		}
	}     
	
	public boolean isScatteredChartTableHasData() {
		if(waitForAppearance(scatteredChartTableRowsXpath, 5000)) {
			return true;
		}else {
			reportStep("Count page scattered chart table doesn't contains data", "fail");
			return false;
		}
	}
	
	public boolean clickROITableBtn() {
		if(waitForAppearance(clickROITableBtnXpath,5000)) {
			click(clickROITableBtnXpath, "ROI Table Button");
			return true;
		}else {
			reportStep("ROI Table Button is not displayed", "fail");
			return false;
		}
	}
}
