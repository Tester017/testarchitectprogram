package com.dz.prism.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.exception.FilloException;
import com.dz.core.common.utils.Reporter;
import com.dz.core.driver.Driver;
import com.dz.core.driver.DriverFactory;
import com.dz.core.testng.listeners.DriverListener;
import com.google.common.io.Files;

public class SeleniumUtils extends Reporter{
	public static final String EXCELCOLUMNKEY = "excelColumnId";
	public static final String EXCELROWKEY = "excelRowId";
	public static String UserDirVar = System.getProperty("user.dir"); // Current Location
	public static ExtentReports extendReports;
	public static ExtentTest testCase;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	public static WebDriver webDriver;
	public static Properties SeleniumProps;
	public static String outputFilePath = null;
	public static String outputHtmlPath = null;
	public static String toastMessageElement = "//*[@id='toast-container']//div[@class='toast-message']";
	public static String toastMessageCloseElement = "//*[@class='toast-close-button']";
	public static Boolean enabledSuccesscreenshot = true;
	public static Boolean enabledFailureScreenshot = true;
	public static JavascriptExecutor js = null;
	public static Properties commonProps;
	public static WebDriverWait wait = null;
	public DriverFactory driverFactory;
	public static boolean isproduction = true;
	public static String environmentName ;
	public static double version ;
	public static boolean isExcelDataWritter =true;
	public static String periodPattern ;
	public static String authToken= ""; 
 	/**
	 * Return the properties of given file
	 * @param ConfigFilePath
	 * @return
	 * @throws IOException
	 * @author ilayaraja
	 */
	public static Properties getConfigProprty(String ConfigFilePath) throws IOException{
		try {
			Properties prop=new Properties();
			FileInputStream ip= new FileInputStream(UserDirVar+ConfigFilePath);
			prop.load(ip);
			return prop;
		} catch (Exception e) {
			return null;
		}
	}


	//	@BeforeClass
	public static void createTestObject(){
		try {

			js = (JavascriptExecutor)webDriver;
			outputHtmlPath = outputFilePath +"PrismReport.html"; 
			extendReports = new ExtentReports();
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(outputHtmlPath);
			htmlReporter.config().setDocumentTitle(SeleniumProps.getProperty("3_con_html_report_title"));
			htmlReporter.config().setReportName(SeleniumProps.getProperty("3_con_report_name"));
			htmlReporter.config().setTheme(Theme.DARK);// use (Theme.DARK/Theme.STANDARD )
			extendReports.attachReporter(htmlReporter);
		} catch (Exception e) {
			extendReports=null;
		}
	}


