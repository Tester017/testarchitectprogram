package com.dz.prism.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class GlobalSearch {
	
	private String previousSearchMode = "xyz";
	
	/**
	 * Global search and click to open tear sheet
	 * @param SearchString
	 * @param type
	 * @author ilayaraja
	 */
	public void searchWithExactMacth(String SearchString, String type) {
		checkSearchMode(type);
		String responseList = "";String splitText="";
		WebElement globalSearch = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch")));
		try {
			globalSearch.clear();
			globalSearch.sendKeys(Keys.BACK_SPACE);
			switch (type) {
				case "account":
				case "coveredac":
				case "prospect":
				case "superacc":
				case "ticker":
					
					
				case "event":
					type = (type.equals("coveredac")||type.equals("prospect")) ? "account" : type.equals("superacc") ? "super account" : type;
					SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString, "xpath");
					responseList = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist"))).getText();
					SeleniumUtils.setTimeOut(10);
					if(responseList.equalsIgnoreCase("No results found for '"+SearchString+"'") || !responseList.toLowerCase().contains(type)){
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}else{
						splitText  = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")+"//li//span")).getText();
						if (Arrays.asList(splitText.split("\\R")).contains(SearchString)) {
							SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span[text()='" + SearchString + "']")).click();
						}else{
							globalSearch.clear();
							globalSearch.sendKeys(Keys.BACK_SPACE);
						}
					}
				break;
				/* search for Contact and check only contact check box */
				case "contact":
					SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString, "xpath");
					responseList = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist"))).getText();
					SeleniumUtils.setTimeOut(10);
					if(responseList.equalsIgnoreCase("No results found for '"+SearchString+"'") || !responseList.toLowerCase().contains(type)){
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}else{
						splitText  = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span")).getText();
						Pattern searchKey = Pattern.compile("\\b"+SearchString+"\\b");
						Matcher searchFrom = searchKey.matcher(splitText);
						if (searchFrom.find()) {
							SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span[text()='" + SearchString + "']")).click();
						}else{
							globalSearch.clear();
							globalSearch.sendKeys(Keys.BACK_SPACE);
						}
					}
				break;
				case "user":
					SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString, "xpath");
					responseList = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist"))).getText();
					SeleniumUtils.setTimeOut(10);
					if(responseList.equalsIgnoreCase("No results found for '"+SearchString+"'")){
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}else{
						splitText  = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span")).getText();
						Pattern searchKey = Pattern.compile("\\b"+SearchString+"\\b");
						Matcher searchFrom = searchKey.matcher(splitText);
						if (searchFrom.find()) {
							SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span[text()='" + SearchString + "']")).click();
						}else{
							globalSearch.clear();
							globalSearch.sendKeys(Keys.BACK_SPACE);
						}
					}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			globalSearch.clear();
			globalSearch.sendKeys(Keys.BACK_SPACE);
		}
	}
	
	/**
	 * Global search and click to open tear sheet
	 * @param SearchString
	 * @param type
	 * @author ilayaraja
	 */
	public boolean searchWithPartialMacth(String SearchString, String type) {
		checkSearchMode(type);
		String responseList = "";String splitText="";
		WebElement globalSearch = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch")));
		try {
			globalSearch.clear();
			globalSearch.sendKeys(Keys.BACK_SPACE);
			switch (type) {
				case "account":
					  
				case "coveredac":
					SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString, "xpath");
					String contactElement =  SeleniumUtils.commonProps.getProperty("GS_id_Partial_Macth_account").replace("?",SearchString);
					if(SeleniumUtils.waitUntilElementDisplayed(contactElement, 5, "xpath")){
						SeleniumUtils.ClickOnItems(contactElement, "xpath");
						return true;
					}else{
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}
				break;
				case "prospect":
				case "superacc":
				case "ticker":
					SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString, "xpath");
					SearchString = SearchString.replace("#", "");
					String tickerElement =  SeleniumUtils.commonProps.getProperty("GS_id_Partial_Macth_ticker").replace("?",SearchString);
					if(SeleniumUtils.waitUntilElementDisplayed(tickerElement, 5, "xpath")){
						SeleniumUtils.ClickOnItems(tickerElement, "xpath");
						return true;
					}else{
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}
				break;
				case "event":
					type = (type.equals("coveredac")||type.equals("prospect")) ? "account" : type.equals("superacc") ? "super account" : type;
					SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString, "xpath");
					responseList = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist"))).getText();
					SeleniumUtils.setTimeOut(10);
					if(responseList.equalsIgnoreCase("No results found for '"+SearchString+"'") || !responseList.toLowerCase().contains(type)){
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}else{
						splitText  = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")+"//li//span")).getText();
						if (splitText.contains(SearchString)) {
							SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span[contains(text(),'" + SearchString + "')]")).click();
						}else{
							globalSearch.clear();
							globalSearch.sendKeys(Keys.BACK_SPACE);
						}
					}
				break;
				/* search for Contact and check only contact check box */
				case "contact":
					SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString, "xpath");
					String accountElement =  SeleniumUtils.commonProps.getProperty("GS_id_Partial_Macth_contact").replace("?",SearchString);
					if(SeleniumUtils.waitUntilElementDisplayed(accountElement, 10, "xpath")){
						SeleniumUtils.ClickOnItems(accountElement, "xpath");
						return true;
					}else{
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}
				break;
				case "user":
					SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString, "xpath");
					responseList = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist"))).getText();
					SeleniumUtils.setTimeOut(10);
					if(responseList.equalsIgnoreCase("No results found for '"+SearchString+"'")){
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}else{
						splitText  = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span")).getText();
						if (splitText.contains(SearchString)) {
							SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span[contains(text(),'" + SearchString + "')]")).click();
						}else{
							globalSearch.clear();
							globalSearch.sendKeys(Keys.BACK_SPACE);
						}
					}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			globalSearch.clear();
			globalSearch.sendKeys(Keys.BACK_SPACE);
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Global Search type check box check if needToCheck true
	 * @param selector
	 * @param needToCheck
	 */
	public void setSearchMode(String type) {
		try {
			WebElement globalSearch = SeleniumUtils.webDriver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch")));
			if (SeleniumUtils.checkElementDisplayedProp("top-search", "id")) {
				SeleniumUtils.js.executeScript("$('#top-search').attr('data-saveusersett','1')");
			}
			if (!SeleniumUtils.waitUntilElementDisplayed(SeleniumUtils.commonProps.getProperty("GS_id_filter_check_area"),5, "xpath")) {
				SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), "test", "xpath");
			Thread.sleep(10000);
			}
			Map<String, Boolean> checkBoxMapping = new HashMap<String, Boolean>();
			checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_contactcheck"), false);
			checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_coveredaccs"), false);
			checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_eventcheck"), false);
			checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_propspectcheck"), false);
			checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_superaccountcheck"), false);
			checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_tickercheck"), false);
			checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_usercheck"), false);
			if (!type.equals("")) {
				switch (type) {
				case "contact":
					checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_contactcheck"), true);
					break;
				case "coveredac":
					checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_coveredaccs"), true);
					break;
				case "event":
					checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_eventcheck"), true);
					break;
				case "prospect":
					checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_propspectcheck"), true);
					break;
				case "superacc":
					checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_superaccountcheck"), true);
					break;
				case "ticker":
					checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_tickercheck"), true);
					break;
				case "user":
					checkBoxMapping.put(SeleniumUtils.commonProps.getProperty("GS_id_usercheck"), true);
					break;
				}
			}
			for (Entry<String, Boolean> entry : checkBoxMapping.entrySet()) {
				long elementLengthInDom = (long) SeleniumUtils.js.executeScript("return $('#" + entry.getKey() + "').length;");
				if(elementLengthInDom > 0){
					WebElement checkBox = SeleniumUtils.webDriver.findElement(By.id(entry.getKey()));
					if(checkBox.getSize() != null){
						if (entry.getValue()) {
							if(!checkBox.isSelected()) {
								SeleniumUtils.js.executeScript("$('#" + entry.getKey() + "').click();");
							}
						} else if (!entry.getValue()) {
							if (checkBox.isSelected()) {
								SeleniumUtils.js.executeScript("$('#" + entry.getKey() + "').click();");
							}
						}
					}
				}
			}
			Thread.sleep(10000);
			globalSearch.clear();
			globalSearch.sendKeys(Keys.BACK_SPACE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void checkSearchMode(String type){
		if(type!=null && !type.isEmpty()
				&& !previousSearchMode.equalsIgnoreCase(type)){
			previousSearchMode = type;
			setSearchMode(type);
		}else{
			if(!previousSearchMode.equalsIgnoreCase(type)){
				previousSearchMode = type;
				setSearchMode("");
			}
		}
	}
}
