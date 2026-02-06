package com.dz.prism.module.login;

import java.io.IOException;
import java.util.Properties;

import com.dz.prism.utils.SeleniumUtils;

public class LoginMain {
public static Properties LOGINPROP  = null;
public static final String moduleName = "LoginMain";

	static{
		// Call for Commision tiles check
		try {
			LOGINPROP = SeleniumUtils.getConfigProprty("\\ModuleConfigurations\\login.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public LoginMain(){
		if(LOGINPROP != null){
			callTestCases();
		} else {
			System.out.println("Login properties file not loadded please verify");
		}
	}
	
	private void callTestCases(){
		try{			
			Login_LP_1.SignInMethod();
			System.out.println("Executed Login Tests");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
