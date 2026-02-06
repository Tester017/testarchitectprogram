package com.dz.prism.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dz.core.common.utils.Reporter;
import com.dz.core.driver.Driver;


public class GlobalSearch extends SeleniumUtils {


	private String previousSearchMode = "xyz";
	WebDriver driver = Driver.getWebDriver();

	/**
	 * Global search and click to open tear sheet
	 * 
	 * @param SearchString
	 * @param type
	 * @author ilayaraja
	 */
	public void searchWithExactMacth(String SearchString, String type) {
		checkSearchMode(type);
		String responseList = "";
		String splitText = "";
		WebElement globalSearch = SeleniumUtils.webDriver
				.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch")));
		try {
			globalSearch.clear();
			globalSearch.sendKeys(Keys.BACK_SPACE);
			switch (type) {
			case "account":
			case "coveredac":
			case "prospect":
			case "superacc":
			case "event":
				type = (type.equals("coveredac") || type.equals("prospect")) ? "account"
						: type.equals("superacc") ? "super account" : type;
				SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString,
						"xpath");
				responseList = SeleniumUtils.webDriver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")))
						.getText();
				if (responseList.equalsIgnoreCase("No results found for '" + SearchString + "'")
						|| !responseList.toLowerCase().contains(type)) {
					globalSearch.clear();
					globalSearch.sendKeys(Keys.BACK_SPACE);
				} else {
					splitText = SeleniumUtils.webDriver
							.findElement(By.xpath(
									SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span"))
							.getText();
					if (Arrays.asList(splitText.split("\\R")).contains(SearchString)) {
						SeleniumUtils.webDriver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")
								+ "//li//span[text()='" + SearchString + "']"))
						.click();
					} else {
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}
				}
				break;
				/* search for Contact and check only contact check box */
			case "contact":
				SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString,
						"xpath");
				responseList = SeleniumUtils.webDriver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")))
						.getText();
				if (responseList.equalsIgnoreCase("No results found for '" + SearchString + "'")
						|| !responseList.toLowerCase().contains(type)) {
					globalSearch.clear();
					globalSearch.sendKeys(Keys.BACK_SPACE);
				} else {
					splitText = SeleniumUtils.webDriver
							.findElement(By.xpath(
									SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span"))
							.getText();
					Pattern searchKey = Pattern.compile("\\b" + SearchString + "\\b");
					Matcher searchFrom = searchKey.matcher(splitText);
					if (searchFrom.find()) {
						SeleniumUtils.webDriver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")
								+ "//li//span[text()='" + SearchString + "']"))
						.click();
					} else {
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}
				}
				break;

			case "ticker":
				SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString,
						"xpath");
				responseList = SeleniumUtils.webDriver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")))
						.getText();
				if (responseList.equalsIgnoreCase("No results found for '" + SearchString + "'")
						|| !responseList.toLowerCase().contains(type)) {
					globalSearch.clear();
					globalSearch.sendKeys(Keys.BACK_SPACE);
				} else {
					splitText = SeleniumUtils.webDriver
							.findElement(By.xpath(
									SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span"))
							.getText();
					Pattern searchKey = Pattern.compile("\\b" + SearchString + "\\b");
					Matcher searchFrom = searchKey.matcher(splitText);
					if (searchFrom.find()) {
						SeleniumUtils.webDriver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")
								+ "//li//span[text()='" + SearchString + "']"))
						.click();
					} else {
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}
				}

