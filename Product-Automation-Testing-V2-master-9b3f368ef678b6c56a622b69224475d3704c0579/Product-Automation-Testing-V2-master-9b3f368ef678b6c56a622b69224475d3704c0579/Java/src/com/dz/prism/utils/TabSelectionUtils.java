package com.dz.prism.utils;

import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.dz.prism.module.checkspecialcharater.CheckSpecialCharaterMain;


public class TabSelectionUtils {
	public static Properties tabSelectProp=null;
	public static boolean tabSelection(String moduleName){
	   try{
		  String enviromentName  = getPropValue(SeleniumUtils.SeleniumProps,"EnviromentName");
		  tabSelectProp =  SeleniumUtils.getConfigProprty("\\ModuleConfigurations\\tabSelection.properties");
		  String tabName = getPropValue(tabSelectProp, enviromentName+"_"+moduleName);
		  if(tabName.contains(",")){
			 String tab[] = tabName.split(",");
			 SeleniumUtils.createTestNode(tab[1]+" Tab Selection", "Given Module name is available in tab selection or not"); 
			 if(SeleniumUtils.tabSelection(tab[0].trim(), tab[1].trim())){
				reportWrite(SeleniumUtils.childTest, tab[1]+" is displayed", true); 
				return true;
			 }else{
				 reportWrite(SeleniumUtils.childTest, tab[1]+" is not displayed", false);
				 return false;
			 }
		  }else{
			  SeleniumUtils.createTestNode(tabName+" Tab Selection", "Given Module name is available in tab selection or not"); 
			  if(SeleniumUtils.tabSelection(tabName.trim())){
				  reportWrite(SeleniumUtils.childTest, tabName+" is displayed", true); 
				  return true;
			  }else{
				  reportWrite(SeleniumUtils.childTest, tabName+" is not displayed", false); 
				  return false;
			  }
		  }
		  
	   }catch (Exception e) {
		e.printStackTrace();
	   }
	   return true;
    }
	/**
	 * get property file value
	 * @param props
	 * @param key
	 * @return
	 */
	public static String getPropValue(Properties props, String key) {
		String result = "";
		try{
		result =  props.getProperty(key).trim();
		}
		catch (Exception e) {
			 System.out.println(key +" not available in props");
		}
		return result;
	}
	/**
	 * generate a report
	 * @param insertData
	 * @param msg
	 * @param status
	 * @param values
	 */
		public static void reportWrite(ExtentTest exten, String msg, boolean status  ) {
			try{
				if(status){
					exten.log(Status.PASS,MarkupHelper.createLabel( msg+" ",ExtentColor.GREEN));            
		        }else {
		        	exten.log(Status.FAIL,
		                    MarkupHelper.createLabel(msg ,
		                            ExtentColor.RED));    
		        	 SeleniumUtils.testCase.addScreenCaptureFromPath(SeleniumUtils.takeSnapShot(CheckSpecialCharaterMain.moduleName,"csc",1));
		        }	
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
}
