package com.dz.prism.utlis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

import com.dz.core.common.utils.Reporter;

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
			String[] splitedArray = Arrays.stream(value.split(splitBy)).map(String::trim).toArray(String[]::new);
			list = new ArrayList<String>(Arrays.asList(splitedArray));
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

	public static boolean compareTwoListValues(List<String> actListValues,List<String> expListValues) {

		if (!(isListNullOrEmpty(expListValues) || isListNullOrEmpty(actListValues))) {
			List<String> copyOfListOne = new ArrayList<String>(actListValues);
			List<String> copyOfListTwo =  new ArrayList<String>(expListValues);;

			copyOfListOne.removeAll(expListValues);;
			copyOfListTwo.removeAll(actListValues);

			if (copyOfListOne.isEmpty() && copyOfListTwo.isEmpty()) {
				reportStep("Expected values are :<b>" + expListValues + "</b> matched with Actual values : <b>" + actListValues + "</b>", "pass");
				reportStep("Actual values are :" + actListValues, "pass");
				return true;
			} else {
				reportStep("Expected values are :<b>" + expListValues + "</b> not matched with Actual values :<b>" + actListValues + "</b>", "fail");
				return false;
			}


		} else {
			return false;
		}
	}

	public static boolean compareTwoSetValues(Set<String> actSetValues,Set<String> expSetValues) {

		if (!(isSetNullOrEmpty(expSetValues) || isSetNullOrEmpty(actSetValues))) {
			actSetValues = actSetValues.stream().map(String::trim).collect(Collectors.toSet());;
			expSetValues = expSetValues.stream().map(String::trim).collect(Collectors.toSet());
			Set<String> one = new HashSet<>(actSetValues);
			Set<String> two = new HashSet<>(expSetValues);
			one.removeAll(expSetValues);
			two.removeAll(actSetValues);
			if (one.isEmpty() && two.isEmpty()) {
				reportStep("Expected values are :<b>" + expSetValues + "</b> matched with Actual values : <b>" + actSetValues + "</b>", "pass");
				reportStep("Actual values are :" + actSetValues, "pass");
				return true;
			} else {
				reportStep("Expected values are :<b>" + expSetValues + "</b> not matched with Actual values :<b>" + actSetValues + "</b>", "fail");
				return false;
			}


		} else {
			return false;
		}
	}


	public static boolean isListNullOrEmpty(List<String> list) {

		if (list == null || list.isEmpty()) {
			//Reporter.reportStep("List values are empty", "Info");
			return true;
		} else {
			return false;
		}
	}

	public static boolean isSetNullOrEmpty(Set<String> set) {

		if (set == null || set.isEmpty()) {
			//Reporter.reportStep("List values are empty", "Info");
			return true;
		} else {
			return false;
		}
	}

	public static boolean compareCommissionHahMap(LinkedHashMap<String, Integer> expData, LinkedHashMap<String, Integer> actData) {

		Set<String> keys = expData.keySet();
		boolean status = true;
		for (String key : keys) {
			Integer validValue = expData.get(key);
			Integer actValue = actData.get(key);
			int max = validValue + PlayWrightUtlis.getNegotiateValue();
			int min = validValue - PlayWrightUtlis.getNegotiateValue();
			if (actValue == null) {
				Reporter.reportStep("The Product <b>" + key + "</b> is not available in Dashboard", "fail");
			} else if (actValue >= min && actValue <= max) {
				Reporter.reportStep(key + " commission <b>" + actValue + "</b> is matched with expected commission : " + expData, "pass");
			} else {
				status = false;
				Reporter.reportStep(key + "'s commission is not matched. Expected value is :: " 
						+ validValue + " Actual value is :: " + actValue, "fail");
			} 
		}

		return status;

	}

	public static boolean compareTwoListValuesExactly(List<String> actListValues,List<String> expListValues) {

		if (!(isListNullOrEmpty(expListValues) || isListNullOrEmpty(actListValues))) {
			actListValues = actListValues.stream().map(String::trim).map(groupName -> groupName.toLowerCase()).collect(Collectors.toList());
			expListValues = expListValues.stream().map(String::trim).map(groupName -> groupName.toLowerCase()).collect(Collectors.toList());
			List<String> copyOfListOne = new ArrayList<String>(actListValues);
			List<String> copyOfListTwo =  new ArrayList<String>(expListValues);

			copyOfListOne.removeAll(expListValues);
			copyOfListTwo.removeAll(actListValues);

			if (copyOfListOne.isEmpty() && copyOfListTwo.isEmpty()) {
				return true;
			} else {
				if (!copyOfListOne.isEmpty()) {
					String reportStep = "";
					int i = 1;
					for (String value : getUniqueListValues(copyOfListOne)) {
						reportStep = reportStep + "<br>" + i + value;
						i++;
					}
					reportStep("<b>Invalid Data</b> is :: " + reportStep, "fail");
				}

				if (!copyOfListTwo.isEmpty()) {
					String reportStep = "";
					int i = 1;
					for (String value : getUniqueListValues(copyOfListTwo)) {
						reportStep = reportStep + "<br>" + i + value;
						i++;
					}
					reportStep("<b>Missing Data</b> is :: " + reportStep, "fail");
				}
				return false;
			}
		} else {
			reportStep("Expected value is :" + expListValues, "Info");
			reportStep("Actuval value is :" + actListValues, "Info");
			return false;
		}
	}

	public static boolean compareDeltaHashMap(LinkedHashMap<String, List<Integer>> expData, LinkedHashMap<String, List<Integer>> actData) {

		boolean status = true;

		LinkedHashMap<String, Integer> expDelta = new LinkedHashMap<String, Integer>();
		LinkedHashMap<String, Integer> expDeltaPer = new LinkedHashMap<String, Integer>();

		LinkedHashMap<String, Integer> actDelta = new LinkedHashMap<String, Integer>();
		LinkedHashMap<String, Integer> actDeltaPer = new LinkedHashMap<String, Integer>();

		for(Map.Entry<String, List<Integer>> m : expData.entrySet()) {

			List<Integer> deltaAndDeltaper = m.getValue();
			expDelta.put(m.getKey(), deltaAndDeltaper.get(0));
			expDeltaPer.put(m.getKey(), deltaAndDeltaper.get(1));
		}

		for(Map.Entry<String, List<Integer>> m : actData.entrySet()) {

			List<Integer> deltaAndDeltaper = m.getValue();
			actDelta.put(m.getKey(), deltaAndDeltaper.get(0));
			actDeltaPer.put(m.getKey(), deltaAndDeltaper.get(1));
		}

		reportStep("<b>Verify Delta Percentage value</b>", "info");
		if (!compareDeltaHahMap(expDeltaPer, actDeltaPer, "Delta Per")) {
			status = false;
		}

		reportStep("<b>Verify Delta value</b>", "info");
		if (!compareDeltaHahMap(expDelta, actDelta, "Delta")) {
			status = false;
		}

		return status;
	}

	public static boolean verifyTwoLongValues(Long Actual,Long Expe, String notes) {
		int rangevalue = getNegotiateValue();
		Reporter.createReportNode(notes);
		String a = String.valueOf(Expe);
		String b = String.valueOf(Actual);
		int c = Integer.parseInt(b);
		int min= Integer.parseInt(a) - rangevalue;
		int max= Integer.parseInt(a) + rangevalue;
		if ((c>=min) && (c<=max)) {
			reportStep("----> Actual value : " + Actual + " matched with Expected value :  "+Expe, "pass");
			return true;
		} else {
			reportStep("---->Actual value : " + Actual + " not matched with Expected value : "+Expe, "fail");
			return false;
		}

	}

	public static boolean compareExpListContainsInActualList(List<String> actListValues,List<String> expListValues) {

		if (!(isListNullOrEmpty(expListValues) || isListNullOrEmpty(actListValues))) {
			List<String> copyOfListOne = new ArrayList<String>(actListValues);
			List<String> copyOfListTwo =  new ArrayList<String>(expListValues);
			copyOfListOne.removeAll(expListValues);
			boolean removeAll = copyOfListTwo.removeAll(actListValues);

			if (removeAll==true) {
				Reporter.reportStep("<b>"+expListValues+"</b> Contains <b>"+actListValues+"</b> as expected", "pass");
				return true;
			} else {
				Reporter.reportStep("Info", "Expected value is :" + expListValues);
				Reporter.reportStep("Info", "Actuval value is :" + actListValues);
				return false;
			}
		} else {
			return false;
		}
	}

	public static List<String> getListValueTrimed(List<String> listValues) {
		if (!(isListNullOrEmpty(listValues))) {
			List<String> trimedValues = new ArrayList<String>();
			for (String list : listValues) {
				trimedValues.add(list.trim());
			}
			return trimedValues;
		} else {
			reportStep("List is empty", "fail");
			return new ArrayList<String>();
		}
	}

	public static boolean isListContainsDuplicates(List<String> listValues) {
		boolean status = false;
		if (!(isListNullOrEmpty(listValues))) {
			Set<String> setValues =new HashSet<String>(listValues);
			if (listValues.size()==setValues.size()) {
				status = true;
			} else {
				status = false;
			}
		} else {
			reportStep("List is empty", "fail");
		}
		return status;
	}

	public static List<String> splitAndTrimInList(List<String> listValues, String splitBy) {
		if (!(isListNullOrEmpty(listValues))) {
			List<String> afterTrim = listValues.stream().map(list -> removeSpace(list, splitBy)).collect(Collectors.toList());
			return afterTrim;
		} else {
			reportStep("List is empty", "Info");
			return listValues;
		}
	}

	public static String removeSpace(String value, String splitBy) {
		String[] arr = value.split(splitBy);
		String aftTrim = "";
		for (String s : arr) {
			aftTrim = aftTrim + s.trim();
		}

		return aftTrim;
	}

	public static boolean compareListValuesWithNoReportStep(List<String> actListValues,List<String> expListValues) {

		if (!(isListNullOrEmpty(expListValues) || isListNullOrEmpty(actListValues))) {
			actListValues = actListValues.stream().map(String::trim).collect(Collectors.toList());
			expListValues = expListValues.stream().map(String::trim).collect(Collectors.toList());
			List<String> copyOfListOne = new ArrayList<String>(actListValues);
			List<String> copyOfListTwo =  new ArrayList<String>(expListValues);

			copyOfListOne.removeAll(expListValues);
			copyOfListTwo.removeAll(actListValues);

			if (copyOfListOne.isEmpty() && copyOfListTwo.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static List<String> removeMailId(List<String> internalAttendeesList) {

		List<String> aftRemoveValue = new ArrayList<String>();

		if (!(internalAttendeesList == null || internalAttendeesList.isEmpty())) {
			for (String interAtt : internalAttendeesList) {
				String[] removedValue = null;
				try {
					removedValue = interAtt.split("\\(");
				} catch (PatternSyntaxException e) {
					e.printStackTrace();
				}
				aftRemoveValue.add(removedValue[0]);
			}
		}
		return aftRemoveValue;
	}

	public static void checkListHasTheValue(List<String> listToCheck, String value, String reportStep) {

		if (!(isListNullOrEmpty(listToCheck))) {

			Optional<String> isPresent = listToCheck.stream().filter(l -> l.equalsIgnoreCase(value)).findFirst();

			if (isPresent.isPresent()) {
				reportStep(reportStep, "pass");
			} else {
				//reportStep(reportStep, "fail");
				reportStep("The given value <b>" +  value + "</b> doesnot exist in list " + listToCheck, "fail");
			}

		} else {
			Reporter.reportStep("The list given is empty or null. Check the list : (" + listToCheck + ")", "Info");
		}
	}

	public static List<String> splitAndIndexZeroToList(List<String> internalAttendeesList, String splitBy) {

		List<String> aftRemoveValue = new ArrayList<String>();
		String[] removedValue = null;
		if (!(internalAttendeesList == null || internalAttendeesList.isEmpty())) {
			for (String interAtt : internalAttendeesList) {
				
				try {
					removedValue = interAtt.split(splitBy);
				} catch (PatternSyntaxException e) {
					e.printStackTrace();
				}
				aftRemoveValue.add(removedValue[0].trim());

			}
		}
		return aftRemoveValue;
	}

	public static boolean isListHasDuplicates(List<String> values) {
		if (!isListNullOrEmpty(values)) {
			List<String> uniqueValue = getUniqueListValues(values);
			if (values.size() == uniqueValue.size()) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public static List<String> getDuplicatesInList(List<String> values) {
		if (!isListNullOrEmpty(values)) {
			Set<String> elements = new HashSet<String>();
			return values.stream()
					.filter(n -> !elements.add(n))
					.collect(Collectors.toList());
		} else {
			return null;
		}
	}

	/**
	 * This method is used to compare two list values exactly
	 * @param actListValues - pass actual data
	 * @param expListValues - pass expected data
	 * @param reportSteps - pass the report step to print in report when failure occurs
	 * @return - boolean
	 * @author shamilya
	 */
	public static boolean compareTwoListValuesExactly(List<String> actListValues,List<String> expListValues, String reportSteps) {

		if (!(isListNullOrEmpty(expListValues) || isListNullOrEmpty(actListValues))) {
			actListValues = actListValues.stream().map(String::trim).collect(Collectors.toList());
			expListValues = expListValues.stream().map(String::trim).collect(Collectors.toList());
			List<String> copyOfListOne = new ArrayList<String>(actListValues);
			List<String> copyOfListTwo =  new ArrayList<String>(expListValues);

			copyOfListOne.removeAll(expListValues);
			copyOfListTwo.removeAll(actListValues);

			if (copyOfListOne.isEmpty() && copyOfListTwo.isEmpty()) {
				return true;
			} else {
				reportStep(reportSteps, "fail");
				if (!copyOfListOne.isEmpty()) {
					String reportStep = "";
					int i = 1;
					for (String value : getUniqueListValues(copyOfListOne)) {
						reportStep = reportStep + "<br>" + i + ". "+ value;
						i++;
					}
					reportStep("<b>Invalid Datas</b> are below." + reportStep, "fail");
				}
				if (!copyOfListTwo.isEmpty()) {
					String reportStep = "";
					int i = 1;
					for (String value : getUniqueListValues(copyOfListTwo)) {
						reportStep = reportStep + "<br>" + i + ". " + value;
						i++;
					}
					reportStep("<b>Missing Datas</b> are below. " + reportStep, "fail");
				}
				return false;
			}
		} else {
			reportStep("Expected value or Actual value list is null or empty","fail");
			reportStep("Expected value is :" + expListValues, "Info");
			reportStep("Actuval value is :" + actListValues, "Info");
			return false;
		}
	}

	public static Set<String> getSemicolonSplittedList(List<String> list) {
		Set<String> listValue = new HashSet<String>();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				String string = list.get(i);
				if (string.contains(";")) {
					String[] split = string.split(";");
					for (String val : split) {
						if (!val.trim().equals("-")&&val.trim().length()>1) {
							listValue.add(val.trim());
						}
					}
				} else {
					listValue.add(list.get(i));
				}
			}
		}else {
			reportStep("List is Empty ", "fail");
		}
		return listValue;
	}

	public static List<String> convertListToLowerCase(List<String> list) {
		return list.stream().map(String::toLowerCase).collect(Collectors.toList());
	}

	public static boolean compareDeltaHahMap(LinkedHashMap<String, Integer> expData, LinkedHashMap<String, Integer> actData, String colName) {

		Set<String> keys = expData.keySet();
		boolean status = true;
		for (String key : keys) {
			Integer validValue = expData.get(key);
			Integer actValue = actData.get(key);
			int max = validValue + PlayWrightUtlis.getNegotiateValue();
			int min = validValue - PlayWrightUtlis.getNegotiateValue();
			if (actValue == null) {
				Reporter.reportStep("The Product : " + key + " is not available", "fail");
			} else if (actValue >= min && actValue <= max) {
				Reporter.reportStep("<b>" + key + "</b> " + colName + " : " + actValue + " is matched ", "pass");
			} else {
				status = false;
				Reporter.reportStep(key + "'s" + colName + " is not matched. Expected value is :: " 
						+ validValue + " Actual value is :: " + actValue, "fail");
			} 
		}

		return status;
	}

	public static boolean compareCommissionHahMap(LinkedHashMap<String, Integer> expData, LinkedHashMap<String, Integer> actData, List<String> reportSteps) {

		Set<String> keys = expData.keySet();
		boolean status = true;
		for (String key : keys) {
			Integer validValue = expData.get(key);
			Integer actValue = actData.get(key);
			int max = validValue + PlayWrightUtlis.getNegotiateValue();
			int min = validValue - PlayWrightUtlis.getNegotiateValue();
			if (actValue == null) {
				Reporter.reportStep("The Product : " + key + " is not available", "fail");
			} else if (actValue >= min && actValue <= max) {
				String step = reportSteps.get(0).replace("actValue", "" + actValue).replace("Key", key);
				Reporter.reportStep(step, "pass");
			} else {
				status = false;
				String step = reportSteps.get(1).replace("Key", key);
				Reporter.reportStep(step + ". Expected value is :: " + validValue + " Actual value is :: " + actValue, "fail");
			} 
		}

		return status;

	}

	public static boolean checkListHasSpecialCharacter(List<String> list,String specialChar) {
		boolean status = true;
		if (list!=null) {
			for (int i = 0; i <list.size(); i++) {
				String val = list.get(i);
				if (!val.contains(specialChar)) {
					reportStep("List contains special character", "pass");
					status = false;
				}
			}
		}else {
			reportStep("List is null", "fail");
		}
		return status;
	}

	public static boolean compareStringContainsInAllIndexInList(List<String> actTableListValues,String filterFieldValue) {
		boolean status = false;;
		if (actTableListValues!=null || filterFieldValue!=null) {
			int matchCount =0;
			for (int i = 0; i < actTableListValues.size(); i++) {
				String tableVal = actTableListValues.get(i);
				if (tableVal.contains(filterFieldValue)) {
					matchCount++;
				}
			}
			if (matchCount==actTableListValues.size()) {
				status = true;
			}
		}
		if (status) {
			reportStep("Expected value : <b>"+filterFieldValue+"</b> contains in all list index values : <b>"+actTableListValues+"</b>", "pass");
		}else {
			reportStep("Expected value : <b>"+filterFieldValue+"</b> not contains in all list index values : <b>"+actTableListValues+"</b>", "fail");

		}
		return status;
	}

	public static List<String> trimList(List<String> strings) {
		List<String> trimmedList = new ArrayList<String>();
		if (strings!=null) {
			// Trimming each string in the list using Streams
			try {
				trimmedList = strings.stream()
						.map(String::trim) // Trims leading and trailing whitespace
						.collect(Collectors.toList());
				return trimmedList;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return trimmedList;
	}
	
	public static String convertListToStringWithSorted(List<String> list, String delim) {

		if (!list.isEmpty()) {
			Collections.sort(list);
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

}
