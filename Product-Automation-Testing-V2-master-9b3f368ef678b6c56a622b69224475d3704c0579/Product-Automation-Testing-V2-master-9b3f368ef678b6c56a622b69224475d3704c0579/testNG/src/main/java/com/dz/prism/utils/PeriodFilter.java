package com.dz.prism.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.By;
import com.dz.core.common.utils.Reporter;
import com.fasterxml.jackson.databind.deser.ValueInstantiator.Gettable;

public class PeriodFilter extends SeleniumUtils {
	
	public static String FourWeak = "4WK";
	public static String YTD = "YTD";
	public static String MTD = "MTD";
	public static String Today = "Today";
 
	public static String YesterDay = "Yesterday";
	public static String TodayDate = getDateWithFormat(SeleniumUtils.periodPattern);
	public static String LTM = "LTM";
	// public static String preYear = "2019";
	public static String preYear = "Prev. Year";
	public static String preWeak = "Prev. Week";
	public static String preMonth = "Prev. Month";
	public static String cusRange = "Custom Range";
	public static String filterDropDown = "//*[contains(@class,'daterangepicker') and  contains(@style,'display: block')]";
	public static int curryear = Calendar.getInstance().get(Calendar.YEAR);
	public static String pervYearNum = getPerviousYearNumber();
	
	public static void doFilterValidattion(By preiodFilter) {

		clickFourWeakFilter(preiodFilter);
		clickYTD(preiodFilter);
		clickMTD(preiodFilter);
		clickToday(preiodFilter);
		clickPreviousYear(preiodFilter);
		clickCurrentDate(preiodFilter);
		clickCustomRange(preiodFilter, "01/16/2020", "01/18/2020");
	}

	public static void clickFourWeakFilter(By preiodFilter) {

		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		waitUntilElementDisplayed(By.xpath(filterDropDown + "//child::*[contains(text(),'" + FourWeak + "')]"));
		click(By.xpath(filterDropDown + "//child::*[contains(text(),'" + FourWeak + "')]"), "4WK button ");
		waitForTriangleLoading();
		String filterLabel = getAttribute("value", preiodFilter);
		if (filterLabel.trim().equalsIgnoreCase(FourWeak)) {
			Reporter.reportStep("pass", "4WK Preiod Filter is selected sucessfully");
		} else {
			Reporter.reportStep("fail", "4WK Preiod Filter is not selected sucessfully");
		}
	}

	public static boolean clickYTD(By preiodFilter) {
		
		String xpath = filterDropDown + "//child::*[contains(text(),'" + YTD + "')]";
		
		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		waitForTriangleLoading();
		if (waitUntilElementDisplayed(By.xpath(xpath))) {
			click(By.xpath(xpath), "YTD button ");
			waitForTriangleLoading();
			String filterLabel = getAttribute("value", preiodFilter);
			System.out.println("filterLabel" + filterLabel);
			if (filterLabel.trim().contains(YTD)) {
				Reporter.reportStep("pass", "YTD Preiod Filter is selected sucessfully");
				return true;
			} else {
				Reporter.reportStep("fail", "YTD Preiod Filter is not selected sucessfully");
				return false;
			}
		} else {
			Reporter.reportStep("fail", "YTD Preiod Filter is not displayed");
			click(preiodFilter, "Preiod Filter button ");
			return false;
		}

	}

	public static boolean clickToday(By preiodFilter) {
		
		String xpath = filterDropDown + "//child::div[@class='ranges']//li[div[@class='ranges']//li[contains(text(),'202') ][2]]";
		
		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitForTriangleLoading();
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		if (waitUntilElementDisplayed(By.xpath(xpath))) {
			click(By.xpath(xpath), "Today button ");
			waitForTriangleLoading();
			return true;
		} else {
			Reporter.reportStep("fail", "Today Preiod Filter is not displayed");
			click(preiodFilter, "Period Filter button ");
			return false;
		}
		
	}

	public static boolean clickMTD(By preiodFilter) {
		
		String MTDXpath = filterDropDown + "//child::*[contains(text(),'" + MTD + "')]";
		
		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Period Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		if (waitUntilElementDisplayed(By.xpath(MTDXpath))) {
			click(By.xpath(filterDropDown + "//child::*[contains(text(),'" + MTD + "')]"), "MTD button ");
			waitForTriangleLoading();
			String filterLabel = getAttribute("value", preiodFilter);
			if (filterLabel.trim().contains(MTD)) {
				Reporter.reportStep("pass", "MTD Preiod Filter is selected sucessfully");
				return true;
			} else {
				Reporter.reportStep("fail", "MTD Preiod Filter is not selected sucessfully");
				return false;
			}
		} else {
			Reporter.reportStep("fail", "MTD Preiod Filter is not displayed");
			click(preiodFilter, "Period Filter button ");
			return false;
		}

	}

