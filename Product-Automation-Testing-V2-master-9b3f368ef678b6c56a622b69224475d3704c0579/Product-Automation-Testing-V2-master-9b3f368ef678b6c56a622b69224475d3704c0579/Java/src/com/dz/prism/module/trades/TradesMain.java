package com.dz.prism.module.trades;

import java.io.IOException;
import java.util.Properties;
import com.dz.prism.main.AutomationDriver; 
import com.dz.prism.utils.SeleniumUtils;

public class TradesMain {
	public static Properties TradesPROP  = null;
	public static final String moduleName = "ContactMove";
	static{
		try {
			TradesPROP = SeleniumUtils.getConfigProprty("\\ModuleConfigurations\\trades.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public TradesMain(){
		if(TradesPROP != null){
			callTestCases();
		} else {
			System.out.println("Trades properties file not loadded please verify");
		}
	}
	 
	private void callTestCases(){
		try{
			if(AutomationDriver.isProductionMode() || AutomationDriver.isDevelopmentMode()){
				Trades_T_1.readExcelData();
				System.out.println("Trades Executed Successfully ");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	
}
