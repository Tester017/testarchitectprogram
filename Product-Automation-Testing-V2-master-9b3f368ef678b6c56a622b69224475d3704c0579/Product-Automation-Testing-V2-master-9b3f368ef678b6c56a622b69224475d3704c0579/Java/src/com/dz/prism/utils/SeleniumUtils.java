package com.dz.prism.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.dz.prism.bo.ExcelObjectBO;
import com.dz.prism.module.employmentchange.EmploymentChangeMain;
import com.dz.prism.module.login.Login_LP_1;
import com.google.common.io.Files;

public class SeleniumUtils {
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
	public static String toastMessageCloseElement = "//div[@id='toast-container']//div";
	public static String closeTabXpath ="//*[@id='panelHeadTab']//child::button";
	public static Boolean enabledSuccesscreenshot = true;
	public static Boolean enabledFailureScreenshot = true;
	public static JavascriptExecutor js = null;
	public static Properties commonProps;
	public static WebDriverWait wait = null;
	public static boolean isproduction = true;
	public static String environmentName ;

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
	
	/**
	 * Create Report generation object and attaching the Report
	 * @author ilayaraja
	 */
	@BeforeClass
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
	/**
	 * check the checkbox is select or not
	 * @param elemSelector
	 * @param byVal
	 * @return
	 */
	public static Boolean checkBoxIsSelect(String elemSelector, String byVal)
	{
		try {
			switch(byVal.toLowerCase())
			{
				case "xpath":
					return webDriver.findElement(By.xpath(elemSelector)).isSelected();
				case "id":
					return webDriver.findElement(By.id(elemSelector)).isSelected();
				case "class":
					return webDriver.findElement(By.className(elemSelector)).isSelected();
			}
			
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	/**
	 * Clear All tokenize Input value
	 * @param searchInputElement tokenize input field xpath
	 * @return 
	 */
	public static boolean clearAllTokenizeInput(String searchInputElement){
		try{
			String elementSelector = searchInputElement.replace("input", "a");
			String checkLi = searchInputElement.replace("/input", "");
			List<WebElement> token = SeleniumUtils.webDriver.findElements(By.xpath(checkLi));
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
	
	/**
	 * Launch the Browser and set the URL to be Run
	 * @param Property
	 * @return
	 */
	public static void startBrowserDriver(String URL){
		try {
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
	
	/**
	 * Clearing filed values by byVal
	 * @param elementSelctor
	 * @param byVal
	 */
	public static void ClearFieldValue(String elementSelctor,String byVal){
		try {
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
			setTimeOut(20);
			element.click();
		} catch (Exception e) {
			try {
				js.executeScript("arguments[0].click()",element);	
				
			} catch (Exception e2) {
				wait.until(ExpectedConditions.elementToBeClickable(element));
				js.executeScript("arguments[0].click()",element);			
			}
		}
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
	public static void setTimeOut(Integer Time){
		try {
			webDriver.manage().timeouts().implicitlyWait(Time, TimeUnit.SECONDS);
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
	public static void setTimeoutUntilVisibility(String selectorName,String byVal){
		try{
			wait = new WebDriverWait(webDriver, 20);
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
		 
		}
	}
	
	/**
	 * Finally terminate the browser
	 * @author ilayaraja
	 */
	public static void closeBrowser(){
		try {
			webDriver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * flush the Report
	 * @author ilayaraja
	 */
	@AfterClass
	public static void flushReport(){
		try {
			extendReports.flush();
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

	public static ExcelObjectBO readExcelDataWithExcelObject(String xlsfilePath) throws IOException {
		ExcelObjectBO excelObjectBO = new ExcelObjectBO();
		try {
			File file = new File(xlsfilePath);
			FileInputStream fis = null;
			if (file.exists()) {
				fis = new FileInputStream(file);
			} else {
				System.out.println("Input xlsx file is not Found in (" + xlsfilePath + ")");
				return null;
			}
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fis);
			excelObjectBO.setOuterMap(readExcelData(xssfWorkbook, excelObjectBO));
			excelObjectBO.setXssfWorkbook(xssfWorkbook);
			excelObjectBO.setPath(xlsfilePath);
			return excelObjectBO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void replaceExcelObject(ExcelObjectBO excelObjectBO) {
        FileOutputStream fileOut = null;
        FileInputStream fileOutNw = null;
        try {
            fileOut = new FileOutputStream(excelObjectBO.getPath());
            excelObjectBO.getXssfWorkbook().write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        try {
            fileOut.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            excelObjectBO.getXssfWorkbook().close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
       
        try{
            fileOutNw = new FileInputStream(excelObjectBO.getPath());
            XSSFWorkbook workbook = new XSSFWorkbook(fileOutNw);
            excelObjectBO.setXssfWorkbook(workbook);
        } catch(Exception ex){
            ex.printStackTrace();
        }
       
       
        finally {
            try {
                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutNw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	public static void writeDataToExcel(ExcelObjectBO excelObjectBO, int lastColIdx)
			throws IOException, InvalidFormatException {
		try {
			XSSFSheet worksheet = null;
			worksheet = excelObjectBO.getXssfWorkbook().getSheetAt(0);
			Row row = worksheet.getRow(excelObjectBO.getRowNumber());
			row.createCell(lastColIdx).setCellValue(excelObjectBO.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private static Map<String, List<Map<String, String>>> readExcelData(XSSFWorkbook workBook,ExcelObjectBO excelObjectBO) {
		Map<String, List<Map<String, String>>> outerMap = new HashMap<String, List<Map<String, String>>>();
		Map<String, String> recordsMap = null;
		String sheetName = null;
		try {
			for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
				List<String> headerList = new ArrayList<String>();
				List<Map<String, String>> dataList = null;
				dataList = new ArrayList<Map<String, String>>();
				XSSFSheet sheet = workBook.getSheetAt(i);
				sheetName = workBook.getSheetName(i);
				XSSFRow row;
				XSSFCell cell;
				Iterator<?> rows = sheet.rowIterator();
				int rowCount = 0;
				while (rows.hasNext()) {
					int headerCnt = 0;
					recordsMap = new LinkedHashMap<String, String>();
					row = (XSSFRow) rows.next();
					if (row.getRowNum() == 0) {
						for (int headerCount = 0; headerCount < row.getLastCellNum(); headerCount++) {
							cell = row.getCell(headerCount, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
							headerList.add(cell.toString());
						}
					} else {
						int dataCount = 0;
						for (dataCount = 0; dataCount < headerList.size(); dataCount++) {
							cell = row.getCell(dataCount, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_STRING:
								recordsMap.put(headerList.get(headerCnt), cell.getRichStringCellValue().getString());
								headerCnt++;
								break;
							case Cell.CELL_TYPE_NUMERIC:
								if (DateUtil.isCellDateFormatted(cell)) {
									DateFormat dateform = new SimpleDateFormat("MM/dd/yyyy");
									java.util.Date currDate = cell.getDateCellValue();
									String res_date = dateform.format(currDate);
									recordsMap.put(headerList.get(headerCnt), res_date);
									headerCnt++;
								} else {
									recordsMap.put(headerList.get(headerCnt),
											Integer.toString((int) cell.getNumericCellValue()));
									headerCnt++;
								}
								break;
							case Cell.CELL_TYPE_FORMULA:
								recordsMap.put(headerList.get(headerCnt), cell.getCellFormula());
								headerCnt++;
								System.out.println();
								break;
							case Cell.CELL_TYPE_BLANK:
								recordsMap.put(headerList.get(headerCnt), "");
								headerCnt++;
								break;
							default:
								System.out.println();
							}
						}
						excelObjectBO.setLastcolumnNumber(dataCount);
						recordsMap.put(EXCELROWKEY, "" + (rowCount + 1));
						rowCount++;
						dataList.add(recordsMap);
					}
				}
				outerMap.put(sheetName, dataList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outerMap;
	}

	/**
	 * Read data from xlsx and return as hashmap
	 * @param xlsfilePath
	 * @return 
	 * @throws IOException
	 * @author ilayaraja
	 */
	@SuppressWarnings({ "deprecation", "resource" })
	public static Map<String, List<Map<String,String>>> readExcelData(String xlsfilePath) throws IOException{
		ExcelObjectBO excelObjectBO = new ExcelObjectBO();
	   	Map<String, List<Map<String,String>>> outerMap = new HashMap<String, List<Map<String,String>>>();
		Map<String,String> recordsMap = null;
		String sheetName = null;
		File file = new File(xlsfilePath);
		FileInputStream  fis= null;
		if (file.exists()) {
		    fis = new FileInputStream(file);
		}else{
			System.out.println("Input xlsx file is not Found in ("+xlsfilePath+")");
		}
		try
		{	            
			XSSFWorkbook workBook = new XSSFWorkbook(fis);
		    for (int i = 0; i < workBook.getNumberOfSheets(); i++)
		    {
		    	List<String> headerList =  new ArrayList<String>();
		    	List<Map<String,String>> dataList =  null;	                
		        dataList =  new ArrayList<Map<String,String>>();
		    	XSSFSheet sheet = workBook.getSheetAt(i);
		    	sheetName = workBook.getSheetName(i);
				XSSFRow row;
				XSSFCell cell;
				Iterator<?> rows = sheet.rowIterator();
				int rowCount = 0;
				while (rows.hasNext()) {
					int headerCnt = 0;
					recordsMap = new LinkedHashMap<String,String>();
					row = (XSSFRow) rows.next();
					if(row.getRowNum()==0){
						for(int headerCount=0; headerCount<row.getLastCellNum(); headerCount++) {
							cell = row.getCell(headerCount, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
							headerList.add(cell.toString());
						}
					}else{
						int dataCount = 0;
						for(dataCount=0; dataCount<headerList.size(); dataCount++) {
							cell = row.getCell(dataCount, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
							switch (cell.getCellType()) {
					            case Cell.CELL_TYPE_STRING:
					            	recordsMap.put(headerList.get(headerCnt),cell.getRichStringCellValue().getString().trim());
				                    headerCnt++;
					                break;
					            case Cell.CELL_TYPE_NUMERIC:
					                if (DateUtil.isCellDateFormatted(cell)) {
					                	DateFormat dateform    	= new SimpleDateFormat("MM/dd/yyyy");
	                            		java.util.Date currDate = cell.getDateCellValue();
	                            		String res_date 		= dateform.format(currDate);
	                                	recordsMap.put(headerList.get(headerCnt),res_date.trim());
		                                headerCnt++;
					                } else {
					                	recordsMap.put(headerList.get(headerCnt),Integer.toString((int)cell.getNumericCellValue()).trim());
		                                headerCnt++;
					                }
					                break;
					            case Cell.CELL_TYPE_FORMULA:
					            	recordsMap.put(headerList.get(headerCnt),cell.getCellFormula().trim());
	                                headerCnt++;
					                System.out.println();
					                break;
					            case Cell.CELL_TYPE_BLANK:
					            	recordsMap.put(headerList.get(headerCnt),"");
	                                headerCnt++;
					                break;
					            default:
					                System.out.println();
							}
						}
						excelObjectBO.setLastcolumnNumber(dataCount);
						recordsMap.put(EXCELROWKEY, "" + rowCount);
						rowCount++;
						dataList.add(recordsMap);
					}
				}
				outerMap.put(sheetName, dataList);
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		finally
        {
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException e)
                {         
                    e.printStackTrace();
                }
            }
        }
		return outerMap;
	}
	
	/**
	 * Tab selection with menu and sub menu name
	 * @param menu
	 * @param subMenu
	 */
	public static boolean tabSelection(String menu, String subMenu) {
		boolean status =false;
		try {
			WebElement tabScroll;
			//wait for selection tab container 
	        waitUntilElementDisplayed("//*[@id='mCSB_1_container']", 20, "xpath");
	        String SideMenuStatus = webDriver.findElement(By.xpath("//*[@id='wrapper']/nav/div[1]")).getAttribute("class");
	        // Expand Tab section
	        SeleniumUtils.waitUntilElementHide("loading_screen", "id");
	        if (!SideMenuStatus.contains("sideMenuExpand")) {
	            webDriver.findElement(By.xpath("//*[@id='wrapper']/nav/div[1]/div[1]/button")).click();
	        }
	        tabScroll = webDriver.findElement(By.xpath("//li[@data-module='"+menu+"']"));
	        SeleniumUtils.scrollUntilElementView("//li[@data-module='"+menu+"']", "xpath");
	        js.executeScript("arguments[0].scrollIntoView();", tabScroll);
	        //menu click
	        if(waitUntilElementDisplayed("//*[@data-module='"+menu+"']", 20, "xpath")){
	        	SeleniumUtils.ClickOnItems("//*[@data-module='"+menu+"']", "xpath");
	        }
	        //sub menu click
	        if(waitUntilElementDisplayed("//li[@data-module='"+menu+"']//child::ul/li[@data-module='"+subMenu+"']", 20,"xpath")){
	        	 SeleniumUtils.ClickOnItems("//li[@data-module='"+menu+"']//child::ul/li[@data-module='"+subMenu+"']", "xpath");
	        	 status=true;
	        }
	        js.executeScript("$('.sideMenubtn .navbar-minimalize span').click();");
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
	        js.executeScript("arguments[0].scrollIntoView();", tabScroll);
	        //menu click
	        if(waitUntilElementDisplayed("//*[@data-module='"+menu+"']", 10, "xpath")){
	        	SeleniumUtils.ClickOnItems("//*[@data-module='"+menu+"']", "xpath");
	        	selectionStatus=true;
	        }
	        js.executeScript("$('.sideMenubtn .navbar-minimalize span').click();");
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
    
    /**
     * Scroll until requested element view
     * @param elementSelctor
     * @param byVal\
     * @author ilayaraja
     */
    public static void scrollUntilElementView(String elementSelctor,String byVal){
    	try {
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
     * check row empty or not
     * @param row
     * @return
     */
    @SuppressWarnings("deprecation")
	public static boolean isRowEmpty(XSSFRow row){
    	try {
    		int firstCol = row.getFirstCellNum();
	        for(int cnt = 0; cnt<4 ; cnt++){
	            Cell cell = row.getCell(firstCol+cnt);
	            if(cell!=null && cell.getCellType()!=Cell.CELL_TYPE_BLANK){
	                return false;
	            }
	        }
	        return true;
		} catch (Exception e) {
			return false;
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
		   js.executeScript("$('"+element+"').datepicker('setDate','"+ dateValue +"').change();");
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
   
   /**
    * get overall product toast text
    * @return toast text
    * @author ilayaraja
    */
   public static String getToastMessage(){
	   try {
		   return SeleniumUtils.getTextfromField(toastMessageElement, "xpath");
		} catch (Exception e) {
			return "";
		}
   }
  /**
   * close overall product toast text
   * @return
   */
   public static boolean closeToastMessage(){
	   try {
		    List<WebElement> toastClose = webDriver.findElements(By.xpath(toastMessageCloseElement));
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
   public static boolean waitUntilElementHide(String selectorName,String byVal){
	   boolean status=true;	
	   try{
			wait = new WebDriverWait(webDriver, 60);
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
			e.printStackTrace();
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
	 * open new tab in chrome
	 * @param role id in config (EquityHead-1,SalesPerson-2,Analyst-3,SalesAssistant-4)
	 * String CurrentId = SeleniumUtils.getCurrentTabId(); -> take last active tab id
	 * SeleniumUtils.loginWithAnotherRole(2); -> send role id mentioned Selenium prop file
	 * SeleniumUtils.chromeTabSwitch(CurrentId); -> move to old tab
	 * SeleniumUtils.chromTabClose(CurrentId); -> close thetab
	*/
	public static void loginWithAnotherRole(int roleId){
		try {
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
			Login_LP_1.commonLogin(roleCredentials[0],roleCredentials[1]);
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
			Login_LP_1.commonLogin(roleCredentials[0],roleCredentials[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * it will return active chrome tab id as String
	 * @return
	 */
	public static String getCurrentTabId(){
		try {
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
   public static void pageRefresh(){
       try {
    	   webDriver.navigate().refresh();
    	   Alert javascriptconfirm = webDriver.switchTo().alert();
		   javascriptconfirm.accept();
       } catch (Exception e) {
    	   webDriver.navigate().refresh();
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
	/**
	 * Log result to same excel
	 * @param excelObjectBO
	 * @param value
	 * @param rowNum
	 * @param colNum
	 */
	public static void logToExcel(ExcelObjectBO excelObjectBO,String value, int rowNum,int colNum){
		try {
			excelObjectBO.setRowNumber(rowNum);
			excelObjectBO.setValue(value);
			SeleniumUtils.writeDataToExcel(excelObjectBO,colNum);
		} catch (Exception e) {
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
	
	public static void closeAllTab() {
		try{
			 
			List<WebElement> closeTab = SeleniumUtils.webDriver.findElements(By.xpath(closeTabXpath));
			 for(WebElement close: closeTab){
				  close.click();
			 }
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
public static Boolean waitUntilElementDisplayed(String elemSelector,int timeInSec, String byVal)
	{
	WebElement element=null;
		try {
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
	public static void hover(String hoverElement, String clickElement){
		try{
			 Actions builder = new Actions(webDriver);
			 WebElement hovElement = webDriver.findElement(By.xpath(hoverElement));
			 
			 WebElement Elementclick=webDriver.findElement(By.xpath(clickElement));
			 
			 builder.moveToElement(hovElement).perform();
			 builder.moveToElement(Elementclick).click().perform();
		}catch (Exception e) {
			 e.printStackTrace();
		}
	}
	public static void switchToNewWindow(){
		try{
			Set<String> allWindowsId = webDriver.getWindowHandles();
			for(String win : allWindowsId){
				webDriver.switchTo().window(win);
				System.out.println(webDriver.switchTo().window(win).getTitle());
			}
			
		}catch (Exception e) {
			 e.printStackTrace();
		}
    }
	public static void createTestNode(String testCaseName,String description) {
		try{
			SeleniumUtils.testCase = SeleniumUtils.extendReports.createTest(testCaseName);
			SeleniumUtils.childTest = SeleniumUtils.testCase
					.createNode(description);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebElement locateElement(String locatorType, String value) {
        try {
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
   
    public static By returnByElement(String locatorType, String value) {
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
   
    public static boolean isElementExist(String locatorType, String value, int milliseconds) {
        try {
           
            wait = new WebDriverWait(webDriver,milliseconds);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(returnByElement(locatorType,value)));
          
            if (webDriver.findElement(returnByElement(locatorType,value)).isDisplayed()) {
                return true;
            } else {
                return false;
            }
        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
     }
	
	
}