	public void startApp(String url) {
		try {
			WebDriver driver = Driver.getWebDriver();
			driver.navigate().to(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("The Browser Could not be Launched. Hence Failed");
			Reporter.reportStep("fail", e.getMessage());
			throw new RuntimeException();
		}

	}

	public static String getData(String columnName) {
		String data = null;
		try {
			data = ExcelDataReader.getData(DriverListener.testSheetName.get(), DriverListener.testScenarioID.get(),
					columnName);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	public static String setData(String columnName, String value) {
		String data = null;
		try {
			if(isExcelDataWritter==true) {
				data = ExcelDataWriter.setData(columnName, value) ;
			}
		} catch (FilloException e) {
			e.printStackTrace();
		}
		return data;
	}





	public static WebElement findElement(By by) {
		WebDriver driver = Driver.getWebDriver();
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		return driver.findElement(by);
	}

	public static void invisibilityOfElement(By by) {
		WebDriver driver = Driver.getWebDriver();
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public static WebElement locateElement(String locatorType, String value) {
		try {
			WebDriver webDriver = Driver.getWebDriver();
			switch(locatorType.toLowerCase()) {
			case "id": return webDriver.findElement(By.id(value));
			case "name": return webDriver.findElement(By.name(value));
			case "class": return webDriver.findElement(By.className(value));
			case "link": return webDriver.findElement(By.linkText(value));
			case "xpath": return webDriver.findElement(By.xpath(value));
			}
		} catch (NoSuchElementException e) {
			System.err.println("The Element with locator:"+locatorType+" Not Found with value: "+value);
			throw new RuntimeException();
		}
		return null;
	}

	public static By returnBy(String locatorType, String value) {
		try {
			switch(locatorType.toLowerCase()) {
			case "id": return By.id(value);
			case "name": return By.name(value);
			case "class": return By.className(value);
			case "link": return By.linkText(value);
			case "xpath": return By.xpath(value);
			default: System.err.println("Invalid Locator type: "+value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void waitForElementClickable(By ele) {
		WebDriver driver = Driver.getWebDriver();
		WebElement element = driver.findElement(ele);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementvisibilityOf(By ele) {
		WebDriver driver = Driver.getWebDriver();
		WebElement element = findElement(ele);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void click(By ele, String text, boolean snap) {
		WebDriver driver = Driver.getWebDriver();
		try {
			if (!waitForPageLoad()) {
				Reporter.reportStep("fail","When click " + text + " clicked page will load more then 30 sec",snap);
			} else {
				System.out.println("##loading working correctly");
			}
			driver.findElement(ele).click();
			Reporter.reportStep("pass","The Element " + text + " is clicked",snap);
		} catch (StaleElementReferenceException e) {
			try {

				WebElement element = waitForElementRefresh(ele);
				element.click();		

			}catch (Exception ex) {
				Reporter.reportStep("fail", "The Element " + text + " could not be clicked as it is stale in js click");

				System.out.println("Expe : " + ex);
				throw new RuntimeException(); 
			}
		} catch (ElementClickInterceptedException e) {
			try {
				if (closePrismUpdateToastMessage()) {
					WebElement element = waitForElementRefresh(ele);
					element.click();
				} else if (checkElementDisplayedProp(By.xpath(toastMessageCloseElement))) {
					closeToastMessage();
					WebElement element = waitForElementRefresh(ele);
					element.click();
				} else {
					waitForPageLoad();
					driver.findElement(ele).click();
				}

			} catch (ElementClickInterceptedException ex) {
				Reporter.reportStep("fail", "The Element " + text + " could not be clicked as it is have ElementClickInterceptedException");
				System.out.println("Expe : " + ex);
				throw new RuntimeException(); 
			}
		} catch (Exception e) {
			Reporter.reportStep("fail", "The Element " + text + " could not be clicked as it is have Exception");
			throw new RuntimeException();
		}

	}

	public static void click(By ele, String text) {
		click(ele,text,false);
	}

	public static void clickNoReport(By ele, String text) {
		try {
			WebDriver driver = Driver.getWebDriver();
			driver.findElement(ele).click();
		} catch (StaleElementReferenceException e) {
			throw new RuntimeException();
		}
	}

	public static void clearAndType(By element, String data, boolean snap) {
		WebElement ele =null;
		try {
			ele = findElement(element);
			ele.clear();
			ele.sendKeys(data);
			Reporter.reportStep("pass", "Data entered Successfully - "+data,snap);
		} catch (ElementNotInteractableException e) {
			Reporter.reportStep("fail", "Element is not Interactable - "+ele.getText());
			throw new RuntimeException();
		}
	}

	public static void clearAndType(By element, String data) {
		clearAndType(element,data,false);
	}

	public static void sendKeys(By element, String data, boolean snap) {
		WebElement ele = null; 
		try {
			WebDriver driver = Driver.getWebDriver();
			ele = findElement(element);
			ele.sendKeys(data);
			Reporter.reportStep("pass", "Data entered Successfully - "+data,snap);
		} catch (ElementNotInteractableException e) {
			Reporter.reportStep("fail", "Element is not Interactable - "+data);
		}catch (Exception e) {
			Reporter.reportStep("fail", "Element is not Interactable - "+data+ e.getMessage());
		}
	}

	public static void sendKeys(By element, String data) {
		sendKeys(element,data,false);
	}


	public static void sendKeysNoReport(By element, String data) {
		WebElement ele = null;
		try { 
			ele = findElement(element);
			ele.sendKeys(data);
		} catch (ElementNotInteractableException e) {
			throw new RuntimeException();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	public static void selectTokenizerValue(By eleContacts, By eleDropDown, String data, String splitBy,boolean snap) {
		try {
			WebDriver driver = Driver.getWebDriver();
			sendKeys(eleContacts, data.substring(0, data.indexOf(splitBy)).trim());
			threadSleep(5000);
			List<WebElement> dropdownValues = driver.findElements(eleDropDown);
			loop: for (WebElement dropdownValue : dropdownValues) {
				if (data.toLowerCase().trim().contains(dropdownValue.getText().toLowerCase().trim())) {
					dropdownValue.click();
					Reporter.reportStep("pass", "TokenizerValue selected Successfully - " + data, snap);
					break loop;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("fail", e.getMessage());
		}
	}


	public static void selectTokenizerValue(By eleContacts, By eleDropDown, String data) {
		try {
			WebDriver driver = Driver.getWebDriver();
			threadSleep(5000);
			List<WebElement> dropdownValues = driver.findElements(eleDropDown);
			System.out.println("size:"+dropdownValues.size());
			loop: for (WebElement dropdownValue : dropdownValues) {
				if (dropdownValue.getText().toLowerCase().trim().equals(data.toLowerCase().trim())) {
					dropdownValue.click();
					Reporter.reportStep("pass", "TokenizerValue selected Successfully - " + data);
					break loop;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("fail", e.getMessage());
		}
	}


	public static void selectTokenizerValue(By eleContacts, By eleDropDown, String data, String splitBy) {
		selectTokenizerValue(eleContacts,eleDropDown,data,splitBy,true);
	}


	public static void selectTokenizerValueByCharLength(By inputFieldElement,By dropDownElement,String inputData,int noOfChar,boolean snap) throws InterruptedException {
		WebDriver driver = Driver.getWebDriver();
		sendKeys(inputFieldElement, inputData.substring(0, noOfChar));
		Thread.sleep(2000);
		waitUntilElementDisplayedWithReport(dropDownElement, "drop down list");
		clearAndType(inputFieldElement, inputData);
		List<WebElement> dropdownValues = driver.findElements(dropDownElement);
		Thread.sleep(10000);
		loop: for (WebElement dropdown : dropdownValues) {
			Thread.sleep(10000);
			String dpvalue = dropdown.getText().trim();

			if (dpvalue.toLowerCase().equals(inputData.toLowerCase())) {
				Thread.sleep(10000);
				waitForElementvisibilityOf(dropDownElement);
				waitUntilElementDisplayed(dropDownElement);
				Thread.sleep(10000);
				dropdown.click(); 
				Reporter.reportStep("pass", "TokenizerValue selected Successfully - " + inputData, snap);
				Thread.sleep(10000);
				break loop;

			}

		}

	}
	public static void selectTokenizerValueByCharLength(By inputFieldElement,By dropDownElement,String inputData,int noOfChar) throws InterruptedException {
		selectTokenizerValueByCharLength(inputFieldElement,dropDownElement,inputData,noOfChar,true);
	}

	public static boolean isAttributePresent(String attributeName,By element,String text) {
		try {
			WebDriver driver = Driver.getWebDriver();
			if(driver.findElement(element).getAttribute(attributeName)!=null) {
				Reporter.reportStep("pass", attributeName+" is present in "+text);
				return true;}
			else {
				Reporter.reportStep("fail", attributeName+" is not present in "+text);
				return false;
			}
		} catch (Exception e) {
			Reporter.reportStep("fail", attributeName+" is not present in "+text);
			return false;
		}
	}


	public static String getAttribute(String attributeName,By element) {
		try {
			WebDriver driver = Driver.getWebDriver();
			return driver.findElement(element).getAttribute(attributeName);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("fail", attributeName+" is not present");
			return null;
		}
	}


	public void selectByVisibleText(By by, String keysToSend) {
		WebElement ele = findElement(by);
		try {
			Select select= new Select(ele);
			select.selectByVisibleText(keysToSend);
			Reporter.reportStep("pass", keysToSend+" is selected successfully");
		} catch (Exception E) {
			Reporter.reportStep("fail", keysToSend+" is not selected successfully");
			//				throw new RuntimeException();
		}
	}

	public static boolean isAlertPresent() 
	{ 
		try 
		{ 
			Driver.getWebDriver().switchTo().alert(); 
			return true; 
		}   // try
		catch (NoAlertPresentException e) 
		{ 
			return false; 
		}  
	}   


	/**
	 * Return the Driver object based on input browser type
	 * @param string
	 * @return
	 * @author ilayaraja
	 * @return 
	 */
	public static void setBrowserDriverConfig(String browserType)
	{
		try {
			switch(browserType)
			{
			case "2":
				System.setProperty("webdriver.gecko.driver",UserDirVar + SeleniumProps.getProperty("3_FireFoxDriverPath"));
				webDriver = new FirefoxDriver();
				break;
			case "3":
				System.setProperty("webdriver.ie.driver",UserDirVar + SeleniumProps.getProperty("2_IEDriverPath"));
				webDriver = new InternetExplorerDriver();
				break;
			case "1":
				System.setProperty("webdriver.chrome.driver",UserDirVar + SeleniumProps.getProperty("1_ChromeDriverPath"));
				webDriver = new ChromeDriver();
				break;
			}
		} catch (Exception e) {
			webDriver = null;
		}
	}



	public static Boolean checkBoxIsSelect(By elemSelector, boolean snap) //bhuvanes
	{
		try {
			WebDriver driver = Driver.getWebDriver();
			return driver.findElement(elemSelector).isSelected();	 

		} catch (Exception e) {
			return false;
		}
	}

	public static Boolean checkBoxIsSelect(By elemSelector) {
		return checkBoxIsSelect(elemSelector,false);
	}


	/**
	 * check the checkbox is select or not
	 * @param elemSelector
	 * @param byVal
	 * @return
	 */
	public static Boolean checkBoxIsSelect(String elemSelector, String byVal) //daniel
	{
		try {
			WebDriver driver = Driver.getWebDriver();

			switch(byVal.toLowerCase())
			{
			case "xpath":
				return driver.findElement(By.xpath(elemSelector)).isSelected();
			case "id":
				return driver.findElement(By.id(elemSelector)).isSelected();
			case "class":
				return driver.findElement(By.className(elemSelector)).isSelected();
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Clear All tokenize Input value
	 * @param searchInputElement tokenize input field xpath
	 * @return 
	 */
	public static boolean clearAllTokenizeInput(String searchInputElement){ //daniel
		try{
			WebDriver webDriver = Driver.getWebDriver();
			String elementSelector = searchInputElement.replace("input", "a");
			String checkLi = searchInputElement.replace("/input", "");
			List<WebElement> token = webDriver.findElements(By.xpath(checkLi));
			for(int i =0 ;i<token.size();i++){
				String resultAttr = token.get(i).getAttribute("class");		    	 
				if(resultAttr.equalsIgnoreCase("token")){
					List<WebElement> inputCount = webDriver.findElements(By.xpath(elementSelector));
					for (WebElement list : inputCount) {			              
						list.click();
						break;			             
					}			            
				}
			}
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	/**
	 * Select the DropDown List
	 * @param searchInput tokenize Input field xpath 
	 * @param input input value
	 * @param byVal
	 * @return 
	 */
	public static boolean dropDownInputTokenize(String searchInput,String inputValue,String byVal){
		try{
			WebDriver webDriver = Driver.getWebDriver();
			setValueToField(searchInput, inputValue, byVal);
			String elementSelctor= searchInput.replace("/li/input","/following-sibling::ul/li"); //get input dropdown list
			Thread.sleep(1000);
			List<WebElement> dropDown = webDriver.findElements(By.xpath(elementSelctor));         
			String firstValue = dropDown.get(0).getText().trim();
			if(!firstValue.contains("Record")){
				for (WebElement list : dropDown) {
					String result = list.getText().toLowerCase().trim();
					if(result.startsWith(inputValue.toLowerCase().trim())){
						list.click();
						return true;
					}
				}
			}
			else{

				SeleniumUtils.ClearFieldValue(searchInput, "xpath");
			}
			return false;
		}catch (Exception e) {
			return false;
		}    
	}
	/**
	 * check element is visible or not 
	 * @param elemSelector
	 * @param byVal
	 * @return true or false
	 * @author ilayaraja
	 */
	public static Boolean checkElementDisplayedProp(String elemSelector, String byVal)
	{
		try {
			WebDriver webDriver = Driver.getWebDriver();
			switch(byVal)
			{
			case "xpath":
				return webDriver.findElement(By.xpath(elemSelector)).isDisplayed();
			case "id":
				return webDriver.findElement(By.id(elemSelector)).isDisplayed();
			case "class":
				return webDriver.findElement(By.className(elemSelector)).isDisplayed();
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}


	public static Boolean checkElementDisplayedProp(By elemSelector,boolean snap) {
		try {
			WebDriver webDriver = Driver.getWebDriver();
			return webDriver.findElement(elemSelector).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	public static Boolean checkElementDisplayedProp(By elemSelector) {
		return checkElementDisplayedProp(elemSelector, true);
	}

	/**
	 * Launch the Browser and set the URL to be Run
	 * @param Property
	 * @return
	 */
	public static void startBrowserDriver(String URL){
		try {
			WebDriver webDriver = Driver.getWebDriver();
			webDriver.manage().window().maximize();       
			webDriver.get(URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set values to field by byVal(xpath,id,className)
	 * @param elementSelctor
	 * @param Value
	 * @param byVal
	 */
	public static void setValueToField(String elementSelctor, String Value,String byVal){
		try {
			WebDriver webDriver = Driver.getWebDriver();
			switch(byVal.toLowerCase()) {
			case "id":
				webDriver.findElement(By.id(elementSelctor)).sendKeys(Value);
				break;
			case "xpath":
				webDriver.findElement(By.xpath(elementSelctor)).sendKeys(Value);
				break;
			case "class":
				webDriver.findElement(By.className(elementSelctor)).sendKeys(Value);
				break;
			default:	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ClearFieldValue(By elementSelctor,Boolean snap){
		try {
			WebDriver webDriver = Driver.getWebDriver();
			webDriver.findElement(elementSelctor).clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ClearFieldValue(By elementSelctor){
		ClearFieldValue(elementSelctor,false);
	}
	/**
	 * Clearing filed values by byVal
	 * @param elementSelctor
	 * @param byVal
	 */
	public static void ClearFieldValue(String elementSelctor,String byVal){
		try {
			WebDriver webDriver = Driver.getWebDriver();
			switch(byVal.toLowerCase()) {
			case "id":
				webDriver.findElement(By.id(elementSelctor)).clear();	
				break;
			case "xpath":
				webDriver.findElement(By.xpath(elementSelctor)).clear();	
				break;
			case "class":
				webDriver.findElement(By.className(elementSelctor)).clear();	
				break;
			default:
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  Clickable items click by byVal
	 * @param elementSelctor
	 * @param byVal
	 */
	public static void ClickOnItems(String elementSelctor,String byVal){
		WebElement element = null;
		WebDriver webDriver = Driver.getWebDriver();
		WebDriverWait wait = new WebDriverWait(webDriver, 30);
		try {
			switch(byVal.toLowerCase()) {
			case "id":
				element = webDriver.findElement(By.id(elementSelctor));
				break;
			case "xpath":
				element = webDriver.findElement(By.xpath(elementSelctor));
				break;
			case "class":
				element = webDriver.findElement(By.className(elementSelctor));
				break;
			default:
			}
			element.click();
		} catch (Exception e) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element));
			} catch (Exception e2) {
				js.executeScript("arguments[0].click()",element);			
			}
		}
	} 
	public static void ClickOnItemsNew(String elementSelctor,String byVal){ //daniel
		WebElement element = null;
		WebDriver driver = Driver.getWebDriver();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		js = (JavascriptExecutor)driver;
		try {
			switch(byVal.toLowerCase()) {
			case "id":
				element = driver.findElement(By.id(elementSelctor));
				break;
			case "xpath":
				element = driver.findElement(By.xpath(elementSelctor));
				break;
			case "class":
				element = driver.findElement(By.className(elementSelctor));
				break;
			default:
			}
			element.click();
		} catch (Exception e) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element));
			} catch (Exception e2) {
				js.executeScript("arguments[0].click()",element);			
			}
		}
	}

	public static String getTextfromField(By byElement,String fieldName){
		String FiledVal = "";
		try {
			WebDriver webDriver = Driver.getWebDriver();		 
			FiledVal = webDriver.findElement(byElement).getText();	
			Reporter.reportStep("pass", "Get text from field "+fieldName);
		} catch (Exception e) {
			Reporter.reportStep("fail", "Not able to Get text from field "+fieldName);
			FiledVal = "";
			e.printStackTrace();
		}
		return FiledVal;
	}

	/**
	 * get value from elements
	 * @param elementSelctor
	 * @param byVal
	 * @return
	 */
	public static String getTextfromField(String elementSelctor,String byVal){
		String FiledVal = "";
		try {
			WebDriver webDriver = Driver.getWebDriver();
			switch(byVal.toLowerCase()) {
			case "id":
				FiledVal = webDriver.findElement(By.id(elementSelctor)).getText();
				break;
			case "xpath":
				FiledVal = webDriver.findElement(By.xpath(elementSelctor)).getText();
				break;
			case "class":
				FiledVal = webDriver.findElement(By.className(elementSelctor)).getText();
				break;
			default:
			}
		} catch (Exception e) {
			FiledVal = "";
		}
		return FiledVal;
	}

	/**
	 * get the count of li's in Dropdown list
	 * @param elementSelctor
	 * @param byVal
	 * @return
	 */
	public static int getCountOfDropdownList(String elementSelctor,String byVal){
		int listCount = 0;
		try {
			WebDriver webDriver = Driver.getWebDriver();
			switch(byVal.toLowerCase()) {
			case "id":
				listCount = webDriver.findElements(By.id(elementSelctor)).size();
				break;
			case "xpath":
				listCount = webDriver.findElements(By.xpath(elementSelctor)).size();
				break;
			case "class":
				listCount = webDriver.findElements(By.className(elementSelctor)).size();
				break;
			default:
			}
		} catch (Exception e) {
			listCount = 0;
		}
		return listCount;
	}

	/**
	 * OverAll Browser will be wait implicitiyly
	 * @param Time
	 * @author ilayaraja
	 */
	public static void setTimeOut(Integer Time){ //daniel
		try {
			WebDriver driver = Driver.getWebDriver();
			driver.manage().timeouts().implicitlyWait(Time, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	/**
	 * Set timeout to the driver until some element to be visible
	 * @param selectorName
	 * @param byVal
	 * @author ilayaraja
	 */
	public static void setTimeoutUntilVisibility(String selectorName,String byVal){ // daniel
		try{
			WebDriver driver = Driver.getWebDriver();
			wait = new WebDriverWait(driver, 10);
			switch(byVal.toLowerCase()) {
			case "id":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(selectorName)));
				break;
			case "xpath":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selectorName)));
				break;
			case "class":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(selectorName)));
				break;
			default:
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Finally terminate the browser
	 * @author ilayaraja
	 */
	public static void closeBrowser(){
		try {
			//webDriver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * open new tab in chrome
	 * @param urltext
	 */
	public static void openNewTab(String urltext){
		try {
			((JavascriptExecutor)webDriver).executeScript("window.open()");//open new tab in chrome
			ArrayList<String> tabs = new ArrayList<String> (webDriver.getWindowHandles());// get all the tabs opened in browser
			webDriver.switchTo().window(tabs.get(1)); //switches to new tab
			webDriver.get(urltext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Take Screenshot and store in Report
	 * testCaseResult = 0(case passed) testCaseResult = 1 (case failed)
	 * @param folderName
	 * @param ReportId
	 * @return 
	 * @throws Exception
	 */
	public static String takeSnapShot(String folderName,String ReportId,int testCaseResult) throws Exception{
		String constantrand = null;
		try {
			if((testCaseResult==1 && enabledFailureScreenshot) || (testCaseResult==0 && enabledSuccesscreenshot)){
				TakesScreenshot scrShot =((TakesScreenshot)webDriver);
				File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
				File file = new File(outputFilePath+folderName);
				file.mkdirs();
				constantrand =getRandomNumber();
				File DestFile=new File(outputFilePath+folderName+File.separator+ReportId+constantrand+".png");
				Files.copy(SrcFile, DestFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputFilePath+folderName+File.separator+ReportId+constantrand+".png";
	}


	/**
	 * Create Random Number with the limit of 1000
	 * @return
	 * @author ilayaraja
	 */
	public static String getRandomNumber(){
		int rand_int = 0;
		try{
			Random rand = new Random();
			rand_int = rand.nextInt(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
		return Integer.toString(rand_int);
	}

	/**
	 * get current Date
	 * @return
	 */
	public static String getCurrDate()  {
		SimpleDateFormat dateFormat = null;
		try {
			dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateFormat.format(new Date());
	}


	public static boolean adminTabSelection(String menue , String subMenue) {
		try { 
			waitForPageLoad();
			waitUntilElementDisplayed(By.id("side-menu"));
			Thread.sleep(3000);
			scrollUntilElementView(By.xpath("//ul[@id='side-menu']//span[text()='"+menue+"']"));
			click(By.xpath("//ul[@id='side-menu']//span[text()='"+menue+"']"), "menue");
			waitUntilElementDisplayed(By.xpath("//ul[@id='side-menu']//span[text()='"+menue+"']//parent::a//following-sibling::ul"));
			scrollUntilElementView(By.xpath("//ul[@id='side-menu']//span[text()='"+menue+"']//parent::a//following-sibling::ul//li//a[text()='"+subMenue+"']"));
			click(By.xpath("//ul[@id='side-menu']//span[text()='"+menue+"']//parent::a//following-sibling::ul//li//a[text()='"+subMenue+"']"),"sub menue");
		}catch (Exception e) {
			return false;
		}	
		return true;
	}


	/**
	 * Tab selection with menu and sub menu name
	 * @param menu
	 * @param subMenu
	 */
	public static boolean tabSelection(String menu, String subMenu) {
		boolean status =false;
		try {
			WebDriver driver = Driver.getWebDriver();
			WebElement tabScroll; 
			//wait for selection tab container 
			waitUntilElementDisplayed("//*[@id='mCSB_1_container']", 20, "xpath");
			String SideMenuStatus = driver.findElement(By.xpath("//*[@id='wrapper']/nav/div[1]")).getAttribute("class");
			// Expand Tab section
			SeleniumUtils.waitUntilElementHide("loading_screen", "id");
			if (!SideMenuStatus.contains("sideMenuExpand")) {
				driver.findElement(By.xpath("//*[@id='wrapper']/nav/div[1]/div[1]/button")).click();
			}
			tabScroll = driver.findElement(By.xpath("//li[@data-module='"+menu+"']"));
			SeleniumUtils.scrollUntilElementView("//li[@data-module='"+menu+"']", "xpath");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", tabScroll);
			//menu click
			if(waitUntilElementDisplayed("//*[@data-module='"+menu+"']", 20, "xpath")){
				click(By.xpath("//*[@data-module='"+menu+"']"), menu+" tab");
			}
			Thread.sleep(2000);
			SeleniumUtils.scrollUntilElementView("//li[@data-module='"+menu+"']//child::ul/li[@data-module='"+subMenu+"']", "xpath");
			//sub menu click
			if(waitUntilElementDisplayed("//li[@data-module='"+menu+"']//child::ul/li[@data-module='"+subMenu+"']", 20,"xpath")){
				click(By.xpath("//li[@data-module='"+menu+"']//child::ul/li[@data-module='"+subMenu+"']"), subMenu+" tab");
				status=true;
			}
			((JavascriptExecutor) driver).executeScript("$('.sideMenubtn .navbar-minimalize span').click();");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return status;
	}

	/**
	 * Tab selection with menu
	 * @param menu
	 */
	public static boolean tabSelection(String menu) {
		boolean selectionStatus = false;
		try { 
			WebDriver webDriver = Driver.getWebDriver();
			//wait for selection tab container 
			WebElement tabScroll;
			waitUntilElementDisplayed("//*[@id='mCSB_1_container']", 20, "xpath");
			String SideMenuStatus = webDriver.findElement(By.xpath("//*[@id='wrapper']/nav/div[1]")).getAttribute("class");
			// Expand Tab section
			if (!SideMenuStatus.contains("sideMenuExpand")) {
				webDriver.findElement(By.xpath("//*[@id='wrapper']/nav/div[1]/div[1]/button")).click();
			}
			tabScroll = webDriver.findElement(By.xpath("//li[@data-module='"+menu+"']"));
			SeleniumUtils.scrollUntilElementView("//li[@data-module='"+menu+"']", "xpath");

			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", tabScroll);
			//menu click
			if(waitUntilElementDisplayed("//*[@data-module='"+menu+"']", 10, "xpath")){
				click(By.xpath("//*[@data-module='"+menu+"']"), menu+" tab");
				selectionStatus=true;
			}
			((JavascriptExecutor) webDriver).executeScript("$('.sideMenubtn .navbar-minimalize span').click();");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return selectionStatus;
	}


	/**
	 * get attribute from field
	 * @param elementSelctor
	 * @param attribute
	 * @param byVal
	 * @return
	 * @author ilayaraja
	 */
	public static String getAttributefromField(String elementSelctor,String attribute, String byVal){
		String FieldVal = "";
		try {
			WebDriver webDriver = Driver.getWebDriver();
			switch(byVal.toLowerCase()) {
			case "id":
				FieldVal = webDriver.findElement(By.id(elementSelctor)).getAttribute(attribute);
				break;
			case "xpath":
				FieldVal = webDriver.findElement(By.xpath(elementSelctor)).getAttribute(attribute);
				break;
			case "class":
				FieldVal = webDriver.findElement(By.className(elementSelctor)).getAttribute(attribute);
				break;
			default:
			}
		} catch (Exception e) {
			FieldVal = "";
		}
		return FieldVal;
	}
	public static boolean scrollUntilElementView(By elementSelctor){
		try {
			WebDriver webDriver = Driver.getWebDriver();
			WebElement element = null;
			element = webDriver.findElement(elementSelctor);
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * Scroll until requested element view
	 * @param elementSelctor
	 * @param byVal\
	 * @author ilayaraja
	 */
	public static void scrollUntilElementView(String elementSelctor,String byVal){
		try {
			WebDriver webDriver = Driver.getWebDriver();
			WebElement element = null;
			switch(byVal.toLowerCase()) {
			case "id":
				element = webDriver.findElement(By.id(elementSelctor));
				break;
			case "xpath":
				element = webDriver.findElement(By.xpath(elementSelctor));
				break;
			case "class":
				element = webDriver.findElement(By.className(elementSelctor));
				break;
			default:
			}
			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrun the html button for download
	 * @param testCasePath
	 * @return
	 */
	public static String createDownloadButton(String testCasePath){
		try {
			return "Test Case Path is "+testCasePath+"<form method='get' action='"+testCasePath+"'><button type='submit' style='background-color: #555555;color:white;display: inline-block;'>Download!</button></form>";
		} catch (Exception e) {
			return "Unable to create test case path";
		}

	}


	/**
	 * Select tokens for tokenizer
	 * @param searchFiledPath
	 * @param dropDownXpath
	 * @param Value
	 * @param SplitBy -  for seach string split (ex - RESN - RPC ) here '-' is Split value so it will search for RESN
	 * @author ilayaraja
	 */
	public static boolean selectTokenizerValue(String searchFiledXpath, String dropDownXpath,String Value,String SplitBy){
		boolean status =false;
		try {          
			String SearchKey = Value.substring(0,Value.indexOf(SplitBy));          
			setValueToField(searchFiledXpath,SearchKey, "xpath");
			String elementSelctor= searchFiledXpath.replace("/input","/following-sibling::ul/li"); //get input dropdown list          
			Thread.sleep(1000);
			List<WebElement> dropDown = webDriver.findElements(By.xpath(elementSelctor));        
			String firstValue = dropDown.get(0).getText().trim();
			if(!firstValue.contains("Record")){
				for (WebElement list : dropDown) {                 
					String result = list.getText().toLowerCase().trim();
					if(result.contains(SearchKey.toLowerCase().trim())){
						list.click();
						return true;
					}
				}
			}
			else{               
				SeleniumUtils.ClearFieldValue(searchFiledXpath, "xpath");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return status;
	}

	/**
	 * Set value to the Date Picker
	 * @param selector
	 * @param dateValue
	 * @param idOrClass
	 * @author ilayaraja
	 */
	public static void addDatePickerValue(String selector, String dateValue, String idOrClassOrfixedid){
		try {
			JavascriptExecutor js = (JavascriptExecutor) Driver.getWebDriver();
			String element ="";
			switch(idOrClassOrfixedid){
			case "id":
				element = "#"+selector;
				break;
			case "class":
				element = "."+selector;
				break;
			case "fixedid":
				element = "[data-fixedid="+selector+"]";
			default:
			}
			//js.executeScript("$('"+element+"').datepicker('setDate','"+ dateValue +"').change();");
			Reporter.reportStep("pass", "Date picked - "+dateValue, false);
		} catch (Exception e) {
			Reporter.reportStep("fail", "Unable to pickdate - "+dateValue);
			Reporter.reportStep("fail", e.getMessage());

			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * get overall product toast text
	 * @return toast text
	 * @author ilayaraja
	 */
	public static String getToastMessage(){
		try {
			waitUntilElementDisplayed(By.xpath(toastMessageElement));
			return SeleniumUtils.getTextfromField(toastMessageElement, "xpath");
		} catch (Exception e) {
			return "";
		}
	}
	/**
	 * close overall product toast text
	 * @return
	 */
	public static boolean closeToastMessage(){ //daniel
		try {
			WebDriver driver = Driver.getWebDriver();
			List<WebElement> toastClose = driver.findElements(By.xpath(toastMessageCloseElement));
			for(WebElement close : toastClose){
				close.click();
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}


	/**
	 * wait until some element to be hide
	 * @param selectorName
	 * @param byVal
	 * @author ilayaraja
	 */
	public static boolean waitUntilElementHide(String selectorName,String byVal){ // daniel
		boolean status=true;	
		try{
			WebDriver driver = Driver.getWebDriver();

			wait = new WebDriverWait(driver, 35);
			switch(byVal.toLowerCase()) {
			case "id":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(selectorName)));
				break;
			case "xpath":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(selectorName)));
				break;
			case "class":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(selectorName)));
				break;
			default:
			}
		}catch(Exception e){
			return false; 
		}
		return status;
	}

	/**
	 * Select dropdown value from select filed bases on input
	 * @param selectorName
	 * @param byVal
	 * @author ilayaraja
	 */
	public static void selectOptGroupDropdownValue(String selectorName,String selectValue,String byVal){
		try{
			WebDriver webDriver = Driver.getWebDriver();
			switch(byVal.toLowerCase()) {
			case "id":
				webDriver.findElement(By.id(selectorName)).sendKeys(selectValue);
				break;
			case "xpath":
				webDriver.findElement(By.xpath(selectorName)).sendKeys(selectValue);
				break;
			case "class":
				webDriver.findElement(By.className(selectorName)).sendKeys(selectValue);
				break;
			}		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Wait for executing code to avoid async execution
	 * @param Time
	 */
	public static void scriptWaitingTime(Integer Time){
		try {
			WebDriver webDriver = Driver.getWebDriver();
			webDriver.manage().timeouts().setScriptTimeout(Time, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return Table rows size
	 * @param selectorName - upto tr
	 * @param byVal
	 * @return
	 * @throws InterruptedException 
	 */
	public static int countOfTableBodyRows(String selectorName , String byVal ){
		try {
			Thread.sleep(2000);
			WebDriver webDriver = Driver.getWebDriver();
			List<WebElement> rows=null;
			switch(byVal.toLowerCase()) {
			case "id":
				rows=webDriver.findElements(By.id(selectorName));
				break;
			case "xpath":
				rows=webDriver.findElements(By.xpath(selectorName));
				break;
			case "class":
				rows=webDriver.findElements(By.className(selectorName));
				break;
			}	
			return rows.size();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Get selected value from Dropdown
	 * @param Selector
	 * @return selected value string
	 * @author ilayaraja
	 */
	public static String getSelectedDropdownValue(String Selector, String byVal) {
		try {
			WebDriver webDriver = Driver.getWebDriver();
			Select select = null;
			switch (byVal.toLowerCase()) {
			case "id":
				select = new Select(webDriver.findElement(By.id(Selector)));
				break;
			case "xpath":
				select = new Select(webDriver.findElement(By.xpath(Selector)));
				break;
			case "class":
				select = new Select((WebElement) webDriver.findElements(By.className(Selector)));
				break;
			}
			return select.getFirstSelectedOption().getText();
		} catch (Exception e) {
			return "";
		}
	}




	/**
	 * it will return active chrome tab id as String
	 * @return
	 */
	public static String getCurrentTabId(){
		try {
			WebDriver webDriver = Driver.getWebDriver();
			return webDriver.getWindowHandle();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * switch to Chrome tab based on given id
	 * @param chrometabid
	 */
	public static void chromeTabSwitch(String chrometabid){
		try {
			WebDriver webDriver = Driver.getWebDriver();
			webDriver.switchTo().window(chrometabid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close the Chrome tab based on given id
	 * @param chrometabid
	 */
	public static void chromTabClose(String chrometabid){
		try {
			WebDriver webDriver = Driver.getWebDriver();
			webDriver.switchTo().window(chrometabid).close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	/**
	 * Return id of the Test case module to be run
	 * @return
	 * @throws InterruptedException
	 */
	public static String launchTestCasePromt(){
		try {
			SeleniumUtils.js.executeScript("var moduleTest = prompt('Enter module number that you want to execute 1.MyList 2.Feedback 3.EventManager 4.Activity 5.AccountContactVerification Or simply cancel the propmt to run all modules'); document.body.setAttribute('test-module', moduleTest);");
			Thread.sleep(10000);
			return SeleniumUtils.webDriver.findElement(By.tagName("body")).getAttribute("test-module");
		} catch (Exception e) {
			return "";
		}
	}
	/**
	 * Current page refresh, if alert present it will accept it automatically 
	 */
	public static void pageRefresh(){ // daniel
		WebDriver driver = Driver.getWebDriver();
		try {
			driver.navigate().refresh();
			Alert javascriptconfirm = webDriver.switchTo().alert();
			javascriptconfirm.accept();
		} catch (Exception e) {
			driver.navigate().refresh();
		}
	}
	/**
	 * Select the dropdown value based on given params
	 * @param SelectorValue -> xpath or id or class of select element
	 * @param selectionValue -> Option value need to be selected
	 * @param selectBy -> select by index or value or visibletext  of option 
	 * @param byVal -> class or xpath or id
	 * @author ilayaraja
	 */
	public static void dropDownItemSelect(String SelectorValue, String selectionValue, String selectBy,String byVal){
		WebElement element=null;
		try {
			WebDriver webDriver = Driver.getWebDriver();
			switch(byVal.toLowerCase()){
			case "xpath":
				element = webDriver.findElement(By.xpath(SelectorValue));
				break;
			case "id":
				element = webDriver.findElement(By.id(SelectorValue));
				break;
			case "class":
				element = webDriver.findElement(By.className(SelectorValue));
				break;
			}
			Select selctElemt=new Select(element);
			switch(selectBy.toLowerCase()){
			case "value":
				selctElemt.selectByValue(selectionValue);
				break;
			case "index":
				selctElemt.selectByIndex(Integer.parseInt(selectionValue));
				break;
			case "visibiletext":
				selctElemt.selectByVisibleText(selectionValue); //This will be case sensitive which is visible in DOM
				break;
			}
		} catch (Exception e) {
			((JavascriptExecutor)webDriver).executeScript("var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }", element,selectionValue );
		}
	}
	public static void waitUntilElementDisplayedWithReport(By elemSelector, String text) {
		try {
			WebDriver driver = Driver.getWebDriver();
			wait = new WebDriverWait(driver, 10); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(elemSelector));
			Reporter.reportStep("pass", "The Element  "+text+"  is displayed" ); 
		}catch (Exception e) {
			Reporter.reportStep("fail", "The Element  "+text+" is  not displayed at xpath : " + elemSelector ); 
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public static boolean waitUntilElementDisplayed(By elemSelector) {
		try {
			WebDriver driver = Driver.getWebDriver();
			wait = new WebDriverWait(driver, 20); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(elemSelector));
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public static Boolean waitUntilElementDisplayed(String elemSelector,int timeInSec, String byVal)
	{
		WebElement element=null;
		try {
			WebDriver webDriver = Driver.getWebDriver();
			wait = new WebDriverWait(webDriver, timeInSec);            
			switch(byVal)
			{
			case "xpath":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elemSelector)));
				element=webDriver.findElement(By.xpath(elemSelector));
				return element.isDisplayed();

			case "id":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elemSelector)));
				element=webDriver.findElement(By.id(elemSelector));
				return element.isDisplayed();

			case "class":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(elemSelector)));
				element=webDriver.findElement(By.className(elemSelector));
				return element.isDisplayed();
			}


		} catch (Exception e) {
			return checkElementDisplayedProp(elemSelector, byVal);

		}
		return false;
	}



	//	public static boolean isElementExist(String locatorType, String value, int milliseconds) {
	//        try {
	//        	
	//        	wait = new WebDriverWait(webDriver,milliseconds);
	//        	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(returnByElement(locatorType,value)));
	//           
	//            if (webDriver.findElement(returnByElement(locatorType,value)).isDisplayed()) {
	//                return true;
	//            } else {
	//                return false;
	//            }
	//        } catch (NoSuchElementException e) {
	//            return false;
	//        } catch (Exception e) {
	//            return false;
	//        }
	//     }


	/**
	 * open new tab in chrome
	 * @param role id in config (EquityHead-1,SalesPerson-2,Analyst-3,SalesAssistant-4)
	 * String CurrentId = SeleniumUtils.getCurrentTabId(); -> take last active tab id
	 * SeleniumUtils.loginWithAnotherRole(2); -> send role id mentioned Selenium prop file
	 * SeleniumUtils.chromeTabSwitch(CurrentId); -> move to old tab
	 * SeleniumUtils.chromTabClose(CurrentId); -> close thetab
	 */
	public static void loginWithAnotherRole(int roleId){
		try {
			WebDriver webDriver = Driver.getWebDriver();
			((JavascriptExecutor)webDriver).executeScript("window.open()");//open new tab in chrome
			ArrayList<String> tabs = new ArrayList<String> (webDriver.getWindowHandles());// get all the tabs opened in browser
			webDriver.switchTo().window(tabs.get(tabs.size()-1));
			webDriver.get(SeleniumProps.getProperty("ApplicationURL"));
			String[] roleCredentials = null;
			switch(roleId) {
			case 1:
				roleCredentials=commonProps.getProperty("1_EquityHead").split("\\|");
				break;
			case 2:
				roleCredentials=commonProps.getProperty("2_SalesPerson").split("\\|");
				break;
			case 3:
				roleCredentials=commonProps.getProperty("3_Analyst").split("\\|");
				break;
			case 4:
				roleCredentials=commonProps.getProperty("4_SalesAssistant").split("\\|");
				break;
			}
			//			Login_LP_1.commonLogin(roleCredentials[0],roleCredentials[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Login with Another  Role without tab switch
	 */
	public static void loginroleWithoutTabSwitch(int roleId){
		try {
			String[] roleCredentials = null;
			switch(roleId) {
			case 1:
				roleCredentials=commonProps.getProperty("1_EquityHead").split("\\|");
				break;
			case 2:
				roleCredentials=commonProps.getProperty("2_SalesPerson").split("\\|");
				break;
			case 3:
				roleCredentials=commonProps.getProperty("3_Analyst").split("\\|");
				break;
			case 4:
				roleCredentials=commonProps.getProperty("4_SalesAssistant").split("\\|");
				break;
			}
			//			Login_LP_1.commonLogin(roleCredentials[0],roleCredentials[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void createTestNode(String testCaseName) {
		try{
			Reporter.createNode(testCaseName);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * this method can split the text by (' ) and merge by contains
	 * Example //tbody//tr//child::*[xpathContaintsText("good'morning")]
	 * output : //tbody//tr//child::*[contains(text(),"good") and contains(text(),"morning")]
	 * @param good'morning
	 * @return contains(text(),"good") and contains(text(),"morning")
	 */

	public static String xpathContaintsText(String text){
		String[] fields = text.split("[' ]");
		String textx = "";
		for (int indx = 0; indx < fields.length; indx++) {
			String stext = fields[indx];
			String cont = "contains(text(),'" + stext.trim() + "') ";
			if (!textx.isEmpty()) {
				textx += " and ";
			}
			textx += cont;
		}
		return textx;
	}

	public static void hover(String hoverElement, String clickElement){
		try{
			WebDriver driver = Driver.getWebDriver();
			Actions builder = new Actions(driver);
			WebElement hovElement = driver.findElement(By.xpath(hoverElement));

			WebElement Elementclick=driver.findElement(By.xpath(clickElement));

			builder.moveToElement(hovElement).perform();
			builder.moveToElement(Elementclick).click().perform();
			Reporter.reportStep("pass", "Hover element is displayed");
		}catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("fail", "Hover element is not displayed");
			throw new RuntimeException();
		}
	}

	public static void onlyhover(String hoverElement) {
		try {
			WebDriver driver = Driver.getWebDriver();
			Actions builder = new Actions(driver);
			WebElement hovElement = driver.findElement(By.xpath(hoverElement));
			builder.moveToElement(hovElement).perform();
			threadSleep(4000);
			Reporter.reportStep("pass", "Hover element is displayed");
		}
		catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("fail", "Hover element is not displayed");
			throw new RuntimeException();
		}
	}

	public static void scrollToTop() {
		try {
			WebDriver driver = Driver.getWebDriver();
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeAllTearSheetTab() {
		scrollToTop();
		WebDriver driver = Driver.getWebDriver();
		List<WebElement> tearSheetTab = driver.findElements(By.xpath("//*[@id='panelHeadTab']//li//button"));
		for(WebElement allTab: tearSheetTab) {
			allTab.click();
		}
	}

	public static Boolean isEnable(By element, String data,boolean snap) {
		try {
			WebDriver driver = Driver.getWebDriver();
			return driver.findElement(element).isEnabled();
		}catch (Exception e) {
			return false;
		}

	}

	public static Boolean isEnable(By element, String data) {
		return isEnable(element,data,false);
	}

	/**
	 * Wait for complete loading in UI
	 * @return
	 */
	public static boolean waitForPageLoad() {
		WebDriver driver = Driver.getWebDriver();
		ExpectedCondition<Boolean> expect = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		wait = new WebDriverWait(driver, 30);
		try {
			wait.until(expect);
		} catch (Exception E) {
			return false;
		}
		return true;
	}

	public static List<String> getListOfElementst(By elemSelector, String getTextBy) {
		List<WebElement> listCount = null;
		List<String> valuesInList = new ArrayList<String>();
		WebDriver driver = null;
		try {
			driver = Driver.getWebDriver();

			listCount = driver.findElements(elemSelector);

		} catch (Exception e) {
			valuesInList = null;
		}
		if (getTextBy.contains("getAttribute")) {
			for (int i = 0; i < listCount.size(); i++) {
				//String[] values = listCount.get(i).getAttribute("aria-label").split(":");
				String value = listCount.get(i).getAttribute("value");
				//String value = values[0];
				//System.out.println("Header" + i + "is::" + headerName);
				valuesInList.add(value);
			}
		} else if (getTextBy.contains("javaScript")) {
			for (int i = 0; i < listCount.size(); i++) {
				String value = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", listCount.get(i));
				valuesInList.add(value.trim());
			}

		} else {
			for (int i = 0; i < listCount.size(); i++) {
				refreshWebElement(listCount.get(i));
				String value = listCount.get(i).getText().trim();
				//System.out.println("Header" + i + "is::" + headerName);
				valuesInList.add(value);
			} 
		}
		return valuesInList;
	}


	public static List<String> getListOfHeaderNames(By elemSelector, String getTextBy) {
		List<WebElement> listCount = null;
		List<String> valuesInList = new ArrayList<String>();
		try {
			WebDriver driver = Driver.getWebDriver();

			listCount = driver.findElements(elemSelector);

			if (getTextBy.contains("getAttribute")) {
				for (int i = 0; i < listCount.size(); i++) {
					String[] headerNameList = listCount.get(i).getAttribute("aria-label").split(":");
					String headerName = headerNameList[0].trim();
					if (!(headerName.equals(null) || headerName.equals(""))) {
						//System.out.println("Header" + i + "is::" + headerName);
						valuesInList.add(headerName);
					}
				}
			} else if (getTextBy.contains("javaScript")) {
				for (int i = 0; i < listCount.size(); i++) {
					String value = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", listCount.get(i));
					valuesInList.add(value.trim());
				}

			} else {
				for (int i = 0; i < listCount.size(); i++) {
					try {
						String headerNameList = listCount.get(i).getText();
						String headerName = headerNameList;
						if (!(headerName.equals(null) || headerName.equals(""))) {
							//System.out.println("Header" + i + "is::" + headerName);
							valuesInList.add(headerName);
						}
					} catch (Exception e) {
						String headerNameList = listCount.get(i).getText();
						String headerName = headerNameList;
						if (!(headerName.equals(null) || headerName.equals(""))) {
							//System.out.println("Header" + i + "is::" + headerName);
							valuesInList.add(headerName);
						}
						e.printStackTrace();
					}
				}
			}
			return valuesInList;

		} catch (Exception e) {
			return valuesInList = null;
		}

	}

	public static boolean isElementClickable(By elemSelector) {
		try {
			WebDriver driver = Driver.getWebDriver();
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(elemSelector));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static List<WebElement> locateElements(By elemSelector) {
		try {
			WebDriver webDriver = Driver.getWebDriver();

			return webDriver.findElements(elemSelector);

		} catch (NoSuchElementException e) {
			System.err.println("The Element with locator:" + elemSelector + "is Not Found " );
			Reporter.reportStep("fail", "The Element with locator:" + elemSelector + "is Not Found ");
			return null;
		}

	}

	public static void backSpace(String xpath, int loopCount) {
		for (int j = 0; j < loopCount; j++) {
			locateElement("xpath", xpath).sendKeys(Keys.BACK_SPACE);
		}
	}

	public static List<WebElement> waitForElementsRefresh(By elemSelector) {
		try {
			WebDriver driver = Driver.getWebDriver();
			//List<WebElement> ele = driver.findElements(elemSelector);
			wait = new WebDriverWait(driver, 20);
			return wait.until(ExpectedConditions.refreshed(new ExpectedCondition<List<WebElement>>() {
				@Override
				public List<WebElement> apply(WebDriver driver) {
					return driver.findElements(elemSelector);
				}
			}));


		} catch (Exception e) {
			return null;
		}
	}

	public static WebElement waitForElementRefresh(By elemSelector) {
		try {
			WebDriver driver = Driver.getWebDriver();
			wait = new WebDriverWait(driver, 10);
			//return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(elemSelector)));
			return wait.until(ExpectedConditions.refreshed(new ExpectedCondition<WebElement>() {
				@Override
				public WebElement apply(WebDriver driver) {
					waitUntilElementDisplayed(elemSelector);
					return driver.findElement(elemSelector);
				}
			}));
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean waitUntilAttributeToBe(By elemSelector,String attributeName,String value) {
		try {
			WebDriver driver = Driver.getWebDriver();
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.attributeToBe(elemSelector, attributeName, value));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getRandomNumWithRange(int size) {

		Random random = new Random();

		int randInt = new Random().ints(size).findFirst().getAsInt();

		int max = 9;
		int min = 1;

		for (int i=0 ; i<size ; i++) {
			max = max * 10;
			min = min * 10;
		}

		int randomtWithARange = random.nextInt(max) + min;
		String num = Integer.toString(randomtWithARange);
		System.out.println(num);

		return num;
	}

	public String getRandomStringWithRange(int stringSize) {

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = stringSize;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		// System.out.println(generatedString);

		return generatedString;
	}


	public static String getDateWithFormat(String pattern) {


		LocalDateTime now = LocalDateTime.now();

		String date = now.format(DateTimeFormatter.ofPattern(pattern)).toString();

		//String todayDate = now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString();

		return date;
	}

	public static boolean waitForElementWithTime(By ele,int waitSec) {
		try {
			WebDriver driver = Driver.getWebDriver();
			WebElement element = findElement(ele);
			wait = new WebDriverWait(driver, waitSec);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch(Exception e) {
			return false;
		}

	}

	/**
	 * this method will select value which does not matches with the value we pass in parameter
	 */
	public void selectDropDown(By xpath, String value) {

		try {
			List<WebElement> dropDownValues = locateElements(xpath);
			if (dropDownValues.size() > 0) {
				for (WebElement ele : dropDownValues) {
					String fieldValue = ele.getText();
					if (!fieldValue.equals(value)) {
						ele.click();
						Reporter.reportStep("pass", "The value " + fieldValue + " is selected");
						break;
					}
				}
			}

		} catch(Exception e) {

		}
	}

	public static boolean waitForElementHideWithTime(By elemSelector,int sec) {
		try {
			WebDriver driver = Driver.getWebDriver();
			wait = new WebDriverWait(driver, sec);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(elemSelector));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static WebElement locateElement(By elemSelector) {
		try {
			WebDriver webDriver = Driver.getWebDriver();
			return webDriver.findElement(elemSelector);
		} catch (NoSuchElementException e) {
			System.err.println("The Element Not Found with value: " + elemSelector);
		}
		return null;
	}

	public void uploadExcelFile(String excelLocation) {

		StringSelection selection = new StringSelection(excelLocation);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		try {
			Thread.sleep(3000);
			Robot robot = new Robot();	
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean closePrismUpdateToastMessage() {
		String toastMsg = "//div[@class='toast-message' and text()='Updates to Prism are available. Would you like to update? ']";
		String toastMsgClose = toastMsg + "//preceding-sibling::button";
		if (checkElementDisplayedProp(By.xpath(toastMsg))) {
			click(By.xpath(toastMsgClose), "Close Prism toast message button ");
			return true;
		} else {
			return false;
		}
	}

	public static boolean scrollUntilElementView(WebElement elementSelctor){
		try {
			WebDriver webDriver = Driver.getWebDriver();

			((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", elementSelctor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getRandomEmailId(int Stringsize, int integersize) {
		String email= getRandomStringWithRange(Stringsize)+"'"+getRandomNumWithRange(integersize)+"@mmm.com";
		return  email;
	}

	public static void CountryCodeSelect(By codeButtonClick , String queryPath) {
		try {
			click(codeButtonClick, "Code button click");
			String PhoneCodeSelect=queryPath;
			js = (JavascriptExecutor)Driver.getWebDriver();
			js.executeScript(PhoneCodeSelect);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("fail", "Country Code is not selected");
			throw new RuntimeException();
		}
	}

	public void selectValueInstantSearch(By xpath, String value) {

		try {
			List<WebElement> dropDownValues = waitForElementsRefresh(xpath);

			if (waitForVisibilityOfAllElements(xpath)) {
				if (dropDownValues.size() > 0) {
					for (WebElement ele : dropDownValues) {

						String fieldValue = ele.getText();

						if (fieldValue.equals("Record does not exist")) {
							Reporter.reportStep("fail", "No Matching Results found for the value : " + value);
						} else if (fieldValue.equals(value)) {
							ele.click();
							Reporter.reportStep("pass", "The value " + fieldValue + " is selected");
							break;
						} else if (value.equals("")){
							ele.click();
							Reporter.reportStep("pass", "The value " + fieldValue + " is selected");
							break;
						}

					}
				} else {
					Reporter.reportStep("fail", "The given xpath : " + xpath + " doesnot contains any element");
				} 
			}

		} catch(StaleElementReferenceException e) {
			Reporter.reportStep("fail", "StaleElementReferenceException exception occured" + e);
		}
	}

	public boolean waitForVisibilityOfAllElements(By elemSelector) {
		try {
			WebDriver driver = Driver.getWebDriver();
			List<WebElement> ele = driver.findElements(elemSelector);
			wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfAllElements(ele));
			return true;
		} catch (Exception e) {
			Reporter.reportStep("fail", "Expected condition for wait is failed. Failed reason : " + e );
			return false;
		}
	}

	public static String convertListToString(List<String> list, String delim) {

		if (!list.isEmpty()) {

			if (list.size()>1) {
				String value = String.join(delim, list);
				return value;
			} else {
				return list.get(0);
			}
		} else {
			return "";
		}

	}

	public int getRandamNumBtwTwoNumbers(int min, int max) {

		Random random = new Random();
		return random.nextInt(max - min) + min;

	}

	public void isSearchPlaceHolderPresent(String inputxpath, String placeHolderValue) {
		scrollUntilElementView(By.xpath(inputxpath));
		String value = locateElement(By.xpath(inputxpath)).getAttribute("placeholder").trim();
		if(value.equals(placeHolderValue)) {
			Reporter.reportStep("pass", "The Value in the placeholder is displayed - " + value);
		}else {
			Reporter.reportStep("fail", "The Value in the placeholder not displayed : " + value);
		}
	}

	public void accpectAlert() {
		Driver.getWebDriver().switchTo().alert().accept();
	}

	public String getTextInAlert() {
		String text = Driver.getWebDriver().switchTo().alert().getText();
		return text;
	}

	public boolean waitForAlertAdminConsole() {

		try {
			WebDriver driver = Driver.getWebDriver();
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (NoAlertPresentException noAlert) {
			noAlert.getMessage();
			return false;
		}

	}

	public static void threadSleep(int sec) {

		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void robotBackSpace() {

		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_BACK_SPACE);
			r.keyRelease(KeyEvent.VK_BACK_SPACE);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public int getRandomWithExclusion(Random rnd, int start, int end, Integer... exclude) {
		int random = start + rnd.nextInt(end - start + 1 - exclude.length);
		for (Integer ex : exclude) {
			if (random < ex) {
				break;
			}
			random++;
		}
		return random;
	}

	public void clickByJavaScript(By xpath) {

		WebDriver driver = Driver.getWebDriver();
		WebElement element = locateElement(xpath);
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()",element);	

	}

	public static List<String> getAllDropDownOPtions(By xpath) {
		try {
			List<String> dropDownOPtions=new ArrayList<String>();
			WebDriver driver = Driver.getWebDriver();
			Select select=new Select(driver.findElement(xpath));
			List<WebElement> allOptions = select.getOptions();
			for (WebElement options : allOptions) {
				dropDownOPtions.add(options.getText().trim());
			}
			return dropDownOPtions;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void print(Object val) {
		System.out.println(val);
	}


	public void waitForLoadingInAdminConsole() {
		waitUntilElementHide("loading_sceen", "id");
	}

	public static boolean isElementClickable(WebElement ele) {
		try {
			WebDriver driver = Driver.getWebDriver();
			wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @author hussains
	 * @return
	 */
	public static boolean isValidFormat(String format, String value, Locale locale) {
		//For Example----->boolean status=isValidFormat("MM/dd/yyyy","12/31/2022",Locale.English)
		LocalDateTime ldt = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

		try {
			ldt = LocalDateTime.parse(value, fomatter);
			String result = ldt.format(fomatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			try {
				LocalDate ld = LocalDate.parse(value, fomatter);
				String result = ld.format(fomatter);
				return result.equals(value);
			} catch (DateTimeParseException exp) {
				try {
					LocalTime lt = LocalTime.parse(value, fomatter);
					String result = lt.format(fomatter);
					return result.equals(value);
				} catch (DateTimeParseException e2) {
					// Debugging purposes
					//e2.printStackTrace();
				}
			}
		}

		return false;
	}

	/**
	 * @author shamilya
	 * This method will select date from a date picker
	 * Note : use capital (M) for month
	 * @param dateToSelect - pass a valid date 
	 * @param format - The format of date which you pass (ex : MM/dd/yyyy , yyyy-MM-dd)
	 */
	public void selectDatefromCalendar(String dateToSelect,String format) {

		String datePickerDatesDiv = "//div[@class='datepicker-days']";
		String datePickerMonthsDiv = "//div[@class='datepicker-months']";
		String datePickerYearsDiv = "//div[@class='datepicker-years']";
		String datePickerYearMonth = "//div[@class='datepicker-days']/table/thead/tr/th[@class='datepicker-switch']";
		String datePickerYear = "//div[@class='datepicker-months']/table/thead/tr/th[@class='datepicker-switch']";
		String datePickerYearStartEnd = "//div[@class='datepicker-years']/table/thead/tr/th[@class='datepicker-switch']";
		String datesList = "//div[@class='datepicker-days']/table/tbody/tr/td[@class='day']";
		String monthsList = "//div[@class='datepicker-months']/table/tbody/tr/td/span";
		String yearList = "//div[@class='datepicker-years']/table/tbody/tr/td/span[@class='year']";
		String prev = "//div[@class='datepicker-years']/table/thead/tr/th[@class='prev']";
		String next = "//div[@class='datepicker-years']/table/thead/tr/th[@class='next']";

		String startDate = "";
		startDate = changeDateFormat(format,"MM/dd/yyyy",dateToSelect);
		String[] dateSplit = startDate.split("/");

		String year = dateSplit[2];
		String month = dateSplit[0];
		String date = dateSplit[1];
		date = date.startsWith("0")? date.substring(1) : date;
		String[] monthArray = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		String[] monthShortArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep",
				"Oct", "Nov", "Dec" };
		String monthShort = monthShortArray[Integer.parseInt(month) - 1];


		String monthYearInCal = locateElement(By.xpath(datePickerYearMonth)).getText();
		String monthYear = monthArray[Integer.parseInt(month) - 1] + " " + year;


		if(checkElementDisplayedProp(By.xpath(datePickerDatesDiv))) {

			if(monthYear.equals(monthYearInCal)) {
				dateSelection(date, datesList);
				Reporter.reportStep("pass", "The given (" + dateToSelect + ") date is selected");
			} else if(year.equals(monthYearInCal.split(" ")[1])) {
				click(By.xpath(datePickerYearMonth), "Date Picker change year month ");
				if(checkElementDisplayedProp(By.xpath(datePickerMonthsDiv))) {
					dateSelection(monthShort, monthsList);
					dateSelection(date, datesList);
					Reporter.reportStep("pass", "The given (" + dateToSelect + ") date is selected");
				}
			} else {
				click(By.xpath(datePickerYearMonth), "Date Picker change year month ");
				click(By.xpath(datePickerYear), "Date Picker change year ");
				int reqDate = Integer.parseInt(year);
				if(checkElementDisplayedProp(By.xpath(datePickerYearsDiv))) {
					boolean yearLocated = false;
					while (!yearLocated) {
						String yearStartEnd = locateElement(By.xpath(datePickerYearStartEnd)).getText();
						String[] yearStartEndArr = yearStartEnd.split("-");
						int start = Integer.parseInt(yearStartEndArr[0]);
						int end = Integer.parseInt(yearStartEndArr[1]);

						if(reqDate >= start && reqDate <= end) {
							dateSelection(year, yearList);
							dateSelection(monthShort, monthsList);
							dateSelection(date, datesList);
							yearLocated = true;
							Reporter.reportStep("pass", "The given (" + dateToSelect + ") date is selected");
						} else if(reqDate > end) {
							click(By.xpath(next), "Clieck Next Button ");
						} else {
							click(By.xpath(prev), "Clieck Prev Button ");
						}
					}
				}
			}
		} else {
			Reporter.reportStep("fail", "Date Picker is not displayed");
		}

	}

	public void dateSelection(String reqValue, String location) {

		List<WebElement> dateElements = locateElements(By.xpath(location));

		for (int i = 0; i < dateElements.size(); i++) {
			//String year = dateElements.get(i).getText();
			if (dateElements.get(i).getText().equals(reqValue)) {
				dateElements.get(i).click();
				break;
			}
		}

	}
	/**
	 * @author shamilya
	 * @param dateFormat - The format of date which you pass (ex : MM/dd/yyyy)
	 * @param convertFormat - The format which you want to convert (ex : yyyy/dd/MM)
	 * @param date - The date you want to change the format (ex : 05/25/2021)
	 * @return will return a String date with format yyyy/dd/MM (ex : 2021/25/05)
	 */
	public static String changeDateFormat(String dateFormat,String convertFormat,String date) {

		DateFormat originalFormat = new SimpleDateFormat(dateFormat);
		DateFormat targetFormat = new SimpleDateFormat(convertFormat);
		Date changedDate = null;
		String formattedDate = "";
		try {
			changedDate = originalFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		formattedDate = targetFormat.format(changedDate);

		return formattedDate;
	}

	public static void checkFieldDateFormate(String strDate, String fieldName, String format) {

		if (strDate.trim().equals("")) {
			return;
		} else {
			SimpleDateFormat sdfrmt = new SimpleDateFormat(format);
			sdfrmt.setLenient(false);
			try {
				Date javaDate = sdfrmt.parse(strDate);
				Reporter.reportStep("pass", "The Date " + strDate + " in field " + fieldName + " is in valid date format ("
						+ format + ")");
			}
			/* Date format is invalid */
			catch (ParseException e) {
				Reporter.reportStep("fail", "The Date " + strDate + " in field " + fieldName + " is in valid date format ("
						+ format + ")");
			}
		}

	}
 
	/**
	 * This method is used to handle stale element reference exception
	 * @author hussain
	 * @param webelement
	 */
	public static void refreshWebElement(WebElement element) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getWebDriver()).withTimeout(30, TimeUnit.SECONDS).pollingEvery(500, TimeUnit.MILLISECONDS);
		wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(element)));
	}
	
	/**
	 * This method is used to return as webelement
	 * @author hussain
	 * @param xpath
	 */
	public WebElement getAsWebelemnt(String xpath) {
		return Driver.getWebDriver().findElement(By.xpath(xpath));
	}

}