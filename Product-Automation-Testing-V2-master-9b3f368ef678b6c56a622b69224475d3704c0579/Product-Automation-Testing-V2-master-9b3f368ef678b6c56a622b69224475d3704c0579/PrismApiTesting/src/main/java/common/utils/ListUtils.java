package common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import common.utils.*;


public class ListUtils extends Reporter{

	/**
	 * This method is used to get unique values in a List
	 * @param values - Pass the List which you want remove duplicates
	 * @return - List<String>
	 * @author shamilya
	 */
	public static List<String> getUniqueListValues(List<String> values) {
		if (values != null &&  !values.isEmpty()) {
			List<String> uniqueValues = values.stream().distinct().collect(Collectors.toList());
			return uniqueValues;
		} else {
			return new ArrayList<String>();
		}
	}

	/**
	 * This method is used to convert a String to List
	 * @param values - Pass the String value
	 * @param splitBy - Pass the regex used to split (Eg ',' or '~~')
	 * @return - List<String>
	 * @author shamilya
	 */
	public static List<String> convertStringToList(String value, String splitBy) {

		List<String> list = new ArrayList<String>();

		if (!(value == null || value.equals(""))) {
			list = new ArrayList<String>(Arrays.asList(value.split(splitBy)));
		} else {
			System.out.println("Can't convert a empty String to List. Please check the value");
		}

		return list;
	}

	/**
	 * This method is used to convert a String to List
	 * @param list - Pass the String value
	 * @param delim - Pass the regex used to join (Eg ',' or '~~')
	 * @return - String
	 * @author shamilya
	 */
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


	public static boolean compareHahMapList(HashMap<String, String> expData, HashMap<String, String> actData) {

		Set<String> keys = actData.keySet();
		boolean status = true;
		for (String key : keys) {

			String validValue = expData.get(key);
			String actValue = actData.get(key);

			if (validValue == null) {
				Reporter.reportStep("Header Mismatch. Header Name is : " + key, "fail");
			} else if (validValue.equals(actValue)) {
				Reporter.reportStep(key + " value is matched ", "pass");
			} else {
				status = false;
				Reporter.reportStep(key + "'s data is not matched. Expected value is :: " 
						+ validValue + " Actual value is :: " + actValue, "fail");
			} 
		}

		return status;

	}

	public static List<String> compareTwoListValues(List<String> actListValues,List<String> expListValues) {

		List<String> copyOfListOne = new ArrayList<String>(actListValues);
		List<String> copyOfListTwo =  new ArrayList<String>(expListValues);
		List<String> comparedList = new ArrayList<String>();
		if (!(isListNullOrEmpty(expListValues) || isListNullOrEmpty(actListValues))) {

			copyOfListOne.removeAll(expListValues);
			copyOfListTwo.removeAll(actListValues);

			if (copyOfListOne.isEmpty() && copyOfListTwo.isEmpty()) {
				return comparedList;
			} else {
				if (!copyOfListOne.isEmpty()) {
					comparedList = copyOfListOne;
				}
				if (!copyOfListTwo.isEmpty()) {
					comparedList = copyOfListTwo;
				}
				return comparedList;
			}
		} else {
			return comparedList;
		}
		
	}
	
	public static boolean isListNullOrEmpty(List<String> list) {

		if (list == null || list.isEmpty()) {
			Reporter.reportStep("List values are empty", "info");
			return true;
		} else {
			return false;
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



}