	public static boolean clickPreviousYear(By preiodFilter) {
		
		String prevYearXpath = filterDropDown + "//child::*[contains(text(),'" + preYear + "')]";
		
		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		if (waitUntilElementDisplayed(By.xpath(prevYearXpath))) {
			click(By.xpath(filterDropDown + "//child::*[contains(text(),'" + preYear + "')]"), "previous year button ");
			waitForTriangleLoading();
			String filterLabel = getAttribute("value", preiodFilter);
			if (filterLabel.trim().contains(preYear)) {
				Reporter.reportStep("pass", "previous Year Preiod Filter is selected sucessfully");
				return true;
			} else {
				Reporter.reportStep("fail", "previous Year Preiod Filter is not selected sucessfully");
				return false;
			}
		} else {
			Reporter.reportStep("fail", "previous Year Preiod Filter is not displayed");
			click(preiodFilter, "Preiod Filter button ");
			return false;
		}
	}

	public static void clickYTDMinusOne(By preiodFilter) {
		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		waitUntilElementDisplayed(By.xpath(filterDropDown + "//child::*[contains(text(),'" + (curryear - 1) + "')]"));
		click(By.xpath(filterDropDown + "//child::*[contains(text(),'" + (curryear - 1) + "')]"),
				"previous year button ");
		// click(By.xpath(filterDropDown+"//child::*[contains(text(),'Prev. Year')]"),
		// "previous year button ");
		waitForTriangleLoading();
		String filterLabel = getAttribute("value", preiodFilter).trim();
		int prevYear = Integer.parseInt(filterLabel);
		if (prevYear == (curryear - 1)) {
			Reporter.reportStep("pass", "Previous Year Preiod Filter is selected sucessfully");
		} else {
			Reporter.reportStep("fail", "Previous Year Preiod Filter is not selected sucessfully");
		}

	}

	public static void clickCurrentDate(By preiodFilter) {
		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		waitUntilElementDisplayed(By.xpath(filterDropDown + "//child::*[contains(text(),'/')]"));
		click(By.xpath(filterDropDown + "//child::*[contains(text(),'/')]"), "MTD button ");
		waitForTriangleLoading();
		String filterLabel = getAttribute("value", preiodFilter);
		checkDateFormate(filterLabel, "current date");
		if (filterLabel.trim().contains("/")) {
			Reporter.reportStep("pass", "previous Year Preiod Filter is selected sucessfully");
		} else {
			Reporter.reportStep("fail", "previous Year Preiod Filter is not selected sucessfully");
		}
	}