				break;
			case "user":
				SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString,
						"xpath");
				responseList = SeleniumUtils.webDriver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")))
						.getText();
				if (responseList.equalsIgnoreCase("No results found for '" + SearchString + "'")) {
					globalSearch.clear();
					globalSearch.sendKeys(Keys.BACK_SPACE);
				} else {
					splitText = SeleniumUtils.webDriver
							.findElement(By.xpath(
									SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span"))
							.getText();
					Pattern searchKey = Pattern.compile("\\b" + SearchString + "\\b");
					Matcher searchFrom = searchKey.matcher(splitText);
					if (searchFrom.find()) {
						SeleniumUtils.webDriver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")
								+ "//li//span[text()='" + SearchString + "']"))
						.click();
					} else {
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
	 * 
	 * @param SearchString
	 * @param type
	 * @author ilayaraja
	 */
	public void searchWithPartialMacth(String SearchString, String type) {
		checkSearchMode(type);
		WebDriver driver = Driver.getWebDriver();
		String responseList = "";
		String splitText = "";
		WebElement globalSearch = driver
				.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch")));
		try {
			globalSearch.clear();
			globalSearch.sendKeys(Keys.BACK_SPACE);
			switch (type) {
			case "account":


				SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString,
						"xpath");
				Thread.sleep(10000);
				responseList = driver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")))
						.getText();
				if (responseList.equalsIgnoreCase("No results found for '" + SearchString + "'")
						|| !responseList.toLowerCase().contains(type)) {
					globalSearch.clear();
					globalSearch.sendKeys(Keys.BACK_SPACE);
				} else {
					splitText = driver
							.findElement(By.xpath(
									SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span"))
							.getText();
					if (splitText.contains(SearchString)) {
						driver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")
								+ "//li//span[contains(text(),'" + SearchString + "')]")).click();
						Thread.sleep(10000);
					} else {
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}
				}
				break;

			case "prospect":
			case "superacc":
			case "event":
				type = (type.equals("coveredac") || type.equals("prospect")) ? "account"
						: type.equals("superacc") ? "super account" : type;
				SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString,
						"xpath");
				responseList = driver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")))
						.getText();
				if (responseList.equalsIgnoreCase("No results found for '" + SearchString + "'")
						|| !responseList.toLowerCase().contains(type)) {
					globalSearch.clear();
					globalSearch.sendKeys(Keys.BACK_SPACE);
				} else {
					splitText = driver
							.findElement(By.xpath(
									SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span"))
							.getText();
					if (splitText.contains(SearchString)) {
						driver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")
								+ "//li//span[contains(text(),'" + SearchString + "')]")).click();
						Thread.sleep(10000);
					} else {
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}
				}
				break;
				/* search for Contact and check only contact check box */
			case "contact":
				SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString,
						"xpath");
				Thread.sleep(10000);
				responseList = driver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")))
						.getText();
				if (responseList.equalsIgnoreCase("No results found for '" + SearchString + "'")
						|| !responseList.toLowerCase().contains(type)) {
					globalSearch.clear();
					globalSearch.sendKeys(Keys.BACK_SPACE);
				} else {
					splitText = driver
							.findElement(By.xpath(
									SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span"))
							.getText();
					if (splitText.contains(SearchString)) {
						driver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")
								+ "//li//span[contains(text(),'" + SearchString + "')]")).click();
						Thread.sleep(10000);
					} else {
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}
				}
				break;
			case "ticker":
				SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString,
						"xpath");
				Thread.sleep(3000);
				responseList = driver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")))
						.getText();
				if (responseList.equalsIgnoreCase("No results found for '" + SearchString + "'")
						|| !responseList.toLowerCase().contains(type)) {
					globalSearch.clear();
					globalSearch.sendKeys(Keys.BACK_SPACE);
				} else {
					splitText = driver
							.findElement(By.xpath(
									SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span"))
							.getText();
					if (splitText.contains(SearchString)) {
						driver.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")
								+ "//li//span[contains(text(),'" + SearchString + "')]")).click();
						Thread.sleep(3000);
					} else {
						globalSearch.clear();
						globalSearch.sendKeys(Keys.BACK_SPACE);
					}
				}
				break;

			case "user":
				SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), SearchString,
						"xpath");
				responseList = SeleniumUtils.webDriver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")))
						.getText();
				if (responseList.equalsIgnoreCase("No results found for '" + SearchString + "'")) {
					globalSearch.clear();
					globalSearch.sendKeys(Keys.BACK_SPACE);
				} else {
					splitText = SeleniumUtils.webDriver
							.findElement(By.xpath(
									SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist") + "//li//span"))
							.getText();
					if (splitText.contains(SearchString)) {
						SeleniumUtils.webDriver
						.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalsearchlist")
								+ "//li//span[contains(text(),'" + SearchString + "')]"))
						.click();
					} else {
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
	 * Global Search type check box check if needToCheck true
	 * 
	 * @param selector
	 * @param needToCheck
	 */
	private void setSearchMode(String type) {
		try {
			WebDriver driver = Driver.getWebDriver();
			WebElement globalSearch = driver
					.findElement(By.xpath(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch")));
			if (SeleniumUtils.checkElementDisplayedProp("top-search", "id")) {
				((JavascriptExecutor) driver).executeScript("$('#top-search').attr('data-saveusersett','1')");
			}
			if (!SeleniumUtils.checkElementDisplayedProp(
					SeleniumUtils.commonProps.getProperty("GS_id_filter_check_area"), "xpath")) {
				SeleniumUtils.setValueToField(SeleniumUtils.commonProps.getProperty("GS_id_globalSearch"), "test",
						"xpath");
			}
			Thread.sleep(4000);
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

				if (SeleniumUtils.waitUntilElementDisplayed(By.xpath("//input[@id='" + entry.getKey() + "']//parent::*"))) {
					WebElement checkBox = driver.findElement(By.id(entry.getKey()));
					if (entry.getValue()) {
						if (!checkBox.isSelected()) {
							((JavascriptExecutor) driver).executeScript("$('#" + entry.getKey() + "').click();");
						}
					} else if (!entry.getValue()) {
						if (checkBox.isSelected()) {
							((JavascriptExecutor) driver).executeScript("$('#" + entry.getKey() + "').click();");
						}
					}

				} else {
					System.out.println(entry.getKey() + " check box is not displayed");
				}

			}
			globalSearch.clear();
			globalSearch.sendKeys(Keys.BACK_SPACE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkSearchMode(String type) {
		if (type != null && !type.isEmpty() && !previousSearchMode.equalsIgnoreCase(type)) {
			previousSearchMode = type;
			setSearchMode(type);
		} else {
			if (!previousSearchMode.equalsIgnoreCase(type)) {
				previousSearchMode = type;
				setSearchMode("");
			}
		}
	}


	/**
	 * @param accountName--> global search search keyword 
	 * @param accType --> search type like account , contact , ticker
	 */
public void selectAccountAndType(String accountName,String accType) {

		
		String accTypeHeader = "//div[@class='typeahead-result globalSettingResult']/ul/li[1]";
		//String noResults = "//li[@class='typeahead-empty']";
		String listOfValues = "//div[@class='typeahead-result globalSettingResult']/ul/li[@class='typeahead-item']";
		String dataGroup = "";
		try {
			
			String data = accountName;
			if (!data.isEmpty()) {
				
				Thread.sleep(2000);
				setTimeoutUntilVisibility("top-search", "id");
				ClearFieldValue(By.id("top-search"));
				Thread.sleep(2000);
				setValueToField("top-search", data, "id");
				
				if(waitUntilElementDisplayed(By.xpath("//*[@class='filter-check-area']//div"))) {
					
					deSelectCheckBox();
					Thread.sleep(2000);
					waitForElementsRefresh(By.xpath(listOfValues));
					dataGroup = selectReqTypesCheckBox(accType);
					waitForPageLoad();
					setTimeoutUntilVisibility(accTypeHeader,"xpath");
					if (waitUntilElementDisplayed(By.xpath(accTypeHeader))) {
						Thread.sleep(3000);
						String textfromField = getTextfromField(accTypeHeader, "xpath");
						if (!textfromField.equalsIgnoreCase("No results found for '"+ data +"'")) {
							waitForElementsRefresh(By.xpath(listOfValues));
							List<WebElement> listValues = locateElements(By.xpath(listOfValues));
							int k = listValues.size() ;
								for (int j=1 ; j<=k ; j++) {
									String nameXpath = listOfValues + "[" + j + "]/a[@data-group='" + dataGroup + "']/span";
									String nameInactiveXpath = listOfValues + "[" + j + "]/a[@data-group='" + dataGroup + "']//div/span";

									if(checkElementDisplayedProp(By.xpath(nameXpath))) {
										Reporter.reportStep("pass", "Account active status verified from Global search dropdown");
										waitUntilElementDisplayed(By.xpath(nameXpath));
										String name = getTextfromField(nameXpath, "xpath");
										//System.out.println(">>>>:"+name+" "+data);
										if (name.contains(data) || name.equalsIgnoreCase(data)) {
											setTimeoutUntilVisibility(nameXpath , "xpath");
											waitForElementRefresh(By.xpath(nameXpath));
											click(By.xpath(nameXpath), "The Active Account : (" + name + ")");
											threadSleep(2000);
											break;
										}
									}else {
										Reporter.reportStep("info", "Account Inactive status verified from Global search dropdown",true);
										waitUntilElementDisplayed(By.xpath(nameInactiveXpath));
										String name = getTextfromField(nameInactiveXpath, "xpath");
										if (name.contains(data)) {
											setTimeoutUntilVisibility(nameInactiveXpath , "xpath");
											click(By.xpath(nameInactiveXpath), "InActive Account Name ");
											threadSleep(2000);
											break;
										}
									}
									
								}
							
							
						} else {
							Reporter.reportStep("fail", "No results found for this Account Name :: " + data);
							//NoSuchFieldError err = new NoSuchFieldError("No results found for this Account" + data);
							//throw err;
						}
					}
					
				} else {
					Reporter.reportStep("fail", "CheckBox list is not displayed ");
					NoSuchFieldError err = new NoSuchFieldError("CheckBox list is not displayed ");
					throw err;
				}
				
			} else {
				Reporter.reportStep("fail", "AccountName column is empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

public void selectInactiveAccountAndType(String accountName,String accType) {

	
	String accTypeHeader = "//div[@class='typeahead-result globalSettingResult']/ul/li[1]";
	//String noResults = "//li[@class='typeahead-empty']";
	String listOfValues = "//div[@class='typeahead-result globalSettingResult']/ul/li[@class='typeahead-item']";
	String dataGroup = "";
	try {
		
		String data = accountName;
		if (!data.isEmpty()) {
			
			Thread.sleep(2000);
			setTimeoutUntilVisibility("top-search", "id");
			ClearFieldValue(By.id("top-search"));
			Thread.sleep(2000);
			setValueToField("top-search", data, "id");
			
			if(waitUntilElementDisplayed(By.xpath("//*[@class='filter-check-area']//div"))) {
				
				int countOfDropdownList = getCountOfDropdownList("//*[@class='filter-check-area']//div", "xpath");
				for (int i = 1; i <= countOfDropdownList; i++) {
					Thread.sleep(500);
					Boolean isSelected = checkBoxIsSelect("//*[@class='filter-check-area']//div[temp]/input".replace("temp", "" + i + ""), "xpath");
					if (isSelected) {
						setTimeoutUntilVisibility("//*[@class='filter-check-area']//div[temp]/label".replace("temp", "" + i + ""), "xpath");
						click(By.xpath("//*[@class='filter-check-area']//div[temp]/label".replace("temp", "" + i + "")), "checkBoxSelect");
					}
				}
				Thread.sleep(3000);
				waitForElementsRefresh(By.xpath(listOfValues));
				dataGroup = selectReqTypesCheckBox(accType);
				waitForPageLoad();
				setTimeoutUntilVisibility(accTypeHeader,"xpath");
				if (waitUntilElementDisplayed(By.xpath(accTypeHeader))) {
					Thread.sleep(4000);
					String textfromField = getTextfromField(accTypeHeader, "xpath");
					if (!textfromField.equalsIgnoreCase("No results found for '"+ data +"'")) {
						
						waitForElementsRefresh(By.xpath(listOfValues));
						List<WebElement> listValues = locateElements(By.xpath(listOfValues));
						int k = listValues.size() ;
						for (int j=1 ; j<=k ; j++) {
							String nameXpath = listOfValues + "[" + j + "]/a[@data-group='" + dataGroup + "']//div/span";
							System.out.println(">>>> : "+nameXpath);
							waitUntilElementDisplayed(By.xpath(nameXpath));
							String name = getTextfromField(nameXpath, "xpath");
							if (name.contains(data)) {
								Reporter.reportStep("pass",name+": Inactive account name verified from global search");
								setTimeoutUntilVisibility(nameXpath , "xpath");
								click(By.xpath(nameXpath), "Account Name ");
								threadSleep(1000);
								break;
							}else {
								Reporter.reportStep("fail",name+" : Inactive account name not verified from global search");
							}
						}
					} else {
						Reporter.reportStep("fail", "No results found for this Account Name :: " + data);
						NoSuchFieldError err = new NoSuchFieldError("No results found for this Account" + data);
						throw err;
					}
				}
				
			} else {
				Reporter.reportStep("fail", "CheckBox list is not displayed ");
				NoSuchFieldError err = new NoSuchFieldError("CheckBox list is not displayed ");
				throw err;
			}
			
		} else {
			Reporter.reportStep("fail", "AccountName column is empty");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

	public void accountVerify(String accType){
		try {
			//com.waitForTriangleLoading();
			String data = getData("AccountName");
			if (!data.isEmpty()) {
				Thread.sleep(2000);
				setTimeoutUntilVisibility("top-search", "id");
				setValueToField("top-search", data, "id");
				int countOfDropdownList = getCountOfDropdownList("//*[@class='filter-check-area']//div", "xpath");
				for (int i = 1; i < countOfDropdownList; i++) {
					Thread.sleep(500);
					Boolean isSelected = checkBoxIsSelect("//*[@class='filter-check-area']//div[temp]/input".replace("temp", "" + i + ""), "xpath");
					if (isSelected) {
						setTimeoutUntilVisibility("//*[@class='filter-check-area']//div[temp]/label".replace("temp", "" + i + ""), "xpath");
						click(By.xpath("//*[@class='filter-check-area']//div[temp]/label".replace("temp", "" + i + "")), "checkBoxSelect");
					}
				}
				//setTimeoutUntilVisibility("//input[@id='ACREV']//following-sibling::label", "xpath");
				if(accType.equalsIgnoreCase("Ticker")) {
					click(By.xpath("//input[@id='TIC']//following-sibling::label"), "Ticker CheckBox");
				}else if(accType.equalsIgnoreCase("Covered Accounts")) {
					if(environmentName.toLowerCase().contains("mizuho")) {
						click(By.xpath("//input[@id='NONEQAC']//following-sibling::label"), "Covered Accounts CheckBox");
						Thread.sleep(1000);
						click(By.xpath("//input[@id='EQAC']//following-sibling::label"), "Covered Accounts CheckBox");
					}else {
						click(By.xpath("//input[@id='ACREV']//following-sibling::label"), "Covered Accounts CheckBox");
					}
				}else if(accType.equalsIgnoreCase("Super Accounts")) {
					click(By.xpath("//input[@id='SAC']//following-sibling::label"), "Super Accounts");
				}else if(accType.equalsIgnoreCase("Contact")) {
					click(By.xpath("//input[@id='CON']//following-sibling::label"), "Contact");
				}else if(accType.equalsIgnoreCase("Prospects")) {
					click(By.xpath("//input[@id='PROS']//following-sibling::label"), "Prospects");
				}else if(accType.equalsIgnoreCase("Users")) {
					click(By.xpath("//input[@id='USR']//following-sibling::label"), "Users");
				}else if(accType.equalsIgnoreCase("EVNT")) {
					click(By.xpath("//input[@id='USR']//following-sibling::label"), "Event");
				}

				Thread.sleep(2000);
				setTimeoutUntilVisibility("(//div[@id='wrapper']//div[@class='typeahead-container header-typeahead typeahead-common result']//a)[1]", "xpath");
				String textfromField = getTextfromField("(//div[@id='wrapper']//div[@class='typeahead-container header-typeahead typeahead-common result']//a)[1]", "xpath");
				if (!textfromField.equalsIgnoreCase("No results found for '"+ data +"'")) {
					int searchList = getCountOfDropdownList("//div[@class='typeahead-result globalSettingResult']//li[@class='typeahead-item']", "xpath");
					for (int i = 1; i <= searchList; i++) {
						String accName = getTextfromField("(//div[@class='typeahead-result globalSettingResult']//li[@class='typeahead-item'][temp]//span)[1]".replace("temp", "" + i + ""), "xpath").trim();
						if (accName.contains(data)) {
							setTimeoutUntilVisibility("(//div[@class='typeahead-result globalSettingResult']//li[@class='typeahead-item'][temp]//span)[1]".replace("temp", "" + i + ""), "xpath");
							click(By.xpath("(//div[@class='typeahead-result globalSettingResult']//li[@class='typeahead-item'][temp]//span)[1]".replace("temp", "" + i + "")), "AccountSelect");
							break;
						}
					}
				}else
					Reporter.reportStep("INFO", "No results found for this AccountName");
			} else {
				Reporter.reportStep("fail", "AccountName column is empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<String> isGlobalSearchCheckBoxDisp() {
		By checkboxIdXpath = By.xpath("//*[@class='filter-check-area']//input");
		List<String> checkboxValues = getListOfElementst(checkboxIdXpath, "getAttribute");
		return checkboxValues;
	}
	
	
	public void selectAccountAndTypeWithInfo(String accountName,String accType) {
		String accTypeHeader = "//div[@class='typeahead-result globalSettingResult']/ul/li[1]";
		String listOfValues = "//div[@class='typeahead-result globalSettingResult']/ul/li[@class='typeahead-item']";
		String dataGroup = "";
		try {
			String data = accountName;
			if (!data.isEmpty()) {
				Thread.sleep(2000);
				setTimeoutUntilVisibility("top-search", "id");
				ClearFieldValue(By.id("top-search"));
				Thread.sleep(2000);
				setValueToField("top-search", data, "id");
				
				if(waitUntilElementDisplayed(By.xpath("//*[@class='filter-check-area']//div"))) {
					
					deSelectCheckBox();
					Thread.sleep(3000);
					waitForElementsRefresh(By.xpath(listOfValues));
					dataGroup = selectReqTypesCheckBox(accType);
					waitForPageLoad();
					setTimeoutUntilVisibility(accTypeHeader,"xpath");
					if (waitUntilElementDisplayed(By.xpath(accTypeHeader))) {
						Thread.sleep(2000);
						String textfromField = getTextfromField(accTypeHeader, "xpath");
						if (!textfromField.equalsIgnoreCase("No results found for '"+ data +"'")) {
							
							waitForElementsRefresh(By.xpath(listOfValues));
							List<WebElement> listValues = locateElements(By.xpath(listOfValues));
							int k = listValues.size() ;
							for (int j=1 ; j<=k ; j++) {
								String nameXpath = listOfValues + "[" + j + "]/a[@data-group='" + dataGroup + "']/span";
								waitUntilElementDisplayed(By.xpath(nameXpath));
								String name = getTextfromField(nameXpath, "xpath");
								if (name.contains(data)) {
									setTimeoutUntilVisibility(nameXpath , "xpath");
									click(By.xpath(nameXpath), "Account Name ");
									break;
								}
								
							}
							
						} else {
							Reporter.reportStep("pass", "No results found for this searched Name :: " + data);
							Reporter.reportStep("pass", "Inactive contact not displayed in global search");
						}
					}
					
				} else {
					Reporter.reportStep("fail", "CheckBox list is not displayed ");
				}
				
			} else {
				Reporter.reportStep("fail", "SearchName column is empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	public List<String> getGlobalSearchCheckBoxLabels() {
		String globalSearchLabel = "//*[@class='filter-check-area']/div/label";
		List<String> globalSearchLabels = getListOfElementst(By.xpath(globalSearchLabel), "text");
		return globalSearchLabels;		
	}
	
	public List<String> getAccountNameWithRAC(String accountName, String accType) {
		
		String accTypeHeader = "//div[@class='typeahead-result globalSettingResult']/ul/li[1]";
		String listOfValues = "//div[@class='typeahead-result globalSettingResult']/ul/li[@class='typeahead-item']";
		List<String> typesCode = new ArrayList<String>();
		String dataGroup = "";
		String data = accountName;
		if (!data.isEmpty()) {
			setTimeoutUntilVisibility("top-search", "id");
			ClearFieldValue(By.id("top-search"));
			threadSleep(1000);
			setValueToField("top-search", data, "id");
			if(waitUntilElementDisplayed(By.xpath("//*[@class='filter-check-area']//div"))) {
				deSelectCheckBox();
				threadSleep(2000);
				waitForElementsRefresh(By.xpath(listOfValues));
				dataGroup = selectReqTypesCheckBox(accType);
				waitForPageLoad();
				setTimeoutUntilVisibility(accTypeHeader,"xpath");
				if (waitUntilElementDisplayed(By.xpath(accTypeHeader))) {
					threadSleep(3000);
					String textfromField = getTextfromField(accTypeHeader, "xpath");
					if (!textfromField.equalsIgnoreCase("No results found for '"+ data +"'")) {
						waitForElementsRefresh(By.xpath(listOfValues));
						List<WebElement> listValues = locateElements(By.xpath(listOfValues));
						int k = listValues.size() ;
						for (int j=1 ; j<=k ; j++) {
							String nameXpath = listOfValues + "[" + j + "]/a[@data-group='" + dataGroup + "']/span";
							if(checkElementDisplayedProp(By.xpath(nameXpath))) {
								String RCATypeXpath = nameXpath + "/span";
								String name = getTextfromField(nameXpath, "xpath");
								if (name.contains(data)) {
									typesCode = getListOfElementst(By.xpath(RCATypeXpath), "Text");
									break;
								}
							} else {
								Reporter.reportStep("info", "Given Account is Inactive Account",true);
								break;
							}
						}
						
					} else {
						Reporter.reportStep("fail", "No results found for this Account Name :: " + data);
						NoSuchFieldError err = new NoSuchFieldError("No results found for this Account" + data);
						throw err;
					}
				}
				
			} else {
				Reporter.reportStep("fail", "CheckBox list is not displayed ");
				NoSuchFieldError err = new NoSuchFieldError("CheckBox list is not displayed ");
				throw err;
			}
			
		} else {
			Reporter.reportStep("fail", "AccountName column is empty");
		}
	
		return typesCode;
	}
	
	public String selectReqTypesCheckBox(String accType) {
		
		String dataGroup = "";
		
		if(accType.equalsIgnoreCase("Ticker")) {
			click(By.xpath("//input[@id='TIC']//following-sibling::label"), "Ticker CheckBox");
			dataGroup = "Ticker";
		} else if(accType.equalsIgnoreCase("Covered Accounts")) {
			if(environmentName.toLowerCase().contains("mizuho")) {
				click(By.xpath("//input[@id='NONEQAC']//following-sibling::label"), "Covered Accounts CheckBox");
				threadSleep(1000);
				click(By.xpath("//input[@id='EQAC']//following-sibling::label"), "Covered Accounts CheckBox");
			}else {
				click(By.xpath("//input[@id='ACREV']//following-sibling::label"), "Covered Accounts CheckBox");
			}
			dataGroup = "Account";
		} else if(accType.equalsIgnoreCase("Super Accounts")) {
			click(By.xpath("//input[@id='SAC']//following-sibling::label"), "Super Accounts");
			dataGroup = "Super Account";
		} else if(accType.equalsIgnoreCase("Contact")) {
			click(By.xpath("//input[@id='CON']//following-sibling::label"), "Contact");
			dataGroup = "Contact";
		} else if(accType.equalsIgnoreCase("Prospects")) {
			click(By.xpath("//input[@id='PROS']//following-sibling::label"), "Prospects");
			dataGroup = "Account";
		} else if(accType.equalsIgnoreCase("Users")) {
			click(By.xpath("//input[@id='USR']//following-sibling::label"), "Users");
			dataGroup = "Sales Trader";
		} else if(accType.equalsIgnoreCase("Event")) {
			click(By.xpath("//input[@id='EVNT']//following-sibling::label"), "Event");
			dataGroup = "Event";
		}
		return dataGroup;
	}
	
	public void deSelectCheckBox() {
		
		int countOfDropdownList = getCountOfDropdownList("//*[@class='filter-check-area']//div", "xpath");
		for (int i = 1; i <= countOfDropdownList; i++) {
			threadSleep(1000);
			Boolean isSelected = checkBoxIsSelect("//*[@class='filter-check-area']//div[temp]/input".replace("temp", "" + i + ""), "xpath");
			if (isSelected) {
				setTimeoutUntilVisibility("//*[@class='filter-check-area']//div[temp]/label".replace("temp", "" + i + ""), "xpath");
				click(By.xpath("//*[@class='filter-check-area']//div[temp]/label".replace("temp", "" + i + "")), "checkBoxSelect");
			}
		}
	}
	public void accoutSearchWithAccountID(String AccountID,String type,String AccountName) {
		try {
			Reporter.createNode("Account search globally");
			invisibilityOfElement(By.id("loading_screen"));
			String data2 = type;
			String data = AccountID;
			if (!data.isEmpty()) {
				Thread.sleep(2000);
				setTimeoutUntilVisibility("top-search", "id");
				setValueToField("top-search", data, "id");
				int countOfDropdownList = getCountOfDropdownList("//*[@class='filter-check-area']//div", "xpath");
				for (int i = 1; i <= countOfDropdownList; i++) {
					Thread.sleep(500);
					Boolean isSelected = checkBoxIsSelect("//*[@class='filter-check-area']//div[temp]/input".replace("temp", "" + i + ""), "xpath");
					if (isSelected) {
						setTimeoutUntilVisibility("//*[@class='filter-check-area']//div[temp]/label".replace("temp", "" + i + ""), "xpath");
						click(By.xpath("//*[@class='filter-check-area']//div[temp]/label".replace("temp", "" + i + "")), "checkBoxSelect");
					}
				}
				if (data2.equalsIgnoreCase("Corporate") || data2.equalsIgnoreCase("Ticker")) {
					setTimeoutUntilVisibility("//input[@id='TIC']//following-sibling::label", "xpath");
					click(By.xpath("//input[@id='TIC']//following-sibling::label"), "AccountCheckBox");
				}else if(data2.equalsIgnoreCase("Investor") || data2.equalsIgnoreCase("Account") || data2.equalsIgnoreCase("Prospect")) {
					if(environmentName.toLowerCase().contains("mizuho")) {
						setTimeoutUntilVisibility("//input[@id='NONEQAC']//following-sibling::label", "xpath");
						click(By.xpath("//input[@id='NONEQAC']//following-sibling::label"), "Covered Accounts CheckBox");
						Thread.sleep(1000);
						setTimeoutUntilVisibility("//input[@id='EQAC']//following-sibling::label", "xpath");
						click(By.xpath("//input[@id='EQAC']//following-sibling::label"), "Covered Accounts CheckBox");
					}else {
						setTimeoutUntilVisibility("//input[@id='ACREV']//following-sibling::label", "xpath");
						click(By.xpath("//input[@id='ACREV']//following-sibling::label"), "Covered Accounts CheckBox");
						Thread.sleep(500);
						setTimeoutUntilVisibility("//input[@id='PROS']//following-sibling::label", "xpath");
						click(By.xpath("//input[@id='PROS']//following-sibling::label"), "AccountCheckBox");
					}
				}else if(data2.equalsIgnoreCase("Service")) {
					setTimeoutUntilVisibility("//input[@id='PROS']//following-sibling::label", "xpath");
					click(By.xpath("//input[@id='PROS']//following-sibling::label"), "AccountCheckBox");
					Thread.sleep(500);
					if(checkElementDisplayedProp(By.xpath("//input[@id='SERV']//following-sibling::label"))) {
					setTimeoutUntilVisibility("//input[@id='SERV']//following-sibling::label", "xpath");
					click(By.xpath("//input[@id='SERV']//following-sibling::label"), "AccountCheckBox");
					}
				}
				Thread.sleep(2000);
				setTimeoutUntilVisibility("(//div[@id='wrapper']//div[@class='typeahead-container header-typeahead typeahead-common result']//a)[1]", "xpath");
				String textfromField = getTextfromField("(//div[@id='wrapper']//div[@class='typeahead-container header-typeahead typeahead-common result']//a)[1]", "xpath");
				if (!textfromField.equalsIgnoreCase("No results found for '"+ data +"'")) {
					int searchList = getCountOfDropdownList("//div[@class='typeahead-result globalSettingResult']//li[@class='typeahead-item']", "xpath");
					List<String> l=new ArrayList<>();
					for (int i = 1; i <= searchList; i++) {
						String accName = getTextfromField("(//div[@class='typeahead-result globalSettingResult']//li[@class='typeahead-item'][temp]//span)[1]".replace("temp", "" + i + ""), "xpath").trim();
						l.add(accName);
						if (accName.contains(AccountName)) {
							setTimeoutUntilVisibility("(//div[@class='typeahead-result globalSettingResult']//li[@class='typeahead-item'][temp]//span)[1]".replace("temp", "" + i + ""), "xpath");
							scrollUntilElementView(By.xpath("(//div[@class='typeahead-result globalSettingResult']//li[@class='typeahead-item'][temp]//span)[1]".replace("temp", "" + i + "")));
							click(By.xpath("(//div[@class='typeahead-result globalSettingResult']//li[@class='typeahead-item'][temp]//span)[1]".replace("temp", "" + i + "")), "AccountSelect");
							Reporter.reportStep("pass", "Account found and clicked");
							invisibilityOfElement(By.id("loading_screen"));
						waitUntilElementDisplayed(By.xpath("//div[@id='page-wrapper']//i[@data-toggle='dropdown']"));
							break;
						}
					}
					if (!l.contains(AccountName)) {
						Reporter.reportStep("fail", "Account not found in the search List");
					}
				}else
					Reporter.reportStep("fail", "No results found for this AccountName");
			} else {
				Reporter.reportStep("fail", "AccountName column is empty");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}