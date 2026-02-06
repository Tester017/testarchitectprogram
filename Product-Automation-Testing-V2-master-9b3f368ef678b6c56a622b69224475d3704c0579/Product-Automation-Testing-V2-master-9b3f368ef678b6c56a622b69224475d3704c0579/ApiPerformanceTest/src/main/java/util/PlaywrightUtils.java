package util;

import java.net.URLEncoder;
import java.util.List;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.PlaywrightException;

import bo.ApiExcelBO;
import bo.ResponceBO;
import common.Report; 

public class PlaywrightUtils  {

	
	
public  APIResponse doPostRequest(String path, String environment, ApiExcelBO api, List<ResponceBO> respoList) {
		
		try {
		    long start = System.currentTimeMillis();
			APIResponse responce = Report.requests.get().post(path);
			long end = System.currentTimeMillis();
			double sec = (end - start) / 1000; 
			if(sec>30) {
				System.out.println("This Api call have more than 30sec of time   actual time :"+sec+" <b> url </b>:"+path);
				ResponceBO respo = new ResponceBO(); 
				 respo.setEnvironmentName(environment);
				 respo.setApiPath(path);
				 respo.setRequestType(api.getRequestType());
				 respo.setModuleName(api.getModule());
				 respo.setApiCallCode("When hit the api call. we recived status code :"+responce.status());
				 respo.setModuleName(api.getModule());
				 respo.setReason("This Api call have more than 30sec of time   actual time in sec :"+sec); 
				 respo.setDescription(api.getDescription());
				 respoList.add(respo);
			}
			
			if (responce.status()!=200) {
				 ResponceBO respo = new ResponceBO(); 
				 respo.setEnvironmentName(environment);
				 respo.setApiPath(path);
				 respo.setRequestType(api.getRequestType());
				 respo.setModuleName(api.getModule());
				 respo.setDescription(api.getDescription());
				 respo.setApiCallCode("When hit the api call. we recived status code :"+responce.status());
				 respo.setModuleName(api.getModule());
				 respo.setReason("API call is failed due the following reason :"+ responce.text()); 
				 respoList.add(respo);
			} else {
				boolean isJsonObj = JsonParser.parseString(responce.text()).isJsonObject();
				//if the responce is json 
				if (isJsonObj) {
					JsonObject jsonObj =JsonParser.parseString(responce.text()).getAsJsonObject();
					String status = getJsonObjectAsString(jsonObj, "status");
					if (! (status.equals("1") || status.equals("0"))) {
						ResponceBO respo = new ResponceBO(); 
						respo.setEnvironmentName(environment);
						respo.setDevResponceCode(status);
						respo.setApiPath(path);
						respo.setRequestType(api.getRequestType());
						respo.setModuleName(api.getModule());
						respo.setDescription(api.getDescription());
						respo.setApiCallCode("When hit the api call. we recived status code :"+responce.status());
						if(!getJsonObjectAsString(jsonObj, "message").trim().isEmpty()) {
							respo.setReason(getJsonObjectAsString(jsonObj, "message"));
						}
						if(!getJsonObjectAsString(jsonObj, "reason").trim().isEmpty()) {
							respo.setReason(getJsonObjectAsString(jsonObj, "reason"));
						}
						respo.setApiPath(path); 
						respo.setExceptionMessage(jsonObj.toString()); 
						respoList.add(respo);
					}
				// if the responce not as json like jsonarray or any other formate 
				}else{
					
					ResponceBO respo = new ResponceBO(); 
					respo.setEnvironmentName(environment); 
					respo.setApiPath(path);
					respo.setRequestType(api.getRequestType());
					respo.setModuleName(api.getModule());
					respo.setDescription(api.getDescription());
					respo.setReason("Responce not in json formate :"+responce.text());
					respoList.add(respo);
				}
			}
			return responce;
		}catch (PlaywrightException e) {
			System.out.println("$%%%%%%%%%%%#####"+e.getLocalizedMessage());
			ResponceBO respo = new ResponceBO();
			respo.setEnvironmentName(environment);
			respo.setModuleName(api.getModule()); 
			respo.setApiPath(path);
			respo.setDescription(api.getDescription()); 
			if(e.getMessage()!=null) {
				respo.setExceptionMessage(e.getMessage());
			} 
			respo.setReason("Api Call Failed");
			respo.setRequestType(api.getRequestType());
			respoList.add(respo); 
		}
		return null;	
		
	} 


public  APIResponse doGetRequest(String path, String environment, ApiExcelBO api, List<ResponceBO> respoList) {
	try {
		long start = System.currentTimeMillis();
		APIResponse responce = Report.requests.get().get(path);
		long end = System.currentTimeMillis();
		double sec = (end - start) / 1000; 
		if(sec>30) {
			System.out.println("This Api call have more than 30sec of time   actual time :"+sec+" <b> url </b>:"+path);
			ResponceBO respo = new ResponceBO(); 
			 respo.setEnvironmentName(environment);
			 respo.setApiPath(path);
			 respo.setModuleName(api.getModule());
			 respo.setApiCallCode(String.valueOf(responce.status()));
			 respo.setModuleName(api.getModule());
			 respo.setReason("This Api call have more than 30sec of time   actual time in sec :"+sec); 
			 respo.setDescription(api.getDescription());
			 respo.setRequestType(api.getRequestType());
			 respoList.add(respo);
		}
		
		if (responce.status()!=200) {
			 ResponceBO respo = new ResponceBO(); 
			 respo.setEnvironmentName(environment);
			 respo.setApiPath(path);
			 respo.setModuleName(api.getModule());
			 respo.setDescription(api.getDescription());
			 respo.setApiCallCode(String.valueOf(responce.status()));
			 respo.setModuleName(api.getModule());
			 respo.setReason("API call is failed due the following reason :"+ responce.text()); 
			 respo.setRequestType(api.getRequestType());
			 respoList.add(respo);
		} else {
			boolean isJsonObj = JsonParser.parseString(responce.text()).isJsonObject();
			if (isJsonObj) {
				JsonObject jsonObj =JsonParser.parseString(responce.text()).getAsJsonObject();
				String status = getJsonObjectAsString(jsonObj, "status");
				if (! (status.equals("1") || status.equals("0"))) {
					ResponceBO respo = new ResponceBO(); 
					respo.setEnvironmentName(environment);
					respo.setDevResponceCode(status);
					respo.setApiPath(path);
					respo.setModuleName(api.getModule());
					respo.setDescription(api.getDescription());
					respo.setRequestType(api.getRequestType());
					respo.setApiCallCode(String.valueOf(responce.status()));
					if(!getJsonObjectAsString(jsonObj, "message").trim().isEmpty()) {
						respo.setReason(getJsonObjectAsString(jsonObj, "message"));
					}
					if(!getJsonObjectAsString(jsonObj, "reason").trim().isEmpty()) {
						respo.setReason(getJsonObjectAsString(jsonObj, "reason"));
					}
					respo.setApiPath(path); 
					respo.setExceptionMessage(jsonObj.toString()); 
					respoList.add(respo);
				}
			}else {
				ResponceBO respo = new ResponceBO(); 
				respo.setEnvironmentName(environment); 
				respo.setApiPath(path);
				respo.setRequestType(api.getRequestType());
				respo.setModuleName(api.getModule());
				respo.setDescription(api.getDescription());
				respo.setReason("Responce not in json formate :"+responce.text());
				respoList.add(respo);
			}
		}
		return responce;
	}catch (Exception e) {
		System.out.println("$%%%%%%%%%%%#####"+e.getLocalizedMessage());
		ResponceBO respo = new ResponceBO();
		respo.setEnvironmentName(environment);
		respo.setModuleName(api.getModule()); 
		respo.setApiPath(path);
		respo.setDescription(api.getDescription());
		if(e.getMessage()!=null) {
			respo.setExceptionMessage(e.getMessage());
		} 
		respo.setReason("Api Call Failed");
		respo.setRequestType(api.getRequestType());
		respoList.add(respo); 
	}
	return null;	
	
}


private String getJsonObjectAsString(JsonObject envDetail, String key) {
	try {
		if (envDetail.has(key)) {
			return envDetail.get(key).getAsString();
		}else {
			return "";
		}
	}catch (Exception e) {
		
		e.printStackTrace();
		return "";
	} 
}
}