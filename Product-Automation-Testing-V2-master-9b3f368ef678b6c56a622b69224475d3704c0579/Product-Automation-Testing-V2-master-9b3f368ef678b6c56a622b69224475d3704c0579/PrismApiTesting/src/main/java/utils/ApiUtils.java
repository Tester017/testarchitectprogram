package utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.Range;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import common.utils.Reporter;
import constant.ProductConstant;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class ApiUtils  { 

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


	public static void calculateTableHeaderValue(JsonArray tableData, JsonObject reveueHeaderSum) {
		Map<String,Double> calculatedSum = new LinkedHashMap<>();
		for(int i=0;i<tableData.size();i++) {
			//System.out.println(tableData.get(i));
			JsonObject accounDetails = JsonParser.parseString(tableData.get(i).toString()).getAsJsonObject();
			//convert json object into Map
			Set<Entry<String, JsonElement>> headerSum = reveueHeaderSum.entrySet();
			//iterate table total header value and check column have value
			for(Map.Entry<String,JsonElement> sumOfTotal : headerSum){
				String headerSumValue =getObjectValueAsString(reveueHeaderSum, sumOfTotal.getKey());
				if(!headerSumValue.trim().isEmpty() && !headerSumValue.equalsIgnoreCase("-") 
						&& !sumOfTotal.getKey().toLowerCase().contains("deltap")
						&& !sumOfTotal.getKey().toLowerCase().contains("delta_p")
						&& !sumOfTotal.getKey().toLowerCase().contains("threshold ")
						&& !sumOfTotal.getKey().toLowerCase().contains("label")) {

					String value =   getObjectValueAsString(accounDetails, sumOfTotal.getKey());
					if(!value.trim().isEmpty() && !value.equalsIgnoreCase("-")) {
						double accountValue=convertToDouble(value);
						if( checkKeyPresentInMap(calculatedSum,sumOfTotal.getKey())) {
							double total = convertToDouble(calculatedSum.get(sumOfTotal.getKey()).toString());
							total += accountValue;
							calculatedSum.put(sumOfTotal.getKey(), total);
						}else {
							calculatedSum.put(sumOfTotal.getKey(), accountValue);
						}
					}

				}

			}
		}

		//System.out.println(reveueHeaderSum.toString());
		for(Entry<String, Double> calculated : calculatedSum.entrySet()) {
			DecimalFormat df = new DecimalFormat(".00"); 
			double headerSum = convertToDouble(reveueHeaderSum.get(calculated.getKey()).toString());

			//System.out.println(calculated.getKey()+"$$$"+reveueHeaderSum.get(calculated.getKey().trim()).toString().trim());
			if(!reveueHeaderSum.get(calculated.getKey()).toString().trim().replaceAll("\"", "").isEmpty() 
					&&!reveueHeaderSum.get(calculated.getKey()).toString().trim().replaceAll("\"", "").equalsIgnoreCase("-") ) {
				Range<Double> calucaltedRange = Range.between(headerSum-ProductConstant.maxAccDifferent,headerSum+ProductConstant.maxAccDifferent);
				if(calucaltedRange.contains(calculated.getValue())) {
					Reporter.reportStep( calculated.getKey()+" -- Matched   -- "+ df.format(calculated.getValue()),"pass");
				}else {
					System.out.println(calculated.getKey()+" -- not Matched   calculated-- "+ df.format(calculated.getValue())+"  ----- ui "+df.format(headerSum));
					Reporter.reportStep(calculated.getKey()+" -- not Matched   calculated-- "+ df.format(calculated.getValue())+"  ----- ui "+df.format(headerSum),"fail");
				}

			}
		}
	}

	public static String getObjectValueAsString(JsonObject accounDetails, String key) {
		try {
			if (accounDetails.has(key)) {

				if (accounDetails.get(key).isJsonNull()) {
					return "";
				} 
				return accounDetails.get(key).getAsString();
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

	public static double getValueFromTitle(String text) {
		String[] title = text.split("title='");
		text = title[1];
		String [] value = text.split("'>");
		text =getNumberfromText(value[0]);

		return convertToDouble(text);
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

	public static String getIdFromDataPersonId(String text,String SplitBy) {
		String[] title = text.split(SplitBy);
		text = title[1];
		String [] value = text.split(" ");
		String replace = value[0].replace("\"", "");
		return replace;
	}
	public static List<String> getOnlySubTitleFromString(String accDetails,String SplitBy) {
		List<String> subTitle=new ArrayList<String>();
		String str = accDetails.replaceAll("\"", "");
		String[] title = str.split(SplitBy);
		for (String string : title) {
			String splitedString = string.replace("\\", "").split(",")[0].trim();
			if (!splitedString.contains("{")||!splitedString.contains(":")) {
				subTitle.add(splitedString);
			}
		}
		System.out.println(subTitle);
		return subTitle;
	}



	public static int getIdFromDataPersonId(String text) {
		String[] title = text.split("data-personid=");
		text = title[1];
		String [] value = text.split(" ");
		text =getNumberfromText(value[0]);

		return Integer.parseInt(text);
	}

	public static String getPersonName(String text) {
		String[] title = text.split("data-accname=");
		text = title[1];
		String[] value = text.split(">");
		text = getOnlyTextFromString(value[0]);

		return text;
	}

	public static String getOnlyTextFromString(String text) {
		String extract = text.replaceAll("[^a-z A-Z]+", "");
		return extract;
	}

	public String getAccNameFromString(String text) {
		String[] title = text.split("data-role_desc=");
		text = title[1];
		String[] value = text.split(">");
		text = value[1];
		String[] value1 = text.split("<");
		return value1[0];
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
	public static String getIdFromDataPersonName(String accDetails,String SplitBy) {
		String[] title = accDetails.split(SplitBy);
		String text = title[1];
		String [] value = text.split(" ");
		text =value[0].replace("\"", "");
		return text;
	}

	public static String getValueByJsoupAttr(String text, String tagName, String attrName) {
		
		if (!text.equals("")&&!text.equals("-")&&!text.equals(null)) {
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
		if (!text.equals("")&&!text.equals("-")&&!text.equals(null)) {
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

	public static String getCoverage(String accDetails,String SplitBy) {
		String[] title = accDetails.split(SplitBy);
		String text = title[1];
		String a = getNumberfromText(text);
		return a;
	}

	public static Double getObjectValueAsDouble(JsonObject accounDetails, String key) {
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

	/**
	 * This method is used to compare table header values
	 * 
	 * @param tableHeaderWithPosition
	 * @param headerNames             split by '~' symbol
	 * @author bhuvaneswaran
	 */
	public static void compareTableHearderColumnValue(JsonArray tableHeaderName,
			String headerName) {

		String column[] = headerName.split("~");
		ArrayList<String> columnName = new ArrayList<String>();
		for (int i = 0; i < column.length; i++) {
			columnName.add(column[i].trim());
		}

		StringBuilder configName = new StringBuilder();
		ArrayList<String> headerNameInApi  = new ArrayList<String>();
		for(int k=0;k<tableHeaderName.size();k++) {
			String name = tableHeaderName.get(k).getAsJsonObject().get("name").getAsString();
			headerNameInApi.add(name.trim());
			configName.append(name+" ~ ");
		}

		System.out.println( );

		for(int j=0 ;j<columnName.size();j++) {
			boolean status  = headerNameInApi.contains(columnName.get(j));
			if(status) {
				headerNameInApi.remove(columnName.get(j));
				Reporter.reportStep(columnName.get(j)+" - Table header is present as per expected", "pass");
			}else {
				Reporter.reportStep(columnName.get(j)+" - Header name present in table but not found in configuration ", "fail");
			}


		}

		if (headerNameInApi.size() != 0) {
			Reporter.reportStep(" Table header in " + headerNameInApi.toString() + " Header name present in table but not found in configuration","fail");
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
					+ expValue + " .Actual value is : " + actValue , "fail");
		}
	}

	public static int getObjectValueAsInt(JsonObject accounDetails, String key) {
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

	public static void calculateTableHeaderOverallSumValue(JsonArray tableData, JsonObject reveueHeaderSum) {
		Map<String,Double> calculatedSum = new LinkedHashMap<>();
		for(int i=0;i<tableData.size();i++) {
			//System.out.println(tableData.get(i));
			JsonObject accounDetails = JsonParser.parseString(tableData.get(i).toString()).getAsJsonObject();
			//convert json object into Map
			Set<Entry<String, JsonElement>> headerSum = reveueHeaderSum.entrySet();
			//iterate table total header value and check column have value
			for(Map.Entry<String,JsonElement> sumOfTotal : headerSum){
				String headerSumValue =getObjectValueAsString(reveueHeaderSum, sumOfTotal.getKey());
				if(!headerSumValue.trim().isEmpty() && !headerSumValue.equalsIgnoreCase("-") 
						&& !sumOfTotal.getKey().toLowerCase().contains("deltap")
						&& !sumOfTotal.getKey().toLowerCase().contains("delta_p")
						&& !sumOfTotal.getKey().toLowerCase().contains("threshold ")
						&& !sumOfTotal.getKey().toLowerCase().contains("label")) {

					String value =   getObjectValueAsString(accounDetails, sumOfTotal.getKey());
					if(!value.trim().isEmpty() && !value.equalsIgnoreCase("-")) {
						double accountValue=convertToDouble(value);
						if( checkKeyPresentInMap(calculatedSum,sumOfTotal.getKey())) {
							double total = convertToDouble(calculatedSum.get(sumOfTotal.getKey()).toString());
							total += accountValue;
							calculatedSum.put(sumOfTotal.getKey(), total);
						}else {
							calculatedSum.put(sumOfTotal.getKey(), accountValue);
						}
					}

				}

			}
		}

		//System.out.println(reveueHeaderSum.toString());
		for(Entry<String, Double> calculated : calculatedSum.entrySet()) {
			DecimalFormat df = new DecimalFormat(".00"); 
			double headerSum = convertToDouble(reveueHeaderSum.get(calculated.getKey()).toString());

			//System.out.println(calculated.getKey()+"$$$"+reveueHeaderSum.get(calculated.getKey().trim()).toString().trim());
			if(!reveueHeaderSum.get(calculated.getKey()).toString().trim().replaceAll("\"", "").isEmpty() 
					&&!reveueHeaderSum.get(calculated.getKey()).toString().trim().replaceAll("\"", "").equalsIgnoreCase("-") ) {
				Range<Double> calucaltedRange = Range.between(headerSum-ProductConstant.maxTotalDifference,headerSum+ProductConstant.maxTotalDifference);
				if(calucaltedRange.contains(calculated.getValue())) {
					Reporter.reportStep( calculated.getKey()+" -- Matched   -- "+ df.format(calculated.getValue()),"pass");
				}else {
					System.out.println(calculated.getKey()+" -- not Matched   calculated-- "+ df.format(calculated.getValue())+"  ----- ui "+df.format(headerSum));
					Reporter.reportStep(calculated.getKey()+" -- not Matched   calculated-- "+ df.format(calculated.getValue())+"  ----- ui "+df.format(headerSum),"fail");
				}

			}
		}
	}
	
	public static boolean getObjectValueAsBoolean(JsonObject accounDetails, String key) {
		try {
			if (accounDetails.has(key)) {

				if (accounDetails.get(key).isJsonNull()) {
					return true;
				} 
				return accounDetails.get(key).getAsBoolean();
			} else {
				if (key.equals("reason")) {
					return accounDetails.get("reasons").getAsBoolean();
				} else {
					Reporter.reportStep("The " + key +" key is not present in object ", "info"); 
					Reporter.reportStep("JsonObject : " + accounDetails, "info"); 
					return true;
				}
			}
		} catch (Exception e) {
			Reporter.reportStep("The " + key +" key is not present in object ", "info"); 
			Reporter.reportStep("JsonObject : " + accounDetails, "info");  
			return false;
		}
	}
	
	public static Stream<JsonElement> convertJsonArrayToStream(JsonArray arr) {
		Stream<JsonElement> stream = StreamSupport.stream(arr.spliterator(), true);
		return stream;
	}

	
	
}
