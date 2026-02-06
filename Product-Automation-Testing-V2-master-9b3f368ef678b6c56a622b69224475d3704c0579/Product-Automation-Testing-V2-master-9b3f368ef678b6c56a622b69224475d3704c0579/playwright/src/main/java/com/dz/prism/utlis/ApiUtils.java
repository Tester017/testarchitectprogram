package com.dz.prism.utlis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.Range;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.dz.core.common.utils.Reporter;
import com.dz.prism.bo.utlis.PeriodFilterBo;
import com.dz.prism.constant.ProductConstant;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


public class ApiUtils  { 

	public static final String READERSHIP_FLT_KEY = "READERSHIP";
	public static final String REVENUE_FLT_KEY = "REVENUE";
	public static final String MAR_FLT_KEY = "MAR";
	public static final String DASHBOARD_FLT_KEY = "DASHBOARD";
	public static final String ACTIVITY_FLT_KEY = "ACTIVITY";
	public static final String RESEARCHLIBRARY_FLT_KEY = "RESEARCHLIBRARY";
	public static final String PERIOD_TYPE_YTD = "YTD";
	public static final String PERIOD_TYPE_PREV_YEAR = "Previous Year";
	public static final String PERIOD_TYPE_PREV_2_YEAR = "Previous 2 Year";
	public static final String PERIOD_TYPE_4WK = "4WK";
	public static final String PERIOD_TYPE_CUSTOM = "custom";
	public static final String PERIOD_TYPE_MTD = "MTD";
	public static final String TRADE_FLT_KEY ="TRADE";
	public static final String PERIOD_TYPE_DATE = "Date";
	public static final String API_PATH_KEY = "endpoints";
	public static final String READERSHIPEMAIL_FLT_KEY="EMAILREADERSHIP";
	public static final String ROI_FLT_KEY = "ROI";
	public static final String PERIOD_TYPE_TODAY = "Today";
	public static final String PERIOD_TYPE_YESTERDAY = "Yesterday";
	public static final String PERIOD_TYPE_LAST7DAYS = "Last 7 days";
	public static final String PERIOD_TYPE_NEXT7DAYS = "Next 7 days";
	public static final String ACCOUNT_SEARCH_CODE = "AC";
	public static final String TICKER_SEARCH_CODE = "TC";
	public static final String SERVICE_SEARCH_CODE = "SERV";
	public static final String SUPER_SEARCH_CODE = "SAC";
	public static final String IB_DEAL_LIST_V2_FLT_KEY= "DEALLISTV2";
	public static final String PERIOD_TYPE_ALL = "ALL";
	public static final String IB_CLOSED_DEALS = "CLOSEDDEALS";
	public static final String CALENDAR_FLT_KEY = "CALENDAR";
	public static final String PERIOD_TYPE_NEXT30DAYS = "Next 30 days";
	public static final String PERIOD_TYPE_NEXT60DAYS = "Next 60 days";
	public static final String PERIOD_TYPE_NEXT90DAYS = "Next 90 days";
	public static final String PERIOD_TYPE_PREV_MTD= "Prev MTD";
	public static final String PERIOD_TYPE_PREV_DATE= "Prev DATE";
	public static final String PERIOD_TYPE_PREV_YEAR_TILL_DATE = "Prev YTD";
	public static final String PERIOD_TYPE_PREVIOUS_DAY = "Previous Day";
	public static final String IB_SUMMARYV2_FLT_KEY ="IBSUMMARYV2";
	