	public static void clickCustomRange(By preiodFilter, String startDate_MM_DD_YY, String endDate_MM_DD_YY) {
		scrollToTop();
		String clearFieldFrom ="//*[contains(@class,'daterangepicker') and  contains(@style,'display: block')]//child::*[@name='daterangepicker_start']";
		String clearFieldTo = "//*[contains(@class,'daterangepicker') and  contains(@style,'display: block')]//child::*[@name='daterangepicker_end']";
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		waitUntilElementDisplayed(By.xpath(filterDropDown + "//child::*[contains(text(),'" + cusRange + "')]"));
		click(By.xpath(filterDropDown + "//child::*[contains(text(),'" + cusRange + "')]"), "cusRange button ");
		waitForTriangleLoading();
		locateElement("xpath", clearFieldFrom).click();
		clearAndType(By.xpath(filterDropDown + "//child::*[@name='daterangepicker_start']"), startDate_MM_DD_YY);
		locateElement("xpath", clearFieldTo).click();
		clearAndType(By.xpath(filterDropDown + "//child::*[@name='daterangepicker_end']"), endDate_MM_DD_YY);
		String dateInStartDate = locateElement(By.xpath(clearFieldFrom)).getAttribute("value");
		String dateInToDate = locateElement(By.xpath(clearFieldTo)).getAttribute("value");
		checkDateFormate(dateInStartDate, "start date");
		checkDateFormate(dateInToDate, "end date");
		waitUntilElementDisplayed(By.xpath(filterDropDown + "//child::button[contains(text(),'Apply')]"));
		// click cancel button
		click(By.xpath(filterDropDown + "//child::button[contains(text(),'Cancel')]"), "Cancel button");
		threadSleep(3000);
		waitForTriangleLoading();
		String filterLabel = getAttribute("value", preiodFilter);
		if (!filterLabel.contains(startDate_MM_DD_YY)) {
			Reporter.reportStep("pass", "Applied custom date range is not displayed after Cancel button is clicked");
		} else {
			Reporter.reportStep("fail", "Applied custom date range is displayed after Cancel button is clicked");
		}
		waitForTriangleLoading();

		// click apply button
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		waitUntilElementDisplayed(By.xpath(filterDropDown + "//child::*[contains(text(),'" + cusRange + "')]"));
		click(By.xpath(filterDropDown + "//child::*[contains(text(),'" + cusRange + "')]"), "cusRange button ");
		waitForTriangleLoading();
		locateElement("xpath", clearFieldFrom).click();
		clearAndType(By.xpath(filterDropDown + "//child::*[@name='daterangepicker_start']"), startDate_MM_DD_YY);
		locateElement("xpath", clearFieldTo).click();
		clearAndType(By.xpath(filterDropDown + "//child::*[@name='daterangepicker_end']"), endDate_MM_DD_YY);
		waitUntilElementDisplayed(By.xpath(filterDropDown + "//child::button[contains(text(),'Apply')]"));
		click(By.xpath(filterDropDown + "//child::button[contains(text(),'Apply')]"), "Apply button");
		if (checkElementDisplayedProp(By.xpath("//*[@id='dashboard_popup']//div[@class='modal-body']"))) {
			String msg = getTextfromField(By.xpath("//*[@id='dashboard_popup']//div[@class='modal-body']"), "Popup ");
			Reporter.reportStep("info", "Alert msg:  " + msg);
			click(By.xpath("//*[@id='reven_filt_yes_btn']"), "Yes");
			threadSleep(5000);
		} else {
			Reporter.reportStep("info", "Pop up not displayed after clicking Custom Range Apply");
		}

	}

	public static void checkDateFormate(String strDate, String fieldName) {

		if (strDate.trim().equals("")) {
			return;
		} else {
			SimpleDateFormat sdfrmt = new SimpleDateFormat(SeleniumUtils.periodPattern);
			sdfrmt.setLenient(false);

			try {
				Date javaDate = sdfrmt.parse(strDate);

				System.out.println(strDate + " is valid date format in " + fieldName);
				Reporter.reportStep("pass", "The date (" + strDate + ") is in valid date format(MM/dd/yyyy)");
			}
			/* Date format is invalid */
			catch (ParseException e) {
				System.out.println(strDate + " is Invalid Date format in " + fieldName);
				Reporter.reportStep("fail", "The date (" + strDate + ") is not in valid date format(MM/dd/yyyy)");
			}
		}

	}

	public static void waitForTriangleLoading() {
		threadSleep(5000);
	}

	public static List<String> getMTDDaysList() {
		
		String curDate = getDateWithFormat(SeleniumUtils.periodPattern);

		DateTimeFormatter format = DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern, Locale.ENGLISH);
		LocalDate date = LocalDate.parse(curDate, format);

		LocalDate currMonthDate = date.withDayOfMonth(1);

		int noOfDays = date.getDayOfMonth();

		List<String> actDates = new ArrayList<String>();

