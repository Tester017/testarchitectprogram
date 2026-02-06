package test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.commons.io.IOUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bo.ResponceBO;
import common.Report;
import function.DoPerformanceCheck;
import function.MailTrigger;

public class ApiHealthCheck {
	static JsonArray envJsonObj = null; 
	
	@BeforeSuite
	public void beforeSuit() {
		 InputStream is = null;
		 String conf ="";
			try {
				is = new FileInputStream("./src/test/resources/Environment.json");     //read the json fiLe from path
			    conf = IOUtils.toString(is, "UTF-8");    //store input stream data in String
				System.out.println(conf);
			} catch (Exception e) {
				System.out.println("Config File Missing please check");
			}
		
		Gson g = new Gson();
		//convert string into jsonObject
		envJsonObj = new JsonParser().parse(conf).getAsJsonArray();
		//validate the mandatory keys in environment.json file
		DoPerformanceCheck doCheck = new DoPerformanceCheck();
		for(int i=0;i<envJsonObj.size();i++) {
			 doCheck.validateEnvironMentDetail(envJsonObj.get(i).getAsJsonObject());
		}
		 
	}
	
	
	@DataProvider(name = "envDetails",parallel = false)
	public Object[] environmentDetails() {
		List<JsonObject> env  = new LinkedList<JsonObject>();
		for(int i=0 ; i< envJsonObj.size();i++) {
			env.add(envJsonObj.get(i).getAsJsonObject());
		}
		
		return env.toArray();
	} 
	@Test(dataProvider = "envDetails")
	public void performanceTest(JsonObject  envDetail) {
		List<ResponceBO> responce = new ArrayList<>();
		Report report = new Report();
		String environmentName = "";
		 try {
			DoPerformanceCheck doCheck = new DoPerformanceCheck();
			if(envDetail.get(doCheck.isExecution).getAsBoolean()) { 
			    environmentName= envDetail.get(doCheck.environmentName).getAsString();
				doCheck.validateLoginAuth(envDetail,responce);
				doCheck.doApiHealthCheck(envDetail,responce);
			}
		 
		 }catch (Exception e) {
		    ResponceBO res = new ResponceBO();
		    if(e.getMessage()!=null) {
		    	res.setExceptionMessage(e.getMessage());
			    res.setEnvironmentName(environmentName);
			    responce.add(res);
			} 
		   
		   
		}
		 
		for(ResponceBO res : responce) {
			System.out.println("Envionment : "+ res.getEnvironmentName());
			System.out.println("module : "+ res.getModuleName());
			System.out.println("Api call : "+ res.getApiPath());
			System.out.println("Api responce Code : "+ res.getApiCallCode());
			System.out.println("reason : "+ res.getReason());
			System.out.println("Exception :" +res.getExceptionMessage());
			System.out.println("dev status :" +res.getDevResponceCode());
			System.out.println("request : "+res.getRequestType());
			System.out.println("");
			System.out.println("");
			
			report.reportInExcelLog(res,environmentName,false);
			report.reportInExcelLog(res,environmentName,true);
			
			
		}
		if(responce.size()>0) {
			MailTrigger mail =new MailTrigger();
			if(mail.validateMailAddress(envDetail)) {
				String mailContent =  mail.generateMailContent(responce,envDetail);
				mail.sendMail(mailContent,envDetail);
			}
			
		}
		
		 
		 
	}
	
	
	
}
