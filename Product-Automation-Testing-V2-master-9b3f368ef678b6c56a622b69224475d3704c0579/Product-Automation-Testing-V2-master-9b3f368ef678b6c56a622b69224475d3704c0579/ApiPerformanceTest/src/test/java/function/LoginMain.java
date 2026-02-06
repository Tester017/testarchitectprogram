package function;

import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64; 
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;

import bo.ResponceBO;
import common.Report; 
 

public class LoginMain extends Report {

	

	public String loginApplication(JsonObject envDetail, List<ResponceBO> respList) {
		DoPerformanceCheck  doCheck = new DoPerformanceCheck();
		String pass = "";
		try {
			if (envDetail.get(doCheck.isEncrypt).getAsBoolean()) {
				pass = getEncryptPass(envDetail.get(doCheck.password).getAsString()).trim();
			} else {
				pass =  envDetail.get(doCheck.password).getAsString();
			}

			String path= "ws/login/auth";
		    //System.out.println(pass);
		    FormData form = FormData.create()
		    		     .set("uname",envDetail.get(doCheck.userName).getAsString())
		    		     .set("pass", pass)
		    		     .set("logintype", envDetail.get(doCheck.loginType).getAsString());
		    APIResponse responce = requests.get().post(path,
				    RequestOptions.create().setForm(form));
		    
		    System.out.println(responce.text());
			JsonObject jsonObj = JsonParser.parseString(responce.text()).getAsJsonObject();
			// Add auth token to the api contex
			Map<String, String> headers = new HashMap<>();
			headers.put("auth_token", jsonObj.get("auth_token").getAsString());
			// We set this header per GitHub guidelines.
			// Add authorization token to all requests.
			// Assuming personal access token available in the environment.
 
			APIRequestContext req = playwrightThread.get().request().newContext(new APIRequest.NewContextOptions()
					// All requests we send go to this API endpoint.
					.setBaseURL(envDetail.get(doCheck.baseUrl).getAsString()).setTimeout(30000)
					.setExtraHTTPHeaders(headers).setIgnoreHTTPSErrors(true)

			);
			requests.set(req);
			return jsonObj.get("auth_token").getAsString();

		} catch (Exception e) {
			ResponceBO res = new ResponceBO();
			res.setEnvironmentName(envDetail.get(doCheck.environmentName).getAsString());
			res.setReason( "Login Auth Fail please check login credentials in environment.json (or) try to login manually ");
			res.setExceptionMessage(e.getMessage());
			res.setModuleName("Login");
			respList.add(res);
			e.printStackTrace();
			throw new RuntimeException();
		} 
	}
	
	
	public String getEncryptPass(String password) {
		String passWord ="";
		try {
			 String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChOrbpjx+bkEmdY8ruuHbXCCBRl06XzDj4IXcN8bDi5J4HBCsgRa5dMEYrkJXXevCs++OYck3/fgNYVUE5s5l3Twa9H7N5arjnp/dkgD0MEhc4hRpyo2HqE/dk4G1on6c5eNlG4Fe0EBELPD2aShdr6+wyh3xJ/U+Xl17Yqzv5CQIDAQAB";
		     String pass =password;
		    
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


	public void setBaseURlInApiContex(JsonObject envDetail) {
		 
		
	}
	
}
