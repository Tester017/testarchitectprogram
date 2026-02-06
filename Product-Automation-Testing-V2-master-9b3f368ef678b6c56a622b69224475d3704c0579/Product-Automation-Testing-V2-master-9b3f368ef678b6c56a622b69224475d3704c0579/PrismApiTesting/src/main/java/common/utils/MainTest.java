package common.utils;

import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

public class MainTest {

	public static void main(String[] args) throws InterruptedException {
		 SimpleDateFormat d = new SimpleDateFormat("dd / MM / YY");
		    try {
		    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		    	Date convertedCurrentDate = sdf.parse("02/05/2023");
		    	Date convertedCurrentDate2 = sdf2.parse("2023-05-02");
		    	if(convertedCurrentDate.equals(convertedCurrentDate2)) {
		    		System.out.println("same ");
		    	}else {
		    		System.out.println("not same");
		    	}
		    	
		    	String date=sdf.format(convertedCurrentDate );
		    	
		    	
		    	System.out.println(date);
		    } catch (Exception except) {
		      except.printStackTrace();
		    }
		 
	      
		
	}
	
	 
	public static void apiLogin() {
		 String str ="  Delta #year,0 ~ Delta #year,0 % ~  #year,0 ~ Delta #year,0 ~ Delta #year,0 % ~ #year,0 ~ Delta #year,0 ~ Delta #year,0 % ~ Annualized Comm. ~ Prev. Full Year Comm. ";
		 String[] strArray = str.split("~");
		 ArrayList< String> list = new ArrayList<String>();
		 ArrayList< String> tempList = new ArrayList<String>();
		 
		 for(String header: strArray) {
			 list.add(header);
			 
		 }
	 
		 for(int i=0;i<list.size();i++) {
			 
			  if(list.get(i).contains("#year")) {
				  String year[]=  list.get(i).split(",");
				  String value = year[1].substring(0,1);
				  value =list.get(i).replace("#year,"+value, "22222");
				  tempList.add(value);
			  }else {
				  tempList.add(list.get(i));
			  }
		 }
		 
		 System.out.println("#year"+ tempList.toString());
	}
	 
	
	private static void passwordEncrypted() {
		try {
			 String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChOrbpjx+bkEmdY8ruuHbXCCBRl06XzDj4IXcN8bDi5J4HBCsgRa5dMEYrkJXXevCs++OYck3/fgNYVUE5s5l3Twa9H7N5arjnp/dkgD0MEhc4hRpyo2HqE/dk4G1on6c5eNlG4Fe0EBELPD2aShdr6+wyh3xJ/U+Xl17Yqzv5CQIDAQAB";
		     String pass ="Automation@1234";
		    
			X509EncodedKeySpec ks = new X509EncodedKeySpec(base64(publicKey));
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PublicKey pub = kf.generatePublic(ks);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pub);
			String test= Base64.encodeBase64String(cipher.doFinal(pass.getBytes()));
			System.out.println( URLEncoder.encode(test, "UTF-8"));	 
		} catch (Exception e) {
			e.printStackTrace();
			 
		}
		
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