	public static String getNumberfromText(String value) {
		try {
			Pattern regex = Pattern.compile("(\\d*\\.?\\d+)");
			Matcher matcher = regex.matcher(value);
			String total="";
			while(matcher.find()){
				//System.out.println(matcher.group());
				total+=matcher.group(); 
			}
			if(total.equalsIgnoreCase("")) {
				total ="0";
			}
			return total;

		}catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("fail", "Fail to get number from Text : '"+value+"'");
		} 
		return "0";
	}


	public static String getObjectValueAsString(JsonObject accounDetails, String key) {
		if (accounDetails == null) {
			Reporter.reportStep("Json Object is null", "Info");
			return "";
		}
		try {
			if (accounDetails.has(key)) {

				if (accounDetails.get(key).isJsonNull()) {
					return "";
				} 
				return accounDetails.get(key).getAsString().trim();
			} else {
				if (key.equals("reason")) {
					return accounDetails.get("reasons").getAsString();
				} else {
					Reporter.reportStep("The " + key +" key is not present in object ", "info"); 
					Reporter.reportStep("JsonObject : " + accounDetails, "info"); 
					return "";
				}
			}
		} catch (Exception e) {
			Reporter.reportStep("The " + key +" key is not present in object ", "info"); 
			Reporter.reportStep("JsonObject : " + accounDetails, "info");  
			return "";
		}
	}

	public static double convertToDouble(String value) {
		try {
			return Double.parseDouble(value.replaceAll("\"", ""));
		}catch (Exception e) {
			return 0;
		} 
	}
	public static boolean checkKeyPresentInMap(Map<String, Double> calculatedSum, String key) {
		try {
			double value= calculatedSum.get(key.trim());
			return true;
		}catch (Exception e) {
			return false;
		}

	}

	public static void compareDoubleValue(String columnName, double expValue, double actValue) {
		DecimalFormat df = new DecimalFormat(".00");
		Range<Double> calucaltedRange = Range.between(Math.abs(expValue)-ProductConstant.maxAccDifferent,Math.abs(expValue)+ProductConstant.maxAccDifferent);

		if(calucaltedRange.contains(Math.abs(actValue))) {
			Reporter.reportStep("The "+ columnName + "'s value : " + df.format(expValue)+" is matched.", "pass");
		} else {
			Reporter.reportStep("The "+ columnName + "'s value is not matched. Expected value is : "
					+ df.format(expValue) + " .Actual value is : " + df.format(actValue), "fail");
		}

	}


	public static boolean checkObjectIsPresent(JsonObject accountDetails, String key) {
		try {
			String value = accountDetails.get(key).toString();
			return true;
		}catch (Exception e) {
			//e.printStackTrace();
		}
		return false;
	}

	public static String getOnlyTextFromString(String text) {
		String extract = text.replaceAll("[^a-z A-Z]+", "");
		return extract;
	}

	public String getValueFromKey(Map<String, String> map, String key) {
		try {
			String val = map.get(key);
			if(val!=null) {
				return val;
			}else {
				return "";
			}

		}catch(Exception e) {
			return "";
		}


	}


	public static String getValueByJsoupAttr(String text, String tagName, String attrName) {
		if ( !(text == null || text.equals("") || text.equals("-"))) {
			//if (!text.equals("")&&!text.equals("-")&&!text.equals(null)) {
			try {
				Document document = Jsoup.parse(text);
				Elements link = document.getElementsByTag(tagName);
				if (link.size() > 0) {
					String value = link.get(0).attr(attrName);
					//text = link.get(0).attr(attrName);
					return value;
				} else {
					return text.trim();
				}

			} catch (Exception e) {
				Reporter.reportStep("Invaild html is passed to Jsoup parse :: " + text, "fail");
				return "";
			}

		} else {
			return "";
		}
	}

	public static String getValueByJsoupText(String text, String tagName) {

		if ( !(text == null || text.equals("") || text.equals("-"))) {
			//if (!text.equals("")&&!text.equals("-")&&!text.equals(null)) {
			if (text.contains(tagName)) {
				Document document = Jsoup.parse(text);
				Elements link = document.getElementsByTag(tagName);
				String value = link.get(0).text();
				return value;
			}else {
				return text.trim();
			}
		}else {
			return "";
		}
	}

	public static Double getObjectValueAsDouble(JsonObject accounDetails, String key) {
		if (accounDetails == null) {
			Reporter.reportStep("Json Object is null", "Info");
			return 0.0;
		}
		try {
			if (accounDetails.has(key)) {
				if (accounDetails.get(key).isJsonNull() || accounDetails.get(key).getAsString().equals("")) {
					return 0.00;
				}
				return accounDetails.get(key).getAsDouble();
			} else {
				Reporter.reportStep("The " + key +" key is not present in object ", "info"); 
				Reporter.reportStep("JsonObject : " + accounDetails, "info"); 
				return 0.00;
			}

		} catch (Exception e) {
			Reporter.reportStep("Expection Occurred while converting value to Double", "fail");
			Reporter.reportStep(e.getMessage(), "info"); 
			return 0.00;
		}
	}


	public static void compareTwoString(String expValue, String actValue, String colName) {

		if (expValue != null && actValue != null) {

			if (expValue.equals(actValue)) {
				Reporter.reportStep("The " + colName + "'s value is matched Actual Value : "+actValue+" matched with Exp : "+expValue, "pass");
			} else {
				Reporter.reportStep("The " + colName + "'s value is not matched. Expected value is : "
						+ expValue + ". But Actual value is : " + actValue, "fail");
			}
		}
	}


	/** @author shamilya
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
			Reporter.reportStep("The date :" + date + " cannot be converted to format : " + convertFormat, "fail");
			e.printStackTrace();
		}
		formattedDate = targetFormat.format(changedDate);

		return formattedDate;
	}

	public Double convertStringToDouble(String value) {
		return Double.valueOf(value.replaceAll("[^\\d.]",""));
	}

	public static String getValueByJsoupTextByIndex(String text, String tagName,int index) {
		Document document = Jsoup.parse(text);
		Elements link = document.getElementsByTag(tagName);
		String value = link.get(index).text();
		return value;
	}

	public static void compareExactIntValue(String columnName, int expValue, int actValue) {

		if(expValue == actValue) {
			Reporter.reportStep("The "+ columnName + "'s value : " + expValue +" is matched.", "pass");
		} else {
			Reporter.reportStep("The "+ columnName + "'s value is not matched. Expected value is : "
					+ expValue + " . Actual value is : " + actValue , "fail");
		}
	}

	public static int getObjectValueAsInt(JsonObject accounDetails, String key) {
		if (accounDetails == null) {
			Reporter.reportStep("Json Object is null", "Info");
			return 0;
		}
		try {
			String value = accounDetails.get(key).getAsString();
			if (value.equals("-")) {
				return 0;
			}
			return accounDetails.get(key).getAsInt();
		}catch (Exception e) {
			Reporter.reportStep(key +" : key missing in object", "info"); 
		}
		return 0;
	}

	public static int getRandamNumBtwTwoNumbers(int min, int max) {

		Random random = new Random();
		return random.nextInt(max - min) + min;

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

	/**
	 * This method is used to get current date with pattern you want
	 * @param pattern - Pass the Date format which you want (Eg : MM/dd/yyyy HH:mm)
	 * @return - Will return the current date in string
	 * @author shamilya
	 */
	public static String getCurrentDateWithFormat(String pattern) {

		LocalDateTime now = LocalDateTime.now();

		String date = now.format(DateTimeFormatter.ofPattern(pattern)).toString();

		return date;

	}

	public static String getPerviousYearNumber() {

		String curDate = getCurrentDateWithFormat("MM/dd/yyyy");

		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
		LocalDate date = LocalDate.parse(curDate, format);

		LocalDate currMonthDate = date.minusYears(1);

		String prevYearNum = Integer.toString(currMonthDate.getYear());
		System.err.println();
		return prevYearNum;
	}

	public void threadSleep(long waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean compareTwoListValuesExactly(List<String> actListValues,List<String> expListValues) {

		if (!(isListNullOrEmpty(expListValues) || isListNullOrEmpty(actListValues))) {
			List<String> copyOfListOne = new ArrayList<String>(actListValues);
			List<String> copyOfListTwo =  new ArrayList<String>(expListValues);

			copyOfListOne.removeAll(expListValues);
			copyOfListTwo.removeAll(actListValues);

			if (copyOfListOne.isEmpty() && copyOfListTwo.isEmpty()) {
				return true;
			} else {
				if (!copyOfListOne.isEmpty()) {
					Reporter.reportStep("Invalid Data is", "fail");
					for (String value : copyOfListOne) {
						Reporter.reportStep(value, "fail");
					}
				}

				if (!copyOfListTwo.isEmpty()) {
					Reporter.reportStep("Missing Data is", "fail");
					for (String value : copyOfListTwo) {
						Reporter.reportStep(value, "fail");
					}
				}
				return false;
			}
		} else {
			Reporter.reportStep("Info", "Expected value is :" + expListValues);
			Reporter.reportStep("Info", "Actuval value is :" + actListValues);
			return false;
		}
	}
	public static boolean isListNullOrEmpty(List<String> list) {

		if (list == null || list.isEmpty()) {
			Reporter.reportStep("info", "List values are empty");
			return true;
		} else {
			return false;
		}
	}

	public static JsonArray getObjectAsJsonArray(JsonObject accounDetails, String key) {
		JsonArray jsonArr = new JsonArray();
		if (accounDetails == null) {
			Reporter.reportStep("Json Object is null", "Info");
			return jsonArr;
		}
		try {

			if (accounDetails.has(key)) {
				if (accounDetails.get(key).isJsonNull()) {
					return jsonArr;
				} 
				return accounDetails.get(key).getAsJsonArray();
			} else {
				if (key.equals("reason")) {
					return accounDetails.get("reasons").getAsJsonArray();
				} else {
					Reporter.reportStep("The " + key +" key is not present in object ", "info"); 
					Reporter.reportStep("JsonObject : " + accounDetails, "info"); 
					return jsonArr;
				}
			}
		} catch (Exception e) {
			Reporter.reportStep("The " + key +" key is not present in object ", "info"); 
			Reporter.reportStep("JsonObject is : " + accounDetails, "info");  
			return jsonArr;
		}
	}

	public static JsonObject getAsJsonObject(JsonObject accounDetails, String key) {
		JsonObject jsonArr = new JsonObject();
		try {

			if (accounDetails.has(key)) {

				if (accounDetails.get(key).isJsonNull()) {
					return jsonArr;
				} 
				return accounDetails.get(key).getAsJsonObject();
			} else {
				if (key.equals("reason")) {
					return accounDetails.get("reasons").getAsJsonObject();
				} else {
					Reporter.reportStep("The " + key +" key is not present in object ", "info"); 
					Reporter.reportStep("JsonObject : " + accounDetails, "info"); 
					return jsonArr;
				}
			}
		} catch (Exception e) {
			Reporter.reportStep("The " + key +" key is not present in object ", "info"); 
			Reporter.reportStep("JsonObject is : " + accounDetails, "info");  
			return jsonArr;
		}
	}

	public static List<PeriodFilterBo> getPeriodFilterBO(String key) {
		List<PeriodFilterBo> periodList  = new LinkedList<>();

		try {
			JsonObject jsonObj;
			if (ProductConstant.isProductionMode) {
				jsonObj = (JsonObject) JsonParser.parseReader(new FileReader("./src/test/resources/Configuration/EnvironmentConfig/"+ProductConstant.PROD_FOLDER+"/"+ProductConstant.environmentName+"/JsonFiles/PeriodFilterJson.json"));
			} else {
				jsonObj = (JsonObject) JsonParser.parseReader(new FileReader("./src/test/resources/Configuration/EnvironmentConfig/"+ProductConstant.UAT_FOLDER+"/"+ProductConstant.environmentName+"/JsonFiles/PeriodFilterJson.json"));
			}

			JsonArray periodFilterArray = getObjectAsJsonArray(jsonObj, key);

			for(int i=0;i<periodFilterArray.size();i++) {
				JsonObject filter = periodFilterArray.get(i).getAsJsonObject();;
				PeriodFilterBo period = new PeriodFilterBo();
				period.setType(getObjectValueAsString(filter, "type"));
				period.setPeriod(getObjectValueAsString(filter, "period"));
				period.setConds(getObjectValueAsString(filter, "conds"));
				period.setStartdate(getObjectValueAsString(filter, "startdate") );
				period.setEnddate(getObjectValueAsString(filter, "enddate") );
				period.setPeriodType(getObjectValueAsString(filter, "periodType") );
				periodList.add(period);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return periodList;
	}

	public static PeriodFilterBo getParticularPeriodFilterBO(String key, String periodType) {
		PeriodFilterBo periodFilterBo = new PeriodFilterBo();
		List<PeriodFilterBo> periodFilterBos = getPeriodFilterBO(key);
		OptionalInt indexOpt = IntStream.range(0, periodFilterBos.size())
				.filter(i -> periodType.equals(periodFilterBos.get(i).getPeriodType()))
				.findFirst();
		if (indexOpt.isPresent()) {
			int k = indexOpt.getAsInt();
			periodFilterBo = periodFilterBos.get(k);
		}
		return periodFilterBo;
	}

	public static Stream<JsonElement> convertJsonArrayToStream(JsonArray arr) {
		Stream<JsonElement> stream = StreamSupport.stream(arr.spliterator(), true);
		return stream;
	}

	public static JsonArray convertListOfJsonEleToJsonArray(List<JsonElement> json) {
		Gson gson = new Gson();
		JsonElement element = gson.toJsonTree(json, new TypeToken<List<JsonElement>>() {}.getType());

		if (!element.isJsonArray() ) {
			Reporter.reportStep("Can't convert List<JsonElement> to JsonArray. Method Name : convertListOfJsonEleToJsonElement() in ApiUtils" , "fail");
			return null;
		}
		JsonArray arr = element.getAsJsonArray();
		return arr;
	}

	public static JsonObject convertJsonEleToJsonObject(Optional<JsonElement> jsonEle) {
		if (jsonEle.isPresent()) {
			JsonObject value = jsonEle.get().getAsJsonObject();
			//String id = getInteractionId(value);
			return value;
		} else {
			return null;
		}
	}

	public static boolean isValidHtmlString(String text) {

		//if (!text.equals("")&&!text.equals("-")&&!text.equals(null)) {
		try {
			Pattern htmlPattern = Pattern.compile(".*\\<[^>]+>.*", Pattern.DOTALL);
			boolean isHTML = htmlPattern.matcher(text).matches();
			return isHTML;
		} catch (Exception e) {
			return false;
		}

	}

	public static int getMaxAccCheckCount(int actSize) {
		int loopCount  = 0;
		if (actSize < ProductConstant.maxAccCheck) {
			loopCount = actSize;
		} else {
			loopCount = ProductConstant.maxAccCheck;
		}
		return loopCount;
	}

	public static String getValueByJsoupAttr(String text, String tagName, String attrName, int indexPos) {
		if ( !(text == null || text.equals("") || text.equals("-"))) {
			//if (!text.equals("")&&!text.equals("-")&&!text.equals(null)) {
			try {
				Document document = Jsoup.parse(text);
				Elements link = document.getElementsByTag(tagName);
				if (link.size() > 0) {
					String value = link.get(indexPos).attr(attrName);
					//text = link.get(0).attr(attrName);
					return value;
				} else {
					return text.trim();
				}

			} catch (Exception e) {
				Reporter.reportStep("Invaild html is passed to Jsoup parse :: " + text, "fail");
				return "";
			}

		} else {
			return "";
		}
	}

	public static JsonObject convertArrayToJsonObject(JsonElement jsonElement) {
		return jsonElement.getAsJsonObject();
	}

	public static JsonArray convertJsonEleToJsonArray(Optional<JsonElement> jsonEle) {
		if (jsonEle.isPresent()) {
			JsonArray value = jsonEle.get().getAsJsonArray();
			//String id = getInteractionId(value);
			return value;
		} else {
			return null;
		}
	}

	public static JsonObject filterParticularJson(String fieldJsonKey,String fieldJsonValue,JsonArray lastUpdatedHistory) {
		if (!(lastUpdatedHistory == null || lastUpdatedHistory.isEmpty()) ) {
			Stream<JsonElement> stream = ApiUtils.convertJsonArrayToStream(lastUpdatedHistory);
			Optional<JsonElement> fieldObj = stream.filter((history) -> {
				String value = ApiUtils.getObjectValueAsString(history.getAsJsonObject(), fieldJsonKey).trim();
				if (value.equals(fieldJsonValue)) {
					return true;
				}
				return false;
			}).findFirst();

			if (fieldObj.isPresent()) {
				return fieldObj.get().getAsJsonObject();
			} else {
				Reporter.reportStep("No history details is found for field name : "+fieldJsonValue, "Info");
				return null;
			}
		}
		return null;
	}
	
	/** @author Shamily
	 * @param jsonArray - Table JSON Array
	 * @param String - Table Column Name Json Key
	 * @return Set of String
	 */
	public static Set<String> getTableColValueAsSet(JsonArray jsonArray, String key) {
		if(jsonArray!=null) {
			Stream<JsonElement> getCTSFundsTableData = ApiUtils.convertJsonArrayToStream(jsonArray);
			Set<String> values = new HashSet<String>();
			getCTSFundsTableData.forEach((data) -> {
				String value = ApiUtils.getObjectValueAsString(data.getAsJsonObject(), key).trim();
				if (!(value.equals("") || value.equals("-"))) {
					values.add(value);
				}
			});
			return values;
		} else {
			return null;
		}
	}
	
	/** @author Shamily
	 * @param jsonArray - Table JSON Array
	 * @param String - Table Column Name Json Key
	 * @param String - HTML Tag name  
	 * @return Set of String
	 */
	public static Set<String> getFundsTabColHtmlValueAsSet(JsonArray jsonArray, String key, String tagName) {
		if(jsonArray!=null) {
			Stream<JsonElement> getCTSFundsTableData = ApiUtils.convertJsonArrayToStream(jsonArray);
			Set<String> values = new HashSet<String>();
			getCTSFundsTableData.forEach((data) -> {
				String value = ApiUtils.getObjectValueAsString(data.getAsJsonObject(), key).trim();
				if (!(value.equals("") || value.equals("-"))) {
					if (ApiUtils.isValidHtmlString(value)) {
						String actValue = ApiUtils.getValueByJsoupText(value, tagName);
						values.add(actValue);
					}
				}
			});
			return values;
		} else {
			return null;
		}
	}
	public static Map<String, String> getAPIPayLoads(String periodTypeKey, String folderPath) {
		Map<String, String> payLoads = new HashMap<String, String>();
		try {
			JsonObject jsonObj;
			if (ProductConstant.isProductionMode) {
				jsonObj = (JsonObject) JsonParser.parseReader(new FileReader("./src/test/resources/Configuration/EnvironmentConfig/"+ProductConstant.PROD_FOLDER+"/"+ProductConstant.environmentName+"/JsonFiles/" + folderPath + ".json"));
			} else {
				jsonObj = (JsonObject) JsonParser.parseReader(new FileReader("./src/test/resources/Configuration/EnvironmentConfig/"+ProductConstant.UAT_FOLDER+"/"+ProductConstant.environmentName+"/JsonFiles/" + folderPath + ".json"));
			}
			
			JsonObject roleObj = getAsJsonObject(jsonObj, ProductConstant.role);
			//JsonObject roleObj = getAsJsonObject(jsonObj, ProductConstant.getProductRole());
			
			String endPoint = getObjectValueAsString(roleObj, "endpoints");
			payLoads.put("endpoints", endPoint);
			
			JsonArray periodFilterArray = getObjectAsJsonArray(roleObj, "payLoad");
			System.out.println(periodTypeKey);
			Stream<JsonElement> periodStream = convertJsonArrayToStream(periodFilterArray);
			Optional<JsonElement> reqPeriod =	periodStream.filter( (pay) -> {
					JsonObject obj = pay.getAsJsonObject();
					String periodType = getObjectValueAsString(obj, "periodType");
					return periodType.equals(periodTypeKey);
				}).findFirst();
			
			if (reqPeriod.isPresent()) {
				JsonObject reqPeriodObj = reqPeriod.get().getAsJsonObject();
				Set<String> objKeys = reqPeriodObj.keySet();
				for (String apiKey : objKeys) {
					String value = getObjectValueAsString(reqPeriodObj, apiKey);
					payLoads.put(apiKey, value);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return payLoads;
	}
	
	public static PeriodFilterBo setCustomDates(PeriodFilterBo periodFilterBoAct, String periodType) {
		if (periodType.equals(PERIOD_TYPE_DATE)) {
			String date = periodFilterBoAct.getPeriod();
			String dateChanged = changeDateFormat("MM/dd/yyyy", "yyyy-MM-dd", date);
			periodFilterBoAct.setStartdate(dateChanged);
			periodFilterBoAct.setEnddate(dateChanged);
		} else if (periodType.equals(PERIOD_TYPE_CUSTOM)) {
			return periodFilterBoAct;
		} else {
			PeriodFilterBo periodFilterBoExp = PeriodFilterUtils.constructPerioddates(periodType);
			periodFilterBoAct.setStartdate(periodFilterBoExp.getStartdate());
			periodFilterBoAct.setEnddate(periodFilterBoExp.getEnddate());
		}
		
		return periodFilterBoAct;
	}
	/** @author Aravindh
	 * @param jsonArray - JSON Array
	 * @param String -  Json Key
	 * @param String - value in that key  
	 * @return First JsonObject which has that value
	 */
	public static JsonObject getObjectfromArray(JsonArray jsonarray,String key,String keyValue) {
		if (jsonarray!=null&&key!=null&&keyValue!=null) {
			for(Object obj : jsonarray){
				JsonObject jsonobj = (JsonObject) obj;
				String eachvalue = ApiUtils.getObjectValueAsString(jsonobj,key);
				if(keyValue.equals(eachvalue)) {
					return jsonobj;
				}
		}
			
	}
		
		return null;
	}

	public static boolean getObjectValueAsBoolean(JsonObject accounDetails, String key) {
		if (accounDetails == null) {
			Reporter.reportStep("Json Object is null", "Info");
			return false;
		}
		try {
			if (accounDetails.has(key)) {
				return accounDetails.get(key).getAsBoolean();
			} else {
				Reporter.reportStep("The " + key +" key is not present in object ", "info"); 
				Reporter.reportStep("JsonObject : " + accounDetails, "info"); 
				return false;
			}
		} catch (Exception e) {
			Reporter.reportStep("The " + key +" key is not present in object ", "info"); 
			Reporter.reportStep("JsonObject : " + accounDetails, "info");  
			return false;
		}
	}
	
	public static JsonObject filterParticularJsonByJsoupText(String fieldJsonKey,String fieldJsonValue,JsonArray lastUpdatedHistory,String tagName) {
		if (!(lastUpdatedHistory == null || lastUpdatedHistory.isEmpty()) ) {
			Stream<JsonElement> stream = ApiUtils.convertJsonArrayToStream(lastUpdatedHistory);
			Optional<JsonElement> fieldObj = stream.filter((history) -> {
				String value = ApiUtils.getObjectValueAsString(history.getAsJsonObject(), fieldJsonKey).trim();
				if (isValidHtmlString(value)) {
					String valueByJsoupText = ApiUtils.getValueByJsoupText(value, tagName);
					if (valueByJsoupText.equals(fieldJsonValue)) {
						return true;
					}
				}
				if (value.equals(fieldJsonValue)) {
					return true;
				}
				return false;
			}).findFirst();

			if (fieldObj.isPresent()) {
				return fieldObj.get().getAsJsonObject();
			} else {
				Reporter.reportStep("No history details is found for field name : "+fieldJsonValue, "Info");
				return null;
			}
		}
		return null;
	}
	
	public static PeriodFilterBo getParticularIBPeriodFilterBO(String key, String periodType) {
		PeriodFilterBo periodFilterBo = new PeriodFilterBo();
		List<PeriodFilterBo> periodFilterBos = getIBPeriodFilterBO(key);
		OptionalInt indexOpt = IntStream.range(0, periodFilterBos.size())
				.filter(i -> periodType.equals(periodFilterBos.get(i).getPeriodType()))
				.findFirst();
		if (indexOpt.isPresent()) {
			int k = indexOpt.getAsInt();
			periodFilterBo = periodFilterBos.get(k);
		}
		return periodFilterBo;
	}
	
	public static List<PeriodFilterBo> getIBPeriodFilterBO(String key) {
		List<PeriodFilterBo> periodList  = new LinkedList<>();

		try {
			JsonObject jsonObj;
			if (ProductConstant.isProductionMode) {
				jsonObj = (JsonObject) JsonParser.parseReader(new FileReader("./src/test/resources/Configuration/EnvironmentConfig/"+ProductConstant.PROD_FOLDER+"/"+ProductConstant.environmentName+"/JsonFiles/IBPeriodFilterJson.json"));
			} else {
				jsonObj = (JsonObject) JsonParser.parseReader(new FileReader("./src/test/resources/Configuration/EnvironmentConfig/"+ProductConstant.UAT_FOLDER+"/"+ProductConstant.environmentName+"/JsonFiles/IBPeriodFilterJson.json"));
			}

			JsonArray periodFilterArray = getObjectAsJsonArray(jsonObj, key);

			for(int i=0;i<periodFilterArray.size();i++) {
				JsonObject filter = periodFilterArray.get(i).getAsJsonObject();;
				PeriodFilterBo period = new PeriodFilterBo();
				period.setType(getObjectValueAsString(filter, "type"));
				period.setPeriod(getObjectValueAsString(filter, "period"));
				period.setConds(getObjectValueAsString(filter, "conds"));
				period.setStartdate(getObjectValueAsString(filter, "startdate") );
				period.setEnddate(getObjectValueAsString(filter, "enddate") );
				period.setPeriodType(getObjectValueAsString(filter, "periodType") );
				periodList.add(period);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return periodList;
	}
	
	public static PeriodFilterBo getPrevYTDPeriodFilterBO(String key, String periodType) {
		PeriodFilterBo periodFilterBo = new PeriodFilterBo();
		List<PeriodFilterBo> periodFilterBos = getPeriodFilterBO(key);
		OptionalInt indexOpt = IntStream.range(0, periodFilterBos.size())
				.filter(i -> ApiUtils.PERIOD_TYPE_DATE.equals(periodFilterBos.get(i).getPeriodType()))
				.findFirst();
		if (indexOpt.isPresent()) {
			int k = indexOpt.getAsInt();
			periodFilterBo = periodFilterBos.get(k);
			periodFilterBo = PeriodFilterUtils.constructPerioddatesBasedOnDate(periodFilterBo.getPeriod(), key, periodType);
		}
		return periodFilterBo;
	}
}
