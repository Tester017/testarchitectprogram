package common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo; 
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import bo.ResponceBO;
 
 

public class Report {
	 public  final static ThreadLocal<Playwright> playwrightThread = new ThreadLocal<Playwright>();
	 public  final static ThreadLocal<APIRequestContext> requests = new ThreadLocal<APIRequestContext>();
	
	 public static void  createAPIRequestContext(String baseurl) {  
		    playwrightThread.set(Playwright.create()); 
		    Map<String, String> headers = new HashMap<>();
		    //headers.put("auth_token", authToken);
		    // We set this header per GitHub guidelines.
		    // Add authorization token to all requests.
		    // Assuming personal access token available in the environment. 
		  
		    APIRequestContext req = playwrightThread.get().request().newContext(new APIRequest.NewContextOptions()
		      // All requests we send go to this API endpoint.
		      .setBaseURL(baseurl)
		      .setTimeout(60000)
		      .setExtraHTTPHeaders(headers)
		      .setIgnoreHTTPSErrors(true)
		       
		    );
		    requests.set(req);
	  }
	 
	 public static String convertToEncodedUrlCall(String path, Map<String, String> data) {
			Set<String> keys = data.keySet();
			int index = 0;
			for (String key : keys) {
				if (index == 0) {
					path += "?";
				}
				if(index > 0) {
					path += "&";
				}
				
				try {
					String encodedValue = URLEncoder.encode(data.get(key), "UTF-8");
					path += key + "=" + encodedValue;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				index++;
			}
			System.out.println(path);
			return path;
		}

	public  void reportInExcelLog(ResponceBO res, String environmentName, boolean overAllStatus) {
		String module= "Module";
		String api_Call="Api_Call";
		String description ="Description";
		String request_Type ="Request_Type";
		String api_Responce_Code="Api_Responce_Code";
		String dev_Responce_Code ="Dev_Responce_Code";
		String reason ="Reason";
		String exception_Message ="Exception_Message";
		String environment = "Environment";
		String time ="DateTime";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();   
		String currentTime = dtf.format(now).toString();
		
		Fillo fillo=new Fillo(); 
        Connection connection = null;
		try {
			connection = fillo.getConnection("./src/test/resources/ReportLog.xlsx");
			String query ="";
		    if(overAllStatus) {
		    	query = "INSERT INTO OverAll("+environment+","+module+","+api_Call+","+description+","+request_Type+","
		                + api_Responce_Code+","+dev_Responce_Code+","+reason+","+exception_Message+","+time+") "
		        		+ "VALUES('"+environmentName+"','"+res.getModuleName()+"','"+res.getApiPath()+"','"+res.getDescription()+"','"+res.getRequestType()+"','"
		        		+ res.getApiCallCode()+"','"+res.getApiCallCode()+"','"+res.getReason().replaceAll("[^a-z A-Z 0-9 {\":_.,]", "` ")+"','"+res.getExceptionMessage().replaceAll("[^a-z A-Z 0-9 {\":_.,]", "`")+"','"+currentTime+"')";  
		    }else {
		    	 query = "INSERT INTO "+environmentName+"("+module+","+api_Call+","+description+","+request_Type+","
			                + api_Responce_Code+","+dev_Responce_Code+","+reason+","+exception_Message+","+time+") "
			        		+ "VALUES('"+res.getModuleName()+"','"+res.getApiPath()+"','"+res.getDescription()+"','"+res.getRequestType()+"','"
			        		+ res.getApiCallCode()+"','"+res.getApiCallCode()+"','"+res.getReason().toString().replaceAll("[^a-z A-Z 0-9 {\":_.,]", "` ")+"','"+res.getExceptionMessage().toString().replaceAll("[^a-z A-Z 0-9 {\":_.,]", "` ")+"','"+currentTime+"')";

		    }
		    connection.executeUpdate (query);
        
        
		} catch (Exception e) {
			 
			e.printStackTrace();
		}finally {
			connection.close();
		}
        
		
	}
	 
}