		for (int i = 0; i < noOfDays; i++) {
			actDates.add(currMonthDate.plusDays(i).format(DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern)).toString());
		}

		System.out.println("Current month Days ::" + actDates);

		return actDates;
	}

	public static List<String> getYTDDaysList() {
		
		String curDate = getDateWithFormat(SeleniumUtils.periodPattern);

		DateTimeFormatter format = DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern, Locale.ENGLISH);
		LocalDate date = LocalDate.parse(curDate, format);

		LocalDate currMonthDate = date.withDayOfYear(1);

		int noOfDays = date.getDayOfYear();

		List<String> actDates = new ArrayList<String>();

		for (int i = 0; i < noOfDays; i++) {
			actDates.add(currMonthDate.plusDays(i).format(DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern)).toString());
		}

		System.out.println("Current Year Days ::" + actDates);

		return actDates;
	}

	public static List<String> getPerviousYearDaysList() {

		String curDate = getDateWithFormat(SeleniumUtils.periodPattern);

		DateTimeFormatter format = DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern, Locale.ENGLISH);
		LocalDate date = LocalDate.parse(curDate, format);

		LocalDate currMonthDate = date.minusYears(1).withDayOfYear(1);

		int noOfDays = date.lengthOfYear();

		List<String> actDates = new ArrayList<String>();

		for (int i = 0; i < noOfDays; i++) {
			actDates.add(currMonthDate.plusDays(i).format(DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern)).toString());
		}

		System.out.println("Previous Year Days ::" + actDates);

		return actDates;
	}

	public static List<String> getCustomDaysList(String fromDateString, String toDateString) {

		DateTimeFormatter format = DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern, Locale.ENGLISH);
		LocalDate fromDate = LocalDate.parse(fromDateString, format);
		LocalDate toDate = LocalDate.parse(toDateString, format);

		// LocalDate currMonthDate = fromDate.minusYears(1).withDayOfYear(1);

		long noOfDays = ChronoUnit.DAYS.between(fromDate, toDate) + 1;

		List<String> actDates = new ArrayList<String>();
		// actDates.add(fromDateString);
		for (int i = 0; i < noOfDays; i++) {
			actDates.add(fromDate.plusDays(i).format(DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern)).toString());
		}

		System.out.println("Custom Days ::" + actDates);

		return actDates;
	}

	public static List<String> get4WKDaysList() {

		String curDate = getDateWithFormat(SeleniumUtils.periodPattern);
		DateTimeFormatter format = DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern, Locale.ENGLISH);
		LocalDate toDate = LocalDate.parse(curDate, format);
		// LocalDate toDate = LocalDate.parse(toDateString, format);

		LocalDate fromDate = toDate.minusWeeks(4);

		long noOfDays = ChronoUnit.DAYS.between(fromDate, toDate) + 1;

		List<String> actDates = new ArrayList<String>();
		// actDates.add(fromDateString);
		for (int i = 0; i < noOfDays; i++) {
			actDates.add(fromDate.plusDays(i).format(DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern)).toString());
		}

		System.out.println("4WK Days ::" + actDates);

		return actDates;
	}

	public static boolean clickTodayDate(By preiodFilter) {

		String xpath = filterDropDown + "//child::div[@class='ranges']//li[contains(text(),'202') ][2]";

		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		if (waitUntilElementDisplayed(By.xpath(xpath))) {
			click(By.xpath(filterDropDown + "//child::div[@class='ranges']//li[contains(text(),'202') ][2]"), "Today button ");
			waitForTriangleLoading();
			return true;
		} else {
			Reporter.reportStep("fail", "Today date filter is not displayed");
			click(preiodFilter, "Preiod Filter button ");
			return false;
		}

	}

	public static boolean clickLTM(By preiodFilter) {
		
		String xpath = filterDropDown + "//child::*[contains(text(),'" + LTM + "')]";
		
		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		
		if (waitUntilElementDisplayed(By.xpath(xpath))) {
			click(By.xpath(xpath), "LTM button ");
			waitForTriangleLoading();
			String filterLabel = getAttribute("value", preiodFilter);
			if (filterLabel.trim().contains(LTM)) {
				Reporter.reportStep("pass", "LTM Preiod Filter is selected sucessfully");
				return true;
			} else {
				Reporter.reportStep("fail", "LTM Preiod Filter is not selected sucessfully");
				return false;
			}
		} else {
			Reporter.reportStep("fail", "LTM Preiod Filter is is not displayed");
			click(preiodFilter, "Preiod Filter button ");
			return false;
		}
		

	}

	public static void clickPreWeak(By preiodFilter) {
		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		waitUntilElementDisplayed(By.xpath(filterDropDown + "//child::*[contains(text(),'" + preWeak + "')]"));
		click(By.xpath(filterDropDown + "//child::*[contains(text(),'" + preWeak + "')]"), "preWeak button ");
		waitForTriangleLoading();
		String filterLabel = getAttribute("value", preiodFilter);
		if (filterLabel.trim().contains(preWeak)) {
			Reporter.reportStep("pass", "Previous Weak Preiod Filter is selected sucessfully");
		} else {
			Reporter.reportStep("fail", "Previous Weak Preiod Filter is not selected sucessfully");
		}
	}

	public static void clickPreMonth(By preiodFilter) {
		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		waitUntilElementDisplayed(By.xpath(filterDropDown + "//child::*[contains(text(),'" + preMonth + "')]"));
		click(By.xpath(filterDropDown + "//child::*[contains(text(),'" + preMonth + "')]"), "prevMonth button ");
		waitForTriangleLoading();
		String filterLabel = getAttribute("value", preiodFilter);
		if (filterLabel.trim().contains(preMonth)) {
			Reporter.reportStep("pass", "Previous Month Preiod Filter is selected sucessfully");
		} else {
			Reporter.reportStep("fail", "Previous Month Preiod Filter is not selected sucessfully");
		}

	}

	public static void clickYesterday(By preiodFilter) {
		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		waitUntilElementDisplayed(By.xpath(filterDropDown + "//child::*[contains(text(),'" + YesterDay + "')]"));
		click(By.xpath(filterDropDown + "//child::*[contains(text(),'" + YesterDay + "')]"), "YesterDay button ");
		waitForTriangleLoading();
		String filterLabel = getAttribute("value", preiodFilter);
		if (filterLabel.trim().contains(YesterDay)) {
			Reporter.reportStep("pass", "YesterDay  Preiod Filter is selected sucessfully");
		} else {
			Reporter.reportStep("fail", "YesterDay  Preiod Filter is not selected sucessfully");
		}

	}

	public static void ClickReportsfilter(String value) {
		By periodFilter = By
				.xpath("(//div[contains(@class,'daterangepicker ') and contains(@style,'display: block')])//ul");
		scrollToTop();
		waitUntilElementDisplayed(periodFilter);
		click(By.xpath("//i[@class='fa fa-calendar-o font-md1']"), "Filter Button is:");
		boolean waitUntilElementDisplayed = waitUntilElementDisplayed(
				By.xpath(filterDropDown + "//child::*[contains(text(),'" + value + "')]"));
		if (waitUntilElementDisplayed == true) {
			click(By.xpath(filterDropDown + "//child::*[contains(text(),'" + value + "')]"), value + "Button");
			waitForTriangleLoading();
			String label = getAttribute("value", By.xpath("//input[@id='event_start_dt']"));
			System.out.println("label" + label);
			if (label.trim().contains(value)) {
				Reporter.reportStep("pass", value + " Period Filter is selected sucessfully");
			} else {
				Reporter.reportStep("fail", value + "  Period Filter is not selected sucessfully");
			}
		} else {
			Reporter.reportStep("fail", value + "  Period Filter is not Displayed in the filter");
		}
	}
	
	public static String getPerviousYearNumber() {
		
		String curDate = getDateWithFormat(SeleniumUtils.periodPattern);

		DateTimeFormatter format = DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern, Locale.ENGLISH);
		LocalDate date = LocalDate.parse(curDate, format);

		LocalDate currMonthDate = date.minusYears(1);
		
		String prevYearNum = Integer.toString(currMonthDate.getYear());
		System.err.println();
		return prevYearNum;
	}
	
	public static boolean clickPrevYearNum(By preiodFilter) {
		
		String xpath = filterDropDown + "//child::*[contains(text(),'" + pervYearNum + "')]";
		
		scrollToTop();
		waitUntilElementDisplayed(preiodFilter);
		click(preiodFilter, "Preiod Filter button ");
		waitUntilElementDisplayed(By.xpath(filterDropDown));
		
		if (waitUntilElementDisplayed(By.xpath(xpath))) {
			click(By.xpath(xpath), "Perv Year button ");
			waitForTriangleLoading();
			String filterLabel = getAttribute("value", preiodFilter);
			if (filterLabel.trim().contains(pervYearNum)) {
				Reporter.reportStep("pass", "Prev Year Preiod Filter is selected sucessfully");
				return true;
			} else {
				Reporter.reportStep("fail", "Prev Year Preiod Filter is not selected sucessfully");
				return false;
			}
		} else {
			Reporter.reportStep("fail", "Prev Year Preiod filter is not displayed");
			click(preiodFilter, "Preiod Filter button ");
			return false;
		}

	}
	
	public static void doFilterValidattionForRevenue(By preiodFilter) {

		/*clickFourWeakFilter(preiodFilter);
		clickYTD(preiodFilter);
		clickMTD(preiodFilter);
		clickToday(preiodFilter);
		clickPreviousYear(preiodFilter);
		clickCurrentDate(preiodFilter);*/
		clickCustomRange(preiodFilter, "10/01/2021", "11/30/2021");
	}
	
	public static String getPerivousDate(String dateString) {

		String curDate = dateString;

		DateTimeFormatter format = DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern, Locale.ENGLISH);
		LocalDate date = LocalDate.parse(curDate, format);

		LocalDate pervDate = date.minusDays(1);
		String reqDate = pervDate.format(DateTimeFormatter.ofPattern(SeleniumUtils.periodPattern)).toString();
		//String changedDateFormat = changeDateFormat("MM/dd/yyyy", "yyyy-MM-dd", reqDate);
		return reqDate;
	}
	

}
