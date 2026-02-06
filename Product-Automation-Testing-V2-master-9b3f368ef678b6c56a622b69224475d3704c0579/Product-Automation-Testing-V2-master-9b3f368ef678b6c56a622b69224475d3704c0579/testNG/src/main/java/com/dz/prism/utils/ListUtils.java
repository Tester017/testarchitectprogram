package com.dz.prism.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.dz.core.common.utils.Reporter;

public class ListUtils {

	public static List<String> getUniqueListValues (List<String> list) {
		
		List<String> uniqueListValues = new ArrayList<String>();
		
		if (!isListNullOrEmpty(uniqueListValues)) {
			uniqueListValues = list.stream().distinct().collect(Collectors.toList());
		} 
		return uniqueListValues;
	}
	
	public static List<String> getListOneValuesNotInListTwo(List<String> listOne, List<String> listTwo) {
		
		List<String> valuesNotInListTwo = new ArrayList<String>();
		
		if (!isListNullOrEmpty(listOne)) {
			valuesNotInListTwo = new ArrayList<String>(listOne);
			valuesNotInListTwo.removeAll(listTwo);
		}
		
		return valuesNotInListTwo;
	}
	
	public static boolean isListNullOrEmpty(List<String> list) {
		
		if (list == null || list.isEmpty()) {
			Reporter.reportStep("info", "List values are empty");
			return true;
		} else {
			return false;
		}
	}
	
	public static List<String> convertStringToList(String value, String splitBy) {
		
		List<String> list = new ArrayList<String>();
		
		if (!(value == null || value.equals(""))) {
			list = new ArrayList<String>(Arrays.asList(value.split(splitBy)));
		} else {
			Reporter.reportStep("fail", "Can't convert a empty String to List. Please check the value");
		}
		
		return list;
	}
	
	public static void compareHahMapList(HashMap<String, String> expData, HashMap<String, String> actData) {
			
		Set<String> keys = expData.keySet();
		for (String key : keys) {
			
			String validValue = expData.get(key);
			String actValue = actData.get(key);
			
			if (actValue == null) {
				Reporter.reportStep("fail", "Header Mismatch. Header Name is : " + key);
			} else if (validValue.equals(actValue)) {
				Reporter.reportStep("pass",
						key + " value is matched ");
			} else {
				Reporter.reportStep("fail", key + "'s data is not matched. Expected value is :: " 
						+ validValue + " Actual value is :: " + actValue);
			} 
		}

	}
	
	public static boolean compareTwoSetValues(Set<String> expSetValues, Set<String> actSetValues) {
		
		if (expSetValues != null && actSetValues !=null ) {
			
			if (!expSetValues.equals(actSetValues)) {
				Set<String> copyOfExpSet = new HashSet<String>(expSetValues);
				Set<String> copyOfActSet =  new HashSet<String>(actSetValues);
				
				copyOfExpSet.removeAll(actSetValues);
				copyOfActSet.removeAll(expSetValues);
				
				if (!copyOfExpSet.isEmpty()) {
					Reporter.reportStep("fail", "Missing expected values are : " + copyOfExpSet);
				}
				if (!copyOfActSet.isEmpty()) {
					Reporter.reportStep("fail", "Invalid values in the set are : " + copyOfActSet);
				}
				return false;
			} else {
				return true;
			}
			
		} else {
			Reporter.reportStep("fail", "One of the Set value is null");
			return false;
		}
		
	}
	
	/**
	 * @author shamilya
	 * @param actListValues - actual values you get from screen
	 * @param expListValues - Expected value to check the testcase (Ex: Data from excel)
	 * @return Will return true if both are same
	 */
	
	public static boolean compareTwoListValues(List<String> actListValues,List<String> expListValues) {
		
		if (!(isListNullOrEmpty(expListValues) || isListNullOrEmpty(actListValues))) {
			List<String> copyOfListOne = new ArrayList<String>(actListValues);
			List<String> copyOfListTwo =  new ArrayList<String>(expListValues);
				
			copyOfListOne.removeAll(expListValues);
			copyOfListTwo.removeAll(actListValues);
			
			if (copyOfListOne.isEmpty() && copyOfListTwo.isEmpty()) {
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
	
	//Double Converter
		public static double convertDouble(String value) {
			 try {
				 if(!value.equals("") && !value.equals("-")) {
				double total =  Double.parseDouble(value);
				return total;
				}else {
					return 0;
				}
				 
			 }catch (Exception e) {
				 Reporter.reportStep("fail", "fail to convert decimal value: '"+value+"'");
			}
			return 0;
		}
}

