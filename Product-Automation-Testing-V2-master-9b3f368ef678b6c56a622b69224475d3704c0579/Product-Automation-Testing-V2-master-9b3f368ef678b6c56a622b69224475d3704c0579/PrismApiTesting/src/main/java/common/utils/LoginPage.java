package common.utils;

import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;

import constant.ProductConstant;

public class LoginPage extends Reporter {
	
	
	public static void loginApplication() {
		String pass ="";
		try { 
		 if(ProductConstant.passEncryption) {
			  pass = getEncryptPass().trim();
		 }else {
			 pass = ProductConstant.passWord;
		 }
		
		 String path= "ws/login/auth";
		    //System.out.println(pass);
		    FormData form = FormData.create()
		    		     .set("uname",ProductConstant.userName.trim())
		    		     .set("pass", pass)
		    		     .set("logintype", ProductConstant.loginType);
		    
		   // Map<String, String> data = new HashMap<>();
		   // data.put("uname", ProductConstant.userName.trim());
		   // data.put("pass", ProductConstant.passWord);
		   // data.put("logintype", ProductConstant.loginType);
		    
		    APIResponse responce = requests.get().post(path,RequestOptions.create().setForm(form));
		    
		    System.out.println(responce.text());
			JsonObject jsonObj = JsonParser.parseString(responce.text()).getAsJsonObject();
			authToken = jsonObj.get("auth_token").toString();
			// get the environment details
			ExtentTest parent = getExtentReports().createTest("Environment Details");
			setParentTest(parent);
			createReportNode("Environment Details");
			reportStep("<b>URL : </b> "+ ProductConstant.PrismUrl, "info");
			reportStep("<b>User Name : </b> "+ ProductConstant.userName, "info");
			reportStep("<b>Password : </b> "+ ProductConstant.passWord, "info");
			reportStep("<b>Api base URL : </b> "+ ProductConstant.apiBaseUrl, "info");
			 
		}catch (Exception e) {
			System.out.println("Login Auth Fail please check login credentials (or) try to login manually");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	public static String getEncryptPass() {
		String passWord ="";
		try {
			 String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChOrbpjx+bkEmdY8ruuHbXCCBRl06XzDj4IXcN8bDi5J4HBCsgRa5dMEYrkJXXevCs++OYck3/fgNYVUE5s5l3Twa9H7N5arjnp/dkgD0MEhc4hRpyo2HqE/dk4G1on6c5eNlG4Fe0EBELPD2aShdr6+wyh3xJ/U+Xl17Yqzv5CQIDAQAB";
		     String pass =ProductConstant.passWord;
		    
			X509EncodedKeySpec ks = new X509EncodedKeySpec(base64(publicKey));
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PublicKey pub = kf.generatePublic(ks);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pub);
		    passWord= Base64.encodeBase64String(cipher.doFinal(pass.getBytes()));
		    passWord = URLEncoder.encode(passWord, "UTF-8");
		 
		}catch (Exception e) {
			 e.printStackTrace();
		}
		return passWord;
	}
	/**
	 * Decode base64 values
	 * 
	 * @return
	 * @author
	 * @on 02/04/2019
	 */
	public static byte[] base64(String str) {
		return Base64.decodeBase64(str);
	}


}
